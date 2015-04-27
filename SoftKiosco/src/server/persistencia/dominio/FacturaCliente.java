package server.persistencia.dominio;

public class FacturaCliente extends Factura{
	private boolean aCargoBecario; 
	private Usuario becario=null;
	private PlanillaES planilla;
	
	public boolean isACargoBecario() {
		return aCargoBecario;
	}
	
	public void setACargoBecario(boolean aCargoBecario) {
		this.aCargoBecario = aCargoBecario;
	}
	
	public Usuario getBecario() {
		return becario;
	}
	
	public void setBecario(Usuario vendedor) {
		this.becario = vendedor;
	}

	public PlanillaES getPlanilla() {
		return planilla;
	}

	public void setPlanilla(PlanillaES planilla) {
		this.planilla = planilla;
	}
		
}
