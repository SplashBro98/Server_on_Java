package Server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    private final static int PORT = 8080;
    private static Map<Socket,User> map = new HashMap<>();
    private static StringBuffer output;
    private static OutputStream fos;



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server Started: " + s);
        try {


                // Блокируется до возникновения нового соединения:
                Socket socket = s.accept();

                try {

                    new ServerThread(socket);


                }
                catch (IOException e) {

                    socket.close();
                }

        }
        finally {
            s.close();

        }

    }
}
