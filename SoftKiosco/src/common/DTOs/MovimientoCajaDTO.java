package common.DTOs;

import java.io.Serializable;
import java.sql.Timestamp;

public class MovimientoCajaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Timestamp fecha;
	private Long nroRecibo;
	private String descripcion;
	private int tipoMovimiento;
	private double importe;
	private PlanillaESDTO planilla=new PlanillaESDTO();
	private boolean conFactura;
	private FacturaProveedorDTO factura;
	private String tipoCajaRegistrado;
	private String periodo;
	
	public MovimientoCajaDTO(){
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Long getNroRecibo() {
		return nroRecibo;
	}

	public void setNroRecibo(Long nroRecibo) {
		this.nroRecibo = nroRecibo;
	}
	//	1entrada 0salida
	public int getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(int tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlanillaESDTO getPlanilla() {
		return planilla;
	}

	public void setPlanilla(PlanillaESDTO planilla) {
		this.planilla = planilla;
	}

	public boolean isConFactura() {
		return conFactura;
	}

	public void setConFactura(boolean conFactura) {
		this.conFactura = conFactura;
	}

	public FacturaProveedorDTO getFactura() {
		return factura;
	}

	public void setFactura(FacturaProveedorDTO factura) {
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
