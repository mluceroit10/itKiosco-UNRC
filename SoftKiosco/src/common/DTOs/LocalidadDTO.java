package common.DTOs;

import java.io.Serializable;

public class LocalidadDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String codPostal;
	private ProvinciaDTO provincia = new ProvinciaDTO();

	public LocalidadDTO(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ProvinciaDTO getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaDTO provincia) {
		this.provincia = provincia;
	}

}
