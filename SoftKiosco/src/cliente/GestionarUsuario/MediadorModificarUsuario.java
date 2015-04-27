package cliente.GestionarUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cliente.ClienteConection;
import cliente.GestionarLocalidad.MediadorGestionarLocalidad;

import common.Utils;
import common.DTOs.LocalidadDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarUsuario.IControlUsuario;

public class MediadorModificarUsuario implements ActionListener {
	public LocalidadDTO localidad;
	private GUIAltaModUsuario guiModUsuario = null;
	public IControlUsuario controlUsuario;
	private MediadorGestionarUsuario mgUsuario;
	private MediadorGestionarLocalidad mgLoc = null;
	public UsuarioDTO cte;

	public MediadorModificarUsuario(MediadorGestionarUsuario oldMGUsuario, UsuarioDTO cl,GUIGestionarUsuario guiPadre) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorModificarUsuario. Constructor");
		}
		this.controlUsuario = clienteConexion.getIControlUsuario();
		guiModUsuario = new GUIAltaModUsuario(cl,guiPadre);
		guiModUsuario.setActionListeners(this);
		mgUsuario = oldMGUsuario;
		cte = cl;
		localidad = cl.getLocalidad();
		Utils.show(guiModUsuario);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiModUsuario.getAceptar()) {
			String apellido = Utils.mayuscula(guiModUsuario.getApellido().getText());
			String nombre = Utils.mayuscula(guiModUsuario.getNombre().getText());
			String dni=guiModUsuario.getDni().getText();
			String tel = guiModUsuario.getTelefono().getText();
			String direccion = Utils.mayuscula(guiModUsuario.getDireccion().getText());
			String nLoc=guiModUsuario.getLocalidad().getText();
			String tipoUsr=guiModUsuario.getJCBTipoUsr().getSelectedItem().toString();
			String nombUsr=guiModUsuario.getNombUsr().getText();
			String beca=guiModUsuario.getBecaUsr().getText();
			try{
				if (dni.length()==0){
					Utils.advertenciaUsr(guiModUsuario,"Por favor ingrese el dni.");
				}else if(dni.length()!=8 &&  dni.length()!=7){
					Utils.advertenciaUsr(guiModUsuario,"Falta algun dígito, debe ser un numero de 7/8 cifras.");	
				}else if (apellido.length()==0 || nombre.length()==0 || direccion.length()==0 || nLoc.length()==0 || nombUsr.length()==0 || beca.length()==0){
					Utils.advertenciaUsr(guiModUsuario,"Alguno de los campos obligatorios esta vacio.");
				}else if((cte.getNombUsuario().compareTo(nombUsr)!=0) && this.controlUsuario.existeUsuarioNombUsr(nombUsr)){
					Utils.advertenciaUsr(guiModUsuario,"Ya existe en el sistema un usuario con ese nombre de usuario.");
				}else if(!Utils.esDouble(beca)){
					Utils.advertenciaUsr(guiModUsuario,"El importe de beca ingresado no es correcto.");
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
					cliente.setContrasenia(cte.getContrasenia());
					cliente.setEliminado(false);
					if (this.controlUsuario.puedoEditar(cte,cliente)){
						this.controlUsuario.modificarUsuario(cte.getId(), cliente);
						guiModUsuario.dispose();
						mgUsuario.cargarDatos();
					} else {
						Utils.advertenciaUsr(guiModUsuario,"El usuario que desea ingresar ya existe en el sistema.");
					}
				}
			} catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorModificarUsuario. ActionPerformed");
			}
		} else if (source == guiModUsuario.getJButtonLocalidad()) {
			buscarLocalidad();
		} else if (source == guiModUsuario.getCancelar()) {    
			guiModUsuario.dispose();
		}
	}

	private void buscarLocalidad() {
		if (mgLoc == null) {
			mgLoc = new MediadorGestionarLocalidad(this,guiModUsuario);
		} else {
			if (!mgLoc.getGUI().isVisible()) {
				mgLoc.getGUI().setVisible(true);
			}
		}
		if (localidad != null){
			this.guiModUsuario.getLocalidad().setText(localidad.getNombre());
			this.cargarLocalidad(localidad);           
		}
	}

	private void cargarLocalidad(LocalidadDTO loc) {
		this.localidad = loc;
	}

	public void actualizarLocalidad() {
		guiModUsuario.setLocalidad(localidad.getNombre());
	}

}
