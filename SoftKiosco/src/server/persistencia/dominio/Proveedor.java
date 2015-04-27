package server.persistencia.dominio;

import java.util.HashSet;
import java.util.Set;

import server.persistencia.OidGenerator;

public class Proveedor {
	private Long id;
	private Long codigo;
	private String nombre;
	private String telefono;
	private String direccion; 
	private Localidad localidad;
	private Set productos = new HashSet();  
	private boolean eliminado;
	
	public Proveedor(){
		id=OidGenerator.getNewId();	
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

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Set getProductos() {
		return productos;
	}

	public void setProductos(Set productos) {
		this.productos = productos;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
}
