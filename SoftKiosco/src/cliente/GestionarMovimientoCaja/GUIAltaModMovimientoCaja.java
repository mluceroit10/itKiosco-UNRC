package cliente.GestionarMovimientoCaja;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

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

import com.toedter.calendar.JDateChooser;
import common.Utils;
import common.constantes.TIPO_CAJA;

public class GUIAltaModMovimientoCaja extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;			    private JPanel jpDatos = null;  
	private JButton jbAceptar = null;	    	private JButton jbCancelar = null;
	private JButton jbFactura = null;
	private JLabel jlNroRecibo = null;		    private JLabel jlFecha = null;
	private JLabel jlTipoMov = null;			private JLabel jlImporte = null;
	private JLabel jlDescr = null;				private JLabel jlTipoCajaReg = null;		
	private JLabel jlFactura = null;			private JLabel jlTipoFact = null;
	private JTextField jtfNroRecibo = null;	    private JTextField jtfImporte = null;
	private JTextField jtfDescr = null;		    private JTextField jtfFactura = null;
	private JComboBox jcbTipoMovim = null;    	private JComboBox jcbConFact = null;
	private JComboBox jcbTipoCajaReg = null;
	private JDateChooser jDateChooserFecha = null;
	public Vector servicios;					public Vector cursos;
	public Long nroAsignado=new Long(0);
	private InputMap map = new InputMap();
	
	public GUIAltaModMovimientoCaja(JDialog guiPadre,Long nroCod) {
		super(guiPadre);
		nroAsignado=nroCod;
		this.setSize(new java.awt.Dimension(636,305));
		this.setTitle("Nuevo Movimiento de Caja");
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
			jpPpal.setSize(new java.awt.Dimension(636,305));
			jpPpal.add(getJPDatos(),null);
			jpPpal.add(getJBAceptar(), null);
			jpPpal.add(getJBCancelar(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	public JPanel getJPDatos() {
		if (jpDatos == null) {
			jlNroRecibo = new JLabel("(*) Nro. Recibo:");
			jlNroRecibo.setBounds(new Rectangle(10,30,150,20));
			jlNroRecibo.setHorizontalAlignment(SwingConstants.RIGHT);
			jlFecha = new JLabel();
			jlFecha.setBounds(new Rectangle(310,30,95,20));
			jlFecha.setText("(*) Fecha:");
			jlFecha.setHorizontalAlignment(SwingConstants.RIGHT);
			jlTipoMov = new JLabel();
			jlTipoMov.setBounds(new Rectangle(10,62,150,20));
			jlTipoMov.setText("(*) Tipo de Movimiento:");
			jlTipoMov.setHorizontalAlignment(SwingConstants.RIGHT);
			jlImporte = new JLabel();
			jlImporte.setBounds(new Rectangle(310,62,95,20));
			jlImporte.setText("(*) Importe:");
			jlImporte.setHorizontalAlignment(SwingConstants.RIGHT);
			jlDescr = new JLabel();
			jlDescr.setBounds(new Rectangle(10,94,150,20));
			jlDescr.setText("(*) Descripción:");
			jlDescr.setHorizontalAlignment(SwingConstants.RIGHT);
			jlFactura = new JLabel();
			jlFactura.setBounds(new Rectangle(10,126,150,20));
			jlFactura.setText("Pago a Proveedor?");
			jlFactura.setHorizontalAlignment(SwingConstants.RIGHT);
			jlTipoFact = new JLabel();
			jlTipoFact.setBounds(new Rectangle(240,126,100,20));
			jlTipoFact.setText("Factura Nro:");
			jlTipoFact.setHorizontalAlignment(SwingConstants.RIGHT);
			jlTipoCajaReg  = new JLabel();
			jlTipoCajaReg.setBounds(new Rectangle(10,158,150,20));
			jlTipoCajaReg.setText("Registrar movimiento en:");
			jlTipoCajaReg.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel jlLeyenda = new JLabel();
			jlLeyenda.setBounds(new Rectangle(300,175,285,20));
			jlLeyenda.setText("(*) Datos obligatorios");
			jlLeyenda.setHorizontalAlignment(SwingConstants.RIGHT);
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBorder(Utils.crearTituloTabla("Datos del Movimiento de Caja"));
			jpDatos.setBounds(new Rectangle(15,15,600,200));
			jpDatos.add(jlNroRecibo, null);
			jpDatos.add(jlFecha, null);
			jpDatos.add(jlTipoMov, null);
			jpDatos.add(jlImporte, null);
			jpDatos.add(jlDescr, null);
			jpDatos.add(jlFactura, null);
			jpDatos.add(jlTipoFact, null);
			jpDatos.add(jlTipoCajaReg, null);
			jpDatos.add(jlLeyenda, null);
			jpDatos.add(getJTFCodigo(), null);
			jpDatos.add(getJDateChooserFecha(), null);
			jpDatos.add(getJCTipoMov(), null);
			jpDatos.add(getJTFImporte(), null);
			jpDatos.add(getJTFDescr(), null);
			jpDatos.add(getJCConFact(), null);
			jpDatos.add(getJTFFactura(), null);
			jpDatos.add(getJBBuscarFact(), null);
			jpDatos.add(getJCTipoCajaRegistrado(), null);
			jpDatos.setBackground(Utils.colorPanel);
		}
		return jpDatos;
	}

	public JTextField getJTFCodigo() {
		if (jtfNroRecibo == null) {
			jtfNroRecibo = new JTextField();
			jtfNroRecibo.setBounds(new Rectangle(170,30,150,22));
			jtfNroRecibo.setDocument(new LimitadorNroMax(jtfNroRecibo,9));
			jtfNroRecibo.setText(String.valueOf(nroAsignado));
		}
		return jtfNroRecibo;
	}

	public JDateChooser getJDateChooserFecha() {
		if (jDateChooserFecha == null) {
			jDateChooserFecha = new JDateChooser("dd - MMMMM - yyyy HH:mm",false);
			jDateChooserFecha.setBounds(new Rectangle(415,30,170,22));
		}
		return jDateChooserFecha;
	}
	
	public JComboBox getJCTipoMov() {
		if (jcbTipoMovim==null){
			jcbTipoMovim=new JComboBox();
			jcbTipoMovim.setBounds(new Rectangle(170,62,150,22));
			jcbTipoMovim.setBackground(new Color(255,255,255));
			jcbTipoMovim.setForeground(java.awt.Color.black);
			jcbTipoMovim.addItem("ENTRADA");
			jcbTipoMovim.addItem("SALIDA");

		}
		return jcbTipoMovim;
	}

	public JTextField getJTFImporte() {
		if (jtfImporte == null) {
			jtfImporte = new JTextField();
			jtfImporte.setBounds(new Rectangle(415,62,170,22));
			jtfImporte.setDocument(new LimitadorPrecio(jtfImporte));
		}
		return jtfImporte;
	}

	public JTextField getJTFDescr() {
		if (jtfDescr == null) {
			jtfDescr = new JTextField();
			jtfDescr.setBounds(new Rectangle(170,94,415,22));
			jtfDescr.setBackground(new Color(255,255,255));
			jtfDescr.setForeground(java.awt.Color.black);
		}
		return jtfDescr;
	}

	public JComboBox getJCConFact() {
		if (jcbConFact==null){
			jcbConFact=new JComboBox();
			jcbConFact.setBounds(new Rectangle(170,126,70,22));
			jcbConFact.setBackground(new Color(255,255,255));
			jcbConFact.setForeground(java.awt.Color.black);
			jcbConFact.addItem("NO");
			jcbConFact.addItem("SI");

		}
		return jcbConFact;
	}
	
	public JTextField getJTFFactura() {
		if (jtfFactura == null) {
			jtfFactura = new JTextField();
			jtfFactura.setBounds(new Rectangle(350,126,140,22));
			jtfFactura.setBackground(new Color(255,255,255));
			jtfFactura.setForeground(java.awt.Color.black);
			jtfFactura.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfFactura.setEnabled(false);

		}
		return jtfFactura;
	}

	public JButton getJBBuscarFact() {
		if (jbFactura == null) {
			jbFactura = new JButton();
			jbFactura.setBounds(new Rectangle(505,126,80,22));
			jbFactura.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbFactura.setText("Buscar");
			jbFactura.setEnabled(false);
			jbFactura.setInputMap(0, map);
		}
		return jbFactura;
	}

	public JComboBox getJCTipoCajaRegistrado() {
		if (jcbTipoCajaReg==null){
			jcbTipoCajaReg=new JComboBox();
			jcbTipoCajaReg.setBounds(new Rectangle(170,158,100,22));
			jcbTipoCajaReg.setBackground(new Color(255,255,255));
			jcbTipoCajaReg.setForeground(java.awt.Color.black);
			jcbTipoCajaReg.addItem(TIPO_CAJA.GENERAL);
			jcbTipoCajaReg.addItem(TIPO_CAJA.DIARIA);

		}
		return jcbTipoCajaReg;
	}

	public JButton getJBAceptar() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setBounds(new Rectangle(205,235,100,30));
			jbAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbAceptar.setText("Aceptar");
			jbAceptar.setInputMap(0, map);
		}
		return jbAceptar;
	}

	public JButton getJBCancelar() {
		if (jbCancelar == null) {
			jbCancelar = new JButton();
			jbCancelar.setBounds(new Rectangle(325,235,100,30));
			jbCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCancelar.setText("Cancelar");
			jbCancelar.setInputMap(0, map);
		}
		return jbCancelar;
	}

	public void setActionListeners(ActionListener lis) {
		jbAceptar.addActionListener(lis);
		jbCancelar.addActionListener(lis);
		jbFactura.addActionListener(lis);
		jcbConFact.addActionListener(lis);
	}

	public void setFactura(String string) {
		jtfFactura.setText(string);
	}

}