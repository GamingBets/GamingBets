package de.blogsiteloremipsum.gamingbets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by quint_000 on 05.11.2015.
 */
public class Database {

    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String name = "bettingGame";
            String user = "root";

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + name + "?user=" + user);
            return con;
            /*Statement stmt = con.createStatement();
            String query = "SELECT name FROM address";
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                System.out.println(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            con.close();*/
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean register(String userName, String password, Date dob, boolean admin){

        Connection con = connect();
        try {


            Statement stmt = con.createStatement();
            String query = "INSERT INTO user (userName, password, bets, loggedIn, admin, active, dob) VALUES (" + userName + ", " + password + ", nichts , false , false, true, " + dob + ")";
            boolean succ = stmt.execute(query);
            stmt.close();
            con.close();
            return succ;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public User getUser(String userName){

        int id=0;
        String uName = userName;
        String password="";
        String bets="";
        boolean admin=false;
        boolean active=true;
        String email="";

        Connection con = connect();
        try{
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM user WHERE userName="+userName+"";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                id = rs.getInt("iD");
                password = rs.getString("password");
                bets = rs.getString("bets");
                admin = rs.getBoolean("admin");
                active = rs.getBoolean("active1");
                email = rs.getString("email");
            }
            return new User(id, uName, email, password, bets, admin, active);
        }catch (Exception e){
            e.printStackTrace();
        }return null;
    }

}
