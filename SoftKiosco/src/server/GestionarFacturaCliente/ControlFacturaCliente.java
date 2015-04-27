package server.GestionarFacturaCliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import server.GestionarBecaAsignada.ControlBecaAsignada;
import server.GestionarProducto.ControlProducto;
import server.GestionarUsuario.ControlUsuario;
import server.assembler.FacturaClienteAssembler;
import server.assembler.ItemFacturaAssembler;
import server.assembler.PlanillaESAssembler;
import server.assembler.ProductoAssembler;
import server.assembler.UsuarioAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.BecaAsignada;
import server.persistencia.dominio.FacturaCliente;
import server.persistencia.dominio.ItemFactura;
import server.persistencia.dominio.MovimientoCaja;
import server.persistencia.dominio.Producto;
import server.persistencia.dominio.Usuario;

import common.Utils;
import common.DTOs.FacturaClienteDTO;
import common.DTOs.ItemFacturaDTO;
import common.GestionarFacturaCliente.IControlFacturaCliente;
import common.constantes.TIPO_FACTURA;

public class ControlFacturaCliente extends UnicastRemoteObject implements IControlFacturaCliente{
	private static final long serialVersionUID = 1L;

	public ControlFacturaCliente() throws RemoteException{   }

	public double agregarFacturaClienteTotal(FacturaClienteDTO fc)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlUsuario cUsr = new ControlUsuario();
		FacturaCliente fcnew= FacturaClienteAssembler.getFacturaCliente(fc);
		ControlProducto cProd = new ControlProducto();
		ControlBecaAsignada cBA= new ControlBecaAsignada();
		Usuario cte = null;
		double result=0;
		
