package cliente.GestionarFacturaCliente;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.ClienteConection;
import cliente.Principal.GUIReport;

import common.Utils;
import common.DTOs.FacturaClienteDTO;
import common.DTOs.ItemFacturaDTO;
import common.DTOs.KioscoDTO;
import common.DTOs.ProductoDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarFacturaCliente.IControlFacturaCliente;
import common.GestionarKiosco.IControlKiosco;
import common.GestionarProducto.IControlProducto;
import common.GestionarUsuario.IControlUsuario;
import common.constantes.TIPO_FACTURA;

public class MediadorFacturarCliente implements ActionListener,ListSelectionListener,KeyListener {
	private GUIFacturarCliente guiFacturarCte = null;
	private IControlFacturaCliente controlFactCliente;
	private IControlKiosco controlKiosco;
	private IControlUsuario controlUsuarios;
	private IControlProducto controlProducto;
	public Vector productos = new Vector();
	public Vector cantProd = new Vector();
	public Vector kilosProd = new Vector();
	public Vector precioUnit = new Vector();
	public Vector precioTotalIt = new Vector();
	public Vector descuentos = new Vector();
	private Vector todosProductos;
	private double importeTotal=0; 
	private KioscoDTO kioscoDTO=null;
	private UsuarioDTO aCuentaDe=null;
	public Vector idsUsrs=new Vector();
	String tipo="";
	JFrame guiPpalPadre=null;

