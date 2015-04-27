package server.GestionarKiosco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import server.GestionarLocalidad.ControlLocalidad;
import server.assembler.KioscoAssembler;
import server.assembler.LocalidadAssembler;
import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.Kiosco;
import server.persistencia.dominio.Localidad;

import common.DTOs.KioscoDTO;
import common.GestionarKiosco.IControlKiosco;

public class ControlKiosco extends UnicastRemoteObject implements IControlKiosco{
	private static final long serialVersionUID = 1L;

	public ControlKiosco() throws RemoteException{   }

	public Long agregarKioscoDTO(KioscoDTO kiDTO)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		ControlLocalidad cLoc = new ControlLocalidad();
		Kiosco knew= KioscoAssembler.getKiosco(kiDTO);
		Long id= new Long(0);
		try{
			mp.initPersistencia();
			Localidad loc = cLoc.buscarLocalidadId(mp,kiDTO.getLocalidad().getId());
			mp.hacerPersistente(knew);
			knew.setLocalidad(loc);
			id=knew.getId();
			mp.commit();
		} finally {
			mp.rollback();
		}
		return id;
	}

	public void modificarKiosco(Long id,KioscoDTO modificado)throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		try{
			ControlLocalidad cLoc = new ControlLocalidad();
			mp.initPersistencia();
			Kiosco kiosco = buscarKioscoId(mp,id);
			Localidad loc = cLoc.buscarLocalidadId(mp,modificado.getLocalidad().getId());
			kiosco.setNombre(modificado.getNombre());
			kiosco.setDireccion(modificado.getDireccion());
			kiosco.setEncargado(modificado.getEncargado());
			kiosco.setInicioAct(modificado.getInicioAct());
			kiosco.setTelefono(modificado.getTelefono());
			kiosco.setLocalidad(loc);
			mp.commit();
		} finally {
			mp.rollback();
		}
	}

	public KioscoDTO obtenerKiosco() throws Exception{
		ManipuladorPersistencia mp=new ManipuladorPersistencia();
		KioscoDTO kDTO = null;
		try {
			mp.initPersistencia();
			Vector kioscos= mp.getAll(Kiosco.class);
			if(kioscos.size()>0){
				Kiosco k = (Kiosco)kioscos.elementAt(0);
				kDTO=KioscoAssembler.getKioscoDTO(k);
				kDTO.setLocalidad(LocalidadAssembler.getLocalidadDTO(k.getLocalidad()));
			}
			mp.commit();
		}  finally{
			mp.rollback();
		}
		return kDTO;
	}

	public Kiosco buscarKioscoId(ManipuladorPersistencia mp, Long id) throws Exception{
		Kiosco ki = new Kiosco();
		String filtro = "id == "+id;
		Collection kioscoCol= mp.getObjects(Kiosco.class,filtro);
		if (kioscoCol.size()==1){
			Iterator i = kioscoCol.iterator();
			ki = (Kiosco)i.next();
		}
		return ki;
	}

}
