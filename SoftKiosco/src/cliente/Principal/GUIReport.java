package cliente.Principal;

import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.JDialog;

import reports.JasperReports;

import common.Utils;
import common.DTOs.KioscoDTO;
import common.DTOs.ProveedorDTO;
import common.DTOs.UsuarioDTO;

import dori.jasper.engine.JRException;
import dori.jasper.engine.JasperPrint;
import dori.jasper.view.JRViewer;

public class GUIReport extends JDialog{
	private static final long serialVersionUID = 1L;
	
	public GUIReport(JDialog parent,int codRep, Vector datos, String titulo,String dato1) throws Exception{
		super(parent,true);
		this.setSize(new java.awt.Dimension(700,570));
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
		this.setTitle("Vista Previa de Impresión");
		JasperPrint report=null;
		String mensaje="No existen productos para efectuar la impresión";
		if(codRep==1)
			report = JasperReports.listarTodosProductosBajoStock(datos,titulo);
		else if(codRep==2)
			report = JasperReports.listarProductosProveedorBajoStock(datos,titulo);
		else if(codRep==3)
			report = JasperReports.listarTodosProductosDisponibles(datos,titulo);
		else if(codRep==4)
			report = JasperReports.listarTodosProductosDisponiblesPrecio(datos,titulo,dato1);
		else if(codRep==5)
			report = JasperReports.listarProductosProveedorDisponibles(datos,titulo);
		else if(codRep==6)
			report = JasperReports.listarProductosProveedorDisponiblesPrecio(datos,titulo,dato1);
		if(datos.size()>0){
			JRViewer jrv=null;
			try {
				jrv = new JRViewer(report);
			} catch (JRException ex) {
				Utils.manejoErrores(ex,"Error en GUIReport. Reportes 1 2 3 4 5 6");
			}
			this.getContentPane().add(jrv);
			Utils.show(this);
		}else{
			Utils.advertenciaUsr(parent,mensaje);
		}
	}

	public GUIReport(JDialog parent,int codRep,Vector datos,String titulo) throws Exception{
		super(parent,true);
		this.setSize(new java.awt.Dimension(700,570));
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
		this.setTitle("Vista Previa de Impresión");
		JasperPrint report=null;
		String mensaje="";
		if(codRep==7){
			mensaje="No existen proveedores para efectuar la impresión";
			report = JasperReports.listarTodosProveedores(datos, titulo);
		}
		if(codRep==8){
			mensaje="No existen usuarios para efectuar la impresión";
			report = JasperReports.listarTodosUsuarios(datos, titulo);
		}	
		if(datos.size()>0){
			JRViewer jrv=null;
			try {
				jrv = new JRViewer(report);
			} catch (JRException ex) {
				Utils.manejoErrores(ex,"Error en GUIReport. Reportes 7 8");
			}
			this.getContentPane().add(jrv);
			Utils.show(this);
		}else{
			Utils.advertenciaUsr(parent,mensaje);
		}
	}

	public GUIReport(JDialog parent,int codRep,String nombre,String encargado,String tel,String direccion,String nLoc) throws Exception{
		super(parent,true);
		this.setSize(new java.awt.Dimension(700,570));
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
		this.setTitle("Vista Previa de Impresión");
		JasperPrint report=null;
		if(codRep==9){
			report = JasperReports.tarjetaKiosco(nombre, encargado, tel, direccion, nLoc);
			JRViewer jrv=null;
			try {
				jrv = new JRViewer(report);
			} catch (JRException ex) {
				Utils.manejoErrores(ex,"Error en GUIReport. Reportes 9");
			}
			this.getContentPane().add(jrv);
			Utils.show(this);
		}	
	}

