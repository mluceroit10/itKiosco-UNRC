package cliente.ListarFacturasCliente;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import cliente.LimitadorNroBarra;
import cliente.LimitadorNroMax;
import cliente.ModeloTabla;

import common.Utils;

public class GUIListarFacturasCliente extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;			   
	private JPanel jpDatos = null;			    private JPanel jpBuscador = null;
	private JButton jbImprimir = null;			private JButton jbSalir = null;			
	private JLabel fecha = null;				private JLabel nro = null; 
	private JLabel becario = null;
	private JTextField jtfFecha = null;			private JTextField jtfNro = null;
	private JTextField jtfVendedor = null;
	public JScrollPane jspDatos = null;
	public JTable jtDatos = null;				private ModeloTabla modTabla = null;
	public String[] titulos = {"Id","Fecha y Hora","Nro de Factura","A cuenta?","Del Usuario","Importe Total"};
	public Object[][] datos;
	private InputMap map = new InputMap();
	private int mesLI;
	private int anioLI;
	private JLabel jlPeriodo= null;			    private JTextField jtfPeriodo = null;
	private JButton jbCambiarPeriodo= null;		
    private JLabel jlMes = null;				private JComboBox jcbMes;
	private JLabel jlAnio = null;				private JTextField jtfAnio;

	public GUIListarFacturasCliente(int mes,int anio,JFrame guiPadre) {
		super(guiPadre);
		mesLI=mes;
		anioLI=anio;
		datos = new Object[0][titulos.length];
		this.setSize(new java.awt.Dimension(736,540));
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Facturas Cliente Existentes");
		this.setContentPane(getJPPpal());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	public JPanel getJPPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.add(getJBImprimir(), null);
			jpPpal.add(getJBSalir(), null);
			jpPpal.add(getJPDatos(), null);
			jpPpal.add(getJPBuscador(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPBuscador() {
		if (jpBuscador == null) {
			JLabel jlForm = new JLabel();
			jlForm.setBounds(new Rectangle(80,45,80,9));
			jlForm.setText("(dd/mm/aaaa)");
			jpBuscador = new JPanel();
			jpBuscador.setLayout(null);
			jpBuscador.setBorder(Utils.crearTituloTabla("Buscar"));
			jpBuscador.setBounds(new Rectangle(15,15,700,60));
			fecha = new JLabel("Fecha:");
			fecha.setHorizontalAlignment(JLabel.RIGHT);
			fecha.setBounds(new Rectangle(24,22,40,20));
			nro = new JLabel("Nro de Factura:");
			nro.setHorizontalAlignment(JLabel.RIGHT);
			nro.setBounds(new Rectangle(180,22,100,20));
			becario = new JLabel("Cuenta de usuario:");
			becario.setHorizontalAlignment(JLabel.RIGHT);
			becario.setBounds(new Rectangle(400,22,120,20));
			jpBuscador.add(jlForm, null);
			jpBuscador.add(fecha, null);
			jpBuscador.add(nro, null);
			jpBuscador.add(becario, null);
			jpBuscador.add(getJTFFecha(), null);
			jpBuscador.add(getJTFNro(), null);
			jpBuscador.add(getJTFVendedor(), null);
			jpBuscador.setBackground(Utils.colorPanel);
		}
		return jpBuscador;
	}

	public JTextField getJTFFecha() {
		if (jtfFecha == null) {
			jtfFecha = new JTextField();
			jtfFecha.setBounds(new Rectangle(80,22,80,22));
			jtfFecha.setDocument(new LimitadorNroBarra(jtfFecha));
		}
		return jtfFecha;
	}

	public JTextField getJTFNro() {
		if (jtfNro == null) {
			jtfNro = new JTextField();
			jtfNro.setBounds(new Rectangle(290,22,80,22));
			jtfNro.setDocument(new LimitadorNroMax(jtfNro,12));
		}
		return jtfNro;
	}

	public JTextField getJTFVendedor() {
		if (jtfVendedor == null) {
			jtfVendedor = new JTextField();
			jtfVendedor.setBounds(new Rectangle(530,22,140,22));
		}
		return jtfVendedor;
	}

	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBounds(new Rectangle(15,90,700,360));
			jpDatos.setBorder(Utils.crearTituloTabla("Facturas Cliente"));
			jpDatos.add(getJSPDatos(), null);
			agregarPeriodoSelec();
			jpDatos.setBackground(Utils.colorPanel);
		}
		return jpDatos;
	}

	private JScrollPane getJSPDatos() {
		if (jspDatos == null) {
			jspDatos = new JScrollPane();
			jspDatos.setBounds(new Rectangle(10,50,680,290));
			jspDatos.setViewportView(getJTDatos());
		}
		return jspDatos;
	}

	public JTable getJTDatos() {
		if (jtDatos == null) {
			modTabla = new ModeloTabla(titulos, datos);
			jtDatos = new JTable(modTabla);
			Utils.ocultarColumnaId(jtDatos);
			TableColumn columna1 = jtDatos.getColumn("Fecha y Hora");
			columna1.setMaxWidth(110); 
			columna1.setPreferredWidth(110);
			TableColumn columna2 = jtDatos.getColumn("A cuenta?");
			columna2.setMaxWidth(60);
			columna2.setPreferredWidth(60);
			TableColumn columna3 = jtDatos.getColumn("Nro de Factura");
			columna3.setPreferredWidth(100); 
			columna3.setMaxWidth(100);
			TableColumn columna4 = jtDatos.getColumn("Importe Total");
			columna4.setMaxWidth(80); 
			columna4.setCellRenderer(Utils.alinearDerecha());
		}
		return jtDatos;
	}
	
	public JButton getJBImprimir() {
		if (jbImprimir == null) {
			jbImprimir = new JButton();
			jbImprimir.setBounds(new Rectangle(415,470,100,30));
			jbImprimir.setText("Imprimir");
			jbImprimir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbImprimir.setInputMap(0, map);
		}
		return jbImprimir;
	}

	public JButton getJBSalir() {
		if (jbSalir  == null) {
			jbSalir = new JButton();
			jbSalir.setBounds(new Rectangle(215,470,100,30));
			jbSalir.setText("Aceptar");
			jbSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbSalir.setInputMap(0, map);
		}
		return jbSalir;
	}

	public void setActionListeners(ActionListener lis) {
		jbImprimir.addActionListener(lis);
		jbSalir.addActionListener(lis);
		jbCambiarPeriodo.addActionListener(lis);
	}

	public void setKeyListener(KeyListener lis) {
		jtfFecha.addKeyListener(lis);
		jtfNro.addKeyListener(lis);
		jtfVendedor.addKeyListener(lis);
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

	public JButton getJBCambiarPeriodo() {
		if (jbCambiarPeriodo == null) {
			jbCambiarPeriodo = new JButton();
			jbCambiarPeriodo.setBounds(new java.awt.Rectangle(450,20,115,20));
			jbCambiarPeriodo.setText("Cambiar Período");
			jbCambiarPeriodo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCambiarPeriodo.setInputMap(0, map);
			
		}
		return jbCambiarPeriodo;
	}
    
    public JTextField getJTFPeriodo() {
		if (jtfPeriodo == null) {
			jtfPeriodo = new JTextField();
			jtfPeriodo.setBounds(new java.awt.Rectangle(85,20,70,20));
			jtfPeriodo.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfPeriodo.setEnabled(false);
		}
		return jtfPeriodo;
	}
    
    public JComboBox getJCBMes() {
		if (jcbMes == null) {
			jcbMes = new JComboBox();
			jcbMes.setBounds(new java.awt.Rectangle(255,20,60,20));
			jcbMes.setBackground(new Color(255,255,255));
			jcbMes.setForeground(java.awt.Color.black);
			jcbMes.addItem("01");
			jcbMes.addItem("02");
			jcbMes.addItem("03");
			jcbMes.addItem("04");
			jcbMes.addItem("05");
			jcbMes.addItem("06");
			jcbMes.addItem("07");
			jcbMes.addItem("08");
			jcbMes.addItem("09");
			jcbMes.addItem("10");
			jcbMes.addItem("11");
			jcbMes.addItem("12");
			jcbMes.setSelectedIndex(mesLI-1);
		}
		return jcbMes;
	}
	
	public JTextField getJTFAnio() {
		if (jtfAnio == null) {
			jtfAnio = new JTextField();
			jtfAnio.setBounds(new java.awt.Rectangle(370,20,60,20));
			jtfAnio.setDocument(new LimitadorNroMax(jtfAnio,4));
			jtfAnio.setText(String.valueOf(anioLI));
		}
		return jtfAnio;
	}
	
	public void agregarPeriodoSelec(){
		jlPeriodo = new JLabel("Período:");
		jlPeriodo.setHorizontalAlignment(JLabel.RIGHT);
		jlPeriodo.setBounds(new Rectangle(20,20,60,20));
		jlMes = new JLabel("Mes:");
		jlMes.setBounds(new Rectangle(200,20,50,20));
		jlMes.setHorizontalAlignment(SwingConstants.RIGHT);
		jlAnio = new JLabel("Año:");
		jlAnio.setBounds(new Rectangle(325,20,40,20));
		jlAnio.setHorizontalAlignment(SwingConstants.RIGHT);
		jpDatos.add(jlPeriodo);
		jpDatos.add(jlMes);
		jpDatos.add(jlAnio);
		jpDatos.add(getJTFPeriodo());
		jpDatos.add(getJCBMes());
		jpDatos.add(getJTFAnio());
		jpDatos.add(getJBCambiarPeriodo());
	}

}

