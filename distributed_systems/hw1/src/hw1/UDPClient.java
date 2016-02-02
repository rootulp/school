package hw1;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// Pass 'localhost' as first arg to UDPClient
public class UDPClient {
	public static void main(String argv[]) throws IOException {
		String name;
		int number;
		String line;
		DatagramSocket clientSocket = new DatagramSocket();	 
		InetAddress address = InetAddress.getByName(argv[0]);
		
		// Get the player's name
		System.out.println("Please Enter Your Name: ");
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		name = keyboard.readLine();
		
		// Send the name to the server
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream (baos);
		dos.writeUTF (name);
		byte[] buf= baos.toByteArray();
		DatagramPacket packet = new DatagramPacket(buf,buf.length, address, 4160);
		clientSocket.send(packet);
		
		// Get response from server
		byte[] rbuf = new byte[256];	
		packet = new DatagramPacket(rbuf,rbuf.length);
		clientSocket.receive(packet);
		ByteArrayInputStream bin = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
		BufferedReader reader = new BufferedReader(new InputStreamReader(bin));
		line = reader.readLine();
		System.out.println (line);
		
		// Start Game
		line = "Incorrect"; // BUGBUG initialize response as incorrect
		while (!line.equals("YOU WIN!!!")) {
			
			// Send guess to client
			keyboard = new BufferedReader(new InputStreamReader(System.in));
			number = Integer.parseInt(keyboard.readLine());
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream (baos);
			dos.writeInt (number);		
			buf = baos.toByteArray();
			packet = new DatagramPacket(buf,buf.length, address, 4160);
			clientSocket.send(packet);
			
			// Receive response
			rbuf = new byte[256];	
			packet = new DatagramPacket(rbuf, rbuf.length);
			clientSocket.receive(packet);
			bin = new ByteArrayInputStream (packet.getData(), 0, packet.getLength());
			reader = new BufferedReader (new InputStreamReader (bin));
			line = reader.readLine();
			System.out.println (line);
		}
		
		// End Game
		clientSocket.close();
		System.exit(0);
	}
}