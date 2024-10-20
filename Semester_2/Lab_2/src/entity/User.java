package entity;

public class User {
    private Integer id;
    private String login;
    private String password;
    private String hash(String str) {
        return str;
    }

    public Integer getId() {return id;};
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = hash(password);
    }
    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = hash(password);
    }
}
