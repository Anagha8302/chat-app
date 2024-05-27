package com.elitesoftwares.chatapp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.elitesoftwares.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker>workers=new ArrayList<>();
	public Server() throws IOException {
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
		System.out.println("Server starts and waiting for the clients to join...");
		handleClientRequest();
	}
	//Multiple client handshaking
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket=serverSocket.accept();//handshaking
			//per client per thread
			ServerWorker serverWorker=new ServerWorker(clientSocket,this);//creating new worker/thread
			workers.add(serverWorker);
			serverWorker.start();
			}	
	}
	//for single client
	/*
	public Server() throws IOException {
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
		System.out.println("Sever started and waiting for the client connection...");
		Socket socket=serverSocket.accept();//handshaking
		System.out.println("Client joins the Server");
		InputStream in=socket.getInputStream();//read bytes from the network
		byte arr[]=in.readAllBytes();
		String str=new String(arr);//bytes converts to string
		System.out.println("Message recv from the client "+str);
		in.close();
		socket.close();
		}
		*/
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server=new Server();
	}

}
