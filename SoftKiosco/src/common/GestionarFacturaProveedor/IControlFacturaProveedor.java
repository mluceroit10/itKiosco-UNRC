package common.GestionarFacturaProveedor;

import java.rmi.Remote;
import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.FacturaProveedor;

import common.DTOs.FacturaProveedorDTO;

public interface IControlFacturaProveedor extends Remote{

	public double agregarFacturaProveedorTotal(FacturaProveedorDTO fp)throws Exception;
	public Long agregarFacturaProveedor(FacturaProveedorDTO fp)throws Exception;
	public Vector obtenerFacturaProveedores(int mesLI,int anioLI)throws Exception;
	public Vector obtenerFacturaProveedoresFechaNroyProveedor(int mesLI,int anioLI,String fe,String nro,String nomProv)throws Exception;
	public FacturaProveedorDTO buscarFacturaProveedorDTOId(Long id) throws Exception;
	public FacturaProveedor buscarFacturaProveedorId(ManipuladorPersistencia mp,Long id) throws Exception;
	public Long obtenerNroFacturaProveedor()throws Exception;

}
