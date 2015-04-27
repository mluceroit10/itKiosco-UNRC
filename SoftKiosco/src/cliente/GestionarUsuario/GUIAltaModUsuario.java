package cliente.GestionarUsuario;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import cliente.LimitadorNroMax;
import cliente.LimitadorPrecio;

import common.Utils;
import common.DTOs.UsuarioDTO;
import common.constantes.TIPO_USUARIOS;

public class GUIAltaModUsuario extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;               private JPanel jpDatosUsuarios = null;  
	private JButton jbCancelar = null;          private JButton jbAceptar = null;
	private JButton jbLocalidad = null;         
	private JLabel jlNombre = null;		    	private JLabel jlDireccion = null;	          
	private JLabel jlApellido = null;          	private JLabel jlTel = null;
	private JLabel jlDni = null;     	      	private JLabel jlTipoUsr = null;
	private JLabel jlLocalidad = null;    		private JLabel jlNombUsr = null;
	private JLabel jlBecaUsr = null;
	private JTextField jtfNombre = null;		private JTextField jtfApellido = null;
	private JTextField jtfDni = null;			private JTextField jtfTel = null;
	private JTextField jtfLocalidad = null;     private JTextField jtfCalle = null;
	private JTextField jtfNombUsr = null;		private JTextField jtfBecaUsr = null;
	private JComboBox  jcbTipoUsr = null;    
	private UsuarioDTO cte;
	private InputMap map = new InputMap();
	
	public GUIAltaModUsuario(GUIGestionarUsuario guiPadre) {
		super(guiPadre);
		inicializar(guiPadre);
		this.setTitle("Nuevo Usuario");
	}

	public GUIAltaModUsuario(UsuarioDTO c,GUIGestionarUsuario guiPadre) {
		super(guiPadre); 
		this.cte = c;
		inicializar(guiPadre);
		this.setTitle("Modificar Usuario");
	}

	private void inicializar(JDialog guiPadre) {
		this.setSize(506,445);
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setContentPane(getPanelPpal());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	private JPanel getPanelPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.setSize(506, 445);
			jpPpal.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, null));
			jpPpal.setName("Gestión Usuario");
			jpPpal.add(getCancelar(), null);
			jpPpal.add(getAceptar(), null);
			jpPpal.add(getJPDatosPers(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPDatosPers() {
		if (jpDatosUsuarios == null) {
			jpDatosUsuarios = new JPanel();
			jpDatosUsuarios.setLayout(null);
			jpDatosUsuarios.setBorder(Utils.crearTituloTabla("Datos Usuario"));
			jpDatosUsuarios.setBounds(new Rectangle(15,15,470,340));
			jlApellido = new JLabel();
			jlApellido.setBounds(new Rectangle(10,30,120,20));
			jlApellido.setText("(*) Apellido:");
			jlApellido.setHorizontalAlignment(SwingConstants.RIGHT);
			jlNombre = new JLabel();
			jlNombre.setBounds(new Rectangle(10,62,120,20));
			jlNombre.setText("(*) Nombre:");
			jlNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			jlDni = new JLabel();
			jlDni.setBounds(new Rectangle(10,94,120,20));
			jlDni.setText("(*) DNI:");
			jlDni.setHorizontalAlignment(SwingConstants.RIGHT);
			jlTel = new JLabel();
			jlTel.setBounds(new Rectangle(10,126,120,20));
			jlTel.setText("Teléfono:");
			jlTel.setHorizontalAlignment(SwingConstants.RIGHT);
			jlDireccion = new JLabel();
			jlDireccion.setBounds(new Rectangle(10,158,120,20));
			jlDireccion.setText("(*) Dirección:");
			jlDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
			jlLocalidad = new JLabel();
			jlLocalidad.setBounds(new Rectangle(10,190,120,20));
			jlLocalidad.setText("(*) Localidad:");
			jlLocalidad.setHorizontalAlignment(SwingConstants.RIGHT);
			jlTipoUsr = new JLabel();
			jlTipoUsr.setBounds(new Rectangle(10,222,120,20));
			jlTipoUsr.setText("(*) Tipo Usuario:");
			jlTipoUsr.setHorizontalAlignment(SwingConstants.RIGHT);
			jlNombUsr = new JLabel();
			jlNombUsr.setBounds(new Rectangle(10,254,120,20));
			jlNombUsr.setText("(*) Nombre Usuario:");
			jlNombUsr.setHorizontalAlignment(SwingConstants.RIGHT);
			jlBecaUsr = new JLabel();
			jlBecaUsr.setBounds(new Rectangle(10,286,120,20));
			jlBecaUsr.setText("(*) Importe Beca:");
			jlBecaUsr.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel jlLeyenda = new JLabel();
			jlLeyenda.setBounds(new Rectangle(10,317,445,20));
			jlLeyenda.setText("(*) Datos obligatorios");
			jlLeyenda.setHorizontalAlignment(SwingConstants.RIGHT);
			jpDatosUsuarios.add(jlApellido, null);
			jpDatosUsuarios.add(jlNombre, null);
			jpDatosUsuarios.add(jlDni, null);
			jpDatosUsuarios.add(jlTel, null);
			jpDatosUsuarios.add(jlDireccion, null);
			jpDatosUsuarios.add(jlLocalidad, null);
			jpDatosUsuarios.add(jlTipoUsr, null);
			jpDatosUsuarios.add(jlNombUsr, null);
			jpDatosUsuarios.add(jlBecaUsr, null);
			jpDatosUsuarios.add(jlLeyenda, null);
			jpDatosUsuarios.add(getApellido(), null);
			jpDatosUsuarios.add(getNombre(), null);
			jpDatosUsuarios.add(getDni(), null);
			jpDatosUsuarios.add(getTelefono(), null);
			jpDatosUsuarios.add(getDireccion(), null);
			jpDatosUsuarios.add(getLocalidad(), null);
			jpDatosUsuarios.add(getJButtonLocalidad(), null);
			jpDatosUsuarios.add(getJCBTipoUsr(), null);
			jpDatosUsuarios.add(getNombUsr(), null);
			jpDatosUsuarios.add(getBecaUsr(), null);
			if (cte!=null) {
				jtfApellido.setText(cte.getApellido());
				jtfNombre.setText(cte.getNombre());
				jtfDni.setText(String.valueOf(cte.getDni()));
				jtfTel.setText(cte.getTelefono());
				jtfCalle.setText(cte.getDireccion());
				jtfLocalidad.setText(cte.getLocalidad().getNombre());
				jcbTipoUsr.setSelectedItem(cte.getTipoUsuario());
				jtfNombUsr.setText(cte.getNombUsuario());
				jtfBecaUsr.setText(String.valueOf(cte.getImporteBeca()));
			}
			jpDatosUsuarios.setBackground(Utils.colorPanel);
		}
		return jpDatosUsuarios;
	}

	public JTextField getApellido() {
		if (jtfApellido == null) {
			jtfApellido = new JTextField();
			jtfApellido.setBounds(new Rectangle(140,30,320,22));
		}
		return jtfApellido;
	}

	public JTextField getNombre() {
		if (jtfNombre == null) {
			jtfNombre = new JTextField();
			jtfNombre.setBounds(new Rectangle(140,62,320,22));
		}
		return jtfNombre;
	}

	public JTextField getDni() {
		if (jtfDni == null) {
			jtfDni = new JTextField();
			jtfDni.setBounds(new Rectangle(140,94,320,22));
			jtfDni.setDocument(new LimitadorNroMax(jtfDni,8));
		}
		return jtfDni;
	}

	public JTextField getTelefono() {
		if (jtfTel == null) {
			jtfTel = new JTextField();
			jtfTel.setBounds(new Rectangle(140,126,320,22));
		}
		return jtfTel;
	}

	public JTextField getDireccion() {
		if (jtfCalle == null) {
			jtfCalle = new JTextField();
			jtfCalle.setBounds(new Rectangle(140,158,320,22));
		}
		return jtfCalle;
	}

	public JTextField getLocalidad() {
		if (jtfLocalidad == null) {
			jtfLocalidad = new JTextField();
			jtfLocalidad.setBounds(new Rectangle(140,190,220,22));
			jtfLocalidad.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfLocalidad.setEnabled(false);
		}
		return jtfLocalidad;
	}

	public JButton getJButtonLocalidad() {
		if (jbLocalidad == null) {
			jbLocalidad = new JButton();
			jbLocalidad.setBounds(new Rectangle(380,190,80,22));
			jbLocalidad.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbLocalidad.setName("Buscar");
			jbLocalidad.setText("Buscar");
			jbLocalidad.setInputMap(0, map);
		}
		return jbLocalidad;
	}

	public JComboBox getJCBTipoUsr() {
		if (jcbTipoUsr==null){
			jcbTipoUsr=new JComboBox();
			jcbTipoUsr.setBounds(new Rectangle(140,222,320,22));
			jcbTipoUsr.setBackground(new Color(255,255,255));
			jcbTipoUsr.setForeground(java.awt.Color.black);
			jcbTipoUsr.addItem(TIPO_USUARIOS.BECARIO);
			jcbTipoUsr.addItem(TIPO_USUARIOS.ADMINISTRADOR);
			jcbTipoUsr.addItem(TIPO_USUARIOS.BECARIO_ADM);
		}
		return jcbTipoUsr;
	}

	public JTextField getNombUsr() {
		if (jtfNombUsr == null) {
			jtfNombUsr = new JTextField();
			jtfNombUsr.setBounds(new Rectangle(140,254,220,22));
		}
		return jtfNombUsr;
	}

	public JTextField getBecaUsr() {
		if (jtfBecaUsr == null) {
			jtfBecaUsr = new JTextField();
			jtfBecaUsr.setBounds(new Rectangle(140,286,220,22));
			jtfBecaUsr.setDocument(new LimitadorPrecio(jtfBecaUsr));
		}
		return jtfBecaUsr;
	}

	public JButton getAceptar() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setBounds(new Rectangle(140,375,100,30));
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
			jbCancelar.setBounds(new Rectangle(260,375,100,30));
			jbCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCancelar.setName("Cancelar");
			jbCancelar.setText("Cancelar");
			jbCancelar.setInputMap(0, map);
		}
		return jbCancelar;
	}

	public void setActionListeners(ActionListener lis){
		this.jbCancelar.addActionListener(lis);
		this.jbAceptar.addActionListener(lis);
		this.jbLocalidad.addActionListener(lis);
	}

	public void setLocalidad(String string) {
		jtfLocalidad.setText(string);
	}

}
