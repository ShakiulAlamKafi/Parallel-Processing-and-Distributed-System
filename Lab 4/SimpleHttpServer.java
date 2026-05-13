/* 
package Lab6;

// Import required classes for HTTP server
import com.sun.net.httpserver.HttpServer;     // Main class to create and run an HTTP server
import com.sun.net.httpserver.HttpHandler;    // Interface to handle HTTP requests
import com.sun.net.httpserver.HttpExchange;   // Represents an HTTP request + response
import java.io.IOException;                   // Exception for input/output operations
import java.io.OutputStream;                  // Used to write data (response) back to the client
import java.net.InetSocketAddress;            // Represents IP + port number where the server listens.

// Define a public class
public class SimpleHttpServer {

    // Main method: entry point of the program
    // 'throws IOException' means if an IO error occurs, let the JVM handle it instead of try-catch here
    public static void main(String[] args) throws IOException {

        //HttpServer.create(...) → static method to build a server
        // Create an HTTP server bound to port 8080
        // InetSocketAddress(8080) → binds the server to port 8080 on the local machine
        // Second parameter '0' → default backlog queue size for incoming connections
        //The resulting server object represents the running HTTP server.
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Create a context (endpoint) at "/hello" path
        // Assign HelloHandler() class to handle requests sent to this path: http://localhost:8080/hello
        server.createContext("/hello", new HelloHandler());

        // Start the server (begins listening for requests)
        server.start();

        // Print message to console
        System.out.println("Server started on port 8080");
    }

    // Custom static inner class named HelloHandler for the "/hello" endpoint
    // 'implements HttpHandler' → this class must override or define the handle() method (interface requirement). 
    static class HelloHandler implements HttpHandler {

        @Override
        // handle(HttpExchange exchange) → receives an HttpExchange object that contains:
        //the client request (headers, method, body).
        //the response (status code, headers, body).
        public void handle(HttpExchange exchange) throws IOException {

            // Set HTTP response header: "Content-Type: text/plain" (not HTML, JSON, etc.)
            exchange.getResponseHeaders().set("Content-Type", "text/plain");

            //The server sends back an HTTP response with:
            //A status code (e.g., 200 OK, 404 Not Found).

            // Send HTTP response headers:
            // 200 → OK (successful response)
            // 0 → unknown response length, data will be streamed until closed
            exchange.sendResponseHeaders(200, 0);

            // Get the output stream to send data back to the client
            OutputStream os = exchange.getResponseBody();

            // The response body content
            String response = "Hello, this is a simple HTTP server!";

            // Write response as bytes to the output stream
            os.write(response.getBytes());

            // Close the output stream and end the exchange
            os.close();
            exchange.close();
        }
    }
}
*/
 
package Lab6;
import com.sun.net.httpserver.HttpServer;     
import com.sun.net.httpserver.HttpHandler;    
import com.sun.net.httpserver.HttpExchange;   
import java.io.IOException;                   
import java.io.OutputStream;                  
import java.net.InetSocketAddress;            

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/hello", new HelloHandler());
        server.start();
        System.out.println("Server started on port 8080");
    }
    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            String response = "Hello, this is a simple HTTP server!";
            os.write(response.getBytes());
            os.close();
            exchange.close();
        }
    }
}
