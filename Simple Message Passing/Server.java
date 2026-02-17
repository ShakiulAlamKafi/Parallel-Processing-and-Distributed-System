package simple_msg_passing;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5005);
            System.out.println("Server is running and waiting for a client...");

            Socket s = ss.accept(); // Wait for client
            System.out.println("Client connected!");

            // Input from client
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // Output to client
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            // Read message from client
            String msg = in.readLine();
            System.out.println("Client says: " + msg);

            // Reply to client
            out.println("Hello Client, message received: " + msg);

            s.close();
            ss.close();
        } catch (Exception e) {
            System.err.println("Server side error: " + e);
        }
    }
}
