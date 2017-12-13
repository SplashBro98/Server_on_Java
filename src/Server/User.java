package Server;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

public class User {

    private String name;
    private String surname;
    private long time;


    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }



    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.time = System.currentTimeMillis();


    }



    public User(String s) throws IOException{
        String[] arr = s.split(" ");
        this.name = arr[0];
        this.surname = arr[1];
        this.time = System.currentTimeMillis();

    }

    public boolean CheckTime(){
        System.out.println(System.currentTimeMillis() - this.time);
        return(System.currentTimeMillis() - this.time) > 3000;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}
