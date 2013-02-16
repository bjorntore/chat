package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Server extends JFrame {

    ArrayList<ServerChatroom> chatrooms = new ArrayList<ServerChatroom>();
    ArrayList<User> usersConnected = new ArrayList<User>();
    ArrayList<Socket> listOfSockets = new ArrayList<Socket>();
    ArrayList<Thread> listOfThreads = new ArrayList<Thread>();
    ArrayList<ObjectOutputStream> listOfOutStreams = new ArrayList<ObjectOutputStream>();
    ArrayList<DataInputStream> listOfInStreams = new ArrayList<DataInputStream>();

    public Server() {
        chatrooms.add(new ServerChatroom("CHATROOM1")); //for testing
        class ConnectionHandler implements Runnable {
           
            Socket clientSocket;

            public ConnectionHandler(Socket clientSocket) {
                this.clientSocket = clientSocket;
                 System.out.println ("startes threaden?");
            }

            public void run() {
                try {
                    InputStream is = clientSocket.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(is);
                    OutputStream os = clientSocket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    User tempUser;
                    
                    while (true) {
                        System.out.append("Mottas d melding?");
                        Message msg = (Message) ois.readObject();
                        
                        if (msg.getSignal().equalsIgnoreCase("CONNECT")) {
                            System.out.println("HURRA");
                            listOfOutStreams.add(oos);
                            tempUser = ((Message) ois.readObject()).getFromUser();
                            tempUser.setIP(clientSocket.getInetAddress().toString().substring(1));
                            usersConnected.add(tempUser);
                            //oos.writeObject(new Message("USER_LIST", ));
                        }
                        if (msg.getSignal().equalsIgnoreCase("JOIN")) {
                            findServerChatroom(msg.getChatroom()).addUserToServerChatroom(oos);
                        }
                        if (msg.getSignal().equalsIgnoreCase("SEND_TO_CHATROOM")) {
                            findServerChatroom(msg.getChatroom()).writeToServerChatroom(msg);
                        }
                    }
                } catch (IOException IOE) {
                } catch (ClassNotFoundException e) {
                   
                    e.printStackTrace();
                };
                //TODO something
            }
        }
        System.out.println("Server up and running");
        show();										//midlertidig til vi har ordna skikkeli serverGUI
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            ServerSocket serverSocket = new ServerSocket(10823);

            while (true) {
                if (serverSocket.isBound()) {
                    
                    listOfSockets.add(serverSocket.accept());
                    listOfThreads.add(new Thread(new ConnectionHandler(listOfSockets.get(listOfSockets.size() - 1))));
                    listOfThreads.get(listOfThreads.size() - 1).start();
                    System.out.println("connected bitch");
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

    public static void main(String[] args) {
        Server server = new Server();
    }

    public enum Signal {

        JOIN, LEAVE, SEND,
    }
}
