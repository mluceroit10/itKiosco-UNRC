package cliente.GestionarProducto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cliente.ClienteConection;
import cliente.GestionarProveedor.MediadorGestionarProveedor;

import common.Utils;
import common.DTOs.ProductoDTO;
import common.DTOs.ProveedorDTO;
import common.GestionarProducto.IControlProducto;

public class MediadorModificarProducto implements ActionListener,KeyListener {

	private GUIAltaModProducto guiProducto = null;
	private MediadorGestionarProducto mgProducto;
	private IControlProducto controlProducto;
	private ProductoDTO prodDTO;
	public ProveedorDTO proveedor;
	private MediadorGestionarProveedor medGestionarProveedor;

	public MediadorModificarProducto(MediadorGestionarProducto oldMGProducto, ProductoDTO prod,GUIGestionarProducto guiPadre) {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorModificarProducto. Constructor");
		}
		this.controlProducto = clienteConexion.getIControlProducto();
		guiProducto = new GUIAltaModProducto(prod,guiPadre);
		guiProducto.setActionListeners(this);
		guiProducto.setKeyListener(this);
		mgProducto = oldMGProducto;
		prodDTO =  prod;
		proveedor = prod.getProveedor();
		Utils.show(guiProducto);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiProducto.getJBAceptar()) {
			String codigo = guiProducto.getJTFCodigo().getText();
			String nombre = Utils.mayuscula(guiProducto.getJTFNombre().getText());
			String stockM = guiProducto.getJTFStockMin().getText();
			String stockA = guiProducto.getJTFStockAct().getText();
			String prEntr = guiProducto.getJTFPrecioEntrada().getText();
			String mGanancia = guiProducto.getJTFMargenGanancia().getText();
			String prVta = guiProducto.getJTFPrecioVenta().getText();
			String prov = guiProducto.getJTFProveedor().getText();
			if (codigo.length()==0 || nombre.length()==0 || stockM.length()==0 || stockA.length()==0 || prEntr.length()==0 || mGanancia.length()==0 || prVta.length()==0 || prov.length()==0 ){
				Utils.advertenciaUsr(guiProducto,"Alguno de los campos obligatorios esta vacio.");
			}else if(!Utils.esNumeroPosNeg(stockA)){
				Utils.advertenciaUsr(guiProducto,"El stock actual ingresado es incorrecto.");            		
			}else if(!Utils.esDouble(prEntr)){
				Utils.advertenciaUsr(guiProducto,"El precio de entrada ingresado es incorrecto.");
			}else if(!Utils.esDouble(prVta)){
				Utils.advertenciaUsr(guiProducto,"El precio venta ingresado es incorrecto.");		
			}else {
				try {
					double prEntrada = Double.parseDouble(prEntr);
					double prVenta = Double.parseDouble(prVta);
					Long cod = new Long(codigo);
					ProductoDTO prod = new ProductoDTO();
					prod.setCodigo(cod);
					prod.setNombre(nombre);
					prod.setStockMinimo(Integer.parseInt(stockM));
					prod.setStockActual(Integer.parseInt(stockA));
					prod.setPrecioEntrada(prEntrada);
					prod.setMargenGanancia(Integer.parseInt(mGanancia));
					prod.setPrecioVenta(prVenta);
					prod.setProveedor(proveedor);
					prod.setEliminado(false);
					if (this.controlProducto.puedoEditar(prodDTO,prod)){
						this.controlProducto.modificarProducto(prodDTO.getId(), prod);
						guiProducto.dispose();
						mgProducto.cargarDatos();
					} else {
						Utils.advertenciaUsr(guiProducto,"El producto que desea ingresar ya existe.");
					}
				} catch(Exception ex) {
					Utils.manejoErrores(ex,"Error en MediadorModificarProducto. ActionPerformed");
				}
			}
		}else if (source == guiProducto.getJBProveedor()) {
			buscarProveedor();
		} else if (source == guiProducto.getJBCancelar()) {   
			guiProducto.dispose();
		} 
	}

	private void buscarProveedor() {
		if (medGestionarProveedor== null) {
			medGestionarProveedor= new MediadorGestionarProveedor(this,guiProducto);
		} else {
			if (!medGestionarProveedor.getGUI().isVisible()) {
				medGestionarProveedor.getGUI().setVisible(true);
			}
		}
		if (proveedor != null){
			this.guiProducto.getJTFProveedor().setText(proveedor.getNombre());
			this.cargarProveedor(proveedor);
		}
	}

	private void cargarProveedor(ProveedorDTO pr) {
		this.proveedor = pr;
	}

	public void actualizarProveedor() {
		guiProducto.setProveedor(proveedor.getNombre());
	}

	public void keyReleased(KeyEvent arg0) {
		String prEntr = this.guiProducto.getJTFPrecioEntrada().getText();
		String mGanancia = this.guiProducto.getJTFMargenGanancia().getText();
		if(prEntr.length()!=0 && mGanancia.length()!=0){
			if(Utils.esDouble(prEntr)){
				double prEntrada=Double.parseDouble(prEntr);
				double mg=Double.parseDouble(mGanancia);
				double prVenta=Utils.redondear(Utils.redondear(prEntrada*(mg/100),2)+prEntrada,2);
				String precio=String.valueOf(prVenta);
				char valor2=precio.charAt(precio.length()-3);
				if(valor2=='.') {
					char valorUlt=precio.charAt(precio.length()-1);
					if(valorUlt=='1' || valorUlt=='2' || valorUlt=='3' || valorUlt=='4') {
						precio=precio.substring(0,precio.length()-1)+"5";
					}
					if(valorUlt=='6' || valorUlt=='7' || valorUlt=='8' || valorUlt=='9'){
						prVenta=Utils.redondear(prVenta, 1);
						precio=String.valueOf(prVenta);
					}
				}
				char valor1=precio.charAt(precio.length()-2);
				if(valor1=='.') precio +="0";
				this.guiProducto.getJTFPrecioVenta().setText(precio);
			}
		}
	}

	public void keyTyped(KeyEvent arg0) {
	} 
	
	public void keyPressed(KeyEvent arg0) {
	}
	
}

