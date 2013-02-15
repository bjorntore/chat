package server;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Chatroom {
	ArrayList<ObjectOutputStream> listOfOutStreams= new ArrayList<ObjectOutputStream>();
	String name;
	
	public void writeToChatroom(Message msg){
		
		for(int i=0;i<listOfOutStreams.size();i++){
			try {
				listOfOutStreams.get(i).writeObject(msg);
			} catch (IOException e) {
				listOfOutStreams.remove(i);			//klarer den ikke å sende til en enhet så blir den fjernet. Får fikse en DB senere
			}
		}
	}

	public void addUserToChatroom(ObjectOutputStream out){ //daaa
		listOfOutStreams.add(out);
		System.out.println("antall pålogget:"+listOfOutStreams.size());
	}
	public void removeUserFromChatroom(ObjectOutputStream out){//same
		for(int i=0;i<listOfOutStreams.size();i++){
			if(listOfOutStreams.get(i)==out){
				listOfOutStreams.remove(i);
			}
		}
	}

	public Chatroom(String name){
		this.name=name;
		System.out.println("Chatroom "+name+" up");
	}


	public static void main(String[] args) {

		new Chatroom("test");
	}

}
