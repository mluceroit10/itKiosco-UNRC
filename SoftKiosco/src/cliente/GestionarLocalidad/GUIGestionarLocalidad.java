package cliente.GestionarLocalidad;

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

import cliente.ModeloTabla;

import common.Utils;

public class GUIGestionarLocalidad extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;			    private JPanel jpGestion = null;
	private JPanel jpDatos = null;			    private JPanel jpBuscador = null;
	private JButton jbCargar = null;		    private JButton jbModif = null;
	private JButton jbAceptar = null;			private JButton jbCancelar = null;
	private JButton jbBorrar = null;
	private JLabel jlNombre = null;
	private JTextField jtfNombre = null;
	public JScrollPane jspDatos = null;
	public JTable jtDatos = null;			    private ModeloTabla modTabla = null;
	public String[] titulos = {"Id","Nombre","Cód. Postal","Provincia"};
	public Object[][] datos;
	private InputMap map = new InputMap();
	
	public GUIGestionarLocalidad(JFrame guiPadre) {
		super(guiPadre);
		datos = new Object[0][titulos.length];
		this.setSize(new java.awt.Dimension(591,385));
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Gestión de Localidades");
		this.setContentPane(getJPPpal());
		this.setModal(true); 
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	public GUIGestionarLocalidad (JDialog guiPadre) {
		super(guiPadre);
		datos = new Object[0][titulos.length];
		this.setSize(new java.awt.Dimension(591,385));
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Gestión de Localidades");
		this.setContentPane(getJPPpal());
		this.setModal(true); 
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	public JPanel getJPPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.add(getJPGestion(), null);
			jpPpal.add(getJBAceptar(), null);
			jpPpal.add(getJBCancelar(), null);
			jpPpal.add(getJPDatos(), null);
			jpPpal.add(getJPBuscador(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPBuscador() {
		if (jpBuscador == null) {
			jlNombre = new JLabel();
			jlNombre.setBounds(new Rectangle(10,22,60,15));
			jlNombre.setText("Nombre:");
			jpBuscador = new JPanel();
			jpBuscador.setLayout(null);
			jpBuscador.setBorder(Utils.crearTituloTabla("Buscar"));
			jpBuscador.setBounds(new Rectangle(15,205,140,90));
			jpBuscador.add(jlNombre, null);
			jpBuscador.add(getJTFBuscador(), null);
			jpBuscador.setBackground(Utils.colorPanel);
		}
		return jpBuscador;
	}
	
	public JTextField getJTFBuscador() {
		if (jtfNombre == null) {
			jtfNombre = new JTextField();
			jtfNombre.setBounds(new Rectangle(10,50,110,22));
		}
		return jtfNombre;
	}
	
	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBounds(new Rectangle(170,15,400,280));
			jpDatos.setBorder(Utils.crearTituloTabla("Localidades"));
			jpDatos.add(getJSPDatos(), null);
			jpDatos.setBackground(Utils.colorPanel);
		}
		return jpDatos;
	}
	
	private JScrollPane getJSPDatos() {
		if (jspDatos == null) {
			jspDatos = new JScrollPane();
			jspDatos.setBounds(new Rectangle(10,20,380,250));
			jspDatos.setViewportView(getJTDatos());
		}
		return jspDatos;
	}

	public JTable getJTDatos() {
		if (jtDatos == null) {
			modTabla = new ModeloTabla(titulos, datos);
			jtDatos = new JTable(modTabla);
			Utils.ocultarColumnaId(jtDatos); 
			TableColumn columna1 = jtDatos.getColumn("Cód. Postal");
			columna1.setPreferredWidth(80);
			columna1.setMaxWidth(80);
			columna1.setCellRenderer(Utils.alinearDerecha());
		}
		return jtDatos;
	}
	
	private JPanel getJPGestion() {
		if (jpGestion == null) {
			jpGestion = new JPanel();
			jpGestion.setLayout(null);
			jpGestion.setBounds(new Rectangle(15,15,140,175));
			jpGestion.setBorder(Utils.crearTituloTabla("Gestión"));
			jpGestion.add(getJBCargar(), null);
			jpGestion.add(getJBMod(), null);
			jpGestion.add(getJBBorrar(), null);
			jpGestion.setBackground(Utils.colorPanel);
		}
		return jpGestion;
	}

	public JButton getJBCargar() {
		if (jbCargar == null) {
			jbCargar = new JButton();
			jbCargar.setText("Ingresar");
			jbCargar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCargar.setBounds(new Rectangle(20,25,100,25));
			jbCargar.setInputMap(0, map);
		}
		return jbCargar;
	}

	public JButton getJBMod() {
		if (jbModif == null) {
			jbModif = new JButton();
			jbModif.setText("Modificar");
			jbModif.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbModif.setBounds(new Rectangle(20,75,100,25));
			jbModif.setInputMap(0, map);
		}
		return jbModif;
	}

	public JButton getJBBorrar() {
		if (jbBorrar == null) {
			jbBorrar = new JButton();
			jbBorrar.setText("Eliminar");
			jbBorrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbBorrar.setBounds(new Rectangle(20,125,100,25));
			jbBorrar.setInputMap(0, map);
		}
		return jbBorrar;
	}

	public JButton getJBAceptar() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setBounds(new Rectangle(142,315,100,30));
			jbAceptar.setText("Aceptar");
			jbAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbAceptar.setInputMap(0, map);
		}
		return jbAceptar;
	}

	public JButton getJBCancelar() {
		if (jbCancelar == null) {
			jbCancelar = new JButton();
			jbCancelar.setBounds(new Rectangle(343,315,100,30));
			jbCancelar.setText("Cancelar");
			jbCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCancelar.setInputMap(0, map);
		}
		return jbCancelar;
	}

	public void setActionListeners(ActionListener lis) {
		jbCancelar.addActionListener(lis);
		jbAceptar.addActionListener(lis);
		jbModif.addActionListener(lis);
		jbCargar.addActionListener(lis);
		jbBorrar.addActionListener(lis);
	}

	public void setKeyListener(KeyListener lis) {
		jtfNombre.addKeyListener(lis);
	}

	public void setListSelectionListener(ListSelectionListener lis) {
		jtDatos.getSelectionModel().addListSelectionListener(lis);
	}

	public void actualizarTabla(){
		jpPpal.remove(getJPDatos());
		jpDatos = null;
		jtDatos = null;
		modTabla = null;
		jspDatos = null;
		jpPpal.add(getJPDatos(), null);
	}
	
}

