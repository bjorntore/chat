package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;




public class Server extends JFrame {
	ArrayList<Chatroom> chatrooms= new ArrayList<Chatroom>();
	//ArrayList<User> usersConnected= new ArrayList<User>();
	ArrayList<Socket> listOfSockets= new ArrayList<Socket>();
	ArrayList<Thread> listOfThreads= new ArrayList<Thread>(); 
	ArrayList<DataOutputStream> listOfOutStreams= new ArrayList<DataOutputStream>();
	ArrayList<DataInputStream> listOfInStreams= new ArrayList<DataInputStream>();

	public Server(){
		chatrooms.add(new Chatroom("CHATROOM1")); //for testing
		class  ConnectionHandler implements Runnable {
			Socket clientSocket;
			public ConnectionHandler(Socket clientSocket){
				this.clientSocket=clientSocket;
			}
			public void run() {
				try{
					DataInputStream DIS= new DataInputStream(clientSocket.getInputStream());
					DataOutputStream DOS= new DataOutputStream(clientSocket.getOutputStream());
					while(true){
						Message msg= new Message(DIS.readUTF());
						msg.parseMessage();
						
						if(msg.signal.equalsIgnoreCase(Signal.JOIN.toString())){ //ser om det blir bedt om å joine en chat av socketen
							for(int i=0;i<chatrooms.size();i++){
								
								if(chatrooms.get(i).name.equalsIgnoreCase(msg.chatroom)){ //joine chatroom
									chatrooms.get(i).addUserToChatroom(DOS);
								}
							}
						}
						if(msg.signal.equalsIgnoreCase(Signal.SEND.toString())){      //sjekker om socketen sender en mld, 
							chatrooms.get(findChatroom(msg.chatroom)).writeToChatroom(msg.from+": "+msg.message); //finner chatroomet som beskjeden skal til
						}
					}
				}catch(IOException IOE){};
				//TODO something
			}
			}
		System.out.println("Server up and running");
		show();										//midlertidig til vi har ordna skikkeli serverGUI
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try{
			ServerSocket serverSocket= new ServerSocket(10823);

			while(true){
				if(serverSocket.isBound()){					
					listOfSockets.add(serverSocket.accept());
					listOfThreads.add(new Thread(new ConnectionHandler(listOfSockets.get(listOfSockets.size()-1))));
					listOfThreads.get(listOfThreads.size()-1).start();
				}
			}
		}
		catch(IOException ex){System.err.println(ex);}
	}
	
	public int findChatroom(String name){
		for(int i=0; i<chatrooms.size();i++){
			if(chatrooms.get(i).name.equalsIgnoreCase(name))
			return i;
		}
		return -1;
	}


	public static void main(String[] args) {
		Server server=new Server();




	}
	public enum Signal{  //kom ikke på fler;P
		JOIN,LEAVE, SEND, 
	}
}
