package ChatLogic;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author BjørnTore
 */
public class User implements Serializable {
    private static final long serialVersionUID = 7526472295622776146L;  // unique id
    private String name, signature,IP;
    private transient ObjectOutputStream oos;
    private int failedAliveChecks = 0;
    private transient Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getFailedAliveChecks() {
        return failedAliveChecks;
    }

    public void setFailedAliveChecks(int failedAliveChecks) {
        this.failedAliveChecks = failedAliveChecks;
    }
    
    public void aliveCheckFailed(){
        failedAliveChecks++;
    }
   
    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }
    
    public User(String name){
    this.name = name;
    }
    
    public User(String name, String signature){
    this.name = name;
    this.signature = signature;
    }

    public String getName() {
        return name;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User)super.clone();
    }    
    

    public String getSignature() {
        return signature;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getIP(){
        return IP;
    }
    
    public void setIP(String IP) {
        this.IP = IP;
    }
}
