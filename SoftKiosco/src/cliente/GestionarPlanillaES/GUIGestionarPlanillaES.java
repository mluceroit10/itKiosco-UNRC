package cliente.GestionarPlanillaES;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import cliente.LimitadorNroBarra;
import cliente.LimitadorNroMax;
import cliente.LimitadorPrecio;
import cliente.ModeloTabla;

import com.toedter.calendar.JDateChooser;
import common.Utils;

public class GUIGestionarPlanillaES extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;				private JPanel jpDatosNP = null;			   
	private JPanel jpDatos = null;			    private JPanel jpBuscador = null;
	private JPanel jpGestion = null;
	private JButton jbBorrar = null;			private JButton jbCargar = null;
	private JButton jbSalir = null;				private JButton jbAceptar = null;	
	private JLabel fecha = null;				private JLabel nro = null;
	private JLabel jlFecha = null;
	private JTextField jtfFecha = null;			private JTextField jtfNro= null;
	private JTextField jtfImporteInic = null;
	private JComboBox combo;
	private JDateChooser jDateChooserFecha;
	public JScrollPane jspDatos = null;
	public JTable jtDatos = null;				private ModeloTabla modTabla = null;
	public String[] titulos = {"Id","Nro. de Cierre de caja","Fecha","Importe en Caja"};
	public Object[][] datos;
	private JLabel jlFechaMov=null;
	private JLabel jlImporteInicial=null;
	private InputMap map = new InputMap();
	private int mesLI;
	private int anioLI;
	private JLabel jlPeriodo= null;			    private JTextField jtfPeriodo = null;
	private JButton jbCambiarPeriodo= null;		
    private JLabel jlMes = null;				private JComboBox jcbMes;
	private JLabel jlAnio = null;				private JTextField jtfAnio;
	private JLabel jlRetiro = null;
	private JLabel jlEnCaja =null;
	private JTextField jtfImporteRetiro;
	private JTextField jtfImporteEnCaja;

	public GUIGestionarPlanillaES(int mes,int anio,JFrame guiPadre) {
		super(guiPadre);
		mesLI=mes;
		anioLI=anio;
		datos = new Object[0][titulos.length];
		this.setSize(new java.awt.Dimension(761,540));
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Cierres de caja existentes");
		this.setContentPane(getJPPpal());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	public JPanel getJPPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.add(getJPDatos(), null);
			jpPpal.add(getJPBuscador(), null);
			jpPpal.add(getJPGestion(), null);
			jpPpal.add(getJBSalir(), null);
			jpPpal.setBackground(Utils.colorFondo);

		}
		return jpPpal;
	}

	private JPanel getJPGestion() {
		if (jpGestion == null) {
			jpGestion = new JPanel();
			jpGestion.setLayout(null);
			jpGestion.setBounds(new Rectangle(15,15,725,140));
			jpGestion.setBorder(Utils.crearTituloTabla("Gestión"));
			jpGestion.add(getJBImprimir(), null);
			jpGestion.add(getJBBorrar(), null);
			jpGestion.add(getJPDatosNuevaPlanilla(),null);
			jpGestion.setBackground(Utils.colorPanel);
		}
		return jpGestion;
	}

	public JButton getJBImprimir() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setText("Imprimir");
			jbAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbAceptar.setBounds(new Rectangle(20,85,100,25));//new Rectangle(210,30,100,25));
			jbAceptar.setInputMap(0, map);
		}
		return jbAceptar;
	}

	public JButton getJBBorrar() {
		if (jbBorrar == null) {
			jbBorrar = new JButton();
			jbBorrar.setText("Eliminar");
			jbBorrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbBorrar.setBounds(new Rectangle(20,35,100,25));//(new Rectangle(50,30,100,25));
			jbBorrar.setInputMap(0, map);
		}
		return jbBorrar;
	}
	
	private JPanel getJPDatosNuevaPlanilla() {
		if (jpDatosNP == null) {
			jlFecha = new JLabel();
			jlFecha.setBounds(new Rectangle(10,25,100,18));
			jlFecha.setText("(*) Fecha hasta:");
			jlFecha.setHorizontalAlignment(SwingConstants.RIGHT);
			jlImporteInicial = new JLabel();
			jlImporteInicial.setBounds(new Rectangle(10,55,100,18));
			jlImporteInicial.setText("(*) Importe inicial:");
			jlImporteInicial.setHorizontalAlignment(SwingConstants.RIGHT);
			jlRetiro = new JLabel();
			jlRetiro.setBounds(new Rectangle(300,25,100,18));
			jlRetiro.setText("(*) Retiro:");
			jlRetiro.setHorizontalAlignment(SwingConstants.RIGHT);
			jlEnCaja = new JLabel();
			jlEnCaja.setBounds(new Rectangle(300,55,100,18));
			jlEnCaja.setText("(*) En Caja:");
			jlEnCaja.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel jlLeyenda = new JLabel();
			jlLeyenda.setBounds(new Rectangle(410,82,150,18));
			jlLeyenda.setText("(*) Datos obligatorios");
			jlLeyenda.setHorizontalAlignment(SwingConstants.RIGHT);
			jpDatosNP = new JPanel();
			jpDatosNP.setLayout(null);
			Border b1= javax.swing.BorderFactory.createLineBorder(new Color(0,0,205),1);
			TitledBorder titulo =BorderFactory.createTitledBorder(b1,"Nuevo cierre de caja - Ingrese datos",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, 
					new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.black);
			jpDatosNP.setBorder(titulo);
			jpDatosNP.setBounds(new Rectangle(135,15,570,114));
			jpDatosNP.add(jlFecha, null);
			jpDatosNP.add(jlImporteInicial, null);
			jpDatosNP.add(jlRetiro, null);
			jpDatosNP.add(jlEnCaja, null);
			jpDatosNP.add(jlLeyenda, null);
			jpDatosNP.add(getJDateChooserFecha(), null);
			jpDatosNP.add(getJTFImporteInicial(), null);
			jpDatosNP.add(getJTFImporteRetiro(), null);
			jpDatosNP.add(getJTFImporteEnCaja(), null);
			jpDatosNP.add(getJBCargar(), null);
			jpDatosNP.setBackground(Utils.colorPanel);
		}
		return jpDatosNP;
	}

	public JDateChooser getJDateChooserFecha() {
		if (jDateChooserFecha == null) {
			jDateChooserFecha = new JDateChooser("dd - MMMMM - yyyy HH:mm",false);
			jDateChooserFecha.setBounds(new Rectangle(120,25,200,18));
		}
		return jDateChooserFecha;
	}

	public JTextField getJTFImporteInicial() {
		if (jtfImporteInic == null) {
			jtfImporteInic = new JTextField();
			jtfImporteInic.setBounds(new Rectangle(120,55,165,18));
			jtfImporteInic.setDocument(new LimitadorPrecio(jtfImporteInic));
		}
		return jtfImporteInic;
	}
	
	public JTextField getJTFImporteRetiro() {
		if (jtfImporteRetiro == null) {
			jtfImporteRetiro = new JTextField();
			jtfImporteRetiro.setBounds(new Rectangle(410,25,130,18));
			jtfImporteRetiro.setDocument(new LimitadorPrecio(jtfImporteRetiro));
		}
		return jtfImporteRetiro;
	}
	
	public JTextField getJTFImporteEnCaja() {
		if (jtfImporteEnCaja == null) {
			jtfImporteEnCaja = new JTextField();
			jtfImporteEnCaja.setBounds(new Rectangle(410,55,130,18));
			jtfImporteEnCaja.setDocument(new LimitadorPrecio(jtfImporteEnCaja));
		}
		return jtfImporteEnCaja;
	}

	public JButton getJBCargar() {
		if (jbCargar == null) {
			jbCargar = new JButton();
			jbCargar.setText("Ingresar");
			jbCargar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCargar.setBounds(new Rectangle(210,82,100,25));
			jbCargar.setInputMap(0, map);
		}
		return jbCargar;
	}
	
	private JPanel getJPBuscador() {
		if (jpBuscador == null) {
			jpBuscador = new JPanel();
			jpBuscador.setLayout(null);
			jpBuscador.setBorder(Utils.crearTituloTabla("Buscar"));
			jpBuscador.setBounds(new Rectangle(15,170,130,90));
			jpBuscador.add(getJCBDatos(), null);
			if (((String)combo.getSelectedItem()).compareTo("Nro Planilla")==0) {
				jpBuscador.add(getJTFNro(), null);
			}
			if (((String)combo.getSelectedItem()).compareTo("Fecha")==0) {
				jpBuscador.add(getJTFFecha(), null);
				jpBuscador.add(getJLFechaFormato(), null);
				getJTFNro();
			}
			jpBuscador.setBackground(Utils.colorPanel);
		}
		return jpBuscador;
	}

	private JLabel getJLFechaFormato() {
		if (jlFechaMov == null) {
			jlFechaMov = new JLabel("(dd/mm/aaaa)");
			jlFechaMov.setBounds(new Rectangle(10,75,90,9));
		}
		return jlFechaMov;
	}

	public JTextField getJTFFecha() {
		if (jtfFecha == null) {
			jtfFecha = new JTextField();
			jtfFecha.setBounds(new Rectangle(10,50,100,22));
			jtfFecha.setDocument(new LimitadorNroBarra(jtfFecha));
		}
		return jtfFecha;
	}

	public JTextField getJTFNro() {
		if (jtfNro == null) {
			jtfNro = new JTextField();
			jtfNro.setBounds(new Rectangle(10,50,100,22));
			jtfNro.setDocument(new LimitadorNroMax(jtfNro));
		}
		return jtfNro;
	}

	private JComboBox getJCBDatos() {
		if (combo == null) {
			combo = new JComboBox();
			combo.setBounds(new Rectangle(10,22,100,22));
			combo.addItem("Fecha");
			combo.addItem("Nro Planilla");
			combo.setName("combo");
		}
		return combo;
	}

	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBounds(new Rectangle(160,170,580,280));
			jpDatos.setBorder(Utils.crearTituloTabla("Cierres de caja"));
			jpDatos.add(getJSPDatos(), null);
			agregarPeriodoSelec();
			jpDatos.setBackground(Utils.colorPanel);
		}
		return jpDatos;
	}

	private JScrollPane getJSPDatos() {
		if (jspDatos == null) {
			jspDatos = new JScrollPane();
			jspDatos.setBounds(new Rectangle(10,50,560,220));
			jspDatos.setViewportView(getJTDatos());
		}
		return jspDatos;
	}

	public JTable getJTDatos() {
		if (jtDatos == null) {
			modTabla = new ModeloTabla(titulos, datos);
			jtDatos = new JTable(modTabla);
			Utils.ocultarColumnaId(jtDatos);
			TableColumn columna0 = jtDatos.getColumn("Nro. de Cierre de caja");
			columna0.setCellRenderer(Utils.alinearDerecha());
			TableColumn columna1 = jtDatos.getColumn("Fecha");
			columna1.setCellRenderer(Utils.alinearCentro());
			TableColumn columna2 = jtDatos.getColumn("Importe en Caja");
			columna2.setCellRenderer(Utils.alinearDerecha());
			
		}
		return jtDatos;
	}

	void mostrarJTFFecha() {
		this.getJPBuscador().remove(getJTFNro());
		this.getJPBuscador().add(getJTFFecha(),null);
		this.getJPBuscador().add(getJLFechaFormato(),null);
		this.repaint();
		this.show();
	}

	void mostrarJTFNro() {
		this.getJPBuscador().remove(getJTFFecha());
		this.getJPBuscador().remove(getJLFechaFormato());
		this.getJPBuscador().add(getJTFNro(),null);
		this.repaint();
		this.show();
	}
		
	public JButton getJBSalir() {
		if (jbSalir == null) {
			jbSalir = new JButton();
			jbSalir.setBounds(new Rectangle(327,470,101,30));
			jbSalir.setText("Salir");
			jbSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbSalir.setInputMap(0, map);
		}
		return jbSalir;
	}
	
	public void setActionListeners(ActionListener lis) {
		jbAceptar.addActionListener(lis);
		jbCargar.addActionListener(lis);
		jbBorrar.addActionListener(lis);
		jbSalir.addActionListener(lis);
		jbCambiarPeriodo.addActionListener(lis);
		combo.addActionListener(lis);
	}

	public void setKeyListener(KeyListener lis) {
		jtfFecha.addKeyListener(lis);
		jtfNro.addKeyListener(lis);
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

