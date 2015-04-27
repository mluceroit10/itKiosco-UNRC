package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import server.persistencia.dominio.*;

import common.DTOs.*;

public class LocalidadAssembler {

	public LocalidadAssembler(){
	}

	public static LocalidadDTO getLocalidadDTO(Localidad objD) {
		LocalidadDTO localidadDTO = new LocalidadDTO();
		localidadDTO.setCodPostal(objD.getCodPostal());
		localidadDTO.setNombre(objD.getNombre());
		localidadDTO.setId(objD.getId());
		return localidadDTO;
	}

	public static Localidad getLocalidad(LocalidadDTO objDTO) {
		Localidad localidad = new Localidad();
		localidad.setCodPostal(objDTO.getCodPostal());
		localidad.setNombre(objDTO.getNombre());
		return localidad;
	}

	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==LocalidadDTO.class) {
				aux.add(getLocalidad((LocalidadDTO) obj));
			}
		}
		return aux;
	}

	public static Vector getVector (Set s) {
		Vector auxDTO = new Vector();
		auxDTO.clear();
		Iterator it = s.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==Localidad.class) {
				auxDTO.add(getLocalidadDTO((Localidad) obj));
			}
		}
		return auxDTO;
	}

}

