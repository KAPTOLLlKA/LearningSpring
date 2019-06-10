package demoShop.data.user;

public class User {
    private int id;
    private String password;
    private String username;
    private String mail;
    private String about;

    public User() {
    }

    public User(int id, String password, String username, String mail, String about) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.mail = mail;
        this.about = about;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
