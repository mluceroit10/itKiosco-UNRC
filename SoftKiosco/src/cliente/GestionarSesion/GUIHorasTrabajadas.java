package cliente.GestionarSesion;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import cliente.LimitadorNroMax;

import common.Utils;

public class GUIHorasTrabajadas extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal=null;  				private JPanel jpTipoDatos=null;
	private JButton jBContinuar = null;			private JButton jBCancelar=null;
	private JLabel jlTitulo = null;				private JLabel jlUsr = null;
	private JLabel jlMes = null;				private JLabel jlAnio = null;
	private JComboBox JCUsuarios = null;		private JComboBox jCMeses = null;
	public Vector usuarios= new Vector();
	private JTextField jtfAnio= null;
	private InputMap map = new InputMap();
	
	public GUIHorasTrabajadas(JFrame guiPadre) {
		super(guiPadre);
		this.setSize(486,320);
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Horas Trabajadas");
		this.setContentPane(getPanelPpal());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	private JPanel getPanelPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.setSize(486, 320);
			jpPpal.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, null));
			jpPpal.setName("Horas Trabajadas");
			jlTitulo = new JLabel();
			jlTitulo.setBounds(new Rectangle(103,12,280,25));
			jlTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jlTitulo.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			jlTitulo.setText("VERIFICAR HORAS TRABAJADAS");
			jpPpal.add(jlTitulo, null);
			try {
				jpPpal.add(getJPTipoDatos(), null);
			} catch (RemoteException ex) {
				Utils.manejoErrores(ex,"Error en GUIHorasTrabajadas. getPanelPpal");
			}
			jpPpal.setBackground(Utils.colorFondo);
			jpPpal.add(getJBCancelar(), null);
		}
		return jpPpal;
	}

	private JPanel getJPTipoDatos() throws RemoteException {
		if (jpTipoDatos == null) {
			jpTipoDatos = new JPanel();
			jpTipoDatos.setLayout(null);
			jpTipoDatos.setBorder(Utils.crearTituloTabla("Seleccione criterios"));
			jpTipoDatos.setBounds(new Rectangle(15,50,450,180));
			jlUsr = new JLabel();
			jlUsr.setBounds(new Rectangle(10,30,120,20));
			jlUsr.setHorizontalAlignment(SwingConstants.RIGHT);
			jlUsr.setText("Seleccione usuario");
			jlMes = new JLabel();
			jlMes.setBounds(new Rectangle(10,62,120,20));
			jlMes.setHorizontalAlignment(SwingConstants.RIGHT);
			jlMes.setText("Seleccione mes");
			jlAnio = new JLabel();
			jlAnio.setBounds(new Rectangle(10,94,120,20));
			jlAnio.setHorizontalAlignment(SwingConstants.RIGHT);
			jlAnio.setText("(*) Ingrese año");
			JLabel jlLeyenda = new JLabel();
			jlLeyenda.setBounds(new Rectangle(10,110,400,20));
			jlLeyenda.setText("(*) Datos obligatorios");
			jlLeyenda.setHorizontalAlignment(SwingConstants.RIGHT);
			jpTipoDatos.add(jlUsr, null);
			jpTipoDatos.add(jlMes, null);
			jpTipoDatos.add(jlAnio, null);
			jpTipoDatos.add(jlLeyenda, null);
			jpTipoDatos.add(getJCBMeses(), null);
			jpTipoDatos.add(getJTFAnio(), null);
			jpTipoDatos.add(getJBContinuar(), null);
			jpTipoDatos.setBackground(Utils.colorPanel);
		}		
		return jpTipoDatos;
	}

	public JComboBox getJCBMeses() {
		if (jCMeses == null) {
			jCMeses = new JComboBox();
			jCMeses.setBounds(new Rectangle(140,64,70,22));
			jCMeses.setBackground(new Color(255,255,255));
			jCMeses.setForeground(java.awt.Color.black);
			jCMeses.addItem("Ene");
			jCMeses.addItem("Feb");
			jCMeses.addItem("Mzo");
			jCMeses.addItem("Abr");
			jCMeses.addItem("May");
			jCMeses.addItem("Jun");
			jCMeses.addItem("Jul");
			jCMeses.addItem("Ago");
			jCMeses.addItem("Set");
			jCMeses.addItem("Oct");
			jCMeses.addItem("Nov");
			jCMeses.addItem("Dic");
		}
		return jCMeses;
	}
	
	public JTextField getJTFAnio() {
		if (jtfAnio == null) {
			jtfAnio = new JTextField();
			jtfAnio.setBounds(new Rectangle(140,94,70,22));
			jtfAnio.setDocument(new LimitadorNroMax(jtfAnio,4));
		}
		return jtfAnio;
	}
	
	public JButton getJBContinuar() {
		if (jBContinuar == null) {
			jBContinuar = new JButton();
			jBContinuar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBContinuar.setBounds(new Rectangle(250,140,150,25));
			jBContinuar.setText("Visualizar Horas");
			jBContinuar.setInputMap(0, map);
		}
		return jBContinuar;
	}

	public JComboBox getJCBUsuarios(){
		if (JCUsuarios == null) {
			JCUsuarios = new JComboBox();
			for(int i=0;i<usuarios.size();i++){
				String usr=(String)usuarios.elementAt(i);
				JCUsuarios.addItem(usr);
			}
			JCUsuarios.setBackground(new Color(255,255,255));
			JCUsuarios.setForeground(java.awt.Color.black);
			JCUsuarios.setBounds(new Rectangle(140,30,200,22));
		}
		return JCUsuarios;
	}

	public JButton getJBCancelar() {
		if (jBCancelar == null) {
			jBCancelar = new JButton();
			jBCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBCancelar.setBounds(new Rectangle(190,250,100,30));
			jBCancelar.setText("Salir");
			jBCancelar.setInputMap(0, map);
		}
		return jBCancelar;
	}

	public void setActionListeners(ActionListener lis) {
		jBContinuar.addActionListener(lis);
		jBCancelar.addActionListener(lis);
	}
	
	public void actualizarTabla(){
		jpTipoDatos.add(getJCBUsuarios(), null);
	}

}