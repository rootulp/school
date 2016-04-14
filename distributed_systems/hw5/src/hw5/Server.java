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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server {
  
  private int serverNumber;
  private int portNumber;

  public Server(int serverNumber) throws Exception {
    this.serverNumber = serverNumber;
    this.portNumber = Integer.parseInt(ConfigLoader.props.getProperty("SERVER_" + serverNumber + "_PORT_NUMBER"));
    ServerSocket serverSocket = new ServerSocket(portNumber);
    System.out.println(serverInfo(serverNumber, serverSocket));
    
    try {
      while (true) {
        new ServerThread(serverSocket.accept(), serverNumber).start();
      }
    } finally {
      serverSocket.close();
    }
  }
  
  private String serverInfo(int serverNumber, ServerSocket serverSocket) {
    try {
      return ("Server #" + serverNumber + " " +
              "Address: " + InetAddress.getLocalHost().getHostAddress() + " " +
              "Port: " + serverSocket.getLocalPort());
    } catch (UnknownHostException e) {
      // HUGE BUG - masking error output
      //e.printStackTrace();
      return ("Invalid Server Info");
    }
  }
  
  private static class ServerThread extends Thread {

    private Socket socket;
    private int serverNumber;

    public ServerThread(Socket socket, int serverNumber) {
        this.socket = socket;
        this.serverNumber = serverNumber;
    }
    
    public void routeFileRequest(String filename, String transferType) {
      // Print file (input) and transferType
      System.out.println("Received request to " + transferType + " the File " + filename);
      if (transferType.equals("download")) {
        download(filename);
      } else if (transferType.equals("upload")) {
        upload(filename);
      } else {
        System.out.println("Unrecognized Transfer Type");
      }
    }
    
    public void download(String filename) {
      try {
        // Setup File
        File myFile = new File("server" + serverNumber + "-" + filename);
        byte[] mybytearray = new byte[(int) myFile.length()];
        // Setup Streams to send file
        BufferedInputStream bis;
        bis = new BufferedInputStream(new FileInputStream(myFile));
        OutputStream os = socket.getOutputStream();
        // Send File
        bis.read(mybytearray, 0, mybytearray.length);
        os.write(mybytearray, 0, mybytearray.length);
        os.flush();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    public void upload(String filename) {
      try {
        BufferedReader brTest = new BufferedReader(new FileReader(filename));
        FileWriter fw = new FileWriter("server" + serverNumber + "-" + filename);
        PrintWriter writer = new PrintWriter(fw);
        String line;
        while ((line = brTest.readLine()) != null) {
          writer.println(line);  
        } 
        writer.flush();
        writer.close();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    // Should be serializing an object but instead 
    // passing this as one long string delimited by spaces
    public String listFiles() throws IOException {
      StringBuilder files = new StringBuilder();
      File directory = new File("./");
      File[] fileList = directory.listFiles();
      for (File file : fileList){
        if (file.isFile() && file.getName().startsWith("server" + serverNumber)) {
          files.append(file.getName() + " ");
        }
      }
      return files.toString();
    }

    public void run() {
      try {
        // Stream Setup
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String input = in.readLine();
        
        if (input.equals("Is server alive?")) {
          out.println("Server is alive!");
        } else if (input.equals("List files")) {
          out.println(listFiles());
        } else {
          String transferType = in.readLine();
          routeFileRequest(input, transferType);
        }
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
    System.out.println("Please Enter Server Number: ");
    int serverNumber = Integer.parseInt(keyboard.readLine());
    new Server(serverNumber);
  }
}
