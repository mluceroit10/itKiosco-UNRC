package server.GestionarProveedor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import server.GestionarLocalidad.ControlLocalidad;
import server.GestionarProducto.ControlProducto;
import server.assembler.LocalidadAssembler;
import server.assembler.ProveedorAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.ItemFactura;
import server.persistencia.dominio.Localidad;
import server.persistencia.dominio.Producto;
import server.persistencia.dominio.Proveedor;

import common.Utils;
import common.DTOs.ProductoDTO;
import common.DTOs.ProveedorDTO;
import common.GestionarProveedor.IControlProveedor;

public class ControlProveedor extends UnicastRemoteObject implements IControlProveedor{
	private static final long serialVersionUID = 1L;

	public ControlProveedor() throws RemoteException{   }

	public Long agregarProveedorDTO(ProveedorDTO p)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlLocalidad cLoc = new ControlLocalidad();
		Proveedor pnew= ProveedorAssembler.getProveedor(p);
		Long id= new Long(0);
		try{
			mp.initPersistencia();
			Localidad loc = cLoc.buscarLocalidadId(mp,p.getLocalidad().getId());
			mp.hacerPersistente(pnew);
			pnew.setLocalidad(loc);
			id=pnew.getId();
			mp.commit();
		} finally {
			mp.rollback();
		}
		return id;
	}

	public void eliminarProveedorDTO(Long id) throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Proveedor pro=null;
		boolean asoc=proveedorAsociado(id);
		try {
			mp.initPersistencia();
			pro = this.buscarProveedorId(mp,id);
			if(!asoc)
				mp.borrar(pro);
			else{
				pro.setEliminado(true);
				Set prods=pro.getProductos();
				for(Iterator it=prods.iterator();it.hasNext();){
					Producto pr=(Producto) it.next();
					String filtro="producto.id=="+pr.getId();
					Vector productos= mp.getObjects(ItemFactura.class,filtro);
					if(productos.size()>0)
						pr.setEliminado(true);
					else
						mp.borrar(pr);
				}
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public void modificarProveedor(Long id,ProveedorDTO modificado)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try{
			ControlLocalidad cLoc = new ControlLocalidad();
			mp.initPersistencia();
			Proveedor proveedor = this.buscarProveedorId(mp,id);
			Localidad loc = cLoc.buscarLocalidadId(mp,modificado.getLocalidad().getId());
			proveedor.setCodigo(modificado.getCodigo());
			proveedor.setNombre(modificado.getNombre());
			proveedor.setDireccion(modificado.getDireccion());
			proveedor.setTelefono(modificado.getTelefono());
			proveedor.setLocalidad(loc);
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public Vector obtenerProveedores()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector proveedors2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro="eliminado =="+false;
			Vector Proveedors= mp.getObjectsOrdered(Proveedor.class,filtro,"codigo ascending");
			for(int i=0; i<Proveedors.size();i++){
				ProveedorDTO pDTO= new ProveedorDTO();
				Proveedor pro = (Proveedor)Proveedors.elementAt(i);
				pDTO=ProveedorAssembler.getProveedorDTO(pro);
				pDTO.setLocalidad(LocalidadAssembler.getLocalidadDTO(pro.getLocalidad()));
				proveedors2.add(pDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return proveedors2;
	}

	public Vector obtenerProveedoresCodyNom(String cod,String nom)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector proveedors2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro=" eliminado =="+false+" && nombre.toLowerCase().indexOf(\""+nom.toLowerCase()+"\")>= 0";
			Vector proveedores= mp.getObjectsOrdered(Proveedor.class,filtro,"codigo ascending");
			for(int i=0; i<proveedores.size();i++){
				ProveedorDTO pDTO= new ProveedorDTO();
				Proveedor pro = (Proveedor)proveedores.elementAt(i);
				if(Utils.comienza(pro.getCodigo().toString(),cod)){
					pDTO=ProveedorAssembler.getProveedorDTO(pro);
					pDTO.setLocalidad(LocalidadAssembler.getLocalidadDTO(pro.getLocalidad()));
					proveedors2.add(pDTO);
				}
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return proveedors2;
	}
	
	public boolean existeProveedorNombre(String nombre)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		boolean existe = false;
		try {
			mp.initPersistencia();
			String filtro = "nombre == \""+nombre+"\"";
			Collection ProveedorCol= mp.getObjects(Proveedor.class,filtro);
			if (ProveedorCol.size()==1)
				existe=true;
			mp.commit();
		} finally {
			mp.rollback();
		}
		return existe;
	}

	public boolean existeProveedorCodigo(Long codigo)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		boolean existe = false;
		try {
			mp.initPersistencia();
			String filtro = "codigo=="+codigo;
			Collection ProveedorCol= mp.getObjects(Proveedor.class,filtro);
			if (ProveedorCol.size()>=1)
				existe=true;
			mp.commit();
		} finally {
			mp.rollback();
		}
		return existe;
	}

	public ProveedorDTO buscarProveedorDTOId(Long id) throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ProveedorDTO pDTO = new ProveedorDTO();
		Proveedor pro = new Proveedor();
		try{
			mp.initPersistencia();
			String filtro = "id == "+id;
			Collection proveedoresCol= mp.getObjects(Proveedor.class,filtro);
			if (proveedoresCol.size()==1){
				Iterator i = proveedoresCol.iterator();
				pro = (Proveedor)i.next();
				pDTO=ProveedorAssembler.getProveedorDTO(pro);
				pDTO.setLocalidad(LocalidadAssembler.getLocalidadDTO(pro.getLocalidad()));
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
		return pDTO;
	}

	public Proveedor buscarProveedorId(ManipuladorPersistencia mp,Long id) throws Exception{
		Proveedor prov = new Proveedor();
		String filtro = "id == "+id;
		Collection proveedoresCol= mp.getObjects(Proveedor.class,filtro);
		if (proveedoresCol.size()==1){
			Iterator i = proveedoresCol.iterator();
			prov = (Proveedor)i.next();
		}
		return prov;
	}

	public boolean puedoEditar(ProveedorDTO dto,ProveedorDTO modificado)throws Exception{
		try{
			if (dto.getNombre().equals(modificado.getNombre())){
				return true;
			} else {
				if(!this.existeProveedorNombre(modificado.getNombre()))
					return true;
				else
					return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean proveedorAsociado(Long id) throws Exception {
		boolean estaAsoc = false;
		ControlProducto ctrlProd = new ControlProducto();
		try {
			Vector productos = ctrlProd.obtenerProductos();
			ProductoDTO p;
			for (int i=0; i<productos.size() && !estaAsoc; i++ ) {
				p = (ProductoDTO)productos.elementAt(i);
				if (p.getProveedor().getId().equals(id)){
					estaAsoc = true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			estaAsoc = true;
		}
		return estaAsoc;
	}

	public Long obtenerNroProveedor()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		long cod=0;
		try{
			mp.initPersistencia();
			Vector socioCol= mp.getAllOrdered(Proveedor.class,"codigo descending");
			if(socioCol.size()==0)cod=Utils.NROFACTURACLIENTE;
			else{
				Proveedor p = (Proveedor)socioCol.elementAt(0);
				cod=p.getCodigo().longValue()+1;
			}
			mp.commit();
			return (new Long(String.valueOf(cod)));
		} finally {
			mp.rollback();
		}
	}
	
}
