package threads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread{
	private String hostIP;
	private int port;
	private Socket clientSocket;

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
			clientSocket = new Socket(hostIP, port);
			//talk to the server now
			String message = "find_msg";
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
			outToServer.writeBytes(message+"\n");
			
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
			//System.out.println(inFromServer.readLine());
			if(inFromServer.readLine().equals("ack_msg")){
				//I found my child in the tree
				//I am parent
			}else {
				//the one I am talking to is already in the hierarchy
				//
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
