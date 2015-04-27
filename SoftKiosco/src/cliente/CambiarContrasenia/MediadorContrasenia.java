package cliente.CambiarContrasenia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JDialog;

import cliente.ClienteConection;

import common.Utils;
import common.DTOs.UsuarioDTO;
import common.GestionarUsuario.IControlUsuario;

public class MediadorContrasenia implements ActionListener{
	private GUIContrasenia guiContrasenia;
	private IControlUsuario controlUsuario;
	private UsuarioDTO usrReg=null;

	public MediadorContrasenia(JDialog guiPadre,Long idUsuario) throws Exception { 
		super();
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorCambiarContraseña. Constructor");
		}
		this.controlUsuario = clienteConexion.getIControlUsuario();
		try {
			usrReg = controlUsuario.buscarUsuarioDTOId(idUsuario);
			this.guiContrasenia = new GUIContrasenia(guiPadre,usrReg.getNombUsuario());
		} catch (RemoteException ex) {
			Utils.manejoErrores(ex,"Error en MediadorCambiarContraseña. Constructor");
		}
		guiContrasenia.setActionListenersInicio(this);
		Utils.show(guiContrasenia);
	}

	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (this.guiContrasenia.getAceptar()==source){
			try {
				char[] contrAnteior=guiContrasenia.getContraseniaAnterior().getPassword();
				char[] nuevacontr=guiContrasenia.getNuevaContrasenia().getPassword();
				char[] repeticionContr=guiContrasenia.getNuevaContraseniaRepeticion().getPassword();
				if (contrAnteior.length==0){
					Utils.advertenciaUsr(guiContrasenia,"Por favor ingrese la contraseña anterior.");
				}else if(nuevacontr.length==0){
					Utils.advertenciaUsr(guiContrasenia,"Por favor ingrese la nueva contraseña.");	
				}else if(String.valueOf(nuevacontr).compareTo(String.valueOf(repeticionContr))!=0){
					Utils.advertenciaUsr(guiContrasenia,"La nueva contraseña y su repeticion no coinciden.");	
				}else{
					UsuarioDTO usr=controlUsuario.verificarUsrContr(usrReg.getId(),String.valueOf(contrAnteior));
					if(usr!=null){
						UsuarioDTO cliente = usrReg;
						Utils.setClave("100-3-248-97-234-56-100-241");
						String encriptado=Utils.encriptar(String.valueOf(nuevacontr));
						cliente.setContrasenia(encriptado);
						this.controlUsuario.modificarUsuario(usr.getId(), cliente);
						guiContrasenia.dispose();
						Utils.advertenciaUsr(guiContrasenia,"La contraseña ha sido cambiada exitosamente.");
					}else{
						Utils.advertenciaUsr(guiContrasenia,"La contraseña anterior es incorrecta.");
					}
				}
			} catch (Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorCambiarContraseña. ActionPerformed");
			}
		}else if (this.guiContrasenia.getCancelar()==source){
			guiContrasenia.dispose();
		}
	}
	
}
