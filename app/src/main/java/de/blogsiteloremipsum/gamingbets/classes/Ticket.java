package de.blogsiteloremipsum.gamingbets.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class Ticket {

    private int id;
    private int userId;
    private int status;
    private String date;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}