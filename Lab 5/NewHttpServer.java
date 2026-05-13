package Lab6;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;

public class NewHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/hello", new HelloHandler());
        server.createContext("/pdf", new PdfHandler());
        server.createContext("/html", new HtmlHandler());
        

        server.start();
        System.out.println("✅ Server started on port 8080");
        System.out.println("👉 Try:");
        System.out.println("   http://localhost:8080/hello");
        System.out.println("   http://localhost:8080/pdf");
        System.out.println("   http://localhost:8080/html");
    }

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello, this is a simple HTTP server!";
            exchange.getResponseHeaders().set("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class PdfHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            
            Headers h = exchange.getResponseHeaders();
            h.add("Content-Type", "application/pdf");

            File file = new File("C:\\Users\\User\\Downloads\\8th AI Assignment.pdf");

            // if (!file.exists()) {
            //     String error = "PDF file not found!";
            //     t.sendResponseHeaders(404, error.length());
            //     OutputStream os = t.getResponseBody();
            //     os.write(error.getBytes());
            //     os.close();
            //     return;
            // }

            byte[] bytearray = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(bytearray, 0, bytearray.length);
            bis.close();

            exchange.sendResponseHeaders(200, 0);

            OutputStream os = exchange.getResponseBody();
            os.write(bytearray, 0, bytearray.length);
            os.close();
        }
    }

    static class HtmlHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String htmlResponse = """
                <html>
                    <head><title>Simple Page</title></head>
                    <body style="font-family: Arial; text-align:center; margin-top:50px;">
                        <h1>Welcome to My Simple HTTP Server</h1>
                        <p>This is an example HTML response.</p>
                        <a href="/pdf">Click here to view the sample PDF</a>
                    </body>
                </html>
                """;
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, htmlResponse.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(htmlResponse.getBytes());
            os.close();
        }
    }
}
