import java.io.IOException;
import java.net.Socket;
import java.io.*;

public class Client{
        public static void main(String[] args) throws IOException {

                Socket socket = new Socket("192.168.153.5", 8181); // IP address of the server
                System.out.println("Connected to server");

                // We will create a messge and send to the server

                FileInputStream fis = null;
                try {
                    fis = new FileInputStream("path/file.txt");
                } catch (FileNotFoundException e) {
                    System.out.println("File not found: " + e.getMessage());
                    return;
                }

                // Open an output stream to write data to the socket
                OutputStream os = socket.getOutputStream();

                // Read the file into a buffer and write it to the socket output stream
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }

                // Close the input stream, output stream, and socket
                os.close();
                fis.close();
                socket.close();


        }
}