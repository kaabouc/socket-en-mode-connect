package tp3.test;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;

    public Client(String adresseServeur , int portServeur){
        super();
        try{
            this.socket = new Socket(adresseServeur,portServeur);
        }catch(IOException e){
            e.printStackTrace();
        }

    }


    public void servir(){
        try {
            InputStream in = socket.getInputStream();
            BufferedReader d = new BufferedReader(new InputStreamReader(in));
            String ch = d.readLine();
            System.out.println("Message du serveur : "+ch);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void request (String filePath){
        try{
            System.out.println("J'envoie le fichier ");
            PrintStream out = new PrintStream(socket.getOutputStream());
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String ligne;
                //la lectrure et l'envoie de ligne par ligne
                while ((ligne = br.readLine()) != null) {
                    out.println(ligne);
                    out.flush();
                }
                //pour signaler la fin du fichier
                out.println("END");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}