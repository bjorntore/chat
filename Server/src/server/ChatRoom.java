package server;


import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Chatroom {
	ArrayList<DataOutputStream> listOfOutStreams= new ArrayList<DataOutputStream>();
	String name;

	public void writeToChatroom(String msg){
		
		for(int i=0;i<listOfOutStreams.size();i++){
			try {
				listOfOutStreams.get(i).writeUTF(msg);
			} catch (IOException e) {
				listOfOutStreams.remove(i);			//klarer den ikke å sende til en enhet så blir den fjernet. Får fikse en DB senere
			}
		}
	}

	public void addUserToChatroom(DataOutputStream out){ //daaa
		listOfOutStreams.add(out);
		System.out.println("antall pålogget:"+listOfOutStreams.size());
	}
	public void removeUserFromChatroom(DataOutputStream out){//same
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
