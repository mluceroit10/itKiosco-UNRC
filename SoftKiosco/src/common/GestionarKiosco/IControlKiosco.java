package common.GestionarKiosco;

import java.rmi.Remote;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.Kiosco;

import common.DTOs.KioscoDTO;

public interface IControlKiosco extends Remote{

	public Long agregarKioscoDTO(KioscoDTO kiDTO)throws Exception;
	public void modificarKiosco(Long id,KioscoDTO modificado)throws Exception;
	public KioscoDTO obtenerKiosco()throws Exception;
	public Kiosco buscarKioscoId(ManipuladorPersistencia mp,Long id) throws Exception;
	
}
