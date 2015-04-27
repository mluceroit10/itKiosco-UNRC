package cliente.CambiarContrasenia;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import common.Utils;

public class GUIContrasenia extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal=null;						private JPanel jContentPane = null;
	private JButton jbCancelar=null;				private JButton jbAceptar=null;
	private JPasswordField jtfContrVieja=null;		private JPasswordField jtfNuevacontrUsr=null;
	private JPasswordField jtfNuevacontrUsrRep=null;
	private JTextField JTFUsuarios = null;
	public String nombUsr;
	private InputMap map = new InputMap();
	
	public GUIContrasenia(JDialog guiPadre,String nombre){
		super(guiPadre);
		this.setSize(new java.awt.Dimension(316,265));
		this.setResizable(false);
		this.setLocationRelativeTo(guiPadre);
		nombUsr=nombre;
		this.setTitle("Cambiar Contraseña");
		this.setContentPane(getPanelPpal());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	private JPanel getPanelPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.setSize(316, 265);
			jpPpal.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, null));
			jpPpal.setName("Gestión Usuario");
			jpPpal.add(getCancelar(), null);
			jpPpal.add(getAceptar(), null);
			jpPpal.add(getJContentPane(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane= new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBorder(Utils.crearTituloTabla("Datos Usuario"));
			jContentPane.setBounds(new Rectangle(15,15,280,160));
			JLabel label0= new JLabel();
			label0.setBounds(new Rectangle(12,30,125,20));
			label0.setText("Usuario: ");
			label0.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel label1= new JLabel();
			label1.setBounds(new Rectangle(12,62,125,20));
			label1.setText("Contraseña Anterior: ");
			label1.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel label2= new JLabel();
			label2.setBounds(new Rectangle(12,94,125,20));
			label2.setText("Nueva Contraseña: ");
			label2.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel label3= new JLabel();
			label3.setBounds(new Rectangle(12,126,125,20));
			label3.setText("Repita Contraseña: ");
			label3.setHorizontalAlignment(SwingConstants.RIGHT);
			jContentPane.add(label0);
			jContentPane.add(label1);
			jContentPane.add(label2);
			jContentPane.add(label3);
			jContentPane.add(getJTFUsuarios(), null);
			jContentPane.add(getContraseniaAnterior(),null);
			jContentPane.add(getNuevaContrasenia(),null);
			jContentPane.add(getNuevaContraseniaRepeticion(),null);
			jContentPane.setBackground(Utils.colorPanel);
		}
		return jContentPane;
	}
	
	public JTextField getJTFUsuarios(){
		if (JTFUsuarios == null) {
			JTFUsuarios = new JTextField();
			JTFUsuarios.setBounds(new Rectangle(140,30,120,20));
			JTFUsuarios.setDisabledTextColor(Utils.colorTextoDisabled);
			JTFUsuarios.setText(nombUsr);
			JTFUsuarios.setEnabled(false);
		}
		return JTFUsuarios;
	}

	public JPasswordField getContraseniaAnterior() {
		if (jtfContrVieja == null) {
			jtfContrVieja = new JPasswordField();
			jtfContrVieja.setBounds(new Rectangle(140,62,100,22));
		}
		return jtfContrVieja;
	}

	public JPasswordField getNuevaContrasenia() {
		if (jtfNuevacontrUsr == null) {
			jtfNuevacontrUsr = new JPasswordField();
			jtfNuevacontrUsr.setBounds(new Rectangle(140,94,100,22));
		}
		return jtfNuevacontrUsr;
	}

	public JPasswordField getNuevaContraseniaRepeticion() {
		if (jtfNuevacontrUsrRep == null) {
			jtfNuevacontrUsrRep = new JPasswordField();
			jtfNuevacontrUsrRep.setBounds(new Rectangle(140,126,100,22));
		}
		return jtfNuevacontrUsrRep;
	}
	
	public JButton getAceptar() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setBounds(new Rectangle(45,195,100,30));
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
			jbCancelar.setBounds(new Rectangle(165,195,100,30));
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
	
}

