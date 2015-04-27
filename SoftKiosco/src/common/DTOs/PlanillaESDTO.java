package common.DTOs;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Vector;

public class PlanillaESDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private int nroPlanilla;
	private Timestamp fecha;
	// Manejo caja diaria y caja general
	private double ingresoCajaDiaria;
	private double saldoCajaDiaria;
	private double saldoCajaGeneral;
	private Vector movimientosCaja=new Vector();
	private Vector facturas=new Vector();
	private Vector usuariosTrab=new Vector();
	private String periodo;
	private double retiro;
	private double enCaja;
	
	public PlanillaESDTO(){
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Vector getMovimientosCaja() {
		return movimientosCaja;
	}

	public void setMovimientosCaja(Vector movimientosCaja) {
		this.movimientosCaja = movimientosCaja;
	}

	public int getNroPlanilla() {
		return nroPlanilla;
	}

	public void setNroPlanilla(int nroPlanilla) {
		this.nroPlanilla = nroPlanilla;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vector getFacturas() {
		return facturas;
	}

	public void setFacturas(Vector facturas) {
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

	public Vector getUsuariosTrab() {
		return usuariosTrab;
	}

	public void setUsuariosTrab(Vector usuariosTrab) {
		this.usuariosTrab = usuariosTrab;
	}
	
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public double getEnCaja() {
		return enCaja;
	}

	public void setEnCaja(double enCaja) {
		this.enCaja = enCaja;
	}

	public double getRetiro() {
		return retiro;
	}

	public void setRetiro(double retiro) {
		this.retiro = retiro;
	}
}
