import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5005);
            System.out.println("Server is running...");

            Socket s = ss.accept();
            System.out.println("Client connected!");

            ss.close();
        } catch (Exception e) {
            System.err.println("Server side error: " + e);
        }
    }
}