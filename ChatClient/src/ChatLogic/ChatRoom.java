package ChatLogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


public class ChatRoom implements PropertyChangeListener{
    private String name;
    private ArrayList<Message> chatLog= new ArrayList<>();
    private ArrayList<User> users= new ArrayList();
    private PropertyChangeSupport chatroomPCS;
    
    public ChatRoom(String name, ServerConnection serverConnection){
        this.name=name;
        chatroomPCS = new PropertyChangeSupport(this);
        serverConnection.addPropertyChangeListener(this);
    }
    
    public void addMessage(Message msg){ 
        chatLog.add(msg);
    }
    public void addUser(User user){ 
        users.add(user);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public void propertyChange(PropertyChangeEvent pce) {
        if(pce.getPropertyName().equals("SEND")&&((Message)pce.getNewValue()).getChatroom().equalsIgnoreCase(name)){
            chatLog.add(((Message)pce.getNewValue()));
        }
        chatroomPCS.firePropertyChange(name, null, chatLog);
        
    }
    }
     
