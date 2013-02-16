/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import ChatLogic.Message;
import ChatLogic.ServerConnection;
import ChatLogic.User;
import GUI.ChatMainFrame;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author BjørnTore
 */
public class ChatClient {

    public ChatClient(String ip, int port, String username) throws ClassNotFoundException {
        ChatMainFrame chatMainFrame = new ChatMainFrame();
        Random random = new Random(1000000);


        Message connectionMessage = new Message("CONNECT", new User(username + random.nextInt()));
        ServerConnection serverConnection = new ServerConnection(ip, port);
        serverConnection.sendMessage(connectionMessage);
        
        try{
         chatMainFrame.refreshUserList(serverConnection.getConnectedUsers());
        }catch(NullPointerException ex){
            System.out.println("No connected users.");
        }        
        
        chatMainFrame.setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new ChatClient("localhost", 10823, "Leif");
    }
}
