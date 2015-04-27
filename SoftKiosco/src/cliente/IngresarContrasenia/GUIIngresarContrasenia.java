package cliente.IngresarContrasenia;

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
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import common.Utils;

public class GUIIngresarContrasenia extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal=null;				private JPanel jpDatos = null;
	private JButton jbCancelar=null;		private JButton jbAceptar=null;
	private JPasswordField jtfcontrUsr=null;
	private InputMap map = new InputMap();
	private JComboBox JCUsuarios = null;
	public Vector usuarios= new Vector();

	public GUIIngresarContrasenia(JFrame guiPadre){
		super(guiPadre);
		this.setSize(new java.awt.Dimension(306,205));
		this.setResizable(false);
		this.setLocationRelativeTo(guiPadre);
		this.setTitle("Registrarse");
		this.setContentPane(getPanelPpal());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	private JPanel getPanelPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.setSize(306, 205);
			jpPpal.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, null));
			jpPpal.setName("Gestión Usuario");
			jpPpal.add(getCancelar(), null);
			jpPpal.add(getAceptar(), null);
			jpPpal.add(getJPDatos(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jpDatos= new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBorder(Utils.crearTituloTabla("Datos Usuario"));
			jpDatos.setBounds(new Rectangle(15,15,270,100));
			JLabel label0= new JLabel();
			label0.setBounds(new Rectangle(12,30,80,20));
			label0.setText("Usuario: ");
			label0.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel label1= new JLabel();
			label1.setBounds(new Rectangle(12,62,80,20));
			label1.setText("Contraseña: ");
			label1.setHorizontalAlignment(SwingConstants.RIGHT);
			jpDatos.add(label0);
			jpDatos.add(label1);
			jpDatos.add(getContrasenia(),null);
			jpDatos.setBackground(Utils.colorPanel);
		}
		return jpDatos;
	}
	
	public JComboBox getJCBUsuarios(){
		if (JCUsuarios == null) {
			JCUsuarios  = new JComboBox();
			for(int i=0;i<usuarios.size();i++){
				String usr=(String)usuarios.elementAt(i);
				JCUsuarios.addItem(usr);
			}
			JCUsuarios.setBackground(new Color(255,255,255));
			JCUsuarios.setForeground(java.awt.Color.black);
			JCUsuarios.setBounds(new Rectangle(100,30,150,22));
		}
		return JCUsuarios;
	}
	
	public JPasswordField getContrasenia() {
		if (jtfcontrUsr == null) {
			jtfcontrUsr = new JPasswordField();
			jtfcontrUsr.setBounds(new Rectangle(100,62,150,22));
		}
		return jtfcontrUsr;
	}

	public JButton getAceptar() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setBounds(new Rectangle(40,135,100,30));
			jbAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbAceptar.setName("Aceptar");
			jbAceptar.setText("Aceptar");
			jbAceptar.setInputMap(0, map);
		}
		return jbAceptar;
	}
	
	public JButton getCancelar() {
		if (jbCancelar == null) {
			jbCancelar = new JButton();
			jbCancelar.setBounds(new Rectangle(160,135,100,30));
			jbCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCancelar.setName("Cancelar");
			jbCancelar.setText("Cancelar");
			jbCancelar.setInputMap(0, map);
		}
		return jbCancelar;
	}

	public void setActionListenersInicio(ActionListener lis){
		this.jbCancelar.addActionListener(lis);
		this.jbAceptar.addActionListener(lis);
	}
	
	public void actualizarTabla(){
		jpDatos.add(getJCBUsuarios(), null);
	}
	
}

