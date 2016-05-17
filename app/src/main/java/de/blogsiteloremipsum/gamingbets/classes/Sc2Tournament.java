package de.blogsiteloremipsum.gamingbets.classes;

/**
 * Created by Andre on 16.05.2016.
 */
public class Sc2Tournament {

    private int idtournament;
    private String name;
    private String location;
    private String link;
    private int status;

    public int getIdtournament() {
        return idtournament;
    }

    public void setIdtournament(int idtournament) {
        this.idtournament = idtournament;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
