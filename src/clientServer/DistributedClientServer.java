package clientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import models.Node;
import threads.ClientThread;
import threads.ServerThread;

public class DistributedClientServer {

	private static ServerSocket serverSocket;
	public static boolean nodeIsVergin = true;	//to make sure client threads are formed only once
	private static Node node;
	
	
	
	public static boolean isNodeIsVergin() {
		return nodeIsVergin;
	}

	public static void setNodeIsVergin(boolean nodeIsVergin) {
		DistributedClientServer.nodeIsVergin = nodeIsVergin;
	}

	public static Node getNode() {
		return node;
	}

	public static void setNode(Node node) {
		DistributedClientServer.node = node;
	}

	public static ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	public static void setServerSocket(ServerSocket serverSocket) {
		DistributedClientServer.serverSocket = serverSocket;
	}
	
	public static void main(String argv[]) throws IOException {
		
		/*
		 * parse the config file (passed in command line argument) here and initialize 'node' variable with the corresponding values for this particular node
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		try {
			//pass the port for this particular node where the code is being deployed
			serverSocket = new ServerSocket(6969);
		    
			while(true){
				//this condition is specifically for the root node
				//it means that client threads for root node are deployed first and then the server thread
				//and vice versa in case of other nodes
				
				//Also, make sure you deploy the code on root node at last so that client threads of the root node have other nodes' server threads to connect to
				
				if(node.isRoot() && nodeIsVergin) {
					for(Node node:node.getNeighbours()){
						ClientThread clientThread = new ClientThread(node.getHostName(), node.getPort());
						clientThread.start();
					}
				}
				
				Socket clientSocket = serverSocket.accept();
				
				//control comes here whenever a new client is connected to the server
				clientSocket.getLocalAddress();
				clientSocket.getLocalPort();
				
				ServerThread serverThread = new ServerThread(clientSocket);
				serverThread.start();
				
				
				if(!node.isRoot() && nodeIsVergin) {
					//create clientThreads for all the neighbors
					for(Node node:node.getNeighbours()){
						ClientThread clientThread = new ClientThread(node.getHostName(), node.getPort());
						clientThread.start();
					}
					
					//current node loses its virginity i.e has invoked its client threads
					nodeIsVergin = false;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			serverSocket.close();
		}
	}
}