	public GUIReport(JDialog parent,int codRep,Vector productos, Vector cantProd, Vector prUnit, Vector prTotal,
			String nroFact, Timestamp fechaHora, KioscoDTO kioscoDTO,ProveedorDTO proveedor, double importeTotal) throws Exception{
		super(parent,true);
		this.setSize(new java.awt.Dimension(700,570));
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
		this.setTitle("Vista Previa de Impresión");
		JasperPrint report=null;
		if(codRep==10){
			report = JasperReports.facturarProveedor(productos,cantProd,prUnit,prTotal, nroFact,fechaHora,kioscoDTO,proveedor,importeTotal);
			JRViewer jrv=null;
			try {
				jrv = new JRViewer(report);
			} catch (JRException ex) {
				Utils.manejoErrores(ex,"Error en GUIReport. Reportes 10");
			}
			this.getContentPane().add(jrv);
			Utils.show(this);
		}	
	}

	public GUIReport(JDialog parent,int codRep,Vector productos, Vector cantProd, Vector prUnit,Vector prTotal,
			String nroFact, Timestamp fechaHora, KioscoDTO kioscoDTO, UsuarioDTO aCuentaDe, double importeTotal,boolean aCargoBecario) {
		super(parent,true);
		this.setSize(new java.awt.Dimension(700,570));
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
		this.setTitle("Vista Previa de Impresión");
		JasperPrint report=null;
		if(codRep==11){
			report = JasperReports.facturarCliente(productos,cantProd,prUnit,prTotal, nroFact,fechaHora,kioscoDTO,aCuentaDe,importeTotal,aCargoBecario);
			JRViewer jrv=null;
			try {
				jrv = new JRViewer(report);
			} catch (JRException ex) {
				Utils.manejoErrores(ex,"Error en GUIReport. Reportes 11");
			}
			this.getContentPane().add(jrv);
			Utils.show(this);
		}	
	}

	public GUIReport(JDialog parent,int codRep,int nroPlanilla, Timestamp fecha,Vector usuarios, 
			double ingresoCajaDiaria,double ingCD,double egrCD,double saldoCajaDiaria,
			double saldoCajaGeneralAnt,double ingCG,double egrCG,double saldoCajaGeneral, double dineroRetira, double dineroCaja) throws Exception {
		super(parent,true);
		this.setSize(new java.awt.Dimension(700,570));
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
		this.setTitle("Vista Previa de Impresión");
		JasperPrint report=null;
		if(codRep==12){
			report = JasperReports.generarBalanceGeneral(nroPlanilla, fecha, usuarios, ingresoCajaDiaria,ingCD,egrCD,saldoCajaDiaria,saldoCajaGeneralAnt,ingCG,egrCG,saldoCajaGeneral,dineroRetira,dineroCaja);
		}if(codRep==13){
			report = JasperReports.generarBalanceDiario(nroPlanilla, fecha, usuarios, ingresoCajaDiaria,ingCD,egrCD,saldoCajaDiaria,dineroRetira,dineroCaja);
		}
		JRViewer jrv=null;
		try {
			jrv = new JRViewer(report);
		} catch (JRException ex) {
			Utils.manejoErrores(ex,"Error en GUIReport. Reportes 12 13");
		}
		this.getContentPane().add(jrv);
		Utils.show(this);
	}

	public GUIReport(JDialog parent,int codRep,KioscoDTO kiosco, String titulo, Vector detalleItImpr,
			Vector fecha, Vector debe, Vector haber,Vector saldo) {
		super(parent,true);
		this.setSize(new java.awt.Dimension(700,570));
		this.setResizable(false);
		this.setLocationRelativeTo(parent);
		this.setTitle("Vista Previa de Impresión");
		JasperPrint report=null;
		String mensaje="No se puede efectuar la impresión";
		if(codRep==13)
			report = JasperReports.detallarCuentaCliente(kiosco, titulo, detalleItImpr, fecha, debe, haber, saldo);
		if(detalleItImpr.size()>0){
			JRViewer jrv=null;
			try {
				jrv = new JRViewer(report);
			} catch (JRException ex) {
				Utils.manejoErrores(ex,"Error en GUIReport. Reportes 13");
			}
			this.getContentPane().add(jrv);
			Utils.show(this);
		}else{
			Utils.advertenciaUsr(parent,mensaje);
		}
	}	

}
