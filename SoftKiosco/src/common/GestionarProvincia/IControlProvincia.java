package common.GestionarProvincia;

import java.rmi.Remote;
import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.Provincia;

import common.DTOs.ProvinciaDTO;

public interface IControlProvincia extends Remote{

	public Long agregarProvinciaDTO(ProvinciaDTO provDTO)throws Exception;
	public void eliminarProvinciaDTO(Long id) throws Exception;
	public void modificarProvincia(Long id,ProvinciaDTO modificado)throws Exception;
	public Vector obtenerProvincias()throws Exception;
	public Vector obtenerProvinciasNombre(String nom)throws Exception;
	public boolean existeProvinciaNombre(String nombre)throws Exception;
	public ProvinciaDTO buscarProvinciaDTOId(Long id) throws Exception;
	public Provincia buscarProvinciaId(ManipuladorPersistencia mp,Long id) throws Exception;
	public boolean puedoEditar(ProvinciaDTO dto,ProvinciaDTO modificado)throws Exception;
	public boolean provinciaAsociada(Long id) throws Exception;
}
