package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class KioscoAssembler {

	public KioscoAssembler() {
	}

	public static KioscoDTO getKioscoDTO(Kiosco objD ) {
		KioscoDTO kioscoDTO = new KioscoDTO();
		kioscoDTO.setId(objD.getId());
		kioscoDTO.setDireccion(objD.getDireccion());
		kioscoDTO.setEncargado(objD.getEncargado());
		kioscoDTO.setInicioAct(objD.getInicioAct());
		kioscoDTO.setNombre(objD.getNombre());
		kioscoDTO.setTelefono(objD.getTelefono());
		return kioscoDTO;
	}

	public static Kiosco getKiosco(KioscoDTO objDTO) {
		Kiosco kiosco = new Kiosco();
		kiosco.setDireccion(objDTO.getDireccion());
		kiosco.setEncargado(objDTO.getEncargado());
		kiosco.setInicioAct(objDTO.getInicioAct());
		kiosco.setNombre(objDTO.getNombre());
		kiosco.setTelefono(objDTO.getTelefono());
		return kiosco;
	}
	
	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==KioscoDTO.class) {
				aux.add(getKiosco((KioscoDTO) obj));
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
			if (obj.getClass()==Kiosco.class) {
				auxDTO.add(getKioscoDTO((Kiosco) obj));
			}
		}
		return auxDTO;
	}
	
}