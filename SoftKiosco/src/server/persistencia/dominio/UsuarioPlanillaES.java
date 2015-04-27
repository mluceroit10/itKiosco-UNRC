package server.persistencia.dominio;

import server.persistencia.OidGenerator;

public class UsuarioPlanillaES{
	private Usuario usuario=null;
	private PlanillaES planillaES=null;
	protected Long id=new Long(0);
	
	public UsuarioPlanillaES(){
		id=OidGenerator.getNewId();
	}
	
	public void setId(Long id){
		this.id=id;
	}
	
	public Long getId(){
		return id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public PlanillaES getPlanillaES() {
		return planillaES;
	}
	
	public void setPlanillaES(PlanillaES planillaES) {
		this.planillaES = planillaES;
	}
		
}	 
