package hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

public class Middleware {
  private int portNumber;
  
  public Middleware() {
    this.portNumber = Integer.parseInt(ConfigLoader.props.getProperty("MIDDLEWARE_PORT_NUMBER"));
    ServerSocket middlewareSocket;
    try {
      middlewareSocket = new ServerSocket(portNumber);
      System.out.println("Middleware" +
                         "Address: " + InetAddress.getLocalHost().getHostAddress() + " " +
                         "Port: " + middlewareSocket.getLocalPort());
      while (true) {
        new MiddlewareThread(middlewareSocket.accept()).start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static class MiddlewareThread extends Thread {

    private Socket socket;

    public MiddlewareThread(Socket socket) {
      this.socket = socket;
    }

    public void run() {
      try {
        // Stream Setup
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String filename = in.readLine();
        // HARD CODE Remote Manager 0 for testing
        //int remoteManagerNumber = hashFilename(filename);
        int remoteManagerNumber = 0;
        
        String remoteManagerAddress = RemoteManager.remoteManagerAddress(remoteManagerNumber);
        int remoteManagerPortNumber = RemoteManager.remoteManagerPortNumber(remoteManagerNumber);
        out.println(remoteManagerAddress);
        out.println(remoteManagerPortNumber);
        System.out.println("Routed request for the File " + filename + " to Remote Manager number: " + remoteManagerNumber + " at Address: " + remoteManagerAddress + " Port: " + remoteManagerPortNumber);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  // Returns 0, 1, or 2 depending on which remote manager has the file
  private static int hashFilename(String filename) {
    int total = 0;
    for (char ch : filename.toCharArray()){
      // Convert char to ASCII value then subtract 96
      // so a is 1, b is 2, etc.
      total += (int) ch - 96;
    }
    return total % 3;
  }
  
  public static void main(String[] args) throws Exception {
    new Middleware();
  }
}
