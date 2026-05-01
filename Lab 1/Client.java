import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try{//needed for exception handling
            Socket s = new Socket("localhost",5005); //always listens to port 5000, Socket constructor
        }
       catch(Exception e){
        System.out.println(e);
       }
    }
}
