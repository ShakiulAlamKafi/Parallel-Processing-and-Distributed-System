import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss= new ServerSocket(5004);
            System.out.println("Server has been created, waiting for client");
            Socket s = ss.accept();
            System.out.println("Client has been connected");
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = " ", str2 = " ";
            while (!str.equals("stop")) {
                str = (String)din.readUTF();
                System.out.println("Client: "+str);
                str2 = br.readLine();
                dout.writeUTF(str2);
                dout.flush();
            }
            din.close();
            dout.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
