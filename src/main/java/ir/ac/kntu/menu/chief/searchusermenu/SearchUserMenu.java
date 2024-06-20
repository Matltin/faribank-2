package ir.ac.kntu.menu.chief.searchusermenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.AdminDB;
import ir.ac.kntu.db.ChiefDB;
import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.chief.userrolemenu.UserRoleMenu;
import ir.ac.kntu.person.admin.Admin;
import ir.ac.kntu.person.chief.Chief;
import ir.ac.kntu.person.customer.Customer;

public class SearchUserMenu extends Menu {

    private CustomerDB customerDB;
    private AdminDB adminDB;
    private ChiefDB chiefDB;
    private UserRoleMenu userRoleMenu;

    public SearchUserMenu(CustomerDB customerDB, AdminDB adminDB, ChiefDB chiefDB, UserRoleMenu userRoleMenu) {
        this.customerDB = customerDB;
        this.adminDB = adminDB;
        this.chiefDB = chiefDB;
        this.userRoleMenu = userRoleMenu;
    }

    @Override
    public void show() {
        System.out.println("manage user Menu");
        SearchUserMenuOption userMenuOption = printMenuOption();
        while (userMenuOption != SearchUserMenuOption.BACK) {
            if (userMenuOption != null) {
                switch (userMenuOption) {
                    case BY_FIRST_NAME -> searchByFirstName();
                    case BY_LAST_NAME -> searchByLastName();
                    case BY_PHONE_NUMBER -> searchByPhoneNumber();
                    case BY_USER_ROLE -> userRoleMenu.show();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            userMenuOption = printMenuOption();
        }
    }

    private SearchUserMenuOption printMenuOption() {
        System.out.println("----------admin Menu----------");
        SearchUserMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(SearchUserMenuOption.class);
    }

    private void searchByFirstName() {
        boolean customerExist = false;
        boolean adminExist = false;
        boolean chiefExist = false;
        String firstName = getFirstName();
        for(Customer customer : customerDB.getCustomers()) {
            if(customer.getFirstName().equals(firstName)) {
                System.out.println(customer + " " + customer.getClass());
                customerExist = true;
            }
        }
        for(Admin admin : adminDB.getAdmins()) {
            if(admin.getFirstName().equals(firstName)) {
                System.out.println(admin + " " + admin.getClass());
                adminExist = true;
            }
        }
        for(Chief chief : chiefDB.getChiefs()) {
            if(chief.getFirstName().equals(firstName)) {
                System.out.println(chief + " " + chief.getClass());
                chiefExist = true;
            }
        }
        if(!customerExist && !adminExist && !chiefExist) {
            System.out.println(Constance.RED + "user not found!!" + Constance.RESET);
        }
    }

    private void searchByLastName() {
        boolean customerExist = false;
        boolean adminExist = false;
        boolean chiefExist = false;
        String lastName = getLastName();
        for(Customer customer : customerDB.getCustomers()) {
            if(customer.getLastName().equals(lastName)) {
                System.out.println(customer + " " + customer.getClass());
                customerExist = true;
            }
        }
        for(Admin admin : adminDB.getAdmins()) {
            if(admin.getLastName().equals(lastName)) {
                System.out.println(admin + " " + admin.getClass());
                adminExist = true;
            }
        }
        for(Chief chief : chiefDB.getChiefs()) {
            if(chief.getLastName().equals(lastName)) {
                System.out.println(chief + " " + chief.getClass());
                chiefExist = true;
            }
        }
        if(!customerExist && !adminExist && !chiefExist) {
            System.out.println(Constance.RED + "user not found!!" + Constance.RESET);
        }
    }

    private void searchByPhoneNumber() {
        if(customerDB.size() == 0) {
            System.out.println("there is no customer show!!");
            return;
        }
        String phoneNumber = getPhoneNumber();
        for(Customer customer : customerDB.getCustomers()) {
            if(customer.getPhoneNumber().equals(phoneNumber)) {
                System.out.println(customer + " " + customer.getClass());
                return;
            }
        }
        System.out.println(Constance.RED + "user not found!!" + Constance.RESET);
    }
}
