package simple_msg_passing;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5005);
            System.out.println("Connected to server!");

            // Output to server
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            // Input from server
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // Send a message
            out.println("Hello Server! This is client.");

            // Receive reply
            String response = in.readLine();
            System.out.println("Server says: " + response);

            s.close();
        } catch (Exception e) {
            System.err.println("Client side error: " + e);
        }
    }
}
