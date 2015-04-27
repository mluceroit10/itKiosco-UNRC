package cliente.GestionarFacturaProveedor;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import cliente.LimitadorNroMax;
import cliente.ModeloTabla;

import com.toedter.calendar.JDateChooser;
import common.Utils;

public class GUIFacturarProveedor extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;				private JPanel jpDatosProd = null;
	private JPanel jpDatosFactura = null;		private JPanel jpDatosItems=null;
	private JButton jbAgregarProd = null;		private JButton jbBuscarC=null;
	private JButton jbEliminarProd=null;		private JButton jbConfirmarFact=null;
	private JLabel jlSelecProd = null;			private JLabel jlFechaFactura=null;
	private JLabel jlCodigo = null;	     		private JLabel jlImporte = null;
	private JLabel jlBusqueda = null;			private JLabel jlNroFactura = null;
	private JLabel jlNombreC=null;				private JLabel jlITotal = null;
	private JLabel jlCantidad = null;			
	private JTextField jtfBusqueda = null;		
	private JTextField jtfCodigo = null;     	private JTextField jtfImporte = null;
	private JTextField jtfCantidad = null;   	private JTextField jtfITotal = null;
	private JTextField jtfNombreC=null;			
	private JComboBox jcbCodigo = null;
	private JDateChooser jDataCFecha = null;
	private JScrollPane jspDatosInsc=null;
	public final String[] titulos ={"Código","Cant.","Producto","Precio Unit.","Precio Total"};
	public Object[][] datos;
	public JTable tabla;					private ModeloTabla modTabla = null;
	public Vector codProd= new Vector();
	public Long nroFactura;
	private InputMap map = new InputMap();
	
	public GUIFacturarProveedor(JFrame guiPadre) {
		super(guiPadre);
		this.setSize(new java.awt.Dimension(740,540));
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Facturación Proveedor");
		this.setContentPane(getJPPpal());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	public JPanel getJPPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.add(getJPDatosProducto(), null);
			jpPpal.add(getJPDatosFactura(), null);
			Object[] temp  = {" "," "," "," "," "};
			datos = new Object[1][titulos.length];
			datos[0]=temp;
			jpPpal.add(getJPDatosItems(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPDatosFactura() {
		if (jpDatosFactura == null) {
			jpDatosFactura = new JPanel();
			jpDatosFactura.setLayout(null);
			jpDatosFactura.setBorder(Utils.crearTituloTabla("Datos Factura"));
			jpDatosFactura.setBounds(new Rectangle(8,15,716,95));
			jpDatosFactura.setBackground(Utils.colorPanel);
			jlNombreC = new JLabel();
			jlNombreC.setBounds(new Rectangle(180,30,80,20));
			jlNombreC.setHorizontalAlignment(SwingConstants.RIGHT);
			jlNombreC.setText("Nombre:");
			jlFechaFactura = new JLabel();
			jlFechaFactura.setBounds(new Rectangle(430,62,60,20));
			jlFechaFactura.setHorizontalAlignment(SwingConstants.RIGHT);
			jlFechaFactura.setText("Fecha:");
			jlNroFactura = new JLabel();
			jlNroFactura.setBounds(new Rectangle(212,62,190,20));
			jpDatosFactura.add(jlNombreC, null);
			jpDatosFactura.add(jlFechaFactura, null);
			jpDatosFactura.add(getJBBuscarC(), null);
			jpDatosFactura.add(getJTFNombreC(), null);
			jpDatosFactura.add(getJDateChooserFecha(),null);
		}
		return jpDatosFactura;
	}

	public JButton getJBBuscarC() {
		if (jbBuscarC == null) {
			jbBuscarC = new JButton();
			jbBuscarC.setText("Seleccione Proveedor");
			jbBuscarC.setName("BuscarP");
			jbBuscarC.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbBuscarC.setBounds(new Rectangle(30,30,150,22));
			jbBuscarC.setInputMap(0, map);
		}
		return jbBuscarC;
	}
	
	public JTextField getJTFNombreC() {
		if (jtfNombreC == null) {
			jtfNombreC = new JTextField();
			jtfNombreC.setBounds(new Rectangle(270,30,150,22));
			jtfNombreC.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfNombreC.setEnabled(false);
		}
		return jtfNombreC;
	}
	
	public JDateChooser getJDateChooserFecha() {
		if (jDataCFecha == null) {
			jDataCFecha = new JDateChooser("dd - MMMMM - yyyy HH:mm",false);
			jDataCFecha.setBounds(new Rectangle(495,62,190,22));
		}
		return jDataCFecha;
	}
	
	private JPanel getJPDatosItems() {
		if (jpDatosItems == null) {
			jpDatosItems = new JPanel();
			jpDatosItems.setLayout(null);
			jpDatosItems.setBounds(new Rectangle(8,115,716,245));
			jpDatosItems.setBorder(Utils.crearTituloTabla("Listado de Productos Comprados"));
			jlITotal = new JLabel();
			jlITotal.setBounds(new Rectangle(490,175,100,30));
			jlITotal.setHorizontalAlignment(SwingConstants.RIGHT);
			jlITotal.setText("Importe Total:");
			jpDatosItems.add(jlITotal, null);
			jpDatosItems.add(getJTFITotal(), null);
			jpDatosItems.add(getJSPDatosI(), null);
			jpDatosItems.setBackground(Utils.colorPanel);
			jpDatosItems.add(getJBEliminarProd(), null);
			jpDatosItems.add(getJBConfirmaFact(), null);
		}
		return jpDatosItems;
	}
	
	public JTextField getJTFITotal() {
		if (jtfITotal == null) {
			jtfITotal = new JTextField();
			jtfITotal.setBounds(new Rectangle(600,175,100,30));
			jtfITotal.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfITotal.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 18));
			jtfITotal.setHorizontalAlignment(SwingConstants.RIGHT);
			jtfITotal.setEnabled(false);
		}
		return jtfITotal;
	}

	private JScrollPane getJSPDatosI() {
		if (jspDatosInsc == null) {
			jspDatosInsc = new JScrollPane();
			jspDatosInsc.setBounds(new Rectangle(10,20,690,150));
			jspDatosInsc.setViewportView(getJTDatosI());
		}
		return jspDatosInsc;
	}

	public JTable getJTDatosI() {
		if (tabla == null) {
			modTabla = new ModeloTabla(titulos, datos);
			tabla = new JTable(modTabla);
			TableColumn columna0 = tabla.getColumn("Código");
			columna0.setPreferredWidth(100);
			columna0.setMaxWidth(100);
			columna0.setCellRenderer(Utils.alinearDerecha());
			TableColumn columna1 = tabla.getColumn("Cant.");
			columna1.setPreferredWidth(60);
			columna1.setMaxWidth(60); 
			columna1.setCellRenderer(Utils.alinearDerecha());
			TableColumn columna3 = tabla.getColumn("Precio Unit.");
			columna3.setPreferredWidth(80);
			columna3.setMaxWidth(80);
			columna3.setCellRenderer(Utils.alinearDerecha());
			TableColumn columna5 = tabla.getColumn("Precio Total");
			columna5.setPreferredWidth(80);
			columna5.setMaxWidth(80);
			columna5.setCellRenderer(Utils.alinearDerecha());
		}
		return tabla;
	}

	public JButton getJBEliminarProd() {
		if (jbEliminarProd == null) {
			jbEliminarProd = new JButton();
			jbEliminarProd.setText("Eliminar Producto de Factura");
			jbEliminarProd.setName("EliminarP");
			jbEliminarProd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbEliminarProd.setBounds(new Rectangle(30,175,200,25));
			jbEliminarProd.setEnabled(false);
			jbEliminarProd.setInputMap(0, map);
		}
		return jbEliminarProd;
	}

	public JButton getJBConfirmaFact() {
		if (jbConfirmarFact == null) {
			jbConfirmarFact = new JButton();
			jbConfirmarFact.setText("CONFIRMAR FACTURA");
			jbConfirmarFact.setName("ConfirmarFact");
			jbConfirmarFact.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbConfirmarFact.setBounds(new Rectangle(280,200,150,30));
			jbConfirmarFact.setEnabled(false);
			jbConfirmarFact.setInputMap(0, map);
		}
		return jbConfirmarFact;
	}
	
	private JPanel getJPDatosProducto() {
		if (jpDatosProd == null) {
			jpDatosProd = new JPanel();
			jpDatosProd.setLayout(null);
			jpDatosProd.setBounds(new Rectangle(8,365,716,130));
			jpDatosProd.setBorder(Utils.crearTituloTabla("Ingreso de Productos"));
			jpDatosProd.setBackground(Utils.colorPanel);
			jlBusqueda = new JLabel();
			jlBusqueda.setBounds(new Rectangle(15,30,200,20));
			jlBusqueda.setText("Buscar Producto:");
			jlSelecProd = new JLabel();
			jlSelecProd.setBounds(new Rectangle(225,30,150,20));
			jlSelecProd.setText("Seleccione Producto ");
			jlCodigo = new JLabel();
			jlCodigo.setBounds(new Rectangle(15,62,200,20));
			jlCodigo.setText("Código_Producto:");
			jlImporte = new JLabel();
			jlImporte.setBounds(new Rectangle(550,62,70,20));
			jlImporte.setText("Importe:");
			JLabel jlAgregar = new JLabel();
			jlAgregar.setBounds(new Rectangle(15,88,685,32));
			jlAgregar.setBorder(Utils.crearTituloTabla(""));
			jlCantidad = new JLabel();
			jlCantidad.setBounds(new Rectangle(350,92,100,20));
			jlCantidad.setText("Ingrese Cantidad:");
			jpDatosProd.add(jlSelecProd, null);
			jpDatosProd.add(jlCodigo, null);
			jpDatosProd.add(jlBusqueda, null);
			jpDatosProd.add(jlImporte, null);
			jpDatosProd.add(jlCantidad, null);
			jpDatosProd.add(jlAgregar, null);
			jpDatosProd.add(getJTFBusqueda(), null);
			jpDatosProd.add(getJTFCodigo(), null);
			jpDatosProd.add(getJTFImporte(), null);
			jpDatosProd.add(getJTFCantidad(), null);
			jpDatosProd.add(getJBAgregarProd(), null);
		}
		return jpDatosProd;
	}

	public JTextField getJTFBusqueda() {
		if (jtfBusqueda == null) {
			jtfBusqueda = new JTextField();
			jtfBusqueda.setBounds(new Rectangle(120,30,90,22));
		}
		return jtfBusqueda;
	}

	public JComboBox getJCBCodigo() {
		if (jcbCodigo == null) {
			jcbCodigo = new JComboBox();
			jcbCodigo.setBounds(new Rectangle(350,30,350,22));
			jcbCodigo.removeAllItems();
			for(int i=0;i<codProd.size();i++){
				String codPr=(String)codProd.elementAt(i);
				jcbCodigo.addItem(codPr);
			}
			jcbCodigo.setBackground(new Color(255,255,255));
			jcbCodigo.setForeground(java.awt.Color.black);
		}
		return jcbCodigo;
	}

	public JTextField getJTFCodigo() {
		if (jtfCodigo == null) {
			jtfCodigo = new JTextField();
			jtfCodigo.setBounds(new Rectangle(120,62,400,22));
			jtfCodigo.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfCodigo.setEnabled(false);
		}
		return jtfCodigo;
	}
	
	public JTextField getJTFImporte() {
		if (jtfImporte == null) {
			jtfImporte = new JTextField();
			jtfImporte.setBounds(new Rectangle(610,62,90,22));
			jtfImporte.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfImporte.setEnabled(false);
		}
		return jtfImporte;
	}

	public JTextField getJTFCantidad() {
		if (jtfCantidad == null) {
			jtfCantidad = new JTextField();
			jtfCantidad.setBounds(new Rectangle(460,92,60,22));
			jtfCantidad.setDocument(new LimitadorNroMax(jtfCantidad,6));
			jtfCantidad.setText("1");
		}
		return jtfCantidad;
	}
	
	public JButton getJBAgregarProd() {
		if (jbAgregarProd == null) {
			jbAgregarProd = new JButton();
			jbAgregarProd.setText("Agregar Producto");
			jbAgregarProd.setName("AgregarProd");
			jbAgregarProd.setBounds(new Rectangle(565,92,120,25));
			jbAgregarProd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbAgregarProd.setEnabled(false);
			jbAgregarProd.setInputMap(0, map);
		}
		return jbAgregarProd;
	}

	public void actualizarDatos(){
		jlNroFactura.setText("Nro Factura:  "+Utils.nroFact(nroFactura));
		jpDatosFactura.add(jlNroFactura, null);
	}
	
	public void actualizarTabla(){
		jpPpal.remove(getJPDatosItems());
		jpDatosItems = null;
		tabla = null;
		modTabla = null;
		jspDatosInsc = null;
		jpPpal.add(getJPDatosItems(), null);
	}

	public void setListSelectionListener(ListSelectionListener lis) {
		tabla.getSelectionModel().addListSelectionListener(lis);
	}

	public void setActionListeners(ActionListener lis) {
		jbAgregarProd.addActionListener(lis);
		jbBuscarC.addActionListener(lis);
		jbEliminarProd.addActionListener(lis);
		jbConfirmarFact.addActionListener(lis);
	}

	public void setActionListeners2(ActionListener lis) {
		jcbCodigo.addActionListener(lis);
	}

	public void setKeyListeners2(KeyListener lis) {
		jtfBusqueda.addKeyListener(lis);
	}
	
	public void mostrarCombo(){
		jpDatosProd.remove(getJCBCodigo());
		getJCBCodigo();
		jcbCodigo = new JComboBox();
		jcbCodigo.setBounds(new Rectangle(350,30,350,22));
		jcbCodigo.setBackground(new Color(255,255,255));
		jcbCodigo.setForeground(java.awt.Color.black);
		for(int i=0;i<codProd.size();i++){
			String codPr=(String)codProd.elementAt(i);
			jcbCodigo.addItem(codPr);
		}
		jpDatosProd.add(getJCBCodigo(), null);
		this.repaint();
		repaint();
	}

	public void ocultarCombo(){
		jpDatosProd.remove(getJCBCodigo());
		this.repaint();
		repaint();
	}

}

