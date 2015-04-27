package common.DTOs;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private int dni;
	private String apellido;
	private String nombre;
	private String telefono;
	private String direccion; 
	private LocalidadDTO localidad;
	private String tipoUsuario;
	private String nombUsuario;
	private String contrasenia;
	private double importeBeca;
	private boolean eliminado;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getDni() {
		return dni;
	}
	
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNombUsuario() {
		return nombUsuario;
	}

	public void setNombUsuario(String nombUsuario) {
		this.nombUsuario = nombUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public double getImporteBeca() {
		return importeBeca;
	}
	
	public void setImporteBeca(double importeBeca) {
		this.importeBeca = importeBeca;
	}
	
	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
}
