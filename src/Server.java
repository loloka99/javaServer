import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;

    public Server() {
        try {
            server = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                Socket client = server.accept();
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                // Generál egy véletlen számot
                int randomNumber = (int) (Math.random() * 100);

                // Elküldi a véletlen számot a kliensnek
                out.println(randomNumber);

                // Fogadja az eredményt a klienstől
                String result = in.readLine();
                System.out.println("Kapott eredmény: " + result);

                // Kiírja az eredményt a terminálba

                // Bezárja az adatfolyamokat és a socketet
                in.close();
                out.close();
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
