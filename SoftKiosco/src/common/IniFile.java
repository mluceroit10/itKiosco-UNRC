package  common;

import java.util.*;
import java.io.*;

public class IniFile {
	private String nameFile = "";  
	private Properties p = null;   

	public IniFile() { 
		p = new Properties();
	}

	public IniFile(String nameFile) {
		this.p = new Properties();
		this.nameFile = nameFile;
		this.setNameFile(this.nameFile);
	}

	/* Leer el parametro del archivo seteado */
	public String getParameters(String nombreParametro){
		return p.getProperty(nombreParametro).trim();
	}

	public void setNameFile(String nameFile) {
		try {
			this.nameFile = nameFile;
			p.load(new FileInputStream(this.nameFile));
		}catch (Exception ex) {
			Utils.manejoErrores(ex,"Error en IniFile. setNameFile.");
		}
	}

	public Set keySet(){
		return p.keySet();
	}

	public void listar(){
		p.list(System.out);
	}

	public static void main(String[] args) {
		// Leer el archivo de configuracion
		String archivoIni="conf.ini";
		IniFile iniFile = new IniFile();
		iniFile.setNameFile(archivoIni);
		System.out.println("Archivo Inicio: "+archivoIni);
	}

}

