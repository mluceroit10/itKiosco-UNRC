package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class SesionAssembler {

	public SesionAssembler() {
	}

	public static SesionDTO getSesionDTO(Sesion objD) {
		SesionDTO sesionDTO = new SesionDTO();
		sesionDTO.setInicio(objD.getInicio());
		sesionDTO.setCierre(objD.getCierre());
		sesionDTO.setId(objD.getId());
		return sesionDTO;
	}

	public static Sesion getSesion(SesionDTO objDTO) {
		Sesion sesion = new Sesion();
		sesion.setInicio(objDTO.getInicio());
		sesion.setCierre(objDTO.getCierre());
		return sesion;
	}

	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==SesionDTO.class) {
				aux.add(getSesion((SesionDTO) obj));
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
			if (obj.getClass()==Sesion.class) {
				auxDTO.add(getSesionDTO((Sesion) obj));
			}
		}
		return auxDTO;
	}

}
