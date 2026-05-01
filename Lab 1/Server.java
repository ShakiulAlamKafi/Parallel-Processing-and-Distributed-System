import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try{//needed for exception handling
            ServerSocket ss = new ServerSocket(5005); //always listens to port 5000, ServerSocket constructor
            System.out.println("Server has been created, waiting for client");
            Socket s = ss.accept(); //returns socket object
            System.out.println("Client has been connected");
        }
       catch(Exception e){
        System.out.println(e);
       }
    }
}
