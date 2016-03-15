package hw2;

import java.io.*;
import java.net.*;
import java.util.Random;

public class MatrixMaxServer {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// Matrix Size
		int MATRIX_SIZE = 4;
		
		// Create matrixMax
		MatrixMax matrixMax = new MatrixMax(MATRIX_SIZE);
		
		// Print Matrix
		matrixMax.print();
		
		// Socket Setup
		ServerSocket serverSocket;
	    Socket clientSocket;
	    int portNumber = 1777;
	    serverSocket = new ServerSocket(portNumber);
	    System.out.println("Waiting for clients to connect on port: " + portNumber);

	    
	    int clientNum = 0;
		while (clientNum < MATRIX_SIZE) {
			clientSocket = serverSocket.accept();
			System.out.println("Client working on row: " + clientNum);
			
			// Setup Streams
			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

			// Write Client Num (also used as Row Num) and matrixMax
			oos.writeInt(clientNum);
			oos.writeObject(matrixMax);
			
			// Write Max to Row Maxes
			// Super janky - need to stop converting between int and string
			// Also currently catching end of file exception and masking output
			try {
				String rowMax;
				while ((rowMax = (String) ois.readObject()) != null) {
					System.out.println("Row: " + clientNum + " max: " + rowMax);
					matrixMax.rowMaxes[clientNum] = Integer.parseInt(rowMax);
				}
			} catch (Exception e) { // End of file exception
				// Close Streams
				ois.close();
				oos.close();
			}
			
			// Close Socket
			clientSocket.close();
			
			// Increment Client Num BUGBUG this is used for rowNum
			clientNum += 1;
		}
		
		// Print Row Maxes
		System.out.println("Row Maxes:");
		matrixMax.printRow(matrixMax.rowMaxes);
		System.out.println();
				
		// Print Matrix Max
		System.out.println("Matrix Max:");
		System.out.println(matrixMax.findMax(matrixMax.rowMaxes));
		System.out.println();
		
		// Close Server Socket
		serverSocket.close();
	}
}