package cliente.GestionarSesion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import cliente.ClienteConection;

import common.Utils;
import common.DTOs.SesionDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarSesion.IControlSesion;
import common.GestionarUsuario.IControlUsuario;

public class MediadorMostrarHorasUsuario implements ActionListener{
	private GUIMostrarHorasUsuario guiHoras = null;
	public IControlSesion controlSesion;
	public IControlUsuario controlUsuario;
	public Date fecha;
	public Vector productos=new Vector();
	public String titulo="";
	public boolean todos=true;
	public Vector idsUsrs=new Vector();
	private UsuarioDTO usrDTO;
	private int mesHT;
	private int anioHT;

	public MediadorMostrarHorasUsuario(GUIHorasTrabajadas guiPadre,Long idUsr,int mes,int anio) throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorMostrarHorasUsuario. Constructor");
		}
		controlUsuario = clienteConexion.getIControlUsuario();
		controlSesion = clienteConexion.getIControlSesion();
		usrDTO=controlUsuario.buscarUsuarioDTOId(idUsr);
		mesHT=mes;
		anioHT=anio;
		this.guiHoras = new GUIMostrarHorasUsuario(guiPadre,usrDTO.getApellido()+" "+usrDTO.getNombre(),mesHT+"/"+anioHT);
		this.guiHoras.setActionListeners(this);
		cargarDatos();
		Utils.show(guiHoras);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiHoras.getJBSalir()) {
			guiHoras.dispose();
		}
	}

	public void cargarDatos() {
		try {
			int cantDias=Utils.obtenerCantidadDiasMes(mesHT,anioHT);
			double cantTotal=0;
			guiHoras.datos = new Object[cantDias][guiHoras.titulos.length];
			for (int j = 1; j <= cantDias; j++) {
				Vector sesionesDia=controlSesion.obtenerSesionesUsuario(usrDTO.getId(), j, mesHT, anioHT);
				long cantMs=0;
				String horasT="";
				for(int cSD = 0; cSD <sesionesDia.size();cSD++){
					SesionDTO ses=(SesionDTO)sesionesDia.elementAt(cSD);
					String diaFin="";
					if (Utils.getDia(ses.getCierre())!=j) diaFin=Utils.getStrUtilDate(ses.getCierre());
					horasT += Utils.getHoraMSUtilDate(ses.getInicio())+" a "+diaFin+" "+Utils.getHoraMSUtilDate(ses.getCierre())+" - ";
					cantMs=cantMs + (ses.getCierre().getTime()-ses.getInicio().getTime());
				}
				double cantHs=Utils.getHorasDeMiliseg(cantMs);
				cantTotal = Utils.redondear((cantTotal+cantHs),2);
				String cantHoras="";
				if(cantHs!=0){ 
					cantHoras=Utils.ordenarDosDecimales(cantHs);
				}	
				Object[] temp = {Utils.mostrarDiaYMes(j,mesHT,anioHT),horasT,cantHoras};
				guiHoras.datos[j-1] = temp;
			}
			guiHoras.getJTFHrTotal().setText(Utils.ordenarDosDecimales(cantTotal));
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorMostrarHorasUsuario. CargarDatos");
		}
		guiHoras.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiHoras.actualizarTabla();
	}

	public void valueChanged(ListSelectionEvent arg0) {
	}

}


