package simple_msg_passing_w2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5005);
            System.out.println("Server is running...");

            Socket s = ss.accept();
            System.out.println("Client connected!");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String clientMsg = "", serverMsg = "";

            while (!clientMsg.equalsIgnoreCase("stop")) {
                // Read message from client
                clientMsg = dis.readUTF();
                System.out.println("Client: " + clientMsg);

                // Send reply from server
                System.out.print("Server: ");
                serverMsg = br.readLine();
                dos.writeUTF(serverMsg);
                dos.flush();
            }

            dis.close();
            dos.close();
            s.close();
            ss.close();

        } catch (Exception e) {
            System.err.println("Server side error: " + e);
        }
    }
}