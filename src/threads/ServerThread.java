package threads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import clientServer.DistributedClientServer;

public class ServerThread extends Thread{
	private Socket clientSocket;
	
	public ServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	public Socket getClientSocket() {
		return clientSocket;
	}
	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	

	public void run(){
		//String message = "hello from server";
		//interact with the client here
		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			//System.out.println(inFromClient.readLine());
			
			if(inFromClient.readLine().equals("find_msg")) {
				if(DistributedClientServer.isNodeIsVergin()){
					//send ack message
					DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
					outToClient.writeBytes("ack_msg");
					//I am child
				} else {
					//send nack message
					DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
					outToClient.writeBytes("nack_msg");
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
