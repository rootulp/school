package hw2;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MatrixMaxClient {
	
	private static class MatrixMaxClientThread extends Thread {
        
        private String matrixMaxServerAddress;
        private int matrixMaxServerPort;
        private Socket matrixMaxServerSocket;
        
        public MatrixMaxClientThread(String matrixMaxServerAddress, int matrixMaxServerPort) {
            this.matrixMaxServerAddress = matrixMaxServerAddress;
            this.matrixMaxServerPort = matrixMaxServerPort;
        }

        public void run() {
            try {
            	matrixMaxServerSocket = new Socket(matrixMaxServerAddress, matrixMaxServerPort);
            	
            	// Stream Setup
        	    ObjectInputStream ois = new ObjectInputStream(matrixMaxServerSocket.getInputStream());
        	    ObjectOutputStream oos = new ObjectOutputStream(matrixMaxServerSocket.getOutputStream());

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
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                try {
                	matrixMaxServerSocket.close();
                } catch (IOException e) {
                	// TODO Auto-generated catch block
    				e.printStackTrace();
                }
            }
        }
    }
	
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
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	    
	    System.out.println("Please enter the MatrixMaxServer Address: ");
		String matrixMaxServerAddress = keyboard.readLine();
		
		System.out.println("Please enter the MatrixMaxServer Port: ");
		int matrixMaxServerPort = Integer.parseInt(keyboard.readLine());
		
		new MatrixMaxClientThread(matrixMaxServerAddress, matrixMaxServerPort).start();  
	}
}