package prueba;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import server.SingletonPersistencia;
import server.persistencia.dominio.Kiosco;
import server.persistencia.dominio.Localidad;
import server.persistencia.dominio.Producto;
import server.persistencia.dominio.Proveedor;
import server.persistencia.dominio.Provincia;
import server.persistencia.dominio.Usuario;

import common.RootAndIp;
import common.Utils;
import common.constantes.TIPO_USUARIOS;

public class CargarDatosBasicosBD {
    public CargarDatosBasicosBD() {
    }
  
    public static void main(String[] args) {
    	RootAndIp.setConf("conf.ini");
    	PersistenceManager pm = SingletonPersistencia.getInstance().getPM();
    	
    	System.out.println("Provincia...");
    	Provincia p1 = new Provincia();    		p1.setNombre("Córdoba");
    	
    	System.out.println("Localidad...");
    	Localidad l1 = new Localidad();	    	l1.setNombre("Río Cuarto");
    	l1.setCodPostal("5800");				l1.setProvincia(p1);
    	
    	System.out.println("Kiosco...");
    	Kiosco ki = new Kiosco();
    	ki.setNombre("Kiosco");			    	ki.setEncargado("...");
    	ki.setLocalidad(l1);			    	ki.setDireccion("...");
    	ki.setInicioAct(Utils.crearFecha(23,05,2012));
    	ki.setTelefono("");

    	System.out.println("Administrador...");
    	Usuario adm = new Usuario();
    	adm.setApellido("Administrador");		adm.setNombre("...");
    	adm.setDni(12345678); 			    	adm.setContrasenia("165-220-124-14-224-55-184-159");
    	adm.setDireccion("...");		    	adm.setLocalidad(l1);
    	adm.setTelefono("");					adm.setImporteBeca(0); 
    	adm.setNombUsuario("admin"); 	    	adm.setTipoUsuario(TIPO_USUARIOS.ADMINISTRADOR);
    	    	
    	System.out.println("Proveedores...");
    	
    	Proveedor deposito= new Proveedor();
    	deposito.setCodigo(new Long("1"));    	deposito.setNombre("Deposito");
    	deposito.setDireccion("-");		    	deposito.setLocalidad(l1);
    	deposito.setTelefono("");
    	
    	Proveedor panadero= new Proveedor();
    	panadero.setCodigo(new Long("2"));    	panadero.setNombre("Panadero");
    	panadero.setDireccion("-");		    	panadero.setLocalidad(l1);
    	panadero.setTelefono("");
    	
    	Proveedor pepsicoProv= new Proveedor();
    	pepsicoProv.setCodigo(new Long("3"));  	pepsicoProv.setNombre("Pepsico");
    	pepsicoProv.setDireccion("");	    	pepsicoProv.setLocalidad(l1);
    	pepsicoProv.setTelefono("");
    	
    	Proveedor delinter= new Proveedor();
    	delinter.setCodigo(new Long("4"));    	delinter.setNombre("Delinter");
    	delinter.setDireccion("-");		    	delinter.setLocalidad(l1);
    	delinter.setTelefono("");
    	
    	Proveedor alejandroAramayo= new Proveedor();
    	alejandroAramayo.setCodigo(new Long("5"));	  	alejandroAramayo.setNombre("Alejandro Aramayo");
    	alejandroAramayo.setDireccion("-");		    	alejandroAramayo.setLocalidad(l1);
    	alejandroAramayo.setTelefono("");
    	
    	Proveedor carmen = new Proveedor();
    	carmen.setCodigo(new Long("6"));	   	carmen.setNombre("Carmen");
    	carmen.setDireccion("-");		    	carmen.setLocalidad(l1);
    	carmen.setTelefono("");
    	
    	Proveedor carmenCarballo= new Proveedor();
    	carmenCarballo.setCodigo(new Long("7"));    	carmenCarballo.setNombre("Carmen Carballo");
    	carmenCarballo.setDireccion("-");		    	carmenCarballo.setLocalidad(l1);
    	carmenCarballo.setTelefono("");
    	
    	Proveedor chesi= new Proveedor();
    	chesi.setCodigo(new Long("8"));	    	chesi.setNombre("Chesi");
    	chesi.setDireccion("-");		    	chesi.setLocalidad(l1);
    	chesi.setTelefono("");
    	
    	Proveedor delinterPritty= new Proveedor();
    	delinterPritty.setCodigo(new Long("9"));    	delinterPritty.setNombre("Delinter y Pritty");	
    	delinterPritty.setDireccion("-");		    	delinterPritty.setLocalidad(l1);
    	delinterPritty.setTelefono("");
    	
    	Proveedor ivana= new Proveedor();
    	ivana.setCodigo(new Long("10"));    	ivana.setNombre("Ivana");
    	ivana.setDireccion("-");		    	ivana.setLocalidad(l1);
    	ivana.setTelefono("");
    	
    	Proveedor kiosco= new Proveedor();
    	kiosco.setCodigo(new Long("11"));    	kiosco.setNombre("Kiosco");
    	kiosco.setDireccion("-");		    	kiosco.setLocalidad(l1);
    	kiosco.setTelefono("");
    	
    	Proveedor pabloAlcoba= new Proveedor();
    	pabloAlcoba.setCodigo(new Long("12")); 	pabloAlcoba.setNombre("Pablo Alcoba");
    	pabloAlcoba.setDireccion("-");	    	pabloAlcoba.setLocalidad(l1);
    	pabloAlcoba.setTelefono("");
    	
    	Proveedor pepeVazquez= new Proveedor();
    	pepeVazquez.setCodigo(new Long("13"));	pepeVazquez.setNombre("Pepe Vazquez");
    	pepeVazquez.setDireccion("-");	    	pepeVazquez.setLocalidad(l1);  
    	pepeVazquez.setTelefono("");
    	
    	Proveedor pritty = new Proveedor();
    	pritty.setCodigo(new Long("14"));		pritty.setNombre("Pritty");
    	pritty.setDireccion("-");		    	pritty.setLocalidad(l1);
    	pritty.setTelefono("");
    	
    	Proveedor superm= new Proveedor();
    	superm.setCodigo(new Long("15"));		superm.setNombre("Super");
    	superm.setDireccion("-");		    	superm.setLocalidad(l1);
    	superm.setTelefono("");
    	
    	System.out.println("Productos...");
    	Producto gaseosa0 = new Producto();
    	gaseosa0.setNombre("Agua Mineral");
    	gaseosa0.setProveedor(delinterPritty);  gaseosa0.setCodigo(new Long("1000"));
    	gaseosa0.setStockActual(161);           gaseosa0.setStockMinimo(1);
    	gaseosa0.setPrecioEntrada(0.00);        gaseosa0.setPrecioVenta(4.00);
    	gaseosa0.setMargenGanancia(0);

    	Producto gaseosa1 = new Producto();
    	gaseosa1.setNombre("Agua Mineral De Litro Y  Medio");
    	gaseosa1.setProveedor(delinter);        gaseosa1.setCodigo(new Long("1001"));
    	gaseosa1.setStockActual(0);             gaseosa1.setStockMinimo(1);
    	gaseosa1.setPrecioEntrada(0.00);        gaseosa1.setPrecioVenta(8.00);
    	gaseosa1.setMargenGanancia(0);

    	Producto gaseosa2 = new Producto();
    	gaseosa2.setNombre("Gatorade");
    	gaseosa2.setProveedor(delinter);        gaseosa2.setCodigo(new Long("1002"));
    	gaseosa2.setStockActual(33);            gaseosa2.setStockMinimo(1);
    	gaseosa2.setPrecioEntrada(0.00);        gaseosa2.setPrecioVenta(7.00);
    	gaseosa2.setMargenGanancia(0);

    	Producto gaseosa3 = new Producto();
    	gaseosa3.setNombre("H2o Litro Y Medio");
    	gaseosa3.setProveedor(delinter);        gaseosa3.setCodigo(new Long("1003"));
    	gaseosa3.setStockActual(28);            gaseosa3.setStockMinimo(1);
    	gaseosa3.setPrecioEntrada(0.00);        gaseosa3.setPrecioVenta(10.00);
    	gaseosa3.setMargenGanancia(0);

    	Producto gaseosa4 = new Producto();
    	gaseosa4.setNombre("H2o Medio");
    	gaseosa4.setProveedor(delinter);        gaseosa4.setCodigo(new Long("1004"));
    	gaseosa4.setStockActual(126);           gaseosa4.setStockMinimo(1);
    	gaseosa4.setPrecioEntrada(0.00);        gaseosa4.setPrecioVenta(5.00);
    	gaseosa4.setMargenGanancia(0);

    	Producto gaseosa5 = new Producto();
    	gaseosa5.setNombre("Jugo Baggio De 200ml");
    	gaseosa5.setProveedor(deposito);        gaseosa5.setCodigo(new Long("1005"));
    	gaseosa5.setStockActual(0);             gaseosa5.setStockMinimo(1);
    	gaseosa5.setPrecioEntrada(0.00);        gaseosa5.setPrecioVenta(2.25);
    	gaseosa5.setMargenGanancia(0);

    	Producto gaseosa6 = new Producto();
    	gaseosa6.setNombre("Levite");
    	gaseosa6.setProveedor(chesi);           gaseosa6.setCodigo(new Long("1006"));
    	gaseosa6.setStockActual(0);             gaseosa6.setStockMinimo(1);
    	gaseosa6.setPrecioEntrada(0.00);        gaseosa6.setPrecioVenta(5.50);
    	gaseosa6.setMargenGanancia(0);

    	Producto gaseosa7 = new Producto();
    	gaseosa7.setNombre("Nestle");
    	gaseosa7.setProveedor(delinter);        gaseosa7.setCodigo(new Long("1007"));
    	gaseosa7.setStockActual(306);           gaseosa7.setStockMinimo(1);
    	gaseosa7.setPrecioEntrada(0.00);        gaseosa7.setPrecioVenta(4.50);
    	gaseosa7.setMargenGanancia(0);

    	Producto gaseosa8 = new Producto();
    	gaseosa8.setNombre("Pepsi Kick");
    	gaseosa8.setProveedor(delinter);        gaseosa8.setCodigo(new Long("1008"));
    	gaseosa8.setStockActual(0);             gaseosa8.setStockMinimo(1);
    	gaseosa8.setPrecioEntrada(0.00);        gaseosa8.setPrecioVenta(5.50);
    	gaseosa8.setMargenGanancia(0);

    	Producto gaseosa9 = new Producto();
    	gaseosa9.setNombre("Pepsi Litro Y Medio");
    	gaseosa9.setProveedor(delinter);        gaseosa9.setCodigo(new Long("1009"));
    	gaseosa9.setStockActual(3);             gaseosa9.setStockMinimo(1);
    	gaseosa9.setPrecioEntrada(0.00);        gaseosa9.setPrecioVenta(10.00);
    	gaseosa9.setMargenGanancia(0);

    	Producto gaseosa10 = new Producto();
    	gaseosa10.setNombre("Pepsi Medio");
    	gaseosa10.setProveedor(delinter);       gaseosa10.setCodigo(new Long("1010"));
    	gaseosa10.setStockActual(294);          gaseosa10.setStockMinimo(1);
    	gaseosa10.setPrecioEntrada(0.00);       gaseosa10.setPrecioVenta(4.50);
    	gaseosa10.setMargenGanancia(0);

    	Producto gaseosa11 = new Producto();
    	gaseosa11.setNombre("Pepsi Twister");
    	gaseosa11.setProveedor(delinter);       gaseosa11.setCodigo(new Long("1011"));
    	gaseosa11.setStockActual(67);           gaseosa11.setStockMinimo(1);
    	gaseosa11.setPrecioEntrada(0.00);       gaseosa11.setPrecioVenta(5.50);
    	gaseosa11.setMargenGanancia(0);

    	Producto gaseosa12 = new Producto();
    	gaseosa12.setNombre("Pepsi Vidrio");
    	gaseosa12.setProveedor(delinter);       gaseosa12.setCodigo(new Long("1012"));
    	gaseosa12.setStockActual(315);          gaseosa12.setStockMinimo(1);
    	gaseosa12.setPrecioEntrada(0.00);       gaseosa12.setPrecioVenta(3.00);
    	gaseosa12.setMargenGanancia(0);

    	Producto gaseosa13 = new Producto();
    	gaseosa13.setNombre("Pritty Litro");
    	gaseosa13.setProveedor(pritty);         gaseosa13.setCodigo(new Long("1013"));
    	gaseosa13.setStockActual(45);           gaseosa13.setStockMinimo(1);
    	gaseosa13.setPrecioEntrada(0.00);       gaseosa13.setPrecioVenta(7.00);
    	gaseosa13.setMargenGanancia(0);

    	Producto gaseosa14 = new Producto();
    	gaseosa14.setNombre("Pritty Litro Y Medio");
    	gaseosa14.setProveedor(pritty);         gaseosa14.setCodigo(new Long("1014"));
    	gaseosa14.setStockActual(7);            gaseosa14.setStockMinimo(1);
    	gaseosa14.setPrecioEntrada(0.00);       gaseosa14.setPrecioVenta(9.00);
    	gaseosa14.setMargenGanancia(0);

    	Producto gaseosa15 = new Producto();
    	gaseosa15.setNombre("Pritty Medio");
    	gaseosa15.setProveedor(pritty);         gaseosa15.setCodigo(new Long("1015"));
    	gaseosa15.setStockActual(35);           gaseosa15.setStockMinimo(1);
    	gaseosa15.setPrecioEntrada(0.00);       gaseosa15.setPrecioVenta(4.50);
    	gaseosa15.setMargenGanancia(0);

    	Producto gaseosa16 = new Producto();
    	gaseosa16.setNombre("Ser");
    	gaseosa16.setProveedor(chesi);          gaseosa16.setCodigo(new Long("1016"));
    	gaseosa16.setStockActual(0);            gaseosa16.setStockMinimo(1);
    	gaseosa16.setPrecioEntrada(0.00);       gaseosa16.setPrecioVenta(5.50);
    	gaseosa16.setMargenGanancia(0);

    	Producto alfajor0 = new Producto();
    	alfajor0.setNombre("Alfajorar Vauquita Dulce De Leche - Capuchino");
    	alfajor0.setProveedor(deposito);        alfajor0.setCodigo(new Long("2000"));
    	alfajor0.setStockActual(0);             alfajor0.setStockMinimo(1);
    	alfajor0.setPrecioEntrada(0.00);        alfajor0.setPrecioVenta(5.00);
    	alfajor0.setMargenGanancia(0);

    	Producto alfajor1 = new Producto();
    	alfajor1.setNombre("Alfajor Aguila");
    	alfajor1.setProveedor(deposito);        alfajor1.setCodigo(new Long("2001"));
    	alfajor1.setStockActual(0);             alfajor1.setStockMinimo(1);
    	alfajor1.setPrecioEntrada(0.00);        alfajor1.setPrecioVenta(5.50);
    	alfajor1.setMargenGanancia(0);

    	Producto alfajor2 = new Producto();
    	alfajor2.setNombre("Alfajor Cofler Simple");
    	alfajor2.setProveedor(deposito);        alfajor2.setCodigo(new Long("2002"));
    	alfajor2.setStockActual(0);             alfajor2.setStockMinimo(1);
    	alfajor2.setPrecioEntrada(0.00);        alfajor2.setPrecioVenta(3.25);
    	alfajor2.setMargenGanancia(0);

    	Producto alfajor3 = new Producto();
    	alfajor3.setNombre("Alfajor Lulemuu");
    	alfajor3.setProveedor(deposito);        alfajor3.setCodigo(new Long("2003"));
    	alfajor3.setStockActual(0);             alfajor3.setStockMinimo(1);
    	alfajor3.setPrecioEntrada(0.00);        alfajor3.setPrecioVenta(5.00);
    	alfajor3.setMargenGanancia(0);

    	Producto alfajor4 = new Producto();
    	alfajor4.setNombre("Alfajor Maizena");
    	alfajor4.setProveedor(deposito);        alfajor4.setCodigo(new Long("2004"));
    	alfajor4.setStockActual(0);             alfajor4.setStockMinimo(1);
    	alfajor4.setPrecioEntrada(0.00);        alfajor4.setPrecioVenta(3.00);
    	alfajor4.setMargenGanancia(0);

    	Producto alfajor5 = new Producto();
    	alfajor5.setNombre("Alfajor Mantecol");
    	alfajor5.setProveedor(deposito);        alfajor5.setCodigo(new Long("2005"));
    	alfajor5.setStockActual(0);             alfajor5.setStockMinimo(1);
    	alfajor5.setPrecioEntrada(0.00);        alfajor5.setPrecioVenta(3.50);
    	alfajor5.setMargenGanancia(0);

    	Producto alfajor6 = new Producto();
    	alfajor6.setNombre("Alfajor Milka/Oreo");
    	alfajor6.setProveedor(deposito);        alfajor6.setCodigo(new Long("2006"));
    	alfajor6.setStockActual(0);             alfajor6.setStockMinimo(1);
    	alfajor6.setPrecioEntrada(0.00);        alfajor6.setPrecioVenta(5.50);
    	alfajor6.setMargenGanancia(0);

    	Producto alfajor7 = new Producto();
    	alfajor7.setNombre("Alfajor Recoleta Premium");
    	alfajor7.setProveedor(deposito);        alfajor7.setCodigo(new Long("2007"));
    	alfajor7.setStockActual(0);             alfajor7.setStockMinimo(1);
    	alfajor7.setPrecioEntrada(0.00);        alfajor7.setPrecioVenta(5.00);
    	alfajor7.setMargenGanancia(0);

    	Producto alfajor8 = new Producto();
    	alfajor8.setNombre("Alfajor Vauquita Big");
    	alfajor8.setProveedor(deposito);        alfajor8.setCodigo(new Long("2008"));
    	alfajor8.setStockActual(0);             alfajor8.setStockMinimo(1);
    	alfajor8.setPrecioEntrada(0.00);        alfajor8.setPrecioVenta(6.00);
    	alfajor8.setMargenGanancia(0);

    	Producto alfajor9 = new Producto();
    	alfajor9.setNombre("Alfajor Vauquita Merengue");
    	alfajor9.setProveedor(deposito);        alfajor9.setCodigo(new Long("2009"));
    	alfajor9.setStockActual(0);             alfajor9.setStockMinimo(1);
    	alfajor9.setPrecioEntrada(0.00);        alfajor9.setPrecioVenta(6.00);
    	alfajor9.setMargenGanancia(0);

    	Producto alfajor10 = new Producto();
    	alfajor10.setNombre("Alfajor B/N");
    	alfajor10.setProveedor(deposito);        alfajor10.setCodigo(new Long("2010"));
    	alfajor10.setStockActual(0);             alfajor10.setStockMinimo(1);
    	alfajor10.setPrecioEntrada(0.00);        alfajor10.setPrecioVenta(4.00);
    	alfajor10.setMargenGanancia(0);

    	Producto alfajor11 = new Producto();
    	alfajor11.setNombre("Alfajor B/N Triple");
    	alfajor11.setProveedor(deposito);        alfajor11.setCodigo(new Long("2011"));
    	alfajor11.setStockActual(0);             alfajor11.setStockMinimo(1);
    	alfajor11.setPrecioEntrada(0.00);        alfajor11.setPrecioVenta(5.00);
    	alfajor11.setMargenGanancia(0);

    	Producto alfajor12 = new Producto();
    	alfajor12.setNombre("Alfajor Bon O Bon");
    	alfajor12.setProveedor(deposito);        alfajor12.setCodigo(new Long("2012"));
    	alfajor12.setStockActual(0);             alfajor12.setStockMinimo(1);
    	alfajor12.setPrecioEntrada(0.00);        alfajor12.setPrecioVenta(3.50);
    	alfajor12.setMargenGanancia(0);

    	Producto alfajor13 = new Producto();
    	alfajor13.setNombre("Alfajor Bon O Bon Triple");
    	alfajor13.setProveedor(deposito);        alfajor13.setCodigo(new Long("2013"));
    	alfajor13.setStockActual(0);             alfajor13.setStockMinimo(1);
    	alfajor13.setPrecioEntrada(0.00);        alfajor13.setPrecioVenta(5.50);
    	alfajor13.setMargenGanancia(0);

    	Producto alfajor14 = new Producto();
    	alfajor14.setNombre("Alfajor Cabsha");
    	alfajor14.setProveedor(deposito);        alfajor14.setCodigo(new Long("2014"));
    	alfajor14.setStockActual(0);             alfajor14.setStockMinimo(1);
    	alfajor14.setPrecioEntrada(0.00);        alfajor14.setPrecioVenta(3.00);
    	alfajor14.setMargenGanancia(0);

    	Producto alfajor15 = new Producto();
    	alfajor15.setNombre("Alfajor Cofler Block");
    	alfajor15.setProveedor(deposito);        alfajor15.setCodigo(new Long("2015"));
    	alfajor15.setStockActual(0);             alfajor15.setStockMinimo(1);
    	alfajor15.setPrecioEntrada(0.00);        alfajor15.setPrecioVenta(5.00);
    	alfajor15.setMargenGanancia(0);

    	Producto alfajor16 = new Producto();
    	alfajor16.setNombre("Alfajor Fantoche Triple");
    	alfajor16.setProveedor(deposito);        alfajor16.setCodigo(new Long("2016"));
    	alfajor16.setStockActual(0);             alfajor16.setStockMinimo(1);
    	alfajor16.setPrecioEntrada(0.00);        alfajor16.setPrecioVenta(4.00);
    	alfajor16.setMargenGanancia(0);

    	Producto alfajor17 = new Producto();
    	alfajor17.setNombre("Alfajor Fulbito");
    	alfajor17.setProveedor(deposito);        alfajor17.setCodigo(new Long("2017"));
    	alfajor17.setStockActual(0);             alfajor17.setStockMinimo(1);
    	alfajor17.setPrecioEntrada(0.00);        alfajor17.setPrecioVenta(0.75);
    	alfajor17.setMargenGanancia(0);

    	Producto alfajor18 = new Producto();
    	alfajor18.setNombre("Alfajor Gran Valle");
    	alfajor18.setProveedor(deposito);        alfajor18.setCodigo(new Long("2018"));
    	alfajor18.setStockActual(0);             alfajor18.setStockMinimo(1);
    	alfajor18.setPrecioEntrada(0.00);        alfajor18.setPrecioVenta(2.00);
    	alfajor18.setMargenGanancia(0);

    	Producto alfajor19 = new Producto();
    	alfajor19.setNombre("Alfajor Grandote Simple");
    	alfajor19.setProveedor(deposito);        alfajor19.setCodigo(new Long("2019"));
    	alfajor19.setStockActual(0);             alfajor19.setStockMinimo(1);
    	alfajor19.setPrecioEntrada(0.00);        alfajor19.setPrecioVenta(1.50);
    	alfajor19.setMargenGanancia(0);

    	Producto alfajor20 = new Producto();
    	alfajor20.setNombre("Alfajor Grandote Triple");
    	alfajor20.setProveedor(deposito);        alfajor20.setCodigo(new Long("2020"));
    	alfajor20.setStockActual(0);             alfajor20.setStockMinimo(1);
    	alfajor20.setPrecioEntrada(0.00);        alfajor20.setPrecioVenta(3.00);
    	alfajor20.setMargenGanancia(0);

    	Producto alfajor21 = new Producto();
    	alfajor21.setNombre("Alfajor Pepito");
    	alfajor21.setProveedor(deposito);        alfajor21.setCodigo(new Long("2021"));
    	alfajor21.setStockActual(0);             alfajor21.setStockMinimo(1);
    	alfajor21.setPrecioEntrada(0.00);        alfajor21.setPrecioVenta(5.50);
    	alfajor21.setMargenGanancia(0);

    	Producto alfajor22 = new Producto();
    	alfajor22.setNombre("Alfajor Tatin Simple");
    	alfajor22.setProveedor(deposito);        alfajor22.setCodigo(new Long("2022"));
    	alfajor22.setStockActual(64);            alfajor22.setStockMinimo(1);
    	alfajor22.setPrecioEntrada(0.00);        alfajor22.setPrecioVenta(1.50);
    	alfajor22.setMargenGanancia(0);

    	Producto alfajor23 = new Producto();
    	alfajor23.setNombre("Alfajor Tatin Triple");
    	alfajor23.setProveedor(deposito);        alfajor23.setCodigo(new Long("2023"));
    	alfajor23.setStockActual(0);             alfajor23.setStockMinimo(1);
    	alfajor23.setPrecioEntrada(0.00);        alfajor23.setPrecioVenta(3.00);
    	alfajor23.setMargenGanancia(0);

    	Producto alfajor24 = new Producto();
    	alfajor24.setNombre("Alfajor Terrabusi X 3");
    	alfajor24.setProveedor(deposito);        alfajor24.setCodigo(new Long("2024"));
    	alfajor24.setStockActual(0);             alfajor24.setStockMinimo(1);
    	alfajor24.setPrecioEntrada(0.00);        alfajor24.setPrecioVenta(5.50);
    	alfajor24.setMargenGanancia(0);

    	Producto alfajor25 = new Producto();
    	alfajor25.setNombre("Alfajor Tofi Triple");
    	alfajor25.setProveedor(deposito);        alfajor25.setCodigo(new Long("2025"));
    	alfajor25.setStockActual(0);             alfajor25.setStockMinimo(1);
    	alfajor25.setPrecioEntrada(0.00);        alfajor25.setPrecioVenta(5.50);
    	alfajor25.setMargenGanancia(0);

    	Producto alfajor26 = new Producto();
    	alfajor26.setNombre("Alfajor Trishot");
    	alfajor26.setProveedor(deposito);        alfajor26.setCodigo(new Long("2026"));
    	alfajor26.setStockActual(0);             alfajor26.setStockMinimo(1);
    	alfajor26.setPrecioEntrada(0.00);        alfajor26.setPrecioVenta(5.50);
    	alfajor26.setMargenGanancia(0);

    	Producto barraCereal0 = new Producto();
    	barraCereal0.setNombre("Barra Cereal Fort");
    	barraCereal0.setProveedor(deposito);    barraCereal0.setCodigo(new Long("3000"));
    	barraCereal0.setStockActual(134);       barraCereal0.setStockMinimo(1);
    	barraCereal0.setPrecioEntrada(0.00);    barraCereal0.setPrecioVenta(3.00);
    	barraCereal0.setMargenGanancia(0);

    	Producto barraCereal1 = new Producto();
    	barraCereal1.setNombre("Barra Cereal Mix");
    	barraCereal1.setProveedor(deposito);    barraCereal1.setCodigo(new Long("3001"));
    	barraCereal1.setStockActual(0);         barraCereal1.setStockMinimo(1);
    	barraCereal1.setPrecioEntrada(0.00);    barraCereal1.setPrecioVenta(3.50);
    	barraCereal1.setMargenGanancia(0);

    	Producto barraCereal2 = new Producto();
    	barraCereal2.setNombre("Barra Cocolate");
    	barraCereal2.setProveedor(deposito);    barraCereal2.setCodigo(new Long("3002"));
    	barraCereal2.setStockActual(0);         barraCereal2.setStockMinimo(1);
    	barraCereal2.setPrecioEntrada(0.00);    barraCereal2.setPrecioVenta(2.00);
    	barraCereal2.setMargenGanancia(0);

    	Producto barraCereal3 = new Producto();
    	barraCereal3.setNombre("Barra Croco");
    	barraCereal3.setProveedor(deposito);    barraCereal3.setCodigo(new Long("3003"));
    	barraCereal3.setStockActual(0);         barraCereal3.setStockMinimo(1);
    	barraCereal3.setPrecioEntrada(0.00);    barraCereal3.setPrecioVenta(3.75);
    	barraCereal3.setMargenGanancia(0);

    	Producto barraCereal4 = new Producto();
    	barraCereal4.setNombre("Barra Quaker");
    	barraCereal4.setProveedor(pepsicoProv); barraCereal4.setCodigo(new Long("3004"));
    	barraCereal4.setStockActual(24);        barraCereal4.setStockMinimo(1);
    	barraCereal4.setPrecioEntrada(0.00);    barraCereal4.setPrecioVenta(2.75);
    	barraCereal4.setMargenGanancia(0);

    	Producto barraCereal5 = new Producto();
    	barraCereal5.setNombre("Barras Ser");
    	barraCereal5.setProveedor(deposito);    barraCereal5.setCodigo(new Long("3005"));
    	barraCereal5.setStockActual(20);        barraCereal5.setStockMinimo(1);
    	barraCereal5.setPrecioEntrada(0.00);    barraCereal5.setPrecioVenta(4.25);
    	barraCereal5.setMargenGanancia(0);

    	Producto barraCereal6 = new Producto();
    	barraCereal6.setNombre("Torroncino");
    	barraCereal6.setProveedor(deposito);    barraCereal6.setCodigo(new Long("3006"));
    	barraCereal6.setStockActual(86);        barraCereal6.setStockMinimo(1);
    	barraCereal6.setPrecioEntrada(0.00);    barraCereal6.setPrecioVenta(3.25);
    	barraCereal6.setMargenGanancia(0);

    	Producto caramelo0 = new Producto();
    	caramelo0.setNombre("Caramelos De 0,30");
    	caramelo0.setProveedor(deposito);       caramelo0.setCodigo(new Long("4000"));
    	caramelo0.setStockActual(0);            caramelo0.setStockMinimo(1);
    	caramelo0.setPrecioEntrada(0.00);       caramelo0.setPrecioVenta(0.30);
    	caramelo0.setMargenGanancia(0);

    	Producto caramelo1 = new Producto();
    	caramelo1.setNombre("Caramelos Fizz");
    	caramelo1.setProveedor(deposito);       caramelo1.setCodigo(new Long("4001"));
    	caramelo1.setStockActual(0);            caramelo1.setStockMinimo(1);
    	caramelo1.setPrecioEntrada(0.00);       caramelo1.setPrecioVenta(1.00);
    	caramelo1.setMargenGanancia(0);

    	Producto caramelo2 = new Producto();
    	caramelo2.setNombre("Caramelos Surtidos X  0,25");
    	caramelo2.setProveedor(deposito);       caramelo2.setCodigo(new Long("4002"));
    	caramelo2.setStockActual(97);           caramelo2.setStockMinimo(1);
    	caramelo2.setPrecioEntrada(0.00);       caramelo2.setPrecioVenta(0.25);
    	caramelo2.setMargenGanancia(0);

    	Producto caramelo3 = new Producto();
    	caramelo3.setNombre("Caramelos Surtidos X  0,50");
    	caramelo3.setProveedor(deposito);       caramelo3.setCodigo(new Long("4003"));
    	caramelo3.setStockActual(0);            caramelo3.setStockMinimo(1);
    	caramelo3.setPrecioEntrada(0.00);       caramelo3.setPrecioVenta(0.50);
    	caramelo3.setMargenGanancia(0);

    	Producto caramelo4 = new Producto();
    	caramelo4.setNombre("Caramelos Surtidos X  0.07");
    	caramelo4.setProveedor(deposito);       caramelo4.setCodigo(new Long("4004"));
    	caramelo4.setStockActual(0);            caramelo4.setStockMinimo(1);
    	caramelo4.setPrecioEntrada(0.00);       caramelo4.setPrecioVenta(0.07);
    	caramelo4.setMargenGanancia(0);

    	Producto caramelo5 = new Producto();
    	caramelo5.setNombre("Caramelos Surtidos X  0.10");
    	caramelo5.setProveedor(deposito);       caramelo5.setCodigo(new Long("4005"));
    	caramelo5.setStockActual(0);            caramelo5.setStockMinimo(1);
    	caramelo5.setPrecioEntrada(0.00);       caramelo5.setPrecioVenta(0.10);
    	caramelo5.setMargenGanancia(0);

    	Producto caramelo6 = new Producto();
    	caramelo6.setNombre("Caramelos Surtidos X 0.2");
    	caramelo6.setProveedor(deposito);       caramelo6.setCodigo(new Long("4006"));
    	caramelo6.setStockActual(365);          caramelo6.setStockMinimo(1);
    	caramelo6.setPrecioEntrada(0.00);       caramelo6.setPrecioVenta(0.20);
    	caramelo6.setMargenGanancia(0);

    	Producto caramelo7 = new Producto();
    	caramelo7.setNombre("Caramelos Surtidos X 0.15");
    	caramelo7.setProveedor(deposito);       caramelo7.setCodigo(new Long("4007"));
    	caramelo7.setStockActual(319);          caramelo7.setStockMinimo(1);
    	caramelo7.setPrecioEntrada(0.00);       caramelo7.setPrecioVenta(0.15);
    	caramelo7.setMargenGanancia(0);

    	Producto caramelo8 = new Producto();
    	caramelo8.setNombre("Chupetin Cosmo Comun");
    	caramelo8.setProveedor(deposito);       caramelo8.setCodigo(new Long("4008"));
    	caramelo8.setStockActual(0);            caramelo8.setStockMinimo(1);
    	caramelo8.setPrecioEntrada(0.00);       caramelo8.setPrecioVenta(0.50);
    	caramelo8.setMargenGanancia(0);

    	Producto caramelo9 = new Producto();
    	caramelo9.setNombre("Chupetin Mr Pop Trompito");
    	caramelo9.setProveedor(deposito);       caramelo9.setCodigo(new Long("4009"));
    	caramelo9.setStockActual(13);           caramelo9.setStockMinimo(1);
    	caramelo9.setPrecioEntrada(0.00);       caramelo9.setPrecioVenta(0.50);
    	caramelo9.setMargenGanancia(0);

    	Producto caramelo10 = new Producto();
    	caramelo10.setNombre("Chupetin Pop Evolution");
    	caramelo10.setProveedor(deposito);       caramelo10.setCodigo(new Long("4010"));
    	caramelo10.setStockActual(0);            caramelo10.setStockMinimo(1);
    	caramelo10.setPrecioEntrada(0.00);       caramelo10.setPrecioVenta(0.50);
    	caramelo10.setMargenGanancia(0);

    	Producto caramelo11 = new Producto();
    	caramelo11.setNombre("Halls");
    	caramelo11.setProveedor(deposito);       caramelo11.setCodigo(new Long("4011"));
    	caramelo11.setStockActual(92);           caramelo11.setStockMinimo(1);
    	caramelo11.setPrecioEntrada(0.00);       caramelo11.setPrecioVenta(2.50);
    	caramelo11.setMargenGanancia(0);

    	Producto caramelo12 = new Producto();
    	caramelo12.setNombre("Halls Free");
    	caramelo12.setProveedor(deposito);       caramelo12.setCodigo(new Long("4012"));
    	caramelo12.setStockActual(69);           caramelo12.setStockMinimo(1);
    	caramelo12.setPrecioEntrada(0.00);       caramelo12.setPrecioVenta(2.75);
    	caramelo12.setMargenGanancia(0);

    	Producto caramelo13 = new Producto();
    	caramelo13.setNombre("Halls Xs");
    	caramelo13.setProveedor(deposito);       caramelo13.setCodigo(new Long("4013"));
    	caramelo13.setStockActual(0);            caramelo13.setStockMinimo(1);
    	caramelo13.setPrecioEntrada(0.00);       caramelo13.setPrecioVenta(3.50);
    	caramelo13.setMargenGanancia(0);

    	Producto caramelo14 = new Producto();
    	caramelo14.setNombre("La Yapa");
    	caramelo14.setProveedor(deposito);       caramelo14.setCodigo(new Long("4014"));
    	caramelo14.setStockActual(0);            caramelo14.setStockMinimo(1);
    	caramelo14.setPrecioEntrada(0.00);       caramelo14.setPrecioVenta(1.00);
    	caramelo14.setMargenGanancia(0);

    	Producto caramelo15 = new Producto();
    	caramelo15.setNombre("Lotza Fizz");
    	caramelo15.setProveedor(deposito);       caramelo15.setCodigo(new Long("4015"));
    	caramelo15.setStockActual(0);            caramelo15.setStockMinimo(1);
    	caramelo15.setPrecioEntrada(0.00);       caramelo15.setPrecioVenta(1.00);
    	caramelo15.setMargenGanancia(0);

    	Producto caramelo16 = new Producto();
    	caramelo16.setNombre("Menthoplus");
    	caramelo16.setProveedor(deposito);       caramelo16.setCodigo(new Long("4016"));
    	caramelo16.setStockActual(73);           caramelo16.setStockMinimo(1);
    	caramelo16.setPrecioEntrada(0.00);       caramelo16.setPrecioVenta(2.25);
    	caramelo16.setMargenGanancia(0);

    	Producto caramelo17 = new Producto();
    	caramelo17.setNombre("Menthoplus Sin Azucar");
    	caramelo17.setProveedor(deposito);       caramelo17.setCodigo(new Long("4017"));
    	caramelo17.setStockActual(70);           caramelo17.setStockMinimo(1);
    	caramelo17.setPrecioEntrada(0.00);       caramelo17.setPrecioVenta(2.75);
    	caramelo17.setMargenGanancia(0);

    	Producto caramelo18 = new Producto();
    	caramelo18.setNombre("Mentitas");
    	caramelo18.setProveedor(deposito);       caramelo18.setCodigo(new Long("4018"));
    	caramelo18.setStockActual(0);            caramelo18.setStockMinimo(1);
    	caramelo18.setPrecioEntrada(0.00);       caramelo18.setPrecioVenta(2.00);
    	caramelo18.setMargenGanancia(0);

    	Producto caramelo19 = new Producto();
    	caramelo19.setNombre("Pastillas Drf");
    	caramelo19.setProveedor(deposito);       caramelo19.setCodigo(new Long("4019"));
    	caramelo19.setStockActual(2);            caramelo19.setStockMinimo(1);
    	caramelo19.setPrecioEntrada(0.00);       caramelo19.setPrecioVenta(1.75);
    	caramelo19.setMargenGanancia(0);

    	Producto caramelo20 = new Producto();
    	caramelo20.setNombre("Pastillas Mensajitos");
    	caramelo20.setProveedor(deposito);       caramelo20.setCodigo(new Long("4020"));
    	caramelo20.setStockActual(0);            caramelo20.setStockMinimo(1);
    	caramelo20.setPrecioEntrada(0.00);       caramelo20.setPrecioVenta(1.50);
    	caramelo20.setMargenGanancia(0);

    	Producto caramelo21 = new Producto();
    	caramelo21.setNombre("Pico Dulce");
    	caramelo21.setProveedor(deposito);       caramelo21.setCodigo(new Long("4021"));
    	caramelo21.setStockActual(0);            caramelo21.setStockMinimo(1);
    	caramelo21.setPrecioEntrada(0.00);       caramelo21.setPrecioVenta(1.00);
    	caramelo21.setMargenGanancia(0);

    	Producto caramelo22 = new Producto();
    	caramelo22.setNombre("Tic Tac");
    	caramelo22.setProveedor(deposito);       caramelo22.setCodigo(new Long("4022"));
    	caramelo22.setStockActual(52);           caramelo22.setStockMinimo(1);
    	caramelo22.setPrecioEntrada(0.00);       caramelo22.setPrecioVenta(3.00);
    	caramelo22.setMargenGanancia(0);

    	Producto chicle0 = new Producto();
    	chicle0.setNombre("Belden Infinit");
    	chicle0.setProveedor(deposito);         chicle0.setCodigo(new Long("5000"));
    	chicle0.setStockActual(0);              chicle0.setStockMinimo(1);
    	chicle0.setPrecioEntrada(0.00);         chicle0.setPrecioVenta(7.50);
    	chicle0.setMargenGanancia(0);

    	Producto chicle1 = new Producto();
    	chicle1.setNombre("Beldent");
    	chicle1.setProveedor(deposito);         chicle1.setCodigo(new Long("5001"));
    	chicle1.setStockActual(415);            chicle1.setStockMinimo(1);
    	chicle1.setPrecioEntrada(0.00);         chicle1.setPrecioVenta(2.50);
    	chicle1.setMargenGanancia(0);

    	Producto chicle2 = new Producto();
    	chicle2.setNombre("Beldent Sensation");
    	chicle2.setProveedor(deposito);         chicle2.setCodigo(new Long("5002"));
    	chicle2.setStockActual(0);              chicle2.setStockMinimo(1);
    	chicle2.setPrecioEntrada(0.00);         chicle2.setPrecioVenta(5.00);
    	chicle2.setMargenGanancia(0);

    	Producto chicle3 = new Producto();
    	chicle3.setNombre("Bubbaloo");
    	chicle3.setProveedor(deposito);         chicle3.setCodigo(new Long("5003"));
    	chicle3.setStockActual(0);              chicle3.setStockMinimo(1);
    	chicle3.setPrecioEntrada(0.00);         chicle3.setPrecioVenta(0.25);
    	chicle3.setMargenGanancia(0);

    	Producto chicle4 = new Producto();
    	chicle4.setNombre("Poosh");
    	chicle4.setProveedor(deposito);         chicle4.setCodigo(new Long("5004"));
    	chicle4.setStockActual(0);              chicle4.setStockMinimo(1);
    	chicle4.setPrecioEntrada(0.00);         chicle4.setPrecioVenta(0.25);
    	chicle4.setMargenGanancia(0);

    	Producto chicle5 = new Producto();
    	chicle5.setNombre("Topline");
    	chicle5.setProveedor(deposito);         chicle5.setCodigo(new Long("5005"));
    	chicle5.setStockActual(272);            chicle5.setStockMinimo(1);
    	chicle5.setPrecioEntrada(0.00);         chicle5.setPrecioVenta(1.75);
    	chicle5.setMargenGanancia(0);

    	Producto chicle6 = new Producto();
    	chicle6.setNombre("Topline Seven");
    	chicle6.setProveedor(deposito);         chicle6.setCodigo(new Long("5006"));
    	chicle6.setStockActual(22);             chicle6.setStockMinimo(1);
    	chicle6.setPrecioEntrada(0.00);         chicle6.setPrecioVenta(3.75);
    	chicle6.setMargenGanancia(0);

    	Producto chocolate0 = new Producto();
    	chocolate0.setNombre("Bananita Dolca Chica");
    	chocolate0.setProveedor(deposito);      chocolate0.setCodigo(new Long("6000"));
    	chocolate0.setStockActual(0);           chocolate0.setStockMinimo(1);
    	chocolate0.setPrecioEntrada(0.00);      chocolate0.setPrecioVenta(2.50);
    	chocolate0.setMargenGanancia(0);

    	Producto chocolate1 = new Producto();
    	chocolate1.setNombre("Bananita Dolca Grande");
    	chocolate1.setProveedor(deposito);      chocolate1.setCodigo(new Long("6001"));
    	chocolate1.setStockActual(0);           chocolate1.setStockMinimo(1);
    	chocolate1.setPrecioEntrada(0.00);      chocolate1.setPrecioVenta(4.00);
    	chocolate1.setMargenGanancia(0);

    	Producto chocolate2 = new Producto();
    	chocolate2.setNombre("Barra Bon O Bon");
    	chocolate2.setProveedor(deposito);      chocolate2.setCodigo(new Long("6002"));
    	chocolate2.setStockActual(0);           chocolate2.setStockMinimo(1);
    	chocolate2.setPrecioEntrada(0.00);      chocolate2.setPrecioVenta(2.75);
    	chocolate2.setMargenGanancia(0);

    	Producto chocolate3 = new Producto();
    	chocolate3.setNombre("Barrita Baton");
    	chocolate3.setProveedor(deposito);      chocolate3.setCodigo(new Long("6003"));
    	chocolate3.setStockActual(0);           chocolate3.setStockMinimo(1);
    	chocolate3.setPrecioEntrada(0.00);      chocolate3.setPrecioVenta(1.25);
    	chocolate3.setMargenGanancia(0);

    	Producto chocolate4 = new Producto();
    	chocolate4.setNombre("Bocadito Bonafide");
    	chocolate4.setProveedor(deposito);      chocolate4.setCodigo(new Long("6004"));
    	chocolate4.setStockActual(4);           chocolate4.setStockMinimo(1);
    	chocolate4.setPrecioEntrada(0.00);      chocolate4.setPrecioVenta(2.00);
    	chocolate4.setMargenGanancia(0);

    	Producto chocolate5 = new Producto();
    	chocolate5.setNombre("Bocadito Cabsha");
    	chocolate5.setProveedor(deposito);      chocolate5.setCodigo(new Long("6005"));
    	chocolate5.setStockActual(29);          chocolate5.setStockMinimo(1);
    	chocolate5.setPrecioEntrada(0.00);      chocolate5.setPrecioVenta(1.50);
    	chocolate5.setMargenGanancia(0);

    	Producto chocolate6 = new Producto();
    	chocolate6.setNombre("Bocadito Holanda");
    	chocolate6.setProveedor(deposito);      chocolate6.setCodigo(new Long("6006"));
    	chocolate6.setStockActual(4);           chocolate6.setStockMinimo(1);
    	chocolate6.setPrecioEntrada(0.00);      chocolate6.setPrecioVenta(0.75);
    	chocolate6.setMargenGanancia(0);

    	Producto chocolate7 = new Producto();
    	chocolate7.setNombre("Bombon Tofi");
    	chocolate7.setProveedor(deposito);      chocolate7.setCodigo(new Long("6007"));
    	chocolate7.setStockActual(0);           chocolate7.setStockMinimo(1);
    	chocolate7.setPrecioEntrada(0.00);      chocolate7.setPrecioVenta(2.00);
    	chocolate7.setMargenGanancia(0);

    	Producto chocolate8 = new Producto();
    	chocolate8.setNombre("Bon O Bon");
    	chocolate8.setProveedor(deposito);      chocolate8.setCodigo(new Long("6008"));
    	chocolate8.setStockActual(32);          chocolate8.setStockMinimo(1);
    	chocolate8.setPrecioEntrada(0.00);      chocolate8.setPrecioVenta(1.75);
    	chocolate8.setMargenGanancia(0);

    	Producto chocolate9 = new Producto();
    	chocolate9.setNombre("Cadbury 3 Sueños");
    	chocolate9.setProveedor(deposito);      chocolate9.setCodigo(new Long("6009"));
    	chocolate9.setStockActual(0);           chocolate9.setStockMinimo(1);
    	chocolate9.setPrecioEntrada(0.00);      chocolate9.setPrecioVenta(5.00);
    	chocolate9.setMargenGanancia(0);

    	Producto chocolate10 = new Producto();
    	chocolate10.setNombre("Cadbury Chico");
    	chocolate10.setProveedor(deposito);      chocolate10.setCodigo(new Long("6010"));
    	chocolate10.setStockActual(0);           chocolate10.setStockMinimo(1);
    	chocolate10.setPrecioEntrada(0.00);      chocolate10.setPrecioVenta(4.00);
    	chocolate10.setMargenGanancia(0);

    	Producto chocolate11 = new Producto();
    	chocolate11.setNombre("Cadbury Frutilla");
    	chocolate11.setProveedor(deposito);      chocolate11.setCodigo(new Long("6011"));
    	chocolate11.setStockActual(0);           chocolate11.setStockMinimo(1);
    	chocolate11.setPrecioEntrada(0.00);      chocolate11.setPrecioVenta(2.50);
    	chocolate11.setMargenGanancia(0);

    	Producto chocolate12 = new Producto();
    	chocolate12.setNombre("Cadbury Grande");
    	chocolate12.setProveedor(deposito);      chocolate12.setCodigo(new Long("6012"));
    	chocolate12.setStockActual(0);           chocolate12.setStockMinimo(1);
    	chocolate12.setPrecioEntrada(0.00);      chocolate12.setPrecioVenta(9.00);
    	chocolate12.setMargenGanancia(0);

    	Producto chocolate13 = new Producto();
    	chocolate13.setNombre("Chocolate  Misky");
    	chocolate13.setProveedor(deposito);      chocolate13.setCodigo(new Long("6013"));
    	chocolate13.setStockActual(0);           chocolate13.setStockMinimo(1);
    	chocolate13.setPrecioEntrada(0.00);      chocolate13.setPrecioVenta(2.00);
    	chocolate13.setMargenGanancia(0);

    	Producto chocolate14 = new Producto();
    	chocolate14.setNombre("Chocolatin Misky Chico");
    	chocolate14.setProveedor(deposito);      chocolate14.setCodigo(new Long("6014"));
    	chocolate14.setStockActual(0);           chocolate14.setStockMinimo(1);
    	chocolate14.setPrecioEntrada(0.00);      chocolate14.setPrecioVenta(0.75);
    	chocolate14.setMargenGanancia(0);

    	Producto chocolate15 = new Producto();
    	chocolate15.setNombre("Cofler Aireado 30 Gr");
    	chocolate15.setProveedor(deposito);      chocolate15.setCodigo(new Long("6015"));
    	chocolate15.setStockActual(10);          chocolate15.setStockMinimo(1);
    	chocolate15.setPrecioEntrada(0.00);      chocolate15.setPrecioVenta(4.00);
    	chocolate15.setMargenGanancia(0);

    	Producto chocolate16 = new Producto();
    	chocolate16.setNombre("Cofler Aireado 60 Gr");
    	chocolate16.setProveedor(deposito);      chocolate16.setCodigo(new Long("6016"));
    	chocolate16.setStockActual(0);           chocolate16.setStockMinimo(1);
    	chocolate16.setPrecioEntrada(0.00);      chocolate16.setPrecioVenta(7.00);
    	chocolate16.setMargenGanancia(0);

    	Producto chocolate17 = new Producto();
    	chocolate17.setNombre("Cofler Block");
    	chocolate17.setProveedor(deposito);      chocolate17.setCodigo(new Long("6017"));
    	chocolate17.setStockActual(0);           chocolate17.setStockMinimo(1);
    	chocolate17.setPrecioEntrada(0.00);      chocolate17.setPrecioVenta(4.00);
    	chocolate17.setMargenGanancia(0);

    	Producto chocolate18 = new Producto();
    	chocolate18.setNombre("Cofler Dos Placeres");
    	chocolate18.setProveedor(deposito);      chocolate18.setCodigo(new Long("6018"));
    	chocolate18.setStockActual(0);           chocolate18.setStockMinimo(1);
    	chocolate18.setPrecioEntrada(0.00);      chocolate18.setPrecioVenta(7.50);
    	chocolate18.setMargenGanancia(0);

    	Producto chocolate19 = new Producto();
    	chocolate19.setNombre("Cofler Extra");
    	chocolate19.setProveedor(deposito);      chocolate19.setCodigo(new Long("6019"));
    	chocolate19.setStockActual(16);          chocolate19.setStockMinimo(1);
    	chocolate19.setPrecioEntrada(0.00);      chocolate19.setPrecioVenta(3.50);
    	chocolate19.setMargenGanancia(0);

    	Producto chocolate20 = new Producto();
    	chocolate20.setNombre("Cofler Leche");
    	chocolate20.setProveedor(deposito);      chocolate20.setCodigo(new Long("6020"));
    	chocolate20.setStockActual(0);           chocolate20.setStockMinimo(1);
    	chocolate20.setPrecioEntrada(0.00);      chocolate20.setPrecioVenta(4.00);
    	chocolate20.setMargenGanancia(0);

    	Producto chocolate21 = new Producto();
    	chocolate21.setNombre("Cofler Light Frutilla");
    	chocolate21.setProveedor(deposito);      chocolate21.setCodigo(new Long("6021"));
    	chocolate21.setStockActual(0);           chocolate21.setStockMinimo(1);
    	chocolate21.setPrecioEntrada(0.00);      chocolate21.setPrecioVenta(4.00);
    	chocolate21.setMargenGanancia(0);

    	Producto chocolate22 = new Producto();
    	chocolate22.setNombre("Cofler Rally");
    	chocolate22.setProveedor(deposito);      chocolate22.setCodigo(new Long("6022"));
    	chocolate22.setStockActual(0);           chocolate22.setStockMinimo(1);
    	chocolate22.setPrecioEntrada(0.00);      chocolate22.setPrecioVenta(2.75);
    	chocolate22.setMargenGanancia(0);

    	Producto chocolate23 = new Producto();
    	chocolate23.setNombre("Cubanito");
    	chocolate23.setProveedor(deposito);      chocolate23.setCodigo(new Long("6023"));
    	chocolate23.setStockActual(0);           chocolate23.setStockMinimo(1);
    	chocolate23.setPrecioEntrada(0.00);      chocolate23.setPrecioVenta(1.50);
    	chocolate23.setMargenGanancia(0);

    	Producto chocolate24 = new Producto();
    	chocolate24.setNombre("Dos  Corazones");
    	chocolate24.setProveedor(deposito);      chocolate24.setCodigo(new Long("6024"));
    	chocolate24.setStockActual(0);           chocolate24.setStockMinimo(1);
    	chocolate24.setPrecioEntrada(0.00);      chocolate24.setPrecioVenta(3.50);
    	chocolate24.setMargenGanancia(0);

    	Producto chocolate25 = new Producto();
    	chocolate25.setNombre("Espacial Fort");
    	chocolate25.setProveedor(deposito);      chocolate25.setCodigo(new Long("6025"));
    	chocolate25.setStockActual(0);           chocolate25.setStockMinimo(1);
    	chocolate25.setPrecioEntrada(0.00);      chocolate25.setPrecioVenta(2.75);
    	chocolate25.setMargenGanancia(0);

    	Producto chocolate26 = new Producto();
    	chocolate26.setNombre("Felling");
    	chocolate26.setProveedor(deposito);      chocolate26.setCodigo(new Long("6026"));
    	chocolate26.setStockActual(0);           chocolate26.setStockMinimo(1);
    	chocolate26.setPrecioEntrada(0.00);      chocolate26.setPrecioVenta(2.75);
    	chocolate26.setMargenGanancia(0);

    	Producto chocolate27 = new Producto();
    	chocolate27.setNombre("Hamlet");
    	chocolate27.setProveedor(deposito);      chocolate27.setCodigo(new Long("6027"));
    	chocolate27.setStockActual(18);          chocolate27.setStockMinimo(1);
    	chocolate27.setPrecioEntrada(0.00);      chocolate27.setPrecioVenta(3.00);
    	chocolate27.setMargenGanancia(0);

    	Producto chocolate28 = new Producto();
    	chocolate28.setNombre("Kinder Bueno");
    	chocolate28.setProveedor(deposito);      chocolate28.setCodigo(new Long("6028"));
    	chocolate28.setStockActual(0);           chocolate28.setStockMinimo(1);
    	chocolate28.setPrecioEntrada(0.00);      chocolate28.setPrecioVenta(6.25);
    	chocolate28.setMargenGanancia(0);

    	Producto chocolate29 = new Producto();
    	chocolate29.setNombre("Kinder Chocolate 50 Gr");
    	chocolate29.setProveedor(deposito);      chocolate29.setCodigo(new Long("6029"));
    	chocolate29.setStockActual(0);           chocolate29.setStockMinimo(1);
    	chocolate29.setPrecioEntrada(0.00);      chocolate29.setPrecioVenta(0.00);
    	chocolate29.setMargenGanancia(0);

    	Producto chocolate30 = new Producto();
    	chocolate30.setNombre("Kinder Maxi 21 Gr");
    	chocolate30.setProveedor(deposito);      chocolate30.setCodigo(new Long("6030"));
    	chocolate30.setStockActual(0);           chocolate30.setStockMinimo(1);
    	chocolate30.setPrecioEntrada(0.00);      chocolate30.setPrecioVenta(4.00);
    	chocolate30.setMargenGanancia(0);

    	Producto chocolate31 = new Producto();
    	chocolate31.setNombre("Mantecol");
    	chocolate31.setProveedor(deposito);      chocolate31.setCodigo(new Long("6031"));
    	chocolate31.setStockActual(0);           chocolate31.setStockMinimo(1);
    	chocolate31.setPrecioEntrada(0.00);      chocolate31.setPrecioVenta(0.00);
    	chocolate31.setMargenGanancia(0);

    	Producto chocolate32 = new Producto();
    	chocolate32.setNombre("Marroc");
    	chocolate32.setProveedor(deposito);      chocolate32.setCodigo(new Long("6032"));
    	chocolate32.setStockActual(0);           chocolate32.setStockMinimo(1);
    	chocolate32.setPrecioEntrada(0.00);      chocolate32.setPrecioVenta(2.00);
    	chocolate32.setMargenGanancia(0);

    	Producto chocolate33 = new Producto();
    	chocolate33.setNombre("Minitorta Rabsodia");
    	chocolate33.setProveedor(deposito);      chocolate33.setCodigo(new Long("6033"));
    	chocolate33.setStockActual(0);           chocolate33.setStockMinimo(1);
    	chocolate33.setPrecioEntrada(0.00);      chocolate33.setPrecioVenta(3.00);
    	chocolate33.setMargenGanancia(0);

    	Producto chocolate34 = new Producto();
    	chocolate34.setNombre("Nugaton");
    	chocolate34.setProveedor(deposito);      chocolate34.setCodigo(new Long("6034"));
    	chocolate34.setStockActual(0);           chocolate34.setStockMinimo(1);
    	chocolate34.setPrecioEntrada(0.00);      chocolate34.setPrecioVenta(3.00);
    	chocolate34.setMargenGanancia(0);

    	Producto chocolate35 = new Producto();
    	chocolate35.setNombre("Obleas Bon O Bon");
    	chocolate35.setProveedor(deposito);      chocolate35.setCodigo(new Long("6035"));
    	chocolate35.setStockActual(0);           chocolate35.setStockMinimo(1);
    	chocolate35.setPrecioEntrada(0.00);      chocolate35.setPrecioVenta(2.75);
    	chocolate35.setMargenGanancia(0);

    	Producto chocolate36 = new Producto();
    	chocolate36.setNombre("Pic-Nic");
    	chocolate36.setProveedor(deposito);      chocolate36.setCodigo(new Long("6036"));
    	chocolate36.setStockActual(0);           chocolate36.setStockMinimo(1);
    	chocolate36.setPrecioEntrada(0.00);      chocolate36.setPrecioVenta(1.50);
    	chocolate36.setMargenGanancia(0);

    	Producto chocolate37 = new Producto();
    	chocolate37.setNombre("Rhodesia");
    	chocolate37.setProveedor(deposito);      chocolate37.setCodigo(new Long("6037"));
    	chocolate37.setStockActual(0);           chocolate37.setStockMinimo(1);
    	chocolate37.setPrecioEntrada(0.00);      chocolate37.setPrecioVenta(2.50);
    	chocolate37.setMargenGanancia(0);

    	Producto chocolate38 = new Producto();
    	chocolate38.setNombre("Rocklets 20  Gr");
    	chocolate38.setProveedor(deposito);      chocolate38.setCodigo(new Long("6038"));
    	chocolate38.setStockActual(0);           chocolate38.setStockMinimo(1);
    	chocolate38.setPrecioEntrada(0.00);      chocolate38.setPrecioVenta(2.00);
    	chocolate38.setMargenGanancia(0);

    	Producto chocolate39 = new Producto();
    	chocolate39.setNombre("Rocklets 40 Gr");
    	chocolate39.setProveedor(deposito);      chocolate39.setCodigo(new Long("6039"));
    	chocolate39.setStockActual(0);           chocolate39.setStockMinimo(1);
    	chocolate39.setPrecioEntrada(0.00);      chocolate39.setPrecioVenta(3.00);
    	chocolate39.setMargenGanancia(0);

    	Producto chocolate40 = new Producto();
    	chocolate40.setNombre("Roklet Bananita");
    	chocolate40.setProveedor(deposito);      chocolate40.setCodigo(new Long("6040"));
    	chocolate40.setStockActual(1);           chocolate40.setStockMinimo(1);
    	chocolate40.setPrecioEntrada(0.00);      chocolate40.setPrecioVenta(2.00);
    	chocolate40.setMargenGanancia(0);

    	Producto chocolate41 = new Producto();
    	chocolate41.setNombre("Roklet Peanut");
    	chocolate41.setProveedor(deposito);      chocolate41.setCodigo(new Long("6041"));
    	chocolate41.setStockActual(0);           chocolate41.setStockMinimo(1);
    	chocolate41.setPrecioEntrada(0.00);      chocolate41.setPrecioVenta(4.00);
    	chocolate41.setMargenGanancia(0);

    	Producto chocolate42 = new Producto();
    	chocolate42.setNombre("Roklets Max");
    	chocolate42.setProveedor(deposito);      chocolate42.setCodigo(new Long("6042"));
    	chocolate42.setStockActual(0);           chocolate42.setStockMinimo(1);
    	chocolate42.setPrecioEntrada(0.00);      chocolate42.setPrecioVenta(4.75);
    	chocolate42.setMargenGanancia(0);

    	Producto chocolate43 = new Producto();
    	chocolate43.setNombre("Sapito Rospo");
    	chocolate43.setProveedor(deposito);      chocolate43.setCodigo(new Long("6043"));
    	chocolate43.setStockActual(0);           chocolate43.setStockMinimo(1);
    	chocolate43.setPrecioEntrada(0.00);      chocolate43.setPrecioVenta(1.00);
    	chocolate43.setMargenGanancia(0);

    	Producto chocolate44 = new Producto();
    	chocolate44.setNombre("Snicker");
    	chocolate44.setProveedor(deposito);      chocolate44.setCodigo(new Long("6044"));
    	chocolate44.setStockActual(0);           chocolate44.setStockMinimo(1);
    	chocolate44.setPrecioEntrada(0.00);      chocolate44.setPrecioVenta(0.00);
    	chocolate44.setMargenGanancia(0);

    	Producto chocolate45 = new Producto();
    	chocolate45.setNombre("Tita");
    	chocolate45.setProveedor(deposito);      chocolate45.setCodigo(new Long("6045"));
    	chocolate45.setStockActual(0);           chocolate45.setStockMinimo(1);
    	chocolate45.setPrecioEntrada(0.00);      chocolate45.setPrecioVenta(1.75);
    	chocolate45.setMargenGanancia(0);

    	Producto chocolate46 = new Producto();
    	chocolate46.setNombre("Tivis");
    	chocolate46.setProveedor(deposito);      chocolate46.setCodigo(new Long("6046"));
    	chocolate46.setStockActual(0);           chocolate46.setStockMinimo(1);
    	chocolate46.setPrecioEntrada(0.00);      chocolate46.setPrecioVenta(5.00);
    	chocolate46.setMargenGanancia(0);

    	Producto chocolate47 = new Producto();
    	chocolate47.setNombre("Toblerone");
    	chocolate47.setProveedor(deposito);      chocolate47.setCodigo(new Long("6047"));
    	chocolate47.setStockActual(0);           chocolate47.setStockMinimo(1);
    	chocolate47.setPrecioEntrada(0.00);      chocolate47.setPrecioVenta(6.00);
    	chocolate47.setMargenGanancia(0);

    	Producto chocolate48 = new Producto();
    	chocolate48.setNombre("Turron");
    	chocolate48.setProveedor(deposito);      chocolate48.setCodigo(new Long("6048"));
    	chocolate48.setStockActual(9);           chocolate48.setStockMinimo(1);
    	chocolate48.setPrecioEntrada(0.00);      chocolate48.setPrecioVenta(1.00);
    	chocolate48.setMargenGanancia(0);

    	Producto chocolate49 = new Producto();
    	chocolate49.setNombre("Vaca Lechera");
    	chocolate49.setProveedor(deposito);      chocolate49.setCodigo(new Long("6049"));
    	chocolate49.setStockActual(0);           chocolate49.setStockMinimo(1);
    	chocolate49.setPrecioEntrada(0.00);      chocolate49.setPrecioVenta(0.50);
    	chocolate49.setMargenGanancia(0);

    	Producto galletita0 = new Producto();
    	galletita0.setNombre("9 De Oro");
    	galletita0.setProveedor(deposito);      galletita0.setCodigo(new Long("7000"));
    	galletita0.setStockActual(0);           galletita0.setStockMinimo(1);
    	galletita0.setPrecioEntrada(0.00);      galletita0.setPrecioVenta(4.50);
    	galletita0.setMargenGanancia(0);

    	Producto galletita1 = new Producto();
    	galletita1.setNombre("Amor");
    	galletita1.setProveedor(deposito);      galletita1.setCodigo(new Long("7001"));
    	galletita1.setStockActual(0);           galletita1.setStockMinimo(1);
    	galletita1.setPrecioEntrada(0.00);      galletita1.setPrecioVenta(5.75);
    	galletita1.setMargenGanancia(0);

    	Producto galletita2 = new Producto();
    	galletita2.setNombre("Azucaradas");
    	galletita2.setProveedor(deposito);      galletita2.setCodigo(new Long("7002"));
    	galletita2.setStockActual(4);           galletita2.setStockMinimo(1);
    	galletita2.setPrecioEntrada(0.00);      galletita2.setPrecioVenta(2.50);
    	galletita2.setMargenGanancia(0);

    	Producto galletita3 = new Producto();
    	galletita3.setNombre("Chocochip");
    	galletita3.setProveedor(deposito);      galletita3.setCodigo(new Long("7003"));
    	galletita3.setStockActual(0);           galletita3.setStockMinimo(1);
    	galletita3.setPrecioEntrada(0.00);      galletita3.setPrecioVenta(6.00);
    	galletita3.setMargenGanancia(0);

    	Producto galletita4 = new Producto();
    	galletita4.setNombre("Chocolinas");
    	galletita4.setProveedor(deposito);      galletita4.setCodigo(new Long("7004"));
    	galletita4.setStockActual(0);           galletita4.setStockMinimo(1);
    	galletita4.setPrecioEntrada(0.00);      galletita4.setPrecioVenta(7.00);
    	galletita4.setMargenGanancia(0);

    	Producto galletita5 = new Producto();
    	galletita5.setNombre("Club Social");
    	galletita5.setProveedor(deposito);      galletita5.setCodigo(new Long("7005"));
    	galletita5.setStockActual(29);          galletita5.setStockMinimo(1);
    	galletita5.setPrecioEntrada(0.00);      galletita5.setPrecioVenta(1.25);
    	galletita5.setMargenGanancia(0);

    	Producto galletita6 = new Producto();
    	galletita6.setNombre("Coquitas");
    	galletita6.setProveedor(deposito);      galletita6.setCodigo(new Long("7006"));
    	galletita6.setStockActual(0);           galletita6.setStockMinimo(1);
    	galletita6.setPrecioEntrada(0.00);      galletita6.setPrecioVenta(3.75);
    	galletita6.setMargenGanancia(0);

    	Producto galletita7 = new Producto();
    	galletita7.setNombre("Criollitas");
    	galletita7.setProveedor(deposito);      galletita7.setCodigo(new Long("7007"));
    	galletita7.setStockActual(1);           galletita7.setStockMinimo(1);
    	galletita7.setPrecioEntrada(0.00);      galletita7.setPrecioVenta(3.00);
    	galletita7.setMargenGanancia(0);

    	Producto galletita8 = new Producto();
    	galletita8.setNombre("Desfile");
    	galletita8.setProveedor(deposito);      galletita8.setCodigo(new Long("7008"));
    	galletita8.setStockActual(0);           galletita8.setStockMinimo(1);
    	galletita8.setPrecioEntrada(0.00);      galletita8.setPrecioVenta(8.00);
    	galletita8.setMargenGanancia(0);

    	Producto galletita9 = new Producto();
    	galletita9.setNombre("Diversion");
    	galletita9.setProveedor(deposito);      galletita9.setCodigo(new Long("7009"));
    	galletita9.setStockActual(0);           galletita9.setStockMinimo(1);
    	galletita9.setPrecioEntrada(0.00);      galletita9.setPrecioVenta(9.50);
    	galletita9.setMargenGanancia(0);

    	Producto galletita10 = new Producto();
    	galletita10.setNombre("Formis");
    	galletita10.setProveedor(deposito);      galletita10.setCodigo(new Long("7010"));
    	galletita10.setStockActual(0);           galletita10.setStockMinimo(1);
    	galletita10.setPrecioEntrada(0.00);      galletita10.setPrecioVenta(3.75);
    	galletita10.setMargenGanancia(0);

    	Producto galletita11 = new Producto();
    	galletita11.setNombre("Galletas Cereal Mix");
    	galletita11.setProveedor(deposito);      galletita11.setCodigo(new Long("7011"));
    	galletita11.setStockActual(0);           galletita11.setStockMinimo(1);
    	galletita11.setPrecioEntrada(0.00);      galletita11.setPrecioVenta(9.00);
    	galletita11.setMargenGanancia(0);

    	Producto galletita12 = new Producto();
    	galletita12.setNombre("Galletas Cereal Mix Chips");
    	galletita12.setProveedor(deposito);      galletita12.setCodigo(new Long("7012"));
    	galletita12.setStockActual(0);           galletita12.setStockMinimo(1);
    	galletita12.setPrecioEntrada(0.00);      galletita12.setPrecioVenta(9.75);
    	galletita12.setMargenGanancia(0);

    	Producto galletita13 = new Producto();
    	galletita13.setNombre("Galletas Quaker Chica");
    	galletita13.setProveedor(deposito);      galletita13.setCodigo(new Long("7013"));
    	galletita13.setStockActual(0);           galletita13.setStockMinimo(1);
    	galletita13.setPrecioEntrada(0.00);      galletita13.setPrecioVenta(2.50);
    	galletita13.setMargenGanancia(0);

    	Producto galletita14 = new Producto();
    	galletita14.setNombre("Galletas Quaker Grande");
    	galletita14.setProveedor(deposito);      galletita14.setCodigo(new Long("7014"));
    	galletita14.setStockActual(0);           galletita14.setStockMinimo(1);
    	galletita14.setPrecioEntrada(0.00);      galletita14.setPrecioVenta(7.00);
    	galletita14.setMargenGanancia(0);

    	Producto galletita15 = new Producto();
    	galletita15.setNombre("Galletitas Ser");
    	galletita15.setProveedor(deposito);      galletita15.setCodigo(new Long("7015"));
    	galletita15.setStockActual(0);           galletita15.setStockMinimo(1);
    	galletita15.setPrecioEntrada(0.00);      galletita15.setPrecioVenta(8.50);
    	galletita15.setMargenGanancia(0);

    	Producto galletita16 = new Producto();
    	galletita16.setNombre("Grimaldi");
    	galletita16.setProveedor(deposito);      galletita16.setCodigo(new Long("7016"));
    	galletita16.setStockActual(0);           galletita16.setStockMinimo(1);
    	galletita16.setPrecioEntrada(0.00);      galletita16.setPrecioVenta(9.00);
    	galletita16.setMargenGanancia(0);

    	Producto galletita17 = new Producto();
    	galletita17.setNombre("Hogareñas");
    	galletita17.setProveedor(deposito);      galletita17.setCodigo(new Long("7017"));
    	galletita17.setStockActual(6);           galletita17.setStockMinimo(1);
    	galletita17.setPrecioEntrada(0.00);      galletita17.setPrecioVenta(6.00);
    	galletita17.setMargenGanancia(0);

    	Producto galletita18 = new Producto();
    	galletita18.setNombre("Kesitas");
    	galletita18.setProveedor(deposito);      galletita18.setCodigo(new Long("7018"));
    	galletita18.setStockActual(0);           galletita18.setStockMinimo(1);
    	galletita18.setPrecioEntrada(0.00);      galletita18.setPrecioVenta(2.75);
    	galletita18.setMargenGanancia(0);

    	Producto galletita19 = new Producto();
    	galletita19.setNombre("Lincoln");
    	galletita19.setProveedor(deposito);      galletita19.setCodigo(new Long("7019"));
    	galletita19.setStockActual(0);           galletita19.setStockMinimo(1);
    	galletita19.setPrecioEntrada(0.00);      galletita19.setPrecioVenta(5.75);
    	galletita19.setMargenGanancia(0);

    	Producto galletita20 = new Producto();
    	galletita20.setNombre("Macucas Chicas");
    	galletita20.setProveedor(deposito);      galletita20.setCodigo(new Long("7020"));
    	galletita20.setStockActual(0);           galletita20.setStockMinimo(1);
    	galletita20.setPrecioEntrada(0.00);      galletita20.setPrecioVenta(4.00);
    	galletita20.setMargenGanancia(0);

    	Producto galletita21 = new Producto();
    	galletita21.setNombre("Macucas Grandes");
    	galletita21.setProveedor(deposito);      galletita21.setCodigo(new Long("7021"));
    	galletita21.setStockActual(0);           galletita21.setStockMinimo(1);
    	galletita21.setPrecioEntrada(0.00);      galletita21.setPrecioVenta(6.00);
    	galletita21.setMargenGanancia(0);

    	Producto galletita22 = new Producto();
    	galletita22.setNombre("Mana");
    	galletita22.setProveedor(deposito);      galletita22.setCodigo(new Long("7022"));
    	galletita22.setStockActual(9);           galletita22.setStockMinimo(1);
    	galletita22.setPrecioEntrada(0.00);      galletita22.setPrecioVenta(5.50);
    	galletita22.setMargenGanancia(0);

    	Producto galletita23 = new Producto();
    	galletita23.setNombre("Mana Rellena");
    	galletita23.setProveedor(deposito);      galletita23.setCodigo(new Long("7023"));
    	galletita23.setStockActual(0);           galletita23.setStockMinimo(1);
    	galletita23.setPrecioEntrada(0.00);      galletita23.setPrecioVenta(7.00);
    	galletita23.setMargenGanancia(0);

    	Producto galletita24 = new Producto();
    	galletita24.setNombre("Marmoladas Fantoche");
    	galletita24.setProveedor(deposito);      galletita24.setCodigo(new Long("7024"));
    	galletita24.setStockActual(0);           galletita24.setStockMinimo(1);
    	galletita24.setPrecioEntrada(0.00);      galletita24.setPrecioVenta(5.25);
    	galletita24.setMargenGanancia(0);

    	Producto galletita25 = new Producto();
    	galletita25.setNombre("Melitas");
    	galletita25.setProveedor(deposito);      galletita25.setCodigo(new Long("7025"));
    	galletita25.setStockActual(0);           galletita25.setStockMinimo(1);
    	galletita25.setPrecioEntrada(0.00);      galletita25.setPrecioVenta(6.00);
    	galletita25.setMargenGanancia(0);

    	Producto galletita26 = new Producto();
    	galletita26.setNombre("Mellizas");
    	galletita26.setProveedor(deposito);      galletita26.setCodigo(new Long("7026"));
    	galletita26.setStockActual(0);           galletita26.setStockMinimo(1);
    	galletita26.setPrecioEntrada(0.00);      galletita26.setPrecioVenta(6.00);
    	galletita26.setMargenGanancia(0);

    	Producto galletita27 = new Producto();
    	galletita27.setNombre("Merengadas");
    	galletita27.setProveedor(deposito);      galletita27.setCodigo(new Long("7027"));
    	galletita27.setStockActual(0);           galletita27.setStockMinimo(1);
    	galletita27.setPrecioEntrada(0.00);      galletita27.setPrecioVenta(6.00);
    	galletita27.setMargenGanancia(0);

    	Producto galletita28 = new Producto();
    	galletita28.setNombre("Oblea Bonafide");
    	galletita28.setProveedor(deposito);      galletita28.setCodigo(new Long("7028"));
    	galletita28.setStockActual(0);           galletita28.setStockMinimo(1);
    	galletita28.setPrecioEntrada(0.00);      galletita28.setPrecioVenta(2.25);
    	galletita28.setMargenGanancia(0);

    	Producto galletita29 = new Producto();
    	galletita29.setNombre("Oblea Cofler");
    	galletita29.setProveedor(deposito);      galletita29.setCodigo(new Long("7029"));
    	galletita29.setStockActual(0);           galletita29.setStockMinimo(1);
    	galletita29.setPrecioEntrada(0.00);      galletita29.setPrecioVenta(3.50);
    	galletita29.setMargenGanancia(0);

    	Producto galletita30 = new Producto();
    	galletita30.setNombre("Opera Chica");
    	galletita30.setProveedor(deposito);      galletita30.setCodigo(new Long("7030"));
    	galletita30.setStockActual(0);           galletita30.setStockMinimo(1);
    	galletita30.setPrecioEntrada(0.00);      galletita30.setPrecioVenta(4.00);
    	galletita30.setMargenGanancia(0);

    	Producto galletita31 = new Producto();
    	galletita31.setNombre("Opera Grande");
    	galletita31.setProveedor(deposito);      galletita31.setCodigo(new Long("7031"));
    	galletita31.setStockActual(0);           galletita31.setStockMinimo(1);
    	galletita31.setPrecioEntrada(0.00);      galletita31.setPrecioVenta(6.00);
    	galletita31.setMargenGanancia(0);

    	Producto galletita32 = new Producto();
    	galletita32.setNombre("Oreo");
    	galletita32.setProveedor(deposito);      galletita32.setCodigo(new Long("7032"));
    	galletita32.setStockActual(0);           galletita32.setStockMinimo(1);
    	galletita32.setPrecioEntrada(0.00);      galletita32.setPrecioVenta(6.00);
    	galletita32.setMargenGanancia(0);

    	Producto galletita33 = new Producto();
    	galletita33.setNombre("Pepitos");
    	galletita33.setProveedor(deposito);      galletita33.setCodigo(new Long("7033"));
    	galletita33.setStockActual(0);           galletita33.setStockMinimo(1);
    	galletita33.setPrecioEntrada(0.00);      galletita33.setPrecioVenta(6.00);
    	galletita33.setMargenGanancia(0);

    	Producto galletita34 = new Producto();
    	galletita34.setNombre("Polvorita");
    	galletita34.setProveedor(deposito);      galletita34.setCodigo(new Long("7034"));
    	galletita34.setStockActual(0);           galletita34.setStockMinimo(1);
    	galletita34.setPrecioEntrada(0.00);      galletita34.setPrecioVenta(2.00);
    	galletita34.setMargenGanancia(0);

    	Producto galletita35 = new Producto();
    	galletita35.setNombre("Recetas De La Abuela");
    	galletita35.setProveedor(deposito);      galletita35.setCodigo(new Long("7035"));
    	galletita35.setStockActual(0);           galletita35.setStockMinimo(1);
    	galletita35.setPrecioEntrada(0.00);      galletita35.setPrecioVenta(5.50);
    	galletita35.setMargenGanancia(0);

    	Producto galletita36 = new Producto();
    	galletita36.setNombre("Recetas De La Abuela Media Lunas");
    	galletita36.setProveedor(deposito);      galletita36.setCodigo(new Long("7036"));
    	galletita36.setStockActual(0);           galletita36.setStockMinimo(1);
    	galletita36.setPrecioEntrada(0.00);      galletita36.setPrecioVenta(4.50);
    	galletita36.setMargenGanancia(0);

    	Producto galletita37 = new Producto();
    	galletita37.setNombre("Rumba");
    	galletita37.setProveedor(deposito);      galletita37.setCodigo(new Long("7037"));
    	galletita37.setStockActual(0);           galletita37.setStockMinimo(1);
    	galletita37.setPrecioEntrada(0.00);      galletita37.setPrecioVenta(6.00);
    	galletita37.setMargenGanancia(0);

    	Producto galletita38 = new Producto();
    	galletita38.setNombre("Saladix Caja");
    	galletita38.setProveedor(deposito);      galletita38.setCodigo(new Long("7038"));
    	galletita38.setStockActual(0);           galletita38.setStockMinimo(1);
    	galletita38.setPrecioEntrada(0.00);      galletita38.setPrecioVenta(7.00);
    	galletita38.setMargenGanancia(0);

    	Producto galletita39 = new Producto();
    	galletita39.setNombre("Salvado Bagley");
    	galletita39.setProveedor(deposito);      galletita39.setCodigo(new Long("7039"));
    	galletita39.setStockActual(0);           galletita39.setStockMinimo(1);
    	galletita39.setPrecioEntrada(0.00);      galletita39.setPrecioVenta(0.00);
    	galletita39.setMargenGanancia(0);

    	Producto galletita40 = new Producto();
    	galletita40.setNombre("Sonrisas");
    	galletita40.setProveedor(deposito);      galletita40.setCodigo(new Long("7040"));
    	galletita40.setStockActual(0);           galletita40.setStockMinimo(1);
    	galletita40.setPrecioEntrada(0.00);      galletita40.setPrecioVenta(6.00);
    	galletita40.setMargenGanancia(0);

    	Producto galletita41 = new Producto();
    	galletita41.setNombre("Sonrrisas Picaras");
    	galletita41.setProveedor(deposito);      galletita41.setCodigo(new Long("7041"));
    	galletita41.setStockActual(0);           galletita41.setStockMinimo(1);
    	galletita41.setPrecioEntrada(0.00);      galletita41.setPrecioVenta(5.00);
    	galletita41.setMargenGanancia(0);

    	Producto galletita42 = new Producto();
    	galletita42.setNombre("Surtido Bagley");
    	galletita42.setProveedor(deposito);      galletita42.setCodigo(new Long("7042"));
    	galletita42.setStockActual(0);           galletita42.setStockMinimo(1);
    	galletita42.setPrecioEntrada(0.00);      galletita42.setPrecioVenta(9.50);
    	galletita42.setMargenGanancia(0);

    	Producto galletita43 = new Producto();
    	galletita43.setNombre("Tentacion");
    	galletita43.setProveedor(deposito);      galletita43.setCodigo(new Long("7043"));
    	galletita43.setStockActual(0);           galletita43.setStockMinimo(1);
    	galletita43.setPrecioEntrada(0.00);      galletita43.setPrecioVenta(7.75);
    	galletita43.setMargenGanancia(0);

    	Producto galletita44 = new Producto();
    	galletita44.setNombre("Tia Maruca");
    	galletita44.setProveedor(deposito);      galletita44.setCodigo(new Long("7044"));
    	galletita44.setStockActual(2);           galletita44.setStockMinimo(1);
    	galletita44.setPrecioEntrada(0.00);      galletita44.setPrecioVenta(7.00);
    	galletita44.setMargenGanancia(0);

    	Producto galletita45 = new Producto();
    	galletita45.setNombre("Tortitas");
    	galletita45.setProveedor(deposito);      galletita45.setCodigo(new Long("7045"));
    	galletita45.setStockActual(0);           galletita45.setStockMinimo(1);
    	galletita45.setPrecioEntrada(0.00);      galletita45.setPrecioVenta(5.00);
    	galletita45.setMargenGanancia(0);

    	Producto galletita46 = new Producto();
    	galletita46.setNombre("Traviata");
    	galletita46.setProveedor(deposito);      galletita46.setCodigo(new Long("7046"));
    	galletita46.setStockActual(2);           galletita46.setStockMinimo(1);
    	galletita46.setPrecioEntrada(0.00);      galletita46.setPrecioVenta(3.00);
    	galletita46.setMargenGanancia(0);

    	Producto galletita47 = new Producto();
    	galletita47.setNombre("Traviata X 3");
    	galletita47.setProveedor(deposito);      galletita47.setCodigo(new Long("7047"));
    	galletita47.setStockActual(0);           galletita47.setStockMinimo(1);
    	galletita47.setPrecioEntrada(0.00);      galletita47.setPrecioVenta(0.00);
    	galletita47.setMargenGanancia(0);

    	Producto galletita48 = new Producto();
    	galletita48.setNombre("Trepin");
    	galletita48.setProveedor(deposito);      galletita48.setCodigo(new Long("7048"));
    	galletita48.setStockActual(0);           galletita48.setStockMinimo(1);
    	galletita48.setPrecioEntrada(0.00);      galletita48.setPrecioVenta(7.00);
    	galletita48.setMargenGanancia(0);

    	Producto galletita49 = new Producto();
    	galletita49.setNombre("Yayita Fantoche");
    	galletita49.setProveedor(deposito);      galletita49.setCodigo(new Long("7049"));
    	galletita49.setStockActual(0);           galletita49.setStockMinimo(1);
    	galletita49.setPrecioEntrada(0.00);      galletita49.setPrecioVenta(7.00);
    	galletita49.setMargenGanancia(0);

    	Producto galletita50 = new Producto();
    	galletita50.setNombre("Yayitas");
    	galletita50.setProveedor(deposito);      galletita50.setCodigo(new Long("7050"));
    	galletita50.setStockActual(0);           galletita50.setStockMinimo(1);
    	galletita50.setPrecioEntrada(0.00);      galletita50.setPrecioVenta(9.50);
    	galletita50.setMargenGanancia(0);

    	Producto panaderia0 = new Producto();
    	panaderia0.setNombre("Alfajor  Maizena Chiquitos");
    	panaderia0.setProveedor(ivana);         panaderia0.setCodigo(new Long("8000"));
    	panaderia0.setStockActual(0);           panaderia0.setStockMinimo(1);
    	panaderia0.setPrecioEntrada(0.00);      panaderia0.setPrecioVenta(2.00);
    	panaderia0.setMargenGanancia(0);

    	Producto panaderia1 = new Producto();
    	panaderia1.setNombre("Arabes");
    	panaderia1.setProveedor(panadero);      panaderia1.setCodigo(new Long("8001"));
    	panaderia1.setStockActual(0);           panaderia1.setStockMinimo(1);
    	panaderia1.setPrecioEntrada(0.00);      panaderia1.setPrecioVenta(5.00);
    	panaderia1.setMargenGanancia(0);

    	Producto panaderia2 = new Producto();
    	panaderia2.setNombre("Bolas De Fraile");
    	panaderia2.setProveedor(panadero);      panaderia2.setCodigo(new Long("8002"));
    	panaderia2.setStockActual(0);           panaderia2.setStockMinimo(1);
    	panaderia2.setPrecioEntrada(0.00);      panaderia2.setPrecioVenta(2.25);
    	panaderia2.setMargenGanancia(0);

    	Producto panaderia3 = new Producto();
    	panaderia3.setNombre("Cafe Solo");
    	panaderia3.setProveedor(kiosco);        panaderia3.setCodigo(new Long("8003"));
    	panaderia3.setStockActual(0);           panaderia3.setStockMinimo(1);
    	panaderia3.setPrecioEntrada(0.00);      panaderia3.setPrecioVenta(3.00);
    	panaderia3.setMargenGanancia(0);

    	Producto panaderia4 = new Producto();
    	panaderia4.setNombre("Cafe Y Alfajor");
    	panaderia4.setProveedor(kiosco);        panaderia4.setCodigo(new Long("8004"));
    	panaderia4.setStockActual(0);           panaderia4.setStockMinimo(1);
    	panaderia4.setPrecioEntrada(0.00);      panaderia4.setPrecioVenta(4.00);
    	panaderia4.setMargenGanancia(0);

    	Producto panaderia5 = new Producto();
    	panaderia5.setNombre("Facturas");
    	panaderia5.setProveedor(panadero);      panaderia5.setCodigo(new Long("8005"));
    	panaderia5.setStockActual(0);           panaderia5.setStockMinimo(1);
    	panaderia5.setPrecioEntrada(0.00);      panaderia5.setPrecioVenta(1.50);
    	panaderia5.setMargenGanancia(0);

    	Producto panaderia6 = new Producto();
    	panaderia6.setNombre("Facturas Dulce De Leche");
    	panaderia6.setProveedor(panadero);      panaderia6.setCodigo(new Long("8006"));
    	panaderia6.setStockActual(0);           panaderia6.setStockMinimo(1);
    	panaderia6.setPrecioEntrada(0.00);      panaderia6.setPrecioVenta(1.75);
    	panaderia6.setMargenGanancia(0);

    	Producto panaderia7 = new Producto();
    	panaderia7.setNombre("Galletas De Avena");
    	panaderia7.setProveedor(carmen);        panaderia7.setCodigo(new Long("8007"));
    	panaderia7.setStockActual(0);           panaderia7.setStockMinimo(1);
    	panaderia7.setPrecioEntrada(0.00);      panaderia7.setPrecioVenta(2.50);
    	panaderia7.setMargenGanancia(0);

    	Producto panaderia8 = new Producto();
    	panaderia8.setNombre("Galletitas Del Panadero");
    	panaderia8.setProveedor(panadero);      panaderia8.setCodigo(new Long("8008"));
    	panaderia8.setStockActual(0);           panaderia8.setStockMinimo(1);
    	panaderia8.setPrecioEntrada(0.00);      panaderia8.setPrecioVenta(6.00);
    	panaderia8.setMargenGanancia(0);

    	Producto panaderia9 = new Producto();
    	panaderia9.setNombre("Hamburguesas");
    	panaderia9.setProveedor(pepeVazquez);   panaderia9.setCodigo(new Long("8009"));
    	panaderia9.setStockActual(0);           panaderia9.setStockMinimo(1);
    	panaderia9.setPrecioEntrada(0.00);      panaderia9.setPrecioVenta(6.50);
    	panaderia9.setMargenGanancia(0);

    	Producto panaderia10 = new Producto();
    	panaderia10.setNombre("Miga");
    	panaderia10.setProveedor(panadero);      panaderia10.setCodigo(new Long("8010"));
    	panaderia10.setStockActual(0);           panaderia10.setStockMinimo(1);
    	panaderia10.setPrecioEntrada(0.00);      panaderia10.setPrecioVenta(3.50);
    	panaderia10.setMargenGanancia(0);

    	Producto panaderia11 = new Producto();
    	panaderia11.setNombre("Milanesas");
    	panaderia11.setProveedor(panadero);      panaderia11.setCodigo(new Long("8011"));
    	panaderia11.setStockActual(0);           panaderia11.setStockMinimo(1);
    	panaderia11.setPrecioEntrada(0.00);      panaderia11.setPrecioVenta(7.50);
    	panaderia11.setMargenGanancia(0);

    	Producto panaderia12 = new Producto();
    	panaderia12.setNombre("Pasta Frola");
    	panaderia12.setProveedor(carmenCarballo); panaderia12.setCodigo(new Long("8012"));
    	panaderia12.setStockActual(0);            panaderia12.setStockMinimo(1);
    	panaderia12.setPrecioEntrada(0.00);       panaderia12.setPrecioVenta(3.75);
    	panaderia12.setMargenGanancia(0);

    	Producto panaderia13 = new Producto();
    	panaderia13.setNombre("Pebeton");
    	panaderia13.setProveedor(panadero);      panaderia13.setCodigo(new Long("8013"));
    	panaderia13.setStockActual(0);           panaderia13.setStockMinimo(1);
    	panaderia13.setPrecioEntrada(0.00);      panaderia13.setPrecioVenta(5.00);
    	panaderia13.setMargenGanancia(0);

    	Producto panaderia14 = new Producto();
    	panaderia14.setNombre("torta");
    	panaderia14.setProveedor(carmenCarballo);panaderia14.setCodigo(new Long("8014"));
    	panaderia14.setStockActual(0);           panaderia14.setStockMinimo(1);
    	panaderia14.setPrecioEntrada(0.00);      panaderia14.setPrecioVenta(2.50);
    	panaderia14.setMargenGanancia(0);

    	Producto pepsico0 = new Producto();
    	pepsico0.setNombre("Lays De 10");
    	pepsico0.setProveedor(pepsicoProv);     pepsico0.setCodigo(new Long("9000"));
    	pepsico0.setStockActual(0);             pepsico0.setStockMinimo(1);
    	pepsico0.setPrecioEntrada(0.00);        pepsico0.setPrecioVenta(10.00);
    	pepsico0.setMargenGanancia(0);

    	Producto pepsico1 = new Producto();
    	pepsico1.setNombre("Lays De 4");
    	pepsico1.setProveedor(pepsicoProv);     pepsico1.setCodigo(new Long("9001"));
    	pepsico1.setStockActual(0);             pepsico1.setStockMinimo(1);
    	pepsico1.setPrecioEntrada(0.00);        pepsico1.setPrecioVenta(4.00);
    	pepsico1.setMargenGanancia(0);

    	Producto pepsico2 = new Producto();
    	pepsico2.setNombre("Lays De 5");
    	pepsico2.setProveedor(pepsicoProv);     pepsico2.setCodigo(new Long("9002"));
    	pepsico2.setStockActual(0);             pepsico2.setStockMinimo(1);
    	pepsico2.setPrecioEntrada(0.00);        pepsico2.setPrecioVenta(5.00);
    	pepsico2.setMargenGanancia(0);

    	Producto pepsico3 = new Producto();
    	pepsico3.setNombre("Lays De 6");
    	pepsico3.setProveedor(pepsicoProv);     pepsico3.setCodigo(new Long("9003"));
    	pepsico3.setStockActual(0);             pepsico3.setStockMinimo(1);
    	pepsico3.setPrecioEntrada(0.00);        pepsico3.setPrecioVenta(6.00);
    	pepsico3.setMargenGanancia(0);

    	Producto pepsico4 = new Producto();
    	pepsico4.setNombre("Lays De 7");
    	pepsico4.setProveedor(pepsicoProv);     pepsico4.setCodigo(new Long("9004"));
    	pepsico4.setStockActual(0);             pepsico4.setStockMinimo(1);
    	pepsico4.setPrecioEntrada(0.00);        pepsico4.setPrecioVenta(7.00);
    	pepsico4.setMargenGanancia(0);

    	Producto pepsico5 = new Producto();
    	pepsico5.setNombre("Lays De 8");
    	pepsico5.setProveedor(pepsicoProv);     pepsico5.setCodigo(new Long("9005"));
    	pepsico5.setStockActual(0);             pepsico5.setStockMinimo(1);
    	pepsico5.setPrecioEntrada(0.00);        pepsico5.setPrecioVenta(8.00);
    	pepsico5.setMargenGanancia(0);

    	Producto pepsico6 = new Producto();
    	pepsico6.setNombre("Lays De 9");
    	pepsico6.setProveedor(pepsicoProv);     pepsico6.setCodigo(new Long("9006"));
    	pepsico6.setStockActual(0);             pepsico6.setStockMinimo(1);
    	pepsico6.setPrecioEntrada(0.00);        pepsico6.setPrecioVenta(9.00);
    	pepsico6.setMargenGanancia(0);

    	Producto variado0 = new Producto();
    	variado0.setNombre("Bolsita De Cereales");
    	variado0.setProveedor(pabloAlcoba);    variado0.setCodigo(new Long("10000"));
    	variado0.setStockActual(0);            variado0.setStockMinimo(1);
    	variado0.setPrecioEntrada(0.00);       variado0.setPrecioVenta(3.00);
    	variado0.setMargenGanancia(0);

    	Producto variado1 = new Producto();
    	variado1.setNombre("Encendedor");
    	variado1.setProveedor(deposito);       variado1.setCodigo(new Long("10001"));
    	variado1.setStockActual(0);            variado1.setStockMinimo(1);
    	variado1.setPrecioEntrada(0.00);       variado1.setPrecioVenta(2.00);
    	variado1.setMargenGanancia(0);

    	Producto variado2 = new Producto();
    	variado2.setNombre("Ensalada De Fruta");
    	variado2.setProveedor(pepeVazquez);    variado2.setCodigo(new Long("10002"));
    	variado2.setStockActual(0);            variado2.setStockMinimo(1);
    	variado2.setPrecioEntrada(0.00);       variado2.setPrecioVenta(5.00);
    	variado2.setMargenGanancia(0);

    	Producto variado3 = new Producto();
    	variado3.setNombre("Mani");
    	variado3.setProveedor(alejandroAramayo); variado3.setCodigo(new Long("10003"));
    	variado3.setStockActual(95);             variado3.setStockMinimo(1);
    	variado3.setPrecioEntrada(0.00);         variado3.setPrecioVenta(2.00);
    	variado3.setMargenGanancia(0);

    	Producto variado4 = new Producto();
    	variado4.setNombre("Pañuelitos Descartables");
    	variado4.setProveedor(deposito);       variado4.setCodigo(new Long("10004"));
    	variado4.setStockActual(95);           variado4.setStockMinimo(1);
    	variado4.setPrecioEntrada(0.00);       variado4.setPrecioVenta(2.00);
    	variado4.setMargenGanancia(0);

    	Producto variado5 = new Producto();
    	variado5.setNombre("Toallitas Lady Soft Ultra");
    	variado5.setProveedor(superm);         variado5.setCodigo(new Long("10005"));
    	variado5.setStockActual(0);            variado5.setStockMinimo(1);
    	variado5.setPrecioEntrada(0.00);       variado5.setPrecioVenta(1.00);
    	variado5.setMargenGanancia(0);

    	Producto variado6 = new Producto();
    	variado6.setNombre("Yerba Amanda");
    	variado6.setProveedor(deposito);       variado6.setCodigo(new Long("10006"));
    	variado6.setStockActual(0);            variado6.setStockMinimo(1);
    	variado6.setPrecioEntrada(0.00);       variado6.setPrecioVenta(7.50);
    	variado6.setMargenGanancia(0);

    	Producto variado7 = new Producto();
    	variado7.setNombre("Yerba Cruz De Malta");
    	variado7.setProveedor(deposito);       variado7.setCodigo(new Long("10007"));
    	variado7.setStockActual(0);            variado7.setStockMinimo(1);
    	variado7.setPrecioEntrada(0.00);       variado7.setPrecioVenta(7.50);
    	variado7.setMargenGanancia(0);

    	Producto variado8 = new Producto();
    	variado8.setNombre("Yerba F.U.R.C Chica");
    	variado8.setProveedor(deposito);       variado8.setCodigo(new Long("10008"));
    	variado8.setStockActual(0);            variado8.setStockMinimo(1);
    	variado8.setPrecioEntrada(0.00);       variado8.setPrecioVenta(8.00);
    	variado8.setMargenGanancia(0);

    	Producto variado9 = new Producto();
    	variado9.setNombre("Yerba F.U.R.C Grande");
    	variado9.setProveedor(deposito);       variado9.setCodigo(new Long("10009"));
    	variado9.setStockActual(0);            variado9.setStockMinimo(1);
    	variado9.setPrecioEntrada(0.00);       variado9.setPrecioVenta(15.00);
    	variado9.setMargenGanancia(0);

    	Producto variado10 = new Producto();
    	variado10.setNombre("Yerba Nobleza Gaucha Azul");
    	variado10.setProveedor(deposito);       variado10.setCodigo(new Long("10010"));
    	variado10.setStockActual(0);            variado10.setStockMinimo(1);
    	variado10.setPrecioEntrada(0.00);       variado10.setPrecioVenta(12.50);
    	variado10.setMargenGanancia(0);

    	Producto variado11 = new Producto();
    	variado11.setNombre("Yerba Nobleza Gaucha Blanca");
    	variado11.setProveedor(deposito);       variado11.setCodigo(new Long("10011"));
    	variado11.setStockActual(0);            variado11.setStockMinimo(1);
    	variado11.setPrecioEntrada(0.00);       variado11.setPrecioVenta(12.50);
    	variado11.setMargenGanancia(0);

    	Producto variado12 = new Producto();
    	variado12.setNombre("Yerba Nobleza Gaucha Roja");
    	variado12.setProveedor(deposito);       variado12.setCodigo(new Long("10012"));
    	variado12.setStockActual(0);            variado12.setStockMinimo(1);
    	variado12.setPrecioEntrada(0.00);       variado12.setPrecioVenta(8.25);
    	variado12.setMargenGanancia(0);

    	Producto variado13 = new Producto();
    	variado13.setNombre("Yerba Rosamonte Chiquita");
    	variado13.setProveedor(deposito);       variado13.setCodigo(new Long("10013"));
    	variado13.setStockActual(0);            variado13.setStockMinimo(1);
    	variado13.setPrecioEntrada(0.00);       variado13.setPrecioVenta(4.00);
    	variado13.setMargenGanancia(0);

    	Producto variado14 = new Producto();
    	variado14.setNombre("Yerba Rosamonte Comun");
    	variado14.setProveedor(deposito);       variado14.setCodigo(new Long("10014"));
    	variado14.setStockActual(0);            variado14.setStockMinimo(1);
    	variado14.setPrecioEntrada(0.00);       variado14.setPrecioVenta(7.50);
    	variado14.setMargenGanancia(0);

    	Producto variado15 = new Producto();
    	variado15.setNombre("Yerba Rosamonte Suave");
    	variado15.setProveedor(deposito);       variado15.setCodigo(new Long("10015"));
    	variado15.setStockActual(0);            variado15.setStockMinimo(1);
    	variado15.setPrecioEntrada(0.00);       variado15.setPrecioVenta(7.50);
    	variado15.setMargenGanancia(0);
    	Transaction tx= pm.currentTransaction();
    	try{
    		try {
    			tx.begin();
    	    	System.out.println("creando datos");
    			pm.makePersistent(p1);
    			pm.makePersistent(l1);
    			pm.makePersistent(ki);
    			pm.makePersistent(adm);
    			
    			System.out.println("cargando proveedores");
    			pm.makePersistent(alejandroAramayo);
    			pm.makePersistent(deposito);
    			pm.makePersistent(delinter);
    			pm.makePersistent(delinterPritty);
    	    	pm.makePersistent(carmen);
    			pm.makePersistent(carmenCarballo);
    			pm.makePersistent(chesi);
    			pm.makePersistent(ivana);
    			pm.makePersistent(kiosco);
    			pm.makePersistent(pabloAlcoba);
    			pm.makePersistent(panadero);
    			pm.makePersistent(pepeVazquez);
    			pm.makePersistent(pepsicoProv);
    			pm.makePersistent(pritty);
    			pm.makePersistent(superm);
    			
    			System.out.println("cargando productos");
    			pm.makePersistent(gaseosa0);    			pm.makePersistent(gaseosa1);
    			pm.makePersistent(gaseosa2);    			pm.makePersistent(gaseosa3);
    			pm.makePersistent(gaseosa4);    			pm.makePersistent(gaseosa5);
    			pm.makePersistent(gaseosa6);    			pm.makePersistent(gaseosa7);
    			pm.makePersistent(gaseosa8);    			pm.makePersistent(gaseosa9);
    			pm.makePersistent(gaseosa10);    			pm.makePersistent(gaseosa11);
    			pm.makePersistent(gaseosa12);    			pm.makePersistent(gaseosa13);
    			pm.makePersistent(gaseosa14);    			pm.makePersistent(gaseosa15);
    			pm.makePersistent(gaseosa16);
    			pm.makePersistent(alfajor0);    			pm.makePersistent(alfajor1);
    			pm.makePersistent(alfajor2);    			pm.makePersistent(alfajor3);
    			pm.makePersistent(alfajor4);    			pm.makePersistent(alfajor5);
    			pm.makePersistent(alfajor6);    			pm.makePersistent(alfajor7);
    			pm.makePersistent(alfajor8);    			pm.makePersistent(alfajor9);
    			pm.makePersistent(alfajor10);    			pm.makePersistent(alfajor11);
    			pm.makePersistent(alfajor12);    			pm.makePersistent(alfajor13);
    			pm.makePersistent(alfajor14);    			pm.makePersistent(alfajor15);
    			pm.makePersistent(alfajor16);    			pm.makePersistent(alfajor17);
    			pm.makePersistent(alfajor18);    			pm.makePersistent(alfajor19);
    			pm.makePersistent(alfajor20);    			pm.makePersistent(alfajor21);
    			pm.makePersistent(alfajor22);    			pm.makePersistent(alfajor23);
    			pm.makePersistent(alfajor24);    			pm.makePersistent(alfajor25);
    			pm.makePersistent(alfajor26);
    			pm.makePersistent(barraCereal0);   			pm.makePersistent(barraCereal1);
    			pm.makePersistent(barraCereal2);   			pm.makePersistent(barraCereal3);
    			pm.makePersistent(barraCereal4);   			pm.makePersistent(barraCereal5);
    			pm.makePersistent(barraCereal6);
    			pm.makePersistent(caramelo0);    			pm.makePersistent(caramelo1);
    			pm.makePersistent(caramelo2);    			pm.makePersistent(caramelo3);
    			pm.makePersistent(caramelo4);    			pm.makePersistent(caramelo5);
    			pm.makePersistent(caramelo6);    			pm.makePersistent(caramelo7);
    			pm.makePersistent(caramelo8);    			pm.makePersistent(caramelo9);
    			pm.makePersistent(caramelo10);    			pm.makePersistent(caramelo11);
    			pm.makePersistent(caramelo12);    			pm.makePersistent(caramelo13);
    			pm.makePersistent(caramelo14);    			pm.makePersistent(caramelo15);
    			pm.makePersistent(caramelo16);    			pm.makePersistent(caramelo17);
    			pm.makePersistent(caramelo18);    			pm.makePersistent(caramelo19);
    			pm.makePersistent(caramelo20);    			pm.makePersistent(caramelo21);
    			pm.makePersistent(caramelo22);
    			pm.makePersistent(chicle0);    				pm.makePersistent(chicle1);
    			pm.makePersistent(chicle2);    				pm.makePersistent(chicle3);
    			pm.makePersistent(chicle4); 	   			pm.makePersistent(chicle5);
    			pm.makePersistent(chicle6);	  
    			
    			pm.makePersistent(chocolate0);    			
    			pm.makePersistent(chocolate1);    			pm.makePersistent(chocolate2);
    			pm.makePersistent(chocolate3);    			pm.makePersistent(chocolate4);
    			pm.makePersistent(chocolate5);    			pm.makePersistent(chocolate6);
    			pm.makePersistent(chocolate7);    			pm.makePersistent(chocolate8);
    			pm.makePersistent(chocolate9);    			pm.makePersistent(chocolate10);
    			pm.makePersistent(chocolate11);    			pm.makePersistent(chocolate12);
    			pm.makePersistent(chocolate13);    			pm.makePersistent(chocolate14);
    			pm.makePersistent(chocolate15);    			pm.makePersistent(chocolate16);
    			pm.makePersistent(chocolate17);    			pm.makePersistent(chocolate18);
    			pm.makePersistent(chocolate19);    			pm.makePersistent(chocolate20);
    			pm.makePersistent(chocolate21);    			pm.makePersistent(chocolate22);
    			pm.makePersistent(chocolate23);    			pm.makePersistent(chocolate24);
    			pm.makePersistent(chocolate25);    			pm.makePersistent(chocolate26);
    			pm.makePersistent(chocolate27);    			pm.makePersistent(chocolate28);
    			pm.makePersistent(chocolate29);    			pm.makePersistent(chocolate30);
    			pm.makePersistent(chocolate31);    			pm.makePersistent(chocolate32);
    			pm.makePersistent(chocolate33);    			pm.makePersistent(chocolate34);
    			pm.makePersistent(chocolate35);    			pm.makePersistent(chocolate36);
    			pm.makePersistent(chocolate37);    			pm.makePersistent(chocolate38);
    			pm.makePersistent(chocolate39);    			pm.makePersistent(chocolate40);
    			pm.makePersistent(chocolate41);    			pm.makePersistent(chocolate42);
    			pm.makePersistent(chocolate43);    			pm.makePersistent(chocolate44);
    			pm.makePersistent(chocolate45);    			pm.makePersistent(chocolate46);
    			pm.makePersistent(chocolate47);    			pm.makePersistent(chocolate48);
    			pm.makePersistent(chocolate49);
    			
    			pm.makePersistent(galletita0);    			pm.makePersistent(galletita1);
    			pm.makePersistent(galletita2);    			pm.makePersistent(galletita3);
    			pm.makePersistent(galletita4);    			pm.makePersistent(galletita5);
    			pm.makePersistent(galletita6);    			pm.makePersistent(galletita7);
    			pm.makePersistent(galletita8);    			pm.makePersistent(galletita9);
    			pm.makePersistent(galletita10);    			pm.makePersistent(galletita11);
    			pm.makePersistent(galletita12);    			pm.makePersistent(galletita13);
    			pm.makePersistent(galletita14);    			pm.makePersistent(galletita15);
    			pm.makePersistent(galletita16);    			pm.makePersistent(galletita17);
    			pm.makePersistent(galletita18);    			pm.makePersistent(galletita19);
    			pm.makePersistent(galletita20);    			pm.makePersistent(galletita21);
    			pm.makePersistent(galletita22);    			pm.makePersistent(galletita23);
    			pm.makePersistent(galletita24);    			pm.makePersistent(galletita25);
    			pm.makePersistent(galletita26);    			pm.makePersistent(galletita27);
    			pm.makePersistent(galletita28);    			pm.makePersistent(galletita29);
    			pm.makePersistent(galletita30);    			pm.makePersistent(galletita31);
    			pm.makePersistent(galletita32);    			pm.makePersistent(galletita33);
    			pm.makePersistent(galletita34);    			pm.makePersistent(galletita35);
    			pm.makePersistent(galletita36);    			pm.makePersistent(galletita37);
    			pm.makePersistent(galletita38);    			pm.makePersistent(galletita39);
    			pm.makePersistent(galletita40);    			pm.makePersistent(galletita41);
    			pm.makePersistent(galletita42);    			pm.makePersistent(galletita43);
    			pm.makePersistent(galletita44);    			pm.makePersistent(galletita45);
    			pm.makePersistent(galletita46);    			pm.makePersistent(galletita47);
    			pm.makePersistent(galletita48);    			pm.makePersistent(galletita49);
    			pm.makePersistent(galletita50);
    			pm.makePersistent(panaderia0);    			pm.makePersistent(panaderia1);
    			pm.makePersistent(panaderia2);    			pm.makePersistent(panaderia3);
    			pm.makePersistent(panaderia4);    			pm.makePersistent(panaderia5);
    			pm.makePersistent(panaderia6);    			pm.makePersistent(panaderia7);
    			pm.makePersistent(panaderia8);    			pm.makePersistent(panaderia9);
    			pm.makePersistent(panaderia10);    			pm.makePersistent(panaderia11);
    			pm.makePersistent(panaderia12);    			pm.makePersistent(panaderia13);
    			pm.makePersistent(panaderia14);
    			pm.makePersistent(pepsico0);    			pm.makePersistent(pepsico1);
    			pm.makePersistent(pepsico2);    			pm.makePersistent(pepsico3);
    			pm.makePersistent(pepsico4);    			pm.makePersistent(pepsico5);
    			pm.makePersistent(pepsico6);
    			pm.makePersistent(variado0);    			pm.makePersistent(variado1);
    			pm.makePersistent(variado2);    			pm.makePersistent(variado3);
    			pm.makePersistent(variado4);    			pm.makePersistent(variado5);
    			pm.makePersistent(variado6);    			pm.makePersistent(variado7);
    			pm.makePersistent(variado8);    			pm.makePersistent(variado9);
    			pm.makePersistent(variado10);    			pm.makePersistent(variado11);
    			pm.makePersistent(variado12);    			pm.makePersistent(variado13);
    			pm.makePersistent(variado14);    			pm.makePersistent(variado15);
    			
    			tx.commit();
    			System.out.println("creación exitosa!");
    		}catch(Exception ex){ 
    			Utils.manejoErrores(ex,"Error en CargarDatos.");
    			if (tx.isActive()) tx.rollback();
    		}
    	}finally{
    		if (tx.isActive()) tx.rollback();
    	}
    }
}
