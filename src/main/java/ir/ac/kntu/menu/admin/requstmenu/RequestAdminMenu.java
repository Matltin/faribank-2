package ir.ac.kntu.menu.admin.requstmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.AnswerDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.admin.searchmenu.SearchMenu;
import ir.ac.kntu.message.State;

public class RequestAdminMenu extends Menu {

    private AnswerDB answerDB;
    private SearchMenu searchMenu;

    public RequestAdminMenu(AnswerDB answerDB, SearchMenu searchMenu) {
        this.answerDB = answerDB;
        this.searchMenu = searchMenu;
    }

    @Override
    public void show() {
        System.out.println("request menu");
        RequestAdminMenuOption requestMenuOption = printMenuOption();
        while (requestMenuOption != RequestAdminMenuOption.BACK) {
            if (requestMenuOption != null) {
                switch (requestMenuOption) {
                    case SEARCH -> searchMenu.show();
                    case SHOW -> showRequest(answerDB);
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            requestMenuOption = printMenuOption();
        }
    }

    private RequestAdminMenuOption printMenuOption() {
        System.out.println("----------request Menu----------");
        RequestAdminMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(RequestAdminMenuOption.class);
    }

    private void showRequest(AnswerDB answerDB) {
        if (answerDB.size() == 0) {
            System.out.println(Constance.RED + "there is no customer to show" + Constance.RESET);
            return;
        }
        answerDB.printMessage();
        int number = getNumber();
        answerDB.getMessageList().get(number - 1).setState(State.IN_PROGRESS);
        System.out.println(answerDB.getMessageList().get(number - 1) + " phone number : " + answerDB.getMessageList().get(number - 1).getPhoneNumber());
    }
}
