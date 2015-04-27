package server;

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

import common.RootAndIp;

public class PrincipalServidor {

	public PrincipalServidor() {
		super();
	}

	public static void main(String[] args) {
		String conf = "";
		if ((args != null) && (args.length  > 0)) { 
			conf = args[0];
		}
		common.RootAndIp.setConf(conf);  
		try {
			// Directorio donde estan las clases
			String classPath = RootAndIp.getBase();

			// El Control que se publicara
			ControlFacturaCliente controlFacturaCliente =new ControlFacturaCliente();
			ControlFacturaProveedor controlFacturaProveedor = new ControlFacturaProveedor();
			ControlKiosco controlKiosco =new ControlKiosco();
			ControlLocalidad controlLocalidad = new ControlLocalidad();
			ControlMovimientoCaja controlMovimientoCaja =new ControlMovimientoCaja();
			ControlProducto controlProducto =new ControlProducto();
			ControlProveedor controlProveedor =new ControlProveedor();
			ControlProvincia controlProvincia = new ControlProvincia();
			ControlUsuario controlUsuario =new ControlUsuario();
			ControlBecaAsignada controlBecaAsignada = new ControlBecaAsignada();
			ControlSesion controlSesion =new ControlSesion();
			ControlRealizarPlanillaES controlRealizarPlanillaES =new ControlRealizarPlanillaES();
		
			// Creando, seteando e inicializando el Servidor
			Servidor servidor = new Servidor();
			servidor.setClassPath(classPath);
			// Controles
			servidor.setControlFacturaCliente(controlFacturaCliente);
			servidor.setControlFacturaProveedor(controlFacturaProveedor);
			servidor.setControlKiosco(controlKiosco);
			servidor.setControlLocalidad(controlLocalidad);
			servidor.setControlMovimientoCaja(controlMovimientoCaja);
			servidor.setControlProducto(controlProducto);
			servidor.setControlProveedor(controlProveedor);
			servidor.setControlProvincia(controlProvincia);
			servidor.setControlUsuario(controlUsuario);
			servidor.setControlBecaAsignada(controlBecaAsignada);
			servidor.setControlSesion(controlSesion);
			servidor.setControlRealizarPlanillaES(controlRealizarPlanillaES);
			servidor.iniciar();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
