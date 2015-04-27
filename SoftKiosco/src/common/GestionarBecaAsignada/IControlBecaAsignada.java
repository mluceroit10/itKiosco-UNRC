package common.GestionarBecaAsignada;

import java.rmi.Remote;
import java.util.Date;
import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.BecaAsignada;

public interface IControlBecaAsignada extends Remote{

	public Vector obtenerBecasAsignadas()throws Exception;
	public Vector obtenerBecasAsignadasDeUsuario(Long idUsr)throws Exception;
	public BecaAsignada buscarBecaActualDeUsuario(ManipuladorPersistencia mp,Long idUsr) throws Exception;
	public boolean hayBecasDelMesAnio(Date hoy) throws Exception;
	public boolean cerrarYAbrirNuevasBecas(Date hoy) throws Exception;
	public boolean abrirBecas(Vector usrSinBeca,Date hoy) throws Exception;
	public void eliminarBecasDeUsuario(ManipuladorPersistencia mp, Long idUsr)throws Exception;
}
