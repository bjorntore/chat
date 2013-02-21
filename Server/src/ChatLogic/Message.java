package ChatLogic;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;  // unique id
    private User fromUser;
    private String chatroom = "";
    private String message = "";
    private String signal = "";
    private ArrayList<User> connectedUsers = new ArrayList<>();
    private ArrayList arrayList = new ArrayList();
    private User toUser;

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Message(String signal, String message, String chatroom, User fromUser, ArrayList arraylist) {
        this.signal = signal;
        this.message = message;
        this.chatroom = chatroom;
        this.fromUser = fromUser;
        this.arrayList = arraylist;
    }

    public Message(String signal, String message, String chatroom, User fromUser) {
        this.signal = signal;
        this.message = message;
        this.chatroom = chatroom;
        this.fromUser = fromUser;
    }
     public Message(String signal) {
        this.signal = signal;
    }
      public Message(String signal, ArrayList arraylist, String message) {
        this.signal = signal;
        this.arrayList= arraylist;
        this.message=message;
    }
     public Message(String signal, String chatroom, User user) {
        this.signal = signal;
        this.chatroom = chatroom;
        this.fromUser= user;
    }
     
      public Message(String signal, ArrayList<User> connectedUsers) {
        this.signal = signal;
        this.connectedUsers = connectedUsers;
    }

    public Message(String signal, User fromUser) {
        this.signal = signal;
        this.fromUser = fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public void setchatroom(String chatroom) {
        this.chatroom = chatroom;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public void addArrayList(ArrayList arraylist) {
        arrayList.add(arraylist);
    }

    public static void main(String[] args) {
    }

    public User getFromUser() {
        return fromUser;
    }

    public String getChatroom() {
        return chatroom;
    }

    public String getMessage() {
        return message;
    }

    public String getSignal() {
        return signal;
    }
    
    public ArrayList<User> getConnectedUsers(){
        return connectedUsers;
    }

    public ArrayList<ArrayList> getArrayList() {
        return arrayList;
    }
}
