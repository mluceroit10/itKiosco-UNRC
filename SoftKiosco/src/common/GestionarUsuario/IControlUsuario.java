package common.GestionarUsuario;

import java.rmi.Remote;
import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.Usuario;

import common.DTOs.UsuarioDTO;

public interface IControlUsuario extends Remote{

	public Long agregarUsuarioDTO(UsuarioDTO usr)throws Exception;
	public void eliminarUsuarioDTO(Long id)throws Exception;
	public void modificarUsuario(Long id,UsuarioDTO modificado)throws Exception;
	public Vector obtenerUsuarios()throws Exception;
	public Vector obtenerUsuariosNomyAp(String nom)throws Exception;
	public Vector obtenerFacturasDeUsuario(Long idUsr)throws Exception;
	public boolean existeUsuarioDni(int dni)throws Exception;
	public boolean existeUsuarioNombUsr(String nombUsr)throws Exception;
	public UsuarioDTO buscarUsuarioDTOId(Long id) throws Exception;
	public Usuario buscarUsuarioId(ManipuladorPersistencia mp, Long id) throws Exception;
	public boolean puedoEditar(UsuarioDTO dto,UsuarioDTO modificado)throws Exception;
	public boolean usuarioAsociado(Long id) throws Exception;
	public UsuarioDTO verificarUsrContr(Long idUsr,String contr) throws Exception;

}
