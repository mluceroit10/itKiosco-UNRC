package cliente.GestionarProducto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import cliente.LimitadorNroMax;
import cliente.ModeloTabla;

import common.Utils;

public class GUIGestionarProducto extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;	    	private JPanel jpGestion = null;
	private JPanel jpDatos = null;	    	private JPanel jpBuscador = null;
	private JButton jbCargar = null;    	private JButton jbModif = null;
	private JButton jbSalir = null;		   	private JButton jbBorrar = null;
	private JButton jbImprimir = null;
	private JLabel jlNombre = null;			private JLabel jlCodigo = null;
	private JTextField jtfNombre = null;	private JTextField jtfCodigo = null;
	public JScrollPane jspDatos = null;
	public JTable jtDatos = null;			private ModeloTabla modTabla = null;
	public String[] titulos = {"Id","Código","Nombre","Stock Actual","Stock Mínimo","Precio Entrada","Margen Ganancia","Precio Venta","Proveedor"};
	public Object[][] datos;
	private InputMap map = new InputMap();
	
	public GUIGestionarProducto(JFrame guiPadre) {
		super(guiPadre);
		datos = new Object[0][titulos.length];
		this.setSize(new java.awt.Dimension(736,525));
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Gestión de Productos");
		this.setContentPane(getJPPpal());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	public JPanel getJPPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.add(getJPGestion(), null);
			jpPpal.add(getJBSalir(), null);
			jpPpal.add(getJPDatos(), null);
			jpPpal.add(getJPBuscador(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPBuscador() {
		if (jpBuscador == null) {
			jpBuscador = new JPanel();
			jlCodigo = new JLabel();
			jlCodigo.setBounds(new Rectangle(15,30,60,20));
			jlCodigo.setText("Código:");
			jlNombre = new JLabel();
			jlNombre.setBounds(new Rectangle(130,30,60,20));
			jlNombre.setText("Nombre:");
			jpBuscador.setLayout(null);
			jpBuscador.setBorder(Utils.crearTituloTabla("Buscar"));
			jpBuscador.setBounds(new Rectangle(410,15,305,65));
			jpBuscador.add(jlCodigo, null);
			jpBuscador.add(jlNombre, null);
			jpBuscador.add(getJTFBuscadorCodigo(), null);
			jpBuscador.add(getJTFBuscadorNombre(), null);
			jpBuscador.setBackground(Utils.colorPanel);
		}
		return jpBuscador;
	}

	public JTextField getJTFBuscadorCodigo() {
		if (jtfCodigo == null) {
			jtfCodigo = new JTextField();
			jtfCodigo.setBounds(new Rectangle(65,30,50,22));
			jtfCodigo.setDocument(new LimitadorNroMax(jtfCodigo,6));
		}
		return jtfCodigo;
	}

	public JTextField getJTFBuscadorNombre() {
		if (jtfNombre == null) {
			jtfNombre = new JTextField();
			jtfNombre.setBounds(new Rectangle(190,30,100,22));
		}
		return jtfNombre;
	}

	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBounds(new Rectangle(15,95,700,340));
			jpDatos.setBorder(Utils.crearTituloTabla("Listado de Productos"));
			jpDatos.add(getJSPDatos(), null);
			jpDatos.setBackground(Utils.colorPanel);
		}
		return jpDatos;
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
		if (jtDatos == null) {
			modTabla = new ModeloTabla(titulos, datos);
			jtDatos = new JTable(modTabla){
				private static final long serialVersionUID = 1L;
				public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
					Component returnComp = super.prepareRenderer(renderer, row,column);
					float cant= Float.parseFloat(getValueAt(row,3).toString());
					float cantmin= Float.parseFloat(getValueAt(row,4).toString());
					if(cant <= cantmin){
						returnComp.setBackground(Color.WHITE);
						returnComp.setForeground(Color.RED);
						returnComp.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
					}else{
						returnComp.setBackground(Color.WHITE);
						returnComp.setForeground(Color.BLACK);
					}
					int[] seleccion=this.getSelectedRows();
					for(int j=0;j<seleccion.length;j++){
						if(seleccion[j]==row){
							returnComp.setBackground(new Color(176,196,222));
							returnComp.setForeground(Color.BLACK);
						}
					}
					return returnComp;
				}
			}; 
			Utils.ocultarColumnaId(jtDatos); 
			TableColumn columna0 = jtDatos.getColumn("Código");
			columna0.setPreferredWidth(70);
			TableColumn columna3 = jtDatos.getColumn("Stock Actual");
			columna3.setPreferredWidth(70);
			columna3.setMaxWidth(70);
			columna3.setCellRenderer(Utils.alinearDerecha());
			TableColumn columna4 = jtDatos.getColumn("Stock Mínimo");
			columna4.setPreferredWidth(80);
			columna4.setMaxWidth(80);
			columna4.setCellRenderer(Utils.alinearDerecha());
			TableColumn columna5 = jtDatos.getColumn("Precio Entrada");
			columna5.setPreferredWidth(80);
			columna5.setCellRenderer(Utils.alinearDerecha());
			TableColumn columna6 = jtDatos.getColumn("Precio Venta");
			columna6.setPreferredWidth(80);
			columna6.setCellRenderer(Utils.alinearDerecha());
			TableColumn columna7 = jtDatos.getColumn("Margen Ganancia");
			columna7.setCellRenderer(Utils.alinearDerecha());
		}
		return jtDatos;
	}

	private JPanel getJPGestion() {
		if (jpGestion == null) {
			jpGestion = new JPanel();
			jpGestion.setLayout(null);
			jpGestion.setBounds(new Rectangle(15,15,380,65));
			jpGestion.setBorder(Utils.crearTituloTabla("Gestión"));
			jpGestion.add(getJBCargar(), null);
			jpGestion.add(getJBMod(), null);
			jpGestion.add(getJBBorrar(), null);
			jpGestion.add(getJBImprimir(), null);
			jpGestion.setBackground(Utils.colorPanel);
		}
		return jpGestion;
	}

	public JButton getJBCargar() {
		if (jbCargar == null) {
			jbCargar = new JButton();
			jbCargar.setText("Ingresar");
			jbCargar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCargar.setBounds(new Rectangle(15,25,80,25));
			jbCargar.setInputMap(0, map);
		}
		return jbCargar;
	}

	public JButton getJBMod() {
		if (jbModif == null) {
			jbModif = new JButton();
			jbModif.setText("Modificar");
			jbModif.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbModif.setBounds(new Rectangle(105,25,80,25));
			jbModif.setInputMap(0, map);
		}
		return jbModif;
	}

	public JButton getJBBorrar() {
		if (jbBorrar == null) {
			jbBorrar = new JButton();
			jbBorrar.setText("Eliminar");
			jbBorrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbBorrar.setBounds(new Rectangle(195,25,80,25));
			jbBorrar.setInputMap(0, map);
		}
		return jbBorrar;
	}

	public JButton getJBImprimir() {
		if (jbImprimir == null) {
			jbImprimir = new JButton();
			jbImprimir.setName("Imprimir");
			jbImprimir.setText("Imprimir");
			jbImprimir.setBounds(new Rectangle(285,25,80,25));
			jbImprimir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbImprimir.setInputMap(0, map);
		}
		return jbImprimir;
	}

	public JButton getJBSalir() {
		if (jbSalir == null) {
			jbSalir = new JButton();
			jbSalir.setName("Salir");
			jbSalir.setText("Salir");
			jbSalir.setBounds(new Rectangle(315,455,100,30));
			jbSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbSalir.setInputMap(0, map);
		}
		return jbSalir;
	}

	public void setActionListeners(ActionListener lis) {
		jbSalir.addActionListener(lis);
		jbModif.addActionListener(lis);
		jbCargar.addActionListener(lis);
		jbImprimir.addActionListener(lis);
		jbBorrar.addActionListener(lis);
	}

	public void setKeyListener(KeyListener lis) {
		jtfNombre.addKeyListener(lis);
		jtfCodigo.addKeyListener(lis);
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

}

