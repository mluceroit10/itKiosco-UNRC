package server.GestionarFacturaProveedor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import server.GestionarProducto.ControlProducto;
import server.GestionarProveedor.ControlProveedor;
import server.assembler.FacturaProveedorAssembler;
import server.assembler.ItemFacturaAssembler;
import server.assembler.ProductoAssembler;
import server.assembler.ProveedorAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.FacturaProveedor;
import server.persistencia.dominio.ItemFactura;
import server.persistencia.dominio.Producto;
import server.persistencia.dominio.Proveedor;

import common.Utils;
import common.DTOs.FacturaProveedorDTO;
import common.DTOs.ItemFacturaDTO;
import common.GestionarFacturaProveedor.IControlFacturaProveedor;
import common.constantes.TIPO_FACTURA;

public class ControlFacturaProveedor extends UnicastRemoteObject implements IControlFacturaProveedor{
	private static final long serialVersionUID = 1L;

	public ControlFacturaProveedor() throws RemoteException{   }

	public double agregarFacturaProveedorTotal(FacturaProveedorDTO fp)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlProveedor cProv = new ControlProveedor();
		FacturaProveedor fpnew= FacturaProveedorAssembler.getFacturaProveedor(fp);
		ControlProducto cProd = new ControlProducto();
		double result=0;
		try{
			mp.initPersistencia();
			mp.hacerPersistente(fpnew);
			fpnew.setPeriodo(Utils.getMes(fp.getFechaHora())+"-"+Utils.getAnio(fp.getFechaHora()));
			for(int i=0;i<fp.getItems().size();i++){
				ItemFacturaDTO itDTO = (ItemFacturaDTO) fp.getItems().elementAt(i);
				ItemFactura itnew= ItemFacturaAssembler.getItemFactura(itDTO);
				Producto pr= cProd.buscarProductoId(mp,itDTO.getProducto().getId());
				// incremento de stock por cada producto de la factura
				pr.setStockActual(pr.getStockActual()+itDTO.getCantidad());
				mp.hacerPersistente(itnew);
				itnew.setProducto(pr);
				itnew.setFactura(fpnew);
			}
			Proveedor prov = cProv.buscarProveedorId(mp,fp.getProveedor().getId());
			fpnew.setProveedor(prov);
			mp.commit();
		} finally {
			mp.rollback();
		}
		return result;
	}
	
	public Long agregarFacturaProveedor(FacturaProveedorDTO fp)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlProveedor cProv = new ControlProveedor();
		FacturaProveedor fpnew= FacturaProveedorAssembler.getFacturaProveedor(fp);
		Long id= new Long(0);
		try{
			mp.initPersistencia();
			Proveedor prov = cProv.buscarProveedorId(mp,fp.getProveedor().getId());
			mp.hacerPersistente(fpnew);
			fpnew.setProveedor(prov);
			id=fpnew.getId();
			mp.commit();
		} finally {
			mp.rollback();
		}
		return id;
	}

	public Vector obtenerFacturaProveedores(int mesLI,int anioLI)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector FacturaProveedors2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro = " periodo==\""+mesLI+"-"+anioLI+"\" && tipoFactura==\""+TIPO_FACTURA.PROVEEDOR+"\"";
			Vector facturaProveedors= mp.getObjectsOrdered(FacturaProveedor.class,filtro,"fechaHora ascending");
			for(int i=0; i<facturaProveedors.size();i++){
				FacturaProveedorDTO fpDTO= new FacturaProveedorDTO();
				FacturaProveedor fp = (FacturaProveedor)facturaProveedors.elementAt(i);
				fpDTO = FacturaProveedorAssembler.getFacturaProveedorDTO(fp); 
				fpDTO.setProveedor(ProveedorAssembler.getProveedorDTO(fp.getProveedor()));
				FacturaProveedors2.add(fpDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return FacturaProveedors2;
	}

	public Vector obtenerFacturaProveedoresFechaNroyProveedor(int mesLI,int anioLI,String fe,String nro,String nomProv)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector facturasProveedores2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro = " periodo==\""+mesLI+"-"+anioLI+"\" && tipoFactura==\""+TIPO_FACTURA.PROVEEDOR+"\" && "+
			"proveedor.nombre.toLowerCase().indexOf(\""+nomProv.toLowerCase()+"\")>= 0";
			Vector facturaProveedors= mp.getObjectsOrdered(FacturaProveedor.class,filtro,"fechaHora ascending");
			for(int i=0; i<facturaProveedors.size();i++){
				FacturaProveedorDTO fpDTO= new FacturaProveedorDTO();
				FacturaProveedor fp = (FacturaProveedor)facturaProveedors.elementAt(i);
				if(Utils.comienza(common.Utils.getStrUtilDate(fp.getFechaHora()),fe)&&
						(Utils.comienza(String.valueOf(fp.getNroFactura()),nro))){
					fpDTO = FacturaProveedorAssembler.getFacturaProveedorDTO(fp); 
					fpDTO.setProveedor(ProveedorAssembler.getProveedorDTO(fp.getProveedor()));
					facturasProveedores2.add(fpDTO);
				}
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return facturasProveedores2;
	}

	public FacturaProveedorDTO buscarFacturaProveedorDTOId(Long id) throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		FacturaProveedorDTO fpDTO = new FacturaProveedorDTO();
		try {
			mp.initPersistencia();
			String filtro = "id == "+id;
			Vector facturaProveedorCol= mp.getObjects(FacturaProveedor.class,filtro);
			if (facturaProveedorCol.size()>=1){
				FacturaProveedor fp = (FacturaProveedor)facturaProveedorCol.elementAt(0);
				fpDTO = FacturaProveedorAssembler.getFacturaProveedorDTO(fp); 
				fpDTO.setProveedor(ProveedorAssembler.getProveedorDTO(fp.getProveedor()));
				Vector items=new Vector();
				Iterator it=fp.getItems().iterator();
				while(it.hasNext()){
					ItemFactura obj = (ItemFactura) it.next();
					ItemFacturaDTO objDTO=ItemFacturaAssembler.getItemFacturaDTO(obj);
					objDTO.setProducto(ProductoAssembler.getProductoDTO(obj.getProducto()));
					objDTO.setFactura(FacturaProveedorAssembler.getFacturaProveedorDTO((FacturaProveedor)obj.getFactura()));
					items.add(objDTO);
				}
				fpDTO.setItems(items);
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
		return fpDTO;
	}

	public FacturaProveedor buscarFacturaProveedorId(ManipuladorPersistencia mp,Long id) throws Exception {
		FacturaProveedor fp = new FacturaProveedor();
		String filtro = "id == "+id;
		Collection facturaProveedorCol= mp.getObjects(FacturaProveedor.class,filtro);
		if (facturaProveedorCol.size()>=1){
			fp = (FacturaProveedor)(facturaProveedorCol.toArray())[0];
		}
		return fp;
	}

	public Long obtenerNroFacturaProveedor()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		long cod=0;
		try{
			mp.initPersistencia();
			String filtro = "tipoFactura==\""+TIPO_FACTURA.PROVEEDOR+"\"";
			Vector facts= new Vector();
			facts= mp.getObjectsOrdered(FacturaProveedor.class,filtro,"nroFactura descending");
			if(facts.size()==0)cod=Utils.NROFACTURAPROVEEDOR;
			else{
				FacturaProveedor f = (FacturaProveedor)facts.elementAt(0);
				cod=(f.getNroFactura().longValue())+1;
			}
			mp.commit();
			return (new Long(String.valueOf(cod)));
		} finally {
			mp.rollback();
		}
	}

}
