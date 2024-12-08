package by.bsu.lab5.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "park_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    private String name;

    private String surname;

    private String birthdate;

    @Column(name = "access_role", nullable = false)
    private String access_role;

    @OneToMany(mappedBy = "driver")
    private List<Car> cars;

    @OneToMany(mappedBy = "driver")
    private List<Trip> trips;

    public User() {}

    public User(Integer id, String login, String password, String name, String surname, String birthdate, String access_role) {
        this.id = id;
        this.login = login;
        this.password = hash(password);
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        //this.access_role = access_role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    private String hash(String str) {
        return str;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hash(password);
    }

    public String getAccessRole() {return access_role;};

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
