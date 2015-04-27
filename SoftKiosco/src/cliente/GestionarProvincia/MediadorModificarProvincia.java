package cliente.GestionarProvincia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cliente.ClienteConection;

import common.Utils;
import common.DTOs.ProvinciaDTO;
import common.GestionarProvincia.IControlProvincia;

public class MediadorModificarProvincia implements ActionListener {
	private GUIAltaModProvincia guiProvincia = null;
	private MediadorGestionarProvincia mgProvincia;
	private IControlProvincia controlProvincia;
	public ProvinciaDTO prov;

	public MediadorModificarProvincia(MediadorGestionarProvincia oldMGProvincia, ProvinciaDTO p,GUIGestionarProvincia guiPadre) { 
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorModificarProvincia. Constructor");
		}
		this.controlProvincia = clienteConexion.getIControlProvincia();
		guiProvincia = new GUIAltaModProvincia(p,guiPadre); 
		guiProvincia.setActionListeners(this);
		mgProvincia = oldMGProvincia;
		prov = p;
		Utils.show(guiProvincia); 
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiProvincia.getJBAceptar()) {
			String nombre = Utils.mayuscula(guiProvincia.getJTFNombre().getText());
			if (nombre.length()==0){
				Utils.advertenciaUsr(guiProvincia,"Alguno de los campos obligatorios esta vacio.");
			} else {
				try {
					ProvinciaDTO miProvincia = new ProvinciaDTO();
					miProvincia.setNombre(nombre);
					if (this.controlProvincia.puedoEditar(prov,miProvincia)){
						this.controlProvincia.modificarProvincia(prov.getId(), miProvincia);
						guiProvincia.dispose();
						mgProvincia.cargarDatos();
					} else {
						Utils.advertenciaUsr(guiProvincia,"La provincia que desea ingresar ya existe.");
					}
				} catch(Exception ex) {
					Utils.manejoErrores(ex,"Error en MediadorModificarProvincia. ActionPerformed");
				}
			}
		} else if (source == guiProvincia.getJBCancelar()) {   
			guiProvincia.dispose();
		}
	}

}

