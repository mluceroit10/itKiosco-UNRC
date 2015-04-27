package cliente.GestionarSesion;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import cliente.ModeloTabla;

import common.Utils;

public class GUIInicioCierreSesion extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;		    
	private JPanel jContentPaneIS = null;	private JPanel jContentPaneCS = null;
	private JButton jbIngresar = null;		private JButton jbSalir = null;
	private JButton jbCerrar = null;		    
	private ModeloTabla modTabla = null;
	public JScrollPane jspDatos = null;
	public final String[] titulos ={"Id","Apellido y Nombre","Fecha Ingreso"};
	public Object[][] datos;
	public JTable tabla;
	private JPasswordField jtfContrasenia = null;	private JPasswordField jtfContraseniaCierre = null;
	private JComboBox JCUsuarios;
	public Vector usuarios=new Vector();
	private InputMap map = new InputMap();

	public GUIInicioCierreSesion(JFrame guiPadre) {
		super(guiPadre);
		datos = new Object[0][titulos.length];
		this.setSize(436,540);
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
			jpPpal.add(getJPInicio(), null);
			jpPpal.add(getJPCierre(), null);
			jpPpal.add(getJBSalir(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPInicio() {
		if (jContentPaneIS == null) {
			jContentPaneIS= new JPanel();
			jContentPaneIS.setLayout(null);
			jContentPaneIS.setBorder(Utils.crearTituloTabla("Iniciar Sesion"));
			jContentPaneIS.setBounds(new Rectangle(15,15,400,140));
			JLabel label0= new JLabel();
			label0.setBounds(new Rectangle(10,30,100,20));
			label0.setText("Usuario:");
			label0.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel label1= new JLabel();
			label1.setBounds(new Rectangle(10,62,100,20));
			label1.setText("Contraseña:");
			label1.setHorizontalAlignment(SwingConstants.RIGHT);
			jContentPaneIS.add(label0);
			jContentPaneIS.add(label1);
			jContentPaneIS.add(getContrasenia(),null);
			jContentPaneIS.add(getJBIniciar(),null);
			jContentPaneIS.setBackground(Utils.colorPanel);
		}
		return jContentPaneIS;
	}

	public JComboBox getJCUsuarios(){
		if (JCUsuarios == null) {
			JCUsuarios = new JComboBox();
			for(int i=0;i<usuarios.size();i++){
				String prov=(String)usuarios.elementAt(i);
				JCUsuarios.addItem(prov);
			}
			JCUsuarios.setBackground(new Color(255,255,255));
			JCUsuarios.setForeground(java.awt.Color.black);
			JCUsuarios.setBounds(new Rectangle(120,30,120,20));
		}
		return JCUsuarios;
	}

	public JPasswordField getContrasenia() {
		if (jtfContrasenia == null) {
			jtfContrasenia = new JPasswordField();
			jtfContrasenia.setBounds(new Rectangle(120,62,120,22));
		}
		return jtfContrasenia;
	}

	public JButton getJBIniciar() {
		if (jbIngresar == null) {
			jbIngresar = new JButton();
			jbIngresar.setName("Iniciar");
			jbIngresar.setText("Iniciar Sesión");
			jbIngresar.setBounds(new Rectangle(260,100,120,25));
			jbIngresar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbIngresar.setInputMap(0, map);
		}
		return jbIngresar;
	}

	private JPanel getJPCierre() {
		if (jContentPaneCS == null) {
			jContentPaneCS= new JPanel();
			jContentPaneCS.setLayout(null);
			jContentPaneCS.setBorder(Utils.crearTituloTabla("Usuarios Registrados"));
			jContentPaneCS.setBounds(new Rectangle(15,170,400,280));
			JLabel label1= new JLabel();
			label1.setBounds(new Rectangle(10,215,250,20));
			label1.setText("Seleccione usuario e ingrese Contraseña: ");
			label1.setHorizontalAlignment(SwingConstants.RIGHT);
			jContentPaneCS.add(label1);
			jContentPaneCS.add(getJSPDatos(),null);
			jContentPaneCS.add(getContraseniaCierre(),null);
			jContentPaneCS.add(getJBCerrar(),null);
			jContentPaneCS.setBackground(Utils.colorPanel);
		}
		return jContentPaneCS;
	}

	private JScrollPane getJSPDatos() {
		if (jspDatos == null) {
			jspDatos = new JScrollPane();
			jspDatos.setBounds(new Rectangle(10,20,380,180));
			jspDatos.setViewportView(getJTDatos());
		}
		return jspDatos;
	}

	public JTable getJTDatos() {
		if (tabla == null) {
			modTabla = new ModeloTabla(titulos, datos);
			tabla = new JTable(modTabla);
			Utils.ocultarColumnaId(tabla);
			TableColumn columna0 = tabla.getColumn("Fecha Ingreso");
			columna0.setPreferredWidth(120);
			columna0.setMaxWidth(120);
			columna0.setCellRenderer(Utils.alinearCentro());
		}
		return tabla;
	}

	public JPasswordField getContraseniaCierre() {
		if (jtfContraseniaCierre == null) {
			jtfContraseniaCierre = new JPasswordField();
			jtfContraseniaCierre.setBounds(new Rectangle(260,215,120,22));
		}
		return jtfContraseniaCierre;
	}    

	public JButton getJBCerrar() {
		if (jbCerrar == null) {
			jbCerrar = new JButton();
			jbCerrar.setName("Cerrar");
			jbCerrar.setText("Cerrar Sesión");
			jbCerrar.setBounds(new Rectangle(260,245,120,25));
			jbCerrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCerrar.setInputMap(0, map);
		}
		return jbCerrar;
	}

	public JButton getJBSalir() {
		if (jbSalir == null) {
			jbSalir = new JButton();
			jbSalir.setName("Salir");
			jbSalir.setText("Salir");
			jbSalir.setBounds(new Rectangle(165,470,100,30));
			jbSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbSalir.setInputMap(0, map);
		}
		return jbSalir;
	}

	public void setListSelectionListener(ListSelectionListener lis) {
		tabla.getSelectionModel().addListSelectionListener(lis);
	}

	public void setActionListeners (ActionListener lis) {
		jbIngresar.addActionListener(lis);
		jbCerrar.addActionListener(lis);
		jbSalir.addActionListener(lis);
	}

	public void repaint() {
		super.repaint();
	}

	public void actualizarTabla(){
		jContentPaneCS.remove(getJSPDatos());
		tabla = null;
		modTabla = null;
		jspDatos = null;
		jContentPaneCS.add(getJSPDatos(), null);
	}

	public void actualizarComboUsr(){
		jContentPaneIS.add(getJCUsuarios(), null);
		repaint();
	}

	public void elimComboUsr(){
		jContentPaneIS.remove(getJCUsuarios());
		jContentPaneIS.add(getJCUsuarios());
		this.repaint();
	}
	
}
