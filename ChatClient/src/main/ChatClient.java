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
import javax.swing.JOptionPane;

/**
 *
 * @author BjørnTore
 */
public class ChatClient implements PropertyChangeListener {

    private ChatMainFrame chatMainFrame;
    private ServerConnection serverConnection;
    private String ip;
    private int port;
    private User user;
    private static ChatClient chatClient;

    public ChatClient(String ip, int port, String username) throws ClassNotFoundException {
        this.ip = ip;
        this.port = port;
        this.user = new User(username);

        chatMainFrame = new ChatMainFrame();
        chatMainFrame.addPropertyChangeListener(this);
        chatMainFrame.setVisible(true);

        Message connectionMessage = new Message("CONNECT", user);
        serverConnection = new ServerConnection();
        serverConnection.addPropertyChangeListener(this);
        serverConnection.connect(ip, port);
        serverConnection.sendMessage(connectionMessage);
       // serverConnection.sendMessage(new Message("CREATE_PUBLIC_CHATROOM","TEST", user));
        serverConnection.sendMessage(new Message("JOIN", "TEST", user));
        
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String nickname = null;
        try {
            nickname = JOptionPane.showInputDialog("Please enter a nickname");
            while ((nickname.trim()).equals("")) {
                nickname = JOptionPane.showInputDialog("Please enter a nickname, it's required");
            }
        } catch (NullPointerException ex) {
            System.exit(0);
        }
        
        chatClient = new ChatClient("localhost", 10823, nickname);
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        switch (pce.getPropertyName()) {
            //SERVER
            case "CONNECTED_USERS":
                chatMainFrame.refreshUserList((ArrayList<User>) pce.getNewValue());
                break;
            case "SERVER_CONNECTION_FAILED":
                chatMainFrame.writeOutput(pce.getNewValue() + "", null);
                break;
            case "SERVER_CONNECTION_LOST":
                chatMainFrame.writeOutput(pce.getNewValue() + "", null);
                serverConnection.destroySocket();
                serverConnection.connect(ip, port);
                break;

            case "SERVER_MESSAGE":
                chatMainFrame.writeOutput((((Message) pce.getNewValue()).getMessage()) + "", ((Message) pce.getNewValue()).getFromUser());
                break;

            //GUI   
            case "SEND_TO_SERVER":
                serverConnection.sendMessage("SEND_TO_SERVER", pce.getNewValue() + "", pce.getOldValue() + "", user);
                break;
        }
    }
}
