package hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class RemoteManager {
  private int portNumber;
  private int remoteManagerNumber;
   
  public RemoteManager(int remoteManagerNumber) throws Exception {
    this.remoteManagerNumber = remoteManagerNumber;
    this.portNumber = Integer.parseInt(ConfigLoader.props.getProperty("REMOTE_MANAGER_" + remoteManagerNumber + "_PORT_NUMBER"));
    
    ServerSocket remoteManager = new ServerSocket(portNumber);

    // Print Address and Port
    System.out.println("Remote Manager #" + remoteManagerNumber + " " +
                       "Address: " + InetAddress.getLocalHost().getHostAddress() + " " +
                       "Port: " + remoteManager.getLocalPort());
    try {
      while (true) {
        new RemoteManagerThread(remoteManager.accept(), remoteManagerNumber).start();
      }
    } finally {
      remoteManager.close();
    }
  }
  
  public static boolean isServerAlive(int serverNumber) {
    String serverAddress = ConfigLoader.props.getProperty("SERVER_" + serverNumber + "_ADDRESS");
    int serverPortNumber = Integer.parseInt(ConfigLoader.props.getProperty("SERVER_" + serverNumber + "_PORT_NUMBER"));
    Socket serverSocket;
    try {
      serverSocket = new Socket(serverAddress, serverPortNumber);
      BufferedReader serverIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
      PrintWriter serverOut = new PrintWriter(serverSocket.getOutputStream(), true);
      serverOut.println("Is server alive?");
      //System.out.println("Is server alive??");
      String response = serverIn.readLine();
      return response.equals("Server is alive!");
    } catch (Exception e){
      // HUGE BUG - MASKING error output
      //e.printStackTrace();
      return false;
    }
  }
  
  // If this is Remote Manager 0: returns 0, 1, or 2
  // If this is Remote Manager 1: returns 3, 4, or 5
  // If this is Remote Manager 2: returns 6, 7, or 8
  public static int randomServerNumber(int remoteManagerNumber) {
    int min = 3 * remoteManagerNumber;
    int max = min + 2;
    Random random = new Random();
    return random.nextInt(max - min + 1) + min;
  }
  
  private static class RemoteManagerThread extends Thread {

    private Socket socket;
    private int remoteManagerNumber;

    public RemoteManagerThread(Socket socket, int remoteManagerNumber) {
      this.socket = socket;
      this.remoteManagerNumber = remoteManagerNumber;
    }

    public void run() {
      try {
        // Stream Setup
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String filename = in.readLine();
        
        //int serverNumber = randomServerNumber(remoteManagerNumber);
        // HARD CODE SERVER 0 for testing
        int serverNumber = 0;
        while (!isServerAlive(serverNumber)) {
          serverNumber = randomServerNumber(remoteManagerNumber);
        }
        
        // Route request to a server that is alive
        String serverAddress = ConfigLoader.props.getProperty("SERVER_" + serverNumber + "_ADDRESS");
        int serverPortNumber = Integer.parseInt(ConfigLoader.props.getProperty("SERVER_" + serverNumber + "_PORT_NUMBER"));
        out.println(serverAddress);
        out.println(serverPortNumber);
        System.out.println("Routed request for the File " + filename + " to server number: " + serverNumber + " at Address: " + serverAddress + " Port: " + serverPortNumber);
      } catch (Exception e) {
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
  
  public static void main(String[] args) throws Exception {
    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Please Enter Remote Manager Number: ");
    int remoteManagerNumber = Integer.parseInt(keyboard.readLine());
    new RemoteManager(remoteManagerNumber);
  }
}
