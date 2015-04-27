package cliente.GestionarProducto;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import cliente.LimitadorNroGuion;
import cliente.LimitadorNroMax;
import cliente.LimitadorPrecio;

import common.Utils;
import common.DTOs.ProductoDTO;

public class GUIAltaModProducto extends JDialog {  
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;		    	private JPanel jpDatos = null; 
	private JButton jbAceptar = null;	    	private JButton jbCancelar = null;
	private JButton jbProveedor = null;
	private JLabel jlNombre = null; 			private JLabel jlCodigo = null;
	private JLabel jlStockA = null;				private JLabel jlStockM = null;
	private JLabel jlProveedor = null;			private JLabel jlMargenGanancia=null;
	private JLabel jlPrecioEntrada = null;		private JLabel jlPrecioVenta = null;
	private JTextField jtfNombre = null;    	private JTextField jtfCodigo  = null;    
	private JTextField jtfStockA = null;    	private JTextField jtfStockM = null;
	private JTextField jtfProveedor = null;		private JTextField jtfMargenGanancia = null;
	private JTextField jtfPrecioEntrada = null;	private JTextField jtfPrecioVenta = null;
	public String[] titServicio ={"Nombre"};
	public Object[][] datosServicio = new Object[0][1];
	public JTable jtServicio = null;
	private ProductoDTO pDTO = null;
	private InputMap map = new InputMap();

