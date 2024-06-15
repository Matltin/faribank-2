package ir.ac.kntu.person.admin;

import ir.ac.kntu.person.Person;

import java.util.Objects;

public class Admin extends Person {

    private String userName;
    private State state;
    private Permission permission;

    public Admin(String firstName, String lastName, String password, String userName) {
        super(firstName, lastName, password);
        this.userName = userName;
        state = State.UNBLOCKED;
        permission = new Permission();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                "userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Admin admin = (Admin) obj;
        return Objects.equals(userName, admin.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userName);
    }
}
