package cliente.GestionarUsuario;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cliente.ClienteConection;
import cliente.GestionarLocalidad.MediadorGestionarLocalidad;

import common.Utils;
import common.DTOs.LocalidadDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarUsuario.IControlUsuario;

public class MediadorAltaUsuario implements ActionListener {
	private GUIAltaModUsuario guiAltaUsuario = null;
	public IControlUsuario controlUsuario;
	private MediadorGestionarUsuario mgc;
	private MediadorGestionarLocalidad medGestionarLoc;
	public LocalidadDTO localidad;

	public MediadorAltaUsuario(MediadorGestionarUsuario oldMGC,GUIGestionarUsuario guiPadre) throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorAltaUsuario. Constructor");
		}
		this.controlUsuario = clienteConexion.getIControlUsuario();
		guiAltaUsuario = new GUIAltaModUsuario(guiPadre);
		guiAltaUsuario.setActionListeners(this);
		mgc = oldMGC;
		Utils.show(guiAltaUsuario);
	}

	public void actionPerformed(ActionEvent e) {
		if ((((Component)e.getSource()).getName().compareTo("Aceptar")) == 0) {
			String apellido = Utils.mayuscula(guiAltaUsuario.getApellido().getText());
			String nombre = Utils.mayuscula(guiAltaUsuario.getNombre().getText());
			String dni=guiAltaUsuario.getDni().getText();
			String tel = guiAltaUsuario.getTelefono().getText();
			String direccion = Utils.mayuscula(guiAltaUsuario.getDireccion().getText());
			String nLoc=guiAltaUsuario.getLocalidad().getText();
			String tipoUsr=guiAltaUsuario.getJCBTipoUsr().getSelectedItem().toString();
			String nombUsr=guiAltaUsuario.getNombUsr().getText();
			String beca=guiAltaUsuario.getBecaUsr().getText();
			try{ 
				if (dni.length()==0){
					Utils.advertenciaUsr(guiAltaUsuario,"Por favor ingrese el dni.");
				}else if(dni.length()!=8 &&  dni.length()!=7){
					Utils.advertenciaUsr(guiAltaUsuario,"Falta algun dígito, debe ser un numero de 7/8 cifras.");	
				}else if(this.controlUsuario.existeUsuarioDni(Integer.parseInt(dni))){
					Utils.advertenciaUsr(guiAltaUsuario,"El usuario que desea ingresar ya existe en el sistema con ese dni.");
				}else if (apellido.length()==0 || nombre.length()==0 || direccion.length()==0 || nLoc.length()==0 || nombUsr.length()==0 || beca.length()==0){
					Utils.advertenciaUsr(guiAltaUsuario,"Alguno de los campos obligatorios esta vacio.");
				}else if(this.controlUsuario.existeUsuarioNombUsr(nombUsr)){
					Utils.advertenciaUsr(guiAltaUsuario,"Ya existe en el sistema un usuario con ese nombre de usuario.");
				}else if(!Utils.esDouble(beca)){
					Utils.advertenciaUsr(guiAltaUsuario,"El importe de beca ingresado no es correcto.");
				}else{
					UsuarioDTO cliente = new UsuarioDTO();
					cliente.setApellido(apellido);
					cliente.setNombre(nombre);
					cliente.setDni(Integer.parseInt(dni));
					cliente.setTelefono(tel);
					cliente.setDireccion(direccion);
					cliente.setLocalidad(localidad);
					cliente.setTipoUsuario(tipoUsr);
					cliente.setNombUsuario(nombUsr);
					cliente.setImporteBeca(Double.parseDouble(beca));
					cliente.setEliminado(false);
					String contr=dni.substring(dni.length()-5, dni.length());
					Utils.setClave("100-3-248-97-234-56-100-241");
					String encriptado=Utils.encriptar(contr);
					cliente.setContrasenia(encriptado);
					this.controlUsuario.agregarUsuarioDTO(cliente);
					guiAltaUsuario.dispose();
					mgc.cargarDatos();
				}
			} catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorAltaUsuario. Agregar");
			}
		}else if ((((Component)e.getSource()).getName().compareTo("Buscar")) == 0) {
			buscarLocalidad();
		}else if ((((Component)e.getSource()).getName().compareTo("Cancelar")) == 0) {
			guiAltaUsuario.dispose();
		}
	}

	private void buscarLocalidad() {
		if (medGestionarLoc== null) {
			medGestionarLoc= new MediadorGestionarLocalidad(this,guiAltaUsuario);
		} else {
			if (!medGestionarLoc.getGUI().isVisible()) {
				medGestionarLoc.getGUI().setVisible(true);
			}
		}
		if (localidad != null){
			this.guiAltaUsuario.getLocalidad().setText(localidad.getNombre());
			this.cargarLocalidad(localidad);
		}
	}

	public void actualizarLocalidad() {
		guiAltaUsuario.setLocalidad(localidad.getNombre());
	}

	private void cargarLocalidad(LocalidadDTO loc) {
		this.localidad = loc;
	}

}