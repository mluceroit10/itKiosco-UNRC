package cliente.Principal;

import javax.swing.JFrame;

import cliente.ClienteConection;
import cliente.backupDataBase.MediadorBackup;

import common.Utils;
import common.GestionarSesion.IControlSesion;

public class MediadorAccionesCerrarPrograma{
	public IControlSesion controlSesion;

	public MediadorAccionesCerrarPrograma(JFrame guiPadre) throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorAccionesCerrarPrograma. Constructor");
		}
		controlSesion = clienteConexion.getIControlSesion();
		controlSesion.cerrarSesionesAbiertas();
		new MediadorBackup(guiPadre,false);
	}
	
}


