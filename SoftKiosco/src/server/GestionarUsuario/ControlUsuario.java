package server.GestionarUsuario;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import server.GestionarBecaAsignada.ControlBecaAsignada;
import server.GestionarLocalidad.ControlLocalidad;
import server.GestionarSesion.ControlSesion;
import server.assembler.FacturaClienteAssembler;
import server.assembler.LocalidadAssembler;
import server.assembler.UsuarioAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.FacturaCliente;
import server.persistencia.dominio.Localidad;
import server.persistencia.dominio.Usuario;
import server.persistencia.dominio.UsuarioPlanillaES;

import common.Utils;
import common.DTOs.FacturaClienteDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarUsuario.IControlUsuario;

public class ControlUsuario extends UnicastRemoteObject implements IControlUsuario{
	private static final long serialVersionUID = 1L;

	public ControlUsuario() throws RemoteException{   }

	public Long agregarUsuarioDTO(UsuarioDTO usr)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlLocalidad cLoc = new ControlLocalidad();
		Usuario unew= UsuarioAssembler.getUsuario(usr);
		Long id= new Long(0);
		try{
			mp.initPersistencia();
			Localidad loc = cLoc.buscarLocalidadId(mp,usr.getLocalidad().getId());
			mp.hacerPersistente(unew);
			unew.setLocalidad(loc);
			id=unew.getId();
			mp.commit();
		} finally {
			mp.rollback();
		}
		return id;
	}

	public void eliminarUsuarioDTO(Long id)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		// Al eliminar un usuario tambien voy a eliminar sus sesiones
		ControlSesion cSes=new ControlSesion();
		ControlBecaAsignada cBAsing=new ControlBecaAsignada();
		Usuario usr=null;
		boolean asoc=usuarioAsociado(id);
		try {
			mp.initPersistencia();
			usr = this.buscarUsuarioId(mp,id);
			cSes.eliminarSesionesDeUsuario(mp,id);
			cBAsing.eliminarBecasDeUsuario(mp,id);
			if(!asoc)
				mp.borrar(usr);
			else
				usr.setEliminado(true);
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public void modificarUsuario(Long id,UsuarioDTO modificado)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try{
			ControlLocalidad cLoc = new ControlLocalidad();
			mp.initPersistencia();
			Usuario usr = this.buscarUsuarioId(mp,id);
			Localidad loc = cLoc.buscarLocalidadId(mp,modificado.getLocalidad().getId());
			usr.setApellido(modificado.getApellido());
			usr.setNombre(modificado.getNombre());
			usr.setDireccion(modificado.getDireccion());
			usr.setTelefono(modificado.getTelefono());
			usr.setTipoUsuario(modificado.getTipoUsuario());
			usr.setContrasenia(modificado.getContrasenia());
			usr.setDni(modificado.getDni());
			usr.setImporteBeca(modificado.getImporteBeca());
			usr.setNombUsuario(modificado.getNombUsuario());
			usr.setLocalidad(loc);
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public Vector obtenerUsuarios()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector Usuarios2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro=" eliminado =="+false;
			Vector usuarios= mp.getObjectsOrdered(Usuario.class,filtro,"apellido ascending");
			for(int i=0; i<usuarios.size();i++){
				UsuarioDTO usrDTO= new UsuarioDTO();
				Usuario usr = (Usuario)usuarios.elementAt(i);
				usrDTO=UsuarioAssembler.getUsuarioDTO(usr);
				usrDTO.setLocalidad(LocalidadAssembler.getLocalidadDTO(usr.getLocalidad()));
				Usuarios2.add(usrDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return Usuarios2;
	}

	public Vector obtenerUsuariosNomyAp(String nom)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector Usuarios2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro=" eliminado =="+false +" && apellido.toLowerCase().indexOf(\""+nom.toLowerCase()+"\")>= 0 || nombre.toLowerCase().indexOf(\""+nom.toLowerCase()+"\")>= 0";
			Vector usuarios= mp.getObjectsOrdered(Usuario.class,filtro,"apellido ascending");
			for(int i=0; i<usuarios.size();i++){
				UsuarioDTO usrDTO= new UsuarioDTO();
				Usuario usr = (Usuario)usuarios.elementAt(i);
				usrDTO=UsuarioAssembler.getUsuarioDTO(usr);
				usrDTO.setLocalidad(LocalidadAssembler.getLocalidadDTO(usr.getLocalidad()));
				Usuarios2.add(usrDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return Usuarios2;
	}

	public Vector obtenerFacturasDeUsuario(Long idUsr)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector FacturaUsuarios2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro = "becario.id=="+idUsr;
			Vector facturaUsuarios= mp.getObjectsOrdered(FacturaCliente.class,filtro,"fechaHora ascending");
			for(int i=0; i<facturaUsuarios.size();i++){
				FacturaClienteDTO fcDTO= new FacturaClienteDTO();
				FacturaCliente fc = (FacturaCliente)facturaUsuarios.elementAt(i);
				fcDTO = FacturaClienteAssembler.getFacturaClienteDTO(fc); 
				fcDTO.setBecario(UsuarioAssembler.getUsuarioDTO(fc.getBecario()));
				FacturaUsuarios2.add(fcDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return FacturaUsuarios2;
	}	
	
	public boolean existeUsuarioDni(int dni)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		boolean existe = false;
		try {
			mp.initPersistencia();
			String filtro = "dni == "+dni;
			Collection UsuarioCol= mp.getObjects(Usuario.class,filtro);
			if (UsuarioCol.size()==1)
				existe=true;
			mp.commit();
		} finally {
			mp.rollback();
		}
		return existe;
	}

	public boolean existeUsuarioNombUsr(String nombUsr)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		boolean existe = false;
		try {
			mp.initPersistencia();
			String filtro = "nombUsuario == \""+nombUsr+"\"";
			Collection UsuarioCol= mp.getObjects(Usuario.class,filtro);
			if (UsuarioCol.size()==1)
				existe=true;
			mp.commit();
		} finally {
			mp.rollback();
		}
		return existe;
	}

	public UsuarioDTO buscarUsuarioDTOId(Long id) throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		UsuarioDTO usrDTO = new UsuarioDTO();
		Usuario usr = new Usuario();
		try{
			mp.initPersistencia();
			String filtro = "id == "+id;
			Collection usuariosCol= mp.getObjects(Usuario.class,filtro);
			if (usuariosCol.size()==1){
				Iterator i = usuariosCol.iterator();
				usr = (Usuario)i.next();
				usrDTO=UsuarioAssembler.getUsuarioDTO(usr);
				usrDTO.setLocalidad(LocalidadAssembler.getLocalidadDTO(usr.getLocalidad()));
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
		return usrDTO;
	}

	public Usuario buscarUsuarioId(ManipuladorPersistencia mp, Long id) throws Exception{
		Usuario usr = new Usuario();
		String filtro = "id == "+id;
		Collection usuariosCol= mp.getObjects(Usuario.class,filtro);
		if (usuariosCol.size()==1){
			Iterator i = usuariosCol.iterator();
			usr = (Usuario)i.next();
		}
		return usr;
	}

	public boolean puedoEditar(UsuarioDTO dto,UsuarioDTO modificado)throws Exception{
		try{
			if (dto.getDni()==modificado.getDni()){
				return true;
			} else {
				if(!this.existeUsuarioDni(modificado.getDni()))
					return true;
				else
					return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean usuarioAsociado(Long id) throws Exception {
		boolean estaAsoc = false;
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try{
			mp.initPersistencia();
			String filtro = "becario != null && becario.id=="+id;
			Collection usuariosCol= mp.getObjects(FacturaCliente.class,filtro);
			if (usuariosCol.size()>0)
				estaAsoc=true;
			String filtro2 = "usuario != null && usuario.id=="+id;
			Collection usuariosPlCol= mp.getObjects(UsuarioPlanillaES.class,filtro2);
			if (usuariosPlCol.size()>0)
				estaAsoc=true;
			mp.commit();
		} finally {
			mp.rollback();
		}
		return estaAsoc;
	}

	public UsuarioDTO verificarUsrContr(Long idUsr,String contr) throws Exception {
		UsuarioDTO usrDTO=null;
		try{
			UsuarioDTO usrVerif=this.buscarUsuarioDTOId(idUsr);
			Utils.setClave("100-3-248-97-234-56-100-241");
			String decriptado=Utils.decriptar(usrVerif.getContrasenia());
			if(decriptado.compareTo(contr)==0)
				usrDTO=usrVerif;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return usrDTO;
	}
	
}
