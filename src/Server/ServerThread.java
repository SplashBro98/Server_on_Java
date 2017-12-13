package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;

public class ServerThread extends Thread{

    private Socket socket;
    private BufferedReader bufr;
    private PrintWriter prw;
    private InetAddress addr;

    private FileWriter fw;

    private User account;




    private int id = counter++;
    private static int counter = 1;

    public ServerThread(Socket s) throws IOException, ClassNotFoundException {
        this.socket = s;
        this.bufr = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.prw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);

        this.fw = new FileWriter("D:\\Проекты_по_проге\\3-й семестр\\Прога\\Лаба№9_7_Server\\src\\Server\\output.txt",true);

        this.addr = this.socket.getInetAddress();


        start();

    }


    @Override
    public void run() {
        try{
            String str;

            str = this.bufr.readLine();

            this.account = new User(str);
            System.out.println("Making the "+ this.id + " client: " + this.account.toString());
            this.prw.println("Server got client");
            while (true) {

                Thread.sleep(3000);
                this.prw.println("Are you online? (Yes or No)");
                //Thread.sleep(5000);

                String s = this.bufr.readLine();
                if(s.equals("No")){
                    this.prw.println("You are offline");
                    break;
                }
            }
            String h = "Client " + this.account.toString() + " stayed online for " + (System.currentTimeMillis() - this.account.getTime())/1000 + " seconds";
            System.out.println(h);
            //Main.getOutput().append(h + '\n');
            this.fw.write(h + '\n');
            this.fw.flush();


        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("closing...");
            try{
                this.socket.close();
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }

        }
        return;

    }
//    public void Notify(){
//        Main.getMap().remove(this.socket);
//        for(User user: Main.getMap().values()){
//            //System.out.println("LALALA");
//            user.getPrw().println(this.account.getSurname() + " is offline now");
//        }
//    }
}
