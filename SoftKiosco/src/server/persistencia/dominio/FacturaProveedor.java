package server.persistencia.dominio;

public class FacturaProveedor extends Factura{
	private Proveedor proveedor=null;
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
