package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class PlanillaESAssembler {

	public PlanillaESAssembler() {
	}

	public static PlanillaESDTO getPlanillaESDTO(PlanillaES objD) {
		PlanillaESDTO planillaESDTO = new PlanillaESDTO();
		planillaESDTO.setFecha(objD.getFecha());
		planillaESDTO.setNroPlanilla(objD.getNroPlanilla());
		planillaESDTO.setSaldoCajaGeneral(objD.getSaldoCajaGeneral());
		planillaESDTO.setSaldoCajaDiaria(objD.getSaldoCajaDiaria());
		planillaESDTO.setIngresoCajaDiaria(objD.getIngresoCajaDiaria());
		planillaESDTO.setId(objD.getId());
		planillaESDTO.setPeriodo(objD.getPeriodo());
		planillaESDTO.setEnCaja(objD.getEnCaja());
		planillaESDTO.setRetiro(objD.getRetiro());
		return planillaESDTO;
	}

	public static PlanillaES getPlanillaES(PlanillaESDTO objDTO) {
		PlanillaES planillaES = new PlanillaES();
		planillaES.setFecha(objDTO.getFecha());
		planillaES.setNroPlanilla(objDTO.getNroPlanilla());
		planillaES.setSaldoCajaGeneral(objDTO.getSaldoCajaGeneral());
		planillaES.setSaldoCajaDiaria(objDTO.getSaldoCajaDiaria());
		planillaES.setIngresoCajaDiaria(objDTO.getIngresoCajaDiaria());
		planillaES.setPeriodo(objDTO.getPeriodo());
		planillaES.setEnCaja(objDTO.getEnCaja());
		planillaES.setRetiro(objDTO.getRetiro());  
		return planillaES;
	}

	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==PlanillaESDTO.class) {
				aux.add(getPlanillaES((PlanillaESDTO) obj));
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
			if (obj.getClass()==PlanillaES.class) {
				auxDTO.add(getPlanillaESDTO((PlanillaES) obj));
			}
		}
		return auxDTO;
	}

}
