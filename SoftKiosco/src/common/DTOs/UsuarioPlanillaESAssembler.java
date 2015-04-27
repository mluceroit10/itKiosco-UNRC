package common.DTOs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import server.persistencia.dominio.UsuarioPlanillaES;

public class UsuarioPlanillaESAssembler {

	public UsuarioPlanillaESAssembler(){
	}

	public static UsuarioPlanillaESDTO getUsuarioPlanillaESDTO(UsuarioPlanillaES p) {
		UsuarioPlanillaESDTO psDTO = new UsuarioPlanillaESDTO();
		psDTO.setId(p.getId());
		return psDTO;
	}

	public static UsuarioPlanillaES getUsuarioPlanillaES(UsuarioPlanillaESDTO pDTO) {
		UsuarioPlanillaES ps = new UsuarioPlanillaES();
		return ps;
	}

	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==UsuarioPlanillaESDTO.class) {
				aux.add(getUsuarioPlanillaES((UsuarioPlanillaESDTO) obj));
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
			if (obj.getClass()==UsuarioPlanillaES.class) {
				auxDTO.add(getUsuarioPlanillaESDTO((UsuarioPlanillaES) obj));
			}
		}
		return auxDTO;
	}

}

