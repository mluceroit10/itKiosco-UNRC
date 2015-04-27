package server.persistencia;

import java.util.Collection;
import java.util.Vector;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import server.SingletonPersistencia;

public class ManipuladorPersistencia extends Thread{
    private PersistenceManager pm;
    private Transaction tx;

    public ManipuladorPersistencia(){
    }

    public synchronized void initPersistencia(){
    	pm = SingletonPersistencia.getInstance().getPM();
        tx = pm.currentTransaction();
        while (tx.isActive()) {
			try {
				sleep(5000);// 5 seg
				tx = pm.currentTransaction();
			} catch (Exception ex){
			}
		}
        if(!tx.isActive())
        	try {
        		tx.begin();
			} catch (Exception ex){
				initPersistencia();
			}
        else
        	initPersistencia();
    }

    public boolean commit(){
        try {
            tx.commit(); 
         } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    	return true;
    }

    public boolean close(){
    	try {
    		pm.close();
    	} catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    	return true;
    }
    
    public void rollback(){
    	if (tx.isActive()) tx.rollback();
    }

    public void hacerPersistente(Object obj)throws Exception{
    	try{
            pm.makePersistent(obj);
    	} catch(Exception e) {
            throw e;
    	}
    }
    
    public void borrar(Object obj)throws Exception{
    	try{
            pm.deletePersistent(obj);
    	} catch(Exception e){
            throw e;
    	}
    }
    
    public Vector getAll(Class clase)throws Exception{
    	Vector objetos=new Vector();
    	try{
            Class clienteClass = clase;
            Extent clnCliente = pm.getExtent(clienteClass, false);
            Query query = pm.newQuery(clnCliente,"");
            Collection coleccion=(Collection)query.execute();
            objetos.addAll(coleccion);
    	} catch(Exception e){
            throw e;
    	}
    	return objetos;
    }
    
    public Vector getObjectsSubc(Class clase,String filter)throws Exception{
    	Vector objetos=new Vector();
    	try{
            Class clienteClass = clase;
            Extent clnCliente = pm.getExtent(clienteClass, true);
            Query query = pm.newQuery(clnCliente,filter);
            Collection coleccion=(Collection)query.execute();
            objetos.addAll(coleccion);
    	} catch(Exception e) {
            throw e;
    	}
    	return objetos;
    }
    
    public Vector getObjects(Class clase,String filter)throws Exception{
    	Vector objetos=new Vector();
    	try{
            Class clienteClass = clase;
            Extent clnCliente = pm.getExtent(clienteClass, false);
            Query query = pm.newQuery(clnCliente,filter);
            Collection coleccion=(Collection)query.execute();
            objetos.addAll(coleccion);
    	} catch(Exception e) {
            throw e;
    	}
    	return objetos;
    }

    public Vector getAllOrdered(Class clase, String ordering)throws Exception{
    	Vector objetos=new Vector();
    	try{
    		Class clienteClass = clase;
            Extent clnCliente = pm.getExtent(clienteClass, false);
            Query query = pm.newQuery(clnCliente,"");
            query.setOrdering(ordering);  
            Collection coleccion=(Collection)query.execute();
            objetos.addAll(coleccion);
    	} catch(Exception e){
            throw e;
    	}
    	return objetos;
    }
    
    public Vector getObjectsOrdered(Class clase,String filter, String ordering)throws Exception{
    	Vector objetos=new Vector();
    	try{
            Class clienteClass = clase;
            Extent clnCliente = pm.getExtent(clienteClass, false);
            Query query = pm.newQuery(clnCliente,filter);
            query.setOrdering(ordering);  
            Collection coleccion=(Collection)query.execute();
            objetos.addAll(coleccion);
    	} catch(Exception e) {
            throw e;
    	}
    	return objetos;
    }
    
    public Vector getAllOrderedSubc(Class clase, String ordering)throws Exception{
    	Vector objetos=new Vector();
    	try{
            Class clienteClass = clase;
            Extent clnCliente = pm.getExtent(clienteClass,true); 
            Query query = pm.newQuery(clnCliente,"");
            query.setOrdering(ordering);  
            Collection coleccion=(Collection)query.execute();
            objetos.addAll(coleccion);
    	} catch(Exception e){
            throw e;
    	}
    	return objetos;
    }
    
    public Vector getObjectsOrderedSubc(Class clase,String filter, String ordering)throws Exception{
    	Vector objetos=new Vector();
    	try{
            Class clienteClass = clase;
            Extent clnCliente = pm.getExtent(clienteClass, true);
            Query query = pm.newQuery(clnCliente,filter);
            query.setOrdering(ordering);  
            Collection coleccion=(Collection)query.execute();
            objetos.addAll(coleccion);
    	} catch(Exception e) {
            throw e;
    	}
    	return objetos;
    }
    
    public Transaction getTx() {
        return tx;
    }
    
}


