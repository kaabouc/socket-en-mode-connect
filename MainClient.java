package tp3.test;

public class MainClient {
    public static void main(String[] args) {

        Client client = new Client("127.0.0.1", 1000);
        String chemin = "text.txt";
        client.request(chemin);
        client.servir();
    }
}