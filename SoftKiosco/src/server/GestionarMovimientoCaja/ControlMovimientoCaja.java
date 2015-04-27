package server.GestionarMovimientoCaja;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import server.GestionarFacturaProveedor.ControlFacturaProveedor;
import server.RealizarPlanillaES.ControlRealizarPlanillaES;
import server.assembler.FacturaProveedorAssembler;
import server.assembler.MovimientoCajaAssembler;
import server.assembler.PlanillaESAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.FacturaProveedor;
import server.persistencia.dominio.MovimientoCaja;

import common.Utils;
import common.DTOs.MovimientoCajaDTO;
import common.DTOs.PlanillaESDTO;
import common.GestionarMovimientoCaja.IControlMovimientoCaja;

public class ControlMovimientoCaja extends UnicastRemoteObject implements IControlMovimientoCaja{
	private static final long serialVersionUID = 1L;
	
	public ControlMovimientoCaja() throws RemoteException{   }

	public Long agregarMovimientoCajaDTO(MovimientoCajaDTO mcDTO)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlFacturaProveedor cFP=new ControlFacturaProveedor();
		MovimientoCaja m = MovimientoCajaAssembler.getMovimientoCaja(mcDTO);
		FacturaProveedor fact= null;
		Long id= new Long(0);
		try{
			mp.initPersistencia();
			if (mcDTO.isConFactura())
				fact = cFP.buscarFacturaProveedorId(mp,mcDTO.getFactura().getId());
			mp.hacerPersistente(m);
			m.setPeriodo(Utils.getMes(mcDTO.getFecha())+"-"+Utils.getAnio(mcDTO.getFecha()));
			if(mcDTO.isConFactura())
				m.setFactura(fact);
			id=m.getId();
			mp.commit();
		} finally {
			mp.rollback();
		}
		return id;
	}

	public void eliminarMovimientoCajaDTO(Long id)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try {
			mp.initPersistencia();
			MovimientoCaja mc = this.buscarMovimientoCajaId(mp,id);
			mp.borrar(mc);
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public Vector obtenerMovimientosCajaBalance()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector movimientos = new Vector();
		try {
			mp.initPersistencia();
			Collection movCol= mp.getAllOrdered(MovimientoCaja.class,"fechaMC ascending");
			for (Iterator i = movCol.iterator(); i.hasNext(); ) {
				MovimientoCajaDTO mcDTO = new MovimientoCajaDTO();
				MovimientoCaja mov = (MovimientoCaja)i.next();
				mcDTO = MovimientoCajaAssembler.getMovimientoCajaDTO(mov);
				if(mov.getPlanilla()!=null)
					mcDTO.setPlanilla(PlanillaESAssembler.getPlanillaESDTO(mov.getPlanilla()));
				else
					mcDTO.setPlanilla(null);
				if(mov.isConFactura())
					mcDTO.setFactura(FacturaProveedorAssembler.getFacturaProveedorDTO(mov.getFactura()));
				movimientos.add(mcDTO);
			}
		} finally {
			mp.rollback();
		}
		return movimientos;
	}
	
	public Vector obtenerMovimientosCaja(int mesLI,int anioLI)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector movimientos = new Vector();
		try {
			mp.initPersistencia();
			String filtro="periodo==\""+mesLI+"-"+anioLI+"\"";
			Collection movCol= mp.getObjectsOrdered(MovimientoCaja.class,filtro,"fechaMC ascending");
			for (Iterator i = movCol.iterator(); i.hasNext(); ) {
				MovimientoCajaDTO mcDTO = new MovimientoCajaDTO();
				MovimientoCaja mov = (MovimientoCaja)i.next();
				mcDTO = MovimientoCajaAssembler.getMovimientoCajaDTO(mov);
				if(mov.getPlanilla()!=null)
					mcDTO.setPlanilla(PlanillaESAssembler.getPlanillaESDTO(mov.getPlanilla()));
				else
					mcDTO.setPlanilla(null);
				if(mov.isConFactura())
					mcDTO.setFactura(FacturaProveedorAssembler.getFacturaProveedorDTO(mov.getFactura()));
				movimientos.add(mcDTO);
			}
		} finally {
			mp.rollback();
		}
		return movimientos;
	}

	public Vector obtenerMovimientosCajaNroyFecha(int mesLI,int anioLI,String nro,String fe)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector movimientos = new Vector();
		try {
			mp.initPersistencia();
			String filtro="periodo==\""+mesLI+"-"+anioLI+"\"";
			Collection movCol= mp.getObjectsOrdered(MovimientoCaja.class,filtro,"fechaMC ascending");
			for (Iterator i = movCol.iterator(); i.hasNext(); ) {
				MovimientoCajaDTO mcDTO = new MovimientoCajaDTO();
				MovimientoCaja mov = (MovimientoCaja)i.next();
				if (Utils.comienza(common.Utils.getStrUtilDate(mov.getFechaMC()),fe)&&
						(Utils.comienza(String.valueOf(mov.getNroRecibo()),nro))) {
					mcDTO = MovimientoCajaAssembler.getMovimientoCajaDTO(mov);
					if(mov.getPlanilla()!=null)
						mcDTO.setPlanilla(PlanillaESAssembler.getPlanillaESDTO(mov.getPlanilla()));
					if(mov.isConFactura())
						mcDTO.setFactura(FacturaProveedorAssembler.getFacturaProveedorDTO(mov.getFactura()));
					movimientos.add(mcDTO);
				}
			}
		} finally {
			mp.rollback();
		}
		return movimientos;
	}

	public Vector obtenerMovimientosCajaPagoProveedor(Long idFactura)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector movimientos = new Vector();
		try {
			mp.initPersistencia();
			String filtro = "conFactura==true && factura.id=="+idFactura;
			Collection movCol= mp.getObjectsOrdered(MovimientoCaja.class,filtro,"fechaMC ascending");
			for (Iterator i = movCol.iterator(); i.hasNext(); ) {
				MovimientoCajaDTO mcDTO = new MovimientoCajaDTO();
				MovimientoCaja mov = (MovimientoCaja)i.next();
				mcDTO = MovimientoCajaAssembler.getMovimientoCajaDTO(mov);
				if(mov.getPlanilla()!=null)
					mcDTO.setPlanilla(PlanillaESAssembler.getPlanillaESDTO(mov.getPlanilla()));
				if(mov.isConFactura())
					mcDTO.setFactura(FacturaProveedorAssembler.getFacturaProveedorDTO(mov.getFactura()));
				movimientos.add(mcDTO);
			}
		} finally {
			mp.rollback();
		}
		return movimientos;
	}

	public boolean existeMovimientoCajaNroRecibo(Long numero)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		boolean existe = false;
		try {
			mp.initPersistencia();
			String filtro = "nroRecibo == "+numero;
			Collection movCol= mp.getObjects(MovimientoCaja.class,filtro);
			if (movCol.size()==1)
				existe=true;
			mp.commit();
		} finally {
			mp.rollback();
		}
		return existe;
	}

	public MovimientoCajaDTO buscarMovimientoCajaDTOId(Long id) throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		MovimientoCajaDTO mcDTO = new MovimientoCajaDTO();
		try{
			MovimientoCaja mov = new MovimientoCaja();
			mp.initPersistencia();
			String filtro = "id == "+id;
			Collection movCol= mp.getObjects(MovimientoCaja.class,filtro);
			mov= (MovimientoCaja)(movCol.toArray())[0];
			mcDTO = MovimientoCajaAssembler.getMovimientoCajaDTO(mov);
			if(mov.getPlanilla()!=null)
				mcDTO.setPlanilla(PlanillaESAssembler.getPlanillaESDTO(mov.getPlanilla()));
			if(mov.isConFactura())
				mcDTO.setFactura(FacturaProveedorAssembler.getFacturaProveedorDTO(mov.getFactura()));
			mp.commit();
		} finally {
			mp.rollback();
		}
		return mcDTO;
	}

	public MovimientoCaja buscarMovimientoCajaId(ManipuladorPersistencia mp, Long id) throws Exception {
		MovimientoCaja mc = new MovimientoCaja();
		String filtro = "id == "+id;
		Collection movCol= mp.getObjects(MovimientoCaja.class,filtro);
		if (movCol.size()>=1){
			mc = (MovimientoCaja)(movCol.toArray())[0];
		}
		return mc;
	}

	public boolean movimientoAsociado(Long id) throws Exception{
		/*ControlRealizarPlanillaES ctrlPlanillaES = new ControlRealizarPlanillaES();
		boolean estaAsoc = false;
		try {
			Vector planillasCol = ctrlPlanillaES.obtenerPlanillasES();
			PlanillaESDTO p;
			for (Iterator i = planillasCol.iterator(); i.hasNext() && !estaAsoc;) {
				p = (PlanillaESDTO)i.next();
				for (Iterator j = p.getMovimientosCaja().iterator(); j.hasNext() && !estaAsoc;) {
					MovimientoCajaDTO movcDTO = (MovimientoCajaDTO) j.next();
					if(movcDTO.getId().equals(id))
						estaAsoc = true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			estaAsoc = true;
		}
		return estaAsoc;
		*/
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		boolean estaAsoc = false;
		MovimientoCaja b = new MovimientoCaja();
		try {
			mp.initPersistencia();
			String filtro = "id == "+id;
			Collection movs= mp.getObjects(MovimientoCaja.class,filtro);
			if (movs.size()>=1){
				b = (MovimientoCaja)(movs.toArray())[0];
				estaAsoc=(b.getPlanilla()!=null);
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
		return estaAsoc;

	} 

	public Long obtenerNroMovCaja() throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		long cod=0;
		try{
			mp.initPersistencia();
			Vector socioCol= mp.getAllOrdered(MovimientoCaja.class,"nroRecibo descending");
			if(socioCol.size()==0)cod=1;
			else{
				MovimientoCaja p = (MovimientoCaja)socioCol.elementAt(0);
				cod=p.getNroRecibo().longValue()+1;
			}
			mp.commit();
			return (new Long(String.valueOf(cod)));
		} finally {
			mp.rollback();
		}
	}	

}
