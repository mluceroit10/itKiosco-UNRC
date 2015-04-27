package cliente.GestionarSesion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.ClienteConection;

import common.Utils;
import common.DTOs.UsuarioDTO;
import common.GestionarSesion.IControlSesion;
import common.GestionarUsuario.IControlUsuario;
import common.constantes.TIPO_USUARIOS;

public class MediadorHorasTrabajadas implements ActionListener, ListSelectionListener{
	private GUIHorasTrabajadas guiHoras = null;
	public IControlSesion controlSesion;
	public IControlUsuario controlUsuario;
	public Date fecha;
	public Vector productos=new Vector();
	public String titulo="";
	public boolean todos=true;
	public Vector idsUsrs=new Vector();

	public MediadorHorasTrabajadas(JFrame guiPadre, String tipoSesion, Long idUsr) throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorHorasTrabajadas. Constructor");
		}
		controlUsuario = clienteConexion.getIControlUsuario();
		controlSesion = clienteConexion.getIControlSesion();
		this.guiHoras = new GUIHorasTrabajadas(guiPadre);
		this.guiHoras.setActionListeners(this);
		try {
			if(tipoSesion.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0){
				Vector prvs = controlUsuario.obtenerUsuarios();
				for(int i=0;i<prvs.size();i++){
					UsuarioDTO p=(UsuarioDTO) prvs.elementAt(i);
					guiHoras.usuarios.add(p.getApellido()+" "+p.getNombre());
					idsUsrs.add(p.getId());
				}
			}else{
				UsuarioDTO usr=controlUsuario.buscarUsuarioDTOId(idUsr);
				guiHoras.usuarios.add(usr.getApellido()+" "+usr.getNombre());
				idsUsrs.add(usr.getId());
			}
		} catch (Exception ex) {
			Utils.manejoErrores(ex,"Error en MediadorHorasTrabajadas. Constructor");
		}
	}

	public void show() throws Exception {
		guiHoras.actualizarTabla();
		Utils.show(guiHoras);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiHoras.getJBContinuar()) {
			String anio = guiHoras.getJTFAnio().getText();
			try {
				if (anio.length()==0){
					Utils.advertenciaUsr(guiHoras,"Por favor ingrese el año");
				}else if (anio.length()!=4){
					Utils.advertenciaUsr(guiHoras,"El año ingresado debe ser un numero de 4 digitos.");		
				}else{
					int nombUsr=guiHoras.getJCBUsuarios().getSelectedIndex();
					Long idUsr=(Long)idsUsrs.elementAt(nombUsr);
					int mes=guiHoras.getJCBMeses().getSelectedIndex()+1; 
					new MediadorMostrarHorasUsuario(guiHoras,idUsr,mes,Integer.parseInt(anio));
				}	  
			} catch (Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorHorasTrabajadas. ActionPerformed");
			}
		} else { 
			guiHoras.dispose();
		}
	}

	public void valueChanged(ListSelectionEvent arg0) {
	}

}


