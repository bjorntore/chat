/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import ChatLogic.Message;
import ChatLogic.ServerConnection;
import GUI.ChatMainFrame;
import ChatLogic.User;
import java.util.ArrayList;

/**
 *
 * @author BjørnTore
 */
public class ChatClient {
    
    public ChatClient(String ip, int port, String username) throws ClassNotFoundException {
   // ChatMainFrame chatMainFrame = new ChatMainFrame();
        User user;
        ArrayList<User> users = new ArrayList<>();
        
        
        
        user = new User(username);
        users.add(user);
       
        Message connectionMessage = new Message("CONNECT", user);
        ServerConnection serverConnection = new ServerConnection(ip, port);  
        serverConnection.sendMessage(connectionMessage);        
        
        //chatMainFrame.refreshUserList(users);
        
        //chatMainFrame.setVisible(true);
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException {		
		new ChatClient("localhost", 10823,"Leif");	
	}
}
