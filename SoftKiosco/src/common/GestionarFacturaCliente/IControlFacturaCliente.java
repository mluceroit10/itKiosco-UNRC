package common.GestionarFacturaCliente;

import java.rmi.Remote;
import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.FacturaCliente;

import common.DTOs.FacturaClienteDTO;

public interface IControlFacturaCliente extends Remote{

	public double agregarFacturaClienteTotal(FacturaClienteDTO fc)throws Exception;
	public Vector obtenerFacturasClienteBalance()throws Exception;
	public Vector obtenerFacturasCliente(int mesLI,int anioLI)throws Exception;
	public Vector obtenerFacturasClienteFechaNroyVendedor(int mesLI,int anioLI,String fe,String nro,String nomVend)throws Exception;
	public FacturaClienteDTO buscarFacturaClienteDTOId(Long id) throws Exception;
	public FacturaCliente buscarFacturaClienteId(ManipuladorPersistencia mp,Long id) throws Exception;
	public Long obtenerNroFacturaCliente()throws Exception;

}
