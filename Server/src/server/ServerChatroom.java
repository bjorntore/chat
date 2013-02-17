package server;

import ChatLogic.Message;
import ChatLogic.User;
import java.io.IOException;
import java.util.ArrayList;

public class ServerChatroom {
	private ArrayList<User> listOfUsers= new ArrayList<>();
	private String name;
	
	public void writeToServerChatroom(Message msg){
		
		for(int i=0;i<listOfUsers.size();i++){
			try {
				listOfUsers.get(i).getOos().writeObject(msg);
			} catch (IOException e) {
				e.printStackTrace();			
			}
		}
	}

	public void addUserToServerChatroom(User user){ //daaa
		listOfUsers.add(user);
		System.out.println("antall pålogget:"+listOfUsers.size());
	}
	public void removeUserFromServerChatroom(User user){//same
		for(int i=0;i<listOfUsers.size();i++){
			if(listOfUsers.get(i)==user){
				listOfUsers.remove(i);
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

    String getName() {
        return name;
    }

}
