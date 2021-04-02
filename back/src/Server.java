import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
	public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/sbout", new AboutHandler());
        server.setExecutor(null); // creates a default executor
        server.start(); 
    }

    static class RootHandler implements HttpHandler {
    	
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "hello";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    static class AboutHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange t) throws IOException {
			Path filename = Path.of("front/about.html");
			String response = Files.readString(filename);
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
		}
    }
    
    
}
