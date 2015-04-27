package cliente.GestionarProveedor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cliente.ClienteConection;
import cliente.GestionarLocalidad.MediadorGestionarLocalidad;

import common.Utils;
import common.DTOs.LocalidadDTO;
import common.DTOs.ProveedorDTO;
import common.GestionarProveedor.IControlProveedor;

public class MediadorAltaProveedor implements ActionListener {
	private GUIAltaModProveedor guiAltaProveedor = null;
	public IControlProveedor controlProveedor;
	private MediadorGestionarProveedor mgc;
	private MediadorGestionarLocalidad medGestionarLoc;
	public LocalidadDTO localidad;

	public MediadorAltaProveedor(MediadorGestionarProveedor oldMGC,GUIGestionarProveedor guiPadre) throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorAltaProveedor. Constructor");
		}
		this.controlProveedor = clienteConexion.getIControlProveedor();
		Long nro= this.controlProveedor.obtenerNroProveedor();
		guiAltaProveedor = new GUIAltaModProveedor(nro,guiPadre);
		guiAltaProveedor.setActionListeners(this);
		mgc = oldMGC;
		Utils.show(guiAltaProveedor);
	}

	public void actionPerformed(ActionEvent e) {
		if ((((Component)e.getSource()).getName().compareTo("Aceptar")) == 0) {
			String nombre = Utils.mayuscula(guiAltaProveedor.getNombre().getText());
			String tel = guiAltaProveedor.getTelefono().getText();
			String codigo = guiAltaProveedor.getCodigo().getText();
			String direccion = Utils.mayuscula(guiAltaProveedor.getDireccion().getText());
			String nLoc=guiAltaProveedor.getLocalidad().getText();
			try{
				if (nombre.length()==0 || codigo.length()==0){
					Utils.advertenciaUsr(guiAltaProveedor,"Por favor ingrese el nombre y código del proveedor.");
				}else if(this.controlProveedor.existeProveedorCodigo(new Long(codigo))){
					Utils.advertenciaUsr(guiAltaProveedor,"El proveedor que desea ingresar ya existe en el sistema con ese código.");
				}else if(this.controlProveedor.existeProveedorNombre(nombre)){
					Utils.advertenciaUsr(guiAltaProveedor,"El proveedor que desea ingresar ya existe en el sistema con ese nombre.");
				}else if (nombre.length()==0 || codigo.length()==0 || direccion.length()==0 ||nLoc.length()==0){
					Utils.advertenciaUsr(guiAltaProveedor,"Alguno de los campos obligatorios esta vacio.");
				} else{
					ProveedorDTO proveedor = new ProveedorDTO();
					proveedor.setNombre(nombre);
					proveedor.setCodigo(new Long(codigo));
					proveedor.setTelefono(tel);
					proveedor.setDireccion(direccion);
					proveedor.setLocalidad(localidad);
					proveedor.setEliminado(false);
					this.controlProveedor.agregarProveedorDTO(proveedor);
					guiAltaProveedor.dispose();
					mgc.cargarDatos();
				}
			} catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorAltaProveedor. ActionPerformed");
			}
		}else if ((((Component)e.getSource()).getName().compareTo("Buscar")) == 0) {
			buscarLocalidad();
		}else if ((((Component)e.getSource()).getName().compareTo("Cancelar")) == 0) {
			guiAltaProveedor.dispose();
		}
	}

	private void buscarLocalidad() {
		if (medGestionarLoc== null) {
			medGestionarLoc= new MediadorGestionarLocalidad(this,guiAltaProveedor);
		} else {
			if (!medGestionarLoc.getGUI().isVisible()) {
				medGestionarLoc.getGUI().setVisible(true);
			}
		}
		if (localidad != null){
			this.guiAltaProveedor.getLocalidad().setText(localidad.getNombre());
			this.cargarLocalidad(localidad);
		}
	}

	public void actualizarLocalidad() {
		guiAltaProveedor.setLocalidad(localidad.getNombre());
	}

	private void cargarLocalidad(LocalidadDTO loc) {
		this.localidad = loc;
	}

}