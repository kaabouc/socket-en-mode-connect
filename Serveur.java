package tp3.test;

import java.io.IOException;
import java.net.ServerSocket;

public class Serveur  {

    private int port ;
    private ServerSocket ss;
    public Serveur(int port ) {
        super();
        this.port  = port;
        try{
            ss = new ServerSocket(port);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void traitDemande(){
        while (true){
            try {
                System.out.println("Attend le client sur le port:"+port);
                Traitement traitement = new Traitement(ss.accept());
                traitement.start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }


}