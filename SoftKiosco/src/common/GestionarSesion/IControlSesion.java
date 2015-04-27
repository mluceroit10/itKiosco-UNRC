package common.GestionarSesion;

import java.rmi.Remote;
import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.Sesion;

import common.DTOs.SesionDTO;

public interface IControlSesion extends Remote{

	public Long agregarSesionDTO(SesionDTO locDTO)throws Exception;
	public void eliminarSesionesDeUsuario(ManipuladorPersistencia mp,Long idUsr)throws Exception;
	public void modificarSesion(Long id,SesionDTO modificado)throws Exception;
	public Vector obtenerSesionesUsuario(Long idUsr,int dia,int mes, int anio)throws Exception;
	public Vector obtenerUsuariosNoConectados() throws Exception;
	public Vector obtenerSesionesAbiertas()throws Exception;
	public SesionDTO buscarSesionDTOId(Long id) throws Exception;
	public Sesion buscarSesionId(ManipuladorPersistencia mp,Long id) throws Exception;
	public void cerrarSesionesAbiertas()throws Exception;

}
