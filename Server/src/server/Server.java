package server;

import ChatLogic.Message;
import ChatLogic.User;
import GUI.ServerMainJFrame;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Server {

    ArrayList<ServerChatroom> chatrooms = new ArrayList<>();
    public static ArrayList<User> usersConnected = new ArrayList<>();
    ArrayList<Socket> listOfSockets = new ArrayList<>();
    ArrayList<Thread> listOfThreads = new ArrayList<>();
    ArrayList<ObjectOutputStream> listOfOutStreams = new ArrayList<>();
    ArrayList<DataInputStream> listOfInStreams = new ArrayList<>();
    ServerMainJFrame mainJFrame = new ServerMainJFrame();

    public Server() {              
        chatrooms.add(new ServerChatroom("CHATROOM1")); //for testing
        class ConnectionHandler implements Runnable {
            Socket clientSocket;
            
            public ConnectionHandler(Socket clientSocket) {
                this.clientSocket = clientSocket;
            }

            @Override
            public void run() {
                try {
                    OutputStream os = clientSocket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);                   
                    InputStream is = clientSocket.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(is);
                    
                    while (true) {
                        Message msg = (Message)ois.readObject();

                        if (msg.getSignal().equalsIgnoreCase("CONNECT")) {
                            mainJFrame.writeOutput("User " + msg.getFromUser().getName() + " (" + clientSocket.getInetAddress().toString().substring(1) + ") connected");
                            listOfOutStreams.add(oos);
                            User tempUser  = msg.getFromUser();
                            tempUser.setIP(clientSocket.getInetAddress().toString().substring(1));
                            usersConnected.add(tempUser);
                            sendUsersConnectedToAll();                     
                        }
                        if (msg.getSignal().equalsIgnoreCase("JOIN")) {
                            findServerChatroom(msg.getChatroom()).addUserToServerChatroom(oos);
                        }
                        if (msg.getSignal().equalsIgnoreCase("SEND_TO_CHATROOM")) {
                            findServerChatroom(msg.getChatroom()).writeToServerChatroom(msg);
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("BT: " + ex);
                }
                //TODO something
            }
        }
        mainJFrame.writeOutput("Server up and running");

        try {
            ServerSocket serverSocket = new ServerSocket(10823);

            while (true) {
                if (serverSocket.isBound()) {
                    listOfSockets.add(serverSocket.accept());
                    listOfThreads.add(new Thread(new ConnectionHandler(listOfSockets.get(listOfSockets.size() - 1))));
                    listOfThreads.get(listOfThreads.size() - 1).start();
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public ServerChatroom findServerChatroom(String name) {
        for (int i = 0; i < chatrooms.size(); i++) {
            if (chatrooms.get(i).name.equalsIgnoreCase(name)) {
                return chatrooms.get(i);
            }
        }
        return null;
    }
    
    public void sendUsersConnectedToAll(){
        ArrayList<User> tempUserList = new ArrayList<>();
        for (User user : usersConnected) {
                tempUserList.add(user);       
        }
        
        for (ObjectOutputStream outputstream : listOfOutStreams) {
            try {
                Message msg= new Message("CONNECTED_USERS", tempUserList); 
                outputstream.writeObject(msg);                
               
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
         
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
