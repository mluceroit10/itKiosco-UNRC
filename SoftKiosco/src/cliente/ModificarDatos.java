package cliente;

import java.util.Vector;

import server.persistencia.ManipuladorPersistencia;
import server.persistencia.dominio.FacturaCliente;
import server.persistencia.dominio.FacturaProveedor;
import server.persistencia.dominio.MovimientoCaja;
import server.persistencia.dominio.PlanillaES;

import common.Utils;

public class ModificarDatos {
    public ModificarDatos() {
    }
  
  
    public static void main(String[] args) throws Exception {
    	ManipuladorPersistencia mp = new ManipuladorPersistencia();
    	try {
			mp.initPersistencia();
				Vector fClientes = mp.getAll(FacturaCliente.class);
				for(int i=0; i<fClientes.size();i++){
					FacturaCliente b = (FacturaCliente)fClientes.elementAt(i);
					System.out.println("pos "+(i+1)+" nro factC " +b.getNroFactura());
					b.setPeriodo(Utils.getMes(b.getFechaHora())+"-"+Utils.getAnio(b.getFechaHora()));
				}	
				
				Vector fProv = mp.getAll(FacturaProveedor.class);
				for(int i=0; i<fProv.size();i++){
					FacturaProveedor fp = (FacturaProveedor)fProv.elementAt(i);
					System.out.println("pos "+(i+1)+" nro factP " +fp.getNroFactura());
					fp.setPeriodo(Utils.getMes(fp.getFechaHora())+"-"+Utils.getAnio(fp.getFechaHora()));
				}	
				
				Vector movsC = mp.getAll(MovimientoCaja.class);
				for(int i=0; i<movsC.size();i++){
					MovimientoCaja mc = (MovimientoCaja)movsC.elementAt(i);
					System.out.println("pos "+(i+1)+" nro factMC " +mc.getNroRecibo());
					mc.setPeriodo(Utils.getMes(mc.getFechaMC())+"-"+Utils.getAnio(mc.getFechaMC()));
				}	
				
				Vector planillas = mp.getAll(PlanillaES.class);
				for(int i=0; i<planillas.size();i++){
					PlanillaES pl = (PlanillaES)planillas.elementAt(i);
					System.out.println("pos "+(i+1)+" nro factP " +pl.getNroPlanilla());
					pl.setPeriodo(Utils.getMes(pl.getFecha())+"-"+Utils.getAnio(pl.getFecha()));
				}	
				
			mp.commit();
		}  finally{
			mp.rollback();
		}
    }
}
