package hw5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
    private String fileName;
    private String transferType;
    private Socket middlewareSocket;
    private Socket remoteManagerSocket;
    private Socket serverSocket;

    public ClientThread(String fileName, String transferType) {
      this.middlewareAddress = ConfigLoader.props.getProperty("MIDDLEWARE_ADDRESS");
      this.middlewarePort = Integer.parseInt(ConfigLoader.props.getProperty("MIDDLEWARE_PORT_NUMBER"));
      this.fileName = fileName;
      this.transferType = transferType;
    }

    public void run() {
      try {
        // Middleware Setup
        middlewareSocket = new Socket(middlewareAddress, middlewarePort);
        BufferedReader middlewareIn = new BufferedReader(new InputStreamReader(middlewareSocket.getInputStream()));
        PrintWriter middlewareOut = new PrintWriter(middlewareSocket.getOutputStream(), true);

        // Write filename to Middleware
        middlewareOut.println(fileName);

        // Get Middleware response
        String remoteManagerAddress = middlewareIn.readLine();
        int remoteManagerPort = Integer.parseInt(middlewareIn.readLine());
        System.out.println("Routed to Remote Manager at Address: " + remoteManagerAddress + " Port: " + remoteManagerPort);

        // Remote Manager Setup
        remoteManagerSocket = new Socket(remoteManagerAddress, remoteManagerPort);
        BufferedReader remoteManagerIn = new BufferedReader(new InputStreamReader(remoteManagerSocket.getInputStream()));
        PrintWriter remoteManagerOut = new PrintWriter(remoteManagerSocket.getOutputStream(), true);
        
        // Write filename to Remote Manager
        remoteManagerOut.println(fileName);
        
        // Get Remote Manager response
        String serverAddress = remoteManagerIn.readLine();
        int serverPort = Integer.parseInt(remoteManagerIn.readLine());
        System.out.println("Routed to Server at Address: " + serverAddress + " Port: " + serverPort);
        
        // Server Setup
        serverSocket = new Socket(serverAddress, serverPort);
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        PrintWriter serverOut = new PrintWriter(serverSocket.getOutputStream(), true);
        
        // Write filename and transfer type to Server
        serverOut.println(fileName);
        serverOut.println(transferType);
        
        if (transferType.equals("download")) {
          download(serverSocket, fileName);
        } else if (transferType.equals("upload")) {
          upload(serverSocket, fileName);
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
          remoteManagerSocket.close();
          serverSocket.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }
  
  public static void download(Socket serverSocket, String fileName) {
    try {
      // Setup Download Streams
      InputStream is = serverSocket.getInputStream();
      BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName));

      // Perform Download
      byte[] mybytearray = new byte[1024];
      int bytesRead = is.read(mybytearray, 0, mybytearray.length);
      bos.write(mybytearray, 0, bytesRead);
      bos.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void upload(Socket serverSocket, String fileName) {
    try {
      // Setup File
      File myFile = new File(fileName);
      byte[] mybytearray = new byte[(int) myFile.length()];

      // Setup Streams to send file
      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
      OutputStream os = serverSocket.getOutputStream();
      
      // Send File
      bis.read(mybytearray, 0, mybytearray.length);
      os.write(mybytearray, 0, mybytearray.length);
      os.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String argv[]) throws IOException {
    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      System.out.println("Please enter the filename: ");
      String fileName = keyboard.readLine();
      System.out.println("Please enter 'download' or 'upload': ");
      String transferType = keyboard.readLine();
      new ClientThread(fileName, transferType).start();
    }
  }
}
