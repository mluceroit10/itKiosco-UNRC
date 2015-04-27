package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class BecaAsignadaAssembler {

	public BecaAsignadaAssembler() {
	}

	public static BecaAsignadaDTO getBecaAsignadaDTO(BecaAsignada objD ) {
		BecaAsignadaDTO becaDTO = new BecaAsignadaDTO();
		becaDTO.setId(objD.getId());
		becaDTO.setFecha(objD.getFecha());
		becaDTO.setCierrePlazo(objD.isCierrePlazo());
		becaDTO.setImporteAsignado(objD.getImporteAsignado());
		becaDTO.setImporteRestante(objD.getImporteRestante());
		return becaDTO;
	}

	public static BecaAsignada getBecaAsignada(BecaAsignadaDTO objDTO) {
		BecaAsignada beca = new BecaAsignada();
		beca.setFecha(objDTO.getFecha());
		beca.setCierrePlazo(objDTO.isCierrePlazo());
		beca.setImporteAsignado(objDTO.getImporteAsignado());
		beca.setImporteRestante(objDTO.getImporteRestante());
		return beca;
	}
	
	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==BecaAsignadaDTO.class) {
				aux.add(getBecaAsignada((BecaAsignadaDTO) obj));
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
			if (obj.getClass()==BecaAsignada.class) {
				auxDTO.add(getBecaAsignadaDTO((BecaAsignada) obj));
			}
		}
		return auxDTO;
	}
	
}