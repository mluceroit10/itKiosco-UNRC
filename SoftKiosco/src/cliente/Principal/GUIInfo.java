package cliente.Principal;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.StyleConstants;

import common.JLinkButton;
import common.RootAndIp;
import common.Utils;

public class GUIInfo extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLinkButton label7;

	public GUIInfo(JFrame guiPadre) throws Exception{
		super(guiPadre);
		this.setSize(new java.awt.Dimension(600,470));
		this.setResizable(false);
		this.setLocationRelativeTo(guiPadre);
		this.setTitle("Informaci�n del Sistema Inform�tico");
		this.setContentPane(getJContentPane());
		this.setModal(true);
	}

	private JPanel getJContentPane(){
		if (jContentPane == null) {
			jContentPane= new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(Utils.colorFondo);
			jContentPane.setSize(new java.awt.Dimension(600,430));
			JLabel icono= new JLabel();
			icono.setBounds(new java.awt.Rectangle(15,15,205,120));
			icono.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"logo2.png"));
			JLabel label0= new JLabel();
			label0.setBounds(new Rectangle(225,90,200,50));
			label0.setText("CHUPALETA");
			label0.setFont(new java.awt.Font("Lucida Handwriting", java.awt.Font.BOLD, 28));
			label0.setHorizontalAlignment(SwingConstants.CENTER);
			label0.setForeground(new Color(0, 0, 205 ));
			JLabel label1= new JLabel();
			label1.setBounds(new Rectangle(15,150,400,19));
			label1.setText("Versi�n: 1.0");
			label1.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			label1.setForeground(Utils.colorNegro);
			JLabel label3= new JLabel();
			label3.setBounds(new Rectangle(15,180,400,19));
			label3.setText("CHUPALETA: Sistema de gesti�n de kioscos");
			label3.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			label3.setHorizontalAlignment(SwingConstants.LEFT);
			label3.setForeground(Utils.colorNegro);
			JTextPane datos= new  JTextPane();
			datos.setBounds(new java.awt.Rectangle(15,200,570,160));
			datos.setText("Sistema para peque�os comerciantes, cuya venta de productos se realiza a clientes minoristas.\n" +
					"- Permite la facturaci�n al cliente y a los proveedores, posibilitando el manejo de sus productos y realizando el control de stock de manera din�mica.\n" +
					"- Cuenta con la posibilidad de realizar balances de las entradas y salidas de dinero, producto de la facturaci�n u otros gastos. \n" +
			        "- Admite gesti�n de usuarios con distintos permisos para administrar el sistema. El administrador tendr� acceso a todas las funcionalidades, mientras que el vendedor podr� realizar la venta al cliente y consultar sus beneficios personales.");
			datos.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.PLAIN, 13));
			Utils.alineacion(StyleConstants.ALIGN_JUSTIFIED,datos);
			datos.setForeground(Utils.colorNegro);
			datos.setBackground(Utils.colorFondo);
			JLabel label5= new JLabel();
			label5.setBounds(new java.awt.Rectangle(15,370,570,19));
			label5.setText("DESARROLLADO POR: \"IT10\" COOPERATIVA DE TRABAJO LIMITADA (RIO CUARTO)" );
			label5.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			label5.setHorizontalAlignment(SwingConstants.CENTER);
			label5.setForeground(Color.BLUE);
			JLabel label6= new JLabel();
			label6.setBounds(new java.awt.Rectangle(15,390,570,19));
			label6.setText("contacto@it10coop.com.ar" );
			label6.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 11));
			label6.setHorizontalAlignment(SwingConstants.CENTER);
			label6.setForeground(Utils.colorNegro);
			try {
				URL uri = new URL("http://www.it10coop.com.ar");
				label7= new JLinkButton(uri) ; 
				label7.setBounds(new java.awt.Rectangle(15,410,570,19));
				label7.setHorizontalAlignment(SwingConstants.CENTER);
				label7.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			} catch (Exception e) {
				e.printStackTrace();
			}
			jContentPane.add(icono);
			jContentPane.add(label0);
			jContentPane.add(label1);
			jContentPane.add(label3);
			jContentPane.add(datos);
			jContentPane.add(label5);
			jContentPane.add(label6);
			jContentPane.add(label7);
		}
		return jContentPane;
	}

	public void setActionListeners(ActionListener lis) {
		label7.addActionListener(lis);
	}

}
