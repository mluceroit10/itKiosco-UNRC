package cliente.ListarFacturasCliente;

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
import cliente.Principal.GUIReport;

import common.Utils;
import common.DTOs.FacturaClienteDTO;
import common.DTOs.ItemFacturaDTO;
import common.DTOs.KioscoDTO;
import common.GestionarFacturaCliente.IControlFacturaCliente;
import common.GestionarKiosco.IControlKiosco;

public class MediadorListarFacturasCliente implements ActionListener, KeyListener, ListSelectionListener {
	private GUIListarFacturasCliente guiTodasFactCte = null;
	protected IControlFacturaCliente controlFactCte;
	private FacturaClienteDTO miFactCte;
	private IControlKiosco controlKiosco;
    private int mesLI;
	private int anioLI;
	
	public MediadorListarFacturasCliente(int mes, int anio,JFrame guiPadre) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			mesLI=mes;
    		anioLI=anio;
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorListarFacturasCliente. Constructor");
		}
		controlFactCte = clienteConexion.getIControlFacturaCliente();
		controlKiosco = clienteConexion.getIControlKiosco();
		this.guiTodasFactCte = new GUIListarFacturasCliente(mes,anio,guiPadre);
		this.guiTodasFactCte.setActionListeners(this);
		cargarDatos();
		this.guiTodasFactCte.setListSelectionListener(this);
		this.guiTodasFactCte.setKeyListener(this);
		Utils.show(guiTodasFactCte);
	}

	public boolean cargarFilaSeleccionada() {
		boolean result=false;
		try{
			if (guiTodasFactCte.jtDatos.getSelectedRow() == -1) {
				Utils.advertenciaUsr(guiTodasFactCte,"Debe seleccionar una factura.");
				result = false;
			}else{
				Long id = (Long)guiTodasFactCte.datos[guiTodasFactCte.jtDatos.getSelectedRow()][0];
				miFactCte =this.controlFactCte.buscarFacturaClienteDTOId(id);
				result = true;
			}
		} catch (Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorListarFacturasCliente. CargarFilaSeleccionada");
		}
		return result;
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiTodasFactCte.getJBImprimir()) {
			try {
				if (guiTodasFactCte.jtDatos.getSelectedRow() == -1){
					Utils.advertenciaUsr(guiTodasFactCte,"Para poder imprimir una factura debe ser previamente seleccionada.");
				}else{
					Long id = (Long)guiTodasFactCte.datos[guiTodasFactCte.jtDatos.getSelectedRow()][0];
					miFactCte =this.controlFactCte.buscarFacturaClienteDTOId(id);
					Vector productos=new Vector();
					Vector cantProd=new Vector();
					Vector prUnit=new Vector();
					Vector prTotal=new Vector();
					Vector items=miFactCte.getItems();
					for(int i=0;i<items.size();i++){
						ItemFacturaDTO pr= (ItemFacturaDTO) items.elementAt(i);
						productos.add(pr.getProducto());
						cantProd.add(String.valueOf(pr.getCantidad()));
						prUnit.add(Utils.ordenarDosDecimales(pr.getPrUnit()));
						prTotal.add(Utils.ordenarDosDecimales(pr.getPrTotal()));
					}	
					KioscoDTO kioscoDTO=controlKiosco.obtenerKiosco();
					new GUIReport(guiTodasFactCte, 11, productos,cantProd,prUnit,prTotal, Utils.nroFact(miFactCte.getNroFactura()),miFactCte.getFechaHora(),
							kioscoDTO, miFactCte.getBecario(),miFactCte.getImporteTotal(),miFactCte.isACargoBecario());
				}
			}
			catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorListarFacturasCliente. ActionPerformed");
			}
		}else if (source == guiTodasFactCte.getJBSalir()){
			this.guiTodasFactCte.dispose();
		}else if (source == guiTodasFactCte.getJBCambiarPeriodo()){
        	String anioB = guiTodasFactCte.getJTFAnio().getText();
        	if(anioB.length()==0){
				Utils.advertenciaUsr(guiTodasFactCte,"Por favor ingrese el Año.");
			}else if(anioB.length()!=4){
				Utils.advertenciaUsr(guiTodasFactCte,"El año debe ser un número de 4 dígitos.");
			}else{
				anioLI= Integer.parseInt(anioB);
				mesLI = guiTodasFactCte.getJCBMes().getSelectedIndex()+1; //para que el numero del indice de con el mes sumo 1
	         	cargarDatos();
			}
		}else { 
			guiTodasFactCte.dispose();
		}
	}

	public void cargarDatos() {
		try {
			Vector facturas = this.controlFactCte.obtenerFacturasCliente(mesLI,anioLI);
			guiTodasFactCte.datos = new Object[facturas.size()][guiTodasFactCte.titulos.length];
			guiTodasFactCte.getJTFPeriodo().setText(mesLI+" - "+anioLI);
			int i = 0;
			for (int j = 0; j < facturas.size(); j++) {
				FacturaClienteDTO fc=(FacturaClienteDTO)facturas.elementAt(j);
				String aCuenta="No";
				String usuarioCta="";
				if(fc.isACargoBecario()){
					aCuenta="Si";
					usuarioCta=fc.getBecario().getApellido()+" "+fc.getBecario().getNombre();
				}
				Object[] temp = {fc.getId(),common.Utils.getStrUtilDate(fc.getFechaHora())+" "+Utils.getHoraUtilDate(fc.getFechaHora()),Utils.nroFact(fc.getNroFactura()),aCuenta,usuarioCta,Utils.ordenarDosDecimales(fc.getImporteTotal())};
				guiTodasFactCte.datos[i] = temp;
				i++;
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorListarFacturasCliente. CargarDatos");
		}
		guiTodasFactCte.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiTodasFactCte.actualizarTabla();
	}

	public void actualizarCampos() {
		try {
			Vector facturas = this.controlFactCte.obtenerFacturasClienteFechaNroyVendedor(mesLI,anioLI,guiTodasFactCte.getJTFFecha().getText(), guiTodasFactCte.getJTFNro().getText(), guiTodasFactCte.getJTFVendedor().getText());
			guiTodasFactCte.datos = new Object[facturas.size()][guiTodasFactCte.titulos.length];
			for (int j = 0; j < facturas.size(); j++) {
				FacturaClienteDTO fc=(FacturaClienteDTO)facturas.elementAt(j);
				String aCuenta="No";
				String usuarioCta="";
				if(fc.isACargoBecario()){
					aCuenta="Si";
					usuarioCta=fc.getBecario().getApellido()+" "+fc.getBecario().getNombre();
				}
				Object[] temp = {fc.getId(),common.Utils.getStrUtilDate(fc.getFechaHora())+" "+Utils.getHoraUtilDate(fc.getFechaHora()),Utils.nroFact(fc.getNroFactura()),aCuenta,usuarioCta,Utils.ordenarDosDecimales(fc.getImporteTotal())};
				guiTodasFactCte.datos[j] = temp;
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorListarFacturasCliente. ActualizarFecha");
		}
		guiTodasFactCte.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiTodasFactCte.actualizarTabla();
	}

	public GUIListarFacturasCliente getGUI() {
		return guiTodasFactCte;
	}

	public void keyReleased(KeyEvent e) {
		actualizarCampos();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void valueChanged(ListSelectionEvent arg0) { 
	}

}




