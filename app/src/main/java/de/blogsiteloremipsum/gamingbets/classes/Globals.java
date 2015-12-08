package de.blogsiteloremipsum.gamingbets.classes;

import android.app.Activity;
import android.app.Application;
import android.view.inputmethod.InputMethodManager;

import de.blogsiteloremipsum.gamingbets.communication.client.ClientMethods;
import de.blogsiteloremipsum.gamingbets.communication.client.LocalClientSocket;

/**
 * Created by Niclas on 24.11.2015.
 */
public class Globals extends Application{

    private User user;
    private UnregisteredUser unregisteredUser;
    private LocalClientSocket client;

    private String usereditName = "";

    public String getUsereditName() {
        return usereditName;
    }

    public void setUsereditName(String usereditName) {
        this.usereditName = usereditName;
    }

    public void setUser (User u){
        user=u;
    }

    public User getUser(){
        return user;
    }

    public void setUnregisteredUser(UnregisteredUser u){
        unregisteredUser=u;
    }

    public UnregisteredUser getUnregisteredUser(){return unregisteredUser;}

    public void setClient(LocalClientSocket c){
        client=c;
    }

    public LocalClientSocket getClient(){
        return client;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
