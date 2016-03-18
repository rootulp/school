package hw4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

  private static class ClientThread extends Thread {

    private String middlewareAddress;
    private int middlewarePort;
    private String filename;
    private String transferType;
    private Socket middlewareSocket;
    private Socket serverSocket;

    public ClientThread(String middlewareAddress, int middlewarePort, String filename, String transferType) {
      this.middlewareAddress = middlewareAddress;
      this.middlewarePort = middlewarePort;
      this.filename = filename;
      this.transferType = transferType;
    }

    public void run() {
      try {
        // Middleware Setup
        middlewareSocket = new Socket(middlewareAddress, middlewarePort);
        BufferedReader middlewareIn = new BufferedReader(new InputStreamReader(middlewareSocket.getInputStream()));
        PrintWriter middlewareOut = new PrintWriter(middlewareSocket.getOutputStream(), true);

        // Write filename to Middleware
        middlewareOut.println(filename);

        // Get Middleware response
        String serverAddress = middlewareIn.readLine();
        int serverPort = Integer.parseInt(middlewareIn.readLine());
        System.out.println("File " + filename + " is located at Server Address: " + serverAddress + " Port: " + serverPort);

        // Server Setup
        serverSocket = new Socket(serverAddress, serverPort);
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        PrintWriter serverOut = new PrintWriter(serverSocket.getOutputStream(), true);
        
        // Write filename and transfer type to Middleware
        serverOut.println(filename);
        serverOut.println(transferType);
        
        if (transferType.equals("download")){
          // Setup Download Streams
          InputStream is = serverSocket.getInputStream();
          FileOutputStream fos = new FileOutputStream(filename + "-download");
          BufferedOutputStream bos = new BufferedOutputStream(fos);

          // Perform Download
          byte[] mybytearray = new byte[1024];
          int bytesRead = is.read(mybytearray, 0, mybytearray.length);
          bos.write(mybytearray, 0, bytesRead);
          bos.close();
        } else if (transferType.equals("upload")){
          // Setup File
          File myFile = new File(filename);
          byte[] mybytearray = new byte[(int) myFile.length()];

          // Setup Streams to send file
          BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
          OutputStream os = serverSocket.getOutputStream();
          
          // Send File
          bis.read(mybytearray, 0, mybytearray.length);
          os.write(mybytearray, 0, mybytearray.length);
          os.flush();
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
          middlewareSocket.close();
          serverSocket.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String argv[]) throws IOException {

    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Please enter the Middleware Address: ");
    String middlewareAddress = keyboard.readLine();

    System.out.println("Please enter the Middleware Port: ");
    int middlewarePort = Integer.parseInt(keyboard.readLine());

    while (true) {
      System.out.println("Please enter the filename: ");
      String filename = keyboard.readLine();
      System.out.println("Please enter 'download' or 'upload': ");
      String transferType = keyboard.readLine();
      new ClientThread(middlewareAddress, middlewarePort, filename, transferType).start();
    }
  }
}
