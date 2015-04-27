package common.DTOs;

import java.io.Serializable;
import java.sql.Timestamp;

public class SesionDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Timestamp inicio;
	private Timestamp cierre;
	private UsuarioDTO usuario;

	public SesionDTO(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getInicio() {
		return inicio;
	}

	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}

	public Timestamp getCierre() {
		return cierre;
	}

	public void setCierre(Timestamp cierre) {
		this.cierre = cierre;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
