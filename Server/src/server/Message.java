package server;

import java.io.Serializable;
import java.util.ArrayList;



public class Message implements Serializable {							
	String sender="";
	String chatroom="";
	String message="";
	String signal="";
	ArrayList<ArrayList> arrayList= new ArrayList<ArrayList>();
	
	
	public Message(String sender){
		this.sender=sender;
	}
	public void setSender(String sender){
		this.sender=sender;
	}
	public void setchatroom(String chatroom){
		this.chatroom=chatroom;
	}
	public void setMessage(String message){
		this.message=message;
	}
	public void setSignal(String signal){
		this.signal=signal;
	}
	public void addArrayList(ArrayList arraylist){
		arrayList.add(arraylist);
	}
	
	public static void main(String[] args) {
		
		
	}
	

}
