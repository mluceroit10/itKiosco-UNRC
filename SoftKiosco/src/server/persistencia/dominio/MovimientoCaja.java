package server.persistencia.dominio;

import java.sql.Timestamp;

import server.persistencia.OidGenerator;
  
public class MovimientoCaja {
	private Long id;
	private Timestamp fechaMC;
	private Long nroRecibo; //unico  
	private String descripcion;
	private int tipoMovimiento;
	private double importe;
	private PlanillaES planilla;
	private boolean conFactura;
	private FacturaProveedor factura;
	private String tipoCajaRegistrado;
	private String periodo;
	
	public MovimientoCaja(){
		id=OidGenerator.getNewId();	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getFechaMC() {
		return fechaMC;
	}

	public void setFechaMC(Timestamp fechaMC) {
		this.fechaMC = fechaMC;
	}

	public Long getNroRecibo() {
		return nroRecibo;
	}

	public void setNroRecibo(Long nroRecibo) {
		this.nroRecibo = nroRecibo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(int tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public PlanillaES getPlanilla() {
		return planilla;
	}

	public void setPlanilla(PlanillaES planilla) {
		this.planilla = planilla;
	}

	public boolean isConFactura() {
		return conFactura;
	}

	public void setConFactura(boolean conFactura) {
		this.conFactura = conFactura;
	}

	public FacturaProveedor getFactura() {
		return factura;
	}

	public void setFactura(FacturaProveedor factura) {
		this.factura = factura;
	}

	public String getTipoCajaRegistrado() {
		return tipoCajaRegistrado;
	}

	public void setTipoCajaRegistrado(String tipoCajaResgistrado) {
		this.tipoCajaRegistrado = tipoCajaResgistrado;
	}
		
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
}
