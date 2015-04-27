package prueba;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class CargarDatos {
	
	public CargarDatos() {
	}

	public static void main(String[] args) throws UnknownHostException {
		System.out.println("ip local: "+InetAddress.getLocalHost());
		System.out.println(InetAddress.getLocalHost().getHostAddress());
	}
}
