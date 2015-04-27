package server.GestionarBecaAsignada;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import server.GestionarUsuario.ControlUsuario;
import server.assembler.BecaAsignadaAssembler;
import server.assembler.UsuarioAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.BecaAsignada;
import server.persistencia.dominio.Usuario;

import common.Utils;
import common.DTOs.BecaAsignadaDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarBecaAsignada.IControlBecaAsignada;

public class ControlBecaAsignada extends UnicastRemoteObject implements IControlBecaAsignada{
	private static final long serialVersionUID = 1L;

	public ControlBecaAsignada() throws RemoteException{   }

	public Vector obtenerBecasAsignadas()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector becasAsig = new Vector();
		try {
			mp.initPersistencia();
			Vector becas= mp.getAllOrdered(BecaAsignada.class,"usuario.apellido ascending");
			for (Iterator i = becas.iterator(); i.hasNext(); ) {
				BecaAsignadaDTO baDTO = new BecaAsignadaDTO();
				BecaAsignada b = (BecaAsignada)i.next();
				baDTO = BecaAsignadaAssembler.getBecaAsignadaDTO(b);
				baDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(b.getUsuario()));
				becasAsig.add(baDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return becasAsig;
	}

	public Vector obtenerBecasAsignadasDeUsuario(Long idUsr)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector becasAsig = new Vector();
		try {
			mp.initPersistencia();
			String filtro="usuario.id=="+idUsr;
			Vector becas= mp.getObjectsOrdered(BecaAsignada.class,filtro,"fecha ascending");
			for (Iterator i = becas.iterator(); i.hasNext(); ) {
				BecaAsignadaDTO baDTO = new BecaAsignadaDTO();
				BecaAsignada b = (BecaAsignada)i.next();
				baDTO = BecaAsignadaAssembler.getBecaAsignadaDTO(b);
				baDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(b.getUsuario()));
				becasAsig.add(baDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return becasAsig;
	}

	public BecaAsignada buscarBecaActualDeUsuario(ManipuladorPersistencia mp,Long idUsr) throws Exception {
		BecaAsignada bA = new BecaAsignada();
		String filtro="usuario.id=="+idUsr+ " && cierrePlazo==false";
		Vector becas= mp.getObjectsOrdered(BecaAsignada.class,filtro,"fecha ascending");
		if (becas.size()==1){
			Iterator i = becas.iterator();
			bA = (BecaAsignada)i.next();
		}
		return bA;
	}
	
	public boolean hayBecasDelMesAnio(Date hoy) throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		boolean hayBecas=false;
		try {
			mp.initPersistencia();
			String filtro="cierrePlazo==false";
			Vector becas= mp.getObjectsOrdered(BecaAsignada.class,filtro,"fecha ascending");
			for (Iterator i = becas.iterator(); i.hasNext() && !hayBecas; ) {
				BecaAsignada b = (BecaAsignada)i.next();
				if(Utils.getAnio(b.getFecha())==Utils.getAnio(hoy) && Utils.getMes(b.getFecha())==Utils.getMes(hoy)){
					hayBecas=true;
				}
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return hayBecas;
	}

	public boolean cerrarYAbrirNuevasBecas(Date hoy) throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlUsuario cUsr = new ControlUsuario();
		Vector usuarios=cUsr.obtenerUsuarios();
		try {
			mp.initPersistencia();
			String filtro="cierrePlazo==false";
			Vector becas= mp.getObjectsOrdered(BecaAsignada.class,filtro,"fecha ascending");
			for (Iterator i = becas.iterator(); i.hasNext(); ) {
				BecaAsignada b = (BecaAsignada)i.next();
				if(Utils.getAnio(b.getFecha())==Utils.getAnio(hoy) && Utils.getMes(b.getFecha())==Utils.getMes(hoy) && Utils.getDia(b.getFecha())==Utils.getDia(hoy))
					;
				else{
					b.setCierrePlazo(true);
				}
			}
			//abrir
			for (Iterator i = usuarios.iterator(); i.hasNext(); ) {
				UsuarioDTO usrDTO= (UsuarioDTO)i.next();
				BecaAsignada ba = new BecaAsignada();
				mp.hacerPersistente(ba);
				Usuario usr = cUsr.buscarUsuarioId(mp,usrDTO.getId());
				ba.setUsuario(usr);
				ba.setImporteAsignado(usr.getImporteBeca());
				ba.setImporteRestante(usr.getImporteBeca());
				ba.setFecha(Utils.crearFecha(hoy));
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return true;
	}
	
	public boolean abrirBecas(Vector usrSinBeca,Date hoy) throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlUsuario cUsr = new ControlUsuario();
		try {
			mp.initPersistencia();
			//abrir
			for (Iterator i = usrSinBeca.iterator(); i.hasNext(); ) {
				UsuarioDTO usrDTO= (UsuarioDTO)i.next();
				BecaAsignada ba = new BecaAsignada();
				mp.hacerPersistente(ba);
				Usuario usr = cUsr.buscarUsuarioId(mp,usrDTO.getId());
				ba.setUsuario(usr);
				ba.setImporteAsignado(usr.getImporteBeca());
				ba.setImporteRestante(usr.getImporteBeca());
				ba.setFecha(Utils.crearFecha(hoy));
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return true;
	}
	
	public void eliminarBecasDeUsuario(ManipuladorPersistencia mp, Long idUsr)throws Exception{
		String filtro="usuario.id=="+idUsr;
		Vector becasAsing= mp.getObjects(BecaAsignada.class,filtro);
		for (Iterator i = becasAsing.iterator(); i.hasNext(); ) {
			BecaAsignada ba = (BecaAsignada)i.next();
			mp.borrar(ba);
		}
	}

	
}
