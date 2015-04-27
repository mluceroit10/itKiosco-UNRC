package common.DTOs;

import java.io.Serializable;

public class ProductoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private Long codigo;
	private int stockActual;
	private int stockMinimo;
	private double precioEntrada;
	private int margenGanancia;
	private double precioVenta;
	private ProveedorDTO proveedor= new ProveedorDTO();
	private boolean eliminado;
	
	public ProductoDTO(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public int getStockActual() {
		return stockActual;
	}

	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public double getPrecioEntrada() {
		return precioEntrada;
	}

	public void setPrecioEntrada(double precioEntrada) {
		this.precioEntrada = precioEntrada;
	}

	public int getMargenGanancia() {
		return margenGanancia;
	}

	public void setMargenGanancia(int margenGanancia) {
		this.margenGanancia = margenGanancia;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public ProveedorDTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}
	
	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

}
