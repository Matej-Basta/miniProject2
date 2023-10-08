import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Client {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Client <message> <server IP> <server port>");
            System.exit(1);
        }

        System.setProperty("javax.net.ssl.trustStore", "myTrustedStore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");
        // System.setProperty("javax.net.debug", "all");
        System.out.println("Client started");
        String message = args[0];
        String serverIP = args[1];
        int serverPort = Integer.parseInt(args[2]);

        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory
                    .getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(serverIP, serverPort);
            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            out.println(message);
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            System.out.println(in.readLine());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}