package common.RealizarPlanillaES;

import java.rmi.Remote;
import java.sql.Timestamp;
import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.PlanillaES;

import common.DTOs.PlanillaESDTO;

public interface IControlRealizarPlanillaES extends Remote{
	public void agregarPlanillaESDTOTotal(PlanillaESDTO pDTO,Vector movimientos, Vector facturas, Vector usrTrab) throws Exception;
	public void eliminarPlanillaESDTO(Long id)throws Exception;
	public Vector obtenerPlanillasES(int mesLI,int anioLI)throws Exception;
	public Vector obtenerPlanillasESFecha(int mesLI,int anioLI,String fe)throws Exception;
	public Vector obtenerPlanillasESNro(int mesLI,int anioLI,String nro)throws Exception;
	public Vector obtenerMovimientosCajaBalance(Timestamp fechaH)throws Exception;
	public Vector obtenerFacturasClienteBalance(Timestamp fechaH)throws Exception;
	public Vector obtenerUsuarioParaPlanillaES(Timestamp fechaAnterior) throws Exception;
	public PlanillaESDTO buscarPlanillaESDTONroPlanilla(int numero) throws Exception;
	public PlanillaESDTO buscarPlanillaESDTOId(Long id) throws Exception;
	public PlanillaES buscarPlanillaESId(ManipuladorPersistencia mp, Long id) throws Exception;
	public PlanillaESDTO obtenerUltimaPlanilla()throws Exception;
		
}
