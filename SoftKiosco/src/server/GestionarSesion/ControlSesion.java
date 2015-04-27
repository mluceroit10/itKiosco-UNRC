package server.GestionarSesion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import server.GestionarUsuario.ControlUsuario;
import server.assembler.SesionAssembler;
import server.assembler.UsuarioAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.Sesion;
import server.persistencia.dominio.Usuario;

import common.Utils;
import common.DTOs.SesionDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarSesion.IControlSesion;

public class ControlSesion extends UnicastRemoteObject implements IControlSesion{
	private static final long serialVersionUID = 1L;

	public ControlSesion() throws RemoteException{   }

	public Long agregarSesionDTO(SesionDTO locDTO)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlUsuario cUsr = new ControlUsuario();
		Sesion lnew= SesionAssembler.getSesion(locDTO);
		Long id= new Long(0);
		try{
			mp.initPersistencia();
			Usuario usr = cUsr.buscarUsuarioId(mp,locDTO.getUsuario().getId());
			mp.hacerPersistente(lnew);
			lnew.setUsuario(usr);
			id=lnew.getId();
			mp.commit();
		} finally {
			mp.rollback();
		}
		return id;
	}
	
	public void eliminarSesionesDeUsuario(ManipuladorPersistencia mp, Long idUsr)throws Exception{
		String filtro="usuario.id=="+idUsr;
		Vector sesiones= mp.getObjects(Sesion.class,filtro);
		for (Iterator i = sesiones.iterator(); i.hasNext(); ) {
			Sesion s = (Sesion)i.next();
			mp.borrar(s);
		}
	}

	public void modificarSesion(Long id,SesionDTO modificado)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try{
			ControlUsuario cUsr = new ControlUsuario();
			mp.initPersistencia();
			Usuario usr = cUsr.buscarUsuarioId(mp,modificado.getUsuario().getId());
			Sesion sesion = buscarSesionId(mp,id);
			sesion.setInicio(modificado.getInicio());
			sesion.setCierre(modificado.getCierre());
			sesion.setUsuario(usr);
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public Vector obtenerSesionesUsuario(Long idUsr,int dia,int mes, int anio)throws Exception{ 
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector sesions2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro="usuario.id=="+idUsr;
			Vector sesiones= mp.getObjectsOrdered(Sesion.class,filtro,"inicio ascending");
			for (Iterator i = sesiones.iterator(); i.hasNext(); ) {
				SesionDTO lDTO = new SesionDTO();
				Sesion loc = (Sesion)i.next();
				if(Utils.getDia(loc.getInicio())==dia && Utils.getMes(loc.getInicio())==mes && Utils.getAnio(loc.getInicio())==anio && loc.getCierre()!=null){	
					lDTO = SesionAssembler.getSesionDTO(loc);
					lDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(loc.getUsuario()));
					sesions2.add(lDTO);
				}
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return sesions2;
	}

	public Vector obtenerUsuariosNoConectados() throws Exception{
		ControlUsuario cUsrs= new ControlUsuario();
		Vector usuarios = cUsrs.obtenerUsuarios();
		Vector sesAbiertas=this.obtenerSesionesAbiertas();
		Vector usrsNoConectados = new Vector();
		for (int i=0; i<usuarios.size(); i++ ) {
			UsuarioDTO uDTO=(UsuarioDTO)usuarios.elementAt(i);
			boolean pertenece=false;
			for(int j=0;j<sesAbiertas.size() && !pertenece;j++){
				SesionDTO lDTO = (SesionDTO) sesAbiertas.elementAt(j);
				if( uDTO.getId().equals(lDTO.getUsuario().getId())) pertenece=true;
			}
			if(!pertenece)
				usrsNoConectados.add(uDTO);
		}
		return usrsNoConectados;
	}

	public Vector obtenerSesionesAbiertas()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector sesions2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro=" inicio != null && cierre == null ";
			Vector sesions= mp.getObjectsOrdered(Sesion.class,filtro,"inicio ascending");
			for (Iterator i = sesions.iterator(); i.hasNext(); ) {
				SesionDTO lDTO = new SesionDTO();
				Sesion loc = (Sesion)i.next();
				lDTO = SesionAssembler.getSesionDTO(loc);
				lDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(loc.getUsuario()));
				sesions2.add(lDTO);
			}
			mp.commit();
		} finally{
			mp.rollback();
		}
		return sesions2;
	}

	public SesionDTO buscarSesionDTOId(Long id) throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		SesionDTO lDTO = new SesionDTO();
		Sesion loc = new Sesion();
		try{
			mp.initPersistencia();
			String filtro = "id == "+id;
			Collection sesionCol= mp.getObjects(Sesion.class,filtro);
			if (sesionCol.size()==1){
				Iterator i = sesionCol.iterator();
				loc = (Sesion)i.next();
				lDTO=SesionAssembler.getSesionDTO(loc);
				lDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(loc.getUsuario()));
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
		return lDTO;
	}

	public Sesion buscarSesionId(ManipuladorPersistencia mp,Long id) throws Exception{
		Sesion ses = new Sesion();
		String filtro = "id == "+id;
		Collection sesionCol= mp.getObjects(Sesion.class,filtro);
		if (sesionCol.size()==1){
			Iterator i = sesionCol.iterator();
			ses = (Sesion)i.next();
		}
		return ses;
	}

	public void cerrarSesionesAbiertas()throws Exception{
		java.util.Date hoy=new java.util.Date();
		Timestamp cierre=Utils.crearFecha(hoy);
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try {
			mp.initPersistencia();
			String filtro=" inicio != null && cierre == null ";
			Vector sesions= mp.getObjectsOrdered(Sesion.class,filtro,"inicio ascending");
			for (Iterator i = sesions.iterator(); i.hasNext(); ) {
				Sesion loc = (Sesion)i.next();
				loc.setCierre(cierre);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
	}

}