	public GUIAltaModProducto(GUIGestionarProducto guiPadre) {
		super(guiPadre);
		this.setSize(new java.awt.Dimension(356,415));
		this.setTitle("Nuevo Producto");
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setContentPane(getJPPpal());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	public GUIAltaModProducto(ProductoDTO p,GUIGestionarProducto guiPadre) {
		super(guiPadre);
		this.pDTO = p;
		this.setSize(new java.awt.Dimension(356,415));
		this.setTitle("Modificar Producto");
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setContentPane(getJPPpal());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	private JPanel getJPPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.setSize(new java.awt.Dimension(356,415));
			jpPpal.add(getJPDatos(),null);
			jpPpal.add(getJBAceptar(), null);
			jpPpal.add(getJBCancelar(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jlCodigo = new JLabel();
			jlCodigo.setBounds(new Rectangle(10,30,110,20));
			jlCodigo.setText("(*) Código:");
			jlCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
			jlNombre = new JLabel("(*) Nombre:");
			jlNombre.setBounds(new Rectangle(10,62,110,20));
			jlNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			jlStockA = new JLabel();
			jlStockA.setBounds(new Rectangle(10,94,110,20));
			jlStockA.setText("(*) Stock Actual:");
			jlStockA.setHorizontalAlignment(SwingConstants.RIGHT);
			jlStockM = new JLabel();
			jlStockM.setBounds(new Rectangle(10,126,110,20));
			jlStockM.setText("(*) Stock Mínimo:");
			jlStockM.setHorizontalAlignment(SwingConstants.RIGHT);
			jlPrecioEntrada = new JLabel();
			jlPrecioEntrada.setBounds(new Rectangle(10,158,110,20));
			jlPrecioEntrada.setText("(*) Precio Entrada:");
			jlPrecioEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
			jlMargenGanancia = new JLabel();
			jlMargenGanancia.setBounds(new Rectangle(10,190,110,20));
			jlMargenGanancia.setText("(*) Ganancia en %:");
			jlMargenGanancia.setHorizontalAlignment(SwingConstants.RIGHT);
			jlPrecioVenta = new JLabel();
			jlPrecioVenta.setBounds(new Rectangle(10,222,110,20));
			jlPrecioVenta.setText("(*) Precio Venta:");
			jlPrecioVenta.setHorizontalAlignment(SwingConstants.RIGHT);
			jlProveedor = new JLabel();
			jlProveedor.setBounds(new Rectangle(10,254,110,20));
			jlProveedor.setText("(*) Proveedor:");
			jlProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel jlLeyenda = new JLabel();
			jlLeyenda.setBounds(new Rectangle(10,286,300,20));
			jlLeyenda.setText("(*) Datos obligatorios");
			jlLeyenda.setHorizontalAlignment(SwingConstants.RIGHT);
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBorder(Utils.crearTituloTabla("Datos del Producto"));
			jpDatos.setBounds(new Rectangle(15,15,320,310));
			jpDatos.add(jlCodigo, null);
			jpDatos.add(jlNombre, null);
			jpDatos.add(jlStockA, null);
			jpDatos.add(jlStockM, null);
			jpDatos.add(jlPrecioEntrada, null);
			jpDatos.add(jlMargenGanancia, null);
			jpDatos.add(jlPrecioVenta, null);
			jpDatos.add(jlProveedor, null);
			jpDatos.add(jlLeyenda, null);
			jpDatos.add(getJTFCodigo(), null);
			jpDatos.add(getJTFNombre(), null);
			jpDatos.add(getJTFStockAct(), null);
			jpDatos.add(getJTFStockMin(), null);
			jpDatos.add(getJTFPrecioEntrada(), null);
			jpDatos.add(getJTFMargenGanancia(), null);
			jpDatos.add(getJTFPrecioVenta(), null);
			jpDatos.add(getJTFProveedor(), null);
			jpDatos.add(getJBProveedor(), null);
			if (pDTO!=null) {
				jtfNombre.setText(pDTO.getNombre());
				jtfCodigo.setText(String.valueOf(pDTO.getCodigo()));
				jtfStockM.setText(String.valueOf(pDTO.getStockMinimo()));
				jtfStockA.setText(String.valueOf(pDTO.getStockActual()));
				jtfPrecioEntrada.setText(Utils.ordenarDosDecimales(pDTO.getPrecioEntrada()));
				jtfMargenGanancia.setText(String.valueOf(pDTO.getMargenGanancia()));
				jtfPrecioVenta.setText(Utils.ordenarDosDecimales(pDTO.getPrecioVenta()));
				jtfProveedor.setText(pDTO.getProveedor().getNombre());
			}
			jpDatos.setBackground(Utils.colorPanel);
		}
		return jpDatos;
	}

	public JTextField getJTFCodigo() {
		if (jtfCodigo==null){
			jtfCodigo=new JTextField();
			jtfCodigo.setBounds(new Rectangle(130,30,170,22));
			jtfCodigo.setBackground(new Color(255,255,255));
			jtfCodigo.setDocument(new LimitadorNroMax(jtfCodigo,13));
		}
		return jtfCodigo;
	}

	public JTextField getJTFNombre() {
		if (jtfNombre == null) {
			jtfNombre = new JTextField();
			jtfNombre.setBounds(new Rectangle(130,62,170,22));
		}
		return jtfNombre;
	}

	public JTextField getJTFStockAct() {
		if (jtfStockA == null) {
			jtfStockA = new JTextField();
			jtfStockA.setBounds(new Rectangle(130,94,100,22));
			jtfStockA.setDocument(new LimitadorNroGuion(jtfStockA));
		}
		return jtfStockA;
	}

	public JTextField getJTFStockMin() {
		if (jtfStockM == null) {
			jtfStockM = new JTextField();
			jtfStockM.setBounds(new Rectangle(130,126,100,22));
			jtfStockM.setDocument(new LimitadorNroMax(jtfStockM));
		}
		return jtfStockM;
	}

	public JTextField getJTFPrecioEntrada() {
		if (jtfPrecioEntrada == null) {
			jtfPrecioEntrada = new JTextField();
			jtfPrecioEntrada.setBounds(new Rectangle(130,158,100,22));
			jtfPrecioEntrada.setDocument(new LimitadorPrecio(jtfPrecioEntrada));
		}
		return jtfPrecioEntrada;
	}

	public JTextField getJTFMargenGanancia() {
		if (jtfMargenGanancia == null) {
			jtfMargenGanancia = new JTextField();
			jtfMargenGanancia.setBounds(new Rectangle(130,190,100,22));
			jtfMargenGanancia.setDocument(new LimitadorNroMax(jtfMargenGanancia,3));
		}
		return jtfMargenGanancia;
	}

	public JTextField getJTFPrecioVenta() {
		if (jtfPrecioVenta == null) {
			jtfPrecioVenta = new JTextField();
			jtfPrecioVenta.setBounds(new Rectangle(130,222,100,22));
			jtfPrecioVenta.setDocument(new LimitadorPrecio(jtfPrecioVenta));
		}
		return jtfPrecioVenta;
	}

	public JTextField getJTFProveedor() {
		if (jtfProveedor == null) {
			jtfProveedor = new JTextField();
			jtfProveedor.setBounds(new Rectangle(130,254,100,22));
			jtfProveedor.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfProveedor.setEnabled(false);
		}
		return jtfProveedor;
	}

	public JButton getJBProveedor() {
		if (jbProveedor == null) {
			jbProveedor = new JButton();
			jbProveedor.setBounds(new Rectangle(240,254,70,22));
			jbProveedor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbProveedor.setName("Buscar");
			jbProveedor.setText("Buscar");
			jbProveedor.setInputMap(0, map);
		}
		return jbProveedor;
	}

	public JButton getJBAceptar() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setBounds(new Rectangle(65,345,100,30));
			jbAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbAceptar.setText("Aceptar");
			jbAceptar.setInputMap(0, map);
		}
		return jbAceptar;
	}

	public JButton getJBCancelar() {
		if (jbCancelar == null) {
			jbCancelar = new JButton();
			jbCancelar.setBounds(new Rectangle(185,345,100,30));
			jbCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCancelar.setText("Cancelar");
			jbCancelar.setInputMap(0, map);
		}
		return jbCancelar;
	}

	public void setActionListeners(ActionListener lis) {
		jbAceptar.addActionListener(lis);
		jbCancelar.addActionListener(lis);
		jbProveedor.addActionListener(lis);
	}

	public void setKeyListener(KeyListener lis) {
		jtfPrecioEntrada.addKeyListener(lis);
		jtfMargenGanancia.addKeyListener(lis);
	}

	public void setProveedor(String string) {
		jtfProveedor.setText(string);
	}

}