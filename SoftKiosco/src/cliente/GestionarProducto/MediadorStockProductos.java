package cliente.GestionarProducto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cliente.ClienteConection;
import cliente.Principal.GUIReport;

import common.Utils;
import common.DTOs.ProveedorDTO;
import common.GestionarProducto.IControlProducto;
import common.GestionarProveedor.IControlProveedor;

public class MediadorStockProductos  implements ActionListener, ListSelectionListener {
	private GUIStockProductos guiImprSC = null;
	public IControlProducto controlProducto;
	public IControlProveedor controlProveedor;
	public Date fecha;
	public Vector productos=new Vector();
	public String titulo="";
	public boolean todos=true;
	public Vector idsProv=new Vector();

	public MediadorStockProductos(JFrame guiPadre) throws Exception {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			Utils.manejoErrores(ex,"Error en MediadorStockProducto. Constructor");
		}
		controlProveedor = clienteConexion.getIControlProveedor();
		controlProducto = clienteConexion.getIControlProducto();
		this.guiImprSC = new GUIStockProductos(guiPadre);
		this.guiImprSC.setActionListeners(this);
		try {
			Vector prvs = controlProveedor.obtenerProveedores();
			for(int i=0;i<prvs.size();i++){
				ProveedorDTO p=(ProveedorDTO) prvs.elementAt(i);
				guiImprSC.proveedores.add(p.getNombre());
				idsProv.add(p.getId());
			}
		} catch (RemoteException ex) {
			Utils.manejoErrores(ex,"Error en MediadorStockProducto. Constructor");
		}
	}

	public void show() throws Exception {
		guiImprSC.actualizarTabla();
		Utils.show(guiImprSC);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiImprSC.getJBContinuar()) {
			ButtonModel tipoProveedor=guiImprSC.opcTipoProv().getSelection();
			String orden=guiImprSC.getJCOrdenListado().getSelectedItem().toString();
			String tipoProv =tipoProveedor.getActionCommand();
			String atOrden;
			if(orden.compareTo("Codigo del Producto")==0){
				atOrden = "codigo";
			}else{
				atOrden = "nombre";
			}
			try {
				fecha=new Date();
				if(tipoProv.compareTo("Todos")==0){
					productos = controlProducto.obtenerStockTodosProductos(atOrden);
					titulo="Todos los productos en bajo stock";
					new GUIReport(guiImprSC,1,productos,titulo,"");
				}
				if(tipoProv.compareTo("unProv")==0){
					int nombProv=guiImprSC.getJCProveedores().getSelectedIndex();
					productos = controlProducto.obtenerStockProductosProveedor((Long)idsProv.elementAt(nombProv),atOrden);
					titulo="Todos los productos en bajo stock del proveedor "+nombProv;
					new GUIReport(guiImprSC,2,productos,titulo,"");
				}
			} catch (Exception ex) {
				Utils.manejoErrores(ex,"Error en MediadorStockProducto. ActionPerformed");
			}
		} else { 
			guiImprSC.dispose();
		}
	}

	public void valueChanged(ListSelectionEvent arg0) {
	}

}
