package ir.ac.kntu.menu.admin.searchmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.AnswerDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.admin.branch.BranchMenu;
import ir.ac.kntu.menu.admin.statemenu.StateMenu;
import ir.ac.kntu.message.Message;
import ir.ac.kntu.message.State;
import ir.ac.kntu.person.admin.Admin;
import ir.ac.kntu.person.admin.Permission;

public class SearchMenu extends Menu {

    private Admin admin;
    private AnswerDB answerDB;
    private StateMenu stateMenu;
    private BranchMenu branchMenu;

    public SearchMenu(AnswerDB answerDB, StateMenu stateMenu, BranchMenu branchMenu) {
        this.answerDB = answerDB;
        this.stateMenu = stateMenu;
        this.branchMenu = branchMenu;
    }

    public void show(Admin admin) {
        this.admin = admin;
        show();
    }

    @Override
    public void show() {
        System.out.println("search menu");
        SearchMenuOption searchMenuOption = printMenuOption();
        while (searchMenuOption != SearchMenuOption.BACK) {
            if (searchMenuOption != null) {
                switch (searchMenuOption) {
                    case STATE -> checkState();
                    case BRANCH -> branchMenu.show();
                    case USER -> checkUser();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            searchMenuOption = printMenuOption();
        }

    }

    private SearchMenuOption printMenuOption() {
        System.out.println("----------request Menu----------");
        SearchMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(SearchMenuOption.class);
    }

    private void checkState() {
        Permission permission = admin.getPermission();
        if(!permission.isState()) {
            System.out.println(Constance.RED + "you do not have permission" + Constance.RESET);
            return;
        }
        stateMenu.show();
    }

    private void checkUser() {
        Permission permission = admin.getPermission();
        if(!permission.isUser()) {
            System.out.println(Constance.RED + "you do not have permission" + Constance.RESET);
            return;
        }
        searchByUser(answerDB);
    }

    private void searchByUser(AnswerDB answerDB) {
        if(answerDB.size() == 0) {
            System.out.println(Constance.RED + "there is no customer to show" + Constance.RESET);
            return;
        }
        String phoneNumber = getPhoneNumber();
        for (Message message : answerDB.getMessageList()) {
            if (message.getPhoneNumber().equals(phoneNumber)) {
                System.out.println(message);
                String answer = getMessage();
                message.setMessageAnswer(answer);
                message.setState(State.CLOSED);
                return;
            }
        }
        System.out.println("the phone number does not exist!!");
    }
}