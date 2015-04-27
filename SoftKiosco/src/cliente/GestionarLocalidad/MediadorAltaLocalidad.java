package cliente.GestionarLocalidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cliente.ClienteConection;
import cliente.GestionarProvincia.MediadorGestionarProvincia;

import common.Utils;
import common.DTOs.LocalidadDTO;
import common.DTOs.ProvinciaDTO;
import common.GestionarLocalidad.IControlLocalidad;

public class MediadorAltaLocalidad implements ActionListener {
	private GUIAltaModLocalidad guiAltaLocalidad = null;
	private MediadorGestionarLocalidad mgLocalidad;
	private MediadorGestionarProvincia medGestionarProvincia;
	public IControlLocalidad controlLocalidad;
	public LocalidadDTO locDTO;
	public ProvinciaDTO prov;

	public MediadorAltaLocalidad(MediadorGestionarLocalidad oldMGLocalidad,GUIGestionarLocalidad guiPadre) throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorAltaLocalidad. Constructor");
		}
		this.controlLocalidad = clienteConexion.getIControlLocalidad();
		guiAltaLocalidad = new GUIAltaModLocalidad(guiPadre);
		guiAltaLocalidad.setActionListeners(this);
		mgLocalidad = oldMGLocalidad;
		Utils.show(guiAltaLocalidad);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiAltaLocalidad.getJBAceptar()) {
			String nombre = Utils.mayuscula(guiAltaLocalidad.getJTFNombre().getText());
			String codPostal = guiAltaLocalidad.getJTFCodPostal().getText();
			String provincia =guiAltaLocalidad.getJTFProvincia().getText();
			try {
				if (nombre.length()==0 || provincia.length() ==0){
					Utils.advertenciaUsr(guiAltaLocalidad,"Alguno de los campos obligatorios esta vacio.");
				}else if (this.controlLocalidad.existeLocalidadNombre(nombre)){
					Utils.advertenciaUsr(guiAltaLocalidad,"La localidad ingresada ya existe.");
				}else{
					LocalidadDTO nuevaLocalidad = new LocalidadDTO();
					nuevaLocalidad.setNombre(nombre);
					nuevaLocalidad.setCodPostal(codPostal);
					nuevaLocalidad.setProvincia(prov);
					this.controlLocalidad.agregarLocalidadDTO(nuevaLocalidad);
					guiAltaLocalidad.dispose();
					mgLocalidad.cargarDatos();
				}
			} catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorAltaLocalidad. ActionPerformed");
			}
		}else if (source == guiAltaLocalidad.getJBProvincia()) {
			buscarProvincia();
		} else if (source == guiAltaLocalidad.getJBCancelar()) {
			guiAltaLocalidad.dispose();
		}
	}

	private void buscarProvincia() {
		if (medGestionarProvincia== null) {
			medGestionarProvincia= new MediadorGestionarProvincia(this,guiAltaLocalidad); 
		} else {
			if (!medGestionarProvincia.getGUI().isVisible()) {
				medGestionarProvincia.getGUI().setVisible(true);
			}
		}
		if (prov != null){
			this.guiAltaLocalidad.getJTFProvincia().setText(prov.getNombre());
			this.cargarProvincia(prov);
		}
	}

	private void cargarProvincia(ProvinciaDTO pr) {
		this.prov = pr;
	}

	public void actualizarProvincia() {
		guiAltaLocalidad.setProvincia(prov.getNombre());
	}

}

