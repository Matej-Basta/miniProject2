import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Security;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class Server {
    public static void main(String[] args) {
        int port = 7007;
        System.setProperty("javax.net.ssl.keyStore", "keystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");
        // System.setProperty("javax.net.debug", "all");
        System.out.println("Waiting for clients...");
        try {
            SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory
                    .getDefault();
            SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(port);
            while (true) {
                Socket socket = sslServerSocket.accept();
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
