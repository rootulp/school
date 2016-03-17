package hw4.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private static class ClientThread extends Thread {
        
        private String middlewareAddress;
        private int middlewarePort;
        private String filename;
        private String transferType;
        private Socket MiddlewareSocket;
        private Socket ServerSocket;
        
        public ClientThread(String middlewareAddress, int middlewarePort, String filename, String transferType) {
            this.middlewareAddress = middlewareAddress;
            this.middlewarePort = middlewarePort;
            this.filename = filename;
            this.transferType = transferType;
        }

        public void run() {
            try {
            	// Middleware Setup
            	MiddlewareSocket = new Socket(middlewareAddress, middlewarePort);
        		BufferedReader middlewareIn = new BufferedReader(new InputStreamReader(MiddlewareSocket.getInputStream()));
        		PrintWriter middlewareOut = new PrintWriter(MiddlewareSocket.getOutputStream(), true);
        	    
        		// Write filename to Middleware
        	    middlewareOut.println(filename);
        	    
        	    // Get Middleware response
        		String serverAddress = middlewareIn.readLine();
        		int serverPort = Integer.parseInt(middlewareIn.readLine());
        		System.out.println("File " + filename + " is located at Server Address: " + serverAddress + " Port: " + serverPort);
        		
        		// Server Setup
        		ServerSocket = new Socket(serverAddress, serverPort);
        		BufferedReader serverIn = new BufferedReader(new InputStreamReader(MiddlewareSocket.getInputStream()));
        		PrintWriter serverOut = new PrintWriter(MiddlewareSocket.getOutputStream(), true);
               
            } catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                try {
                	MiddlewareSocket.close();
                	ServerSocket.close();
                } catch (IOException e) {
                	// TODO Auto-generated catch block
    				e.printStackTrace();
                }
            }
        }
    }
	
	public static void main(String argv[]) throws IOException {

	    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	    
	    System.out.println("Please enter the Middleware Address: ");
		String middlewareAddress = keyboard.readLine();
		
		System.out.println("Please enter the Middleware Port: ");
		int middlewarePort = Integer.parseInt(keyboard.readLine());
			    
		while (true) {
			System.out.println("Please enter the filename: ");
			String filename = keyboard.readLine();
			System.out.println("Please enter 'download' or 'upload': ");
			String transferType = keyboard.readLine();
            new ClientThread(middlewareAddress, middlewarePort, filename, transferType).start();
        }
	}
}