package server.RealizarPlanillaES;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import server.GestionarFacturaCliente.ControlFacturaCliente;
import server.GestionarMovimientoCaja.ControlMovimientoCaja;
import server.GestionarUsuario.ControlUsuario;
import server.assembler.FacturaClienteAssembler;
import server.assembler.MovimientoCajaAssembler;
import server.assembler.PlanillaESAssembler;
import server.assembler.SesionAssembler;
import server.assembler.UsuarioAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.FacturaCliente;
import server.persistencia.dominio.MovimientoCaja;
import server.persistencia.dominio.PlanillaES;
import server.persistencia.dominio.Sesion;
import server.persistencia.dominio.Usuario;
import server.persistencia.dominio.UsuarioPlanillaES;

import common.Utils;
import common.DTOs.FacturaClienteDTO;
import common.DTOs.MovimientoCajaDTO;
import common.DTOs.PlanillaESDTO;
import common.DTOs.SesionDTO;
import common.DTOs.UsuarioDTO;
import common.DTOs.UsuarioPlanillaESAssembler;
import common.DTOs.UsuarioPlanillaESDTO;
import common.RealizarPlanillaES.IControlRealizarPlanillaES;

public class ControlRealizarPlanillaES extends UnicastRemoteObject implements IControlRealizarPlanillaES{
	private static final long serialVersionUID = 1L;
	
	public ControlRealizarPlanillaES() throws RemoteException{   }
	
	public void agregarPlanillaESDTOTotal(PlanillaESDTO pDTO,Vector movimientos, Vector facturas, Vector usrTrab) throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		PlanillaES pl = PlanillaESAssembler.getPlanillaES(pDTO);
		ControlMovimientoCaja cMC = new ControlMovimientoCaja();
		ControlFacturaCliente cFC = new ControlFacturaCliente();
		ControlUsuario cUsr = new ControlUsuario();
		try{
			mp.initPersistencia();
			mp.hacerPersistente(pl);
			pl.setPeriodo(Utils.getMes(pDTO.getFecha())+"-"+Utils.getAnio(pDTO.getFecha()));
			// setea movimientos
			for(Iterator i = movimientos.iterator(); i.hasNext();){
				MovimientoCajaDTO mc=(MovimientoCajaDTO)i.next();
				MovimientoCaja mov = cMC.buscarMovimientoCajaId(mp,mc.getId());
				mov.setPlanilla(pl);
			}
			// setea facturas
			for(int j = 0; j < facturas.size();j++){
				FacturaClienteDTO fc=(FacturaClienteDTO)facturas.elementAt(j);
				FacturaCliente f = cFC.buscarFacturaClienteId(mp,fc.getId());
				f.setPlanilla(pl);
			}
			// crea y vincula los usuarios q poseen sesiones abiertas con la planilla.
			for(int k = 0; k < usrTrab.size();k++){
				UsuarioDTO usrDTO=(UsuarioDTO)usrTrab.elementAt(k);
				UsuarioPlanillaES up= new UsuarioPlanillaES();
				mp.hacerPersistente(up);
				up.setPlanillaES(pl);
				Usuario usr = cUsr.buscarUsuarioId(mp,usrDTO.getId());
				up.setUsuario(usr);
			}
			// cierra las sesiones abiertas
			java.util.Date hoy=new java.util.Date();
			Timestamp cierre=Utils.crearFecha(hoy);
			String filtro=" inicio != null && cierre == null ";
			Vector sesions= mp.getObjectsOrdered(Sesion.class,filtro,"inicio ascending");
			for (Iterator i = sesions.iterator(); i.hasNext();) {
				Sesion loc = (Sesion)i.next();
				loc.setCierre(cierre);
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
	}
		
	public void eliminarPlanillaESDTO(Long id)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try {
			mp.initPersistencia();
			// Primero elimino los trabajadores de la planilla
			String filtro="planillaES.id=="+id;
			Vector trabajando= mp.getObjects(UsuarioPlanillaES.class,filtro);
			for (Iterator i = trabajando.iterator(); i.hasNext(); ) {
				UsuarioPlanillaES up = (UsuarioPlanillaES)i.next();
				mp.borrar(up);
			}
			//Luego borro la planilla
			PlanillaES p = this.buscarPlanillaESId(mp,id);
			mp.borrar(p);
			mp.commit();
		}finally{
			mp.rollback();
		}
	}
	
