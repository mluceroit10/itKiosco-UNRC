package cliente.GestionarFacturaProveedor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.ClienteConection;
import cliente.GestionarProveedor.MediadorGestionarProveedor;
import cliente.Principal.GUIReport;

import common.RootAndIp;
import common.Utils;
import common.DTOs.FacturaProveedorDTO;
import common.DTOs.ItemFacturaDTO;
import common.DTOs.KioscoDTO;
import common.DTOs.ProductoDTO;
import common.DTOs.ProveedorDTO;
import common.GestionarFacturaProveedor.IControlFacturaProveedor;
import common.GestionarKiosco.IControlKiosco;
import common.GestionarProducto.IControlProducto;

public class MediadorFacturarProveedor implements ActionListener,ListSelectionListener,KeyListener {
	private GUIFacturarProveedor guiFacturarProv = null;
	private IControlFacturaProveedor controlFactProveedor;
	private IControlKiosco controlKiosco;
	private IControlProducto controlProducto;
	private MediadorGestionarProveedor medGestionarProveedor;
	public ProveedorDTO proveedor;
	public Vector productos = new Vector();
	public Vector cantProd = new Vector();
	public Vector precioUnit = new Vector();
	public Vector precioTotalIt = new Vector();
	private double importeTotal=0; 
	private Vector todosProductos;
	private KioscoDTO kioscoDTO=null;
	JFrame guiPpalPadre=null;

