package ir.ac.kntu.person.customer;

import ir.ac.kntu.Constant;

public enum State {
    ACCEPTED(Constant.GREEN + "Accepted"),
    IN_PROGRESSING(Constant.YELLOW + "in Progressing"),
    REJECT(Constant.RED + "Reject");

    private final String state;

    State(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
