package reports;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import common.RootAndIp;
import common.Utils;
import common.DTOs.KioscoDTO;
import common.DTOs.ProductoDTO;
import common.DTOs.ProveedorDTO;
import common.DTOs.UsuarioDTO;

import dori.jasper.engine.JRException;
import dori.jasper.engine.JasperCompileManager;
import dori.jasper.engine.JasperExportManager;
import dori.jasper.engine.JasperFillManager;
import dori.jasper.engine.JasperPrint;
import dori.jasper.engine.JasperReport;

public class JasperReports{
	public static String PATH_REPORT_XML = RootAndIp.getPathReports();
	public static String PATH_REPORT_PDF = common.RootAndIp.getArchivos()+"/reportes/";

	public static JasperPrint listarTodosProductosBajoStock(Vector productos, String titulo) throws Exception{  //codRep==1
		JasperPrint jasperPrint;
		try{
			Date fecha= new Date();
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] param = { {"Titulo",titulo},{"Fecha",Utils.getStrUtilDate(fecha)},{"Institucion",Utils.Institucion()}};
			Object[][] values = new Object[productos.size()][4];;
			for (int i = 0; i < productos.size(); i++) {
				ProductoDTO prod= (ProductoDTO)productos.elementAt(i);
				int restan= prod.getStockMinimo() - prod.getStockActual();
				String resto = String.valueOf(restan);
				Object[] temp = {String.valueOf(prod.getCodigo()),
						prod.getNombre(),
						prod.getProveedor().getNombre(),
						resto
				};
				values[i] = temp;
			}
			String[] fieldXml = { "Codigo","Prod_Kilos","Proveedor","Resto"};
			jasperPrint=generarReporte("ListadoTodosProductosBajoStock", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. listarTodosProductosBajoStock.");
			return null;
		}
	}

	public static JasperPrint listarProductosProveedorBajoStock(Vector productos, String titulo) throws Exception{ //codRep==2 
		JasperPrint jasperPrint;
		try{
			Date fecha= new Date();
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] param = { {"Titulo",titulo},{"Fecha",Utils.getStrUtilDate(fecha)},{"Institucion",Utils.Institucion()}};
			Object[][] values = new Object[productos.size()][3];;
			for (int i = 0; i < productos.size(); i++) {
				ProductoDTO prod= (ProductoDTO)productos.elementAt(i);
				int restan= prod.getStockMinimo() - prod.getStockActual();
				String resto = String.valueOf(restan);
				Object[] temp = {String.valueOf(prod.getCodigo()),
						prod.getNombre(),
						resto};
				values[i] = temp;
			}
			String[] fieldXml = { "Codigo","Prod_Kilos","Resto"};
			jasperPrint=generarReporte("ListadoProductosBajoStockProveedor", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. ListadoProductosBajoStockProveedor.");
			return null;
		}
	}	

	public static JasperPrint listarTodosProductosDisponibles(Vector productos, String titulo) throws Exception{ //codRep==3
		JasperPrint jasperPrint;
		try{
			Date fecha= new Date();
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] param = { {"Titulo",titulo},{"Fecha",Utils.getStrUtilDate(fecha)},{"Institucion",Utils.Institucion()}};
			Object[][] values = new Object[productos.size()][5];;
			for (int i = 0; i < productos.size(); i++) {
				ProductoDTO prod= (ProductoDTO)productos.elementAt(i);
				String pent = Utils.ordenarDosDecimales(prod.getPrecioEntrada());
				String pventa = Utils.ordenarDosDecimales(prod.getPrecioVenta());
				Object[] temp = {String.valueOf(prod.getCodigo()),
						prod.getNombre(),
						prod.getProveedor().getNombre(),
						pent,pventa};

				values[i] = temp;
			}
			String[] fieldXml = { "Codigo","Prod_Kilos","Proveedor","PrecioEntrada","PrecioVenta"};
			jasperPrint=generarReporte("ListadoTodosProductos", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. listarTodosProductosDisp.");
			return null;
		}
	}

