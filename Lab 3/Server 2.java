package Lab4;

import java.io.*; // Imports IO classes: DataInputStream, DataOutputStream, BufferedReader, etc.
import java.net.*; // Imports networking classes: ServerSocket, Socket.

public class Server { // Declares the Server class (entry point with main).
    static int clientCount = 0; // shared counter
    public static void main(String[] args) { // JVM calls main() to start the program.
        try { // Start a try block to catch any runtime exceptions.
            ServerSocket ss = new ServerSocket(6000); // Bind a TCP server socket to port 6000; starts listening.
            System.out.println("Server started. Waiting for clients..."); // Log status.

            while (true) { // Infinite loop: keep accepting clients forever.
                Socket s = ss.accept(); // BLOCKS until a client connects; returns a Socket for that client.
                clientCount++; // increase count
                System.out.println("Client " + clientCount + " connected.");
                
                // Create a new thread to handle the client
                ClientHandler clientHandler = new ClientHandler(s, clientCount); // Create a handler object for this client.

                // Start the thread
                new Thread(clientHandler).start(); // Launch a new thread so the server can accept more clients concurrently.
            }

        } catch (Exception e) { // Catch any exception thrown in the try block.
            System.out.println(e); // Print the exception’s toString(); (not a full stack trace).
        }
    } // End of main().
} // End of Server class.

class ClientHandler implements Runnable { // A per-client task that can run in its own thread.
    Socket socket; // Holds the connected client's socket.
    DataInputStream din; // For reading UTF-8-ish strings (modified UTF) from client over the socket.
    DataOutputStream dout; // For writing UTF strings back to client over the socket.
    BufferedReader br; // For reading lines from the SERVER OPERATOR's console (System.in).

    int clientNumber;
    public ClientHandler(Socket socket, int clientNumber) { // Constructor receives the client's socket.
        try {
            this.socket = socket; // Save socket.
            this.clientNumber = clientNumber;
            this.din = new DataInputStream(socket.getInputStream()); // Wrap input stream to read data types/UTF.
            this.dout = new DataOutputStream(socket.getOutputStream()); // Wrap output stream to write data types/UTF.
            this.br = new BufferedReader(new InputStreamReader(System.in)); // Read replies from server console.
        } catch (IOException e) {
            System.out.println(e); // Log setup errors.
        }
    }

    @Override
    public void run() { // This method runs in a separate thread per client.
        String str = "", str2 = ""; // str = last message from client; str2 = server’s typed reply.
        try {
            while (!str.equals("exit")) { // Keep chatting until client sends "exit".
                str = (String) din.readUTF(); // BLOCKS: waits for a UTF string from client.
                System.out.println("Client " + clientNumber + " says: " + str); // Show client's message on server console.
                str2 = br.readLine(); // BLOCKS: wait for server operator to type a reply.
                dout.writeUTF(str2); // Send the reply to the client.
                dout.flush(); // Force bytes out immediately.
            }
        } catch (IOException e) {
            System.out.println(e); // Log IO errors (client disconnect, etc.).
        } finally {
            try {
                // Close resources when the client exits
                din.close(); // Close input stream (also closes underlying socket input).
                dout.close(); // Close output stream.
                socket.close(); // Close the socket itself (good practice even if streams close it).
            } catch (IOException e) {
                System.out.println(e); // Log any close errors.
            }
        }
    }
}
