package cliente.GestionarMovimientoCaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cliente.ClienteConection;
import cliente.ListarFacturasProveedor.MediadorListarFacturasProveedor;

import common.Utils;
import common.DTOs.FacturaProveedorDTO;
import common.DTOs.MovimientoCajaDTO;
import common.GestionarFacturaCliente.IControlFacturaCliente;
import common.GestionarMovimientoCaja.IControlMovimientoCaja;

public class MediadorAltaMovimientoCaja implements ActionListener {
	private GUIAltaModMovimientoCaja guiMovimientoCaja = null;
	private MediadorGestionarMovimientoCaja mgMovimientoCaja;
	private MediadorListarFacturasProveedor medListarFactProv;
	public IControlMovimientoCaja controlMovimientoCaja;
	public IControlFacturaCliente controlFactCte;
	public String tipoFact;
	public FacturaProveedorDTO factura;
	public int codProv=0;
	
	public MediadorAltaMovimientoCaja(MediadorGestionarMovimientoCaja oldMGMovimientoCaja,GUIGestionarMovimientoCaja guiPadre) throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorAltaMovimientoCaja. Constructor");
		}
		controlMovimientoCaja = clienteConexion.getIControlMovimientoCaja();
		Long nro= this.controlMovimientoCaja.obtenerNroMovCaja();
		guiMovimientoCaja = new GUIAltaModMovimientoCaja(guiPadre,nro);
		guiMovimientoCaja.setActionListeners(this);
		mgMovimientoCaja = oldMGMovimientoCaja;
		Utils.show(guiMovimientoCaja);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiMovimientoCaja.getJBAceptar()) {
			String codigo = guiMovimientoCaja.getJTFCodigo().getText();
			String tipoMov = guiMovimientoCaja.getJCTipoMov().getSelectedItem().toString();
			java.util.Date fu= guiMovimientoCaja.getJDateChooserFecha().getDate();
			String importe = guiMovimientoCaja.getJTFImporte().getText();
			String desc = guiMovimientoCaja.getJTFDescr().getText();
			String conFact = guiMovimientoCaja.getJCConFact().getSelectedItem().toString();
			String nroFact = guiMovimientoCaja.getJTFFactura().getText();
			String tipoCajaReg = guiMovimientoCaja.getJCTipoCajaRegistrado().getSelectedItem().toString();
			try{
				if (codigo.length()==0){
					Utils.advertenciaUsr(guiMovimientoCaja,"Por favor ingrese el código del movimiento de caja.");
				}else if (this.controlMovimientoCaja.existeMovimientoCajaNroRecibo(new Long(codigo))){
					Utils.advertenciaUsr(guiMovimientoCaja,"El movimiento de caja con ese código ya existe.");   
				}else if (tipoMov.length()==0 || importe.length()==0  || desc.length()==0 || conFact.length()==0){
					Utils.advertenciaUsr(guiMovimientoCaja,"Alguno de los campos obligatorios esta vacio.");
				}else if(!Utils.esDouble(importe)){
					Utils.advertenciaUsr(guiMovimientoCaja,"El importe ingresado es incorrecto.");	
				}else if((conFact.compareTo("SI")==0) && (nroFact.length()==0)  ){
					Utils.advertenciaUsr(guiMovimientoCaja,"Debe cargar cargar los datos de la factura.");
				}else {
					MovimientoCajaDTO mimovDTO = new MovimientoCajaDTO();
					mimovDTO.setNroRecibo(new Long(codigo));
					mimovDTO.setFecha(Utils.crearFecha(fu));
					mimovDTO.setImporte(Double.parseDouble(importe));
					mimovDTO.setDescripcion(desc);
					mimovDTO.setTipoCajaRegistrado(tipoCajaReg);
					if(conFact.compareTo("SI")==0){
						mimovDTO.setConFactura(true);
						mimovDTO.setFactura(factura);
					}else{
						mimovDTO.setConFactura(false);
					}
					if(tipoMov.compareTo("ENTRADA")==0)
						mimovDTO.setTipoMovimiento(1);
					else
						mimovDTO.setTipoMovimiento(0);
					this.controlMovimientoCaja.agregarMovimientoCajaDTO(mimovDTO);
					guiMovimientoCaja.dispose();
					mgMovimientoCaja.cargarDatos();
				}
			} catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorAltaMovimientoCaja. ActionPerformed");
			}
		}else if ((source == guiMovimientoCaja.getJBBuscarFact())) {
			buscarFacturaProveedor();        
		} else if (source == guiMovimientoCaja.getJBCancelar()) {
			guiMovimientoCaja.dispose();
		}else{
			if(guiMovimientoCaja.getJCConFact()==source){
				if(guiMovimientoCaja.getJCConFact().getSelectedItem().toString().compareTo("SI")==0){
					guiMovimientoCaja.getJBBuscarFact().setEnabled(true);
					guiMovimientoCaja.getJCTipoMov().setSelectedItem("SALIDA");
				}else{
					guiMovimientoCaja.getJBBuscarFact().setEnabled(false);
					guiMovimientoCaja.setFactura("");
				}
			}
		}
	}

	private void buscarFacturaProveedor() {
		java.util.Date hoy = new java.util.Date();
		int mesL=Utils.getMes(hoy);
		int anioL=Utils.getAnio(hoy);
		if (medListarFactProv== null) {
			medListarFactProv= new MediadorListarFacturasProveedor(mesL,anioL,this,guiMovimientoCaja);
		} else {
			if (!medListarFactProv.getGUI().isVisible()) {
				medListarFactProv.getGUI().setVisible(true);
			}
		}
		if (factura != null){
			this.guiMovimientoCaja.getJTFFactura().setText(Utils.nroFact(factura.getNroFactura()));
			this.cargarFactura(factura);
		}
	}

	public void actualizarFactura() {
		guiMovimientoCaja.setFactura(Utils.nroFact(factura.getNroFactura()));
	}

	private void cargarFactura(FacturaProveedorDTO fact) {
		this.factura = fact;
	}
	
}

