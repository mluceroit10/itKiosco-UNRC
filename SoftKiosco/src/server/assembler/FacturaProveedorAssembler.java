package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class FacturaProveedorAssembler {

	public FacturaProveedorAssembler() {
	}

	public static FacturaProveedorDTO getFacturaProveedorDTO(FacturaProveedor objD ) {
		FacturaProveedorDTO factProveedorDTO = new FacturaProveedorDTO();
		factProveedorDTO.setId(objD.getId());
		factProveedorDTO.setFechaHora(objD.getFechaHora());
		factProveedorDTO.setImporteTotal(objD.getImporteTotal());
		factProveedorDTO.setNroFactura(objD.getNroFactura());
		factProveedorDTO.setTipoFactura(objD.getTipoFactura());
		factProveedorDTO.setPeriodo(objD.getPeriodo());
		return factProveedorDTO;
	}

	public static FacturaProveedor getFacturaProveedor(FacturaProveedorDTO objDTO) {
		FacturaProveedor factProveedor = new FacturaProveedor();
		factProveedor.setFechaHora(objDTO.getFechaHora());
		factProveedor.setImporteTotal(objDTO.getImporteTotal());
		factProveedor.setNroFactura(objDTO.getNroFactura());
		factProveedor.setTipoFactura(objDTO.getTipoFactura());
		factProveedor.setPeriodo(objDTO.getPeriodo());
		return factProveedor;
	}
	
	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==FacturaProveedorDTO.class) {
				aux.add(getFacturaProveedor((FacturaProveedorDTO) obj));
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
			if (obj.getClass()==FacturaProveedor.class) {
				auxDTO.add(getFacturaProveedorDTO((FacturaProveedor) obj));
			}
		}
		return auxDTO;
	}
	
}