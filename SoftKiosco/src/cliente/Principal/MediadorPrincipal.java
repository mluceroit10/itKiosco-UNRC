package cliente.Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import cliente.GestionarFacturaCliente.MediadorFacturarCliente;
import cliente.GestionarFacturaProveedor.MediadorFacturarProveedor;
import cliente.GestionarKiosco.MediadorModificarKiosco;
import cliente.GestionarLocalidad.MediadorGestionarLocalidad;
import cliente.GestionarMovimientoCaja.MediadorGestionarMovimientoCaja;
import cliente.GestionarPlanillaES.MediadorGestionarPlanillaES;
import cliente.GestionarProducto.MediadorGestionarProducto;
import cliente.GestionarProducto.MediadorStockProductos;
import cliente.GestionarProveedor.MediadorGestionarProveedor;
import cliente.GestionarProvincia.MediadorGestionarProvincia;
import cliente.GestionarSesion.MediadorHorasTrabajadas;
import cliente.GestionarSesion.MediadorInicioCierreSesion;
import cliente.GestionarUsuario.MediadorGestionarUsuario;
import cliente.IngresarContrasenia.MediadorIngresarContrasenia;
import cliente.ListarFacturasCliente.MediadorListarFacturasCliente;
import cliente.ListarFacturasProveedor.MediadorListarFacturasProveedor;
import cliente.backupDataBase.MediadorBackup;

import common.RootAndIp;
import common.Utils;
import common.DTOs.UsuarioDTO;
import common.constantes.TIPO_USUARIOS;

public class MediadorPrincipal implements ActionListener{
	private GUIPrincipal guiPrincipal;
	public UsuarioDTO usuarioRegistrado;
	private Date hoy= new Date();
	
	public MediadorPrincipal() throws Exception{
		GUIInicio guiInicio = new GUIInicio();
		guiInicio.setVisible(true);
		new MediadorAccionesIniciarPrograma();
		guiInicio.dispose();
		this.guiPrincipal = new GUIPrincipal();
		this.guiPrincipal.setActionListeners(this);
	}

