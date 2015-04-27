package cliente.GestionarProveedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cliente.ClienteConection;
import cliente.GestionarLocalidad.MediadorGestionarLocalidad;

import common.Utils;
import common.DTOs.LocalidadDTO;
import common.DTOs.ProveedorDTO;
import common.GestionarProveedor.IControlProveedor;

public class MediadorModificarProveedor implements ActionListener {
	private GUIAltaModProveedor guiModProveedor = null;
	public IControlProveedor controlProveedor;
	private MediadorGestionarProveedor mgProveedor;
	private MediadorGestionarLocalidad mgLoc = null;
	public ProveedorDTO prov;
	public LocalidadDTO localidad;
	private Long codProv;

	public MediadorModificarProveedor(MediadorGestionarProveedor oldMGProveedor, ProveedorDTO pr,GUIGestionarProveedor guiPadre) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorModificarProveedor. Constructor");
		}
		this.controlProveedor = clienteConexion.getIControlProveedor();
		guiModProveedor = new GUIAltaModProveedor(pr,guiPadre); 
		guiModProveedor.setActionListeners(this);
		mgProveedor = oldMGProveedor;
		prov = pr;
		codProv = pr.getCodigo();
		localidad = pr.getLocalidad();
		Utils.show(guiModProveedor);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiModProveedor.getAceptar()) {
			String nombre = Utils.mayuscula(guiModProveedor.getNombre().getText());
			String tel = guiModProveedor.getTelefono().getText();
			String codigo = guiModProveedor.getCodigo().getText();
			String direccion = Utils.mayuscula(guiModProveedor.getDireccion().getText());
			String nLoc=guiModProveedor.getLocalidad().getText();
			try{
				if (nombre.length()==0 || codigo.length()==0 || direccion.length()==0 ||nLoc.length()==0){
					Utils.advertenciaUsr(guiModProveedor,"Alguno de los campos obligatorios esta vacio.");
				}else if(!(new Long(codigo)).equals(codProv) && this.controlProveedor.existeProveedorCodigo(new Long(codigo))){
					Utils.advertenciaUsr(guiModProveedor,"El proveedor que desea ingresar ya existe en el sistema con ese código.");	
				}else{
					ProveedorDTO proveedor = new ProveedorDTO();
					proveedor.setNombre(nombre);
					proveedor.setCodigo(new Long(codigo));
					proveedor.setTelefono(tel);
					proveedor.setDireccion(direccion);
					proveedor.setLocalidad(localidad);
					proveedor.setEliminado(false);
					if (this.controlProveedor.puedoEditar(prov,proveedor)){
						this.controlProveedor.modificarProveedor(prov.getId(), proveedor);
						guiModProveedor.dispose();
						mgProveedor.cargarDatos();
					} else {
						Utils.advertenciaUsr(guiModProveedor,"El Proveedor que desea ingresar ya existe en el sistema.");
					}

				}
			} catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorModificarProveedor. ActionPerformed");
			}
		} else if (source == guiModProveedor.getJButtonLocalidad()) {
			buscarLocalidad();
		} else if (source == guiModProveedor.getCancelar()) {    
			guiModProveedor.dispose();
		}
	}

	private void buscarLocalidad() {
		if (mgLoc == null) {
			mgLoc = new MediadorGestionarLocalidad(this,guiModProveedor);
		} else {
			if (!mgLoc.getGUI().isVisible()) {
				mgLoc.getGUI().setVisible(true);
			}
		}
		if (localidad != null){
			this.guiModProveedor.getLocalidad().setText(localidad.getNombre());
			this.cargarLocalidad(localidad);           
		}
	}

	private void cargarLocalidad(LocalidadDTO loc) {
		this.localidad = loc;
	}

	public void actualizarLocalidad() {
		guiModProveedor.setLocalidad(localidad.getNombre());
	}

}
