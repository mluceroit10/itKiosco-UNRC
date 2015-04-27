package cliente.Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import common.RootAndIp;
import common.Utils;

public class GUIPrincipal extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	//PANEL PRINCIPAL
	private JPanel jContentPane = null;  
	
	//BARRA MENU
	private JMenuBar menuPrinc=null;
	private JMenu archivo=null;
	private JMenu gestionar=null;
	private JMenu baseDatos=null;

	private JMenuItem infoKiosco=null;				private JMenuItem infoPrograma=null;
	private JMenuItem archivoSalir=null;
	private JMenuItem gestionarProvincia=null;  	private JMenuItem gestionarLocalidad=null;
	private JMenuItem backupBD=null;


	private Border b= javax.swing.BorderFactory.createLineBorder(new Color(0,0,205),2);
	//PANEL ACCESOS RAPIDOS
	private JPanel jPanelAccesosFactCte=null;		private JPanel jPanelAccesosFactProv=null;
	private JPanel jPanelAccesosContables=null;		private JPanel jPanelAccesosProductos=null;
	private JPanel jPanelAccesosSesion=null;		private JPanel jPanelAccesosUsuario=null;
	private JPanel jPanelAccesosProveedores=null;
	
	private JButton jBFacturaCliente=null;			private JButton jBTodasFactCliente=null;
	private JButton jBFacturaProveedor=null;		private JButton jBTodasFactProv=null;
	private JButton jBGestionarMC=null;				private JButton jBPlanillaES=null;
	private JButton jBProductos=null;				private JButton jBControlStock=null;
	private JButton jBInicioCierreSesion=null;		private JButton jBHorasTrabajadas=null;
	private JButton jBGestionUsuarios=null;			private JButton jBGestionProveedores=null;
	private JButton jButtonSalir = null;
	
	Color colorFuenteMenu = new Color(0,0,128);
	private JLabel jlUsuario=null;
	private InputMap map = new InputMap();
	
	public GUIPrincipal() throws Exception{
		super();
		this.setSize(new java.awt.Dimension(800,570));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Sistema Informático 'Chupaleta' - "+Utils.Institucion()+"");
		jlUsuario = new JLabel();
		this.setContentPane(getJContentPane());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(RootAndIp.getPathImagenes()+"logo2.png"));
		this.setDefaultCloseOperation(0);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		this.init();
	}

	public JPanel getJContentPane() {
		if(jContentPane==null){
			jContentPane = new JPanel();
			jContentPane.setBackground(Utils.colorFondo);
			jContentPane.setLayout(null);
			jContentPane.add(getJPanelAccesosFactCliente(), null);
			jContentPane.add(getJPanelAccesosFactProveedor(), null);
			jContentPane.add(getJPanelAccesosContables(), null);
			jContentPane.add(getJPanelAccesosProductos(), null);
			jContentPane.add(getJPanelAccesosSesiones(),null);
			jContentPane.add(getJPanelAccesosUsuarios(), null);		
			jContentPane.add(getJPanelAccesosProveedores(), null);
			JLabel salir = new JLabel("SALIR");
			salir.setBounds(new Rectangle(660,470,44,20));
			salir.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			jContentPane.add(salir,null);
			jContentPane.add(getJButtonSalir(),null);
			jlUsuario.setBounds(new Rectangle(50,25,400,20));
			jlUsuario.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			this.setJMenuBar(getJBarraMenu());
		}
		return jContentPane;
	}

	public void setUsuario(String usr){
		jlUsuario.setText(usr);
	}

	private JMenuBar getJBarraMenu(){
		if (menuPrinc==null){
			menuPrinc = new JMenuBar();
			archivo = new JMenu("Información        ");
			archivo.setForeground(colorFuenteMenu);
			archivo.setMnemonic(KeyEvent.VK_I);
			archivo.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			gestionar = new JMenu("Gestionar             ");
			gestionar.setForeground(colorFuenteMenu);
			gestionar.setMnemonic(KeyEvent.VK_G);
			gestionar.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			baseDatos = new JMenu("Datos         ");
			baseDatos.setForeground(colorFuenteMenu);
			baseDatos.setMnemonic(KeyEvent.VK_D);
			baseDatos.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			menuPrinc.add(archivo);
			menuPrinc.add(gestionar);
			menuPrinc.add(baseDatos);
			infoKiosco= new JMenuItem("Del Kiosco");
			infoKiosco.setForeground(colorFuenteMenu);
			infoKiosco.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			infoPrograma= new JMenuItem("Del Programa");
			infoPrograma.setForeground(colorFuenteMenu);
			infoPrograma.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			archivoSalir= new JMenuItem("Salir        ");
			archivoSalir.setForeground(colorFuenteMenu);
			archivoSalir.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			gestionarProvincia= new JMenuItem("Provincia");
			gestionarProvincia.setForeground(colorFuenteMenu);
			gestionarProvincia.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			gestionarLocalidad= new JMenuItem("Localidad");
			gestionarLocalidad.setForeground(colorFuenteMenu);
			gestionarLocalidad.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			backupBD= new JMenuItem("BackUP de Base de Datos");
			backupBD.setForeground(colorFuenteMenu);
			backupBD.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 14));
			archivo.add(infoKiosco);
			archivo.add(infoPrograma);
			archivo.add(archivoSalir);
			gestionar.add(gestionarProvincia);
			gestionar.add(gestionarLocalidad);
			baseDatos.add(backupBD);
		}
		return menuPrinc;
	}

	// paneles
	private JPanel getJPanelAccesosFactCliente(){
		if (jPanelAccesosFactCte==null){
			jPanelAccesosFactCte = new JPanel();
			jPanelAccesosFactCte.setLayout(null);
			jPanelAccesosFactCte.setBackground(Utils.colorPanel);
			jPanelAccesosFactCte.setBorder(b);
			jPanelAccesosFactCte.setBounds(new Rectangle(10,10,370,115));
			JLabel titulosoc= new JLabel("FACTURACION - CLIENTE");
			titulosoc.setBounds(new Rectangle(90,5,190,20));
			titulosoc.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			jPanelAccesosFactCte.add(titulosoc, null);
			jPanelAccesosFactCte.add(getJButtonFacturaCliente(), null);
			JLabel nFact= new JLabel("VENTA");
			nFact.setBounds(new Rectangle(55,95,100,15));
			nFact.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			jPanelAccesosFactCte.add(nFact, null);
			jPanelAccesosFactCte.add(getJButtonTodasFactCliente(), null);
			JLabel factGen= new JLabel("FACTURAS GENERADAS");
			factGen.setBounds(new Rectangle(230,95,135,15));
			factGen.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			jPanelAccesosFactCte.add(factGen, null);
		}
		return jPanelAccesosFactCte;
	}
	
	public JButton getJButtonFacturaCliente() {
		if (jBFacturaCliente == null) {
			jBFacturaCliente= new JButton();
			jBFacturaCliente.setBounds(new Rectangle(45,28,60,60));
			jBFacturaCliente.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"facturaC.PNG"));
			jBFacturaCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBFacturaCliente.setInputMap(0, map);
		}
		return jBFacturaCliente;
	}
	
	public JButton getJButtonTodasFactCliente() {
		if (jBTodasFactCliente == null) {
			jBTodasFactCliente= new JButton();
			jBTodasFactCliente.setBounds(new Rectangle(265,28,60,60));
			jBTodasFactCliente.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"tFactCte.png"));
			jBTodasFactCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBTodasFactCliente.setInputMap(0, map);
		}
		return jBTodasFactCliente;
	}
	
	private JPanel getJPanelAccesosFactProveedor(){
		if (jPanelAccesosFactProv==null){
			jPanelAccesosFactProv = new JPanel();
			jPanelAccesosFactProv.setLayout(null);
			jPanelAccesosFactProv.setBackground(Utils.colorPanel);
			jPanelAccesosFactProv.setBorder(b);
			jPanelAccesosFactProv.setBounds(new Rectangle(410,10,370,115));
			JLabel titulosoc= new JLabel("FACTURACION - PROVEEDOR");
			titulosoc.setBounds(new Rectangle(80,5,220,20));
			titulosoc.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			jPanelAccesosFactProv.add(titulosoc, null);
			jPanelAccesosFactProv.add(getJButtonFacturaProveedor(), null);
			JLabel nFact= new JLabel("NUEVA FACTURA");
			nFact.setBounds(new Rectangle(25,95,103,15));
			nFact.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			jPanelAccesosFactProv.add(nFact, null);
			jPanelAccesosFactProv.add(getJButtonTodasFactProveedor(), null);
			JLabel factGen= new JLabel("FACTURAS GENERADAS");
			factGen.setBounds(new Rectangle(230,95,140,15));
			factGen.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			jPanelAccesosFactProv.add(factGen, null);
		}
		return jPanelAccesosFactProv;
	}
	
	public JButton getJButtonFacturaProveedor() {
		if (jBFacturaProveedor== null) {
			jBFacturaProveedor= new JButton();
			jBFacturaProveedor.setBounds(new Rectangle(45,28,60,60));
			jBFacturaProveedor.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"facturaP.png"));
			jBFacturaProveedor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBFacturaProveedor.setInputMap(0, map);
		}
		return jBFacturaProveedor;
	}
	
	public JButton getJButtonTodasFactProveedor() {
		if (jBTodasFactProv== null) {
			jBTodasFactProv= new JButton();
			jBTodasFactProv.setBounds(new Rectangle(265,28,60,60));
			jBTodasFactProv.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"tFactProv.png"));
			jBTodasFactProv.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBTodasFactProv.setInputMap(0, map);
		}
		return jBTodasFactProv;
	}
	
	private JPanel getJPanelAccesosContables(){
		if (jPanelAccesosContables==null){
			jPanelAccesosContables = new JPanel();
			jPanelAccesosContables.setLayout(null);
			jPanelAccesosContables.setBackground(Utils.colorPanel);
			jPanelAccesosContables.setBorder(b);
			jPanelAccesosContables.setBounds(new Rectangle(10,135,370,115));
			JLabel titulosoc= new JLabel("GESTION CONTABLE");
			titulosoc.setBounds(new Rectangle(120,5,150,20));
			titulosoc.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			jPanelAccesosContables.add(titulosoc, null);
			jPanelAccesosContables.add(getJButtonGestionarMC(), null);
			JLabel movCaja= new JLabel("MOVIMIENTO DE CAJA");
			movCaja.setBounds(new Rectangle(10,95,135,15));
			movCaja.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			jPanelAccesosContables.add(movCaja, null);
			jPanelAccesosContables.add(getJButtonPlanillaES(), null);
			JLabel plES= new JLabel("CIERRE DE CAJA");
			plES.setBounds(new Rectangle(249,95,94,15));
			plES.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			jPanelAccesosContables.add(plES, null);
		}
		return jPanelAccesosContables;
	}
	
	public JButton getJButtonGestionarMC() {
		if (jBGestionarMC == null) {
			jBGestionarMC= new JButton();
			jBGestionarMC.setBounds(new Rectangle(45,28,60,60));
			jBGestionarMC.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"movCaja.png"));
			jBGestionarMC.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		}
		return jBGestionarMC;
	}

	public JButton getJButtonPlanillaES() {
		if (jBPlanillaES == null) {
			jBPlanillaES= new JButton();
			jBPlanillaES.setBounds(new Rectangle(265,28,60,60));
			jBPlanillaES.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"planillaES.png"));
			jBPlanillaES.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBPlanillaES.setInputMap(0, map);
		}
		return jBPlanillaES;
	}
	
	private JPanel getJPanelAccesosProductos(){
		if (jPanelAccesosProductos==null){
			jPanelAccesosProductos = new JPanel();
			jPanelAccesosProductos.setLayout(null);
			jPanelAccesosProductos.setBackground(Utils.colorPanel);
			jPanelAccesosProductos.setBorder(b);
			jPanelAccesosProductos.setBounds(new Rectangle(410,135,370,115));
			JLabel titulosoc= new JLabel("PRODUCTOS");
			titulosoc.setBounds(new Rectangle(150,5,109,16));
			titulosoc.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			jPanelAccesosProductos.add(titulosoc, null);
			jPanelAccesosProductos.add(getJButtonProductos(), null);
			JLabel gestion= new JLabel("GESTION");
			gestion.setBounds(new Rectangle(48,95,55,12));
			gestion.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			jPanelAccesosProductos.add(gestion, null);
			jPanelAccesosProductos.add(getJButtonControlStock(), null);
			JLabel stock= new JLabel("CONTROL STOCK");
			stock.setBounds(new Rectangle(245,95,100,12));
			stock.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			jPanelAccesosProductos.add(stock, null);
		}
		return jPanelAccesosProductos;
	}

	public JButton getJButtonProductos() {
		if (jBProductos == null) {
			jBProductos= new JButton();
			jBProductos.setBounds(new Rectangle(45,28,60,60));
			jBProductos.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"productos.png"));
			jBProductos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBProductos.setInputMap(0, map);
		}
		return jBProductos;
	}

	public JButton getJButtonControlStock() {
		if (jBControlStock == null) {
			jBControlStock= new JButton();
			jBControlStock.setBounds(new Rectangle(265,28,60,60));
			jBControlStock.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"stock.PNG"));
			jBControlStock.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBControlStock.setInputMap(0, map);
		} 
		return jBControlStock;
	}
	
	private JPanel getJPanelAccesosSesiones(){
		if (jPanelAccesosSesion==null){
			jPanelAccesosSesion = new JPanel();
			jPanelAccesosSesion.setLayout(null);
			jPanelAccesosSesion.setBackground(Utils.colorPanel);
			jPanelAccesosSesion.setBorder(b);
			jPanelAccesosSesion.setBounds(new Rectangle(10,260,370,115));
			JLabel tituloc= new JLabel("SESIONES");
			tituloc.setBounds(new Rectangle(150,5,80,20));
			tituloc.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			JLabel gestion= new JLabel("INICIO/CIERRE");
			gestion.setBounds(new Rectangle(35,95,95,15));
			gestion.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			JLabel contr= new JLabel("HORAS TRABAJADAS");
			contr.setBounds(new Rectangle(235,95,120,15));
			contr.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			jPanelAccesosSesion.add(tituloc, null);
			jPanelAccesosSesion.add(contr, null);
			jPanelAccesosSesion.add(getJButtonInicioCierreSesion(), null);
			jPanelAccesosSesion.add(getJButtonHorasTrabajadas(), null);
			jPanelAccesosSesion.add(gestion, null);
		}
		return jPanelAccesosSesion;
	}

	public JButton getJButtonInicioCierreSesion() {
		if (jBInicioCierreSesion == null) {
			jBInicioCierreSesion= new JButton();
			jBInicioCierreSesion.setBounds(new Rectangle(45,28,60,60));
			jBInicioCierreSesion.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"inicioCierreSesion.png"));
			jBInicioCierreSesion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBInicioCierreSesion.setInputMap(0, map);
		}
		return jBInicioCierreSesion;
	}

	public JButton getJButtonHorasTrabajadas() {
		if (jBHorasTrabajadas == null) {
			jBHorasTrabajadas= new JButton();
			jBHorasTrabajadas.setBounds(new Rectangle(265,28,60,60));
			jBHorasTrabajadas.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"horasTrabajadas.png"));
			jBHorasTrabajadas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBHorasTrabajadas.setInputMap(0, map);
		}
		return jBHorasTrabajadas;
	}
	
	private JPanel getJPanelAccesosUsuarios(){
		if (jPanelAccesosUsuario==null){
			jPanelAccesosUsuario = new JPanel();
			jPanelAccesosUsuario.setLayout(null);
			jPanelAccesosUsuario.setBackground(Utils.colorPanel);
			jPanelAccesosUsuario.setBorder(b);
			jPanelAccesosUsuario.setBounds(new Rectangle(410,260,150,115));
			JLabel tituloc= new JLabel("USUARIOS");
			tituloc.setBounds(new Rectangle(38,5,80,20));
			tituloc.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			JLabel gestion= new JLabel("GESTION");
			gestion.setBounds(new Rectangle(48,95,55,15));
			gestion.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			jPanelAccesosUsuario.add(tituloc, null);
			jPanelAccesosUsuario.add(getJButtonUsuarios(), null);
			jPanelAccesosUsuario.add(gestion, null);
		}
		return jPanelAccesosUsuario;
	}

	public JButton getJButtonUsuarios() {
		if (jBGestionUsuarios == null) {
			jBGestionUsuarios= new JButton();
			jBGestionUsuarios.setBounds(new Rectangle(45,28,60,60));
			jBGestionUsuarios.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"socios.png"));
			jBGestionUsuarios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBGestionUsuarios.setInputMap(0, map);
		}
		return jBGestionUsuarios;
	}
	
	private JPanel getJPanelAccesosProveedores(){
		if (jPanelAccesosProveedores==null){
			jPanelAccesosProveedores = new JPanel();
			jPanelAccesosProveedores.setLayout(null);
			jPanelAccesosProveedores.setBackground(Utils.colorPanel);
			jPanelAccesosProveedores.setBorder(b);
			jPanelAccesosProveedores.setBounds(new Rectangle(630,260,150,115));
			JLabel titulov= new JLabel("PROVEEDORES");
			titulov.setBounds(new Rectangle(20,5,108,20));
			titulov.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 16));
			jPanelAccesosProveedores.add(titulov, null);
			jPanelAccesosProveedores.add(getJButtonProveedores(), null);
			JLabel gestion= new JLabel("GESTION");
			gestion.setBounds(new Rectangle(48,95,55,15));
			gestion.setFont(new java.awt.Font(Utils.tipoLetra, java.awt.Font.BOLD, 12));
			jPanelAccesosProveedores.add(gestion, null);
		}
		return jPanelAccesosProveedores;
	}

	public JButton getJButtonProveedores() {
		if (jBGestionProveedores == null) {
			jBGestionProveedores= new JButton();
			jBGestionProveedores.setBounds(new Rectangle(45,28,60,60));  
			jBGestionProveedores.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"proveedores.png"));
			jBGestionProveedores.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jBGestionProveedores.setInputMap(0, map);
		}
		return jBGestionProveedores;
	}

	public JButton getJButtonSalir() {
		if (jButtonSalir == null) {
			jButtonSalir = new JButton();
			jButtonSalir.setBounds(new Rectangle(650,400,65,65));
			jButtonSalir.setIcon(new ImageIcon(RootAndIp.getPathImagenes()+"salir.png"));
			jButtonSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0,0,205),0));
			jButtonSalir.setBackground(Utils.colorFondo);
			jButtonSalir.setInputMap(0, map);
		}
		return jButtonSalir;
	}

	public void setActionListeners(ActionListener lis) {
		this.jButtonSalir.addActionListener(lis);
		this.infoKiosco.addActionListener(lis);
		this.infoPrograma.addActionListener(lis);
		this.archivoSalir.addActionListener(lis);
		this.gestionarProvincia.addActionListener(lis);
		this.gestionarLocalidad.addActionListener(lis);
		this.jBGestionarMC.addActionListener(lis);
		this.jBPlanillaES.addActionListener(lis);
		this.jBGestionUsuarios.addActionListener(lis);
		this.jBGestionProveedores.addActionListener(lis);
		this.jBProductos.addActionListener(lis);
		this.jBControlStock.addActionListener(lis);
		this.jBFacturaProveedor.addActionListener(lis);
		this.jBTodasFactProv.addActionListener(lis);
		this.jBFacturaCliente.addActionListener(lis);
		this.jBTodasFactCliente.addActionListener(lis);
		this.backupBD.addActionListener(lis);
		this.jBInicioCierreSesion.addActionListener(lis);
		this.jBHorasTrabajadas.addActionListener(lis);
	}

	public JMenuItem getArchivoInfoKiosco() {
		return infoKiosco;
	}

	public JMenuItem getArchivoInfoPrograma() {
		return infoPrograma;
	}

	public JMenuItem getArchivoSalir() {
		return archivoSalir;
	}

	public JMenuItem getGestionarProvincia() {
		return gestionarProvincia;
	}

	public JMenuItem getGestionarLocalidad() {
		return gestionarLocalidad;
	}

	public JMenuItem getBaseDatos() {
		return backupBD;
	}

	private int d,m,a,hora, minutos, segundos;
	private int dia;
	JLabel label;
	Calendar calendario;
	Thread h1;

	public void init() {
		label = new JLabel(" ");
		label.setFont(new Font( Utils.tipoLetra, Font.BOLD, 16 ));
		label.setBounds(new Rectangle(15,470,300,20));
		this.jContentPane.add(label,null);
		h1 = new Thread(this);
		h1.start();
	}

	public void calcula () {
		Calendar calendario = new GregorianCalendar();
		a=calendario.get(Calendar.YEAR);
		m=calendario.get(Calendar.MONTH)+1;
		d=calendario.get(Calendar.DATE);
		dia=calendario.get(Calendar.DAY_OF_WEEK);
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND); 
	}
	
	private String nombreDia(int diaSemana){
		if(diaSemana==1) return "Domingo";
		if(diaSemana==2) return "Lunes";
		if(diaSemana==3) return "Martes";
		if(diaSemana==4) return "Miércoles";
		if(diaSemana==5) return "Jueves";
		if(diaSemana==6) return "Viernes";
		if(diaSemana==7) return "Sábado";
		return "";
	}
	
	public void run() {
		Thread ct = Thread.currentThread();
		while(ct == h1) {   
			calcula();
			String min=String.valueOf(minutos);
			if(min.length()==1) min ="0"+min;
			String seg=String.valueOf(segundos);
			if(seg.length()==1) seg ="0"+seg;
			label.setText("  "+nombreDia(dia)+"  "+d+"/"+m+"/"+a+" - "+hora + ":" + min + ":" + seg);
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {}
		}
	}

	public void stop() {
		h1 = null;
	}
	
}