	public static JasperPrint listarTodosProductosDisponiblesPrecio(Vector productos, String titulo, String precio) throws Exception{ //codRep==4 
		JasperPrint jasperPrint;
		try{
			Date fecha= new Date();
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] param = { {"Titulo",titulo},{"Fecha",Utils.getStrUtilDate(fecha)},{"Institucion",Utils.Institucion()},{"Precio","$ "+precio}};
			Object[][] values = new Object[productos.size()][4];;
			for (int i = 0; i < productos.size(); i++) {
				ProductoDTO prod= (ProductoDTO)productos.elementAt(i);
				String prEV="";
				if(precio.compareTo("ENTRADA")==0)
					prEV = Utils.ordenarDosDecimales(prod.getPrecioEntrada());
				if(precio.compareTo("VENTA")==0)
					prEV = Utils.ordenarDosDecimales(prod.getPrecioVenta());
				Object[] temp = {String.valueOf(prod.getCodigo()),
						prod.getNombre(),
						prod.getProveedor().getNombre(),
						prEV};

				values[i] = temp;
			}
			String[] fieldXml = { "Codigo","Prod_Kilos","Proveedor","PrecioEV"};
			jasperPrint=generarReporte("ListadoTodosProductosPrecio", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. listarTodosProductosPrecioDisp.");
			return null;
		}
	}

	public static JasperPrint listarProductosProveedorDisponibles(Vector productos, String titulo) throws Exception{  //codRep==5
		JasperPrint jasperPrint;
		try{
			Date fecha= new Date();
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] param = { {"Titulo",titulo},{"Fecha",Utils.getStrUtilDate(fecha)},{"Institucion",Utils.Institucion()}};
			Object[][] values = new Object[productos.size()][4];;
			for (int i = 0; i < productos.size(); i++) {
				ProductoDTO prod= (ProductoDTO)productos.elementAt(i);
				String pent = Utils.ordenarDosDecimales(prod.getPrecioEntrada());
				String pventa = Utils.ordenarDosDecimales(prod.getPrecioVenta());
				Object[] temp = {String.valueOf(prod.getCodigo()),
						prod.getNombre(),
						pent,pventa};
				values[i] = temp;
			}
			String[] fieldXml = { "Codigo","Prod_Kilos","PrecioEntrada","PrecioVenta"};
			jasperPrint=generarReporte("ListadoProductosProveedor", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. ListadoProductosProveedorDisp.");
			return null;
		}
	}

	public static JasperPrint listarProductosProveedorDisponiblesPrecio(Vector productos, String titulo,String precio) throws Exception{ //codRep==6
		JasperPrint jasperPrint;
		try{
			Date fecha= new Date();
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] param = { {"Titulo",titulo},{"Fecha",Utils.getStrUtilDate(fecha)},{"Institucion",Utils.Institucion()},{"Precio","$ "+precio}};
			Object[][] values = new Object[productos.size()][3];;
			for (int i = 0; i < productos.size(); i++) {
				ProductoDTO prod= (ProductoDTO)productos.elementAt(i);
				String prEV="";
				if(precio.compareTo("ENTRADA")==0)
					prEV = Utils.ordenarDosDecimales(prod.getPrecioEntrada());
				if(precio.compareTo("VENTA")==0)
					prEV = Utils.ordenarDosDecimales(prod.getPrecioVenta());
				Object[] temp = {String.valueOf(prod.getCodigo()),
						prod.getNombre(),
						prEV};
				values[i] = temp;
			}
			String[] fieldXml = { "Codigo","Prod_Kilos","PrecioEV"};
			jasperPrint=generarReporte("ListadoProductosProveedorPrecio", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. ListadoProductosProveedorPrecioDisp.");
			return null;
		}
	}

	public static JasperPrint listarTodosProveedores(Vector proveedores, String titulo) throws Exception{ //codRep==7
		JasperPrint jasperPrint;
		try{
			Date fecha= new Date();
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] param = { {"Titulo",titulo},{"Fecha",Utils.getStrUtilDate(fecha)},{"Institucion",Utils.Institucion()}};
			Object[][] values = new Object[proveedores.size()][5];;
			for (int i = 0; i < proveedores.size(); i++) {
				ProveedorDTO prov= (ProveedorDTO)proveedores.elementAt(i);
				Object[] temp = {String.valueOf(prov.getCodigo()),prov.getNombre(),
						prov.getDireccion(),prov.getLocalidad().getNombre(),prov.getTelefono()};
				values[i] = temp;
			}
			String[] fieldXml = { "Codigo","Proveedor","Direccion","Localidad","Telefono"};
			jasperPrint=generarReporte("ListadoProveedores", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. ListadoClientes.");
			return null;
		}
	}	

