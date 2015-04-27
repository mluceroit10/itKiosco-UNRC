package cliente.GestionarSesion;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.ClienteConection;

import common.Utils;
import common.DTOs.SesionDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarSesion.IControlSesion;
import common.GestionarUsuario.IControlUsuario;

public class MediadorInicioCierreSesion implements ActionListener, ListSelectionListener {
	private GUIInicioCierreSesion guiUsuario = null;
	protected IControlUsuario controlUsuario;
	protected IControlSesion controlSesion;
	public SesionDTO sesionDTO=null;
	public Vector idsUsrs=new Vector();

	public MediadorInicioCierreSesion(JFrame guiPadre) {
		super();
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorInicioCierreSesion. Constructor");
		}
		this.controlUsuario = clienteConexion.getIControlUsuario();
		this.controlSesion = clienteConexion.getIControlSesion();  
		this.guiUsuario = new GUIInicioCierreSesion(guiPadre);
		try {
			Vector usrs = controlSesion.obtenerUsuariosNoConectados();
			for(int i=0;i<usrs.size();i++){
				UsuarioDTO p=(UsuarioDTO) usrs.elementAt(i);
				guiUsuario.usuarios.add(p.getNombUsuario());
				idsUsrs.add(p.getId());
			}
		} catch (Exception ex) {
			Utils.manejoErrores(ex,"Error en MediadorInicioCierreSesion. Constructor usr no conectados");
		}
		this.guiUsuario.setActionListeners(this);
		cargarDatos();
		this.guiUsuario.setListSelectionListener(this);
	}

	public void show(){
		guiUsuario.actualizarComboUsr();
		Utils.show(guiUsuario);
	}

	public void cargarDatos() {
		try {
			Vector clientes = new Vector();
			clientes = this.controlSesion.obtenerSesionesAbiertas();
			guiUsuario.datos = new Object[clientes.size()][guiUsuario.titulos.length];
			int i = 0;
			if(clientes!=null){
				for (int j = 0; j < clientes.size(); j++) {
					SesionDTO ses = (SesionDTO) clientes.elementAt(j);
					Object[] temp = {ses.getId(),ses.getUsuario().getApellido()+" "+ses.getUsuario().getNombre(),Utils.getStrUtilDate(ses.getInicio())+" "+Utils.getHoraUtilDate(ses.getInicio())};
					guiUsuario.datos[i] = temp;
					i++;
				}
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorInicioCierreSesion. CargarDatos");
		}
		guiUsuario.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiUsuario.actualizarTabla();
	}

	public void actionPerformed(ActionEvent e) {
		try{
			if ((((Component)e.getSource()).getName().compareTo("Iniciar")) == 0) {
				int nombUsr=guiUsuario.getJCUsuarios().getSelectedIndex();
				char[] contr=guiUsuario.getContrasenia().getPassword();
				if (contr.length==0){
					Utils.advertenciaUsr(guiUsuario,"Por favor ingrese la contraseña para iniciar sesión.");
				}else{
					UsuarioDTO usrDTO=controlUsuario.verificarUsrContr((Long)idsUsrs.elementAt(nombUsr),String.valueOf(contr));
					if(usrDTO!=null){
						guiUsuario.getContrasenia().setText("");
						SesionDTO sesDTO = new SesionDTO();
						sesDTO.setUsuario(usrDTO);
						sesDTO.setInicio(Utils.crearFecha(new java.util.Date()));
						sesDTO.setCierre(null);
						this.controlSesion.agregarSesionDTO(sesDTO);
						idsUsrs=new Vector();
						Vector usrs = controlSesion.obtenerUsuariosNoConectados();
						guiUsuario.getJCUsuarios().removeAllItems();
						guiUsuario.getJCUsuarios().setEnabled(true);
						for(int i=0;i<usrs.size();i++){
							UsuarioDTO p=(UsuarioDTO) usrs.elementAt(i);
							guiUsuario.getJCUsuarios().addItem(p.getNombUsuario());
							idsUsrs.add(p.getId());
						}
						guiUsuario.elimComboUsr();
						cargarDatos();
					}else{
						Utils.advertenciaUsr(guiUsuario,"La contraseña anterior es incorrecta.");
					}
				}
			} else if ((((Component)e.getSource()).getName().compareTo("Cerrar")) == 0){
				if(cargarFilaSeleccionada()){
					char[] contrCierre=guiUsuario.getContraseniaCierre().getPassword();
					UsuarioDTO idUsr=controlUsuario.verificarUsrContr(sesionDTO.getUsuario().getId(),String.valueOf(contrCierre));
					if(idUsr!=null){
						guiUsuario.getContraseniaCierre().setText("");
						SesionDTO sesDTO = sesionDTO;
						sesDTO.setCierre(Utils.crearFecha(new java.util.Date()));
						this.controlSesion.modificarSesion(sesionDTO.getId(),sesDTO);
						idsUsrs=new Vector();
						Vector usrs = controlSesion.obtenerUsuariosNoConectados();
						guiUsuario.getJCUsuarios().removeAllItems();
						guiUsuario.getJCUsuarios().setEnabled(true);
						for(int i=0;i<usrs.size();i++){
							UsuarioDTO p=(UsuarioDTO) usrs.elementAt(i);
							guiUsuario.getJCUsuarios().addItem(p.getNombUsuario());
							idsUsrs.add(p.getId());
						}
						guiUsuario.elimComboUsr();
						cargarDatos();
					}else{
						Utils.advertenciaUsr(guiUsuario,"La contraseña anterior es incorrecta.");
					}
				}
			} else{ 
				if((((Component)e.getSource()).getName().compareTo("Salir")) == 0){ 
					guiUsuario.dispose();
				} 
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex, "Error en mediadorInicioCierreSesion ActionPerformed.");
		}
	}

	public boolean cargarFilaSeleccionada() {
		boolean result=false;
		try{
			if (guiUsuario.tabla.getSelectedRow() == -1) {
				Utils.advertenciaUsr(guiUsuario,"Debe seleccionar un usuario.");
				result = false;
			}else{
				Long id = (Long)guiUsuario.datos[guiUsuario.tabla.getSelectedRow()][0];
				sesionDTO = this.controlSesion.buscarSesionDTOId(id);
				result = true;
			}
		} catch (Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorInicioCierreSesion. CargarFilaSeleccionada");
		}
		return result;
	}

	public void valueChanged(ListSelectionEvent e) {
	}

}