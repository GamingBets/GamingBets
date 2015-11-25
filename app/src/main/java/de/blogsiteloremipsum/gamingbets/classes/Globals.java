package de.blogsiteloremipsum.gamingbets.classes;

import android.app.Application;

/**
 * Created by Niclas on 24.11.2015.
 */
public class Globals extends Application{

    private User user;

    public void setUser (User u){
        user=u;
    }

    public User getUser(){
        return user;
    }
}
