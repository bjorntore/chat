/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import ChatLogic.Message;
import ChatLogic.ServerConnection;
import ChatLogic.User;
import GUI.ChatMainFrame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author BjørnTore
 */
public class ChatClient implements PropertyChangeListener {

    private ChatMainFrame chatMainFrame;
    private ServerConnection serverConnection;

    public ChatClient(String ip, int port, String username) throws ClassNotFoundException {
        chatMainFrame = new ChatMainFrame();
        chatMainFrame.setVisible(true);
        Random random = new Random();


        Message connectionMessage = new Message("CONNECT", new User(username + random.nextInt(1000000)));
        serverConnection = new ServerConnection(ip, port);
        serverConnection.addPropertyChangeListener(this);
        serverConnection.sendMessage(connectionMessage);

        
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new ChatClient("localhost", 10823, "Leif");
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals("CONNECTED_USERS")) {
            chatMainFrame.refreshUserList((ArrayList<User>) pce.getNewValue());
            System.err.print(((ArrayList<User>) pce.getNewValue()).size());
        }
    }
}
