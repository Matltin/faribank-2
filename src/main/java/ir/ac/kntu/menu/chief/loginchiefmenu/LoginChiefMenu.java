package ir.ac.kntu.menu.chief.loginchiefmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.ChiefDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.chief.chiefmenu.ChiefMenu;
import ir.ac.kntu.person.admin.Admin;
import ir.ac.kntu.person.chief.Chief;
import ir.ac.kntu.person.chief.State;

import java.text.ParseException;

public class LoginChiefMenu extends Menu {

    private ChiefDB chiefDB;
    private ChiefMenu chiefMenu;

    public LoginChiefMenu(ChiefDB chiefDB, ChiefMenu chiefMenu) {
        this.chiefDB = chiefDB;
        this.chiefMenu = chiefMenu;
    }

    @Override
    public void show() throws ParseException {
        System.out.println("logging page");
        LoginChiefMenuOption loginChiefMenuOption = printMenuOption();
        while (loginChiefMenuOption != LoginChiefMenuOption.BACK) {
            if (loginChiefMenuOption != null) {
                switch (loginChiefMenuOption) {
                    case LOGIN -> login();
                    case REGISTER -> register();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            loginChiefMenuOption = printMenuOption();
        }
    }

    private LoginChiefMenuOption printMenuOption() {
        System.out.println("----------logging admin Menu----------");
        LoginChiefMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(LoginChiefMenuOption.class);
    }

    private void login() throws ParseException {
        String userName = getUserName();
        String password = getPassword();
        for (Chief chief : chiefDB.getChiefs()) {
            if (chief.getUserName().equals(userName) && chief.getPassword().equals(password)) {
                if(chief.getState() == State.BLOCKED) {
                    System.out.println(Constance.RED + "you have been blocked!!" + Constance.RESET);
                    return;
                }
                chiefMenu.show(chief);
                return;
            }
        }
        System.out.println(Constance.RED + "invalid username or password" + Constance.RESET);
    }

    private void register() {
        System.out.println("nadarim hanoz!\n" +
                "zoor nazan farsi neveshtam!!");
    }
}
