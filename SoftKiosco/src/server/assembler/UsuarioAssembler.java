package server.assembler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import server.persistencia.dominio.*;
import common.DTOs.*;

public class UsuarioAssembler {

	public UsuarioAssembler() {
	}

	public static UsuarioDTO getUsuarioDTO(Usuario objD ) {
		UsuarioDTO usrDTO = new UsuarioDTO();
		usrDTO.setId(objD.getId());
		usrDTO.setApellido(objD.getApellido());
		usrDTO.setContrasenia(objD.getContrasenia());
		usrDTO.setDireccion(objD.getDireccion());
		usrDTO.setDni(objD.getDni());
		usrDTO.setImporteBeca(objD.getImporteBeca());
		usrDTO.setNombre(objD.getNombre());
		usrDTO.setTelefono(objD.getTelefono());
		usrDTO.setTipoUsuario(objD.getTipoUsuario());
		usrDTO.setNombUsuario(objD.getNombUsuario());
		usrDTO.setEliminado(objD.isEliminado());
		return usrDTO;
	}

	public static Usuario getUsuario(UsuarioDTO objDTO) {
		Usuario usr = new Usuario();
		usr.setApellido(objDTO.getApellido());
		usr.setContrasenia(objDTO.getContrasenia());
		usr.setDireccion(objDTO.getDireccion());
		usr.setDni(objDTO.getDni());
		usr.setImporteBeca(objDTO.getImporteBeca());
		usr.setNombre(objDTO.getNombre());
		usr.setTelefono(objDTO.getTelefono());
		usr.setTipoUsuario(objDTO.getTipoUsuario());
		usr.setNombUsuario(objDTO.getNombUsuario());
		usr.setEliminado(objDTO.isEliminado());
		return usr;
	}
	
	public static Set getConjunto(Vector v){
		Set aux = new HashSet();
		Iterator it = v.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==UsuarioDTO.class) {
				aux.add(getUsuario((UsuarioDTO) obj));
			}
		}
		return aux;
	}

	public static Vector getVector (Set s) {
		Vector auxDTO = new Vector();
		auxDTO.clear();
		Iterator it = s.iterator();
		Object obj;
		while(it.hasNext()){
			obj = it.next();
			if (obj.getClass()==Usuario.class) {
				auxDTO.add(getUsuarioDTO((Usuario) obj));
			}
		}
		return auxDTO;
	}
	
}