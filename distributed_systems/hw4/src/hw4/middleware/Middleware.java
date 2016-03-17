package hw4.middleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;

public class Middleware {
	
	private static class MiddlewareThread extends Thread {
        
		private Socket socket;
		private String[] serverAddresses;
		private int[] serverPorts;
        
        public MiddlewareThread(Socket socket, String[] serverAddresses, int[] serverPorts) {
            this.socket = socket;
            this.serverAddresses = serverAddresses;
            this.serverPorts = serverPorts;
        }

        public void run() {
            try {        		
        		// Stream Setup
        		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        	            	   
        		String filename = in.readLine();
        		int serverNumber = hashFilename(filename);
    			String serverAddress = serverAddresses[serverNumber];
    			int serverPort = serverPorts[serverNumber];
    			out.println(serverAddress);
    			out.println(serverPort);
                System.out.println("Routed request for the File " + filename + " to server number: " + serverNumber + " at Address: " + serverAddress + " Port: " + serverPort);
            } catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                try {
                    socket.close();
                } catch (IOException e) {
                	// TODO Auto-generated catch block
    				e.printStackTrace();
                }
            }
        }
    }

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

	// Pass port number as first argument
	public static void main(String[] args) throws Exception {

	    // Port and Socket Setup
	    int portNumber = Integer.parseInt(args[0]);
	    ServerSocket middlewareSocket = new ServerSocket(portNumber);
	    
	    // Initial Server Setup
	    // Sketchy that I'm storing this info in two separate arrays
	    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	    String[] serverAddresses = new String[3];
	    int[] serverPorts = new int[3];
	    
	    for (int serverNum = 0; serverNum <= 2; serverNum++) {
	    	System.out.println("Please enter the Address for Server: " + serverNum);
			serverAddresses[serverNum] = keyboard.readLine();
			
			System.out.println("Please enter the Port for Server: " + serverNum);
			serverPorts[serverNum] = Integer.parseInt(keyboard.readLine());
	    }
	    
	    // Print Address and Port
	    System.out.println("Middleware waiting for clients to connect on:");
	    System.out.println("Address: " + InetAddress.getLocalHost().getHostAddress());
	    System.out.println("Port: " + middlewareSocket.getLocalPort());
	    System.out.println();

	    try {
            while (true) {
                new MiddlewareThread(middlewareSocket.accept(), serverAddresses, serverPorts).start();
            }
        } finally {
            middlewareSocket.close();
        }
	}
}