package ir.ac.kntu.menu.admin.statemenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.AnswerDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.message.Message;
import ir.ac.kntu.message.State;

public class StateMenu extends Menu {

    private AnswerDB answerDB;

    public StateMenu(AnswerDB answerDB) {
        this.answerDB = answerDB;
    }

    @Override
    public void show() {
        System.out.println("state menu");
        State state = printMenuOption();
        while (state != State.BACK) {
            if (state != null) {
                switch (state) {
                    case SUBMIT -> showSubmit();
                    case IN_PROGRESS -> showInProgress();
                    case CLOSED -> showClosed();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            state = printMenuOption();
        }
    }

    private State printMenuOption() {
        System.out.println("----------request Menu----------");
        State.printOption();
        System.out.print("Enter your choice : ");
        return getOption(State.class);
    }

    private void showSubmit() {
        if(answerDB.size() == 0) {
            System.out.println(Constance.RED + "there is no customer to show!!" + Constance.RESET);
            return;
        }
        if(!checkState(State.SUBMIT)) {
            System.out.println(Constance.RED + "there is no submitted customer!!" + Constance.RESET);
            return;
        }
        print(State.SUBMIT);
        int number = getNumber();
        int counter = 0;
        for (Message message : answerDB.getMessageList()) {
            if (message.getState() == State.SUBMIT) {
                counter++;
                checkForClosed(counter, number, message);
            }
        }
    }

    private void showInProgress() {
        if(answerDB.size() == 0) {
            System.out.println(Constance.RED + "there is no customer to show!!");
            return;
        }
        if(!checkState(State.IN_PROGRESS)) {
            System.out.println(Constance.RED + "there is no in progressed customer!!" + Constance.RESET);
            return;
        }
        print(State.IN_PROGRESS);
        int number = getNumber();
        int counter = 0;
        for (Message message : answerDB.getMessageList()) {
            if (message.getState() == State.IN_PROGRESS) {
                counter++;
                checkForClosed(counter, number, message);
            }
        }
    }

    private void showClosed() {
        if(answerDB.size() == 0) {
            System.out.println(Constance.RED + "there is no customer to show!!");
            return;
        }
        if(!checkState(State.CLOSED)) {
            System.out.println(Constance.RED + "there is no closed customer!!" + Constance.RESET);
            return;
        }
        print(State.CLOSED);
    }

    private void checkForClosed(int counter, int number, Message message) {
        if (counter == number) {
            System.out.println(message);
            String answer = getMessage();
            message.setMessageAnswer(answer);
            message.setState(State.CLOSED);
        }
    }

    private void print(State state) {
        int counter = 1;
        for (Message message : answerDB.getMessageList()) {
            if (message.getState() == state) {
                System.out.println(counter + "." + message);
                counter++;
            }
        }
    }

    private boolean checkState(State state) {
        for(Message message : answerDB.getMessageList()) {
            if(message.getState() == state) {
                return true;
            }
        }
        return false;
    }
}
