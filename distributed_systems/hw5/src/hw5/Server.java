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
import java.util.Timer;
import java.util.TimerTask;

public class Server {
  
  private int serverNumber;
  private int portNumber;

  public Server(int serverNumber) throws Exception {
    this.serverNumber = serverNumber;
    this.portNumber = serverPortNumber(serverNumber);
    ServerSocket serverSocket = new ServerSocket(portNumber);
    System.out.println(serverInfo(serverNumber, serverSocket));
    
    // Sync Servers every 5 seconds
    Timer timer = new Timer();
    timer.schedule(new SyncServerThread(serverNumber), 0, 5000);
    
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
        download(socket, serverNumber, filename);
      } else if (transferType.equals("upload")) {
        upload(socket, serverNumber, filename);
      } else {
        System.out.println("Unrecognized Transfer Type");
      }
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
          out.println(listFiles(serverNumber));
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
  
  private static class SyncServerThread extends TimerTask {
    private int serverNumber;
    public SyncServerThread(int serverNumber) {
      this.serverNumber = serverNumber;
    }
    
    public void run() {
      int otherServer = randomAliveOtherServerNumber(serverNumber);
      System.out.println("Other Server Number: " + otherServer);
      String otherServerAddress = serverAddress(otherServer);
      int otherServerPort = serverPortNumber(otherServer);
      Socket serverSocket;
      try {
        serverSocket = new Socket(otherServerAddress, otherServerPort);
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        PrintWriter serverOut = new PrintWriter(serverSocket.getOutputStream(), true);
        serverOut.println("List files");
        //System.out.println("Response from server: " + serverIn.readLine());
        String files = serverIn.readLine();
        if (!files.isEmpty()) {
          String[] filesList = files.split(" ");
          System.out.println("Files " + files);
          for (String filename: filesList) {
            System.out.println("Trying to download: " + filename);
            serverOut.println(filename);
            serverOut.println("download");
            upload(serverSocket, serverNumber, filename);
          }
        }
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  public static void download(Socket socket, int serverNumber, String filename) {
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
  
  public static void upload(Socket socket, int serverNumber, String filename) {
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
  public static String listFiles(int serverNumber) throws IOException {
    StringBuilder files = new StringBuilder();
    File directory = new File("./");
    File[] fileList = directory.listFiles();
    for (File file : fileList) {
      String fileName = file.getName();
      if (file.isFile() && fileName.startsWith("server" + serverNumber)) {
        files.append(fileName.replace("server" + serverNumber + "-", "") + " ");
      }
    }
    return files.toString();
  }
  
  public static String serverAddress(int serverNumber) {
    return ConfigLoader.props.getProperty("SERVER_" + serverNumber + "_ADDRESS");
  }
  
  public static int serverPortNumber(int serverNumber) {
    return Integer.parseInt(ConfigLoader.props.getProperty("SERVER_" + serverNumber + "_PORT_NUMBER"));
  }
  
  public static int randomAliveOtherServerNumber(int currentServerNumber) {
    int otherServerNumber = RemoteManager.randomAliveServerNumber(currentServerNumber/3);
    while (currentServerNumber == otherServerNumber) {
      otherServerNumber = RemoteManager.randomAliveServerNumber(currentServerNumber/3);
    }
    return otherServerNumber;
  }

  
  public static void main(String[] args) throws Exception {
    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Please Enter Server Number: ");
    int serverNumber = Integer.parseInt(keyboard.readLine());
    new Server(serverNumber);
  }
}
