package server.GestionarLocalidad;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import server.GestionarKiosco.ControlKiosco;
import server.GestionarProveedor.ControlProveedor;
import server.GestionarProvincia.ControlProvincia;
import server.GestionarUsuario.ControlUsuario;
import server.assembler.LocalidadAssembler;
import server.assembler.ProvinciaAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.Localidad;
import server.persistencia.dominio.Provincia;

import common.DTOs.KioscoDTO;
import common.DTOs.LocalidadDTO;
import common.DTOs.ProveedorDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarLocalidad.IControlLocalidad;

public class ControlLocalidad extends UnicastRemoteObject implements IControlLocalidad{
	private static final long serialVersionUID = 1L;

	public ControlLocalidad() throws RemoteException{   }

	public Long agregarLocalidadDTO(LocalidadDTO locDTO)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlProvincia cProv = new ControlProvincia();
		Localidad lnew= LocalidadAssembler.getLocalidad(locDTO);
		Long id= new Long(0);
		try{
			mp.initPersistencia();
			Provincia prov = cProv.buscarProvinciaId(mp,locDTO.getProvincia().getId());
			mp.hacerPersistente(lnew);
			lnew.setProvincia(prov);
			id=lnew.getId();
			mp.commit();
		} finally {
			mp.rollback();
		}
		return id;
	}

	public void eliminarLocalidadDTO(Long id) throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try {
			mp.initPersistencia();
			Localidad loc = this.buscarLocalidadId(mp,id);
			mp.borrar(loc);
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public void modificarLocalidad(Long id,LocalidadDTO modificado)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try{
			ControlProvincia cProv = new ControlProvincia();
			mp.initPersistencia();
			Localidad localidad = buscarLocalidadId(mp,id);
			Provincia prov = cProv.buscarProvinciaId(mp,modificado.getProvincia().getId());
			localidad.setNombre(modificado.getNombre());
			localidad.setCodPostal(modificado.getCodPostal());
			localidad.setProvincia(prov);
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public Vector obtenerLocalidades()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector localidads2 = new Vector();
		try {
			mp.initPersistencia();
			Vector localidads= mp.getAllOrdered(Localidad.class,"nombre ascending");
			for (Iterator i = localidads.iterator(); i.hasNext(); ) {
				LocalidadDTO lDTO = new LocalidadDTO();
				Localidad loc = (Localidad)i.next();
				lDTO = LocalidadAssembler.getLocalidadDTO(loc);
				lDTO.setProvincia(ProvinciaAssembler.getProvinciaDTO(loc.getProvincia()));
				localidads2.add(lDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return localidads2;
	}

	public Vector obtenerLocalidadesNombre(String nom)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector localidads2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro="nombre.toLowerCase().indexOf(\""+nom.toLowerCase()+"\")>= 0";
			Vector localidades= mp.getObjectsOrdered(Localidad.class,filtro,"nombre ascending");
			for (Iterator i = localidades.iterator(); i.hasNext(); ) {
				LocalidadDTO lDTO = new LocalidadDTO();
				Localidad loc = (Localidad)i.next();
				lDTO = LocalidadAssembler.getLocalidadDTO(loc);
				lDTO.setProvincia(ProvinciaAssembler.getProvinciaDTO(loc.getProvincia()));
				localidads2.add(lDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return localidads2;
	}

	public boolean existeLocalidadNombre(String nombre)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		boolean existe = false;
		try {
			mp.initPersistencia();
			String filtro = "nombre == \""+nombre+"\"";
			Collection localidadCol= mp.getObjects(Localidad.class,filtro);
			if (localidadCol.size()==1)
				existe=true;
			mp.commit();
		} finally {
			mp.rollback();
		}
		return existe;
	}

	public LocalidadDTO buscarLocalidadDTOId(Long id) throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		LocalidadDTO lDTO = new LocalidadDTO();
		Localidad loc = new Localidad();
		try{
			mp.initPersistencia();
			String filtro = "id == "+id;
			Collection localidadCol= mp.getObjects(Localidad.class,filtro);
			if (localidadCol.size()==1){
				Iterator i = localidadCol.iterator();
				loc = (Localidad)i.next();
				lDTO=LocalidadAssembler.getLocalidadDTO(loc);
				lDTO.setProvincia(ProvinciaAssembler.getProvinciaDTO(loc.getProvincia()));
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
		return lDTO;
	}

	public Localidad buscarLocalidadId(ManipuladorPersistencia mp,Long id) throws Exception{
		Localidad loc = new Localidad();
		String filtro = "id == "+id;
		Collection localidadCol= mp.getObjects(Localidad.class,filtro);
		if (localidadCol.size()==1){
			Iterator i = localidadCol.iterator();
			loc = (Localidad)i.next();
		}
		return loc;
	}

	public boolean puedoEditar(LocalidadDTO dto,LocalidadDTO modificado)throws Exception{
		try{
			if (dto.getNombre().equals(modificado.getNombre())){
				return true;
			} else {
				if(!this.existeLocalidadNombre(modificado.getNombre()))
					return true;
				else
					return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean localidadAsociada(Long id) throws Exception {
		boolean estaAsoc = false;
		ControlUsuario ctrlUsr = new ControlUsuario();
		ControlProveedor ctrlProveedor = new ControlProveedor();
		ControlKiosco ctrlKiosco= new ControlKiosco();
		try {
			Vector usuarios = ctrlUsr.obtenerUsuarios();
			UsuarioDTO c;
			for (int i=0; i<usuarios.size() && !estaAsoc; i++ ) {
				c = (UsuarioDTO)usuarios.elementAt(i);
				if (c.getLocalidad().getId().equals(id)){
					estaAsoc = true;
				}
			}
			Vector proveedor = ctrlProveedor.obtenerProveedores();
			ProveedorDTO p;
			for (int i=0; i<proveedor.size() && !estaAsoc; i++ ) {
				p = (ProveedorDTO) proveedor.elementAt(i);
				if (p.getLocalidad().getId().equals(id)){
					estaAsoc = true;
				}
			}
			KioscoDTO distrib = ctrlKiosco.obtenerKiosco();
			if (distrib.getLocalidad().getId().equals(id)){
				estaAsoc = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
			estaAsoc = true;
		}
		return estaAsoc;
	}

}
