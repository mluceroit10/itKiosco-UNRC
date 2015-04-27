package server.GestionarProducto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import server.GestionarProveedor.ControlProveedor;
import server.assembler.ProductoAssembler;
import server.assembler.ProveedorAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.ItemFactura;
import server.persistencia.dominio.Producto;
import server.persistencia.dominio.Proveedor;

import common.Utils;
import common.DTOs.ProductoDTO;
import common.GestionarProducto.IControlProducto;

public class ControlProducto extends UnicastRemoteObject implements IControlProducto{
	private static final long serialVersionUID = 1L;

	public ControlProducto() throws RemoteException{   }

	public Long agregarProductoDTO(ProductoDTO p)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlProveedor cProv = new ControlProveedor();
		Producto pnew= ProductoAssembler.getProducto(p);
		Long id= new Long(0);
		try{
			mp.initPersistencia();
			Proveedor prov = cProv.buscarProveedorId(mp,p.getProveedor().getId());
			mp.hacerPersistente(pnew);
			pnew.setProveedor(prov);
			id=pnew.getId();
			mp.commit();
		} finally {
			mp.rollback();
		}
		return id;
	}

	public void eliminarProductoDTO(Long id)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Producto pro = null;
		boolean asoc = productoAsociado(id);
		try {
			mp.initPersistencia();
			pro = this.buscarProductoId(mp,id);
			if(!asoc)
				mp.borrar(pro);
			else
				pro.setEliminado(true);
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public void modificarProducto(Long id,ProductoDTO modificado)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try{
			ControlProveedor cProv = new ControlProveedor();
			mp.initPersistencia();
			Producto producto = buscarProductoId(mp,id);
			Proveedor proveedor = cProv.buscarProveedorId(mp,modificado.getProveedor().getId());
			producto.setCodigo(modificado.getCodigo());
			producto.setNombre(modificado.getNombre());
			producto.setMargenGanancia(modificado.getMargenGanancia());
			producto.setPrecioEntrada(modificado.getPrecioEntrada());
			producto.setPrecioVenta(modificado.getPrecioVenta());
			producto.setStockActual(modificado.getStockActual());
			producto.setStockMinimo(modificado.getStockMinimo());
			producto.setProveedor(proveedor);
			mp.commit();
		} finally {
			mp.rollback();
		}
	}
	
	public Vector obtenerProductos()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector productos2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro="eliminado =="+false;
			Vector productos= mp.getObjectsOrdered(Producto.class,filtro,"nombre ascending");
			for(int i=0; i<productos.size();i++){
				ProductoDTO prDTO= new ProductoDTO();
				Producto pr = (Producto)productos.elementAt(i);
				prDTO=ProductoAssembler.getProductoDTO(pr);
				prDTO.setProveedor(ProveedorAssembler.getProveedorDTO(pr.getProveedor()));
				productos2.add(prDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return productos2;
	}

	public Vector obtenerProductosCodyNom(String cod,String nom)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector productos2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro=" eliminado =="+false +" && nombre.toLowerCase().indexOf(\""+nom.toLowerCase()+"\")>= 0";
			Vector productos= mp.getObjectsOrdered(Producto.class,filtro,"nombre ascending");
			for(int i=0; i<productos.size();i++){
				ProductoDTO prDTO= new ProductoDTO();
				Producto pr = (Producto)productos.elementAt(i);
				if(Utils.comienza(pr.getCodigo().toString(),cod)){
					prDTO=ProductoAssembler.getProductoDTO(pr);
					prDTO.setProveedor(ProveedorAssembler.getProveedorDTO(pr.getProveedor()));
					productos2.add(prDTO);
				}
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return productos2;
	}

	public Vector obtenerStockTodosProductos(String atOrden)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector productos2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro=" eliminado =="+false;
			Vector productos= mp.getObjectsOrdered(Producto.class,filtro,atOrden+" ascending");
			for(int i=0; i<productos.size();i++){
				Producto pr = (Producto)productos.elementAt(i);
				if(pr.getStockActual()<=pr.getStockMinimo()){
					ProductoDTO prDTO= new ProductoDTO();
					prDTO=ProductoAssembler.getProductoDTO(pr);
					prDTO.setProveedor(ProveedorAssembler.getProveedorDTO(pr.getProveedor()));
					productos2.add(prDTO);
				}
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return productos2;
	}

	public Vector obtenerStockProductosProveedor(Long idProv,String atOrden)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector productos2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro=" eliminado =="+false +" && proveedor.id=="+idProv;
			Vector productos= mp.getObjectsOrderedSubc(Producto.class,filtro,atOrden+" ascending");
			for(int i=0; i<productos.size();i++){
				Producto pr = (Producto)productos.elementAt(i);
				if(pr.getStockActual()<=pr.getStockMinimo()){
					ProductoDTO prDTO= new ProductoDTO();
					prDTO=ProductoAssembler.getProductoDTO(pr);
					prDTO.setProveedor(ProveedorAssembler.getProveedorDTO(pr.getProveedor()));
					productos2.add(prDTO);
				}
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return productos2;
	}

	public Vector obtenerProductosProveedor(String nombrProv)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector productos2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro=" eliminado =="+false +" && proveedor.nombre==\""+nombrProv+"\"";
			Vector productos= mp.getObjectsOrderedSubc(Producto.class,filtro,"nombre ascending");
			for(int i=0; i<productos.size();i++){
				ProductoDTO prDTO= new ProductoDTO();
				Producto pr = (Producto)productos.elementAt(i);
				prDTO=ProductoAssembler.getProductoDTO(pr);
				prDTO.setProveedor(ProveedorAssembler.getProveedorDTO(pr.getProveedor()));
				productos2.add(prDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return productos2;
	}
	
	public boolean existeProductoCodigo(Long codigo)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		boolean existe = false;
		try {
			mp.initPersistencia();
			String filtro = "codigo == "+codigo;
			Collection productoCol= mp.getObjects(Producto.class,filtro);
			if (productoCol.size()==1)
				existe=true;
			mp.commit();
		} finally {
			mp.rollback();
		}
		return existe;
	}

	public ProductoDTO buscarProductoDTOId(Long id) throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ProductoDTO pDTO = new ProductoDTO();
		Producto pro = new Producto();
		try{
			mp.initPersistencia();
			String filtro = "id == "+id;
			Collection productosCol= mp.getObjects(Producto.class,filtro);
			if (productosCol.size()==1){
				Iterator i = productosCol.iterator();
				pro = (Producto)i.next();
				pDTO=ProductoAssembler.getProductoDTO(pro);
				pDTO.setProveedor(ProveedorAssembler.getProveedorDTO(pro.getProveedor()));
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
		return pDTO;
	}

	public ProductoDTO buscarProductoDTOCodigo(Long cod) throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ProductoDTO pDTO = new ProductoDTO();
		Producto pro = new Producto();
		try{
			mp.initPersistencia();
			String filtro = "codigo == "+cod;
			Collection productosCol= mp.getObjects(Producto.class,filtro);
			if (productosCol.size()==1){
				Iterator i = productosCol.iterator();
				pro = (Producto)i.next();
				pDTO=ProductoAssembler.getProductoDTO(pro);
				pDTO.setProveedor(ProveedorAssembler.getProveedorDTO(pro.getProveedor()));
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
		return pDTO;
	}
	
	public Producto buscarProductoId(ManipuladorPersistencia mp,Long id) throws Exception{
		Producto pro = new Producto();
		String filtro = "id == "+id;
		Collection productosCol= mp.getObjects(Producto.class,filtro);
		if (productosCol.size()==1){
			Iterator i = productosCol.iterator();
			pro = (Producto)i.next();
		}
		return pro;
	}

	public boolean puedoEditar(ProductoDTO dto,ProductoDTO modificado)throws Exception{
		try{
			if (dto.getCodigo().equals(modificado.getCodigo())){
				return true;
			} else {
				if(!this.existeProductoCodigo(modificado.getCodigo()))
					return true;
				else
					return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean productoAsociado(Long id) throws Exception {
		boolean estaAsoc = false;
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try {
			mp.initPersistencia();
			String filtro="producto.id=="+id;
			Vector productos= mp.getObjects(ItemFactura.class,filtro);
			if(productos.size()>0)
				estaAsoc = true;
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return estaAsoc;
	}
	
}
