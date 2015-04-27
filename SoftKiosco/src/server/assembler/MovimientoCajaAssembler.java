package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class MovimientoCajaAssembler {

	public MovimientoCajaAssembler() {
	}

	public static MovimientoCajaDTO getMovimientoCajaDTO(MovimientoCaja objD) {
		MovimientoCajaDTO movimientoDTO = new MovimientoCajaDTO();
		movimientoDTO.setDescripcion(objD.getDescripcion());
		movimientoDTO.setFecha(objD.getFechaMC());
		movimientoDTO.setImporte(objD.getImporte());
		movimientoDTO.setNroRecibo(objD.getNroRecibo());
		movimientoDTO.setTipoMovimiento(objD.getTipoMovimiento());
		movimientoDTO.setConFactura(objD.isConFactura());
		movimientoDTO.setTipoCajaRegistrado(objD.getTipoCajaRegistrado());
		movimientoDTO.setPeriodo(objD.getPeriodo());
		movimientoDTO.setId(objD.getId());
		return movimientoDTO;
	}

	public static MovimientoCaja getMovimientoCaja(MovimientoCajaDTO objDTO) {
		MovimientoCaja movimiento = new MovimientoCaja();
		movimiento.setDescripcion(objDTO.getDescripcion());
		movimiento.setFechaMC(objDTO.getFecha());
		movimiento.setImporte(objDTO.getImporte());
		movimiento.setNroRecibo(objDTO.getNroRecibo());
		movimiento.setTipoMovimiento(objDTO.getTipoMovimiento());
		movimiento.setConFactura(objDTO.isConFactura());
		movimiento.setTipoCajaRegistrado(objDTO.getTipoCajaRegistrado());
		movimiento.setPeriodo(objDTO.getPeriodo());
		return movimiento;
	}

	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==MovimientoCajaDTO.class) {
				aux.add(getMovimientoCaja((MovimientoCajaDTO) obj));
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
			if (obj.getClass()==MovimientoCaja.class) {
				auxDTO.add(getMovimientoCajaDTO((MovimientoCaja) obj));
			}
		}
		return auxDTO;
	}

}
