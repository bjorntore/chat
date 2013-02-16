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
    private ArrayList<Message> messages = new ArrayList<>();
    private Thread serverListener;
    
    class ServerListener implements Runnable {

        public void run() {
            Message tempMsg;
            try {
               while(true){
                  tempMsg = (Message) ois.readObject();
                   messages.add(tempMsg);
                   //TODO MASSE IF-SETTNINGA
               }    
                
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
    }
    
    public ServerConnection(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
             is = socket.getInputStream();
             ois = new ObjectInputStream(is);
            serverListener = new Thread(new ServerListener());
            serverListener.start();
            System.out.println("Requesting connection to server...");
            
        } catch (Exception ex) {
        ex.printStackTrace();
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
