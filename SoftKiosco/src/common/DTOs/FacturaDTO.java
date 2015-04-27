package common.DTOs;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Vector;

public class FacturaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long nroFactura;
	private Vector items=new Vector();
	private String tipoFactura; 
	private double importeTotal; 
	private Timestamp fechaHora;
	private String periodo;
	
	public FacturaDTO(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(Long nroFactura) {
		this.nroFactura = nroFactura;
	}

	public Vector getItems() {
		return items;
	}

	public void setItems(Vector items) {
		this.items = items;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Timestamp getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
}
