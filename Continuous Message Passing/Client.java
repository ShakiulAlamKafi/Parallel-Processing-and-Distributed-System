package simple_msg_passing_w2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5005);

            System.out.println("Connected to server!");

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msg = "", reply = "";

            while (!msg.equalsIgnoreCase("stop")) {
                System.out.print("Client: ");
                msg = br.readLine();
                dos.writeUTF(msg);
                dos.flush();

                reply = dis.readUTF();
                System.out.println("Server: " + reply);
            }

            dos.close();
            dis.close();
            s.close();

        } catch (Exception e) {
            System.err.println("Client side error: " + e);
        }
    }
}