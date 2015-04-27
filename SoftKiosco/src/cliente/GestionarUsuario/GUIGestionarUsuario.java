package cliente.GestionarUsuario;

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
import common.constantes.TIPO_USUARIOS;

public class GUIGestionarUsuario extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;		    private JPanel jpDatos = null;
	private JPanel jpGestion = null;	    private JPanel jpBuscador = null;
	private JButton jbIngresar = null;		private JButton jbModif = null;
	private JButton jbEliminar = null;    	private JButton jbSalir = null;
	private JButton jbImprimir;			    private JButton jbCContr = null;
	private JLabel jlNombre;
	private JTextField jtfNombre = null;    
	private ModeloTabla modTabla = null;
	public JScrollPane jspDatos = null;
	public final String[] titulos ={"Id","Apellido y Nombre","DNI","Teléfono","Dirección", "Localidad","tipo Usuario","nombre Usr","Beca"};
	public Object[][] datos;
	public JTable tabla;
	private JButton jbECuenta;
	private String tipoSesionUsr="";
	int altopanelBuscador=65;	int indiceYPanelDatos=95;
	int altoPanelDatos=340;		int altoJSPDatos=310;
	private InputMap map = new InputMap();


	public GUIGestionarUsuario(JFrame guiPadre,String tipoSesion) {
		super(guiPadre);
		tipoSesionUsr=tipoSesion;
		if(tipoSesionUsr.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0){
			altopanelBuscador=105;
			indiceYPanelDatos=135;
			altoPanelDatos=300;
			altoJSPDatos=270;
		}
		datos = new Object[0][titulos.length];
		this.setSize(736,525);
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Gestión de Usuarios");
		this.setContentPane(getJPPpal());
		this.getContentPane().setName("GUIGestionarUsuario");
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
			jpPpal.add(getJBSalir(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPBuscador() {
		if (jpBuscador == null) {
			jpBuscador = new JPanel();
			jlNombre = new JLabel();
			jlNombre.setBounds(new Rectangle(10,30,60,20));
			jlNombre.setText("Nombre:");
			jpBuscador.setLayout(null);
			jpBuscador.setBorder(Utils.crearTituloTabla("Buscar"));
			jpBuscador.setBounds(new Rectangle(410,15,305,65));
			jpBuscador.add(jlNombre, null);
			jpBuscador.add(getJTFBuscador(), null);
			jpBuscador.setBackground(Utils.colorPanel);
		}
		return jpBuscador;
	}
	
	public JTextField getJTFBuscador() {
		if (jtfNombre == null) {
			jtfNombre = new JTextField();
			jtfNombre.setBounds(new Rectangle(67,30,220,22));
		}
		return jtfNombre;
	}

	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBounds(new Rectangle(15,indiceYPanelDatos,700,altoPanelDatos));
			jpDatos.setBorder(Utils.crearTituloTabla("Listado de Usuarios"));
			jpDatos.add(getJSPDatos(), null);
			jpDatos.setBackground(Utils.colorPanel);
		}
		return jpDatos;
	}

	private JScrollPane getJSPDatos() {
		if (jspDatos == null) {
			jspDatos = new JScrollPane();
			jspDatos.setBounds(new Rectangle(10,20,680,altoJSPDatos));
			jspDatos.setViewportView(getJTDatos());
		}
		return jspDatos;
	}

	public JTable getJTDatos() {
		if (tabla == null) {
			modTabla = new ModeloTabla(titulos, datos);
			tabla = new JTable(modTabla);
			Utils.ocultarColumnaId(tabla);
			TableColumn columna0 = tabla.getColumn("DNI");
			columna0.setPreferredWidth(70);
			columna0.setMaxWidth(70);
			columna0.setCellRenderer(Utils.alinearDerecha());
			TableColumn columna7 = tabla.getColumn("Beca");
			columna7.setPreferredWidth(60);
			columna7.setMaxWidth(60);
			columna7.setCellRenderer(Utils.alinearDerecha());
		}
		return tabla;
	}

	private JPanel getJPGestion() {
		if (jpGestion == null) {
			jpGestion = new JPanel();
			jpGestion.setLayout(null);
			jpGestion.setBounds(new Rectangle(15,15,380,altopanelBuscador));
			jpGestion.setBorder(Utils.crearTituloTabla("Gestión"));
			if(tipoSesionUsr.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0){
				jpGestion.add(getJBIngresar(), null);
				jpGestion.add(getJBModificar(), null);
				jpGestion.add(getJBEliminar(), null);
			}
			jpGestion.add(getJBImprimir(), null);
			jpGestion.add(getJBECuenta(), null);
			jpGestion.add(getJBCContrasenia(), null);
			jpGestion.setBackground(Utils.colorPanel);
		}
		return jpGestion;
	}
	
	public JButton getJBIngresar() {
		if (jbIngresar == null) {
			jbIngresar = new JButton();
			jbIngresar.setName("Alta");
			jbIngresar.setText("Ingresar");
			jbIngresar.setBounds(new Rectangle(15,65,90,25));
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
			jbModif.setBounds(new Rectangle(120,65,90,25));
			jbModif.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbModif.setInputMap(0, map);
		}
		return jbModif;
	}
	
	public JButton getJBEliminar() {
		if (jbEliminar == null) {
			jbEliminar = new JButton();
			jbEliminar.setName("Baja");
			jbEliminar.setText("Eliminar");
			jbEliminar.setBounds(new Rectangle(225,65,90,25));
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
			jbImprimir.setBounds(new Rectangle(120,25,90,25));
			jbImprimir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbImprimir.setInputMap(0, map);
		}
		return jbImprimir;
	}

	public JButton getJBECuenta() {
		if (jbECuenta == null) {
			jbECuenta = new JButton();
			jbECuenta.setName("Cuenta");
			jbECuenta.setText("E. Cuenta");
			jbECuenta.setBounds(new Rectangle(15,25,90,25));
			jbECuenta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbECuenta.setInputMap(0, map);
		}
		return jbECuenta;
	}

	public JButton getJBCContrasenia() {
		if (jbCContr == null) {
			jbCContr = new JButton();
			jbCContr.setName("Contrasenia");
			jbCContr.setText("Cambiar Contraseña");
			jbCContr.setBounds(new Rectangle(225,25,140,25));
			jbCContr.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCContr.setInputMap(0, map);
		}
		return jbCContr;
	}

	public JButton getJBSalir() {
		if (jbSalir == null) {
			jbSalir = new JButton();
			jbSalir.setName("Salir");
			jbSalir.setText("Salir");
			jbSalir.setBounds(new Rectangle(315,455,100,30));
			jbSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbSalir.setInputMap(0, map);
		}
		return jbSalir;
	}

	public void setKeyListener(KeyListener lis) {
		jtfNombre.addKeyListener(lis);
	}

	public void setListSelectionListener(ListSelectionListener lis) {
		tabla.getSelectionModel().addListSelectionListener(lis);
	}

	public void setActionListeners (ActionListener lis) {
		if(tipoSesionUsr.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0){
			jbIngresar.addActionListener(lis);
			jbModif.addActionListener(lis);
			jbEliminar.addActionListener(lis);
		}
		jbImprimir.addActionListener(lis);
		jbECuenta.addActionListener(lis);
		jbCContr.addActionListener(lis);
		jbSalir.addActionListener(lis);
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
