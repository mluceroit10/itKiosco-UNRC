package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class ProveedorAssembler {

	public ProveedorAssembler() {
	}

	public static ProveedorDTO getProveedorDTO(Proveedor objD ) {
		ProveedorDTO proveedorDTO = new ProveedorDTO();
		proveedorDTO.setId(objD.getId());
		proveedorDTO.setCodigo(objD.getCodigo());
		proveedorDTO.setDireccion(objD.getDireccion());
		proveedorDTO.setNombre(objD.getNombre());
		proveedorDTO.setTelefono(objD.getTelefono());
		proveedorDTO.setEliminado(objD.isEliminado());
		return proveedorDTO;
	}

	public static Proveedor getProveedor(ProveedorDTO objDTO) {
		Proveedor proveedor = new Proveedor();
		proveedor.setCodigo(objDTO.getCodigo());
		proveedor.setDireccion(objDTO.getDireccion());
		proveedor.setNombre(objDTO.getNombre());
		proveedor.setTelefono(objDTO.getTelefono());
		proveedor.setEliminado(objDTO.isEliminado());
		return proveedor;
	}
	
	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==ProveedorDTO.class) {
				aux.add(getProveedor((ProveedorDTO) obj));
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
			if (obj.getClass()==Proveedor.class) {
				auxDTO.add(getProveedorDTO((Proveedor) obj));
			}
		}
		return auxDTO;
	}
	
}