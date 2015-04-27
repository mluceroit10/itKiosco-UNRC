package cliente.GestionarProveedor;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import cliente.LimitadorNroMax;

import common.Utils;
import common.DTOs.ProveedorDTO;

public class GUIAltaModProveedor extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;               private JPanel jpDatosProveedor = null; 
	private JButton jbCancelar = null;          private JButton jbAceptar = null;
	private JButton jbLocalidad = null;         
	private JLabel jlNombre = null;		    	private JLabel jlDireccion = null;	          
	private JLabel jlCodigo = null;           	private JLabel jlTel = null;
	private JLabel jlLocalidad = null;    
	private JTextField jtfNombre = null;    
	private JTextField jtfCodigo = null;       	private JTextField jtfTel = null;
	private JTextField jtfLocalidad = null;     private JTextField jtfCalle = null;
	private ProveedorDTO prov;
	private Long nroAsignado=new Long(0);
	private InputMap map = new InputMap();
	
	public GUIAltaModProveedor(Long nroCod,GUIGestionarProveedor guiPadre) {
		super(guiPadre);
		nroAsignado=nroCod;
		inicializar(guiPadre);
		this.setTitle("Nuevo Proveedor");
	}

	public GUIAltaModProveedor(ProveedorDTO p,GUIGestionarProveedor guiPadre) {
		super(guiPadre);
		this.prov = p;
		inicializar(guiPadre);
		this.setTitle("Modificar Proveedor");
	}

	private void inicializar(JDialog guiPadre) {
		this.setSize(506,318);
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
			jpPpal.setSize(506, 318);
			jpPpal.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, null));
			jpPpal.setName("Gestión Proveedor");
			jpPpal.add(getCancelar(), null);
			jpPpal.add(getAceptar(), null);
			jpPpal.add(getJPDatosProv(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPDatosProv() {
		if (jpDatosProveedor == null) {
			jpDatosProveedor = new JPanel();
			jpDatosProveedor.setLayout(null);
			jpDatosProveedor.setBorder(Utils.crearTituloTabla("Datos Proveedor"));
			jpDatosProveedor.setBounds(new Rectangle(15,15,470,213));
			jlCodigo = new JLabel();
			jlCodigo.setBounds(new Rectangle(12,30,110,20));
			jlCodigo.setText("(*) Código:");
			jlCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
			jlNombre = new JLabel();
			jlNombre.setBounds(new Rectangle(12,62,110,20));
			jlNombre.setText("(*) Nombre:");
			jlNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			jlTel = new JLabel();
			jlTel.setBounds(new Rectangle(12,94,110,20));
			jlTel.setText("Teléfono:");
			jlTel.setHorizontalAlignment(SwingConstants.RIGHT);
			jlDireccion = new JLabel();
			jlDireccion.setBounds(new Rectangle(12,126,110,20));
			jlDireccion.setText("(*) Dirección:");
			jlDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
			jlLocalidad = new JLabel();
			jlLocalidad.setBounds(new Rectangle(12,158,110,20));
			jlLocalidad.setText("(*) Localidad:");
			jlLocalidad.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel jlLeyenda = new JLabel();
			jlLeyenda.setBounds(new Rectangle(10,190,440,20));
			jlLeyenda.setText("(*) Datos obligatorios");
			jlLeyenda.setHorizontalAlignment(SwingConstants.RIGHT);
			jpDatosProveedor.add(jlCodigo, null);
			jpDatosProveedor.add(jlNombre, null);
			jpDatosProveedor.add(jlTel, null);
			jpDatosProveedor.add(jlDireccion, null);
			jpDatosProveedor.add(jlLocalidad, null);
			jpDatosProveedor.add(jlLeyenda, null);
			jpDatosProveedor.add(getCodigo(), null);
			jpDatosProveedor.add(getNombre(), null);
			jpDatosProveedor.add(getTelefono(), null);
			jpDatosProveedor.add(getDireccion(), null);
			jpDatosProveedor.add(getLocalidad(), null);
			jpDatosProveedor.add(getJButtonLocalidad(), null);
			if (prov!=null) {
				jtfNombre.setText(prov.getNombre());
				jtfCodigo.setText(String.valueOf(prov.getCodigo()));
				jtfTel.setText(prov.getTelefono());
				jtfCalle.setText(prov.getDireccion());
				jtfLocalidad.setText(prov.getLocalidad().getNombre());
			}
			jpDatosProveedor.setBackground(Utils.colorPanel);
		}
		return jpDatosProveedor;
	}

	public JTextField getCodigo() {
		if (jtfCodigo == null) {
			jtfCodigo = new JTextField();
			jtfCodigo.setBounds(new Rectangle(130,30,330,22));
			jtfCodigo.setDocument(new LimitadorNroMax(jtfCodigo,6));
			jtfCodigo.setText(String.valueOf(nroAsignado));
		}
		return jtfCodigo;
	}

	public JTextField getNombre() {
		if (jtfNombre == null) {
			jtfNombre = new JTextField();
			jtfNombre.setBounds(new Rectangle(130,62,330,22));
		}
		return jtfNombre;
	}
	
	public JTextField getTelefono() {
		if (jtfTel == null) {
			jtfTel = new JTextField();
			jtfTel.setBounds(new Rectangle(130,94,330,22));
		}
		return jtfTel;
	}

	public JTextField getDireccion() {
		if (jtfCalle == null) {
			jtfCalle = new JTextField();
			jtfCalle.setBounds(new Rectangle(130,126,330,22));
		}
		return jtfCalle;
	}

	public JTextField getLocalidad() {
		if (jtfLocalidad == null) {
			jtfLocalidad = new JTextField();
			jtfLocalidad.setBounds(new Rectangle(130,158,230,22));
			jtfLocalidad.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfLocalidad.setEnabled(false);
		}
		return jtfLocalidad;
	}

	public JButton getJButtonLocalidad() {
		if (jbLocalidad == null) {
			jbLocalidad = new JButton();
			jbLocalidad.setBounds(new Rectangle(380,158,80,22));
			jbLocalidad.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbLocalidad.setName("Buscar");
			jbLocalidad.setText("Buscar");
			jbLocalidad.setInputMap(0, map);
		}
		return jbLocalidad;
	}
	
	public JButton getAceptar() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setBounds(new Rectangle(140,248,100,30));
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
			jbCancelar.setBounds(new Rectangle(260,248,100,30));
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
