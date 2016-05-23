package de.blogsiteloremipsum.gamingbets.classes;

/**
 * Created by Andre on 17.05.2016.
 */
public class User {
    private Integer id;
    private String userName;
    private String password;
    private Boolean loggedIn;
    private Boolean admin;
    private boolean active;
    private String email;
    private Integer score;
    private Integer profilePic;
    private Integer unlocks;

    public Integer getUnlocks() {
        return unlocks;
    }

    public void setUnlocks(Integer unlocks) {
        this.unlocks = unlocks;
    }

    public Integer getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Integer profilePic) {
        this.profilePic = profilePic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
