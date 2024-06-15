package ir.ac.kntu.menu.chief.userrolemenu;

import ir.ac.kntu.db.AdminDB;
import ir.ac.kntu.db.ChiefDB;
import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.admin.Admin;
import ir.ac.kntu.person.chief.Chief;
import ir.ac.kntu.person.customer.Customer;

public class UserRoleMenu extends Menu {

    private CustomerDB customerDB;
    private AdminDB adminDB;
    private ChiefDB chiefDB;

    public UserRoleMenu(CustomerDB customerDB, AdminDB adminDB, ChiefDB chiefDB) {
        this.customerDB = customerDB;
        this.adminDB = adminDB;
        this.chiefDB = chiefDB;
    }

    @Override
    public void show() {
        System.out.println("manage user Menu");
        UserRoleMenuOption userRoleMenuOption = printMenuOption();
        while (userRoleMenuOption != UserRoleMenuOption.BACK) {
            if (userRoleMenuOption != null) {
                switch (userRoleMenuOption) {
                    case ADMIN -> admin();
                    case CHIEF -> chief();
                    case CUSTOMER -> customer();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            userRoleMenuOption = printMenuOption();
        }
    }

    private UserRoleMenuOption printMenuOption() {
        System.out.println("----------admin Menu----------");
        UserRoleMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(UserRoleMenuOption.class);
    }

    private void admin() {
        int counter = 1;
        if(adminDB.getAdmins().isEmpty()) {
            System.out.println("there is no admin to show!!");
            return;
        }
        for(Admin admin : adminDB.getAdmins()) {
            System.out.println(counter + "." + admin.getFirstName() + " " + admin.getLastName());
            counter++;
        }
        int number = getNumber();
        int i = 0;
        if(0 < number && number < counter) {
            for(Admin admin : adminDB.getAdmins()) {
                i++;
                if(i == number) {
                    System.out.println(admin + " " + admin.getClass());
                }
            }
        }
    }
    private void chief() {
        int counter = 1;
        if(chiefDB.getChiefs().isEmpty()) {
            System.out.println("there is no admin to show!!");
            return;
        }
        for(Chief chief : chiefDB.getChiefs()) {
            System.out.println(counter + "." + chief.getFirstName() + " " + chief.getLastName());
            counter++;
        }
        int number = getNumber();
        int i = 0;
        if(0 < number && number < counter) {
            for(Chief chief : chiefDB.getChiefs()) {
                i++;
                if(i == number) {
                    System.out.println(chief + " " + chief.getClass());
                }
            }
        }
    }
    private void customer() {
        int counter = 1;
        if(customerDB.getCustomers().isEmpty()) {
            System.out.println("there is no admin to show!!");
            return;
        }
        for(Customer customer : customerDB.getCustomers()) {
            System.out.println(counter + "." + customer.getFirstName() + " " + customer.getLastName());
            counter++;
        }
        int number = getNumber();
        int i = 0;
        if(0 < number && number < counter) {
            for(Customer customer : customerDB.getCustomers()) {
                i++;
                if(i == number) {
                    System.out.println(customer + " " + customer.getClass());
                }
            }
        }
    }
    
}
