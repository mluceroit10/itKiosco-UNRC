package common.DTOs;

import java.io.Serializable;

public class UsuarioPlanillaESDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private UsuarioDTO usuario=null;
	private PlanillaESDTO planillaES=null;
	protected Long id=new Long(0);

	public UsuarioPlanillaESDTO(){
	}
	
	public void setId(Long id){
		this.id=id;
	}
	
	public Long getId(){
		return id;
	}
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	public PlanillaESDTO getPlanillaES() {
		return planillaES;
	}
	
	public void setPlanillaES(PlanillaESDTO planillaES) {
		this.planillaES = planillaES;
	}

}	 
