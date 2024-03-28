package tp3.test;

import java.io.*;
import java.net.Socket;

public class Traitement extends Thread{
    private Socket cl;

    public Traitement(Socket socket){
        this.cl = socket;
    }

    public void servir() {
        try {
            //La creation du fichier pour la copie
            String filePath = "test.txt";
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("fichier crie");
            } else {
                System.out.println("fichier existe deja");
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            InputStream in = cl.getInputStream();
            BufferedReader d = new BufferedReader(new InputStreamReader(in));
            String ch ;
            //Reception de la linge et ecriture dans le fichier copie
            while ((ch = d.readLine()) != null && !ch.equals("END")) {
                bw.write(ch + System.lineSeparator());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void repondre (String msg){
        try{
            PrintStream out = new PrintStream(cl.getOutputStream());
            out.println(msg);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            servir();
            repondre("Le fichier est recu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}