	public MediadorFacturarProveedor(JFrame guiPadre) throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorFacturarProveedor. Constructor");
		}
		controlFactProveedor = clienteConexion.getIControlFacturaProveedor();
		controlProducto = clienteConexion.getIControlProducto();
		controlKiosco = clienteConexion.getIControlKiosco();
		kioscoDTO=controlKiosco.obtenerKiosco();
		if(kioscoDTO==null){ 
			Utils.advertenciaUsr(guiFacturarProv,"Debe completar los datos del kiosco para facturar.");
		}else{	
			guiPpalPadre=guiPadre;
			guiFacturarProv = new GUIFacturarProveedor(guiPadre);
			guiFacturarProv.nroFactura=controlFactProveedor.obtenerNroFacturaProveedor();
			guiFacturarProv.getJTFBusqueda().setEnabled(false);
			guiFacturarProv.setActionListeners(this);
			guiFacturarProv.setKeyListeners2(this);
		}
	}

	public void show() {
		if(kioscoDTO!=null){
			guiFacturarProv.actualizarDatos();
			Utils.show(guiFacturarProv);
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.guiFacturarProv.getJCBCodigo()) {
			String cod_Prod = (String)this.guiFacturarProv.getJCBCodigo().getSelectedItem();
			guiFacturarProv.ocultarCombo();
			this.guiFacturarProv.getJTFCodigo().setText(cod_Prod);
			String cod=cod_Prod.substring(0,(cod_Prod.indexOf("_")-1));
			try{
				ProductoDTO pr= (ProductoDTO) this.controlProducto.buscarProductoDTOCodigo(new Long(cod));
				this.guiFacturarProv.getJTFImporte().setText(String.valueOf(pr.getPrecioEntrada()));
				this.guiFacturarProv.getJTFCantidad().setText("");
				this.guiFacturarProv.getJBAgregarProd().setEnabled(true);
			} catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorFacturarProveedor. CargarProductoSeleccionado");
			}
		}else
			if ((((Component)e.getSource()).getName().compareTo("ConfirmarFact")) == 0) {
				try {
					java.util.Date fu=guiFacturarProv.getJDateChooserFecha().getDate();
					Timestamp fecha= Utils.crearFecha(fu);
					if (this.productos.size()==0){
						Utils.advertenciaUsr(guiFacturarProv,"Debe agregar algún producto para poder generar la factura.");
					}else if(guiFacturarProv.getJTFNombreC().getText().length()==0 ){
						Utils.advertenciaUsr(guiFacturarProv,"Debe seleccionar el proveedor.");
					}else {
						FacturaProveedorDTO fp= new FacturaProveedorDTO();
						fp.setProveedor(proveedor);
						fp.setFechaHora(fecha);
						fp.setImporteTotal(importeTotal);
						fp.setNroFactura(guiFacturarProv.nroFactura);
						fp.setTipoFactura("FacturaProveedor");
						Vector itemsAgregar=new Vector();
						for(int k=0;k<productos.size();k++){
							ItemFacturaDTO itNew = new ItemFacturaDTO();
							itNew.setFactura(fp);
							int cantpr=Integer.parseInt((String)cantProd.elementAt(k));
							itNew.setCantidad(cantpr);
							ProductoDTO pr=(ProductoDTO)productos.elementAt(k);
							itNew.setProducto(pr);
							itNew.setPrUnit(Double.parseDouble((String)precioUnit.elementAt(k)));
							double prTotIt=Double.parseDouble((String)precioTotalIt.elementAt(k));
							itNew.setPrTotal(prTotIt);
							itemsAgregar.add(itNew);
						}
						fp.setItems(itemsAgregar);
						controlFactProveedor.agregarFacturaProveedorTotal(fp);
						this.guiFacturarProv.dispose();
						int prueba =JOptionPane.showConfirmDialog(guiFacturarProv,"Desea imprimir un comprobante?","ATENCION!!!",0,3,new ImageIcon(RootAndIp.getPathImagenes()+"emitir.gif"));
						if( prueba==0 ){
							new GUIReport(guiFacturarProv, 10, productos,cantProd,precioUnit,precioTotalIt, Utils.nroFact(guiFacturarProv.nroFactura),fecha,
									kioscoDTO, proveedor,importeTotal);
						}
						MediadorFacturarProveedor msprod = new MediadorFacturarProveedor(guiPpalPadre);
						msprod.show();
					}
				} catch(Exception ex) {
					Utils.manejoErrores(ex,"Error en MediadorFacturarProveedor. ConfirmarFactura");
				}
			}else if ((((Component)e.getSource()).getName().compareTo("BuscarP")) == 0) {
				buscarProveedor();
			}else if ((((Component)e.getSource()).getName().compareTo("AgregarProd")) == 0) {
				try{
					String cod_Prod = guiFacturarProv.getJTFCodigo().getText();
					String cant = guiFacturarProv.getJTFCantidad().getText();
					String cod=cod_Prod.substring(0,(cod_Prod.indexOf("_")-1));
					if(cod.length()>0){
						boolean existe =  this.controlProducto.existeProductoCodigo(new Long(cod));
						if(existe){
							ProductoDTO pr= (ProductoDTO) this.controlProducto.buscarProductoDTOCodigo(new Long(cod));
							if(cant.length()==0){
								Utils.advertenciaUsr(guiFacturarProv,"Debe ingresar una cantidad.");
							}else {	
								productos.add(pr);
								int c=Integer.parseInt(cant);
								cantProd.add(String.valueOf(c));
								precioUnit.add(Utils.ordenarDosDecimales(pr.getPrecioEntrada()));
								double prTotal=0;
								prTotal = Utils.redondear(pr.getPrecioEntrada()*c,2);
								precioTotalIt.add(Utils.ordenarDosDecimales(prTotal));
								guiFacturarProv.getJTFCantidad().setText("");
								guiFacturarProv.getJTFCodigo().setText("");
								guiFacturarProv.getJTFImporte().setText("");
								guiFacturarProv.getJTFBusqueda().setText("");
								cargarDatos();
								this.guiFacturarProv.getJBAgregarProd().setEnabled(false);
								this.guiFacturarProv.getJTFBusqueda().requestFocus();
							}
						}else
							Utils.advertenciaUsr(guiFacturarProv,"El producto no existe.");
					}else{
						Utils.advertenciaUsr(guiFacturarProv,"El código correspondiente al producto es erroneo.");
					}
				}catch(Exception ex){
					Utils.manejoErrores(ex,"Error en MediadorFacturarProveedor. AgregarProducto");
				}
			}else if ((((Component)e.getSource()).getName().compareTo("EliminarP")) == 0) {
				if (guiFacturarProv.tabla.getSelectedRow() == -1){
					Utils.advertenciaUsr(guiFacturarProv,"Para poder eliminar un producto de la factura debe seleccionarlo previamente.");
				} else {
					int posProd = guiFacturarProv.tabla.getSelectedRow();
					int prueba=Utils.aceptarCancelarAccion(guiFacturarProv,"Esta seguro que desea eliminar el Item Número "+ (posProd+1)+" de la Factura.");
					if (prueba == 0){
						productos.removeElementAt(posProd);
						cantProd.removeElementAt(posProd);
						precioUnit.removeElementAt(posProd);
						precioTotalIt.removeElementAt(posProd);
						cargarDatos();
					}
				}
			}else if ((((Component)e.getSource()).getName().compareTo("Cancelar")) == 0) {
				guiFacturarProv.dispose();
			}
	}

	private void buscarProveedor() {
		if (medGestionarProveedor== null) {
			medGestionarProveedor= new MediadorGestionarProveedor(this,guiFacturarProv);
		} else {
			if (!medGestionarProveedor.getGUI().isVisible()) {
				medGestionarProveedor.getGUI().setVisible(true);
			}
		}
		if (proveedor != null){
			this.guiFacturarProv.getJTFNombreC().setText(proveedor.getNombre());
			this.guiFacturarProv.getJTFBusqueda().setEnabled(true);
			try {
				todosProductos = controlProducto.obtenerProductosProveedor(proveedor.getNombre());
			} catch (Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorFacturarProveedor. BuscarProveedor.");
			}
			this.cargarProveedor(proveedor);
			this.guiFacturarProv.getJTFBusqueda().requestFocus();
		}
	}

	private void cargarProveedor(ProveedorDTO c) {
		this.proveedor = c;
	}

	public void actualizarProveedor() {
		this.guiFacturarProv.getJTFNombreC().setText(proveedor.getNombre());
		this.guiFacturarProv.getJTFBusqueda().setEnabled(true);
		try {
			todosProductos = controlProducto.obtenerProductosProveedor(proveedor.getNombre());
		} catch (Exception ex) {
			Utils.manejoErrores(ex,"Error en MediadorFacturarProveedor. ActualizarProveedor.");
		}
		this.guiFacturarProv.getJTFBusqueda().requestFocus();
	}

	public void cargarDatos() {
		importeTotal=0;
		try {
			guiFacturarProv.datos = new Object[productos.size()][guiFacturarProv.titulos.length];
			int i = 0;
			for (int j = 0; j < productos.size(); j++) {
				ProductoDTO pr= (ProductoDTO) productos.elementAt(j);
				String prUnit= (String)precioUnit.elementAt(j); 
				String cantidad=(String)cantProd.elementAt(j);
				String precioTotal=(String)precioTotalIt.elementAt(j);
				double prTotal=Double.parseDouble(precioTotal);
				importeTotal = Utils.redondear(importeTotal+prTotal,2);
				Object[] temp = {(String.valueOf(pr.getCodigo())),cantidad,
						pr.getNombre()+" - "+pr.getProveedor().getNombre(),
						prUnit, precioTotal};
				guiFacturarProv.datos[i] = temp;
				i++;
			}
			if(productos.size()>0){
				this.guiFacturarProv.getJBEliminarProd().setEnabled(true);
				this.guiFacturarProv.getJBConfirmaFact().setEnabled(true);
			}else{
				this.guiFacturarProv.getJBEliminarProd().setEnabled(false);	
				this.guiFacturarProv.getJBConfirmaFact().setEnabled(false);
			}	
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorFacturarProveedor. CargarDatos");
		}
		guiFacturarProv.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiFacturarProv.actualizarTabla();
		guiFacturarProv.getJTFITotal().setText(Utils.ordenarDosDecimales(Utils.redondear(importeTotal,2)));
	}

	private void actualizarComboCodProd() {
		try {
			String texto = guiFacturarProv.getJTFBusqueda().getText();
			int j;
			guiFacturarProv.codProd.removeAllElements();
			for (j = 0; j< todosProductos.size(); j++) {
				ProductoDTO cte = (ProductoDTO) todosProductos.elementAt(j);
				if(Utils.comienza(String.valueOf(cte.getCodigo()), texto) || Utils.comienza(cte.getNombre(), texto)) {
					ProductoDTO p=(ProductoDTO) todosProductos.elementAt(j);
					guiFacturarProv.codProd.add(String.valueOf(p.getCodigo()+" _ "+p.getNombre()+" - "+p.getProveedor().getNombre()));
				}
			}
			if(guiFacturarProv.codProd.size()==1){
				String cod_Prod =(String) guiFacturarProv.codProd.elementAt(0);
				String cod=cod_Prod.substring(0,(cod_Prod.indexOf("_")-1));
				ProductoDTO pr= (ProductoDTO) this.controlProducto.buscarProductoDTOCodigo(new Long((cod)));
				double importeProd=pr.getPrecioEntrada();
				this.guiFacturarProv.getJTFCodigo().setText(cod_Prod);
				this.guiFacturarProv.getJTFImporte().setText(String.valueOf(importeProd));
				this.guiFacturarProv.getJTFCantidad().setText("");
				this.guiFacturarProv.getJBAgregarProd().setEnabled(true);
				guiFacturarProv.ocultarCombo();
			}else{
				guiFacturarProv.mostrarCombo();
				this.guiFacturarProv.getJTFCodigo().setText("");
				this.guiFacturarProv.getJTFImporte().setText("");
				this.guiFacturarProv.getJTFCantidad().setText("");
				this.guiFacturarProv.getJBAgregarProd().setEnabled(false);
				guiFacturarProv.setActionListeners2(this);
			}
		} catch(Exception ex) {
			Utils.manejoErrores(ex,"Error en MediadorFacturarProveedor. ActualizarTablaConCodigo");
		}
	}

	public void keyReleased(KeyEvent e) {
		actualizarComboCodProd();
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void valueChanged(ListSelectionEvent arg0) {
	}

}
