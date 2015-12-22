package de.blogsiteloremipsum.gamingbets.communication.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.CommunicationPackage;
import de.blogsiteloremipsum.gamingbets.communication.communication_types;

/**
 * Created by Felix Morsbach on 16.11.2015.
 */
public class LocalClientSocket implements ClientMethods{

    public final static int port = 4567;

    private final static String serverAdress = "134.255.218.20";
    //private final static String serverAdress = "10.0.2.2";
    //private final static String serverAdress = "localhost";

    public static void main(String args[]){

        LocalClientSocket lc = new LocalClientSocket();
        System.out.println(lc.register("Test", "test", "test", null));

    }

    public boolean send(CommunicationPackage cp){

        String response = "";

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            Socket socket = new Socket(serverAdress, port);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.writeObject(cp);
            response = in.readLine();

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO Case if response is not "done", String contains Error Message, handling of this case needs implementation
        return response.equalsIgnoreCase("done");
    }

    public CommunicationPackage sendObject(CommunicationPackage cp){

        try {
            Socket socket = new Socket(serverAdress, port);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(cp);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            cp = (CommunicationPackage) in.readObject();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cp;

    }

    @Override
    public boolean setStatus(Ticket ticket) {
        return send(new CommunicationPackage(communication_types.SETSTATUS, null, null, ticket, null, null));
    }

    @Override
    public boolean login(User user) {
       return send(new CommunicationPackage(communication_types.LOGIN, user, null, null, null, null));
    }

    @Override
    public boolean logout(User user) {
        return send(new CommunicationPackage(communication_types.LOGOUT, user, null, null, null, null));
    }

    @Override
    public boolean register(String username, String email, String pw, Date dob) {
        UnregisteredUser u = new UnregisteredUser(username, email, pw, dob);
        CommunicationPackage cp = new CommunicationPackage(communication_types.REGISTER, null, null, null, u, null);

        return send(cp);
    }

    @Override
    public boolean edit(User user) {
        return send(new CommunicationPackage(communication_types.EDIT, user, null, null, null, null));
    }

    @Override
    public boolean editAdmin(User user) {
        return send(new CommunicationPackage(communication_types.EDITADMIN, user, null, null, null, null));
    }

    @Override
    public boolean placeBet(Bet bet) {
        return send(new CommunicationPackage(communication_types.EDIT, null, bet, null, null, null));
    }

    @Override
    public boolean sendTicket(Ticket ticket) {
        return send(new CommunicationPackage(communication_types.POSTTICKET, null, null, ticket, null, null));
    }

    @Override
    public ArrayList<User> getUsers() {
        CommunicationPackage cp = sendObject(new CommunicationPackage(communication_types.SENDUSERS, null, null, null, null, null));
        return cp.getAllUser();
    }

    @Override
    public User getUser(String userName) {
            CommunicationPackage cp = sendObject(new CommunicationPackage(communication_types.SENDUSER, new User(userName, ""), null, null, null, null));
            return cp.getUser();
    }

    @Override
    public ArrayList<Ticket> getTickets() {
        CommunicationPackage cp = sendObject(new CommunicationPackage(communication_types.SENDTICKETS, null, null, null, null, null));
        return cp.getAllTickets();
    }

    @Override
    public ArrayList<User> getLeaderboard() {
        CommunicationPackage cp = sendObject(new CommunicationPackage(communication_types.SENDLEADERBOARD, null, null, null, null, null));

        return cp.getAllUser();

    }
}
