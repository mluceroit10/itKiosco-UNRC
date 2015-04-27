package server.persistencia.dominio;
  
import server.persistencia.OidGenerator;

public class ItemFactura {
	private Long id;
	private int cantidad;
	private Producto producto = null;
	private Factura factura = null;
	private double prUnit;
	private double prTotal;
	
	public ItemFactura(){
		id=OidGenerator.getNewId();	
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
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
