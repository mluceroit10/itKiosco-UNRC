package cliente.GestionarLocalidad;

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

import common.Utils;
import common.DTOs.LocalidadDTO;

public class GUIAltaModLocalidad extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;			    private JPanel jpDatos = null;
	private JButton jbAceptar = null;		    private JButton jbCancelar = null;
	private JButton jbProvincia = null;
	private JLabel jlNombre = null;			    private JLabel jlCodPostal = null;
	private JLabel jlProvincia = null;
	private JTextField jtfNombre = null;	    private JTextField jtfCodPostal = null;
	private JTextField jtfProvincia = null;
	private LocalidadDTO loc = null;
	private InputMap map = new InputMap();

	public GUIAltaModLocalidad(GUIGestionarLocalidad guiPadre) {
		super(guiPadre); 
		this.setSize(new java.awt.Dimension(336,255));
		this.setTitle("Nueva Localidad");
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setContentPane(getJPPpal());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	public GUIAltaModLocalidad(LocalidadDTO p,GUIGestionarLocalidad guiPadre) {
		super(guiPadre); 
		this.loc = p;
		this.setSize(new java.awt.Dimension(336,255));
		this.setTitle("Modificar Localidad");
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
			jpPpal.add(getJPDatos(),null);
			jpPpal.add(getJBAceptar(), null);
			jpPpal.add(getJBCancelar(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jlNombre = new JLabel("(*) Nombre:");
			jlNombre.setBounds(new Rectangle(10,30,85,20));
			jlNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			jlCodPostal = new JLabel("Cod. Postal:");
			jlCodPostal.setBounds(new Rectangle(10,62,85,20));
			jlCodPostal.setHorizontalAlignment(SwingConstants.RIGHT);
			jlProvincia = new JLabel("(*) Provincia:");
			jlProvincia.setBounds(new Rectangle(10,94,85,20));
			jlProvincia.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel jlLeyenda = new JLabel();
			jlLeyenda.setBounds(new Rectangle(12,126,275,20));
			jlLeyenda.setText("(*) Datos obligatorios");
			jlLeyenda.setHorizontalAlignment(SwingConstants.RIGHT);
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setSize(new java.awt.Dimension(300,130));
			jpDatos.setBorder(Utils.crearTituloTabla("Datos de la Localidad"));
			jpDatos.setBounds(new Rectangle(15,15,300,150));
			jpDatos.add(jlNombre, null);
			jpDatos.add(jlCodPostal, null);
			jpDatos.add(jlProvincia, null);
			jpDatos.add(jlLeyenda, null);
			jpDatos.add(getJTFNombre(), null);
			jpDatos.add(getJTFCodPostal(), null);
			jpDatos.add(getJTFProvincia(), null);
			jpDatos.add(getJBProvincia(), null);
			if (loc!=null) {
				jtfNombre.setText(loc.getNombre());
				jtfCodPostal.setText(String.valueOf(loc.getCodPostal()));
				jtfProvincia.setText(loc.getProvincia().getNombre());
			}
			jpDatos.setBackground(Utils.colorPanel);
		}
		return jpDatos;
	}

	public JTextField getJTFNombre() {
		if (jtfNombre == null) {
			jtfNombre = new JTextField();
			jtfNombre.setBounds(new Rectangle(105,30,180,22));
		}
		return jtfNombre;
	}

	public JTextField getJTFCodPostal() {
		if (jtfCodPostal == null) {
			jtfCodPostal = new JTextField();
			jtfCodPostal.setBounds(new Rectangle(105,62,180,22));
		}
		return jtfCodPostal;
	}

	public JTextField getJTFProvincia() {
		if (jtfProvincia == null) {
			jtfProvincia = new JTextField();
			jtfProvincia.setBounds(new Rectangle(105,94,95,22));
			jtfProvincia.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfProvincia.setEnabled(false);
		}
		return jtfProvincia;
	}

	public JButton getJBProvincia() {
		if (jbProvincia == null) {
			jbProvincia = new JButton();
			jbProvincia.setBounds(new Rectangle(205,94,80,22));
			jbProvincia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbProvincia.setName("Buscar");
			jbProvincia.setText("Buscar");
			jbProvincia.setInputMap(0, map);
		}
		return jbProvincia;
	}

	public JButton getJBAceptar() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setBounds(new Rectangle(55,185,100,30));
			jbAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbAceptar.setText("Aceptar");
			jbAceptar.setInputMap(0, map);
		}
		return jbAceptar;
	}

	public JButton getJBCancelar() {
		if (jbCancelar == null) {
			jbCancelar = new JButton();
			jbCancelar.setBounds(new Rectangle(175,185,100,30));
			jbCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCancelar.setText("Cancelar");
			jbCancelar.setInputMap(0, map);
		}
		return jbCancelar;
	}

	public void setActionListeners(ActionListener lis) {
		jbAceptar.addActionListener(lis);
		jbCancelar.addActionListener(lis);
		jbProvincia.addActionListener(lis);
	}

	public void setProvincia(String string) {
		jtfProvincia.setText(string);
	}
	
}