	public static JasperPrint listarTodosUsuarios(Vector usuarios, String titulo) throws Exception{ //codRep==8
		JasperPrint jasperPrint;
		try{
			Date fecha= new Date();
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] param = { {"Titulo",titulo},{"Fecha",Utils.getStrUtilDate(fecha)},{"Institucion",Utils.Institucion()}};
			Object[][] values = new Object[usuarios.size()][5];;
			for (int i = 0; i < usuarios.size(); i++) {
				UsuarioDTO cte= (UsuarioDTO)usuarios.elementAt(i);
				Object[] temp = {cte.getApellido()+" "+cte.getNombre(),String.valueOf(cte.getDni()),
						cte.getTelefono(),cte.getDireccion(),cte.getLocalidad().getNombre()};
				values[i] = temp;
			}
			if(usuarios.size()==0){
				values = new Object[1][5];;
				Object[] temp ={"","NO se encontraron clientes registrados","","",""};
				values[0] = temp;
			}
			String[] fieldXml = { "Usuario","Dni","Telefono","Direccion","Localidad"};
			jasperPrint=generarReporte("ListadoUsuarios", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. ListadoUsuarios.");
			return null;
		}
	}	

	public static JasperPrint tarjetaKiosco(String nombre, String encargado, String tel, String dir,String loc){ //codRep==9
		JasperPrint jasperPrint;
		System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
		Object[][] param = { {"Nombre",nombre},{"Encargado",encargado},{"Tel",tel},{"Dir",dir},{"Loc",loc}};
		Object[][] values = new Object[1][0];
		String[] fieldXml = {};
		jasperPrint=generarReporte("TarjetaKiosco", param, fieldXml, values);
		return jasperPrint;
	}

	public static JasperPrint facturarProveedor(Vector productos,Vector cantidades,Vector precioUnit,Vector prTotIt,String nroFact,Timestamp fechaFact,  //codRep==10 
			KioscoDTO kiosco, ProveedorDTO prov,double iTotal){ 
		JasperPrint jasperPrint;
		try{
			String cpost="";
			if(kiosco.getLocalidad().getCodPostal()!=null || (kiosco.getLocalidad().getCodPostal().compareTo("")!=0)) 
				cpost="("+kiosco.getLocalidad().getCodPostal()+")";
			String dir = kiosco.getDireccion()+" "+kiosco.getLocalidad().getNombre()+cpost;
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] param = { {"nroFact",nroFact},{"FechaFact",Utils.getStrUtilDate(fechaFact)+" "+Utils.getHoraUtilDate(fechaFact)},
					{"ProvNombre",prov.getNombre()},
					{"Institucion",kiosco.getNombre()},{"InstDomicilio",dir},{"TotalFact",Utils.ordenarDosDecimales(iTotal)}
			};
			Object[][] values = new Object[productos.size()][4];;
			for (int i = 0; i < productos.size(); i++) {
				ProductoDTO prod= (ProductoDTO)productos.elementAt(i);
				String cant=(String) cantidades.elementAt(i);
				String prUnit=(String) precioUnit.elementAt(i);
				String prtotal=(String) prTotIt.elementAt(i);
				String descr=prod.getCodigo()+"-"+prod.getNombre();
				Object[] temp = {cant,descr,prUnit,prtotal};
				values[i] = temp;
			}
			String[] fieldXml = { "Cant","Producto","PUnit","PTotal"};
			jasperPrint=generarReporte("FacturaProveedor", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. facturarProveedor.");
			return null;
		}
	}

