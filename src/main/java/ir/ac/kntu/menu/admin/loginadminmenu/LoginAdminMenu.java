package ir.ac.kntu.menu.admin.loginadminmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.AdminDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.admin.adminmenu.AdminMenu;
import ir.ac.kntu.person.admin.Admin;

public class LoginAdminMenu extends Menu {

    private AdminDB adminDB;
    private AdminMenu adminMenu;

    public LoginAdminMenu(AdminDB adminDB, AdminMenu adminMenu) {
        this.adminDB = adminDB;
        this.adminMenu = adminMenu;
    }

    @Override
    public void show() {
        System.out.println("logging page");
        LoginAdminMenuOption adminMenuOption = printMenuOption();
        while (adminMenuOption != LoginAdminMenuOption.BACK) {
            if (adminMenuOption != null) {
                switch (adminMenuOption) {
                    case LOGIN -> login();
                    case REGISTER -> register();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            adminMenuOption = printMenuOption();
        }
    }

    private LoginAdminMenuOption printMenuOption() {
        System.out.println("----------logging admin Menu----------");
        LoginAdminMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(LoginAdminMenuOption.class);
    }

    private void login() {
        String userName = getUserName();
        String password = getPassword();
        for (Admin admin : adminDB.getAdmins()) {
            if (admin.getUserName().equals(userName) && admin.getPassword().equals(password)) {
                adminMenu.show();
                return;
            }
        }
        System.out.println(Constance.RED + "invalid username or password" + Constance.RESET);
    }

    private void register() {
        System.out.println("nadarim hanoz!" +
                "zoor nazan farsi neveshtam!!");
    }
}
