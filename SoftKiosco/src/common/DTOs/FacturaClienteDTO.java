package common.DTOs;

import java.io.Serializable;

public class FacturaClienteDTO extends FacturaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean aCargoBecario; 
	private UsuarioDTO becario=new UsuarioDTO();
	private PlanillaESDTO planilla=new PlanillaESDTO();

	public FacturaClienteDTO(){
	}

	public boolean isACargoBecario() {
		return aCargoBecario;
	}

	public void setACargoBecario(boolean aCargoBecario) {
		this.aCargoBecario = aCargoBecario;
	}

	public UsuarioDTO getBecario() {
		return becario;
	}

	public void setBecario(UsuarioDTO vendedor) {
		this.becario = vendedor;
	}

	public PlanillaESDTO getPlanilla() {
		return planilla;
	}

	public void setPlanilla(PlanillaESDTO planilla) {
		this.planilla = planilla;
	}

}
