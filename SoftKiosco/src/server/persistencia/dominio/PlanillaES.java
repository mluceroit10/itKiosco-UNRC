package server.persistencia.dominio;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import server.persistencia.OidGenerator;

public class PlanillaES { 
	private Long id;
	private int nroPlanilla;
	private Timestamp fechaP;
	private double ingresoCajaDiaria;
	private double saldoCajaDiaria;
	private double saldoCajaGeneral;
	private Set movimientosCaja = new HashSet();
	private Set facturas = new HashSet();
	private Set usuariosTrab = new HashSet();
	private String periodo;
	private double retiro;
	private double enCaja;
	
	public double getEnCaja() {
		return enCaja;
	}

	public void setEnCaja(double enCaja) {
		this.enCaja = enCaja;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public double getRetiro() {
		return retiro;
	}

	public void setRetiro(double retiro) {
		this.retiro = retiro;
	}

	public PlanillaES(){
		id=OidGenerator.getNewId();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Timestamp getFecha() {
		return fechaP;
	}

	public void setFecha(Timestamp fecha) {
		this.fechaP = fecha;
	}

	public int getNroPlanilla() {
		return nroPlanilla;
	}

	public void setNroPlanilla(int nroPlanilla) {
		this.nroPlanilla = nroPlanilla;
	}

	public Set getMovimientosCaja() {
		return movimientosCaja;
	}

	public void setMovimientosCaja(Set movimientosCaja) {
		this.movimientosCaja = movimientosCaja;
	}

	public Set getFacturas() {
		return facturas;
	}

	public void setFacturas(Set facturas) {
		this.facturas = facturas;
	}

	public double getIngresoCajaDiaria() {
		return ingresoCajaDiaria;
	}

	public void setIngresoCajaDiaria(double ingresoCajaDiaria) {
		this.ingresoCajaDiaria = ingresoCajaDiaria;
	}

	public double getSaldoCajaDiaria() {
		return saldoCajaDiaria;
	}

	public void setSaldoCajaDiaria(double saldoCajaDiaria) {
		this.saldoCajaDiaria = saldoCajaDiaria;
	}

	public double getSaldoCajaGeneral() {
		return saldoCajaGeneral;
	}

	public void setSaldoCajaGeneral(double saldoCajaGeneral) {
		this.saldoCajaGeneral = saldoCajaGeneral;
	}

	public Set getUsuariosTrab() {
		return usuariosTrab;
	}

	public void setUsuariosTrab(Set usuarios) {
		this.usuariosTrab = usuarios;
	}
	
}
