package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class ItemFacturaAssembler {

	public ItemFacturaAssembler() {
	}

	public static ItemFacturaDTO getItemFacturaDTO(ItemFactura objD ) {
		ItemFacturaDTO itFactDTO = new ItemFacturaDTO();
		itFactDTO.setId(objD.getId());
		itFactDTO.setCantidad(objD.getCantidad());
		itFactDTO.setPrTotal(objD.getPrTotal());
		itFactDTO.setPrUnit(objD.getPrUnit());
		return itFactDTO;
	}

	public static ItemFactura getItemFactura(ItemFacturaDTO objDTO) {
		ItemFactura itFact = new ItemFactura();
		itFact.setCantidad(objDTO.getCantidad());
		itFact.setPrTotal(objDTO.getPrTotal());
		itFact.setPrUnit(objDTO.getPrUnit());
		return itFact;
	}
	
	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==ItemFacturaDTO.class) {
				aux.add(getItemFactura((ItemFacturaDTO) obj));
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
			if (obj.getClass()==ItemFactura.class) {
				auxDTO.add(getItemFacturaDTO((ItemFactura) obj));
			}
		}
		return auxDTO;
	}
	
}