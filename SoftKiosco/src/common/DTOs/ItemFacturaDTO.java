package common.DTOs;

import java.io.Serializable;

public class ItemFacturaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private int cantidad;
	private ProductoDTO producto = new ProductoDTO();
	private FacturaDTO factura = new FacturaDTO();
	private double prUnit;
	private double prTotal;

	public ItemFacturaDTO(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

	public FacturaDTO getFactura() {
		return factura;
	}

	public void setFactura(FacturaDTO factura) {
		this.factura = factura;
	}

	public double getPrUnit() {
		return prUnit;
	}

	public void setPrUnit(double prUnit) {
		this.prUnit = prUnit;
	}

	public double getPrTotal() {
		return prTotal;
	}

	public void setPrTotal(double prTotal) {
		this.prTotal = prTotal;
	}

}
