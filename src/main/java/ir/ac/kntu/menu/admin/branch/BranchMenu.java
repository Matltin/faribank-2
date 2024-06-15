package ir.ac.kntu.menu.admin.branch;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.AnswerDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.message.Message;
import ir.ac.kntu.message.MessageOption;
import ir.ac.kntu.message.State;

public class BranchMenu extends Menu {

    private AnswerDB answerDB;

    public BranchMenu(AnswerDB answerDB) {
        this.answerDB = answerDB;
    }

    @Override
    public void show() {
        System.out.println("request menu");
        MessageOption messageOption = printMenuOption();
        while (messageOption != MessageOption.BACK) {
            if (messageOption != null) {
                switch (messageOption) {
                    case CONTACT -> showByContact(answerDB);
                    case SETTING -> showSetting(answerDB);
                    case TRANSFER -> showTransfer(answerDB);
                    case REPORT -> showReport(answerDB);
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            messageOption = printMenuOption();
        }
    }

    private MessageOption printMenuOption() {
        System.out.println("----------request Menu----------");
        MessageOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(MessageOption.class);
    }

    private void showByContact(AnswerDB answerDB) {
        if(answerDB.size() == 0) {
            System.out.println(Constance.RED + "there is no customer" + Constance.RESET);
            return;
        }
        if(!checkMessageOption(MessageOption.CONTACT)) {
            System.out.println("there is no contact message to show!!");
            return;
        }
        print(answerDB, MessageOption.CONTACT);
        int number = getNumber();
        int counter = 0;
        for (Message message : answerDB.getMessageList()) {
            if (message.getMessageOption() == MessageOption.CONTACT) {
                counter++;
                checkForClosed(counter, number, message);
            }
        }
    }

    private void showSetting(AnswerDB answerDB) {
        if(answerDB.size() == 0) {
            System.out.println(Constance.RED + "there is no customer" + Constance.RESET);
            return;
        }
        if(!checkMessageOption(MessageOption.SETTING)) {
            System.out.println("there is no setting message to show!!");
            return;
        }
        print(answerDB, MessageOption.SETTING);
        int number = getNumber();
        int counter = 0;
        for (Message message : answerDB.getMessageList()) {
            if (message.getMessageOption() == MessageOption.SETTING) {
                counter++;
                checkForClosed(counter, number, message);
            }
        }
    }

    private void showTransfer(AnswerDB answerDB) {
        if(answerDB.size() == 0) {
            System.out.println(Constance.RED + "there is no customer" + Constance.RESET);
            return;
        }
        if(!checkMessageOption(MessageOption.TRANSFER)) {
            System.out.println("there is no transfer message to show!!");
            return;
        }
        print(answerDB, MessageOption.TRANSFER);
        int number = getNumber();
        int counter = 0;
        for (Message message : answerDB.getMessageList()) {
            if (message.getMessageOption() == MessageOption.TRANSFER) {
                counter++;
                checkForClosed(counter, number, message);
            }
        }
    }

    private void showReport(AnswerDB answerDB) {
        if(answerDB.size() == 0) {
            System.out.println(Constance.RED + "there is no customer" + Constance.RESET);
            return;
        }
        if(!checkMessageOption(MessageOption.REPORT)) {
            System.out.println(Constance.RED + "there is no report message to show!!" + Constance.RESET);
            return;
        }
        print(answerDB, MessageOption.REPORT);
        int number = getNumber();
        int counter = 0;
        for (Message message : answerDB.getMessageList()) {
            if (message.getMessageOption() == MessageOption.REPORT) {
                counter++;
                checkForClosed(counter, number, message);
            }
        }
    }

    private void checkForClosed(int counter, int number, Message message) {
        if (counter == number) {
            System.out.println(message);
            String answer = getMessage();
            message.setMessageAnswer(answer);
            message.setState(State.CLOSED);
        }
    }

    private void print(AnswerDB answerDB, MessageOption messageOption) {
        int counter = 1;
        for (Message message : answerDB.getMessageList()) {
            if (message.getMessageOption() == messageOption) {
                System.out.println(counter + "." + message);
                counter++;
            }
        }
    }

    private boolean checkMessageOption(MessageOption messageOption) {
        for(Message message : answerDB.getMessageList()) {
            if(message.getMessageOption() == messageOption) {
                return true;
            }
        }
        return false;
    }
}
