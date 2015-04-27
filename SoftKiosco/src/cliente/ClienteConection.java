package cliente;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.RootAndIp;
import common.GestionarBecaAsignada.IControlBecaAsignada;
import common.GestionarFacturaCliente.IControlFacturaCliente;
import common.GestionarFacturaProveedor.IControlFacturaProveedor;
import common.GestionarKiosco.IControlKiosco;
import common.GestionarLocalidad.IControlLocalidad;
import common.GestionarMovimientoCaja.IControlMovimientoCaja;
import common.GestionarProducto.IControlProducto;
import common.GestionarProveedor.IControlProveedor;
import common.GestionarProvincia.IControlProvincia;
import common.GestionarSesion.IControlSesion;
import common.GestionarUsuario.IControlUsuario;
import common.RealizarPlanillaES.IControlRealizarPlanillaES;

public class ClienteConection {
	private String maquina = RootAndIp.getMaquina();
	private String hostServer =RootAndIp.getIp();
	private IControlFacturaCliente iControlFacturaCliente = null;
	private IControlFacturaProveedor iControlFacturaProveedor = null;
	private IControlKiosco iControlKiosco =null;
	private IControlLocalidad iControlLocalidad = null;
	private IControlMovimientoCaja iControlMovimientoCaja = null;
	private IControlProducto iControlProducto = null;
	private IControlProvincia iControlProvincia =null;
	private IControlProveedor iControlProveedor =null;
	private IControlUsuario iControlUsuario =null;
	private IControlBecaAsignada iControlBecaAsignada =null;
	private IControlSesion iControlSesion =null;
	private IControlRealizarPlanillaES iControlRealizarPlanillaES = null;
	
	public ClienteConection (){
	}

	public void iniciar() throws Exception {
		if(maquina.compareTo("servidor")==0) 
			hostServer = (InetAddress.getLocalHost().getHostAddress()).toString();
		Registry Naming =LocateRegistry.getRegistry(hostServer,1099);
		String nombreServer = "";

		nombreServer = "rmi://"+this.hostServer+"/IControlFacturaCliente";
		IControlFacturaCliente iControlFacturaCliente= (IControlFacturaCliente)Naming.lookup(nombreServer);
		this.iControlFacturaCliente = iControlFacturaCliente;

		nombreServer = "rmi://"+this.hostServer+"/IControlFacturaProveedor";
		IControlFacturaProveedor iControlFacturaProveedor= (IControlFacturaProveedor)Naming.lookup(nombreServer);
		this.iControlFacturaProveedor = iControlFacturaProveedor;

		nombreServer = "rmi://"+this.hostServer+"/IControlKiosco";
		IControlKiosco iControlKiosco= (IControlKiosco)Naming.lookup(nombreServer);
		this.iControlKiosco = iControlKiosco;

		nombreServer = "rmi://"+this.hostServer+"/IControlLocalidad";
		IControlLocalidad iControlLocalidad= (IControlLocalidad)Naming.lookup(nombreServer);
		this.iControlLocalidad = iControlLocalidad;    

		nombreServer = "rmi://"+this.hostServer+"/IControlMovimientoCaja";
		IControlMovimientoCaja iControlMovimientoCaja= (IControlMovimientoCaja)Naming.lookup(nombreServer);
		this.iControlMovimientoCaja = iControlMovimientoCaja;

		nombreServer = "rmi://"+this.hostServer+"/IControlProducto";
		IControlProducto iControlProducto= (IControlProducto)Naming.lookup(nombreServer);
		this.iControlProducto = iControlProducto;

		nombreServer = "rmi://"+this.hostServer+"/IControlProvincia";
		IControlProvincia iControlProvincia= (IControlProvincia)Naming.lookup(nombreServer);
		this.iControlProvincia = iControlProvincia;

		nombreServer = "rmi://"+this.hostServer+"/IControlProveedor";
		IControlProveedor iControlProveedor= (IControlProveedor)Naming.lookup(nombreServer);
		this.iControlProveedor = iControlProveedor;

		nombreServer = "rmi://"+this.hostServer+"/IControlUsuario";
		IControlUsuario iControlUsuario= (IControlUsuario)Naming.lookup(nombreServer);
		this.iControlUsuario = iControlUsuario;

		nombreServer = "rmi://"+this.hostServer+"/IControlBecaAsignada";
		IControlBecaAsignada iControlBecaAsignada= (IControlBecaAsignada)Naming.lookup(nombreServer);
		this.iControlBecaAsignada = iControlBecaAsignada;

		nombreServer = "rmi://"+this.hostServer+"/IControlSesion";
		IControlSesion iControlSesion= (IControlSesion)Naming.lookup(nombreServer);
		this.iControlSesion = iControlSesion;

		nombreServer = "rmi://"+this.hostServer+"/IControlRealizarPlanillaES";
		IControlRealizarPlanillaES iControlRealizarPlanillaES= (IControlRealizarPlanillaES)Naming.lookup(nombreServer);
		this.iControlRealizarPlanillaES = iControlRealizarPlanillaES;

	}

	public String getHostServer() {
		return hostServer;
	}

	public IControlFacturaCliente getIControlFacturaCliente() {
		return iControlFacturaCliente;
	}

	public IControlFacturaProveedor getIControlFacturaProveedor() {
		return iControlFacturaProveedor;
	}

	public IControlKiosco getIControlKiosco( ) {
		return iControlKiosco;
	}

	public IControlLocalidad getIControlLocalidad() {
		return iControlLocalidad;
	}

	public IControlMovimientoCaja getIControlMovimientoCaja() {
		return iControlMovimientoCaja;
	}

	public IControlProducto getIControlProducto() {
		return iControlProducto;
	}

	public IControlProvincia getIControlProvincia( ) {
		return iControlProvincia;
	}

	public IControlProveedor getIControlProveedor( ) {
		return iControlProveedor;
	}

	public IControlUsuario getIControlUsuario( ) {
		return iControlUsuario;
	}

	public IControlBecaAsignada getIControlBecaAsignada( ) {
		return iControlBecaAsignada;
	}

	public IControlSesion getIControlSesion( ) {
		return iControlSesion;
	}

	public IControlRealizarPlanillaES getIControlRealizarPlanillaES() {
		return iControlRealizarPlanillaES;
	}
	

}
