package cliente.GestionarSesion;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

import cliente.ModeloTabla;

import common.Utils;

public class GUIMostrarHorasUsuario extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;		    private JPanel jpDatos = null;
	private JButton jbAceptar = null;
	private ModeloTabla modTabla = null;
	public JScrollPane jspDatos = null;
	public final String[] titulos ={"Día","Horas inicio y fin","Horas por día"};
	public Object[][] datos;
	public JTable tabla;
	private String nombUsuario; 	
	private String fecha;
	private JTextField jtfHoras=null;
	private InputMap map = new InputMap();
	
	public GUIMostrarHorasUsuario(JDialog guiPadre,String nombreUsr,String fe) {
		super(guiPadre);
		nombUsuario=nombreUsr;
		fecha=fe;
		datos = new Object[0][titulos.length];
		this.setSize(736,540);
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Cantidad de horas trabajadas");
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
			JLabel jlNombre = new JLabel();
			jlNombre.setBounds(new Rectangle(15,20,400,25));
			jlNombre.setText("Usuario: "+nombUsuario);
			jlNombre.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			JLabel jlPeriodo = new JLabel();
			jlPeriodo.setBounds(new Rectangle(15,50,400,25));
			jlPeriodo.setText("Mes: "+fecha);
			jlPeriodo.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			jpPpal.add(jlNombre, null);
			jpPpal.add(jlPeriodo, null);
			jpPpal.add(getJPDatos(), null);
			jpPpal.add(getJBSalir(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBounds(new Rectangle(15,80,700,370));
			jpDatos.setBorder(Utils.crearTituloTabla("Horas del mes"));
			jpDatos.add(getJSPDatos(), null);
			jpDatos.setBackground(Utils.colorPanel);
			JLabel hTotal= new JLabel();
			hTotal.setBounds(new Rectangle(460,340,100,20));
			hTotal.setText("Horas Totales: ");
			hTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			jpDatos.add(hTotal, null);
			jpDatos.add(getJTFHrTotal(),null);
		}
		return jpDatos;
	}

	public JTextField getJTFHrTotal() {
		if (jtfHoras == null) {
			jtfHoras = new JTextField();
			jtfHoras.setBounds(new Rectangle(570,340,120,22));
			jtfHoras.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfHoras.setEnabled(false);
		}
		return jtfHoras;
	}

	private JScrollPane getJSPDatos() {
		if (jspDatos == null) {
			jspDatos = new JScrollPane();
			jspDatos.setBounds(new Rectangle(10,20,680,310));
			jspDatos.setViewportView(getJTDatos());
		}
		return jspDatos;
	}

	public JTable getJTDatos() {
		if (tabla == null) {
			modTabla = new ModeloTabla(titulos, datos);
			tabla = new JTable(modTabla);
			TableColumn columna0 = tabla.getColumn("Día");
			columna0.setPreferredWidth(80);
			columna0.setMaxWidth(80);

			TableColumn columna7 = tabla.getColumn("Horas por día");
			columna7.setPreferredWidth(100);
			columna7.setMaxWidth(100);
			columna7.setCellRenderer(Utils.alinearDerecha());
		}
		return tabla;
	}

	public JButton getJBSalir() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setName("Salir");
			jbAceptar.setText("Salir");
			jbAceptar.setBounds(new Rectangle(315,470,100,30));
			jbAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbAceptar.setInputMap(0, map);
		}
		return jbAceptar;
	}

	public void setActionListeners (ActionListener lis) {
		jbAceptar.addActionListener(lis);
	}

	public void actualizarTabla(){
		jpPpal.remove(getJPDatos());
		jpDatos = null;
		tabla = null;
		modTabla = null;
		jspDatos = null;
		jpPpal.add(getJPDatos(), null);
	}

}
