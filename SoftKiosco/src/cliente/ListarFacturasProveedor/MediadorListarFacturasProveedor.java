package cliente.ListarFacturasProveedor;

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
import cliente.GestionarMovimientoCaja.GUIAltaModMovimientoCaja;
import cliente.GestionarMovimientoCaja.MediadorAltaMovimientoCaja;
import cliente.Principal.GUIReport;

import common.Utils;
import common.DTOs.FacturaProveedorDTO;
import common.DTOs.ItemFacturaDTO;
import common.DTOs.KioscoDTO;
import common.DTOs.MovimientoCajaDTO;
import common.GestionarFacturaProveedor.IControlFacturaProveedor;
import common.GestionarKiosco.IControlKiosco;
import common.GestionarMovimientoCaja.IControlMovimientoCaja;

public class MediadorListarFacturasProveedor implements ActionListener, KeyListener, ListSelectionListener {
	private GUIListarFacturasProveedor guiTodasFactProv = null;
	private IControlFacturaProveedor controlFactProv;
	private FacturaProveedorDTO miFactProv;
	private IControlKiosco controlKiosco;
	private IControlMovimientoCaja controlMC;
	private boolean flag=false;
	private MediadorAltaMovimientoCaja medAltaMovCaja;
    private int mesLI;
	private int anioLI;
	
