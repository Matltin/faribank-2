package ir.ac.kntu.menu.mainmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.admin.loginadminmenu.LoginAdminMenu;
import ir.ac.kntu.menu.chief.loginchiefmenu.LoginChiefMenu;
import ir.ac.kntu.menu.customer.logincustomermenu.LoginCustomerMenu;

import java.text.ParseException;

public class MainMenu extends Menu {

    private LoginAdminMenu loginAdminMenu;
    private LoginCustomerMenu loginCustomerMenu;
    private LoginChiefMenu loginChiefMenu;

    public MainMenu(LoginAdminMenu loginAdminMenu, LoginCustomerMenu loginCustomerMenu, LoginChiefMenu loginChiefMenu) {
        this.loginAdminMenu = loginAdminMenu;
        this.loginCustomerMenu = loginCustomerMenu;
        this.loginChiefMenu = loginChiefMenu;
    }

    @Override
    public void show() throws ParseException {
        System.out.println("Welcome to Fari Bank");
        MainMenuOption mainMenuOption = printMenuOptions();
        while (mainMenuOption != MainMenuOption.EXIT) {
            if (mainMenuOption != null) {
                switch (mainMenuOption) {
                    case ADMIN -> loginAdminMenu.show();
                    case CUSTOMER -> loginCustomerMenu.show();
                    case CHIEF -> loginChiefMenu.show();
                    default -> System.out.print("");
                }
            } else {
                System.out.println(Constance.RED + "invalid input!!");
            }
            mainMenuOption = printMenuOptions();
        }
    }

    private MainMenuOption printMenuOptions() {
        System.out.println("----------main Menu----------");
        MainMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(MainMenuOption.class);
    }
}