	public MediadorFacturarCliente(JFrame guiPadre) throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorFacturarCliente. Constructor");
		}
		controlFactCliente = clienteConexion.getIControlFacturaCliente();
		controlProducto = clienteConexion.getIControlProducto();
		controlKiosco = clienteConexion.getIControlKiosco();
		controlUsuarios = clienteConexion.getIControlUsuario();
		kioscoDTO=controlKiosco.obtenerKiosco();
		if(kioscoDTO==null){ 
			Utils.advertenciaUsr(guiFacturarCte,"Debe completar los datos del kiosco para facturar.");
		}else{	
			guiPpalPadre=guiPadre;
			guiFacturarCte = new GUIFacturarCliente(guiPadre);
			try {
				Vector usrs = controlUsuarios.obtenerUsuarios();
				for(int i=0;i<usrs.size();i++){
					UsuarioDTO p=(UsuarioDTO) usrs.elementAt(i);
					guiFacturarCte.usuarios.add(p.getNombUsuario());
					idsUsrs.add(p.getId());
				}
			} catch (RemoteException ex) {
				Utils.manejoErrores(ex,"Error en MediadorFacturarCliente. Constructor");
			}
			todosProductos = controlProducto.obtenerProductos();
			guiFacturarCte.nroFactura=controlFactCliente.obtenerNroFacturaCliente();
			guiFacturarCte.setActionListeners(this);
			guiFacturarCte.setKeyListeners2(this);
		}
	}

	public void show() {
		if(kioscoDTO!=null){
			guiFacturarCte.actualizarDatos();
			Utils.show(guiFacturarCte);
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.guiFacturarCte.getJCBCodigo()) {
			String cod_Prod = (String)this.guiFacturarCte.getJCBCodigo().getSelectedItem();
			guiFacturarCte.ocultarCombo();
			this.guiFacturarCte.getJTFCodigo().setText(cod_Prod);
			String cod=cod_Prod.substring(0,(cod_Prod.indexOf("_")-1));
			try{
				ProductoDTO pr= (ProductoDTO) this.controlProducto.buscarProductoDTOCodigo(new Long(cod));
				double importeProd=pr.getPrecioVenta();
				this.guiFacturarCte.getJTFImporte().setText(String.valueOf(importeProd));
				this.guiFacturarCte.getJTFCantidad().setText("");
				this.guiFacturarCte.getJBAgregarProd().setEnabled(true);
			} catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorFacturarCliente. BuscarProductoSeleccionado");
			}
		}else
			if ((((Component)e.getSource()).getName().compareTo("ConfirmarFact")) == 0) {
				try {
					ButtonModel condVenta=guiFacturarCte.opcCondVenta().getSelection();
					String tipoVenta =condVenta.getActionCommand();
					boolean aCuenta=false;
					if(tipoVenta.compareTo("aCuentaBecario")==0) aCuenta=true;
					Timestamp fecha= Utils.crearFecha(new java.util.Date());
					if (this.productos.size()==0){
						Utils.advertenciaUsr(guiFacturarCte,"Debe agregar algún producto para poder generar la factura.");
					} else {
						boolean confirmarVenta=true;
						FacturaClienteDTO fc= new FacturaClienteDTO();
						fc.setFechaHora(fecha);
						fc.setImporteTotal(Utils.redondear(importeTotal,2));
						fc.setNroFactura(guiFacturarCte.nroFactura);
						fc.setTipoFactura(TIPO_FACTURA.CLIENTE);
						fc.setACargoBecario(aCuenta); 
						if(aCuenta){
							int nombUsr=guiFacturarCte.getJCUsuarios().getSelectedIndex();
							aCuentaDe = controlUsuarios.buscarUsuarioDTOId((Long)idsUsrs.elementAt(nombUsr));
							fc.setBecario(aCuentaDe);
							JPasswordField jpf = new JPasswordField();
							JLabel titulo = new JLabel ("Ingrese la contraseña del becario");
							int rta=JOptionPane.showConfirmDialog(guiFacturarCte, new Object[]{titulo, jpf}, "Confirmación de compra", JOptionPane.OK_CANCEL_OPTION);
							if(rta==0){
								char p[] = jpf.getPassword();
								UsuarioDTO usrDTOVta=controlUsuarios.verificarUsrContr(aCuentaDe.getId(),String.valueOf(p));
								if(usrDTOVta==null){ 
									confirmarVenta=false;
									Utils.advertenciaUsr(guiFacturarCte,"La contraseña es incorrecta");
								}
							}else
								confirmarVenta=false;
						}else	 
							fc.setBecario(null);
						if(confirmarVenta){
							guiFacturarCte.nroFactura=controlFactCliente.obtenerNroFacturaCliente();
							fc.setNroFactura(guiFacturarCte.nroFactura);
							Vector itemsAgregar=new Vector();
							for(int k=0;k<productos.size();k++){
								ItemFacturaDTO itNew = new ItemFacturaDTO();
								itNew.setFactura(fc);
								int cantpr=Integer.parseInt((String)cantProd.elementAt(k));
								itNew.setCantidad(cantpr);
								ProductoDTO pr=(ProductoDTO)productos.elementAt(k);
								itNew.setProducto(pr);
								itNew.setPrUnit(Double.parseDouble((String)precioUnit.elementAt(k)));
								double prTotIt=Double.parseDouble((String)precioTotalIt.elementAt(k));
								itNew.setPrTotal(prTotIt);
								itemsAgregar.add(itNew);
							}	
							fc.setItems(itemsAgregar);
							double impRest=controlFactCliente.agregarFacturaClienteTotal(fc);
							if(aCuenta)
								Utils.advertenciaUsr(guiFacturarCte,"El saldo restante en su cuenta es de "+impRest);
							this.guiFacturarCte.dispose();
							if(guiFacturarCte.getJCheckImprimir().isSelected()){
								new GUIReport(guiFacturarCte, 11, productos,cantProd,precioUnit,precioTotalIt, Utils.nroFact(guiFacturarCte.nroFactura),fecha,
										kioscoDTO, aCuentaDe,importeTotal,aCuenta);
							}
							MediadorFacturarCliente msprod = new MediadorFacturarCliente(guiPpalPadre);
							msprod.show();
						}
					}
				} catch(Exception ex) {
					Utils.manejoErrores(ex,"Error en MediadorFacturarCliente. ConfirmarFactura");
				}
			}else if ((((Component)e.getSource()).getName().compareTo("AgregarProd")) == 0) {
				try{
					String cod_Prod = guiFacturarCte.getJTFCodigo().getText();
					String cant = guiFacturarCte.getJTFCantidad().getText();
					String cod=cod_Prod.substring(0,(cod_Prod.indexOf("_")-1));
					if(cod.length()>0){
						boolean existe =  this.controlProducto.existeProductoCodigo(new Long(cod));
						if(existe){
							ProductoDTO pr= (ProductoDTO) this.controlProducto.buscarProductoDTOCodigo(new Long(cod));
							if(cant.length()==0){
								Utils.advertenciaUsr(guiFacturarCte,"Debe ingresar una cantidad.");
							}else {	
								productos.add(pr);
								int c=Integer.parseInt(cant);
								cantProd.add(String.valueOf(c));
								precioUnit.add(Utils.ordenarDosDecimales(pr.getPrecioVenta()));
								double prTotal=0;
								prTotal = Utils.redondear(pr.getPrecioVenta()*c,2);
								precioTotalIt.add(Utils.ordenarDosDecimales(prTotal));
								guiFacturarCte.getJTFCantidad().setText("");
								guiFacturarCte.getJTFCodigo().setText("");
								guiFacturarCte.getJTFImporte().setText("");
								guiFacturarCte.getJTFBusqueda().setText("");
								cargarDatos();
								this.guiFacturarCte.getJBAgregarProd().setEnabled(false);
								this.guiFacturarCte.getJTFBusqueda().requestFocus();
							}
						}else
							Utils.advertenciaUsr(guiFacturarCte,"El producto no existe.");
					}else{
						Utils.advertenciaUsr(guiFacturarCte,"El código correspondiente al producto es erroneo.");
					}
				}catch(Exception ex){
					Utils.manejoErrores(ex,"Error en MediadorFacturarCliente. AgregarProducto");
				}
			}else if ((((Component)e.getSource()).getName().compareTo("EliminarP")) == 0) {
				if (guiFacturarCte.tabla.getSelectedRow() == -1){
					Utils.advertenciaUsr(guiFacturarCte,"Para poder eliminar un producto de la factura debe seleccionarlo previamente.");
				} else {
					int posProd = guiFacturarCte.tabla.getSelectedRow();
					int prueba=Utils.aceptarCancelarAccion(guiFacturarCte,"Esta seguro que desea eliminar el Item Número "+ (posProd+1)+" de la Factura.");
					if (prueba == 0){
						productos.removeElementAt(posProd);
						cantProd.removeElementAt(posProd);
						precioUnit.removeElementAt(posProd);
						precioTotalIt.removeElementAt(posProd);
						cargarDatos();
					}
				}
			}else if ((((Component)e.getSource()).getName().compareTo("SelaCuentaBecario")) == 0) {  //habilitar combo
				guiFacturarCte.getJCUsuarios().setEnabled(true);
			}else if ((((Component)e.getSource()).getName().compareTo("SelcteComun")) == 0) {  //deshabilitar combo
				guiFacturarCte.getJCUsuarios().setEnabled(false);	   
			}else if ((((Component)e.getSource()).getName().compareTo("Cancelar")) == 0) {
				guiFacturarCte.dispose();
			}
	}

	public void cargarDatos() {
		importeTotal=0;
		try {
			guiFacturarCte.datos = new Object[productos.size()][guiFacturarCte.titulos.length];
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
						prUnit,precioTotal};
				guiFacturarCte.datos[i] = temp;
				i++;
			}
			if(productos.size()>0){
				this.guiFacturarCte.getJBEliminarProd().setEnabled(true);
				this.guiFacturarCte.getJBConfirmaFact().setEnabled(true);
			}else{
				this.guiFacturarCte.getJBEliminarProd().setEnabled(false);	
				this.guiFacturarCte.getJBConfirmaFact().setEnabled(false);
			}	
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorFacturarCliente. CargarDatos");
		}
		guiFacturarCte.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiFacturarCte.actualizarTabla();
		guiFacturarCte.getJTFITotal().setText(Utils.ordenarDosDecimales(importeTotal));
	}

	public void keyReleased(KeyEvent e) {
		actualizarComboCodProd();
	}

	private void actualizarComboCodProd() {
		try {
			String texto = guiFacturarCte.getJTFBusqueda().getText();
			int j;
			guiFacturarCte.codProd.removeAllElements();
			for (j = 0; j< todosProductos.size(); j++) {
				ProductoDTO cte = (ProductoDTO) todosProductos.elementAt(j);
				if(Utils.comienza(String.valueOf(cte.getCodigo()), texto) || Utils.comienza(cte.getNombre(), texto)) {
					ProductoDTO p=(ProductoDTO) todosProductos.elementAt(j);
					guiFacturarCte.codProd.add(String.valueOf(p.getCodigo()+" _ "+p.getNombre()+" - "+p.getProveedor().getNombre()));
				}
			}
			if(guiFacturarCte.codProd.size()==1){
				String cod_Prod =(String) guiFacturarCte.codProd.elementAt(0);
				String cod=cod_Prod.substring(0,(cod_Prod.indexOf("_")-1));
				ProductoDTO pr= (ProductoDTO) this.controlProducto.buscarProductoDTOCodigo(new Long((cod)));
				double importeProd=pr.getPrecioVenta();
				this.guiFacturarCte.getJTFCodigo().setText(cod_Prod);
				this.guiFacturarCte.getJTFImporte().setText(String.valueOf(importeProd));
				this.guiFacturarCte.getJTFCantidad().setText("");
				this.guiFacturarCte.getJBAgregarProd().setEnabled(true);
				guiFacturarCte.ocultarCombo();
			}else{
				guiFacturarCte.mostrarCombo();
				this.guiFacturarCte.getJTFCodigo().setText("");
				this.guiFacturarCte.getJTFImporte().setText("");
				this.guiFacturarCte.getJTFCantidad().setText("");
				this.guiFacturarCte.getJBAgregarProd().setEnabled(false);
				guiFacturarCte.setActionListeners2(this);
			}
		} catch(Exception ex) {
			Utils.manejoErrores(ex,"Error en MediadorFacturarCliente. ActualizarTablaConCodigo");
		}
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void valueChanged(ListSelectionEvent arg0) {
	}
	
}
