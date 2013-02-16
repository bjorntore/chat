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

    Socket socket;
    OutputStream os;
    InputStream is;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    ArrayList<ChatRoom> chatrooms = new ArrayList<>();
    Message msg;

    public ServerConnection(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            System.out.println("Connected to server");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /*   public void sendMessage(String signal, String msg, String chatroom, User fromUser) {
        
     try {          
     os = socket.getOutputStream();
     oos = new ObjectOutputStream(os);
     oos.writeObject(new Message(signal, msg, chatroom, fromUser));
           
     } catch (IOException ex) {
           
     }
     }*/
    public void sendMessage(Message msg) {
        try {
            oos.writeObject(msg);
            System.out.println("Melding sendt.");

            while (true) {
                msg = (Message) ois.readObject();
                System.out.println(msg.getSignal());
                /*if (msg.getSignal().equalsIgnoreCase("CHATROOMMESSAGE")) {
                 findChatroom(msg.getChatroom()).addMessage(msg);
                 }*/
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
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