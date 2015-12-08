package de.blogsiteloremipsum.gamingbets.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class Ticket implements Serializable{

    private int ID;
    private int userID;
    private int status;
    private Date date;
    private List<User> processors;
    private String content;
    private String email;

    public Ticket(int ID, int userID, int status, Date date, List<User> processors, String content, String email) {
        this.ID = ID;
        this.userID = userID;
        this.status = status;
        this.date = date;
        this.processors = processors;
        this.content = content;
        this.email = email;
    }

    public Ticket(String content, String email) {
        this.content = content;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<User> getProcessors() {
        return processors;
    }

    public void setProcessors(List<User> processors) {
        this.processors = processors;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}