package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;




public class Server extends JFrame {
	ArrayList<Chatroom> chatrooms= new ArrayList<Chatroom>();
	//ArrayList<User> usersConnected= new ArrayList<User>();
	ArrayList<Socket> listOfSockets= new ArrayList<Socket>();
	ArrayList<Thread> listOfThreads= new ArrayList<Thread>(); 
	ArrayList<ObjectOutputStream> listOfOutStreams= new ArrayList<ObjectOutputStream>();
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
					InputStream is = clientSocket.getInputStream();   
					ObjectInputStream ois = new ObjectInputStream(is); 
					OutputStream os= clientSocket.getOutputStream();
					ObjectOutputStream oos=new ObjectOutputStream(os);
					
					while(true){
						Message	msg = (Message)ois.readObject(); 
						
						if(msg.signal.equalsIgnoreCase("CONNECT")){
							listOfOutStreams.add(oos);
						}
						if(msg.signal.equalsIgnoreCase("JOIN")){
							findChatroom(msg.chatroom).addUserToChatroom(oos);
						}
						if(msg.signal.equalsIgnoreCase("SEND")){
							findChatroom(msg.chatroom).writeToChatroom(msg);
						}
					}
				}catch(IOException IOE){} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
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
		} catch(IOException ex){System.err.println(ex);}
	}

	public Chatroom findChatroom(String name){
		for(int i=0; i<chatrooms.size();i++){
			if(chatrooms.get(i).name.equalsIgnoreCase(name)) return chatrooms.get(i);
		}
		return null;
	}


	public static void main(String[] args) {
		Server server=new Server();
	}
	public enum Signal{
		JOIN,LEAVE, SEND, 
	}
}
