package hw4.middleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

public class Middleware {
	
	// Returns 0, 1, or 2 depending on which server has the file
	private static int hashFilename(String filename) {
	    int total = 0;
		for (char ch : filename.toCharArray()){
			// Convert char to ASCII value then subtract 96
			// so a is 1, b is 2, etc.
			total += (int) ch - 96;
	    }
		return total % 3;
	}

	// Pass port number as first arg
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

	    // Port and Socket Setup
	    int portNumber = Integer.parseInt(args[0]);
	    ServerSocket middlewareSocket = new ServerSocket(portNumber);
	    
	    // Print Address and Port
	    System.out.println("Waiting for clients to connect on:");
	    System.out.println("Host: " + InetAddress.getLocalHost().getHostAddress());
	    System.out.println("Port: " + middlewareSocket.getLocalPort());
	    System.out.println();

	    try {
            while (true) {
                Socket socket = middlewareSocket.accept();
                try {
        			System.out.println("Client request from InetAdress: " + socket.getInetAddress() + " Port: " + socket.getPort());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    String filename = in.readLine();
                    System.out.println("Received request for the file: " + filename);
        			out.println(hashFilename(filename));
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            middlewareSocket.close();
        }   
	}
}