package server;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FileTransfer {

    public FileTransfer(Socket fromSocket, Socket toSocket) {

        final int BUFFER_SIZE = 65536; //blir ikke så stor med mindre d er mye stress på nettet
        try {

            byte[] buffer = new byte[BUFFER_SIZE];
            String fileName = "";
            long fileSize = 0;
            int read;
            long totalRead = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataInputStream dis = new DataInputStream(fromSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(toSocket.getOutputStream());
            String fileInfo = dis.readUTF();
            dos.writeUTF(fileInfo);
            InputStream fromClientStream = fromSocket.getInputStream();
            OutputStream toClientStream = toSocket.getOutputStream();
            for (int i = 0; i < fileInfo.length() - 3; i++) {
                if (fileInfo.substring(i, i + 3).equalsIgnoreCase("/*/")) {
                    System.out.println("Filnavn: " + fileInfo.substring(0, i));
                    System.out.println("FilLengde: " + fileInfo.substring(i + 3));
                    fileSize = Long.parseLong(fileInfo.substring(i + 3));

                }
            }
            long startTime = System.currentTimeMillis();
            while ((totalRead != fileSize)) {

                read=fromClientStream.read(buffer);
                toClientStream.write(buffer,0,read);
                }

            long endTime = System.currentTimeMillis();
            System.out.println(totalRead + " bytes read in " + (endTime - startTime) + " ms.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws Exception {
        ServerSocket SS = new ServerSocket(10823);
        Socket socket;
        ArrayList<Socket> sockets= new ArrayList<>();
        while (true) {
            if (SS.isBound()) {
               sockets.add(SS.accept());
               if(sockets.size()>1) break;
            }
        }
        new FileTransfer(sockets.get(1), sockets.get(0));
    }
}
