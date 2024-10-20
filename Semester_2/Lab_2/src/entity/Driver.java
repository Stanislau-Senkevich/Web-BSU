package entity;

public class Driver extends User {
    private String name;
    private String surname;
    private String birthDate;

    public Driver(Integer id, String login, String password, String name, String surname, String birthDate) {
        super(id, login, password);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
