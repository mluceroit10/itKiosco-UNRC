package server.persistencia.dominio;

import java.sql.Timestamp;

import server.persistencia.OidGenerator;
  
public class Sesion {
	private Long id;
	private Timestamp inicio;
	private Timestamp cierre;
	private Usuario usuario;
	
	public Sesion(){
		id=OidGenerator.getNewId();	
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