	public MediadorListarFacturasProveedor(int mes, int anio,JFrame guiPadre) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			mesLI=mes;
    		anioLI=anio;
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorListarFacturasProveedor. Constructor");
		}
		flag=true;
		controlFactProv = clienteConexion.getIControlFacturaProveedor();
		controlKiosco = clienteConexion.getIControlKiosco();
		controlMC = clienteConexion.getIControlMovimientoCaja();
		this.guiTodasFactProv = new GUIListarFacturasProveedor(mes,anio,guiPadre);
		this.guiTodasFactProv.setActionListeners(this);
		cargarDatos();
		this.guiTodasFactProv.setListSelectionListener(this);
		this.guiTodasFactProv.setKeyListener(this);
		Utils.show(guiTodasFactProv);
	}

	public MediadorListarFacturasProveedor(int mes, int anio,MediadorAltaMovimientoCaja medAMC,GUIAltaModMovimientoCaja guiPadre) {
		this.medAltaMovCaja = medAMC;
		ClienteConection clienteConexion = new ClienteConection();
		try{
			mesLI=mes;
    		anioLI=anio;
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorListarFacturasProveedor. Constructor");
		}
		controlFactProv = clienteConexion.getIControlFacturaProveedor();
		controlKiosco = clienteConexion.getIControlKiosco();
		controlMC = clienteConexion.getIControlMovimientoCaja();
		this.guiTodasFactProv = new GUIListarFacturasProveedor(mes,anio,guiPadre);
		this.guiTodasFactProv.setActionListeners(this);
		cargarDatos();
		this.guiTodasFactProv.setListSelectionListener(this);
		this.guiTodasFactProv.setKeyListener(this);
		this.guiTodasFactProv.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiTodasFactProv.getJBImprimir()) {
			try {
				if (guiTodasFactProv.jtDatos.getSelectedRow() == -1){
					Utils.advertenciaUsr(guiTodasFactProv,"Para poder imprimir una factura debe ser previamente seleccionada.");
				}else{
					Long id = (Long)guiTodasFactProv.datos[guiTodasFactProv.jtDatos.getSelectedRow()][0];
					miFactProv =this.controlFactProv.buscarFacturaProveedorDTOId(id);
					Vector productos=new Vector();
					Vector cantProd=new Vector();
					Vector prUnit=new Vector();
					Vector prTotal=new Vector();
					Vector items=miFactProv.getItems();
					for(int i=0;i<items.size();i++){
						ItemFacturaDTO pr= (ItemFacturaDTO) items.elementAt(i);
						productos.add(pr.getProducto());
						cantProd.add(String.valueOf(pr.getCantidad()));
						prUnit.add(Utils.ordenarDosDecimales(pr.getPrUnit()));
						prTotal.add(Utils.ordenarDosDecimales(pr.getPrTotal()));
					}	
					KioscoDTO kioscoDTO=controlKiosco.obtenerKiosco();
					new GUIReport(guiTodasFactProv, 10, productos,cantProd,prUnit,prTotal, Utils.nroFact(miFactProv.getNroFactura()),miFactProv.getFechaHora(),
							kioscoDTO, miFactProv.getProveedor(),miFactProv.getImporteTotal());
				}
			}
			catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorListarFacturasProveedor. ActionPerformed");
			}
		}else if (source == guiTodasFactProv.getJBSalir()){
			if(flag){
				this.guiTodasFactProv.dispose();
			}else{
				if (cargarFilaSeleccionada()) {
					if (medAltaMovCaja != null) {
						medAltaMovCaja.factura = miFactProv;
						medAltaMovCaja.actualizarFactura();
						this.guiTodasFactProv.dispose();
					}
				}
			}		
		}else if (source == guiTodasFactProv.getJBCambiarPeriodo()){
        	String anioB = guiTodasFactProv.getJTFAnio().getText();
        	if(anioB.length()==0){
				Utils.advertenciaUsr(guiTodasFactProv,"Por favor ingrese el Año.");
			}else if(anioB.length()!=4){
				Utils.advertenciaUsr(guiTodasFactProv,"El año debe ser un número de 4 dígitos.");
			}else{
				anioLI= Integer.parseInt(anioB);
				mesLI = guiTodasFactProv.getJCBMes().getSelectedIndex()+1; //para que el numero del indice de con el mes sumo 1
	         	cargarDatos();
			}
		}else { 
			guiTodasFactProv.dispose();
		}
	}

	public boolean cargarFilaSeleccionada() {
		boolean result=false;
		try{
			if (guiTodasFactProv.jtDatos.getSelectedRow() == -1) {
				Utils.advertenciaUsr(guiTodasFactProv,"Debe seleccionar una factura.");
				result = false;
			}else{
				Long id = (Long)guiTodasFactProv.datos[guiTodasFactProv.jtDatos.getSelectedRow()][0];
				miFactProv =this.controlFactProv.buscarFacturaProveedorDTOId(id);
				result = true;
			}
		} catch (Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorListarFacturasProveedor. CargarFilaSeleccionada");
		}
		return result;
	}

	public void cargarDatos() {
		try {
			Vector facturas = this.controlFactProv.obtenerFacturaProveedores(mesLI,anioLI);
			guiTodasFactProv.datos = new Object[facturas.size()][guiTodasFactProv.titulos.length];
			guiTodasFactProv.getJTFPeriodo().setText(mesLI+" - "+anioLI);
			int i = 0;
			for (int j = 0; j < facturas.size(); j++) {
				FacturaProveedorDTO p=(FacturaProveedorDTO)facturas.elementAt(j);
				String pago = "No";
				String compr = "";
				Vector movsPago = controlMC.obtenerMovimientosCajaPagoProveedor(p.getId());
				if(movsPago.size()>0){
					pago = "Si";
					for(int k=0; k<movsPago.size(); k++){
						MovimientoCajaDTO mc = (MovimientoCajaDTO) movsPago.elementAt(k);
						compr +=mc.getNroRecibo()+"-";
					}
					if(compr.length()>1)
						compr=compr.substring(0,compr.length()-1);
				}
				Object[] temp = {p.getId(),common.Utils.getStrUtilDate(p.getFechaHora())+" "+Utils.getHoraUtilDate(p.getFechaHora()),Utils.nroFact(p.getNroFactura()),p.getProveedor().getNombre(),Utils.ordenarDosDecimales(p.getImporteTotal()),pago,compr};
				guiTodasFactProv.datos[i] = temp;
				i++;
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorListarFacturasProveedor. CargarDatos");
		}
		guiTodasFactProv.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiTodasFactProv.actualizarTabla();
	}

	public void actualizarCampos() {
		try {
			Vector facturas = this.controlFactProv.obtenerFacturaProveedoresFechaNroyProveedor(mesLI,anioLI,guiTodasFactProv.getJTFFecha().getText(),guiTodasFactProv.getJTFNro().getText(),guiTodasFactProv.getJTFProveedor().getText());
			guiTodasFactProv.datos = new Object[facturas.size()][guiTodasFactProv.titulos.length];
			for (int j = 0; j < facturas.size(); j++) {
				FacturaProveedorDTO p=(FacturaProveedorDTO)facturas.elementAt(j);
				String pago = "No";
				String compr = "";
				Vector movsPago = controlMC.obtenerMovimientosCajaPagoProveedor(p.getId());
				if(movsPago.size()>0){
					pago = "Si";
					for(int k=0; k<movsPago.size(); k++){
						MovimientoCajaDTO mc = (MovimientoCajaDTO) movsPago.elementAt(k);
						compr +=mc.getNroRecibo()+"-";
					}
					if(compr.length()>1)
						compr=compr.substring(0,compr.length()-1);
				}
				Object[] temp = {p.getId(),common.Utils.getStrUtilDate(p.getFechaHora())+" "+Utils.getHoraUtilDate(p.getFechaHora()),Utils.nroFact(p.getNroFactura()),p.getProveedor().getNombre(),Utils.ordenarDosDecimales(p.getImporteTotal()),pago,compr};
				guiTodasFactProv.datos[j] = temp;
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorListarFacturasProveedor. ActualizarFecha");
		}
		guiTodasFactProv.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiTodasFactProv.actualizarTabla();
	}

	public GUIListarFacturasProveedor getGUI() {
		return guiTodasFactProv;
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




