package cliente.GestionarProveedor;

import java.awt.Component;
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
import cliente.GestionarFacturaProveedor.MediadorFacturarProveedor;
import cliente.GestionarProducto.MediadorAltaProducto;
import cliente.GestionarProducto.MediadorModificarProducto;
import cliente.Principal.GUIReport;

import common.Utils;
import common.DTOs.LocalidadDTO;
import common.DTOs.ProveedorDTO;
import common.GestionarProveedor.IControlProveedor;

public class MediadorGestionarProveedor implements ActionListener, ListSelectionListener, KeyListener {
	private GUIGestionarProveedor guiProveedor = null;
	protected IControlProveedor controlProveedor;
	public ProveedorDTO socDto=null;
	private boolean flag=false;
	private ProveedorDTO miProveedorDto;
	private MediadorAltaProducto medAltaProducto;
	private MediadorModificarProducto medModProducto;
	private MediadorFacturarProveedor medFactProv;

	public MediadorGestionarProveedor(JFrame guiPadre) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProveedor. Constructor");
		}
		this.controlProveedor = clienteConexion.getIControlProveedor();
		this.guiProveedor = new GUIGestionarProveedor(guiPadre);
		this.guiProveedor.setActionListeners(this);
		cargarDatos();
		this.guiProveedor.setListSelectionListener(this);
		this.guiProveedor.setKeyListener(this);
		this.flag=true;
		Utils.show(guiProveedor);
	}

	public MediadorGestionarProveedor(MediadorAltaProducto medAP,JDialog guiPadre) {
		this.medAltaProducto = medAP;
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProveedor. Constructor");
		}
		this.controlProveedor = clienteConexion.getIControlProveedor();
		this.guiProveedor = new GUIGestionarProveedor(guiPadre);
		this.guiProveedor.setActionListeners(this);
		cargarDatos();
		this.guiProveedor.setListSelectionListener(this);
		this.guiProveedor.setKeyListener(this);
		Utils.show(guiProveedor);
	}

	public MediadorGestionarProveedor(MediadorModificarProducto medMP,JDialog guiPadre) {
		this.medModProducto = medMP;
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProveedor. Constructor");
		}
		this.controlProveedor = clienteConexion.getIControlProveedor();
		this.guiProveedor = new GUIGestionarProveedor(guiPadre);
		this.guiProveedor.setActionListeners(this);
		cargarDatos();
		this.guiProveedor.setListSelectionListener(this);
		this.guiProveedor.setKeyListener(this);
		Utils.show(guiProveedor);
	}

	public MediadorGestionarProveedor(MediadorFacturarProveedor medFP,JDialog guiPadre) {
		this.medFactProv = medFP;
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProveedor. Constructor");
		}
		this.controlProveedor = clienteConexion.getIControlProveedor();
		this.guiProveedor = new GUIGestionarProveedor(guiPadre);
		this.guiProveedor.setActionListeners(this);
		cargarDatos();
		this.guiProveedor.setListSelectionListener(this);
		this.guiProveedor.setKeyListener(this);
		Utils.show(guiProveedor);
	}

	public void cargarDatos() {
		try {
			Vector proveedors = this.controlProveedor.obtenerProveedores();
			guiProveedor.datos = new Object[proveedors.size()][guiProveedor.titulos.length];
			int i = 0;
			if(proveedors!=null){
				for (int j = 0; j < proveedors.size(); j++) {
					ProveedorDTO cte = (ProveedorDTO) proveedors.elementAt(j);
					Object[] temp = {cte.getId(),String.valueOf(cte.getCodigo()),cte.getNombre(),
							cte.getTelefono(),cte.getDireccion(),
							(((LocalidadDTO)cte.getLocalidad()).getNombre())};
					guiProveedor.datos[i] = temp;
					i++;
				}
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProveedor. CargarDatos");
		}
		guiProveedor.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiProveedor.actualizarTabla();
	}

	public void actionPerformed(ActionEvent e) {
		if ((((Component)e.getSource()).getName().compareTo("Alta")) == 0) {
			try{
				new MediadorAltaProveedor(this,guiProveedor);
			} catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorGestionarProveedor. ActionPerformed");
			}
		} else if ((((Component)e.getSource()).getName().compareTo("Baja")) == 0){
			eliminar();
		}else if ((((Component)e.getSource()).getName().compareTo("Modificar")) == 0){
			modificar();
		}else if ((((Component)e.getSource()).getName().compareTo("Imprimir")) == 0){
			try{
				Vector provs ;
				String titulo="";
				provs = controlProveedor.obtenerProveedores();
				titulo="Listado de Proveedores";
				new GUIReport(guiProveedor,7, provs,titulo);
			} catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorGestionarProveedor. ActionPerformed");
			}   
		}else if ((((Component)e.getSource()).getName().compareTo("Aceptar")) == 0){
			if(flag){
				this.guiProveedor.dispose();
			}
			else{
				if (cargarFilaSeleccionada()) {
					if (medModProducto != null) {
						medModProducto.proveedor = miProveedorDto;
						medModProducto.actualizarProveedor();
						this.guiProveedor.dispose();
					}else if (medAltaProducto != null) {
						medAltaProducto.proveedor = miProveedorDto;
						medAltaProducto.actualizarProveedor();
						this.guiProveedor.dispose();
					}else if (medFactProv != null) {
						medFactProv.proveedor = miProveedorDto;
						medFactProv.actualizarProveedor();
						this.guiProveedor.dispose();
					}
				}
			}
		}else { 
			if((((Component)e.getSource()).getName().compareTo("Cancelar")) == 0){ 
				guiProveedor.dispose();
			} 
		}
	}

	public boolean cargarFilaSeleccionada() {
		boolean result=false;
		try{
			if (guiProveedor.tabla.getSelectedRow() == -1) {
				Utils.advertenciaUsr(guiProveedor,"Debe seleccionar un proveedor.");
				result = false;
			}else{
				Long id=(Long)guiProveedor.datos[guiProveedor.tabla.getSelectedRow()][0];
				miProveedorDto = (ProveedorDTO)this.controlProveedor.buscarProveedorDTOId(id);
				result = true;
			}
		} catch (Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProveedor. CargarFilaSeleccionada");
		}
		return result;
	}

	private void eliminar() {
		try{
			if ( this.controlProveedor.obtenerProveedores().isEmpty()){
				Utils.advertenciaUsr(guiProveedor,"No hay proveedores guardados.");
			} else {
				if (guiProveedor.tabla.getSelectedRow() == -1){
					Utils.advertenciaUsr(guiProveedor,"Para poder eliminar un proveedor debe seleccionarlo previamente.");
				} else {
					Long id=(Long)guiProveedor.datos[guiProveedor.tabla.getSelectedRow()][0];
					String nombre = (String)guiProveedor.datos[guiProveedor.tabla.getSelectedRow()][2];
        			if (controlProveedor.proveedorAsociado(id)) {
                    	int prueba = Utils.aceptarCancelarAccion(guiProveedor,"Esta seguro que desea Eliminar el Proveedor y sus Productos\n"+ nombre);
                    	if (prueba == 0)
                    			this.controlProveedor.eliminarProveedorDTO(id);
                    }else{
                    	int prueba = Utils.aceptarCancelarAccion(guiProveedor,"Esta seguro que desea Eliminar el Proveedor \n"+ nombre);
                    	if (prueba == 0)
                    			this.controlProveedor.eliminarProveedorDTO(id);
                    }
					cargarDatos();
				}
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProveedor. Eliminar");
		}
	}

	public void modificar(){
		try {
			if (this.controlProveedor.obtenerProveedores().isEmpty()){
				Utils.advertenciaUsr(guiProveedor,"No hay proveedores guardados.");

			} else if (guiProveedor.tabla.getSelectedRow() == -1){
				Utils.advertenciaUsr(guiProveedor,"Para poder modificar un proveedor debe seleccionarlo previamente.");
			} else {
				Long id=(Long)guiProveedor.datos[guiProveedor.tabla.getSelectedRow()][0];
				ProveedorDTO provDTO = (ProveedorDTO)this.controlProveedor.buscarProveedorDTOId(id);
				new MediadorModificarProveedor(this, provDTO,guiProveedor);
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProveedor. Modificar");
		}
	}

	private void actualizarTablaConNombre() {
		try {
			Vector proveedors = this.controlProveedor.obtenerProveedoresCodyNom(guiProveedor.getJTFBuscadorCodigo().getText(),guiProveedor.getJTFBuscadorNombre().getText());
			guiProveedor.datos = new Object[proveedors.size()][guiProveedor.titulos.length];
			for (int j = 0; j < proveedors.size(); j++) {
				ProveedorDTO cte = (ProveedorDTO) proveedors.elementAt(j);
				Object[] temp = {cte.getId(),String.valueOf(cte.getCodigo()),cte.getNombre(),
						cte.getTelefono(),cte.getDireccion(),
						(((LocalidadDTO)cte.getLocalidad()).getNombre())};
				guiProveedor.datos[j] = temp;
			}
		} catch(Exception ex) {
			Utils.manejoErrores(ex,"Error en MediadorGestionarProveedor. ActualizarTablaConNombre");
		}
		guiProveedor.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiProveedor.actualizarTabla();
	}

	public GUIGestionarProveedor getGUI() {
		return guiProveedor;
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