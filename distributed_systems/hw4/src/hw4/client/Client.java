package hw4.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String argv[]) throws IOException, ClassNotFoundException {

		// Keyboard input
	    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	    
	    System.out.println("Please enter the Middleware Address: ");
		String middlewareAddress = keyboard.readLine();
		
		System.out.println("Please enter the Middleware Port: ");
		int middlewarePort = Integer.parseInt(keyboard.readLine());
	    
		// Socket Setup
		Socket socket = new Socket(middlewareAddress, middlewarePort);
		
		// Stream Setup
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    
		// Send filename
	    System.out.println("Please enter the filename: ");
		String filename = keyboard.readLine();
	    out.println(filename);
	    
	    // Print server number
		String server = in.readLine();
		System.out.println(server);
		
//	    System.out.println("Please enter 'upload' or 'download': ");
//	    String transferType = keyboard.readLine();
//	    oos.writeObject(transferType);
	    
	    // Close Socket
	    socket.close();
	}
}