	public void show() {
		guiPrincipal.show();
	}

	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();
		int mesL=Utils.getMes(hoy);
		int anioL=Utils.getAnio(hoy);
		if(this.guiPrincipal.getArchivoInfoKiosco()==source){
			try{
				boolean ingresoPermitido=false;
				String tipoSesion="";
				MediadorIngresarContrasenia mi=new MediadorIngresarContrasenia(guiPrincipal);
				mi.show();
				tipoSesion=mi.getTipoSesion();
				if(tipoSesion.length()>0){
					if(tipoSesion.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0)
						ingresoPermitido=true;
					new MediadorModificarKiosco(guiPrincipal,ingresoPermitido);
				}
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorGestionarProveedor.");
			}
		}else if(this.guiPrincipal.getArchivoInfoPrograma()==source){
			try{
				new MediadorInfo(this,guiPrincipal);
			} catch (Exception p){}
		}else if(this.guiPrincipal.getArchivoSalir()==source){
			int prueba = JOptionPane.showConfirmDialog(guiPrincipal,"Esta seguro que desea Salir","ATENCION!!!", 0,3,new ImageIcon(RootAndIp.getPathImagenes()+"deseaSalir.gif"));
			if( prueba==0 ){
				try {
					new MediadorAccionesCerrarPrograma(guiPrincipal);
				} catch (Exception ex) {
					Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorAccionesCerrarPrograma.");
				}	
				System.exit(0);
			}
		}else if (this.guiPrincipal.getGestionarProvincia()== source) {
			try{
				new MediadorGestionarProvincia(guiPrincipal); 
			} catch (Exception p){}
		}else if (this.guiPrincipal.getGestionarLocalidad()== source) {
			try{
				new MediadorGestionarLocalidad(guiPrincipal);
			} catch (Exception p){}	
		}else if(this.guiPrincipal.getBaseDatos()==source){
			try{
				new MediadorBackup(guiPrincipal,true);
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorBackup.");
			}	
		}else if(this.guiPrincipal.getJButtonFacturaCliente()==source){
			try{
				MediadorFacturarCliente msprod = new MediadorFacturarCliente(guiPrincipal);
				msprod.show();
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorFacturarCliente.");
			}	
		}else if(this.guiPrincipal.getJButtonTodasFactCliente()==source){
			try{
				new MediadorListarFacturasCliente(mesL,anioL,guiPrincipal);
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorListarFacturasCliente.");
			}			
		}else if(this.guiPrincipal.getJButtonGestionarMC()==source){
			try{
				new MediadorGestionarMovimientoCaja(mesL,anioL,guiPrincipal);
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorGestionarMovimientoCaja.");
			}	
		}else if(this.guiPrincipal.getJButtonPlanillaES()==source){
			try{
				boolean ingresoPermitido=false;
				String tipoSesion="";
				MediadorIngresarContrasenia mi=new MediadorIngresarContrasenia(guiPrincipal);
				mi.show();
				tipoSesion=mi.getTipoSesion();
				boolean esAdmin=false;
				if(tipoSesion.length()>0){
					if(tipoSesion.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0){
						ingresoPermitido=true;
						esAdmin=true;
					}if(tipoSesion.compareTo(TIPO_USUARIOS.BECARIO_ADM)==0)
						ingresoPermitido=true;
					if(tipoSesion.compareTo(TIPO_USUARIOS.BECARIO)==0)
						ingresoPermitido=true;
					if(ingresoPermitido){
						new MediadorGestionarPlanillaES(mesL,anioL,guiPrincipal,esAdmin);
					}else
						Utils.advertenciaUsr(guiPrincipal, "Usted no tiene permiso para acceder a esta funcionalidad");
				}
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorGestionarPlanillaES.");
			}			
		}else if(this.guiPrincipal.getJButtonUsuarios()==source){
			try{
				boolean ingresoPermitido=false;
				String tipoSesion="";
				MediadorIngresarContrasenia mi=new MediadorIngresarContrasenia(guiPrincipal);
				mi.show();
				tipoSesion=mi.getTipoSesion();
				Long idUsr=mi.getIdUsrRegistrado();
				if(tipoSesion.length()>0){
					if(tipoSesion.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0)
						ingresoPermitido=true;
					if(tipoSesion.compareTo(TIPO_USUARIOS.BECARIO_ADM)==0)
						ingresoPermitido=true;
					if(tipoSesion.compareTo(TIPO_USUARIOS.BECARIO)==0)
						ingresoPermitido=true;
					if(ingresoPermitido){
						new MediadorGestionarUsuario(guiPrincipal,tipoSesion,idUsr);
					}else
						Utils.advertenciaUsr(guiPrincipal, "Usted no tiene permiso para acceder a esta funcionalidad");
				}
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorGestionarUsuario.");
			}	
		}else if(this.guiPrincipal.getJButtonInicioCierreSesion()==source){
			try{
				MediadorInicioCierreSesion mc=new MediadorInicioCierreSesion(guiPrincipal);
				mc.show();
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorInicioCierreSesion.");
			}	
		}else if(this.guiPrincipal.getJButtonHorasTrabajadas()==source){
			try{
				boolean ingresoPermitido=false;
				String tipoSesion="";
				MediadorIngresarContrasenia mi=new MediadorIngresarContrasenia(guiPrincipal);
				mi.show();
				tipoSesion=mi.getTipoSesion();
				Long idUsr=mi.getIdUsrRegistrado();
				if(tipoSesion.length()>0){
					if(tipoSesion.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0)
						ingresoPermitido=true;
					if(tipoSesion.compareTo(TIPO_USUARIOS.BECARIO_ADM)==0)
						ingresoPermitido=true;
					if(tipoSesion.compareTo(TIPO_USUARIOS.BECARIO)==0)
						ingresoPermitido=true;
					if(ingresoPermitido){
						MediadorHorasTrabajadas mHt=new MediadorHorasTrabajadas(guiPrincipal,tipoSesion,idUsr);
						mHt.show();
					}else
						Utils.advertenciaUsr(guiPrincipal, "Usted no tiene permiso para acceder a esta funcionalidad");
				}
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorHorasTrabajadas.");
			}	
		}else if(this.guiPrincipal.getJButtonFacturaProveedor()==source){
			try{
				boolean ingresoPermitido=false;
				String tipoSesion="";
				MediadorIngresarContrasenia mi=new MediadorIngresarContrasenia(guiPrincipal);
				mi.show();
				tipoSesion=mi.getTipoSesion();
				if(tipoSesion.length()>0){
					if(tipoSesion.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0)
						ingresoPermitido=true;
					if(tipoSesion.compareTo(TIPO_USUARIOS.BECARIO_ADM)==0)
						ingresoPermitido=true;
					if(ingresoPermitido){
						MediadorFacturarProveedor mfp=new MediadorFacturarProveedor(guiPrincipal);
						mfp.show();
					}else
						Utils.advertenciaUsr(guiPrincipal, "Usted no tiene permiso para acceder a esta funcionalidad");
				}
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorFacturarProveedor.");
			}	
		}else if(this.guiPrincipal.getJButtonTodasFactProveedor()==source){
			try{
				boolean ingresoPermitido=false;
				String tipoSesion="";
				MediadorIngresarContrasenia mi=new MediadorIngresarContrasenia(guiPrincipal);
				mi.show();
				tipoSesion=mi.getTipoSesion();
				if(tipoSesion.length()>0){
					if(tipoSesion.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0)
						ingresoPermitido=true;
					if(tipoSesion.compareTo(TIPO_USUARIOS.BECARIO_ADM)==0)
						ingresoPermitido=true;
					if(ingresoPermitido){
						new MediadorListarFacturasProveedor(mesL,anioL,guiPrincipal);
					}else
						Utils.advertenciaUsr(guiPrincipal, "Usted no tiene permiso para acceder a esta funcionalidad");
				}
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorListarFacturasProveedor.");
			}		
		}else if (this.guiPrincipal.getJButtonProductos()== source) {
			try{
				boolean ingresoPermitido=false;
				String tipoSesion="";
				MediadorIngresarContrasenia mi=new MediadorIngresarContrasenia(guiPrincipal);
				mi.show();
				tipoSesion=mi.getTipoSesion();
				if(tipoSesion.length()>0){
					if(tipoSesion.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0)
						ingresoPermitido=true;
					if(tipoSesion.compareTo(TIPO_USUARIOS.BECARIO_ADM)==0)
						ingresoPermitido=true;
					if(ingresoPermitido)
						new MediadorGestionarProducto(guiPrincipal);
					else
						Utils.advertenciaUsr(guiPrincipal, "Usted no tiene permiso para acceder a esta funcionalidad");
				}
			} catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorGestionarProducto.");
			}
		}else if(this.guiPrincipal.getJButtonControlStock()==source){
			try{
				boolean ingresoPermitido=false;
				String tipoSesion="";
				MediadorIngresarContrasenia mi=new MediadorIngresarContrasenia(guiPrincipal);
				mi.show();
				tipoSesion=mi.getTipoSesion();
				if(tipoSesion.length()>0){
					if(tipoSesion.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0)
						ingresoPermitido=true;
					if(tipoSesion.compareTo(TIPO_USUARIOS.BECARIO_ADM)==0)
						ingresoPermitido=true;
					if(ingresoPermitido){
						MediadorStockProductos msprod = new MediadorStockProductos(guiPrincipal);
						msprod.show();
					}else
						Utils.advertenciaUsr(guiPrincipal, "Usted no tiene permiso para acceder a esta funcionalidad");
				}
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorStockProductos.");
			}	
		}else if(this.guiPrincipal.getJButtonProveedores()==source){
			try{
				boolean ingresoPermitido=false;
				String tipoSesion="";
				MediadorIngresarContrasenia mi=new MediadorIngresarContrasenia(guiPrincipal);
				mi.show();
				tipoSesion=mi.getTipoSesion();
				if(tipoSesion.length()>0){
					if(tipoSesion.compareTo(TIPO_USUARIOS.ADMINISTRADOR)==0)
						ingresoPermitido=true;
					if(ingresoPermitido){
						new MediadorGestionarProveedor(guiPrincipal);
					}else
						Utils.advertenciaUsr(guiPrincipal, "Usted no tiene permiso para acceder a esta funcionalidad");
				}
			}catch (Exception ex){
				Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorGestionarProveedor.");
			}
		}else
			if(this.guiPrincipal.getJButtonSalir()== source){
				int prueba = JOptionPane.showConfirmDialog(guiPrincipal,"Esta seguro que desea Salir","ATENCION!!!", 0,3,new ImageIcon(RootAndIp.getPathImagenes()+"deseaSalir.gif"));
				if( prueba==0 ){
					try {
						new MediadorAccionesCerrarPrograma(guiPrincipal);
					} catch (Exception ex) {
						Utils.manejoErrores(ex,"Error en MediadorPrincipal. MediadorAccionesCerrarPrograma.");
					}	
					System.exit(0);
				}
			}   
	}	
	
}
