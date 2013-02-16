/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatLogic;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Stub
 */
public class ServerConnection {

    private Socket socket;
    private OutputStream os;
    private InputStream is;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ArrayList<User> connectedUsers = new ArrayList<>();
    private ArrayList<ChatRoom> chatrooms = new ArrayList<>();

    public ServerConnection(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            System.out.println("Requesting connection to server...");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void sendMessage(String signal, String msg, String chatroom, User fromUser) {
        try {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            oos.writeObject(new Message(signal, msg, chatroom, fromUser));

        } catch (Exception ex) {
        }
    }

    public void sendMessage(Message msg) {
        try {
            oos.writeObject(msg);
            while (true) {
                msg = (Message) ois.readObject();
                if (msg.getSignal().equalsIgnoreCase("CONNECTION_SUCCESSFUL")) {
                    System.out.println("Connection to server successful.");
                    //findChatroom(msg.getChatroom()).addMessage(msg);
                }
                else if(msg.getSignal().equalsIgnoreCase("CONNECTED_USERS")){
                    connectedUsers = msg.getConnectedUsers();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public ArrayList<User> getConnectedUsers() {
        return connectedUsers;
    }
    
    

    public ChatRoom findChatroom(String name) {
        for (int i = 0; i < chatrooms.size(); i++) {
            if (chatrooms.get(i).getName().equalsIgnoreCase(name)) {
                return chatrooms.get(i);
            }
        }
        return null;
    }
}