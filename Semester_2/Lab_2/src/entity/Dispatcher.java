package entity;

public class Dispatcher extends User {
    private String name;
    private String surname;
    private String birthDate;

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public Dispatcher(Integer id, String login, String passwordHash, String name, String surname, String birthDate) {
        super(id, login, passwordHash);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }
}
