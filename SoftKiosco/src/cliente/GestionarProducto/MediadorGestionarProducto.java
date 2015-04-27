package cliente.GestionarProducto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.ClienteConection;
import cliente.Principal.GUIReport;

import common.Utils;
import common.DTOs.ProductoDTO;
import common.DTOs.ProveedorDTO;
import common.GestionarProducto.IControlProducto;
import common.GestionarProveedor.IControlProveedor;

public class MediadorGestionarProducto implements ActionListener, KeyListener, ListSelectionListener {
	private GUIGestionarProducto guiProducto = null;
	protected IControlProducto controlProducto;
	protected IControlProveedor controlProveedor;
	public Vector productos=new Vector();
	public String titulo="";

	public MediadorGestionarProducto(JFrame guiPadre) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProducto. Constructor");
		}
		this.controlProducto = clienteConexion.getIControlProducto();
		this.controlProveedor = clienteConexion.getIControlProveedor();
		this.guiProducto = new GUIGestionarProducto(guiPadre);
		this.guiProducto.setActionListeners(this);
		cargarDatos();
		this.guiProducto.setListSelectionListener(this);
		this.guiProducto.setKeyListener(this);
		Utils.show(guiProducto);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiProducto.getJBCargar()) {
			try {
				new MediadorAltaProducto(this,guiProducto);
			} catch (Exception ex) { 
				Utils.manejoErrores(ex,"Error en MediadorGestionarProducto. ActionPerformed");
			}
		} else if (source == guiProducto.getJBMod()) {
			modificar();
		} else if (source == guiProducto.getJBBorrar()) {
			eliminar();
		} else if (source == guiProducto.getJBImprimir()) {
			try{
				if (this.controlProducto.obtenerProductos().isEmpty()){
					Utils.advertenciaUsr(guiProducto,"No hay productos guardados.");
				} else{
					Object[] valoresPosibles = {"Todos los Proveedores", "Por Proveedor"};
					String prov = (String)JOptionPane.showInputDialog(guiProducto,
							"Seleccione Proveedor", "Criterios de Impresión",
							JOptionPane. QUESTION_MESSAGE, null,
							valoresPosibles, valoresPosibles[0]);
					if(prov!=null){
						if(prov.compareTo("Todos los Proveedores")==0){
							productos = controlProducto.obtenerProductos();
							Object[] valoresImpr = {"Precio Entrada y Salida", "Precio Entrada", "Precio Salida"};
							String impr = (String)JOptionPane.showInputDialog(guiProducto,
									"Precios a imprimir", "Criterios de Impresión",
									JOptionPane. QUESTION_MESSAGE, null,valoresImpr, valoresImpr[0]);
							titulo="Todos los productos";
							if(impr!=null){
								if(impr.compareTo("Precio Entrada y Salida")==0){
									new GUIReport(guiProducto,3,productos,titulo,"");
								}
								if(impr.compareTo("Precio Entrada")==0){
									new GUIReport(guiProducto,4,productos,titulo,"ENTRADA");
								}
								if(impr.compareTo("Precio Salida")==0){
									new GUIReport(guiProducto,4,productos,titulo,"VENTA");
								}
							}
						}
						if(prov.compareTo("Por Proveedor")==0){
							Vector provs = controlProveedor.obtenerProveedores();
							Object[] provsPosibles= new Object[provs.size()];
							for (int k=0;k<provs.size();k++){
								provsPosibles[k]=((ProveedorDTO)provs.elementAt(k)).getNombre();
							}
							String unprov = (String)JOptionPane.showInputDialog(null,
									"Seleccione el Proveedor", "Criterios de Impresión",
									JOptionPane. QUESTION_MESSAGE, null,
									provsPosibles, provsPosibles[0]);
							productos = controlProducto.obtenerProductosProveedor(unprov);
							titulo="Todos los productos del proveedor "+unprov;
							Object[] valoresImpr = {"Precio Entrada y Salida", "Precio Entrada", "Precio Salida"};
							String impr = (String)JOptionPane.showInputDialog(null,
									"Precios a imprimir", "Criterios de Impresión",
									JOptionPane. QUESTION_MESSAGE, null,valoresImpr, valoresImpr[0]);
							if(impr!=null){
								if(impr.compareTo("Precio Entrada y Salida")==0){
									new GUIReport(guiProducto,5,productos,titulo,"");
								}
								if(impr.compareTo("Precio Entrada")==0){
									new GUIReport(guiProducto,6,productos,titulo,"ENTRADA");
								}
								if(impr.compareTo("Precio Salida")==0){
									new GUIReport(guiProducto,6,productos,titulo,"VENTA");
								}
							}
						}	
					}
				}
			} catch (Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorGestionarProducto. Imprimir.");
			}
		} else if (source == guiProducto.getJBSalir()) {
			guiProducto.dispose();
		}
	}

	public void cargarDatos() {
		try {
			Vector productos = this.controlProducto.obtenerProductos();
			guiProducto.datos = new Object[productos.size()][guiProducto.titulos.length];
			int i = 0;
			for (int j = 0; j < productos.size(); j++) {
				ProductoDTO p=(ProductoDTO)productos.elementAt(j);
				Object[] temp = {p.getId(),String.valueOf(p.getCodigo()),p.getNombre(),
						String.valueOf(p.getStockActual()),String.valueOf(p.getStockMinimo()),
						Utils.ordenarDosDecimales(p.getPrecioEntrada()),p.getMargenGanancia()+" %",Utils.ordenarDosDecimales(p.getPrecioVenta()),
						p.getProveedor().getNombre()};
				guiProducto.datos[i] = temp;
				i++;
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProducto. CargarDatos");
		}
		guiProducto.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiProducto.actualizarTabla();
	}

	private void actualizarTabla() {
		try {
			Vector productos = this.controlProducto.obtenerProductosCodyNom(guiProducto.getJTFBuscadorCodigo().getText(),guiProducto.getJTFBuscadorNombre().getText());
			guiProducto.datos = new Object[productos.size()][guiProducto.titulos.length];
			for (int j = 0; j < productos.size(); j++) {
				ProductoDTO p=(ProductoDTO)productos.elementAt(j);
				Object[] temp = {p.getId(),String.valueOf(p.getCodigo()),p.getNombre(),
						String.valueOf(p.getStockActual()),String.valueOf(p.getStockMinimo()),
						String.valueOf(p.getPrecioEntrada()),p.getMargenGanancia()+" %",String.valueOf(p.getPrecioVenta()),
						p.getProveedor().getNombre()};
				guiProducto.datos[j] = temp;
			}
		} catch(Exception ex) {
			Utils.manejoErrores(ex,"Error en MediadorGestionarProducto. ActualizarTabla");
		}
		guiProducto.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiProducto.actualizarTabla();
	}

	private void modificar() {
		try {
			if (this.controlProducto.obtenerProductos().isEmpty()){
				Utils.advertenciaUsr(guiProducto,"No hay productos guardados.");
			} else if (guiProducto.jtDatos.getSelectedRow() == -1){
				Utils.advertenciaUsr(guiProducto,"Para poder modificar un producto debe ser previamente seleccionado.");
			} else {
				Long id=(Long)guiProducto.datos[guiProducto.jtDatos.getSelectedRow()][0];
				ProductoDTO prod = (ProductoDTO)this.controlProducto.buscarProductoDTOId(id);
				new MediadorModificarProducto(this,prod,guiProducto);
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProducto. Modificar");
		}
	}

	private void eliminar() {
		try{
			if ( this.controlProducto.obtenerProductos().isEmpty()){
				Utils.advertenciaUsr(guiProducto,"No hay Productos guardados.");
			} else {
				if (guiProducto.jtDatos.getSelectedRow() == -1){
					Utils.advertenciaUsr(guiProducto,"Para poder eliminar un producto debe seleccionarlo previamente.");
				} else {
					Long id=(Long)guiProducto.datos[guiProducto.jtDatos.getSelectedRow()][0];
					String nombre = (String)guiProducto.datos[guiProducto.jtDatos.getSelectedRow()][2];
					int prueba = Utils.aceptarCancelarAccion(guiProducto,"Esta seguro que desea Eliminar el Producto \n"+ nombre);
					if (prueba == 0)
						this.controlProducto.eliminarProductoDTO(id);
					cargarDatos();
				}
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarProducto. Eliminar");
		}
	}

	public GUIGestionarProducto getGUI() {
		return guiProducto;
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




