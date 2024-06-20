package ir.ac.kntu.menu.chief.editmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.AdminDB;
import ir.ac.kntu.db.ChiefDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.admin.Admin;
import ir.ac.kntu.person.chief.Chief;

public class EditUserMenu extends Menu {

    private AdminDB adminDB;
    private ChiefDB chiefDB;

    public EditUserMenu(AdminDB adminDB, ChiefDB chiefDB) {
        this.adminDB = adminDB;
        this.chiefDB = chiefDB;
    }

    @Override
    public void show() {
        System.out.println("logging page");
        EditUserMenuOption userMenuOption = printMenuOption();
        while (userMenuOption != EditUserMenuOption.BACK) {
            if (userMenuOption != null) {
                switch (userMenuOption) {
                    case ADMIN -> editAdmin();
                    case CHIEF -> editChief();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            userMenuOption = printMenuOption();
        }
    }

    private EditUserMenuOption printMenuOption() {
        System.out.println("----------logging admin Menu----------");
        EditUserMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(EditUserMenuOption.class);
    }

    private void editAdmin() {
        String userName = getUserName();
        Admin admin = adminDB.findAdmin(userName);
        if (admin == null) {
            System.out.println(Constance.RED + "there is no admin with this user name" + Constance.RESET);
            return;
        }
        admin.setFirstName(getFirstName());
        admin.setLastName(getLastName());
        admin.setUserName(getUserName());
        admin.setPassword(getPassword());
        System.out.println("information changed!!");
    }

    private void editChief() {
        String userName = getUserName();
        Chief chief = chiefDB.findChief(userName);
        if (chief == null) {
            System.out.println(Constance.RED + "there is no chief with this user name" + Constance.RESET);
            return;
        }
        chief.setFirstName(getFirstName());
        chief.setLastName(getLastName());
        chief.setUserName(getUserName());
        chief.setPassword(getPassword());
        System.out.println("information changed!!");
    }
}
