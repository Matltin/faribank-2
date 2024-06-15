package ir.ac.kntu.menu.chief.addusermenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.AdminDB;
import ir.ac.kntu.db.ChiefDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.admin.Admin;
import ir.ac.kntu.person.chief.Chief;

public class AddUserMenu extends Menu {

    private Chief chief;
    private ChiefDB chiefDB;
    private AdminDB adminDB;

    public AddUserMenu(ChiefDB chiefDB, AdminDB adminDB) {
        this.chiefDB = chiefDB;
        this.adminDB = adminDB;
    }

    public void show(Chief chief) {
        this.chief = chief;
        show();
    }

    @Override
    public void show() {
        System.out.println("request menu");
        AddUserMenuOption addUserMenuOption = printMenuOption();
        while (addUserMenuOption != AddUserMenuOption.BACK) {
            if (addUserMenuOption != null) {
                switch (addUserMenuOption) {
                    case ADD_ADMIN -> addAdmin();
                    case ADD_CHIEF -> addChief();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            addUserMenuOption = printMenuOption();
        }
    }

    private AddUserMenuOption printMenuOption() {
        System.out.println("----------request Menu----------");
        AddUserMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(AddUserMenuOption.class);
    }

    private void addAdmin() {
        String firstName = getFirstName();
        String lastName = getLastName();
        String password = getPassword();
        String userName = getUserName();
        Admin admin = adminDB.findAdmin(userName);
        if(admin != null) {
            System.out.println(Constance.RED + "this admin is already exist!!" + Constance.RESET);
            return;
        }
        admin = new Admin(firstName, lastName, password, userName);
        adminDB.addAdmin(admin);
    }

    private void addChief() {
        String firstName = getFirstName();
        String lastName = getLastName();
        String password = getPassword();
        String userName = getUserName();
        Chief chief = chiefDB.findChief(userName);
        if(chief != null) {
            System.out.println(Constance.RED + "this chief is already exist!!" + Constance.RESET);
            return;
        }
        chief = new Chief(firstName, lastName, password, userName, this.chief.getPosition() + 1);
        chiefDB.addChief(chief);
    }

}
