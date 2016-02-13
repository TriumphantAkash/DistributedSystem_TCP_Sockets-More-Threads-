package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import threads.ServerThread;

public class DistributedServer {

	private static ServerSocket serverSocket;
	
	public static ServerSocket getServerSocket() {
		return serverSocket;
	}
	public static void setServerSocket(ServerSocket serverSocket) {
		DistributedServer.serverSocket = serverSocket;
	}
	
	public static void main(String argv[]) throws IOException {
		
		//parse config file here and then extact the relevant information from it 
		try {
			serverSocket = new ServerSocket(6969);
		    
			while(true){             
				Socket clientSocket = serverSocket.accept();
				
				//control comes here whenever a new client is connected to the server
				clientSocket.getLocalAddress();
				clientSocket.getLocalPort();
				
				
//				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//				curr_client.setInputStream(inFromClient);
//				
//				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//				curr_client.setOutputStream(outToClient);
				
				ServerThread serverThread = new ServerThread(clientSocket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			serverSocket.close();
		}
	}
}
