package ir.ac.kntu.person.chief;

import ir.ac.kntu.person.Person;

public class Chief extends Person {
    private String userName;
    private int position;
    private State state;

    public Chief(String firstName, String lastName, String password, String userName, int position) {
        super(firstName, lastName, password);
        this.userName = userName;
        this.position = position;
        state = State.UNBLOCKED;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Chief{" +
                super.toString() +
                "userName='" + userName + '\'' +
                '}';
    }
}
