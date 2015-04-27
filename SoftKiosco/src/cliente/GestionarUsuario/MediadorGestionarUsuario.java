package cliente.GestionarUsuario;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.ClienteConection;
import cliente.CambiarContrasenia.MediadorContrasenia;
import cliente.CuentaCliente.MediadorCuentaBecario;
import cliente.Principal.GUIReport;

import common.Utils;
import common.DTOs.LocalidadDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarUsuario.IControlUsuario;
import common.constantes.TIPO_USUARIOS;

public class MediadorGestionarUsuario implements ActionListener, ListSelectionListener, KeyListener {
	private GUIGestionarUsuario guiUsuario = null;
	protected IControlUsuario controlUsuario;
	public UsuarioDTO socDto=null;
	private String tipoSesionInic="";
	private Long idUsuario=null;

	public MediadorGestionarUsuario(JFrame guiPadre, String tipoSesion, Long idUsr) {
		super();
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarUsuario. Constructor");
		}
		idUsuario=idUsr;
		tipoSesionInic=tipoSesion;
		this.controlUsuario = clienteConexion.getIControlUsuario();
		this.guiUsuario = new GUIGestionarUsuario(guiPadre, tipoSesionInic);
		this.guiUsuario.setActionListeners(this);
		cargarDatos();
		this.guiUsuario.setListSelectionListener(this);
		this.guiUsuario.setKeyListener(this);
		Utils.show(guiUsuario);
	}

	public void cargarDatos() {
		try {
			Vector clientes = new Vector();
			clientes = this.controlUsuario.obtenerUsuarios();
			guiUsuario.datos = new Object[clientes.size()][guiUsuario.titulos.length];
			int i = 0;
			if(clientes!=null){
				for (int j = 0; j < clientes.size(); j++) {
					UsuarioDTO cte = (UsuarioDTO) clientes.elementAt(j);
					Object[] temp = {cte.getId(),cte.getApellido()+" "+cte.getNombre(),String.valueOf(cte.getDni()),
							cte.getTelefono(),cte.getDireccion(),(((LocalidadDTO)cte.getLocalidad()).getNombre()),
							cte.getTipoUsuario(),cte.getNombUsuario(),Utils.ordenarDosDecimales(cte.getImporteBeca())};
					guiUsuario.datos[i] = temp;
					i++;
				}
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarCliente. CargarDatos");
		}
		guiUsuario.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiUsuario.actualizarTabla();
	}

	public void actionPerformed(ActionEvent e) {
		if ((((Component)e.getSource()).getName().compareTo("Alta")) == 0) {
			try{
				new MediadorAltaUsuario(this,guiUsuario);
			} catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorGestionarUsuario. ActionPerformed");
			}
		} else if ((((Component)e.getSource()).getName().compareTo("Baja")) == 0){
			eliminar();
		} else if ((((Component)e.getSource()).getName().compareTo("Modificar")) == 0){
			modificar();
		} else if ((((Component)e.getSource()).getName().compareTo("Imprimir")) == 0){
			try{
				Vector clientes ;
				String titulo="";
				clientes = controlUsuario.obtenerUsuarios();
				titulo="Listado de Usuarios";
				new GUIReport(guiUsuario,8,clientes,titulo);

			} catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorGestionarUsuario. Imprimir");
			}
		}else if ((((Component)e.getSource()).getName().compareTo("Salir")) == 0){
			this.guiUsuario.dispose();
		}else if ((((Component)e.getSource()).getName().compareTo("Cuenta")) == 0){
			obtenerCuentaUsuario();
		}else if ((((Component)e.getSource()).getName().compareTo("Contrasenia")) == 0){	
			try {
				new MediadorContrasenia(guiUsuario,idUsuario);
			} catch (Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorGestionarUsuario. CambiarContrasenia");
			}
		}
	}

	private void eliminar() {
		try{
			if ( this.controlUsuario.obtenerUsuarios().isEmpty()){
				Utils.advertenciaUsr(guiUsuario,"No hay usuarios guardados.");
			} else {
				if (guiUsuario.tabla.getSelectedRow() == -1){
					Utils.advertenciaUsr(guiUsuario,"Para poder eliminar un usuario debe seleccionarlo previamente");
				} else {
					Long id = (Long) guiUsuario.datos[guiUsuario.tabla.getSelectedRow()][0];
					String nombre = (String)guiUsuario.datos[guiUsuario.tabla.getSelectedRow()][1];
					int prueba = Utils.aceptarCancelarAccion(guiUsuario,"Esta seguro que desea Eliminar el Usuario \n"+ nombre+"\nRecuerde que al eliminarlo se borrará el registro de sus sesiones y de las becas asignadas.");
					if (prueba == 0) 
						this.controlUsuario.eliminarUsuarioDTO(id);
					cargarDatos();
				}
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarUsuario. Eliminar");
		}
	}

	public void modificar(){
		try {
			if (this.controlUsuario.obtenerUsuarios().isEmpty()){
				Utils.advertenciaUsr(guiUsuario,"No hay usuarios guardados en el sistema.");
			} else if (guiUsuario.tabla.getSelectedRow() == -1){
				Utils.advertenciaUsr(guiUsuario,"Para poder modificar un usuario debe seleccionarlo previamente.");
			} else {
				Long id = (Long)guiUsuario.datos[guiUsuario.tabla.getSelectedRow()][0];
				UsuarioDTO usr =(UsuarioDTO)this.controlUsuario.buscarUsuarioDTOId(id);
				new MediadorModificarUsuario(this, usr,guiUsuario);
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarUsuario. Modificar");
		}
	}

	public void obtenerCuentaUsuario(){
		try {
			if (this.controlUsuario.obtenerUsuarios().isEmpty()){
				Utils.advertenciaUsr(guiUsuario,"No hay usuarios guardados en el sistema.");
			} else {
				Long id =null;
				if(tipoSesionInic.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0){
					if (guiUsuario.tabla.getSelectedRow() == -1)
						Utils.advertenciaUsr(guiUsuario,"Para poder verificar la cuenta de un usuario debe seleccionarlo previamente.");
					else 
						id = (Long)guiUsuario.datos[guiUsuario.tabla.getSelectedRow()][0];
				}else
					id=this.idUsuario;
				if(id!=null){
					UsuarioDTO usr =(UsuarioDTO)this.controlUsuario.buscarUsuarioDTOId(id);
					new MediadorCuentaBecario(this,usr,guiUsuario);
				}
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarUsuario. E cuenta");
		}
	}

	private void actualizarTablaConNombre() {
		try {
			Vector clientes = new Vector();
			clientes = this.controlUsuario.obtenerUsuariosNomyAp(guiUsuario.getJTFBuscador().getText());
			guiUsuario.datos = new Object[clientes.size()][guiUsuario.titulos.length];
			for (int j = 0; j < clientes.size(); j++) {
				UsuarioDTO cte = (UsuarioDTO) clientes.elementAt(j);
				Object[] temp = {cte.getId(),cte.getApellido()+" "+cte.getNombre(),String.valueOf(cte.getDni()),
						cte.getTelefono(),cte.getDireccion(),(((LocalidadDTO)cte.getLocalidad()).getNombre()),
						cte.getTipoUsuario(),cte.getNombUsuario(),Utils.ordenarDosDecimales(cte.getImporteBeca())};
				guiUsuario.datos[j] = temp;
			}
		} catch(Exception ex) {
			Utils.manejoErrores(ex,"Error en MediadorGestionarUsuario. ActualizarTablaConNombre");
		}
		guiUsuario.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiUsuario.actualizarTabla();
	}

	public GUIGestionarUsuario getGUI() {
		return guiUsuario;
	}
	
	public void keyReleased(KeyEvent e) {
		actualizarTablaConNombre();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void valueChanged(ListSelectionEvent e) {
	}
	
}