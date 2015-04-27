package cliente.GestionarProducto;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import common.Utils;

public class GUIStockProductos extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal=null;  				private JPanel jpTipoDatos=null;
	private JButton jBContinuar = null;			private JButton jBCancelar=null;
	private JLabel jlTitulo = null;				private JLabel jlProveedor = null;
	private JLabel jLFormaOrden = null;
	private JRadioButton unProv = null;			private JRadioButton todos = null;
	private JComboBox JCProveedores = null;		private JComboBox jCOrdenListado = null;
	private ButtonGroup group = null;
	public Vector proveedores= new Vector();
	private InputMap map = new InputMap();

	public GUIStockProductos(JFrame guiPadre) {
		super(guiPadre);
		this.setSize(486,380);
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Control de Stock");
		this.setContentPane(getPanelPpal());
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	private JPanel getPanelPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.setSize(486, 380);
			jpPpal.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, null));
			jpPpal.setName("Control de Stock");
			jlTitulo = new JLabel();
			jlTitulo.setBounds(new Rectangle(103,12,280,25));
			jlTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jlTitulo.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			jlTitulo.setText("CONTROL DE STOCK DE PRODUCTOS");
			jpPpal.add(jlTitulo, null);
			try {
				jpPpal.add(getJPTipoDatos(), null);
			} catch (RemoteException ex) {
				Utils.manejoErrores(ex,"Error en GUIStockProductos. getPanelPpal");
			}
			jpPpal.setBackground(Utils.colorFondo);
			jpPpal.add(getJBContinuar(), null);
			jpPpal.add(getJBCancelar(), null);
		}
		return jpPpal;
	}

	private JPanel getJPTipoDatos() throws RemoteException {
		if (jpTipoDatos == null) {
			jpTipoDatos = new JPanel();
			jpTipoDatos.setLayout(null);
			jpTipoDatos.setBorder(Utils.crearTituloTabla("Seleccione criterios de busqueda"));
			jpTipoDatos.setBounds(new Rectangle(15,50,450,240));
			jlProveedor = new JLabel();
			jlProveedor.setBounds(new Rectangle(30,30,390,100));
			jlProveedor.setHorizontalAlignment(SwingConstants.LEFT);
			jlProveedor.setBorder(Utils.crearTituloTabla("Seleccione Proveedor"));
			jLFormaOrden = new JLabel();
			jLFormaOrden.setBounds(new Rectangle(30,150,220,60));
			jLFormaOrden.setHorizontalAlignment(SwingConstants.LEFT);
			jLFormaOrden.setBorder(Utils.crearTituloTabla("Seleccione el orden del listado"));
			jpTipoDatos.add(jlProveedor, null);
			jpTipoDatos.add(jLFormaOrden, null);
			jpTipoDatos.setBackground(Utils.colorPanel);
			jpTipoDatos.add(getTodos(), null);
			jpTipoDatos.add(getUnProveedor(), null);
			jpTipoDatos.add(getJCOrdenListado(), null);
			opcTipoProv();
		}		
		return jpTipoDatos;
	}

	private JRadioButton getTodos() {
		if (todos == null) {
			todos = new JRadioButton("Todos",false);
			todos.setBounds(new Rectangle(60,60,73,21));
			todos.setBackground(Utils.colorPanel);
			todos.setActionCommand("Todos");
		}
		return todos;
	}
	
	private JRadioButton getUnProveedor() {
		if (unProv == null) {
			unProv = new JRadioButton("Un Proveedor",true);
			unProv.setBounds(new Rectangle(60,90,110,21));
			unProv.setBackground(Utils.colorPanel);
			unProv.setActionCommand("unProv");
		}
		return unProv;
	}

	public JComboBox getJCOrdenListado() {
		if (jCOrdenListado == null) {
			jCOrdenListado = new JComboBox();
			jCOrdenListado.setBounds(new Rectangle(60,180,170,20));
			jCOrdenListado.setBackground(new Color(255,255,255));
			jCOrdenListado.setForeground(java.awt.Color.black);
			jCOrdenListado.addItem("Codigo del Producto");
			jCOrdenListado.addItem("Nombre del Producto");
		}
		return jCOrdenListado;
	}
	
	public ButtonGroup opcTipoProv() {
		if (group == null) {
			group = new ButtonGroup();
			group.add(todos);
			group.add(unProv);
		}
		return group;
	}
	
	public JComboBox getJCProveedores(){
		if (JCProveedores == null) {
			JCProveedores = new JComboBox();
			for(int i=0;i<proveedores.size();i++){
				String prov=(String)proveedores.elementAt(i);
				JCProveedores.addItem(prov);
			}
			JCProveedores.setBackground(new Color(255,255,255));
			JCProveedores.setForeground(java.awt.Color.black);
			JCProveedores.setBounds(new Rectangle(200,90,140,20));
		}
		return JCProveedores;
	}

	public JButton getJBContinuar() {
		if (jBContinuar == null) {
			jBContinuar = new JButton();
			jBContinuar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBContinuar.setBounds(new Rectangle(90,310,100,30));
			jBContinuar.setText("Continuar");
			jBContinuar.setInputMap(0, map);
		}
		return jBContinuar;
	}

	public JButton getJBCancelar() {
		if (jBCancelar == null) {
			jBCancelar = new JButton();
			jBCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBCancelar.setBounds(new Rectangle(290,310,100,30));
			jBCancelar.setText("Cancelar");
			jBCancelar.setInputMap(0, map);
		}
		return jBCancelar;
	}
	
	public void setActionListeners(ActionListener lis) {
		jBContinuar.addActionListener(lis);
		jBCancelar.addActionListener(lis);
	}

	public void actualizarTabla(){
		jpTipoDatos.add(getJCProveedores(), null);
	}

}
