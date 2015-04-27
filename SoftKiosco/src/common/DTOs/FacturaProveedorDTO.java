package common.DTOs;

import java.io.Serializable;

public class FacturaProveedorDTO extends FacturaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProveedorDTO proveedor=new ProveedorDTO();

	public FacturaProveedorDTO(){
	}

	public ProveedorDTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}

}
