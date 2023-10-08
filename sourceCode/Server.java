import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Waiting for clients...");

        try {
            ServerSocket serverSocket = new ServerSocket(7007);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection established");
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = in.readLine();
                System.out.println(message);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message + " back at you");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
