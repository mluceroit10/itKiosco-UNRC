package cliente.GestionarKiosco;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;
import common.Utils;
import common.DTOs.KioscoDTO;

public class GUIAltaModKiosco extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jpPpal = null;               private JPanel jpDatosKiosco = null;  
	private JButton jbCancelar = null;          private JButton jbAceptar = null;
	private JButton jbLocalidad = null;         private JButton jbImprTarjeta = null;     
	private JLabel jlNombre = null;		    	private JLabel jlDireccion = null;	          
	private JLabel jlEncargado = null;         	private JLabel jlTel = null;
	private JLabel jlInicioAct = null;			private JLabel jlLocalidad = null;
	private JTextField jtfNombre = null;		private JTextField jtfEncargado = null;       	    
	private JTextField jtfLocalidad = null;     private JTextField jtfCalle = null;
	private JTextField jtfTel = null;			
	private JDateChooser jDataCFecha = null;
	private KioscoDTO kiosco; 
	private InputMap map = new InputMap();
	private boolean edicion = true;
	
	public GUIAltaModKiosco(KioscoDTO k,JFrame guiPadre,boolean permisoEdicion) {
		super(guiPadre);
		this.kiosco = k;
		this.edicion = permisoEdicion;
		inicializar(guiPadre);
		this.setTitle("Datos Kiosco");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	private void inicializar(JFrame guiPadre) {
		this.setSize(506,350);
		this.setResizable(false);
		this.setLocationRelativeTo(guiPadre);
		this.setContentPane(getPanelPpal());
		this.setModal(true);
	}

	private JPanel getPanelPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.setSize(506, 350);
			jpPpal.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, null));
			jpPpal.setName("Gestión Cliente");
			jpPpal.add(getCancelar(), null);
			jpPpal.add(getAceptar(), null);
			jpPpal.add(getJPDatosKiosco(), null);
			jpPpal.add(getJButtonImprimirTarjeta(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJPDatosKiosco() {
		if (jpDatosKiosco == null) {
			jpDatosKiosco = new JPanel();
			jpDatosKiosco.setLayout(null);
			jpDatosKiosco.setBorder(Utils.crearTituloTabla("Datos del Kiosco"));
			jpDatosKiosco.setBounds(new Rectangle(15,15,470,245));
			jlNombre = new JLabel();
			jlNombre.setBounds(new Rectangle(12,30,120,20));  
			jlNombre.setText("(*) Nombre:");
			jlNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			jlEncargado = new JLabel();
			jlEncargado.setBounds(new Rectangle(12,62,120,20));
			jlEncargado.setText("(*) Encargado:");
			jlEncargado.setHorizontalAlignment(SwingConstants.RIGHT);
			jlInicioAct = new JLabel();
			jlInicioAct.setBounds(new Rectangle(12,94,120,20));
			jlInicioAct.setText("Inicio de Actividades:");  
			jlInicioAct.setHorizontalAlignment(SwingConstants.RIGHT);
			jlTel = new JLabel();
			jlTel.setBounds(new Rectangle(12,126,120,20));
			jlTel.setText("Teléfono:");
			jlTel.setHorizontalAlignment(SwingConstants.RIGHT);
			jlDireccion = new JLabel();
			jlDireccion.setBounds(new Rectangle(12,158,120,20));
			jlDireccion.setText("(*) Dirección:");
			jlDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
			jlLocalidad = new JLabel();
			jlLocalidad.setBounds(new Rectangle(12,190,120,20));
			jlLocalidad.setText("(*) Localidad:");
			jlLocalidad.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel jlLeyenda = new JLabel();
			jlLeyenda.setBounds(new Rectangle(12,222,450,20));
			jlLeyenda.setText("(*) Datos obligatorios");
			jlLeyenda.setHorizontalAlignment(SwingConstants.RIGHT);
			jpDatosKiosco.add(jlNombre, null);
			jpDatosKiosco.add(jlEncargado, null);
			jpDatosKiosco.add(jlInicioAct, null);
			jpDatosKiosco.add(jlTel, null);
			jpDatosKiosco.add(jlDireccion, null);
			jpDatosKiosco.add(jlLocalidad, null);
			jpDatosKiosco.add(jlLeyenda, null);
			jpDatosKiosco.add(getNombre(), null);
			jpDatosKiosco.add(getEncargado(), null);
			jpDatosKiosco.add(getJDateChooserFecha(), null);
			jpDatosKiosco.add(getTelefono(), null);
			jpDatosKiosco.add(getDireccion(), null);
			jpDatosKiosco.add(getLocalidad(), null);
			jpDatosKiosco.add(getJButtonLocalidad(), null);
			if (kiosco!=null) {
				jtfNombre.setText(kiosco.getNombre());
				jtfTel.setText(kiosco.getTelefono());
				jtfCalle.setText(kiosco.getDireccion());
				jtfLocalidad.setText(kiosco.getLocalidad().getNombre());
				jDataCFecha.setDate(kiosco.getInicioAct());
				jtfEncargado.setText(kiosco.getEncargado());
			}
			jpDatosKiosco.setBackground(Utils.colorPanel);
		}
		return jpDatosKiosco;
	}

	public JTextField getNombre() {
		if (jtfNombre == null) {
			jtfNombre = new JTextField();
			jtfNombre.setBounds(new Rectangle(140,30,320,22));
			jtfNombre.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfNombre.setEnabled(edicion);
		}
		return jtfNombre;
	}

	public JTextField getEncargado() {
		if (jtfEncargado == null) {
			jtfEncargado = new JTextField();
			jtfEncargado.setBounds(new Rectangle(140,62,320,22));
			jtfEncargado.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfEncargado.setEnabled(edicion);
		}
		return jtfEncargado;
	}
	
	public JDateChooser getJDateChooserFecha() {
		if (jDataCFecha == null) {
			jDataCFecha = new JDateChooser("dd - MMMMM - yyyy",false);
			jDataCFecha.setBounds(new Rectangle(140,94,180,22));
			jDataCFecha.setVisible(edicion);
			if(!edicion){
				JTextField fecha = new JTextField();
				fecha.setBounds(new Rectangle(140,94,180,22));
				fecha.setDisabledTextColor(Utils.colorTextoDisabled);
				fecha.setEnabled(edicion);
				jpDatosKiosco.add(fecha,null);
				if(kiosco!=null)
					fecha.setText(Utils.getStrUtilDate(kiosco.getInicioAct()));
			}
		}
		return jDataCFecha;
	}

	public JTextField getTelefono() {
		if (jtfTel == null) {
			jtfTel = new JTextField();
			jtfTel.setBounds(new Rectangle(140,126,320,22));
			jtfTel.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfTel.setEnabled(edicion);
		}
		return jtfTel;
	}

	public JTextField getDireccion() {
		if (jtfCalle == null) {
			jtfCalle = new JTextField();
			jtfCalle.setBounds(new Rectangle(140,158,320,22));
			jtfCalle.setDisabledTextColor(Utils.colorTextoDisabled);
			jtfCalle.setEnabled(edicion);
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
			jbLocalidad.setEnabled(edicion);
		}
		return jbLocalidad;
	}

	public JButton getAceptar() {
		if (jbAceptar == null) {
			jbAceptar = new JButton();
			jbAceptar.setBounds(new Rectangle(140,280,100,30));
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
			jbCancelar.setBounds(new Rectangle(260,280,100,30));
			jbCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbCancelar.setName("Cancelar");
			jbCancelar.setText("Cancelar");
			jbCancelar.setInputMap(0, map);
		}
		return jbCancelar;
	}
	
	public JButton getJButtonImprimirTarjeta() {
		if (jbImprTarjeta == null) {
			jbImprTarjeta = new JButton();
			jbImprTarjeta.setBounds(new Rectangle(10,280,80,30));
			jbImprTarjeta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jbImprTarjeta.setName("Imprimir");
			jbImprTarjeta.setText("Imprimir");
			jbImprTarjeta.setInputMap(0, map);
		}
		return jbImprTarjeta;
	}

	public void setActionListeners(ActionListener lis){
		this.jbCancelar.addActionListener(lis);
		this.jbAceptar.addActionListener(lis);
		this.jbLocalidad.addActionListener(lis);
		this.jbImprTarjeta.addActionListener(lis);
	}

	public void setLocalidad(String string) {
		jtfLocalidad.setText(string);
	}

}
