package cliente.GestionarLocalidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cliente.ClienteConection;
import cliente.GestionarProvincia.MediadorGestionarProvincia;

import common.Utils;
import common.DTOs.LocalidadDTO;
import common.DTOs.ProvinciaDTO;
import common.GestionarLocalidad.IControlLocalidad;

public class MediadorModificarLocalidad implements ActionListener {
	private GUIAltaModLocalidad guiLocalidad = null;
	private MediadorGestionarLocalidad mgLocalidad;
	private MediadorGestionarProvincia medGestionarProvincia;
	private IControlLocalidad controlLocalidad;
	private LocalidadDTO locDTO;
	public ProvinciaDTO prov;

	public MediadorModificarLocalidad(MediadorGestionarLocalidad oldMGLocalidad, LocalidadDTO loc,GUIGestionarLocalidad guiPadre) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorModificarLocalidad. Constructor");
		}
		this.controlLocalidad = clienteConexion.getIControlLocalidad();
		guiLocalidad = new GUIAltaModLocalidad(loc,guiPadre);
		guiLocalidad.setActionListeners(this);
		mgLocalidad = oldMGLocalidad;
		locDTO = loc;
		prov = loc.getProvincia();
		Utils.show(guiLocalidad); 
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiLocalidad.getJBAceptar()) {
			String nombre = Utils.mayuscula(guiLocalidad.getJTFNombre().getText());
			String codPostal = guiLocalidad.getJTFCodPostal().getText();
			String provincia =guiLocalidad.getJTFProvincia().getText();
			if (nombre.length()==0 || provincia.length()==0){
				Utils.advertenciaUsr(guiLocalidad,"Alguno de los campos obligatorios esta vacio.");
			} else {
				try {
					LocalidadDTO modLocalidad = new LocalidadDTO();
					modLocalidad.setNombre(nombre);
					modLocalidad.setCodPostal(codPostal);
					modLocalidad.setProvincia(prov);
					if (this.controlLocalidad.puedoEditar(locDTO,modLocalidad)){
						this.controlLocalidad.modificarLocalidad(locDTO.getId(), modLocalidad);
						guiLocalidad.dispose();
						mgLocalidad.cargarDatos();
					} else {
						Utils.advertenciaUsr(guiLocalidad,"La Localidad que desea Ingresar ya existe.");
					}
				} catch(Exception ex) {
					Utils.manejoErrores(ex,"Error en MediadorModificarLocalidad. ActionPerformed");
				}
			}
		}else if (source == guiLocalidad.getJBProvincia()) {
			buscarProvincia();
		}else if (source == guiLocalidad.getJBCancelar()) {   
			guiLocalidad.dispose();
		}
	}
	
	private void buscarProvincia() {
		if (medGestionarProvincia== null) {
			medGestionarProvincia= new MediadorGestionarProvincia(this,guiLocalidad); 
		} else {
			if (!medGestionarProvincia.getGUI().isVisible()) {
				medGestionarProvincia.getGUI().setVisible(true);
			}
		}
		if (prov != null){
			this.guiLocalidad.getJTFProvincia().setText(prov.getNombre());
			this.cargarProvincia(prov);
		}
	}

	private void cargarProvincia(ProvinciaDTO pr) {
		this.prov = pr;
	}

	public void actualizarProvincia() {
		guiLocalidad.setProvincia(prov.getNombre());
	}

}

