package common;

public class RootAndIp {

	/* Valores por defecto - verificar el conf.ini */
	private static String base="C:/SoftKiosco/classes/";
	private static String extras="C:/SoftKiosco/extras/";
	private static String ip="localhost";
	private static String url_db="jdbc:mysql://localhost/SoftKioscoDB";
	private static String username = "root";
	private static String password = "";
	private static String archivos = "";
	private static String maquina = "servidor";

	public static String getArchivos() {
		return archivos;
	}
	
	public static String getUrlDb(){
		return url_db;        
	}    

	public static String getUserName(){
		return username;
	}  

	public static String getPassword(){
		return password;
	}

	public static String getIp() {
		return ip;
	}

	public static String getBase() {
		return base;
	}

	public static String getExtras() {
		return extras;
	}

	public static String getPathImagenes() {
		return extras+"imagenes/";
	}

	public static String getPathReports() {
		return extras+"reports/";
	}

	public static String getMaquina() {
		return maquina;
	}

	public static void setConf(String nameFile) {
		if ((nameFile == null)||(nameFile.trim().length() == 0)){
			nameFile = "conf.ini";
		}
		IniFile ini = new IniFile(nameFile);
		base = ini.getParameters("base");
		extras = ini.getParameters("extras");
		ip = ini.getParameters("host");
		url_db = ini.getParameters("url_db");
		username = ini.getParameters("username");
		password = ini.getParameters("password");
		archivos = ini.getParameters("pathArchivos");
		maquina = ini.getParameters("maquina");
		System.out.println("base: "+ base);
		System.out.println("extras: "+ extras);
		System.out.println("host: "+ ip);
		System.out.println("url_db:"+ url_db);
		System.out.println("username:" + username);
		System.out.println("password:"+ password);
		System.out.println("archivos:"+ archivos);
		System.out.println("maquina:"+ maquina);
	}
	
}

