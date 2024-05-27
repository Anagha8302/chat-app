package com.elitesoftwares.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.elitesoftwares.chatapp.utils.ConfigReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
 public Client(JTextArea textArea) throws UnknownHostException, IOException {
	int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
	 socket=new Socket(ConfigReader.getValue("SERVER_IP"),PORT);
	 out=socket.getOutputStream();
	 in=socket.getInputStream();
	 textArea=this.textArea;
	 readMessages();
//	 System.out.println("Client comes...");
//	 System.out.println("Enter the message send to the server...");
//	 Scanner scanner=new Scanner(System.in);
//	 String message=scanner.nextLine();
//	 OutputStream out=socket.getOutputStream();//write bytes on network
//	 out.write(message.getBytes());
//	 System.out.println("Message sent to the server.");
//	 scanner.close();
//	 out.close();
//	 socket.close();
 }
 public void sendMessage(String message) throws IOException {
	 message=message+"\n";
	 out.write(message.getBytes());
 }
 public void readMessages() {
	 worker=new ClientWorker(in,textArea);
	 worker.start();
 }
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		// TODO Auto-generated method stub
//		Client client=new Client();
//	}

}
