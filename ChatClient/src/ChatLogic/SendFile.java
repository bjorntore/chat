package ChatLogic;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class SendFile {

   private final int BUFFER_SIZE = 65535;

    public SendFile(File fileToSend, Socket socket) { //må legge til ny socket, evt ny socket i User kallt fileTransferSocket eller no, fileToSend hentes fra fileChooser
        try {

            FileInputStream fileInputStream = new FileInputStream(fileToSend);
            System.out.println("prøver å sende:" + fileToSend.getName());
            DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
	    dos.writeUTF(fileToSend.getName()+"/*/"+fileToSend.length());
            byte[] buffer = new byte[BUFFER_SIZE];
            int read;
            int readTotal = 0;
            long startTime = System.currentTimeMillis();
            while ((read = fileInputStream.read(buffer)) != -1) {
                socket.getOutputStream().write(buffer, 0, read);
                readTotal += read;
            }

            
            long endTime = System.currentTimeMillis();
            System.out.println(readTotal + " bytes written in " + (endTime - startTime) + " ms.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8000);
        new SendFile(new File("c:/Users/Stub/Desktop/de.mp4"), socket);

    }*/
}
