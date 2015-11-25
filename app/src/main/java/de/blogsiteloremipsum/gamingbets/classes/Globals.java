package de.blogsiteloremipsum.gamingbets.classes;

import android.app.Application;

import de.blogsiteloremipsum.gamingbets.communication.client.ClientMethods;
import de.blogsiteloremipsum.gamingbets.communication.client.LocalClientSocket;

/**
 * Created by Niclas on 24.11.2015.
 */
public class Globals extends Application{

    private User user;
    private LocalClientSocket client;

    public void setUser (User u){
        user=u;
    }

    public User getUser(){
        return user;
    }

    public void setClient(LocalClientSocket c){
        client=c;
    }

    public LocalClientSocket getClient(){
        return client;
    }
}
