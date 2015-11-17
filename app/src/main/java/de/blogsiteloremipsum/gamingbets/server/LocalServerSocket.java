package de.blogsiteloremipsum.gamingbets.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Felix Morsbach on 13.11.2015.
 */
public class LocalServerSocket implements ServerMethods{

    public final static int port = 4567;
    private PrintWriter out;
    private BufferedReader in;
    private boolean exit;


    public static void main(String args[]){

        LocalServerSocket server = new LocalServerSocket();
        server.start();


    }

    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientsocket = serverSocket.accept();

            out = new PrintWriter(clientsocket.getOutputStream(), true );
            in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));

        }catch(IOException e){
            e.printStackTrace();
        }
        exit = false;
        run();
    }

    //TODO Threads?
    private void run() {

        while (!exit) {
            //do stuff!
        }


    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

}
