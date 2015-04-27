package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class ProductoAssembler {

	public ProductoAssembler() {
	}

	public static ProductoDTO getProductoDTO(Producto objD ) {
		ProductoDTO productoDTO = new ProductoDTO();
		productoDTO.setId(objD.getId());
		productoDTO.setCodigo(objD.getCodigo());
		productoDTO.setMargenGanancia(objD.getMargenGanancia());
		productoDTO.setNombre(objD.getNombre());
		productoDTO.setPrecioEntrada(objD.getPrecioEntrada());
		productoDTO.setPrecioVenta(objD.getPrecioVenta());
		productoDTO.setStockActual(objD.getStockActual());
		productoDTO.setStockMinimo(objD.getStockMinimo());
		productoDTO.setEliminado(objD.isEliminado());
		return productoDTO;
	}

	public static Producto getProducto(ProductoDTO objDTO) {
		Producto producto = new Producto();
		producto.setCodigo(objDTO.getCodigo());
		producto.setMargenGanancia(objDTO.getMargenGanancia());
		producto.setNombre(objDTO.getNombre());
		producto.setPrecioEntrada(objDTO.getPrecioEntrada());
		producto.setPrecioVenta(objDTO.getPrecioVenta());
		producto.setStockActual(objDTO.getStockActual());
		producto.setStockMinimo(objDTO.getStockMinimo());
		producto.setEliminado(objDTO.isEliminado());
		return producto;
	}
	
	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==ProductoDTO.class) {
				aux.add(getProducto((ProductoDTO) obj));
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
			if (obj.getClass()==Producto.class) {
				auxDTO.add(getProductoDTO((Producto) obj));
			}
		}
		return auxDTO;
	}
	
}