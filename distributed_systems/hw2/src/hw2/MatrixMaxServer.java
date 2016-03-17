package hw2;

import java.io.*;
import java.net.*;

public class MatrixMaxServer {

  private static class MatrixMaxThread extends Thread {

    private Socket socket;
    private int clientNum;
    private MatrixMax matrixMax;

        public MatrixMaxThread(Socket socket, MatrixMax matrixMax, int clientNum) {
            this.socket = socket;
            this.matrixMax = matrixMax;
            this.clientNum = clientNum;
        }

        public void run() {
            try {

          System.out.println("Client working on row: " + clientNum);

          // Setup Streams
          ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
          ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

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

  // Pass port number as first argument
  public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    // Matrix Size
    int MATRIX_SIZE = 4;

    // Create matrixMax
    MatrixMax matrixMax = new MatrixMax(MATRIX_SIZE);

    // Print Matrix
    matrixMax.print();

    // Port and Socket Setup
      int portNumber = Integer.parseInt(args[0]);
      ServerSocket serverSocket = new ServerSocket(portNumber);

      // Print Address and Port
      System.out.println("Matrix Max Server waiting for clients to connect on:");
      System.out.println("Address: " + InetAddress.getLocalHost().getHostAddress());
      System.out.println("Port: " + serverSocket.getLocalPort());
      System.out.println();

      try {
        int clientNum = 0;
      while (clientNum < MATRIX_SIZE) {
                new MatrixMaxThread(serverSocket.accept(), matrixMax, clientNum).start();
          // Increment Client Num BUGBUG this is used for rowNum
          clientNum += 1;
            }
        } finally {
            serverSocket.close();
        }

    // Print Row Maxes
    System.out.println("Row Maxes:");
    MatrixMax.printRow(matrixMax.rowMaxes);
    System.out.println();

    // Print Matrix Max
    System.out.println("Matrix Max:");
    System.out.println(MatrixMax.findMax(matrixMax.rowMaxes));
    System.out.println();

    // Close Server Socket
    serverSocket.close();
  }
}
