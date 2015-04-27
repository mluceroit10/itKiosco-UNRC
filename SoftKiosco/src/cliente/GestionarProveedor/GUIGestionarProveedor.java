package cliente.GestionarProveedor;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import cliente.LimitadorNroMax;
import cliente.ModeloTabla;

import common.Utils;

public class GUIGestionarProveedor extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;		    private JPanel jpDatos = null;
	private JPanel jpGestion = null;	    private JPanel jpBuscador = null;
	private JButton jbIngresar = null;		private JButton jbModif = null;
	private JButton jbEliminar = null;    	private JButton jbAceptar = null;
	private JButton jbCancelar = null;	    private JButton jbImprimir = null;
	private JLabel jlNombre = null;			private JLabel jlCodigo = null;
	private JTextField jtfNombre = null;    private JTextField jtfCodigo = null;
	private ModeloTabla modTabla = null;
	public JScrollPane jspDatos = null;
	public final String[] titulos ={"Id","C�digo","Nombre","Tel�fono","Direcci�n", "Localidad"};
	public Object[][] datos;
	public JTable tabla;
	private InputMap map = new InputMap();
	
	public GUIGestionarProveedor(JFrame guiPadre) {
		super(guiPadre);
		datos = new Object[0][titulos.length];
		this.setSize(736,525);
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Gesti�n de Proveedores");
		this.setContentPane(getJPPpal());
		this.getContentPane().setName("GUIGestionarProveedor");
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	public GUIGestionarProveedor(JDialog guiPadre) {
		super(guiPadre);
		datos = new Object[0][titulos.length];
		this.setSize(736,525);
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Gesti�n de Proveedores");
		this.setContentPane(getJPPpal());
		this.getContentPane().setName("GUIGestionarProveedor");
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	public JPanel getJPPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.add(getJPGestion(), null);
			jpPpal.add(getJPDatos(), null);
			jpPpal.add(getJPBuscador(), null);
			jpPpal.add(getJBAceptar(), null);
			jpPpal.add(getJBCancelar(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPBuscador() {
		if (jpBuscador == null) {
			jpBuscador = new JPanel();
			jlCodigo = new JLabel();
			jlCodigo.setBounds(new Rectangle(15,30,60,20));
			jlCodigo.setText("C�digo:");

			jlNombre = new JLabel();
			jlNombre.setBounds(new Rectangle(130,30,60,20));
			jlNombre.setText("Nombre:");
			jpBuscador.setLayout(null);
			jpBuscador.setBorder(Utils.crearTituloTabla("Buscar"));
			jpBuscador.setBounds(new Rectangle(410,15,305,65));
			jpBuscador.add(jlCodigo, null);
			jpBuscador.add(jlNombre, null);
			jpBuscador.add(getJTFBuscadorCodigo(), null);
			jpBuscador.add(getJTFBuscadorNombre(), null);
			jpBuscador.setBackground(Utils.colorPanel);
		}
		return jpBuscador;
	}

	private JPanel getJPGestion() {
		if (jpGestion == null) {
			jpGestion = new JPanel();
			jpGestion.setLayout(null);
			jpGestion.setBounds(new Rectangle(15,15,380,65));
			jpGestion.setBorder(Utils.crearTituloTabla("Gesti�n"));
			jpGestion.add(getJBIngresar(), null);
			jpGestion.add(getJBModificar(), null);
			jpGestion.add(getJBEliminar(), null);
			jpGestion.add(getJBImprimir(), null);
			jpGestion.setBackground(Utils.colorPanel);
		}
		return jpGestion;
	}

	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBounds(new Rectangle(15,95,700,340));
			jpDatos.setBorder(Utils.crearTituloTabla("Listado de Proveedores"));
			jpDatos.add(getJSPDatos(), null);
			jpDatos.setBackground(Utils.colorPanel);
		}
		return jpDatos;
	}

	private JScrollPane getJSPDatos() {
		if (jspDatos == null) {
			jspDatos = new JScrollPane();
			jspDatos.setBounds(new Rectangle(10,20,680,310));
			jspDatos.setViewportView(getJTDatos());
		}
		return jspDatos;
	}

	public JTable getJTDatos() {
		if (tabla == null) {
			modTabla = new ModeloTabla(titulos, datos);
			tabla = new JTable(modTabla);
			Utils.ocultarColumnaId(tabla); 
			TableColumn columna0 = tabla.getColumn("C�digo");
			columna0.setPreferredWidth(70);
			columna0.setMaxWidth(70);
			columna0.setCellRenderer(Utils.alinearDerecha());
		}
		return tabla;
	}

	public JButton getJBIngresar() {
		if (jbIngresar == null) {
			jbIngresar = new JButton();
			jbIngresar.setName("Alta");
			jbIngresar.setText("Ingresar");
			jbIngresar.setBounds(new Rectangle(15,25,80,25));
			jbIngresar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbIngresar.setInputMap(0, map);
		}
		return jbIngresar;
	}

	public JButton getJBModificar() {
		if (jbModif == null) {
			jbModif = new JButton();
			jbModif.setName("Modificar");
			jbModif.setText("Modificar");
			jbModif.setBounds(new Rectangle(105,25,80,25));
			jbModif.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbModif.setInputMap(0, map);
		}
		return jbModif;
	}

	public JTextField getJTFBuscadorCodigo() {
		if (jtfCodigo == null) {
			jtfCodigo = new JTextField();
			jtfCodigo.setBounds(new Rectangle(65,30,50,22));
			jtfCodigo.setDocument(new LimitadorNroMax(jtfCodigo,6));
		}
		return jtfCodigo;
	}

	public JTextField getJTFBuscadorNombre() {
		if (jtfNombre == null) {
			jtfNombre = new JTextField();
			jtfNombre.setBounds(new Rectangle(190,30,100,22));
		}
		return jtfNombre;
	}

	public JButton getJBEliminar() {
		if (jbEliminar == null) {
			jbEliminar = new JButton();
			jbEliminar.setName("Baja");
			jbEliminar.setText("Eliminar");
			jbEliminar.setBounds(new Rectangle(195,25,80,25));
			jbEliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbEliminar.setInputMap(0, map);
		}
		return jbEliminar;
	}

	public JButton getJBImprimir() {
		if (jbImprimir == null) {
			jbImprimir = new JButton();
			jbImprimir.setName("Imprimir");
			jbImprimir.setText("Imprimir");
			jbImprimir.setBounds(new Rectangle(285,25,80,25));
			jbImprimir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbImprimir.setInputMap(0, map);
		}
		return jbImprimir;
	}

	public JButton getJBAceptar() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setName("Aceptar");
			jbAceptar.setText("Aceptar");
			jbAceptar.setBounds(new Rectangle(215,455,100,30));
			jbAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbAceptar.setInputMap(0, map);
		}
		return jbAceptar;
	}

	public JButton getJBCancelar() {
		if (jbCancelar == null) {
			jbCancelar = new JButton();
			jbCancelar.setName("Cancelar");
			jbCancelar.setText("Cancelar");
			jbCancelar.setBounds(new Rectangle(415,455,100,30));
			jbCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCancelar.setInputMap(0, map);
		}
		return jbCancelar;
	}

	public void setKeyListener(KeyListener lis) {
		jtfNombre.addKeyListener(lis);
		jtfCodigo.addKeyListener(lis);
	}

	public void setListSelectionListener(ListSelectionListener lis) {
		tabla.getSelectionModel().addListSelectionListener(lis);
	}

	public void setActionListeners (ActionListener lis) {
		jbIngresar.addActionListener(lis);
		jbModif.addActionListener(lis);
		jbEliminar.addActionListener(lis);
		jbImprimir.addActionListener(lis);
		jbAceptar.addActionListener(lis);
		jbCancelar.addActionListener(lis);
	}

	public void repaint() {
		super.repaint();
	}

	public void actualizarTabla(){
		jpPpal.remove(getJPDatos());
		jpDatos = null;
		tabla = null;
		modTabla = null;
		jspDatos = null;
		jpPpal.add(getJPDatos(), null);
	}
}
