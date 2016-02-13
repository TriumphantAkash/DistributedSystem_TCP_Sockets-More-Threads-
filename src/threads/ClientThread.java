package threads;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread{
	private String hostIP;
	private int port;
	private Socket socket;

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ClientThread(String hostIP, int port) {
		this.hostIP = hostIP;
		this.port = port;
	}
	
	public void run(){
		//interact with the client here
		try {
			socket = new Socket(hostIP, port);
			//talk to the server now
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
