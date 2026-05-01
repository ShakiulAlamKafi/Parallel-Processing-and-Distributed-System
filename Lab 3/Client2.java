package Lab4;

import java.io.*; // IO classes.
import java.net.*; // Networking classes.

/**
 *
 * @author s
 */
public class Client2 { // Client app entry point.
    public static void main(String[] args) { // JVM entry.
        try {
            Socket s = new Socket("localhost", 6000); // Connect to server on local machine, port 6000 (BLOCKS until
                                                      // connected).

            DataInputStream din = new DataInputStream(s.getInputStream()); // For reading server messages (UTF).
            DataOutputStream dout = new DataOutputStream(s.getOutputStream()); // For sending messages (UTF) to server.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Read user's keyboard input.

            String str = "", str2 = ""; // str = user’s message to send; str2 = server’s reply.

            while (!str.equals("exit")) { // Loop until user types "exit".
                str = br.readLine(); // BLOCKS: wait for user to type a line.
                dout.writeUTF(str); // Send that line to server using DataOutputStream format.
                dout.flush(); // Ensure it's sent immediately.
                str2 = (String) din.readUTF(); // BLOCKS: wait for server reply.
                System.out.println("Server Says: " + str2); // Display reply.
            }

            dout.close(); // Close output stream (good to close input and socket too).
            s.close(); // Close socket (also closes streams in many cases, but explicit is better).
        } catch (Exception e) {
            System.out.println(e);
        }
        ; // Print exception's toString().
    }
}
