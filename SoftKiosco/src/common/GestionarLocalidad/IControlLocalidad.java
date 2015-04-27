package common.GestionarLocalidad;

import java.rmi.Remote;
import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.Localidad;

import common.DTOs.LocalidadDTO;

public interface IControlLocalidad extends Remote{

	public Long agregarLocalidadDTO(LocalidadDTO locDTO)throws Exception;
	public void eliminarLocalidadDTO(Long id) throws Exception;
	public void modificarLocalidad(Long id,LocalidadDTO modificado)throws Exception;
	public Vector obtenerLocalidades()throws Exception;
	public Vector obtenerLocalidadesNombre(String nom)throws Exception;
	public boolean existeLocalidadNombre(String nombre)throws Exception;
	public LocalidadDTO buscarLocalidadDTOId(Long id) throws Exception;
	public Localidad buscarLocalidadId(ManipuladorPersistencia mp,Long id) throws Exception;
	public boolean puedoEditar(LocalidadDTO dto,LocalidadDTO modificado)throws Exception;
	public boolean localidadAsociada(Long id) throws Exception;

}
