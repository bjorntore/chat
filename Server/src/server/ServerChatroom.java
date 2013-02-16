package server;

import ChatLogic.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServerChatroom {
	ArrayList<ObjectOutputStream> listOfOutStreams= new ArrayList<>();
	String name;
	
	public void writeToServerChatroom(Message msg){
		
		for(int i=0;i<listOfOutStreams.size();i++){
			try {
				listOfOutStreams.get(i).writeObject(msg);
			} catch (IOException e) {
				listOfOutStreams.remove(i);			//klarer den ikke å sende til en enhet så blir den fjernet. Får fikse en DB senere
			}
		}
	}

	public void addUserToServerChatroom(ObjectOutputStream out){ //daaa
		listOfOutStreams.add(out);
		System.out.println("antall pålogget:"+listOfOutStreams.size());
	}
	public void removeUserFromServerChatroom(ObjectOutputStream out){//same
		for(int i=0;i<listOfOutStreams.size();i++){
			if(listOfOutStreams.get(i)==out){
				listOfOutStreams.remove(i);
			}
		}
	}

	public ServerChatroom(String name){
		this.name=name;
		System.out.println("Chatroom "+name+" up");
	}


	public static void main(String[] args) {

		new ServerChatroom("test");
	}

}
