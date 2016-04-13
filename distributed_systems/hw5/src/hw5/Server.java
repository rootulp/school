package hw5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
  
  private int serverNumber;
  private int portNumber;
  
  public Server(int serverNumber) throws Exception {
    this.serverNumber = serverNumber;
    this.portNumber = Integer.parseInt(ConfigLoader.props.getProperty("SERVER_" + serverNumber + "_PORT_NUMBER"));
    
    ServerSocket serverSocket = new ServerSocket(portNumber);

    // Print Address and Port
    System.out.println("Server #" + serverNumber + " " +
                       "Address: " + InetAddress.getLocalHost().getHostAddress() + " " +
                       "Port: " + serverSocket.getLocalPort());
    try {
      while (true) {
        new ServerThread(serverSocket.accept()).start();
      }
    } finally {
      serverSocket.close();
    }
  }

  private static class ServerThread extends Thread {

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
      try {
        // Stream Setup
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String filename = in.readLine();
        String transferType = in.readLine();
        
        System.out.println("Received request to " + transferType + " the File " + filename);
        
        if (transferType.equals("download")){
          // Setup File
          File myFile = new File(filename);
          byte[] mybytearray = new byte[(int) myFile.length()];

          // Setup Streams to send file
          BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
          OutputStream os = socket.getOutputStream();
          
          // Send File
          bis.read(mybytearray, 0, mybytearray.length);
          os.write(mybytearray, 0, mybytearray.length);
          os.flush();
        } else if (transferType.equals("upload")){
          BufferedReader brTest = new BufferedReader(new FileReader(filename));
          FileWriter fw = new FileWriter(filename + "-upload");
          PrintWriter writer = new PrintWriter(fw);
          String line;
          while ((line = brTest.readLine()) != null) {
            writer.println(line);  
          } 
          writer.flush();
          writer.close();
        } else {
          System.out.println("Unrecognized Transfer Type");
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
  
  public static void main(String[] args) throws Exception {
    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Please Enter Server Number: ");
    int serverNumber = Integer.parseInt(keyboard.readLine());
    new Server(serverNumber);
  }
}
