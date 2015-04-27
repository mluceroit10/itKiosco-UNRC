package cliente.GestionarKiosco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JFrame;

import cliente.ClienteConection;
import cliente.GestionarLocalidad.MediadorGestionarLocalidad;
import cliente.Principal.GUIReport;

import common.Utils;
import common.DTOs.KioscoDTO;
import common.DTOs.LocalidadDTO;
import common.GestionarKiosco.IControlKiosco;

public class MediadorModificarKiosco implements ActionListener {
	public LocalidadDTO localidad;
	private GUIAltaModKiosco guiKiosco = null;
	public IControlKiosco controlKiosco;
	private MediadorGestionarLocalidad mgLoc = null;
	public KioscoDTO kiosco=null;

	public MediadorModificarKiosco(JFrame guiPadre,boolean permisoEdicion) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorModificarKiosco. Constructor");
		}
		this.controlKiosco = clienteConexion.getIControlKiosco();
		try{
			kiosco = controlKiosco.obtenerKiosco();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorModificarKiosco. Constructor");
		}
		if(kiosco!=null)
			localidad = kiosco.getLocalidad();
		guiKiosco = new GUIAltaModKiosco(kiosco,guiPadre,permisoEdicion);
		guiKiosco.setActionListeners(this);
		Utils.show(guiKiosco);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiKiosco.getAceptar()) {
			String nombre = Utils.mayuscula(guiKiosco.getNombre().getText());
			String tel = guiKiosco.getTelefono().getText();
			String direccion = Utils.mayuscula(guiKiosco.getDireccion().getText());
			String nLoc=guiKiosco.getLocalidad().getText();
			String encargado=guiKiosco.getEncargado().getText();
			java.util.Date fu=guiKiosco.getJDateChooserFecha().getDate();
			Date fecha= Utils.crearFecha(Utils.getDia(fu),Utils.getMes(fu),Utils.getAnio(fu));
			if (nombre.length()==0 || direccion.length()==0 ||nLoc.length()==0 || encargado.length()==0){
				Utils.advertenciaUsr(guiKiosco,"Alguno de los campos obligatorios esta vacio.");
			} else {
				try{
					KioscoDTO dNew = new KioscoDTO();
					dNew.setNombre(nombre);
					dNew.setTelefono(tel);
					dNew.setDireccion(direccion);
					dNew.setInicioAct(fecha);
					dNew.setEncargado(encargado);
					dNew.setLocalidad(localidad);
					if (kiosco!=null){
						controlKiosco.modificarKiosco(kiosco.getId(),dNew);
					}else{
						controlKiosco.agregarKioscoDTO(dNew);
					}
					guiKiosco.dispose();
				} catch(Exception ex) {
					Utils.manejoErrores(ex,"Error en MediadorModificarKiosco. ActionPerformed.");
				}
			}
		} else if (source == guiKiosco.getJButtonLocalidad()) {
			buscarLocalidad();
		} else if (source == guiKiosco.getJButtonImprimirTarjeta()) {
			String nombre = Utils.mayuscula(guiKiosco.getNombre().getText());
			String encargado = guiKiosco.getEncargado().getText();
			String tel = guiKiosco.getTelefono().getText();
			String direccion = Utils.mayuscula(guiKiosco.getDireccion().getText());
			String nLoc=guiKiosco.getLocalidad().getText();
			try {
				new GUIReport(guiKiosco,9,nombre, encargado, tel, direccion, nLoc);
			} catch (Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorModificarKiosco. ImprimirTarjeta.");
			} 
		} else if (source == guiKiosco.getCancelar()) {    
			guiKiosco.dispose();
		}
	}

	private void buscarLocalidad() {
		if (mgLoc == null) {
			mgLoc = new MediadorGestionarLocalidad(this,guiKiosco);
		} else {
			if (!mgLoc.getGUI().isVisible()) {
				mgLoc.getGUI().setVisible(true);
			}
		}
		if (localidad != null){
			this.guiKiosco.getLocalidad().setText(localidad.getNombre());
			this.cargarLocalidad(localidad);           
		}
	}

	private void cargarLocalidad(LocalidadDTO loc) {
		this.localidad = loc;
	}

	public void actualizarLocalidad() {
		guiKiosco.setLocalidad(localidad.getNombre());
	}

}
