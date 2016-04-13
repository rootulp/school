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
    private String filename;
    private String transferType;
    private Socket middlewareSocket;
    private Socket remoteManagerSocket;
    private Socket serverSocket;

    public ClientThread(String filename, String transferType) {
      this.middlewareAddress = ConfigLoader.props.getProperty("MIDDLEWARE_ADDRESS");
      this.middlewarePort = Integer.parseInt(ConfigLoader.props.getProperty("MIDDLEWARE_PORT_NUMBER"));
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
        String remoteManagerAddress = middlewareIn.readLine();
        int remoteManagerPort = Integer.parseInt(middlewareIn.readLine());
        System.out.println("Routed to Remote Manager at Address: " + remoteManagerAddress + " Port: " + remoteManagerPort);

        // Remote Manager Setup
        remoteManagerSocket = new Socket(remoteManagerAddress, remoteManagerPort);
        BufferedReader remoteManagerIn = new BufferedReader(new InputStreamReader(remoteManagerSocket.getInputStream()));
        PrintWriter remoteManagerOut = new PrintWriter(remoteManagerSocket.getOutputStream(), true);
        
        // Write filename to Remote Manager
        remoteManagerOut.println(filename);
        
        // Get Remote Manager response
        String serverAddress = remoteManagerIn.readLine();
        int serverPort = Integer.parseInt(remoteManagerIn.readLine());
        System.out.println("Routed to Server at Address: " + serverAddress + " Port: " + serverPort);
        
        // Server Setup
        serverSocket = new Socket(serverAddress, serverPort);
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        PrintWriter serverOut = new PrintWriter(serverSocket.getOutputStream(), true);
        
        // Write filename and transfer type to Server
        serverOut.println(filename);
        serverOut.println(transferType);
        
        if (transferType.equals("download")){
          BufferedReader brTest = new BufferedReader(new FileReader(filename));
          FileWriter fw = new FileWriter(filename + "-download");
          PrintWriter writer = new PrintWriter(fw);
          String line;
          while ((line = brTest.readLine()) != null) {
            writer.println(line);  
          } 
          writer.flush();
          writer.close();
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
          remoteManagerSocket.close();
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

    while (true) {
      System.out.println("Please enter the filename: ");
      String filename = keyboard.readLine();
      System.out.println("Please enter 'download' or 'upload': ");
      String transferType = keyboard.readLine();
      new ClientThread(filename, transferType).start();
    }
  }
}
