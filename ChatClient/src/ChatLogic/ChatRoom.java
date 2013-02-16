package ChatLogic;

import java.util.ArrayList;

/**
 *
 * @author BjørnTore
 */
public class ChatRoom {
    private String name;
    private ArrayList<String> chatLog= new ArrayList<>();
    
    public void addMessage(Message msg){ 
        chatLog.add(msg.getFromUser()+": "+msg.getMessage());
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    }
     
