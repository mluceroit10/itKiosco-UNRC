package cliente.GestionarMovimientoCaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.ClienteConection;

import common.Utils;
import common.DTOs.MovimientoCajaDTO;
import common.GestionarMovimientoCaja.IControlMovimientoCaja;
import common.constantes.TIPO_CAJA;

public class MediadorGestionarMovimientoCaja implements ActionListener, KeyListener, ListSelectionListener {
	private GUIGestionarMovimientoCaja guiMovimientoCaja = null;
	protected IControlMovimientoCaja controlMovimientoCaja;
    private int mesLI;
	private int anioLI;

	public MediadorGestionarMovimientoCaja(int mes, int anio,JFrame guiPadre) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			mesLI=mes;
    		anioLI=anio;
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarMovimientoCaja. Constructor");
		}
		controlMovimientoCaja = clienteConexion.getIControlMovimientoCaja();
		this.guiMovimientoCaja = new GUIGestionarMovimientoCaja(mes,anio,guiPadre);
		this.guiMovimientoCaja.setActionListeners(this);
		cargarDatos();
		this.guiMovimientoCaja.setListSelectionListener(this);
		this.guiMovimientoCaja.setKeyListener(this);
		Utils.show(guiMovimientoCaja);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiMovimientoCaja.getJBCargar()) {
			try {
				new MediadorAltaMovimientoCaja(this,guiMovimientoCaja);
			} catch (Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorGestionarMovimientoCaja. ActionPerformed");
			}
		} else if (source == guiMovimientoCaja.getJBBorrar()) {
			eliminar();
		}else if (source == guiMovimientoCaja.getJBCambiarPeriodo()){
        	String anioB = guiMovimientoCaja.getJTFAnio().getText();
        	if(anioB.length()==0){
				Utils.advertenciaUsr(guiMovimientoCaja,"Por favor ingrese el Año.");
			}else if(anioB.length()!=4){
				Utils.advertenciaUsr(guiMovimientoCaja,"El año debe ser un número de 4 dígitos.");
			}else{
				anioLI= Integer.parseInt(anioB);
				mesLI = guiMovimientoCaja.getJCBMes().getSelectedIndex()+1; //para que el numero del indice de con el mes sumo 1
	         	cargarDatos();
			}	
		} else if (source == guiMovimientoCaja.getJBAceptar()) {
			guiMovimientoCaja.dispose();
		}	
	}

	public void cargarDatos() {
		try {
			Vector movsCaja = this.controlMovimientoCaja.obtenerMovimientosCaja(mesLI,anioLI);
			guiMovimientoCaja.datos = new Object[movsCaja.size()][guiMovimientoCaja.titulos.length];
			guiMovimientoCaja.getJTFPeriodo().setText(mesLI+" - "+anioLI);
			int i = 0;
			for (int j = 0; j < movsCaja.size(); j++) {
				MovimientoCajaDTO mc=(MovimientoCajaDTO)movsCaja.elementAt(j);
				String tipoMov="SALIDA";
				if(mc.getTipoMovimiento()==1){
					tipoMov="ENTRADA";
				}
				String pagoProv="No";
				String nroFact="";
				if(mc.isConFactura()){
					pagoProv="Si";
					nroFact = Utils.nroFact(mc.getFactura().getNroFactura());
				}
				String caja="GENERAL";
				if(mc.getTipoCajaRegistrado().compareTo(TIPO_CAJA.DIARIA)==0) caja="DIARIA";
				Object[] temp = {mc.getId(),String.valueOf(mc.getNroRecibo()),common.Utils.getStrUtilDate(mc.getFecha())+" "+Utils.getHoraUtilDate(mc.getFecha()),caja,tipoMov,Utils.ordenarDosDecimales(mc.getImporte()),mc.getDescripcion(),pagoProv,nroFact};
				guiMovimientoCaja.datos[i] = temp;
				i++;
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarMovimientoCaja. CargarDatos");
		}
		guiMovimientoCaja.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiMovimientoCaja.actualizarTabla();
	}

	private void actualizarTablaCodFecha() {
		try {
			Vector movsCaja = this.controlMovimientoCaja.obtenerMovimientosCajaNroyFecha(mesLI,anioLI,guiMovimientoCaja.getJTFBuscadorCodigo().getText(),guiMovimientoCaja.getJTFBuscadorFecha().getText());
			guiMovimientoCaja.datos = new Object[movsCaja.size()][guiMovimientoCaja.titulos.length];
			for (int j = 0; j < movsCaja.size(); j++) {
				MovimientoCajaDTO mc=(MovimientoCajaDTO)movsCaja.elementAt(j);
				String tipoMov="SALIDA";
				if(mc.getTipoMovimiento()==1){
					tipoMov="ENTRADA";
				}
				String pagoProv="No";
				String nroFact="";
				if(mc.isConFactura()){
					pagoProv="Si";
					nroFact = Utils.nroFact(mc.getFactura().getNroFactura());
				}
				String caja="GENERAL";
				if(mc.getTipoCajaRegistrado().compareTo(TIPO_CAJA.DIARIA)==0) caja="DIARIA";
				Object[] temp = {mc.getId(),String.valueOf(mc.getNroRecibo()),common.Utils.getStrUtilDate(mc.getFecha())+" "+Utils.getHoraUtilDate(mc.getFecha()),caja,tipoMov,Utils.ordenarDosDecimales(mc.getImporte()),mc.getDescripcion(),pagoProv,nroFact};
				guiMovimientoCaja.datos[j] = temp;
			}
		} catch(Exception ex) {
			Utils.manejoErrores(ex,"Error en MediadorGestionarMovimientoCaja. ActualizarTablaFechaMov");
		}
		guiMovimientoCaja.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiMovimientoCaja.actualizarTabla();
	}

	private void eliminar() {
		try{
			if ( this.controlMovimientoCaja.obtenerMovimientosCaja(mesLI,anioLI).isEmpty()){
				Utils.advertenciaUsr(guiMovimientoCaja,"No hay Movimientos de Caja guardados.");
			} else {
				if (guiMovimientoCaja.jtDatos.getSelectedRow() == -1){
					Utils.advertenciaUsr(guiMovimientoCaja,"Para poder Eliminar un Movimiento de Caja debe seleccionarlo previamente.");
				} else {
					Long id = (Long)guiMovimientoCaja.datos[guiMovimientoCaja.jtDatos.getSelectedRow()][0];
					if (controlMovimientoCaja.movimientoAsociado(id)) {
						Utils.advertenciaUsr(guiMovimientoCaja,"El Movimiento de Caja no puede ser borrado.");
					}else{
						MovimientoCajaDTO mcDTO = (MovimientoCajaDTO) this.controlMovimientoCaja.buscarMovimientoCajaDTOId(id);	
						int prueba = Utils.aceptarCancelarAccion(guiMovimientoCaja,"Esta seguro que desea Eliminar el Movimiento de Caja \n"+ mcDTO.getNroRecibo());
						if (prueba == 0){
							this.controlMovimientoCaja.eliminarMovimientoCajaDTO(id);
						}    
					}     
					cargarDatos();
				}
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarMovimientoCaja. Eliminar");
		}
	}

	public GUIGestionarMovimientoCaja getGUI() {
		return guiMovimientoCaja;
	}

	public void keyReleased(KeyEvent e) {
		actualizarTablaCodFecha();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void valueChanged(ListSelectionEvent arg0) { 
	}
	
}




