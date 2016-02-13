package threads;

import java.net.Socket;

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
		//interact with the client thread here
	}
}
