package server;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.GestionarBecaAsignada.ControlBecaAsignada;
import server.GestionarFacturaCliente.ControlFacturaCliente;
import server.GestionarFacturaProveedor.ControlFacturaProveedor;
import server.GestionarKiosco.ControlKiosco;
import server.GestionarLocalidad.ControlLocalidad;
import server.GestionarMovimientoCaja.ControlMovimientoCaja;
import server.GestionarProducto.ControlProducto;
import server.GestionarProveedor.ControlProveedor;
import server.GestionarProvincia.ControlProvincia;
import server.GestionarSesion.ControlSesion;
import server.GestionarUsuario.ControlUsuario;
import server.RealizarPlanillaES.ControlRealizarPlanillaES;
import cliente.Principal.GUIServidorIniciado;

public class Servidor {
	private String name = "";
	private String ip = "";
	// Controles
	private ControlFacturaCliente controlFacturaCliente =null;
	private ControlFacturaProveedor controlFacturaProveedor = null;
	private ControlKiosco controlKiosco =null;
	private ControlLocalidad controlLocalidad = null;
	private ControlMovimientoCaja controlMovimientoCaja =null;
	private ControlProducto controlProducto =null;
	private ControlProveedor controlProveedor =null;
	private ControlProvincia controlProvincia = null;
	private ControlUsuario controlUsuario =null;
	private ControlBecaAsignada controlBecaAsignada=null;
	private ControlSesion controlSesion =null;
	private ControlRealizarPlanillaES controlRealizarPlanillaES =null;
	private String classPath = "";

	public void iniciar() throws Exception {
		Registry Naming =LocateRegistry.createRegistry(1099);
		System.setProperty("java.rmi.server.codebase", "file:" + this.classPath);
		System.out.println("Iniciando servidor !!!");

		this.ip=(InetAddress.getLocalHost().getHostAddress()).toString();//RootAndIp.getIp();
		System.out.println("Ip: " + this.ip);

		this.name = "rmi://" + this.ip + "/IControlFacturaCliente";
		Naming.rebind(this.name, this.controlFacturaCliente);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlFacturaProveedor";
		Naming.rebind(this.name, this.controlFacturaProveedor);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlKiosco";
		Naming.rebind(this.name, this.controlKiosco);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlLocalidad";
		Naming.rebind(this.name, this.controlLocalidad);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlMovimientoCaja";
		Naming.rebind(this.name, this.controlMovimientoCaja);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlProducto";
		Naming.rebind(this.name, this.controlProducto);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlProveedor";
		Naming.rebind(this.name, this.controlProveedor);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlProvincia";
		Naming.rebind(this.name, this.controlProvincia);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlUsuario";
		Naming.rebind(this.name, this.controlUsuario);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlBecaAsignada";
		Naming.rebind(this.name, this.controlBecaAsignada);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlSesion";
		Naming.rebind(this.name, this.controlSesion);
		System.out.println("Nombre: " + this.name);

		this.name = "rmi://" + this.ip + "/IControlRealizarPlanillaES";
		Naming.rebind(this.name, this.controlRealizarPlanillaES);
		System.out.println("Nombre: " + this.name);

		GUIServidorIniciado serv=new GUIServidorIniciado(ip);
		serv.show();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public void setControlFacturaCliente(ControlFacturaCliente controlFC) {
		this.controlFacturaCliente = controlFC;
	}

	public void setControlFacturaProveedor(ControlFacturaProveedor controlFP) {
		this.controlFacturaProveedor = controlFP;
	}

	public void setControlKiosco(ControlKiosco controlK) {
		this.controlKiosco = controlK;
	}

	public void setControlLocalidad(ControlLocalidad controlLocalidad) {
		this.controlLocalidad = controlLocalidad;
	}

	public void setControlMovimientoCaja(ControlMovimientoCaja controlMC) {
		this.controlMovimientoCaja = controlMC;
	}

	public void setControlProducto(ControlProducto controlPr) {
		this.controlProducto = controlPr;
	}

	public void setControlProveedor(ControlProveedor controlRec) {
		this.controlProveedor = controlRec;
	}

	public void setControlProvincia(ControlProvincia controlP) {
		this.controlProvincia = controlP;
	}

	public void setControlUsuario(ControlUsuario controlV) {
		this.controlUsuario = controlV;
	}

	public void setControlBecaAsignada(ControlBecaAsignada controlBecaAsig) {
		this.controlBecaAsignada = controlBecaAsig;
	}

	public void setControlSesion(ControlSesion controlS) {
		this.controlSesion = controlS;
	}

	public void setControlRealizarPlanillaES(ControlRealizarPlanillaES controlPES) {
		this.controlRealizarPlanillaES = controlPES;
	}
	
}

