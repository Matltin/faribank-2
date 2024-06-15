package ir.ac.kntu.menu.admin.useraccessmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.admin.searchusermenu.SearchUserMenu;
import ir.ac.kntu.person.customer.Customer;

public class UserAccessMenu extends Menu {

    private CustomerDB customerDB;
    private SearchUserMenu searchUserMenu;

    public UserAccessMenu(CustomerDB customerDB, SearchUserMenu searchUserMenu) {
        this.customerDB = customerDB;
        this.searchUserMenu = searchUserMenu;
    }

    @Override
    public void show() {
        System.out.println("user access menu");
        UserAccessMenuOption usrAcsMenuOption = printMenuOption();
        while (usrAcsMenuOption != UserAccessMenuOption.BACK) {
            if (usrAcsMenuOption != null) {
                switch (usrAcsMenuOption) {
                    case SHOW_USER -> showUser();
                    case SEARCH_USER -> searchUserMenu.show();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            usrAcsMenuOption = printMenuOption();
        }
    }

    private UserAccessMenuOption printMenuOption() {
        System.out.println("----------user access Menu----------");
        UserAccessMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(UserAccessMenuOption.class);
    }

    private void showUser() {
        int counter = 1;
        if(customerDB.size() == 0) {
            System.out.println(Constance.RED + "it is empty!!" + Constance.RESET);
            return;
        }
        for (Customer customer : customerDB.getCustomers()) {
            System.out.println(counter + "." + customer.getFirstName() + " " + customer.getLastName() + " " + customer.getPhoneNumber());
            counter++;
        }


    }
}
