package common.GestionarMovimientoCaja;

import java.rmi.Remote;
import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.MovimientoCaja;

import common.DTOs.MovimientoCajaDTO;

public interface IControlMovimientoCaja extends Remote{

	public Long agregarMovimientoCajaDTO(MovimientoCajaDTO mcDTO)throws Exception;
	public void eliminarMovimientoCajaDTO(Long id)throws Exception;
	public Vector obtenerMovimientosCajaBalance()throws Exception;
	public Vector obtenerMovimientosCaja(int mesLI,int anioLI)throws Exception;
	public Vector obtenerMovimientosCajaNroyFecha(int mesLI,int anioLI,String nro,String fe)throws Exception;
	public Vector obtenerMovimientosCajaPagoProveedor(Long idFactura)throws Exception;
	public boolean existeMovimientoCajaNroRecibo(Long numero)throws Exception;
	public MovimientoCajaDTO buscarMovimientoCajaDTOId(Long id) throws Exception;
	public MovimientoCaja buscarMovimientoCajaId(ManipuladorPersistencia mp,Long id) throws Exception;
	public boolean movimientoAsociado(Long id) throws Exception;
	public Long obtenerNroMovCaja()throws Exception;

}
