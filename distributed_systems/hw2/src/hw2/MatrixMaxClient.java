package hw2;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class MatrixMaxClient {
	
	private static int findMax(int[] row) {
		int max = row[0];
		for (int i = 0; i < row.length; i++) {
		    if (row[i] > max) {
		      max = row[i];
		    }
		}
		return max;
	}
	
	public static void main(String argv[]) throws IOException, ClassNotFoundException {
		
		// Socket Setup
	    Socket socket;
	    int portNumber = 1777;
	    socket = new Socket(InetAddress.getLocalHost(), portNumber);
	    
	    // Stream Setup
	    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

	    // Read rowNum and matrixMax
	    int rowNum = ois.readInt();
	    MatrixMax matrixMax = (MatrixMax) ois.readObject();
	    
	    // Print rowNum and matrix
	    System.out.println("Row num: " + rowNum);
	    System.out.println();
	    matrixMax.print();
	    
	    int maxForRow = findMax(matrixMax.getRow(rowNum));
	    
	    // Print max
	    System.out.println("Max: " + maxForRow);
	    
	    oos.writeObject(Integer.toString(maxForRow));
	    
	    // Close Streams
	    ois.close();
	    oos.close();
	    
	    // Close Socket
	    socket.close();
	}
}