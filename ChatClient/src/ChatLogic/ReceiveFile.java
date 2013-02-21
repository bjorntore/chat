package ChatLogic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class ReceiveFile {

   private int storedInRam = 70000;  //raskeste æ fann

    public ReceiveFile(Socket socket, String location) {  //Igjen legge til socket i User kalt fileTransfer socket eller no
                                                           //Må velge en location på et vis
        final int BUFFER_SIZE = 65536; //samme som tcp-pakke

        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            String fileName = "";
            long fileSize = 0;
            int read;
            long totalRead = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String fileInfo = dis.readUTF();
            InputStream clientInputStream = socket.getInputStream();

            for (int i = 0; i < fileInfo.length() - 3; i++) {
                if (fileInfo.substring(i, i + 3).equalsIgnoreCase("/*/")) {
                    System.out.println("Filnavn: " + fileInfo.substring(0, i));
                    System.out.println("FilLengde: " + fileInfo.substring(i + 3));
                    fileName = fileInfo.substring(0, i);
                    fileSize = Long.parseLong(fileInfo.substring(i + 3));
                }
            }
            FileOutputStream fos = new FileOutputStream(location+fileName);
            long startTime = System.currentTimeMillis();
            while ((totalRead != fileSize)) {
                if (totalRead == fileSize) {
                    break;
                }
                read = clientInputStream.read(buffer);
                totalRead += read;

                baos.write(buffer, 0, read);

                if (storedInRam < baos.toByteArray().length || totalRead >= fileSize) {
                    fos.write(baos.toByteArray());
                    baos.reset();
                }
            }
            System.out.println("BAOS size: " + baos.toByteArray().length);
            fos.close();
            long endTime = System.currentTimeMillis();
            System.out.println(totalRead + " bytes read in " + (endTime - startTime) + " ms.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

   /* public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8000);
        new ReceiveFile(socket, "c:/Users/Stub/Desktop/Mottak/" );
    }*/
}
