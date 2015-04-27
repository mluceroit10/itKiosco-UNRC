package cliente.GestionarProvincia; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cliente.ClienteConection;

import common.Utils;
import common.DTOs.ProvinciaDTO;
import common.GestionarProvincia.IControlProvincia;

public class MediadorAltaProvincia implements ActionListener {
	private GUIAltaModProvincia guiProvincia = null;
	private MediadorGestionarProvincia mgProvincia;
	public IControlProvincia controlProvincia;

	public MediadorAltaProvincia(MediadorGestionarProvincia oldMGProvincia,GUIGestionarProvincia guiPadre) throws Exception { 
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorAltaProvincia. Constructor");
		}
		this.controlProvincia = clienteConexion.getIControlProvincia();
		guiProvincia = new GUIAltaModProvincia(guiPadre); 
		guiProvincia.setActionListeners(this);
		mgProvincia = oldMGProvincia;
		Utils.show(guiProvincia); 
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiProvincia.getJBAceptar()) {
			String nombre = Utils.mayuscula(guiProvincia.getJTFNombre().getText());
			try {
				if (nombre.length()==0){
					Utils.advertenciaUsr(guiProvincia,"Alguno de los campos obligatorios esta vacio.");
				}else if (this.controlProvincia.existeProvinciaNombre(nombre)){
					Utils.advertenciaUsr(guiProvincia,"La provincia ingresada ya existe.");
				}else{
					ProvinciaDTO miProvincia = new ProvinciaDTO();
					miProvincia.setNombre(nombre);
					this.controlProvincia.agregarProvinciaDTO(miProvincia);
					guiProvincia.dispose();
					mgProvincia.cargarDatos();
				}
			} catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorAltaProvincia. ActionPerformed");
			}
		} else if (source == guiProvincia.getJBCancelar()) {
			guiProvincia.dispose();
		}
	}

}

