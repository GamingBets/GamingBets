package de.blogsiteloremipsum.gamingbets.classes;

import java.util.Date;
import java.util.List;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class Ticket {

    private int ID;
    private User user;
    private int status;
    private Date date;
    private List<User> processors;
    private List<String[]> messages;

    public Ticket(int ID, User user, int status, Date date, List<User> processors, List<String[]> messages) {
        this.ID = ID;
        this.user = user;
        this.status = status;
        this.date = date;
        this.processors = processors;
        this.messages = messages;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public int getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public List<User> getProcessors() {
        return processors;
    }

    public List<String[]> getMessages() {
        return messages;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setProcessors(List<User> processors) {
        this.processors = processors;
    }

    public void setMessages(List<String[]> messages) {
        this.messages = messages;
    }
}
