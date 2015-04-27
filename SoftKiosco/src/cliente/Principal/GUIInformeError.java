package cliente.Principal;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import common.Utils;

public class GUIInformeError extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private InputMap map = new InputMap();
	String error="";
	Object excep=null;
	private JPanel jpPpal=null;
	
	public GUIInformeError(JFrame guiPadre,String msjError,String msj){
		super(guiPadre);
		error=msjError;
		excep=msj;
		this.setSize(new java.awt.Dimension(400,350));
		this.setResizable(false);
		this.setLocation(10,10);
		this.setTitle("- Mensaje de Error -");
		this.setContentPane(getPanelPpal());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}
	
	private JPanel getPanelPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.setSize(400, 350);
			jpPpal.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, null));
			jpPpal.setName("Informe de error");
			jpPpal.add(getJContentPane(), null);
			jpPpal.setBackground(Utils.colorFondo);
		}
		return jpPpal;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane= new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBorder(Utils.crearTituloTabla("Tipo de Error"));
			jContentPane.setBounds(new Rectangle(15,15,360,230));
			JScrollPane JSPTipoError = new JScrollPane();
			JSPTipoError.setBounds(new Rectangle(20,20,320,200));
			JTextArea mensaje= new JTextArea();
			mensaje.setBounds(new Rectangle(20,20,300,170));
			String msj=error+"\n";
			if(excep!=null) msj += excep;
			mensaje.setText(msj);
			mensaje.setEditable(false);
			mensaje.setDisabledTextColor(Utils.colorTextoDisabled);
			JSPTipoError.setViewportView(mensaje);
			jContentPane.add(JSPTipoError);
			jContentPane.setBackground(Utils.colorPanel);
		}
		return jContentPane;
	}
	
}

