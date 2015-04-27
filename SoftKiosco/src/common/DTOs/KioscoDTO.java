package common.DTOs;

import java.io.Serializable;
import java.sql.Date;

public class KioscoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String telefono;
	private String direccion; 
	private LocalidadDTO localidad= new LocalidadDTO();
	private String encargado;
	private Date inicioAct;

	public KioscoDTO(){
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

	public String getEncargado() {
		return encargado;
	}

	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}

	public Date getInicioAct() {
		return inicioAct;
	}

	public void setInicioAct(Date inicioAct) {
		this.inicioAct = inicioAct;
	}

}
