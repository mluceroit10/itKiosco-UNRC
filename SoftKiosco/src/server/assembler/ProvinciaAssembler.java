package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class ProvinciaAssembler {

	public ProvinciaAssembler() {
	}

	public static ProvinciaDTO getProvinciaDTO(Provincia objD ) {
		ProvinciaDTO provinciaDTO = new ProvinciaDTO();
		provinciaDTO.setId(objD.getId());
		provinciaDTO.setNombre(objD.getNombre());
		return provinciaDTO;
	}

	public static Provincia getProvincia(ProvinciaDTO objDTO) {
		Provincia provincia = new Provincia();
		provincia.setNombre(objDTO.getNombre());
		return provincia;
	}
	
	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==ProvinciaDTO.class) {
				aux.add(getProvincia((ProvinciaDTO) obj));
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
			if (obj.getClass()==Provincia.class) {
				auxDTO.add(getProvinciaDTO((Provincia) obj));
			}
		}
		return auxDTO;
	}
	
}