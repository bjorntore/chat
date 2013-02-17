package server;

import ChatLogic.Message;
import ChatLogic.User;
import GUI.ServerMainJFrame;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    ArrayList<ServerChatroom> chatrooms = new ArrayList<>();
    public static ArrayList<User> usersConnected = new ArrayList<>();
    ArrayList<Thread> listOfThreads = new ArrayList<>();
    ServerMainJFrame mainJFrame = new ServerMainJFrame();
    User serverUser = new User("Server");

    public Server() {
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
                        Message msg = (Message) ois.readObject();


                        switch (msg.getSignal()) {
                            case "CONNECT":
                                mainJFrame.writeOutput("User " + msg.getFromUser().getName() + " (" + clientSocket.getInetAddress().toString().substring(1) + ") connected");
                                User tempUser = msg.getFromUser();
                                tempUser.setOos(oos);
                                tempUser.setIP(clientSocket.getInetAddress().toString().substring(1));
                                usersConnected.add(tempUser);
                                sendUsersConnectedToAll();
                                break;

                            case "PULSE_BEAT":
                                for (User _user : usersConnected) {
                                    if (_user.getName().equalsIgnoreCase(msg.getFromUser().getName())) {
                                        _user.setFailedAliveChecks(0);
                                    }
                                }
                                break;

                            case "JOIN":
                                mainJFrame.writeOutput(msg.getFromUser() + " joinet chatroom: " + findServerChatroom(msg.getChatroom()).getName());
                                findServerChatroom(msg.getChatroom()).addUserToServerChatroom(msg.getFromUser());

                                break;

                            case "SEND_TO_CHATROOM":
                                findServerChatroom(msg.getChatroom()).writeToServerChatroom(msg);
                                break;

                            case "CREATE_PUBLIC_CHATROOM":

                                if (findServerChatroom(msg.getChatroom()) == null) {
                                    chatrooms.add(new ServerChatroom(msg.getChatroom()));
                                    mainJFrame.writeOutput("Made new Chatroom: " + chatrooms.get(chatrooms.size() - 1).getName());
                                }

                            case "SEND_TO_SERVER":
                                mainJFrame.writeOutput(msg.getFromUser().getName() + ": " + msg.getMessage());
                                sendMessage("SERVER_MESSAGE", msg.getMessage() + "", "DEFAULT", msg.getFromUser());
                                break;

                            default:
                                break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //TODO something
            }
        }
        mainJFrame.writeOutput("Server up and running");

        try {
            ServerSocket serverSocket = new ServerSocket(10823);
            Thread refreshThread = new Thread(new UpdateClients());
            refreshThread.start();
            while (true) {
                if (serverSocket.isBound()) {
                    listOfThreads.add(new Thread(new ConnectionHandler(serverSocket.accept())));
                    listOfThreads.get(listOfThreads.size() - 1).start();

                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    class UpdateClients implements Runnable {

        @Override
        public void run() {
            ArrayList tempList = new ArrayList();
            while (true) {
                sendUsersConnectedToAll();
                updateClientChatroomList();
                aliveCheck();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    public void aliveCheck() {
        if (usersConnected.isEmpty()) {
            return;
        }
        for (User user : usersConnected) {
            if (user.getFailedAliveChecks() < 2) {
                try {
                    user.getOos().writeObject(new Message("ALIVE_CHECK"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                sendMessage("SERVER_MESSAGE", user.getName() + " diconnected", "DEFAULT", serverUser);
                usersConnected.remove(user);
                user = null;
            }

        }
    }

    public ServerChatroom findServerChatroom(String name) {
        for (int i = 0; i < chatrooms.size(); i++) {
            if (chatrooms.get(i).getName().equalsIgnoreCase(name)) {
                return chatrooms.get(i);
            }
        }

        System.err.println("Fant ikke chatroom");
        return null;
    }

    public void sendUsersConnectedToAll() {
        if (usersConnected.isEmpty()) {
            return;
        }
        ArrayList<User> tempUserList = new ArrayList<>();
        for (User user : usersConnected) {
            tempUserList.add(user);
        }

        for (User user : usersConnected) {
            try {
                Message msg = new Message("CONNECTED_USERS", tempUserList);
                user.getOos().writeObject(msg);

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }

    public void sendMessage(String signal, String messageText, String chatroom, User FromUser) {
        for (User user : usersConnected) {
            try {
                user.getOos().writeObject(new Message(signal, messageText, chatroom, user));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<ServerChatroom> getChatrooms() {
        return chatrooms;
    }

    public void updateClientChatroomList() {
        if (usersConnected.isEmpty()) {
            return;
        }
        ArrayList tempList = new ArrayList();
        for (User user : usersConnected) {
            try {
                for (ServerChatroom serverChatroom : chatrooms) {
                    tempList.add(serverChatroom);
                }
                user.getOos().writeObject(new Message("REFRESH_CHATROOMLIST", tempList, ""));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
