package hw4.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private static class ClientThread extends Thread {
        
        private String middlewareAddress;
        private int middlewarePort;
        private String filename;
        private String transferType;
        private Socket middlewareSocket;
        private Socket serverSocket;
        
        public ClientThread(String middlewareAddress, int middlewarePort, String filename, String transferType) {
            this.middlewareAddress = middlewareAddress;
            this.middlewarePort = middlewarePort;
            this.filename = filename;
            this.transferType = transferType;
        }

        public void run() {
            try {
            	// Middleware Setup
            	middlewareSocket = new Socket(middlewareAddress, middlewarePort);
        		BufferedReader middlewareIn = new BufferedReader(new InputStreamReader(middlewareSocket.getInputStream()));
        		PrintWriter middlewareOut = new PrintWriter(middlewareSocket.getOutputStream(), true);
        	    
        		// Write filename to Middleware
        	    middlewareOut.println(filename);
        	    
        	    // Get Middleware response
        		String serverAddress = middlewareIn.readLine();
        		int serverPort = Integer.parseInt(middlewareIn.readLine());
        		System.out.println("File " + filename + " is located at Server Address: " + serverAddress + " Port: " + serverPort);
        		
        		// Server Setup
        		serverSocket = new Socket(serverAddress, serverPort);
               
            } catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                try {
                	middlewareSocket.close();
                	serverSocket.close();
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