	public static JasperPrint facturarCliente(Vector productos,Vector cantidades, Vector precioUnit,Vector prTotIt, String nroFact,Timestamp fechaFact,  //codRep==11
			KioscoDTO kiosco, UsuarioDTO aCuentaDe, double iTotal,boolean aCargoBecario){ 
		JasperPrint jasperPrint;
		try{
			String fecha=Utils.getStrUtilDate(fechaFact);
			String cpost="";
			if(kiosco.getLocalidad().getCodPostal()!=null || (kiosco.getLocalidad().getCodPostal().compareTo("")!=0))
				cpost="("+kiosco.getLocalidad().getCodPostal()+")";
			String dir = kiosco.getDireccion()+" "+kiosco.getLocalidad().getNombre()+cpost;
			String aCargo="";
			if(aCargoBecario) aCargo=aCuentaDe.getApellido()+" "+aCuentaDe.getNombre();
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] param = { {"Institucion",kiosco.getNombre()},{"InstEncargado",kiosco.getEncargado()},{"InstDomicilio",dir},{"InstTel",kiosco.getTelefono()},
					{"nroCompr",nroFact},{"FechaCompr",fecha},
					{"aCuentaDe",aCargo},
					{"TotalCompr",Utils.ordenarDosDecimales(iTotal)}
			};
			Object[][] values = new Object[productos.size()][4];;
			for (int i = 0; i < productos.size(); i++) {
				ProductoDTO prod= (ProductoDTO)productos.elementAt(i);
				String cant=(String) cantidades.elementAt(i);
				String prUnit=(String) precioUnit.elementAt(i);
				String prtotal=(String) prTotIt.elementAt(i);
				String descr=prod.getCodigo()+"-"+prod.getNombre();
				Object[] temp = {cant,descr,prUnit,prtotal};
				values[i] = temp;
			}
			String[] fieldXml = { "Cant","Producto","PUnit","PTotal"};
			jasperPrint=generarReporte("ComprobanteCliente", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. facturarCliente.");
			return null;
		}
	}

	public static JasperPrint generarBalanceGeneral(int nroPl,Timestamp fecha, Vector usuarios,
			double ingresoCajaDiaria,double ingCD,double egrCD,double saldoCajaDiaria,
			double saldoCajaGeneralAnt,double ingCG,double egrCG,double saldoCajaGeneral, double dineroRetira, double dineroCaja) throws Exception{ //codRep==12
		JasperPrint jasperPrint;
		try{
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] values = new Object[usuarios.size()][1];;
			for (int i = 0; i < usuarios.size(); i++) {
				UsuarioDTO usr = (UsuarioDTO) usuarios.elementAt(i);
				Object[] temp = {usr.getApellido()+" "+usr.getNombre()};
				values[i] = temp;
			}
			if(usuarios.size()==0){
				values=new Object[1][1];;
				Object[] temp = {"No se registraron becarios"};
				values[0] = temp;
			}	
			String[] fieldXml = { "Becario" };
			Object[][] param = { {"NroPlanilla",String.valueOf(nroPl)},{"Fecha",Utils.getStrUtilDate(fecha)+" "+Utils.getHoraUtilDate(fecha)},
					{"SaldoCajaDiaria" ,Utils.ordenarDosDecimales(ingresoCajaDiaria)},
					{"TotalEntradasCDiaria",Utils.ordenarDosDecimales(ingCD)},{"TotalSalidasCDiaria",Utils.ordenarDosDecimales(egrCD)},
					{"TotalCajaDiaria",Utils.ordenarDosDecimales(saldoCajaDiaria)},
					{"SaldoCajaGeneral",Utils.ordenarDosDecimales(saldoCajaGeneralAnt)},
					{"TotalEntradasCGeneral",Utils.ordenarDosDecimales(ingCG)},{"TotalSalidasCGeneral",Utils.ordenarDosDecimales(egrCG)},
					{"TotalCajaGeneral",Utils.ordenarDosDecimales(saldoCajaGeneral)},
					{"Institucion",Utils.Institucion()},{"DineroRetira",Utils.ordenarDosDecimales(dineroRetira)},{"DineroCaja",Utils.ordenarDosDecimales(dineroCaja)}};
			jasperPrint=generarReporte("PlanillaDeCaja", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. generarBalanceGeneral.");
			return null;
		}
	} 

