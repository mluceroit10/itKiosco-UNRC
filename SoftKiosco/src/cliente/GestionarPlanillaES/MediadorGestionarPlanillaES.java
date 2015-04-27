package cliente.GestionarPlanillaES;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.ClienteConection;
import cliente.Principal.GUIReport;

import common.Utils;
import common.DTOs.FacturaClienteDTO;
import common.DTOs.MovimientoCajaDTO;
import common.DTOs.PlanillaESDTO;
import common.DTOs.UsuarioDTO;
import common.DTOs.UsuarioPlanillaESDTO;
import common.GestionarMovimientoCaja.IControlMovimientoCaja;
import common.GestionarSesion.IControlSesion;
import common.RealizarPlanillaES.IControlRealizarPlanillaES;
import common.constantes.TIPO_CAJA;

public class MediadorGestionarPlanillaES implements ActionListener, KeyListener, ListSelectionListener {
	private GUIGestionarPlanillaES guiImprimirPlanillaES = null;
	protected IControlRealizarPlanillaES controlRealizarPlanillaES;
	protected IControlMovimientoCaja controlMovimientoCaja;
	protected IControlSesion controlSesion;
	private PlanillaESDTO miPESDto;
	private int codRep=12;
    private int mesLI;
	private int anioLI;

	public MediadorGestionarPlanillaES(int mes, int anio,JFrame guiPadre,boolean esAdmin) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			mesLI=mes;
    		anioLI=anio;
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarPlanillaES. Constructor");
		}
		if(!esAdmin) codRep=13;
		controlMovimientoCaja = clienteConexion.getIControlMovimientoCaja();
		controlRealizarPlanillaES = clienteConexion.getIControlRealizarPlanillaES();
		controlSesion = clienteConexion.getIControlSesion();
		this.guiImprimirPlanillaES = new GUIGestionarPlanillaES(mes,anio,guiPadre);
		this.guiImprimirPlanillaES.setActionListeners(this);
		cargarDatos();
		this.guiImprimirPlanillaES.setListSelectionListener(this);
		this.guiImprimirPlanillaES.setKeyListener(this);
		Utils.show(guiImprimirPlanillaES);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiImprimirPlanillaES.getJBImprimir()) {
			try {
				if (guiImprimirPlanillaES.jtDatos.getSelectedRow() == -1){
					Utils.advertenciaUsr(guiImprimirPlanillaES,"Para poder imprimir un cierre de caja debe ser previamente seleccionada.");
				}else{
					Long id = (Long)guiImprimirPlanillaES.datos[guiImprimirPlanillaES.jtDatos.getSelectedRow()][0];
					miPESDto = this.controlRealizarPlanillaES.buscarPlanillaESDTOId(id);
					PlanillaESDTO anterior = new PlanillaESDTO();
					if(miPESDto.getNroPlanilla()>(Utils.NROPLANILLAANTERIOR+1))
						anterior=this.controlRealizarPlanillaES.buscarPlanillaESDTONroPlanilla(miPESDto.getNroPlanilla()-1);
					else anterior.setSaldoCajaGeneral(0);
					double egrCD=this.totalEgresosCajaDiaria(miPESDto.getMovimientosCaja());
					double ingCD=this.totalIngresosCajaDiaria(miPESDto.getFacturas(), miPESDto.getMovimientosCaja());
					double ingCG=this.totalIngresosCajaGeneral(miPESDto.getMovimientosCaja());
					double egrCG=this.totalEgresosCajaGeneral(miPESDto.getMovimientosCaja());
					Vector usrTrab=miPESDto.getUsuariosTrab();
					Vector usuarios= new Vector();
					for(int k=0;k<usrTrab.size();k++){
						UsuarioPlanillaESDTO usrT= (UsuarioPlanillaESDTO)usrTrab.elementAt(k);
						UsuarioDTO usr= usrT.getUsuario();
						usuarios.add(usr);
					}
					new GUIReport(guiImprimirPlanillaES,codRep,miPESDto.getNroPlanilla(),miPESDto.getFecha(),usuarios,
							miPESDto.getIngresoCajaDiaria(),ingCD,egrCD,miPESDto.getSaldoCajaDiaria(),
							anterior.getSaldoCajaGeneral(),ingCG,egrCG,miPESDto.getSaldoCajaGeneral(),miPESDto.getRetiro(),miPESDto.getEnCaja());
				}
			}
			catch(Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorGestionarPlanillaES. ActionPerformed");
			}
		}else if (source == guiImprimirPlanillaES.getJBCargar()) {
			String impInic=guiImprimirPlanillaES.getJTFImporteInicial().getText();
			String impRetirado=guiImprimirPlanillaES.getJTFImporteRetiro().getText();
			String impEnCaja=guiImprimirPlanillaES.getJTFImporteEnCaja().getText();
			if(impInic.length()==0){
				Utils.advertenciaUsr(guiImprimirPlanillaES,"Por favor ingrese el importe inicial de la caja diaria.");
			}else if (!Utils.esDouble(impInic)){
				Utils.advertenciaUsr(guiImprimirPlanillaES,"El inporte inicial ingresado es incorrecto.");
			}else if(impRetirado.length()==0){
				Utils.advertenciaUsr(guiImprimirPlanillaES,"Por favor ingrese el importe retirado de la caja diaria.");
			}else if (!Utils.esDouble(impRetirado)){
				Utils.advertenciaUsr(guiImprimirPlanillaES,"El inporte retirado ingresado es incorrecto.");
			}else if(impEnCaja.length()==0){
				Utils.advertenciaUsr(guiImprimirPlanillaES,"Por favor ingrese el importe que queda en caja de la caja diaria.");
			}else if (!Utils.esDouble(impEnCaja)){
				Utils.advertenciaUsr(guiImprimirPlanillaES,"El inporte en caja ingresado es incorrecto.");
			}else{
				double importeInicial = Double.parseDouble(impInic);
				double impRetiradoC = Double.parseDouble(impRetirado);
				double impCaja = Double.parseDouble(impEnCaja);
				java.util.Date fu = guiImprimirPlanillaES.getJDateChooserFecha().getDate();
				Timestamp fecha= Utils.crearFecha(fu);
				try {
					PlanillaESDTO ultima=controlRealizarPlanillaES.obtenerUltimaPlanilla();
					PlanillaESDTO miplDTO = new PlanillaESDTO();
					miplDTO.setNroPlanilla(ultima.getNroPlanilla()+1);
					miplDTO.setFecha(fecha);
					miplDTO.setIngresoCajaDiaria(importeInicial);
					Vector mov=controlRealizarPlanillaES.obtenerMovimientosCajaBalance(fecha);
					Vector fact=controlRealizarPlanillaES.obtenerFacturasClienteBalance(fecha);
					double egrCD=this.totalEgresosCajaDiaria(mov);
					double ingCD=this.totalIngresosCajaDiaria(fact, mov);
					double impCDiaria=generarSaldoCajaDiaria(importeInicial,ingCD,egrCD);
					miplDTO.setSaldoCajaDiaria(impCDiaria);
					double ingCG=this.totalIngresosCajaGeneral(mov);
					double egrCG=this.totalEgresosCajaGeneral(mov);
					int prueba=0;
					if(Utils.redondear(impCDiaria,2)!=Utils.redondear(impRetiradoC+impCaja,2)){
						prueba = Utils.aceptarCancelarAccion(guiImprimirPlanillaES,"No concuerdan los datos ingresados.\n" +
								"Importe obtenido en caja diaria: "+ Utils.ordenarDosDecimales(Utils.redondear(impCDiaria,2))+"\n"+
								"Importe retirado: "+Utils.ordenarDosDecimales(Utils.redondear(impRetiradoC,2))+"\n"+
								"Importe en caja: "+Utils.ordenarDosDecimales(Utils.redondear(impCaja,2))+"\n"+
								"Desea crear el Cierre de Caja de todos modos?"); 
					}	
					if (prueba == 0){
						miplDTO.setSaldoCajaGeneral(generarSaldoCajaGeneral(ultima.getSaldoCajaGeneral(),ingCG,egrCG,impRetiradoC));
						miplDTO.setEnCaja(impCaja);
						miplDTO.setRetiro(impRetiradoC);
						Vector usuarios= controlRealizarPlanillaES.obtenerUsuarioParaPlanillaES(ultima.getFecha());
						for(int k=0;k<usuarios.size();k++){
							UsuarioDTO usr= (UsuarioDTO)usuarios.elementAt(k);
							UsuarioPlanillaESDTO usrT= new UsuarioPlanillaESDTO();
							usrT.setPlanillaES(miplDTO);
							usrT.setUsuario(usr);
						}
						this.controlRealizarPlanillaES.agregarPlanillaESDTOTotal(miplDTO,mov,fact,usuarios);
						Utils.advertenciaUsr(guiImprimirPlanillaES, "Las sesiones activas involucradas en este cierre de caja han sido cerradas");
						new GUIReport(guiImprimirPlanillaES,codRep,ultima.getNroPlanilla()+1,fecha,usuarios,
								importeInicial,ingCD,egrCD,impCDiaria,
								ultima.getSaldoCajaGeneral(),ingCG,egrCG,miplDTO.getSaldoCajaGeneral(),impRetiradoC,impCaja);
						this.cargarDatos();
					}
				}
				catch(Exception ex) {
					Utils.manejoErrores(ex,"Error en MediadorGestionarPlanillaES. ActionPerformed");
				}
			}
		}else if (source == guiImprimirPlanillaES.getJBBorrar()) {
			try{
				if ( this.controlRealizarPlanillaES.obtenerPlanillasES(mesLI,anioLI).isEmpty()){
					Utils.advertenciaUsr(guiImprimirPlanillaES,"No hay planillas guardadas.");
				} else {
					if (guiImprimirPlanillaES.jtDatos.getSelectedRow() == -1){
						Utils.advertenciaUsr(guiImprimirPlanillaES,"Para poder eliminar una planilla debe seleccionarla previamente.");
					} else {
						int sel=guiImprimirPlanillaES.jtDatos.getSelectedRow();
						if(sel==(guiImprimirPlanillaES.datos.length -1)){
							Long id = (Long)guiImprimirPlanillaES.datos[guiImprimirPlanillaES.jtDatos.getSelectedRow()][0];
							miPESDto = this.controlRealizarPlanillaES.buscarPlanillaESDTOId(id);
							int prueba = Utils.aceptarCancelarAccion(guiImprimirPlanillaES,"Esta seguro que desea eliminar la planilla Nro: \n"+ miPESDto.getNroPlanilla());
							if (prueba == 0){
								this.controlRealizarPlanillaES.eliminarPlanillaESDTO(id);
							}
							cargarDatos();
						}else{
							Utils.advertenciaUsr(guiImprimirPlanillaES,"Solo es posible eliminar el último cierre de caja");
						}
					}
				}
			}catch(Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorGestionarPlanillaES. Eliminar");
			}
		} else if (source == guiImprimirPlanillaES.getJBSalir()) {
			guiImprimirPlanillaES.dispose();	
		}else if (source == guiImprimirPlanillaES.getJBCambiarPeriodo()){
        	String anioB = guiImprimirPlanillaES.getJTFAnio().getText();
        	if(anioB.length()==0){
				Utils.advertenciaUsr(guiImprimirPlanillaES,"Por favor ingrese el Año.");
			}else if(anioB.length()!=4){
				Utils.advertenciaUsr(guiImprimirPlanillaES,"El año debe ser un número de 4 dígitos.");
			}else{
				anioLI= Integer.parseInt(anioB);
				mesLI = guiImprimirPlanillaES.getJCBMes().getSelectedIndex()+1; //para que el numero del indice de con el mes sumo 1
	         	cargarDatos();
			}
		} else if ((((Component)e.getSource()).getName().compareTo("combo")) == 0){
			if (((String)(((JComboBox)e.getSource()).getSelectedItem())).compareTo("Fecha")==0) {
				guiImprimirPlanillaES.mostrarJTFFecha();
			}
			if (((String)(((JComboBox)e.getSource()).getSelectedItem())).compareTo("Nro Planilla")==0) {
				guiImprimirPlanillaES.mostrarJTFNro();
			}
		} else { 
			guiImprimirPlanillaES.dispose();
		}
	}

	public void cargarDatos() {
		try {
			Vector planillas = this.controlRealizarPlanillaES.obtenerPlanillasES(mesLI,anioLI);
			guiImprimirPlanillaES.datos = new Object[planillas.size()][guiImprimirPlanillaES.titulos.length];
			guiImprimirPlanillaES.getJTFPeriodo().setText(mesLI+" - "+anioLI);
			int i = 0;
			for (int j = 0; j < planillas.size(); j++) {
				PlanillaESDTO p=(PlanillaESDTO)planillas.elementAt(j);
				Object[] temp = {p.getId(),String.valueOf(p.getNroPlanilla()),common.Utils.getStrUtilDate(p.getFecha())+" "+Utils.getHoraUtilDate(p.getFecha()),Utils.ordenarDosDecimales(p.getEnCaja())};
				guiImprimirPlanillaES.datos[i] = temp;
				i++;
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarPlanillaES. CargarDatos");
		}
		guiImprimirPlanillaES.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiImprimirPlanillaES.actualizarTabla();
	}

	public void actualizarFecha() {
		try {
			Vector planillas = this.controlRealizarPlanillaES.obtenerPlanillasESFecha(mesLI,anioLI,guiImprimirPlanillaES.getJTFFecha().getText());
			guiImprimirPlanillaES.datos = new Object[planillas.size()][guiImprimirPlanillaES.titulos.length];
			for (int j = 0; j < planillas.size(); j++) {
				PlanillaESDTO p=(PlanillaESDTO)planillas.elementAt(j);
				Object[] temp = {p.getId(),String.valueOf(p.getNroPlanilla()),common.Utils.getStrUtilDate(p.getFecha())+" "+Utils.getHoraUtilDate(p.getFecha()),Utils.ordenarDosDecimales(p.getEnCaja())};
				guiImprimirPlanillaES.datos[j] = temp;
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarPlanillaES. ActualizarFecha");
		}
		guiImprimirPlanillaES.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiImprimirPlanillaES.actualizarTabla();
	}

	public void actualizarNroPlanilla() {
		try {
			Vector planillas = this.controlRealizarPlanillaES.obtenerPlanillasESNro(mesLI,anioLI,guiImprimirPlanillaES.getJTFNro().getText());
			guiImprimirPlanillaES.datos = new Object[planillas.size()][guiImprimirPlanillaES.titulos.length];
			for (int j = 0; j < planillas.size(); j++) {
				PlanillaESDTO p=(PlanillaESDTO)planillas.elementAt(j);
				Object[] temp = {p.getId(),String.valueOf(p.getNroPlanilla()),common.Utils.getStrUtilDate(p.getFecha())+" "+Utils.getHoraUtilDate(p.getFecha()),Utils.ordenarDosDecimales(p.getEnCaja())};
				guiImprimirPlanillaES.datos[j] = temp;
			}
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorGestionarPlanillaES. ActualizarNroPlanilla");
		}
		guiImprimirPlanillaES.jtDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiImprimirPlanillaES.actualizarTabla();
	}

	public GUIGestionarPlanillaES getGUI() {
		return guiImprimirPlanillaES;
	}

	private double generarSaldoCajaDiaria(double importeInic, double ingresosCD, double egresosCD){
		double total=importeInic;
		double numero=total + ingresosCD;
		total = Utils.redondear(numero, 2);
		numero=total - egresosCD;
		total = Utils.redondear(numero, 2);
		return total;
	}

	private double totalIngresosCajaDiaria(Vector fact, Vector movs){
		double total=0;
		for(int i=0;i<fact.size();i++){
			FacturaClienteDTO f= (FacturaClienteDTO)fact.elementAt(i);
			double numero=total+f.getImporteTotal();
			total = Utils.redondear(numero, 2);
		}

		for(int j=0;j<movs.size();j++){
			MovimientoCajaDTO m= (MovimientoCajaDTO)movs.elementAt(j);
			if(m.getTipoCajaRegistrado().compareTo(TIPO_CAJA.DIARIA)==0){
				if(m.getTipoMovimiento()==1){ //entrada
					double numero=total+m.getImporte();
				total = Utils.redondear(numero, 2);
				}
			}
		}
		return total;
	}

	private double totalEgresosCajaDiaria(Vector movs){
		double total=0;
		for(int j=0;j<movs.size();j++){
			MovimientoCajaDTO m= (MovimientoCajaDTO)movs.elementAt(j);
			if(m.getTipoCajaRegistrado().compareTo(TIPO_CAJA.DIARIA)==0){
				if(m.getTipoMovimiento()!=1){ //salida
					double numero=total+m.getImporte();
					total = Utils.redondear(numero, 2);
				}
			}
		}
		return total;
	}

	private double generarSaldoCajaGeneral(double saldoCajaGeneral,double ingresosCG, double egresosCG,double ingresoCajadiaria){ //double impInic,double impCDiaria){
		double total=saldoCajaGeneral;
		//double ingresoCajadiaria=Utils.redondear(impCDiaria-impInic,2);
		total=Utils.redondear(total+ingresoCajadiaria,2);
		double numero=total + ingresosCG;
		total = Utils.redondear(numero, 2);
		numero = total - egresosCG;
		total = Utils.redondear(numero, 2);

		return total;
	}

	private double totalIngresosCajaGeneral(Vector movs){
		double total=0;
		for(int i=0;i<movs.size();i++){
			MovimientoCajaDTO m= (MovimientoCajaDTO)movs.elementAt(i);
			if(m.getTipoCajaRegistrado().compareTo(TIPO_CAJA.GENERAL)==0){
				if(m.getTipoMovimiento()==1){ //entrada
					double numero=total+m.getImporte();
					total = Utils.redondear(numero, 2);
				}	
			}
		}
		return total;
	}

	private double totalEgresosCajaGeneral(Vector movs){
		double total=0;
		for(int i=0;i<movs.size();i++){
			MovimientoCajaDTO m= (MovimientoCajaDTO)movs.elementAt(i);
			if(m.getTipoCajaRegistrado().compareTo(TIPO_CAJA.GENERAL)==0){
				if(m.getTipoMovimiento()!=1){ //salida
					double numero=total+m.getImporte();
					total = Utils.redondear(numero, 2);
				}	
			}
		}
		return total;
	}

	public void keyReleased(KeyEvent e) {
		Object source = e.getSource();
		if (source == this.guiImprimirPlanillaES.getJTFNro()) {
			actualizarNroPlanilla();
		}else
			actualizarFecha();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void valueChanged(ListSelectionEvent arg0) { 
	}
	
}




