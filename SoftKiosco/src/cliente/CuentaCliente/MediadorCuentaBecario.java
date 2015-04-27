package cliente.CuentaCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.ClienteConection;
import cliente.GestionarUsuario.GUIGestionarUsuario;
import cliente.GestionarUsuario.MediadorGestionarUsuario;
import cliente.Principal.GUIReport;

import common.Utils;
import common.DTOs.BecaAsignadaDTO;
import common.DTOs.FacturaClienteDTO;
import common.DTOs.KioscoDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarBecaAsignada.IControlBecaAsignada;
import common.GestionarKiosco.IControlKiosco;
import common.GestionarUsuario.IControlUsuario;

public class MediadorCuentaBecario implements ActionListener,ListSelectionListener {
	public IControlUsuario controlUsuario;
	private IControlKiosco controlKiosco;
	private IControlBecaAsignada controlBeca;
	private GUICuentaBecario guiCuentaBecario=null;
	public UsuarioDTO cliente=null;
	Vector detalleIt= new Vector();
	Vector fecha = new Vector();
	Vector debe= new Vector();
	Vector haber= new Vector();
	Vector saldo= new Vector();
	Vector detalleItImpr= new Vector();
	Vector fechaImpr = new Vector();
	Vector debeImpr= new Vector();
	Vector haberImpr= new Vector();
	Vector saldoImpr= new Vector();
	double saldoI=0;

	public MediadorCuentaBecario(MediadorGestionarUsuario mgc, UsuarioDTO cte,GUIGestionarUsuario guiPadre) throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorCuentaBecario. Constructor");
		}
		controlUsuario = clienteConexion.getIControlUsuario();
		controlKiosco = clienteConexion.getIControlKiosco();
		controlBeca = clienteConexion.getIControlBecaAsignada();
		cliente = cte;
		organizarDatosMostrar();
		guiCuentaBecario = new GUICuentaBecario(detalleIt,fecha,debe,haber,saldo,cliente.getNombre(),guiPadre);
		guiCuentaBecario.setActionListeners(this);
		guiCuentaBecario.setListSelectionListener(this);
		Utils.show(guiCuentaBecario);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiCuentaBecario.getJBImprimir()) {
			try{
				if(cargarFilasSeleccionadas()){
					String titulo="Estado de Cuenta del Cliente:"+cliente.getNombre();
					KioscoDTO kiosco=controlKiosco.obtenerKiosco();
					new GUIReport(guiCuentaBecario,13,kiosco,titulo,detalleItImpr, fechaImpr,debeImpr,haberImpr,saldoImpr);
				}
			} catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorCuentaBecario. ActionPerformed");
			}
		}else if (source == guiCuentaBecario.getJCheckSelectAll() ) {
			if(guiCuentaBecario.getJCheckSelectAll().isSelected()){
				guiCuentaBecario.jtDatos.selectAll();
			}else{
				guiCuentaBecario.jtDatos.clearSelection();
			}	
		}else if (source == guiCuentaBecario.getJBSalir()) {
			guiCuentaBecario.dispose();
		}
	}

	public boolean cargarFilasSeleccionadas() {
		boolean result=false;
		try{
			if (guiCuentaBecario.jtDatos.getSelectedRow() == -1) {
				Utils.advertenciaUsr(guiCuentaBecario,"Debe seleccionar uno o más datos.");
				result=false;
			}else{
				result=true;
				detalleItImpr= new Vector();
				fechaImpr = new Vector();
				debeImpr= new Vector();
				haberImpr= new Vector();
				saldoImpr= new Vector();
				int primerMov=guiCuentaBecario.jtDatos.getSelectedRow();
				int cantMovs = guiCuentaBecario.jtDatos.getSelectedRowCount();
				for(int i=primerMov;i<(primerMov+cantMovs);i++){
					detalleItImpr.add(detalleIt.elementAt(i));
					fechaImpr.add(fecha.elementAt(i));
					debeImpr.add(debe.elementAt(i));
					haberImpr.add(haber.elementAt(i));
					saldoImpr.add(saldo.elementAt(i));
				}
			}
		} catch (Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorCuentaBecario. CargarFilasSeleccionadas");
		}
		return result;
	}

	public void organizarDatosMostrar() throws Exception{
		Vector becasAsig = controlBeca.obtenerBecasAsignadasDeUsuario(cliente.getId());
		Vector todasFacturasCte = controlUsuario.obtenerFacturasDeUsuario(cliente.getId());
		for(int i=0;i<becasAsig.size();i++){
			BecaAsignadaDTO baDTO = (BecaAsignadaDTO)becasAsig.elementAt(i);
			Timestamp fechaPost = Utils.crearFecha(new Date());
			if(i!=(becasAsig.size()-1)){
				BecaAsignadaDTO baPostDTO = (BecaAsignadaDTO)becasAsig.elementAt(i+1);
				fechaPost = baPostDTO.getFecha();
			}
			Vector facturasBeca=this.facturasDeBeca(todasFacturasCte, baDTO.getFecha(), fechaPost);
			detalleIt.add("Asignación de beca");
			fecha.add(Utils.getStrUtilDate(baDTO.getFecha()));
			saldoI =baDTO.getImporteAsignado();
			debe.add(" ");
			haber.add(Utils.ordenarDosDecimales(saldoI));
			saldo.add(Utils.ordenarDosDecimales(saldoI));
			for(int j=0;j<facturasBeca.size();j++){
				FacturaClienteDTO fc = (FacturaClienteDTO) facturasBeca.elementAt(j);
				detalleIt.add("     Factura Nro:"+Utils.nroFact(fc.getNroFactura()));
				fecha.add(Utils.getStrUtilDate(fc.getFechaHora()));
				saldoI =Utils.redondear(saldoI-fc.getImporteTotal(),2);
				debe.add(Utils.ordenarDosDecimales(fc.getImporteTotal()));
				haber.add(" ");
				saldo.add(Utils.ordenarDosDecimales(saldoI));
			}
			detalleIt.add("IMPORTE RESTANTE FINAL");
			fecha.add(" ");
			debe.add(" ");
			haber.add(" ");
			saldo.add(Utils.ordenarDosDecimales(baDTO.getImporteRestante()));
			detalleIt.add(" - ");
			fecha.add(" ");
			debe.add(" ");
			haber.add(" ");
			saldo.add(" ");
		}
	}

	public Vector facturasDeBeca(Vector todasFacturasCte,Timestamp fechaBeca, Timestamp fechaPosteriorBeca){
		Vector facturasObtenidas = new Vector();
		for(int i=0; i < todasFacturasCte.size(); i++ ){
			FacturaClienteDTO fact = (FacturaClienteDTO)todasFacturasCte.elementAt(i);
			if(fact.getFechaHora().after(fechaBeca) && fact.getFechaHora().before(fechaPosteriorBeca) ){
				facturasObtenidas.add(fact);
			}
		}
		return facturasObtenidas;
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
	}

}