	public static JasperPrint generarBalanceDiario(int nroPl,Timestamp fecha, Vector usuarios,
			double ingresoCajaDiaria,double ingCD,double egrCD,double saldoCajaDiaria, double dineroRetira, double dineroCaja) throws Exception{ //codRep==12
		JasperPrint jasperPrint;
		try{
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] values = new Object[usuarios.size()][1];;
			for (int i = 0; i < usuarios.size(); i++) {
				UsuarioDTO usr = (UsuarioDTO) usuarios.elementAt(i);
				Object[] temp = {usr.getApellido()+" "+usr.getNombre()};
				values[i] = temp;
			}
			if(usuarios.size()==0){
				values=new Object[1][1];;
				Object[] temp = {"No se registraron becarios"};
				values[0] = temp;
			}	
			String[] fieldXml = { "Becario" };
			Object[][] param = { {"NroPlanilla",String.valueOf(nroPl)},{"Fecha",Utils.getStrUtilDate(fecha)+" "+Utils.getHoraUtilDate(fecha)},
					{"SaldoCajaDiaria" ,Utils.ordenarDosDecimales(ingresoCajaDiaria)},
					{"TotalEntradasCDiaria",Utils.ordenarDosDecimales(ingCD)},{"TotalSalidasCDiaria",Utils.ordenarDosDecimales(egrCD)},
					{"TotalCajaDiaria",Utils.ordenarDosDecimales(saldoCajaDiaria)},
					{"Institucion",Utils.Institucion()},{"DineroRetira",Utils.ordenarDosDecimales(dineroRetira)},{"DineroCaja",Utils.ordenarDosDecimales(dineroCaja)}};
			jasperPrint=generarReporte("CierreDeCaja", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. generarBalanceDiario.");
			return null;
		}
	} 

	public static JasperPrint detallarCuentaCliente(KioscoDTO kiosco,String titulo, Vector detalle,Vector fecha, Vector debe, Vector haber, Vector saldo){  //codRep==13 
		JasperPrint jasperPrint;
		try{
			Date hoy= new Date();
			String fechaHoy=Utils.getStrUtilDate(hoy);
			// Estado de cuenta neg deuda.
			System.setProperty("org.xml.sax.driver","org.apache.xerces.parsers.SAXParser");
			Object[][] param = { {"Institucion",kiosco.getNombre()},{"Titulo",titulo},{"FechaHoy",fechaHoy}};
			Object[][] values = new Object[detalle.size()][5];;
			for (int i = 0; i < detalle.size(); i++) {
				String det = (String)detalle.elementAt(i);
				String fe = (String)fecha.elementAt(i);
				String de = (String)debe.elementAt(i);
				String ha = (String)haber.elementAt(i);
				String sa = (String)saldo.elementAt(i);
				Object[] temp = {det,fe,de,ha,sa};
				values[i] = temp;
			}
			String[] fieldXml = {"DetalleIt","Fecha","Debe","Haber","Saldo"};
			jasperPrint=generarReporte("DetalleCuentaCliente", param, fieldXml, values);
			return jasperPrint;
		}catch (Exception ex){
			Utils.manejoErrores(ex,"Error en JasperReports. detalleCuentaCliente.");
			return null;
		}
	}

	private static JasperPrint generarReporte(String report, Object[][] param, String[] fieldXml, Object[][] values){
		JasperReport jasperReport;
		JasperPrint jasperPrint; 
		try {
			java.util.HashMap parameters = new java.util.HashMap();
			for (int i = 0; i < param.length; i++) {
				parameters.put(param[i][0], param[i][1]);
			}
			java.util.Hashtable ht = new java.util.Hashtable();
			for (int i = 0; i < fieldXml.length; i++) {
				ht.put(fieldXml[i], new Integer(i));
			}
			DataSourceJasper data = new DataSourceJasper(values, ht);
			String fileXML = PATH_REPORT_XML + report + ".xml";
			String fileJRPRINT = PATH_REPORT_PDF + report + ".pdf";
			// 1-Compilamos el archivo XML y lo cargamos en memoria			
			jasperReport = JasperCompileManager.compileReport(fileXML);
			// 2-Llenamos el reporte con la información y parámetros necesarios 
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,data);
			JasperExportManager.exportReportToPdfFile(jasperPrint,fileJRPRINT);
			return jasperPrint;
		} catch (JRException ex) {
			Utils.manejoErrores(ex,"Error en JasperReports. generarReporte.");
			return null;
		}
	}

	public static void main(String[] args){
		;
	}

}