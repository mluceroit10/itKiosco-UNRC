package cliente.GestionarLocalidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.ClienteConection;
import cliente.GestionarKiosco.MediadorModificarKiosco;
import cliente.GestionarProveedor.MediadorAltaProveedor;
import cliente.GestionarProveedor.MediadorModificarProveedor;
import cliente.GestionarUsuario.MediadorAltaUsuario;
import cliente.GestionarUsuario.MediadorModificarUsuario;

import common.Utils;
import common.DTOs.LocalidadDTO;
import common.GestionarLocalidad.IControlLocalidad;

public class MediadorGestionarLocalidad implements ActionListener, KeyListener, ListSelectionListener {
	private GUIGestionarLocalidad guiLocalidad = null;
	protected IControlLocalidad controlLocalidad;
	private LocalidadDTO miLoc;
	private MediadorAltaUsuario medAltaUsuario = null;
	private MediadorModificarUsuario medModUsuario = null;
	private MediadorAltaProveedor medAltaProveedor = null;
	private MediadorModificarProveedor medModProveedor = null;
	private MediadorModificarKiosco medModKiosco;
	private boolean flag=false;

	public MediadorGestionarLocalidad(JFrame guiPadre) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. Constructor");
		}
		this.controlLocalidad = clienteConexion.getIControlLocalidad();
		this.guiLocalidad = new GUIGestionarLocalidad(guiPadre);
		this.guiLocalidad.setActionListeners(this);
		cargarDatos();
		this.guiLocalidad.setListSelectionListener(this);
		this.guiLocalidad.setKeyListener(this);
		this.flag=true;
		Utils.show(guiLocalidad);
	}

	public MediadorGestionarLocalidad(MediadorAltaUsuario medAltaUsuario,JDialog guiPadre) {
		this.medAltaUsuario = medAltaUsuario;
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. Constructor");
		}
		this.controlLocalidad = clienteConexion.getIControlLocalidad();
		this.guiLocalidad = new GUIGestionarLocalidad(guiPadre);
		this.guiLocalidad.setActionListeners(this);
		cargarDatos();
		this.guiLocalidad.setListSelectionListener(this);
		this.guiLocalidad.setKeyListener(this);
		Utils.show(guiLocalidad);
	}

	public MediadorGestionarLocalidad(MediadorModificarUsuario medModUsuario,JDialog guiPadre) {
		this.medModUsuario = medModUsuario;
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. Constructor");
		}
		this.controlLocalidad = clienteConexion.getIControlLocalidad();
		this.guiLocalidad = new GUIGestionarLocalidad(guiPadre);
		this.guiLocalidad.setActionListeners(this);

		cargarDatos();
		this.guiLocalidad.setListSelectionListener(this);
		this.guiLocalidad.setKeyListener(this);
		Utils.show(guiLocalidad);
	}

	public MediadorGestionarLocalidad(MediadorAltaProveedor medAltaProveedor,JDialog guiPadre) {
		this.medAltaProveedor = medAltaProveedor;
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. Constructor");
		}
		this.controlLocalidad = clienteConexion.getIControlLocalidad();
		this.guiLocalidad = new GUIGestionarLocalidad(guiPadre);
		this.guiLocalidad.setActionListeners(this);
		cargarDatos();
		this.guiLocalidad.setListSelectionListener(this);
		this.guiLocalidad.setKeyListener(this);
		Utils.show(guiLocalidad);
	}
	
	public MediadorGestionarLocalidad(MediadorModificarProveedor medModProveedor,JDialog guiPadre) {
		this.medModProveedor = medModProveedor;
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. Constructor");
		}
		this.controlLocalidad = clienteConexion.getIControlLocalidad();
		this.guiLocalidad = new GUIGestionarLocalidad(guiPadre);
		this.guiLocalidad.setActionListeners(this);

		cargarDatos();
		this.guiLocalidad.setListSelectionListener(this);
		this.guiLocalidad.setKeyListener(this);
		Utils.show(guiLocalidad);
	}
	
	public MediadorGestionarLocalidad(MediadorModificarKiosco medModKiosco,JDialog guiPadre) {
		this.medModKiosco = medModKiosco;
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. Constructor");
		}
		this.controlLocalidad = clienteConexion.getIControlLocalidad();
		this.guiLocalidad = new GUIGestionarLocalidad(guiPadre);
		this.guiLocalidad.setActionListeners(this);
		cargarDatos();
		this.guiLocalidad.setListSelectionListener(this);
		this.guiLocalidad.setKeyListener(this);
		Utils.show(guiLocalidad);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiLocalidad.getJBCargar()) {
			try {
				new MediadorAltaLocalidad(this,guiLocalidad);
			} catch (Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. ActionPerformed");
			}
		} else if (source == guiLocalidad.getJBMod()) {
			modificar();
		} else if (source == guiLocalidad.getJBCancelar()) {
			guiLocalidad.dispose();
		} else if (source == guiLocalidad.getJBBorrar()) {
			eliminar();
		} else if (source == guiLocalidad.getJBAceptar()) {
			if(flag){
				this.guiLocalidad.dispose();
			} else {
				if (cargarFilaSeleccionada()) {
					if (medModUsuario != null) {
						medModUsuario.localidad = miLoc;
						medModUsuario.actualizarLocalidad();
						this.guiLocalidad.dispose();
					} else if (medAltaUsuario != null) {
						medAltaUsuario.localidad = miLoc;
						medAltaUsuario.actualizarLocalidad();
						this.guiLocalidad.dispose();
					} else if (medAltaProveedor != null) {
						medAltaProveedor.localidad = miLoc;
						medAltaProveedor.actualizarLocalidad();
						this.guiLocalidad.dispose();
					} else if (medModProveedor != null) {
						medModProveedor.localidad = miLoc;
						medModProveedor.actualizarLocalidad();
						this.guiLocalidad.dispose();
					} else if (medModKiosco != null) {
						medModKiosco.localidad = miLoc;
						medModKiosco.actualizarLocalidad();
						this.guiLocalidad.dispose();
					}
				}
			}
		}
	}

	public boolean cargarFilaSeleccionada() {
		boolean result=false;
		try{
			if (guiLocalidad.jtDatos.getSelectedRow() == -1) {
				Utils.advertenciaUsr(guiLocalidad,"Debe seleccionar una Localidad.");
				result = false;
			}else{
				Long id= (Long) guiLocalidad.datos[guiLocalidad.jtDatos.getSelectedRow()][0];
				miLoc = this.controlLocalidad.buscarLocalidadDTOId(id);
				result = true;
			}
		} catch (Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. CargarFilaSeleccionada");
		}
		return result;
	}

	public void cargarDatos() {
		try {
			Vector localidades = this.controlLocalidad.obtenerLocalidades();
			guiLocalidad.datos = new Object[localidades.size()][guiLocalidad.titulos.length];
			int i = 0;
			for (int j = 0; j < localidades.size(); j++) {
				LocalidadDTO loc=(LocalidadDTO) localidades.elementAt(j);
				Object[] temp = {loc.getId(),loc.getNombre(),(String.valueOf(loc.getCodPostal())),loc.getProvincia().getNombre()};
				guiLocalidad.datos[i] = temp;
				i++;
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. CargarDatos");
		}
		guiLocalidad.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiLocalidad.actualizarTabla();
	}

	private void actualizarTabla() {
		try {
			Vector localidades = this.controlLocalidad.obtenerLocalidadesNombre(guiLocalidad.getJTFBuscador().getText());
			guiLocalidad.datos = new Object[localidades.size()][guiLocalidad.titulos.length];
			for (int j = 0; j < localidades.size(); j++) {
				LocalidadDTO loc=(LocalidadDTO) localidades.elementAt(j);
				Object[] temp = {loc.getId(),loc.getNombre(),(String.valueOf(loc.getCodPostal())),loc.getProvincia().getNombre()};
				guiLocalidad.datos[j] = temp;
			}
		} catch(Exception ex) {
			Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. ActualizarTabla");
		}
		guiLocalidad.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiLocalidad.actualizarTabla();
	}

	private void modificar() {
		try {
			if (this.controlLocalidad.obtenerLocalidades().isEmpty()){
				Utils.advertenciaUsr(guiLocalidad,"No hay localidades guardadas.");
			} else if (guiLocalidad.jtDatos.getSelectedRow() == -1){
				Utils.advertenciaUsr(guiLocalidad,"Para poder modificar un localidad debe ser previamente seleccionada.");
			} else {
				Long id=(Long)guiLocalidad.datos[guiLocalidad.jtDatos.getSelectedRow()][0];
				LocalidadDTO locDTO = null;
				locDTO = (LocalidadDTO)this.controlLocalidad.buscarLocalidadDTOId(id);
				new MediadorModificarLocalidad(this,locDTO,guiLocalidad);
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. Modificar");
		}
	}

	private void eliminar() {
		try{
			if ( this.controlLocalidad.obtenerLocalidades().isEmpty()){
				Utils.advertenciaUsr(guiLocalidad,"No hay localidades guardadas.");
			} else {
				if (guiLocalidad.jtDatos.getSelectedRow() == -1){
					Utils.advertenciaUsr(guiLocalidad,"Para poder eliminar una localidad debe seleccionarla previamente.");
				} else {
					Long id=(Long)guiLocalidad.datos[guiLocalidad.jtDatos.getSelectedRow()][0];
					LocalidadDTO locDTO;
					locDTO = (LocalidadDTO) this.controlLocalidad.buscarLocalidadDTOId(id);
					if (controlLocalidad.localidadAsociada(id)) {
						Utils.advertenciaUsr(guiLocalidad,"La localidad no puede ser borrada.");
					}else{
						int prueba = Utils.aceptarCancelarAccion(guiLocalidad,"Esta seguro que desea eliminar la localidad \n"+ locDTO.getNombre());
						if (prueba == 0)
							this.controlLocalidad.eliminarLocalidadDTO(id);
					}     
					cargarDatos();
				}
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarLocalidad. Eliminar");
		}
	}

	public GUIGestionarLocalidad getGUI() {
		return guiLocalidad;
	}

	public void keyReleased(KeyEvent e) {
		actualizarTabla();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void valueChanged(ListSelectionEvent arg0) { 
	}

}




