package cliente;

import cliente.Principal.MediadorPrincipal;

public class Main {  
  
    public static void main(String[] args) throws Exception {
        String conf = "";
        if (args.length > 0) {
            conf = args[0];
        }    
        common.RootAndIp.setConf(conf);
        MediadorPrincipal mp = new MediadorPrincipal();
		mp.show();
    }  
  
}
