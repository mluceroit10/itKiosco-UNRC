package cliente.Principal;

import java.util.Date;
import java.util.Vector;

import cliente.ClienteConection;

import common.Utils;
import common.DTOs.UsuarioDTO;
import common.GestionarBecaAsignada.IControlBecaAsignada;
import common.GestionarUsuario.IControlUsuario;

public class MediadorAccionesIniciarPrograma{
	public IControlBecaAsignada controlBecaAsignada;
	public IControlUsuario controlUsuarios;
	
	public MediadorAccionesIniciarPrograma() throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorAccionesIniciarPrograma. Constructor");
		}
		controlBecaAsignada = clienteConexion.getIControlBecaAsignada();
		controlUsuarios = clienteConexion.getIControlUsuario();
		Date hoy= new Date();
		if(Utils.getDia(hoy)>=4){ 
			if(!controlBecaAsignada.hayBecasDelMesAnio(hoy)){
				controlBecaAsignada.cerrarYAbrirNuevasBecas(hoy);
			}else{
				Vector usuarios = controlUsuarios.obtenerUsuarios();
				for(int i=0;i<usuarios.size();i++){
					UsuarioDTO usr=(UsuarioDTO)usuarios.elementAt(i);
					Vector becas = controlBecaAsignada.obtenerBecasAsignadasDeUsuario(usr.getId());
					Vector usrSinBeca=new Vector();
					if(becas.isEmpty()){
						usrSinBeca.add(usr);
					}
					if(usrSinBeca.size()>0)
						controlBecaAsignada.abrirBecas(usrSinBeca,hoy);
				}
			}
		}
	}
	
}


