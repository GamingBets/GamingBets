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
public class LocalServerSocket {

    private int port = 4567;
    private PrintWriter out;
    private BufferedReader in;
    private boolean exit;

    //Logic
    private ServerMethods logic = new Logic();

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

        while (!exit){
            //do stuff!

            int aktion = 0;

            switch (aktion){
                case 0: logic.login(null);
                    break;
                case 1: logic.logout(null);
                    break;
                case 2: logic.edit(null);
                    break;
                case 3: logic.editAdmin(null);
                    break;
                case 4: logic.register(null, null, null, null);
                    break;
                case 5: logic.placeBet(null);
                    break;
                //...
                default:
                    //TODO Default fall überlegen
                    break;
            }


        }

    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
