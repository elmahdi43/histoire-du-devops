import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {
        public static void main(String[] args) throws IOException {
                ServerSocket serverSocket = new ServerSocket(8181);
                System.out.println("Server started, waiting for client...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                // Open an output stream to write the received file to disk
                FileOutputStream fos = null;
                try {
                        fos = new FileOutputStream("path/to/recevied_file.txt");
                } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                        return;
                }

                // Open an input stream to read data from the socket
                InputStream is = socket.getInputStream();

                // Read data from the socket input stream and write it to the output file
                byte[] buffer = new byte[4096];
                int bytesRead;
                 while ((bytesRead = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                }

                // Close the input stream, output stream, and socket
                fos.close();
                is.close();
                socket.close();
                serverSocket.close();
        }

}
