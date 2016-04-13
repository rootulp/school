package hw5;

public class StartupScript {
  
  int INITIAL_SERVER_PORT_NUMBER = Integer.parseInt(ConfigLoader.props.getProperty("INITIAL_SERVER_PORT_NUMBER"));
  
  private static class StartServerThread extends Thread {
    
    int portNumber;
    int serverNumber;
    
    public StartServerThread(int portNumber, int serverNumber) {
      this.portNumber = portNumber;
      this.serverNumber = serverNumber;
    }
    public void run() {
      try {
        //new Server(portNumber, serverNumber);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  public void startServers() throws Exception {
    for (int serverNumber = 0; serverNumber < 9; serverNumber++) {
      new StartServerThread(INITIAL_SERVER_PORT_NUMBER + serverNumber, serverNumber).start();
    }
  }
  
  public static void main(String[] args) throws Exception {
    StartupScript script = new StartupScript();
    script.startServers();
  }
}