		try{
			mp.initPersistencia();
			mp.hacerPersistente(fcnew);
			fcnew.setPeriodo(Utils.getMes(fc.getFechaHora())+"-"+Utils.getAnio(fc.getFechaHora()));
			for(int i=0;i<fc.getItems().size();i++){
				ItemFacturaDTO itDTO = (ItemFacturaDTO) fc.getItems().elementAt(i);
				ItemFactura itnew= ItemFacturaAssembler.getItemFactura(itDTO);
				Producto pr= cProd.buscarProductoId(mp,itDTO.getProducto().getId());
				// decremento de stock por cada producto de la factura
				pr.setStockActual(pr.getStockActual()-itDTO.getCantidad());
				mp.hacerPersistente(itnew);
				itnew.setProducto(pr);
				itnew.setFactura(fcnew);
			}
			if(fc.getBecario()!=null){
				cte = cUsr.buscarUsuarioId(mp,fc.getBecario().getId());
				fcnew.setBecario(cte);
				BecaAsignada bAsig=cBA.buscarBecaActualDeUsuario(mp,fc.getBecario().getId());
				double impRest=Utils.redondear(bAsig.getImporteRestante()-fc.getImporteTotal(),2);
				result=impRest;
				bAsig.setImporteRestante(impRest);
			}	
			mp.commit();
		} finally {
			mp.rollback();
		}
		return result;
	}
	
	public Vector obtenerFacturasClienteBalance()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector facturaClientes2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro = " tipoFactura==\""+TIPO_FACTURA.CLIENTE+"\"";
			Vector facturaClientes= mp.getObjectsOrdered(FacturaCliente.class,filtro,"fechaHora ascending");
			for(int i=0; i<facturaClientes.size();i++){
				FacturaClienteDTO fcDTO= new FacturaClienteDTO();
				FacturaCliente fc = (FacturaCliente)facturaClientes.elementAt(i);
				fcDTO = FacturaClienteAssembler.getFacturaClienteDTO(fc);
				if(fc.getBecario()!=null)
					fcDTO.setBecario(UsuarioAssembler.getUsuarioDTO(fc.getBecario()));
				else
					fcDTO.setBecario(null);
				if(fc.getPlanilla()!=null)
					fcDTO.setPlanilla(PlanillaESAssembler.getPlanillaESDTO(fc.getPlanilla()));
				else
					fcDTO.setPlanilla(null);
				facturaClientes2.add(fcDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return facturaClientes2;
	}
	
	public Vector obtenerFacturasCliente(int mesLI,int anioLI)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector facturaClientes2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro = " periodo==\""+mesLI+"-"+anioLI+"\" && tipoFactura==\""+TIPO_FACTURA.CLIENTE+"\"";
			Vector facturaClientes= mp.getObjectsOrdered(FacturaCliente.class,filtro,"fechaHora ascending");
			for(int i=0; i<facturaClientes.size();i++){
				FacturaClienteDTO fcDTO= new FacturaClienteDTO();
				FacturaCliente fc = (FacturaCliente)facturaClientes.elementAt(i);
				fcDTO = FacturaClienteAssembler.getFacturaClienteDTO(fc);
				if(fc.getBecario()!=null)
					fcDTO.setBecario(UsuarioAssembler.getUsuarioDTO(fc.getBecario()));
				else
					fcDTO.setBecario(null);
				if(fc.getPlanilla()!=null)
					fcDTO.setPlanilla(PlanillaESAssembler.getPlanillaESDTO(fc.getPlanilla()));
				else
					fcDTO.setPlanilla(null);
				facturaClientes2.add(fcDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return facturaClientes2;
	}

	public Vector obtenerFacturasClienteFechaNroyVendedor(int mesLI,int anioLI,String fe,String nro,String nomVend)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector facturasClientes2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro = " periodo==\""+mesLI+"-"+anioLI+"\" && tipoFactura==\""+TIPO_FACTURA.CLIENTE+"\" ";
			if(nomVend.length()>0)
				filtro +="&& becario.apellido.toLowerCase().indexOf(\""+nomVend.toLowerCase()+"\")>= 0 || becario.nombre.toLowerCase().indexOf(\""+nomVend.toLowerCase()+"\")>= 0";
			Vector facturaClientes= mp.getObjectsOrdered(FacturaCliente.class,filtro,"fechaHora ascending");
			for(int i=0; i<facturaClientes.size();i++){
				FacturaClienteDTO fcDTO= new FacturaClienteDTO();
				FacturaCliente fc = (FacturaCliente)facturaClientes.elementAt(i);
				if(Utils.comienza(common.Utils.getStrUtilDate(fc.getFechaHora()),fe) && (Utils.comienza(String.valueOf(fc.getNroFactura()),nro))){
					fcDTO = FacturaClienteAssembler.getFacturaClienteDTO(fc); 
					if(fc.getBecario()!=null)
						fcDTO.setBecario(UsuarioAssembler.getUsuarioDTO(fc.getBecario()));
					facturasClientes2.add(fcDTO);
				}
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return facturasClientes2;
	}

	public FacturaClienteDTO buscarFacturaClienteDTOId(Long id) throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		FacturaClienteDTO fcDTO = new FacturaClienteDTO();
		try {
			mp.initPersistencia();
			String filtro = "id == "+id;
			Vector facturaClienteCol= mp.getObjects(FacturaCliente.class,filtro);
			if (facturaClienteCol.size()>=1){
				FacturaCliente fc = (FacturaCliente)facturaClienteCol.elementAt(0);
				fcDTO = FacturaClienteAssembler.getFacturaClienteDTO(fc); 
				if(fc.getBecario()!=null)
					fcDTO.setBecario(UsuarioAssembler.getUsuarioDTO(fc.getBecario()));
				Vector items=new Vector();
				Iterator it=fc.getItems().iterator();
				while(it.hasNext()){
					ItemFactura obj = (ItemFactura) it.next();
					ItemFacturaDTO objDTO=ItemFacturaAssembler.getItemFacturaDTO(obj);
					objDTO.setProducto(ProductoAssembler.getProductoDTO(obj.getProducto()));
					objDTO.setFactura(FacturaClienteAssembler.getFacturaClienteDTO((FacturaCliente)obj.getFactura()));
					items.add(objDTO);
				}
				fcDTO.setItems(items);
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
		return fcDTO;
	}

	public FacturaCliente buscarFacturaClienteId(ManipuladorPersistencia mp,Long id) throws Exception {
		FacturaCliente fc = new FacturaCliente();
		String filtro = "id == "+id;
		Collection FacturaClienteCol= mp.getObjects(FacturaCliente.class,filtro);
		if (FacturaClienteCol.size()>=1){
			fc = (FacturaCliente)(FacturaClienteCol.toArray())[0];
		}
		return fc;
	}

	public Long obtenerNroFacturaCliente()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		long cod=0;
		try{
			mp.initPersistencia();
			String filtro = "tipoFactura==\""+TIPO_FACTURA.CLIENTE+"\"";
			Vector facts= new Vector();
			facts= mp.getObjectsOrdered(FacturaCliente.class,filtro,"nroFactura descending");
			if(facts.size()==0)cod=Utils.NROFACTURACLIENTE;
			else{
				FacturaCliente f = (FacturaCliente)facts.elementAt(0);
				cod=(f.getNroFactura().longValue())+1;
			}
			mp.commit();
			return (new Long(String.valueOf(cod)));
		} finally {
			mp.rollback();
		}
	}

}
