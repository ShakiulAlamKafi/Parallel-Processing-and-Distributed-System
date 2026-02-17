import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5005);

            if (s.isConnected()) {
                System.out.println("Socket is connected!");
            } else {
                System.out.println("Trying... please wait!");
            }

            s.close();
        } catch (Exception e) {
            System.err.println("Client side error: " + e);
        }
    }
}