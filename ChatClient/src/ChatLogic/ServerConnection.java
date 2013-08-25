/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatLogic;

import GUI.ChatMainFrame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stub
 */
public class ServerConnection implements PropertyChangeListener {

    private PropertyChangeSupport serverConnectionPCS;
    private Socket socket;
    private OutputStream os;
    private InputStream is;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ArrayList<User> connectedUsers = new ArrayList<>();
    private ArrayList<ChatRoom> chatrooms = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();
    private Thread serverListener;
    int numberOfConnectionAttempts = 0;
    int secondsBetweenConnectionAttempts = 5;
    
    public ServerConnection() {
        serverConnectionPCS = new PropertyChangeSupport(this);

    }

    public void connect(String ip, int port){
        while (socket == null) {
            try {
                numberOfConnectionAttempts++;
                socket = new Socket(ip, port);
                os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);
                is = socket.getInputStream();
                ois = new ObjectInputStream(is);
                serverListener = new Thread(new ServerListener());
                serverListener.start();
                serverConnectionPCS.firePropertyChange("SERVER_CONNECTION_SUCCESS", null, "Connected to server");
            } catch (Exception ex) {
               //ex.printStackTrace();
                
                serverConnectionPCS.firePropertyChange("SERVER_CONNECTION_FAILED", null, "No server found, retrying in " + secondsBetweenConnectionAttempts + " seconds (" + numberOfConnectionAttempts + ")");
                try {
                    Thread.sleep(secondsBetweenConnectionAttempts * 1000);
                } catch (InterruptedException ex1) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void sendMessage(String signal, String msg, String chatroom, User fromUser) {
        try {
            oos.writeObject(new Message(signal, msg, chatroom, fromUser));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void sendMessage(Message msg) {
        try {
            oos.writeObject(msg);
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

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
    }

    public void addPropertyChangeListener(PropertyChangeListener PCL) {
        serverConnectionPCS.addPropertyChangeListener(PCL);
    }

    public void destroySocket() {
        socket = null;
    }

    class ServerListener implements Runnable {

        public void run() {
            Message tempMsg;
            try {
                while (true) {
                    tempMsg = (Message) ois.readObject();
                    messages.add(tempMsg);
                    
                    switch(tempMsg.getSignal()){
                        case "CONNECTED_USERS":
                            CopyOnWriteArrayList<User> tempList = new CopyOnWriteArrayList<>();
                            for (User tmpUser : tempMsg.getConnectedUsers()) {
                                tempList.add(tmpUser);
                            }
                            serverConnectionPCS.firePropertyChange(tempMsg.getSignal(), null, tempList);
                            break;
                        case "SERVER_MESSAGE":
                            serverConnectionPCS.firePropertyChange(tempMsg.getSignal(), null, tempMsg);
                            System.out.println(tempMsg.getSignal() + " from " + tempMsg.getFromUser() + ": " + tempMsg.getMessage());
                            break;
                            
                        case "REFRESH_CHATROOMLIST":
                            break;
                            
                        case "ALIVE_CHECK":                            
                            serverConnectionPCS.firePropertyChange(tempMsg.getSignal(), null, tempMsg);  
                            break;
                            
                        default:
                            serverConnectionPCS.firePropertyChange(tempMsg.getSignal(), null, tempMsg);
                            break;
                    }                    
                    
                    
                
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                int seconds = 5;
                serverConnectionPCS.firePropertyChange("SERVER_CONNECTION_LOST", null, "Connection to server lost, attempting reconnect");
                try {
                    Thread.sleep(seconds * 1000);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

        }
    }
}
