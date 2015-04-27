package server.persistencia.dominio;

import java.sql.Timestamp;

import server.persistencia.OidGenerator;

public class BecaAsignada {
	private Long id;
	private Timestamp fecha;
	private boolean cierrePlazo;
	private double importeAsignado;
	private double importeRestante;
	private Usuario usuario;
	
	public BecaAsignada(){
		id=OidGenerator.getNewId();	
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
