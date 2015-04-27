package server.GestionarProvincia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import server.GestionarLocalidad.ControlLocalidad;
import server.assembler.ProvinciaAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.Provincia;
import common.DTOs.LocalidadDTO;
import common.DTOs.ProvinciaDTO;
import common.GestionarProvincia.IControlProvincia;

public class ControlProvincia extends UnicastRemoteObject implements IControlProvincia{
	private static final long serialVersionUID = 1L;

	public ControlProvincia() throws RemoteException{   }

	public Long agregarProvinciaDTO(ProvinciaDTO provDTO)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Provincia prov = ProvinciaAssembler.getProvincia(provDTO);
		Long id= new Long(0);
		try{
			mp.initPersistencia();
			mp.hacerPersistente(prov);
			id=prov.getId();
			mp.commit();
		} finally {
			mp.rollback();
		}
		return id;
	}

	public void eliminarProvinciaDTO(Long id) throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try {
			mp.initPersistencia();
			Provincia prov = this.buscarProvinciaId(mp,id);
			mp.borrar(prov);
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public void modificarProvincia(Long id,ProvinciaDTO modificado)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try{
			mp.initPersistencia();
			Provincia prov = buscarProvinciaId(mp,id);
			//mp.initPersistencia();
			prov.setNombre(modificado.getNombre());
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public Vector obtenerProvincias()throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector Provincias2 = new Vector();
		try {
			mp.initPersistencia();
			Vector Provincias= mp.getAllOrdered(Provincia.class,"nombre ascending");
			for(int i=0; i<Provincias.size();i++){
				ProvinciaDTO provDTO = new ProvinciaDTO();
				Provincia prov = (Provincia)Provincias.elementAt(i);
				provDTO.setId(prov.getId());
				provDTO.setNombre(prov.getNombre());
				Provincias2.add(provDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return Provincias2;
	}

	public Vector obtenerProvinciasNombre(String nom)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		Vector Provincias2 = new Vector();
		try {
			mp.initPersistencia();
			String filtro="nombre.toLowerCase().indexOf(\""+nom.toLowerCase()+"\")>= 0";
			Vector provincias= mp.getObjectsOrdered(Provincia.class,filtro,"nombre ascending");
			for(int i=0; i<provincias.size();i++){
				ProvinciaDTO provDTO = new ProvinciaDTO();
				Provincia prov = (Provincia)provincias.elementAt(i);
				provDTO.setId(prov.getId());
				provDTO.setNombre(prov.getNombre());
				Provincias2.add(provDTO);
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return Provincias2;
	}

	public boolean existeProvinciaNombre(String nombre)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		boolean existe = false;
		try {
			mp.initPersistencia();
			String filtro = "nombre == \""+nombre+"\"";
			Collection ProvinciaCol= mp.getObjects(Provincia.class,filtro);
			if (ProvinciaCol.size()==1)
				existe=true;
			mp.commit();
		} finally {
			mp.rollback();
		}
		return existe;
	}

	public ProvinciaDTO buscarProvinciaDTOId(Long id) throws Exception {
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ProvinciaDTO prDTO = new ProvinciaDTO();
		Provincia pr = new Provincia();
		try{
			mp.initPersistencia();
			String filtro = "id == "+id;
			Collection provCol= mp.getObjects(Provincia.class,filtro);
			if (provCol.size()==1){
				Iterator i = provCol.iterator();
				pr = (Provincia)i.next();
				prDTO=ProvinciaAssembler.getProvinciaDTO(pr);
			}
			mp.commit();
		} finally {
			mp.rollback();
		}
		return prDTO;
	}

	public Provincia buscarProvinciaId(ManipuladorPersistencia mp,Long id) throws Exception{
		Provincia pr = new Provincia();
		String filtro = "id == "+id;
		Collection provCol= mp.getObjects(Provincia.class,filtro);
		if (provCol.size()==1){
			Iterator i = provCol.iterator();
			pr = (Provincia)i.next();
		}
		return pr;
	}

	public boolean puedoEditar(ProvinciaDTO dto,ProvinciaDTO modificado)throws Exception{
		try{
			if (dto.getNombre().equals(modificado.getNombre())){
				return true;
			} else {
				if(!this.existeProvinciaNombre(modificado.getNombre()))
					return true;
				else
					return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean provinciaAsociada(Long id) throws Exception {
		ControlLocalidad ctrlLoc = new ControlLocalidad();
		boolean estaAsoc = false;
		try {
			Vector locsCol = ctrlLoc.obtenerLocalidades();
			LocalidadDTO loc;
			for (Iterator i = locsCol.iterator(); i.hasNext() && !estaAsoc;) {
				loc = (LocalidadDTO)i.next();
				if (loc.getProvincia().getId().equals(id)){
					estaAsoc = true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			estaAsoc = true;
		}
		return estaAsoc;
	}

}
