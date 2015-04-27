package common.DTOs;

import java.io.Serializable;
import java.util.Vector;

public class ProveedorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long codigo;
	private String nombre;
	private String telefono;
	private String direccion; 
	private LocalidadDTO localidad;
	private Vector productos = new Vector();  
	private boolean eliminado;
	
	public ProveedorDTO(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	public Vector getProductos() {
		return productos;
	}

	public void setProductos(Vector productos) {
		this.productos = productos;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
}	 
