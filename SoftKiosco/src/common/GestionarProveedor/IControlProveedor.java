package common.GestionarProveedor;

import java.rmi.Remote;
import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.Proveedor;

import common.DTOs.ProveedorDTO;

public interface IControlProveedor extends Remote{

	public Long agregarProveedorDTO(ProveedorDTO p)throws Exception;
	public void eliminarProveedorDTO(Long id)throws Exception;
	public void modificarProveedor(Long id,ProveedorDTO modificado)throws Exception;
	public Vector obtenerProveedores()throws Exception;
	public Vector obtenerProveedoresCodyNom(String cod,String nom)throws Exception;
	public boolean existeProveedorNombre(String loc)throws Exception;
	public boolean existeProveedorCodigo(Long codigo)throws Exception;
	public ProveedorDTO buscarProveedorDTOId(Long id) throws Exception;
	public Proveedor buscarProveedorId(ManipuladorPersistencia mp,Long id) throws Exception;
	public boolean puedoEditar(ProveedorDTO dto,ProveedorDTO modificado)throws Exception;
	public boolean proveedorAsociado(Long id) throws Exception;
	public Long obtenerNroProveedor()throws Exception;
	
}
