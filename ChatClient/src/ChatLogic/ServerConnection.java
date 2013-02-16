/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatLogic;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    Message msg;
    ArrayList<ChatRoom> chatrooms = new ArrayList<>();

    public ServerConnection(String ip, int port) {


        try {

            socket = new Socket(ip, port);
            

            is = socket.getInputStream();
            ois = new ObjectInputStream(is);

            while (true) {
                msg = (Message) ois.readObject();
                if (msg.getSignal().equalsIgnoreCase("CHATROOMMESSAGE")) {
                    findChatroom(msg.getChatroom()).addMessage(msg);
                }


            }

        } catch (Exception ex) {
        }
    }

    public void sendMessage(String signal,String msg, String chatroom, User fromUser ) {
        try {
            
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            oos.writeObject(new Message(signal,msg, chatroom, fromUser ));
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
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