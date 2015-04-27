package cliente.IngresarContrasenia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;

import cliente.ClienteConection;

import common.Utils;
import common.DTOs.UsuarioDTO;
import common.GestionarUsuario.IControlUsuario;

public class MediadorIngresarContrasenia implements ActionListener{
	private GUIIngresarContrasenia guiInicio;
	private IControlUsuario controlUsuario;
	private String tipoSesion="";
	private Long idUsuario=null;
	public Vector idsUsrs=new Vector();
	
	public MediadorIngresarContrasenia(JFrame guiPadre) throws Exception { 
		super();
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorIngresarContrasenia. Constructor");
		}
		this.controlUsuario = clienteConexion.getIControlUsuario();
		this.guiInicio = new GUIIngresarContrasenia(guiPadre);
		guiInicio.setActionListenersInicio(this);
		Vector usrs;
		try {
			usrs = controlUsuario.obtenerUsuarios();
			for(int i=0;i<usrs.size();i++){
				UsuarioDTO p=(UsuarioDTO) usrs.elementAt(i);
				guiInicio.usuarios.add(p.getNombUsuario());
				idsUsrs.add(p.getId());
			}
		} catch (Exception ex) {
			Utils.manejoErrores(ex,"Error en MediadorHorasTrabajadas. Constructor");
		}
	}

	public void show() throws Exception {
		guiInicio.actualizarTabla();
		Utils.show(guiInicio);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (this.guiInicio.getAceptar()==source){
			try {
				int nomb=guiInicio.getJCBUsuarios().getSelectedIndex();
				Long idUsr=(Long)idsUsrs.elementAt(nomb);
				char[] contr=guiInicio.getContrasenia().getPassword();
				if(contr.length==0){
					Utils.advertenciaUsr(guiInicio,"Por favor ingrese la contraseña.");	
				}else{
					UsuarioDTO uReg=controlUsuario.verificarUsrContr(idUsr,String.valueOf(contr));
					if(uReg!=null){
						tipoSesion=uReg.getTipoUsuario();
						idUsuario=idUsr;
						guiInicio.dispose();
					}else{
						Utils.advertenciaUsr(guiInicio,"El usuario o la contraseña es incorrecto");
					}
				}

			} catch (Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorIngresarContrasenia. ActionPerformed.");
			}
		}else if (this.guiInicio.getCancelar()==source){
			guiInicio.dispose();
		}
	}

	public String getTipoSesion(){
		return tipoSesion;
	}

	public Long getIdUsrRegistrado(){
		return idUsuario;
	}
		
}
