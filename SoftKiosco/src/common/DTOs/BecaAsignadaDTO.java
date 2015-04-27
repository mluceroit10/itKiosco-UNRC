package common.DTOs;

import java.io.Serializable;
import java.sql.Timestamp;

public class BecaAsignadaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Timestamp fecha;
	private boolean cierrePlazo;
	private double importeAsignado;
	private double importeRestante;
	private UsuarioDTO usuario= new UsuarioDTO();

	public BecaAsignadaDTO(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public boolean isCierrePlazo() {
		return cierrePlazo;
	}

	public void setCierrePlazo(boolean cierrePlazo) {
		this.cierrePlazo = cierrePlazo;
	}

	public double getImporteAsignado() {
		return importeAsignado;
	}

	public void setImporteAsignado(double importeAsignado) {
		this.importeAsignado = importeAsignado;
	}

	public double getImporteRestante() {
		return importeRestante;
	}

	public void setImporteRestante(double importeRestante) {
		this.importeRestante = importeRestante;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
}
