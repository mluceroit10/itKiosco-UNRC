package cliente.CuentaCliente;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import cliente.ModeloTabla;

import common.Utils;

public class GUICuentaBecario extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;				private JPanel jpCompromisos = null;
	private JButton jbSalir = null;
	public JScrollPane jspDatos = null;
	public JTable jtDatos = null;			    private ModeloTabla modTabla = null;
	public String[] titulos = {"Detalle","Fecha","Debe","Haber","Saldo"};
	public Object[][] datos;
	public Font a = new Font(Utils.tipoLetra,Font.BOLD,18);
	private JButton jbImprimir;
	private Vector detalleItM= new Vector();
	private Vector fechaM = new Vector();
	private Vector debeM= new Vector();
	private Vector haberM= new Vector();
	private Vector saldoM= new Vector();
	private JCheckBox jCheckSelectAll = null;
	private InputMap map = new InputMap();

	public GUICuentaBecario(Vector detalleIt, Vector fecha2, Vector debe, Vector haber, Vector saldo, String cliente,JDialog guiPadre) {
		super(guiPadre);
		detalleItM=detalleIt;
		fechaM=fecha2;
		debeM=debe;
		haberM=haber;
		saldoM=saldo;
		this.setSize(new java.awt.Dimension(706,435));
		this.setTitle("Estado de Cuenta del Becario: "+cliente);
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
			jpPpal.add(getJPCompromisos(),null);
			jpPpal.add(getJBImprimir(),null);
			jpPpal.add(getJBSalir(),null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPCompromisos() {
		if (jpCompromisos == null) {
			jpCompromisos = new JPanel();
			jpCompromisos.setLayout(null);
			jpCompromisos.setBounds(new Rectangle(15,15,670,330));
			jpCompromisos.setBorder(javax.swing.BorderFactory.createTitledBorder(Utils.b, " Movimientos de la Cuenta ",
					javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,a, null));
			jpCompromisos.add(getJSPDatos(), null);
			jpCompromisos.setBackground(Utils.colorPanel);
			jpCompromisos.add(getJCheckSelectAll(), null);
		}
		return jpCompromisos;
	} 

	private JScrollPane getJSPDatos() {
		if (jspDatos == null) {
			jspDatos = new JScrollPane();
			jspDatos.setBounds(new Rectangle(10,30,650,260));
			jspDatos.setViewportView(getJTDatos());
		}
		return jspDatos;
	}

	public JTable getJTDatos() {
		if (jtDatos == null) {
			datos= new Object[detalleItM.size()][titulos.length];
			for(int i=0; i<detalleItM.size();i++){
				Object[] temp = {detalleItM.elementAt(i),fechaM.elementAt(i),
						debeM.elementAt(i),haberM.elementAt(i),saldoM.elementAt(i)};
				datos[i] = temp;
			}		
			modTabla = new ModeloTabla(titulos, datos);
			jtDatos = new JTable(modTabla){
				private static final long serialVersionUID = 1L;
				public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
					Component returnComp = super.prepareRenderer(renderer, row,column);
					String detalle=getValueAt(row,0).toString();
					if(detalle.compareTo("IMPORTE RESTANTE FINAL")==0){
						float impRestante= Float.parseFloat(getValueAt(row,4).toString());
						if(impRestante<0){
							returnComp.setBackground(Color.WHITE);
							returnComp.setForeground(Color.RED);
							returnComp.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
						}else{
							returnComp.setBackground(Color.WHITE);
							returnComp.setForeground(Color.BLUE);
							returnComp.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
						}
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
			TableColumn columna1 = jtDatos.getColumn("Fecha");
			columna1.setPreferredWidth(80);
			columna1.setMaxWidth(80);
			TableColumn columna2 = jtDatos.getColumn("Debe");
			columna2.setPreferredWidth(90);
			columna2.setMaxWidth(90);
			TableColumn columna3 = jtDatos.getColumn("Haber");
			columna3.setPreferredWidth(90);
			columna3.setMaxWidth(90);
			TableColumn columna4 = jtDatos.getColumn("Saldo");
			columna4.setPreferredWidth(90);
			columna4.setMaxWidth(90);
		}
		return jtDatos;
	} 

	public JCheckBox getJCheckSelectAll() {
		if (jCheckSelectAll == null) {
			jCheckSelectAll = new JCheckBox();
			jCheckSelectAll.setBounds(new Rectangle(10,303,130,17));
			jCheckSelectAll.setText("Seleccionar Todo");
			jCheckSelectAll.setName("SelectAll");
			jCheckSelectAll.setBackground(Utils.colorPanel);
		}
		return jCheckSelectAll;
	}
	
	public JButton getJBImprimir() {
		if (jbImprimir == null) {
			jbImprimir = new JButton();
			jbImprimir.setBounds(new Rectangle(200,365,100,30));
			jbImprimir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbImprimir.setText("Imprimir");
			jbImprimir.setName("Imprimir");
			jbImprimir.setInputMap(0, map);
		}
		return jbImprimir;
	}

	public JButton getJBSalir() {
		if (jbSalir == null) {
			jbSalir = new JButton();
			jbSalir.setBounds(new Rectangle(400,365,100,30));
			jbSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbSalir.setText("Salir");
			jbSalir.setName("Salir");
			jbSalir.setInputMap(0, map);
		}
		return jbSalir;
	}
	
	public void setActionListeners(ActionListener lis) {
		jbSalir.addActionListener(lis);
		jbImprimir.addActionListener(lis);
		jCheckSelectAll.addActionListener(lis);
	}

	public void setListSelectionListener(ListSelectionListener lis) {
		jtDatos.getSelectionModel().addListSelectionListener(lis);
	}
	
}