package hw4;

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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

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
        BufferedReader brTest = new BufferedReader(new FileReader(filename));
        System.out.println("Firstline is : " + brTest.readLine());

        
        
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
          // Setup Download Streams
          InputStream is = socket.getInputStream();
          FileOutputStream fos = new FileOutputStream(filename + "-upload");
          BufferedOutputStream bos = new BufferedOutputStream(fos);

          // Perform Download
          byte[] mybytearray = new byte[1024];
          int bytesRead = is.read(mybytearray, 0, mybytearray.length);
          bos.write(mybytearray, 0, bytesRead);
          bos.close();
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

  // Pass port number as first argument
  public static void main(String[] args) throws Exception {

    // Port and Socket Setup
    int portNumber = Integer.parseInt(args[0]);
    ServerSocket serverSocket = new ServerSocket(portNumber);

    // Print Address and Port
    System.out.println("Server waiting for clients to connect on:");
    System.out.println("Address: " + InetAddress.getLocalHost().getHostAddress());
    System.out.println("Port: " + serverSocket.getLocalPort());
    System.out.println();

    try {
      while (true) {
        new ServerThread(serverSocket.accept()).start();
      }
    } finally {
      serverSocket.close();
    }
  }
}
