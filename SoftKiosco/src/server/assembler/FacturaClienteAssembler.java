package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class FacturaClienteAssembler {

	public FacturaClienteAssembler() {
	}

	public static FacturaClienteDTO getFacturaClienteDTO(FacturaCliente objD ) {
		FacturaClienteDTO factClienteDTO = new FacturaClienteDTO();
		factClienteDTO.setId(objD.getId());
		factClienteDTO.setFechaHora(objD.getFechaHora());
		factClienteDTO.setImporteTotal(objD.getImporteTotal());
		factClienteDTO.setNroFactura(objD.getNroFactura());
		factClienteDTO.setTipoFactura(objD.getTipoFactura());
		factClienteDTO.setACargoBecario(objD.isACargoBecario());
		factClienteDTO.setPeriodo(objD.getPeriodo());
		return factClienteDTO;
	}

	public static FacturaCliente getFacturaCliente(FacturaClienteDTO objDTO) {
		FacturaCliente factCliente = new FacturaCliente();
		factCliente.setFechaHora(objDTO.getFechaHora());
		factCliente.setImporteTotal(objDTO.getImporteTotal());
		factCliente.setNroFactura(objDTO.getNroFactura());
		factCliente.setTipoFactura(objDTO.getTipoFactura());
		factCliente.setACargoBecario(objDTO.isACargoBecario());
		factCliente.setPeriodo(objDTO.getPeriodo());
		return factCliente;
	}

	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==FacturaClienteDTO.class) {
				aux.add(getFacturaCliente((FacturaClienteDTO) obj));
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
			if (obj.getClass()==FacturaCliente.class) {
				auxDTO.add(getFacturaClienteDTO((FacturaCliente) obj));
			}
		}
		return auxDTO;
	}
	
}