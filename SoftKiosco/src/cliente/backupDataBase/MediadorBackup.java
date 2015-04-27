package cliente.backupDataBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.Date;

import javax.swing.JFrame;

import common.RootAndIp;
import common.Utils;

public class MediadorBackup implements ActionListener{

	private GUIBackup guiBackup=null;
	private String pathBackup = common.RootAndIp.getArchivos()+ "/backup/";
	private String date;

	public MediadorBackup(JFrame guiPadre, boolean condicion) throws Exception{
		if(condicion){
			this.guiBackup = new GUIBackup(guiPadre);
			this.guiBackup.setActionListeners(this);
			this.backUpDataBase();	
		}else{
			if(RootAndIp.getMaquina().compareTo("servidor")==0){
				this.guiBackup = new GUIBackup(guiPadre);
				this.guiBackup.setActionListeners(this);
				this.backUpDataBase();
			}
		}
	}
	
	public void backUpDataBase(){
		boolean seHizo=false;
		try{
			this.backup();
			seHizo=true;
		}catch(Exception ex){
			Utils.manejoErroresSencillos(ex, "Error en MediadorBackup. backUpDataBase.");
			seHizo=false;
		}
		if(seHizo){
			try{
				this.guiBackup.getAdvertencia().setText("Se realiz� correctamente el Backup de su Base de Datos");
				this.guiBackup.getResultado().setText("El archivo se guardo en el directorio "+pathBackup+" \ncon el nombre backupBD_"+date+ ".SQL");
			}catch(Exception ex){
				Utils.manejoErroresSencillos(ex, "Error en MediadorBackup. backUpDataBase no se efectuo.");
				seHizo=false;
				Utils.advertenciaUsr(guiBackup, "Ocurri� un error en el sistema, mientras se intentaba\nrealizar el Backup de su Base de Datos. Intente nuevamente.");
			}
		}else{
			this.guiBackup.getAdvertencia().setText("Ocurri� un error mientras se intentaba realizar el Backup de su Base de Datos. Intente nuevamente.");
			this.guiBackup.getResultado().setText("No se cre� el archivo correspondiente al Backup de la Base de Datos.");
		}
		Utils.show(guiBackup);
	}

	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(this.guiBackup.getJButtonSalir()==source){
			this.guiBackup.dispose();
		}
	}

	public void backup()throws Exception{
		Runtime rt= Runtime.getRuntime();
		FileOutputStream archivo= new FileOutputStream(pathBackup+"backup.bat");
		Date fecha= new Date();
		int p = fecha.toLocaleString().indexOf(" ");
		String primerParte = fecha.toLocaleString().substring(0,p);
		boolean esConGuion = false; 
		int barra1 = primerParte.indexOf("-");
		if(barra1 >= 0) {
			esConGuion = true;
		}
		String diaMesAnio ="";
		if(!esConGuion){
			barra1 = primerParte.indexOf("/");
			if(barra1 < 0 ){
				barra1 = primerParte.indexOf(".");
			}
			String parte1Barra = primerParte.substring(0,barra1);
			diaMesAnio = parte1Barra +"-"+ primerParte.substring(barra1+1,barra1+3)+"-"+primerParte.substring(barra1+4,primerParte.length());
		}else{
			diaMesAnio  = primerParte.substring(0,11);
		}
		String segundaParte = fecha.toLocaleString().substring(p+1,fecha.toLocaleString().length());
		int punto1 = segundaParte.indexOf(":");
		String parte1Punto = segundaParte.substring(0,punto1);
		String horaMinSeg = parte1Punto +"-"+ segundaParte.substring(punto1+1,punto1+3)+"-"+segundaParte.substring(punto1+4,segundaParte.length());
		date = diaMesAnio+"_hr_"+horaMinSeg;
		String nameURL=RootAndIp.getUrlDb();
		String[] datos=nameURL.split("/");
		String nameDB=datos[datos.length-1];
		// --host=""
		String maq=RootAndIp.getMaquina();
		String linea="";
		if(maq.compareTo("servidor")==0)
			linea= new String("C:/mysql/bin/MYSQLDUMP.EXE --opt --password="+common.RootAndIp.getPassword()+" --user="+common.RootAndIp.getUserName()+" "+nameDB+" jdo_table oid provincia localidad " +
					"kiosco proveedor usuario producto planilla_es factura factura_cliente factura_proveedor item_factura movimiento_caja usuario_planilla_es sesion beca_asignada >"+pathBackup+"backupBD_"+date+".SQL");
		else if(maq.compareTo("cliente")==0)
			linea= new String("C:/mysql/bin/MYSQLDUMP.EXE --opt --host="+RootAndIp.getIp()+" --password="+common.RootAndIp.getPassword()+" --user="+common.RootAndIp.getUserName()+" "+nameDB+" jdo_table oid provincia localidad " +
					"kiosco proveedor usuario producto planilla_es factura factura_cliente factura_proveedor item_factura movimiento_caja usuario_planilla_es sesion beca_asignada >"+pathBackup+"backupBD_"+date+".SQL");
		
		archivo.write(linea.getBytes());
		archivo.close();
		rt.exec(pathBackup+"backup.bat");
	}
}
