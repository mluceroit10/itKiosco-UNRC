package cliente.backupDataBase;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.StyleConstants;

import common.RootAndIp;
import common.Utils;

public class GUIBackup extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton JButtonS = null;
	private JLabel icono = null;
	private JTextPane advertencia = null;		private JTextPane resultado = null;
	private JTextPane jTextPaneBackup = null;
	private InputMap map = new InputMap();

	public GUIBackup(JFrame guiPadre) throws Exception {
		super(guiPadre);
		this.setSize(706, 350);
		this.setLocationRelativeTo(guiPadre);
		this.setResizable(false);
		this.setTitle("Backup Base de Datos");
		this.setContentPane(getJContentPane());
		this.setModal(true);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			advertencia = new JTextPane();
			advertencia.setText("");
			advertencia.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			advertencia.setAlignmentX(CENTER_ALIGNMENT);
			advertencia.setForeground(new Color(0,0,205));
			advertencia.setBounds(new Rectangle(15,10,670,40));
			advertencia.setBackground(Utils.colorPanel);
			advertencia.setDisabledTextColor(Utils.colorTextoDisabled);
			advertencia.setEnabled(false);
			icono= new JLabel();
			icono.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"guardar.png"));
			icono.setBounds(new Rectangle(15,100,50,50));
			icono.setBorder(javax.swing.BorderFactory.createLineBorder(Color.red));
			resultado = new JTextPane();
			resultado.setText("");
			resultado.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			resultado.setForeground(new Color(0,0,205)); 
			resultado.setDisabledTextColor(Utils.colorTextoDisabled);
			resultado.setBounds(new Rectangle(15,220,670,40));
			resultado.setBackground(Utils.colorPanel);
			resultado.setEnabled(false);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(Utils.colorFondo);
			jContentPane.add(advertencia, null);
			jContentPane.add(resultado, null);
			jContentPane.add(icono, null);
			jContentPane.add(getJButtonSalir(), null);
			jContentPane.add(getJTextPaneBackup(), null);
		}
		return jContentPane;
	}

	public JButton getJButtonSalir() {
		if (JButtonS == null) {
			JButtonS = new JButton();
			JButtonS.setBounds(new Rectangle(300,280,100,30));
			JButtonS.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			JButtonS.setText("Salir");
			JButtonS.setInputMap(0, map);
		}
		return JButtonS;
	}

	public void setActionListeners(ActionListener lis){
		this.JButtonS.addActionListener(lis);
	}

	public JTextPane getAdvertencia() {
		return advertencia;
	}

	public JTextPane getResultado() {
		return resultado;
	}

	public JTextPane getJTextPaneBackup() {
		if (jTextPaneBackup == null) {
			jTextPaneBackup = new JTextPane();
			jTextPaneBackup.setBounds(new Rectangle(85,60,600,153));
			jTextPaneBackup.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sabia Ud. que...?", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", java.awt.Font.BOLD, 12), java.awt.Color.black));
			jTextPaneBackup.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			jTextPaneBackup.setText("El Backup es una copia de seguridad de su Base de Datos; es decir al realizar un Backup se crea autom�ticamente un archivo con la informaci�n almacenada en su Base de Datos en el momento de la ejecuci�n. Este archivo le servir� como respaldo, copia o recuerdo de la informaci�n que usted tenga almacenada en el sistema. Pueden realizarse la cantidad de Backups que desee y cada uno de los archivos creados quedar� almacenado en un directorio comun y con un nombre que distingue a cada uno.");
			Utils.alineacion(StyleConstants.ALIGN_JUSTIFIED,jTextPaneBackup);
			jTextPaneBackup.setDisabledTextColor(Utils.colorNegro);
			jTextPaneBackup.setEnabled(false);

		}
		return jTextPaneBackup;
	}

}
