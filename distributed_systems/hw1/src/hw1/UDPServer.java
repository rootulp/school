package hw1;

import java.io.*;
import java.net.*;
import java.util.Random;

public class UDPServer {
	public static void main(String[] args) throws IOException {
		
		// Generate randomInt between 1 and 10
		int randomInt = randInt(1, 10);
		
		// Server setup
		DatagramSocket serverSocket = new DatagramSocket(4160);
		byte[] rbuf = new byte[256];
		DatagramPacket packet = new DatagramPacket(rbuf, rbuf.length);		
		serverSocket.receive(packet);
		ByteArrayInputStream bin = new ByteArrayInputStream(rbuf, 0, packet.getLength());
		DataInputStream dis = new DataInputStream(bin);
		String playerName = dis.readUTF();
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		
		// Send response
		String line = "Hello " + playerName + ", time to play the GUESSING GAME. " + 
					  playerName + ", please enter a number between 1 and 10.";
		byte[] sbuf = new byte[256];
		sbuf = line.getBytes();
		packet = new DatagramPacket(sbuf, sbuf.length, address, port);
		serverSocket.send(packet);
		
//		// Start the game
//		int playerGuess = 0; // BUGBUG initialize playerGuess to an incorrect guess
//		while (playerGuess != randomInt) {
			
		// Start Game
		line = "Incorrect"; // BUGBUG initialize response as incorrect
		while (!line.equals("YOU WIN!!!")) {
			rbuf = new byte[256];
			packet = new DatagramPacket(rbuf, rbuf.length);
			serverSocket.receive(packet);
			bin = new ByteArrayInputStream(rbuf, 0, packet.getLength());
			dis = new DataInputStream(bin);
			int i = dis.readInt();
			if (i < randomInt) {
				line = "Too Small, Guess Again";	
			} else if (i > randomInt) {
				line = "Too Big, Guess Again";	
			} else {
				line = "YOU WIN!!!";
			}
			sbuf = line.getBytes();
			packet = new DatagramPacket(sbuf, sbuf.length, address, port);
			serverSocket.send(packet);
		}
		
		// End Game
		serverSocket.close();
		System.exit(0);
	}
	
	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>. Use this for a random number between
	 * 1 and 10 for your Hi Low game
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}	
}