package ir.ac.kntu.menu.chief.chiefmenu;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.chief.Chief;

import java.text.ParseException;

public class ChiefMenu extends Menu {

    private Chief chief;
    private ManageUserMenu manageUserMenu;
    private SettingMenu settingMenu;
    private AutoTransaction autoTransaction;

    public ChiefMenu(ManageUserMenu manageUserMenu, SettingMenu settingMenu, AutoTransaction autoTransaction) {
        this.manageUserMenu = manageUserMenu;
        this.settingMenu = settingMenu;
        this.autoTransaction = autoTransaction;
    }

    public void show(Chief chief) throws ParseException {
        this.chief = chief;
        show();
    }

    @Override
    public void show() throws ParseException {
        System.out.println("Chief Menu");
        ChiefMenuOption chiefMenuOption = printMenuOption();
        while (chiefMenuOption != ChiefMenuOption.BACK) {
            if (chiefMenuOption != null) {
                switch (chiefMenuOption) {
                    case MANAGE_USERS -> manageUserMenu.show(chief);
                    case AUTO_TRANSACTION -> autoTransaction.show();
                    case SETTING -> settingMenu.show();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            chiefMenuOption = printMenuOption();
        }
    }

    private ChiefMenuOption printMenuOption() {
        System.out.println("----------chief Menu----------");
        ChiefMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(ChiefMenuOption.class);
    }
}
