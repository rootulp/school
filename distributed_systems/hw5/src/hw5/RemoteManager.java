package hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

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
        new RemoteManagerThread(remoteManager.accept()).start();
      }
    } finally {
      remoteManager.close();
    }
  }
  
  private static class RemoteManagerThread extends Thread {

    private Socket socket;

    public RemoteManagerThread(Socket socket) {
      this.socket = socket;
    }

    public void run() {
      try {
        // Stream Setup
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String filename = in.readLine();
        
        // HARD CODE SERVER 0 for testing
        int serverNumber = 0;
        String serverAddress = ConfigLoader.props.getProperty("SERVER_" + serverNumber + "_ADDRESS");
        int serverPortNumber = Integer.parseInt(ConfigLoader.props.getProperty("SERVER_" + serverNumber + "_PORT_NUMBER"));
        
        out.println(serverAddress);
        out.println(serverPortNumber);
        System.out.println("Routed request for the File " + filename + " to server number: " + serverNumber + " at Address: " + serverAddress + " Port: " + serverPortNumber);
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

  // Make shift load balancing alg
  // Returns 0, 1, or 2 depending on ordinal value of first char
  private static int hashFilename(String filename) {
    return (int) filename.toCharArray()[0] % 3;
  }
  
  public static void main(String[] args) throws Exception {
    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Please Enter Remote Manager Number: ");
    int remoteManagerNumber = Integer.parseInt(keyboard.readLine());
    new RemoteManager(remoteManagerNumber);
  }
}