	public Vector obtenerPlanillasES(int mesLI,int anioLI)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector planillas = new Vector();
		try {
			mp.initPersistencia();
			String filtro = " periodo==\""+mesLI+"-"+anioLI+"\"";
			Collection planillasCol= mp.getObjectsOrdered(PlanillaES.class,filtro,"nroPlanilla ascending");
			for (Iterator i = planillasCol.iterator(); i.hasNext(); ) {
				PlanillaESDTO pDTO = new PlanillaESDTO();
				PlanillaES p = (PlanillaES)i.next();
				pDTO = PlanillaESAssembler.getPlanillaESDTO(p);
				pDTO.setMovimientosCaja(MovimientoCajaAssembler.getVector(p.getMovimientosCaja()));
				Vector usrTrab=new Vector();
				Iterator it=p.getUsuariosTrab().iterator();
				while(it.hasNext()){
					UsuarioPlanillaES obj = (UsuarioPlanillaES) it.next();
					UsuarioPlanillaESDTO psDTO=UsuarioPlanillaESAssembler.getUsuarioPlanillaESDTO(obj);
					psDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(obj.getUsuario()));
					psDTO.setPlanillaES(PlanillaESAssembler.getPlanillaESDTO(obj.getPlanillaES()));
					usrTrab.add(psDTO);
				}
				pDTO.setUsuariosTrab(usrTrab);
				planillas.add(pDTO);
			}
		}finally{
			mp.rollback();
		}
		return planillas;
	}
	
	public Vector obtenerPlanillasESFecha(int mesLI,int anioLI,String fe)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector planillas = new Vector();
		try {
			mp.initPersistencia();
			String filtro = " periodo==\""+mesLI+"-"+anioLI+"\"";
			Collection planillasCol= mp.getObjectsOrdered(PlanillaES.class,filtro,"nroPlanilla ascending");
			for (Iterator i = planillasCol.iterator(); i.hasNext(); ) {
				PlanillaESDTO pDTO = new PlanillaESDTO();
				PlanillaES p = (PlanillaES)i.next();
				if (Utils.comienza(common.Utils.getStrUtilDate(p.getFecha()), fe)) {
					pDTO = PlanillaESAssembler.getPlanillaESDTO(p);
					pDTO.setMovimientosCaja(MovimientoCajaAssembler.getVector(p.getMovimientosCaja()));
					Vector usrTrab=new Vector();
					Iterator it=p.getUsuariosTrab().iterator();
					while(it.hasNext()){
						UsuarioPlanillaES obj = (UsuarioPlanillaES) it.next();
						UsuarioPlanillaESDTO psDTO=UsuarioPlanillaESAssembler.getUsuarioPlanillaESDTO(obj);
						psDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(obj.getUsuario()));
						psDTO.setPlanillaES(PlanillaESAssembler.getPlanillaESDTO(obj.getPlanillaES()));
						usrTrab.add(psDTO);
					}
					pDTO.setUsuariosTrab(usrTrab);
					planillas.add(pDTO);
				}
			}
		}finally{
			mp.rollback();
		}
		return planillas;
	}
	
	public Vector obtenerPlanillasESNro(int mesLI,int anioLI,String nro)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector planillas = new Vector();
		try {
			mp.initPersistencia();
			String filtro = " periodo==\""+mesLI+"-"+anioLI+"\"";
			Collection planillasCol= mp.getObjectsOrdered(PlanillaES.class,filtro,"nroPlanilla ascending");
			for (Iterator i = planillasCol.iterator(); i.hasNext(); ) {
				PlanillaESDTO pDTO = new PlanillaESDTO();
				PlanillaES p = (PlanillaES)i.next();
				if (Utils.comienza(String.valueOf(p.getNroPlanilla()),nro)) {
					pDTO = PlanillaESAssembler.getPlanillaESDTO(p);
					pDTO.setMovimientosCaja(MovimientoCajaAssembler.getVector(p.getMovimientosCaja()));
					Vector usrTrab=new Vector();
					Iterator it=p.getUsuariosTrab().iterator();
					while(it.hasNext()){
						UsuarioPlanillaES obj = (UsuarioPlanillaES) it.next();
						UsuarioPlanillaESDTO psDTO=UsuarioPlanillaESAssembler.getUsuarioPlanillaESDTO(obj);
						psDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(obj.getUsuario()));
						psDTO.setPlanillaES(PlanillaESAssembler.getPlanillaESDTO(obj.getPlanillaES()));
						usrTrab.add(psDTO);
					}
					pDTO.setUsuariosTrab(usrTrab);
					planillas.add(pDTO);
				}
			}
		}finally{
			mp.rollback();
		}
		return planillas;
	}
	
	public Vector obtenerMovimientosCajaBalance(Timestamp fechaH)throws Exception{
		Vector movimientos = new Vector();
		ControlMovimientoCaja cMov= new ControlMovimientoCaja();
		Vector todosMov=cMov.obtenerMovimientosCajaBalance();
		try {
			for (Iterator i = todosMov.iterator(); i.hasNext(); ) {
				MovimientoCajaDTO mov = (MovimientoCajaDTO)i.next();
				if(mov.getPlanilla()==null){
					// Pido todos los movimientos de caja cuya fecha/hora sea anterior o igual a la de la hr planilla.
					if(((mov.getFecha()).before(fechaH)) || ((mov.getFecha()).equals(fechaH)))  
						movimientos.add(mov);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return movimientos;
	}
	
	public Vector obtenerFacturasClienteBalance(Timestamp fechaH)throws Exception{
		Vector facturas = new Vector();
		ControlFacturaCliente cFC= new ControlFacturaCliente();
		Vector todasFact=cFC.obtenerFacturasClienteBalance();
		try {
			for (Iterator i = todasFact.iterator(); i.hasNext(); ) {
				FacturaClienteDTO fc = (FacturaClienteDTO)i.next();
				if(fc.getPlanilla()==null && !fc.isACargoBecario()){
					// Pido todos los movimientos de caja cuya fecha/hora sea anterior o igual a la de la hr planilla.
					if(((fc.getFechaHora()).before(fechaH)) || ((fc.getFechaHora()).equals(fechaH)))  
						facturas.add(fc);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return facturas;
	}
	
	public Vector obtenerUsuarioParaPlanillaES(Timestamp fechaAnterior) throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector usuarios = new Vector();
		Vector sesions2 = new Vector();
		try {
			mp.initPersistencia();
			String filtroUsrTrab=" inicio != null && cierre == null ";
			Vector sesions= mp.getObjectsOrdered(Sesion.class,filtroUsrTrab,"inicio ascending");
			for (Iterator i = sesions.iterator(); i.hasNext(); ) {
				SesionDTO lDTO = new SesionDTO();
				Sesion loc = (Sesion)i.next();
				lDTO = SesionAssembler.getSesionDTO(loc);
				lDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(loc.getUsuario()));
				sesions2.add(lDTO);
			}
			String filtroUsrCerraronSes=" inicio != null && cierre != null ";
			Vector sesiones= mp.getObjectsOrdered(Sesion.class,filtroUsrCerraronSes,"inicio ascending");
			for (Iterator i = sesiones.iterator(); i.hasNext(); ) {
				Sesion loc = (Sesion)i.next();
				if(loc.getInicio().after(fechaAnterior)){
					SesionDTO lDTO = new SesionDTO();
					lDTO = SesionAssembler.getSesionDTO(loc);
					lDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(loc.getUsuario()));
					sesions2.add(lDTO);
				}
			}
			for(int k=0; k<sesions2.size(); k++){
				SesionDTO sesDTO = (SesionDTO) sesions2.elementAt(k);
				boolean noPertenece=true;
				for(int j=0;j<usuarios.size() && noPertenece; j++){
					UsuarioDTO usrDTO=(UsuarioDTO) usuarios.elementAt(j);
					if(usrDTO.getId().equals(sesDTO.getUsuario().getId())) noPertenece=false;
				}
				if(noPertenece) usuarios.add(sesDTO.getUsuario());
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return usuarios;
	}
	
	public PlanillaESDTO buscarPlanillaESDTONroPlanilla(int numero) throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		PlanillaESDTO pDTO = new PlanillaESDTO();
		try{
			PlanillaES p = new PlanillaES();
			mp.initPersistencia();
			String filtro = "nroPlanilla == "+numero;
			Collection pCol= mp.getObjects(PlanillaES.class,filtro);
			p= (PlanillaES)(pCol.toArray())[0];
			pDTO = PlanillaESAssembler.getPlanillaESDTO(p);
			pDTO.setMovimientosCaja(MovimientoCajaAssembler.getVector(p.getMovimientosCaja()));
			Vector usrTrab=new Vector();
			Iterator it=p.getUsuariosTrab().iterator();
			while(it.hasNext()){
				UsuarioPlanillaES obj = (UsuarioPlanillaES) it.next();
				UsuarioPlanillaESDTO psDTO=UsuarioPlanillaESAssembler.getUsuarioPlanillaESDTO(obj);
				psDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(obj.getUsuario()));
				psDTO.setPlanillaES(PlanillaESAssembler.getPlanillaESDTO(obj.getPlanillaES()));
				usrTrab.add(psDTO);
			}
			pDTO.setUsuariosTrab(usrTrab);
			mp.commit();
		} finally {
			mp.rollback();
		}
		return pDTO;
	}
	
	public PlanillaESDTO buscarPlanillaESDTOId(Long id) throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		PlanillaESDTO pDTO = new PlanillaESDTO();
		try{
			PlanillaES p = new PlanillaES();
			mp.initPersistencia();
			String filtro = "id == "+id;
			Collection pCol= mp.getObjects(PlanillaES.class,filtro);
			p= (PlanillaES)(pCol.toArray())[0];
			pDTO = PlanillaESAssembler.getPlanillaESDTO(p);
			pDTO.setMovimientosCaja(MovimientoCajaAssembler.getVector(p.getMovimientosCaja()));
			pDTO.setFacturas(FacturaClienteAssembler.getVector(p.getFacturas()));
			Vector usrTrab=new Vector();
			Iterator it=p.getUsuariosTrab().iterator();
			while(it.hasNext()){
				UsuarioPlanillaES obj = (UsuarioPlanillaES) it.next();
				UsuarioPlanillaESDTO psDTO=UsuarioPlanillaESAssembler.getUsuarioPlanillaESDTO(obj);
				psDTO.setUsuario(UsuarioAssembler.getUsuarioDTO(obj.getUsuario()));
				psDTO.setPlanillaES(PlanillaESAssembler.getPlanillaESDTO(obj.getPlanillaES()));
				usrTrab.add(psDTO);
			}
			pDTO.setUsuariosTrab(usrTrab);
			mp.commit();
		} finally {
			mp.rollback();
		}
		return pDTO;
	}
	
	public PlanillaES buscarPlanillaESId(ManipuladorPersistencia mp, Long id) throws Exception {
		PlanillaES p = new PlanillaES();
		String filtro = "id == "+id;
		Collection pCol= mp.getObjects(PlanillaES.class,filtro);
		if (pCol.size()>=1){
			p = (PlanillaES)(pCol.toArray())[0];
		}
		return p;
	}
	
	public PlanillaESDTO obtenerUltimaPlanilla()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		PlanillaESDTO pDTO = new PlanillaESDTO();
		try{
			mp.initPersistencia();
			Collection pCol= mp.getAllOrdered(PlanillaES.class,"nroPlanilla descending");
			if(pCol.size()==0){
				pDTO.setNroPlanilla(Utils.NROPLANILLAANTERIOR);
				pDTO.setSaldoCajaGeneral(Utils.SALDOANTERIOR);
				pDTO.setFecha(new Timestamp(0,0,0,0,0,0,0));
			}
			else{
				PlanillaES p = (PlanillaES)(pCol.toArray())[0];
				pDTO = PlanillaESAssembler.getPlanillaESDTO(p);
			}
			mp.commit();
			return pDTO;
		} finally {
			mp.rollback();
		}
	}
				
}
