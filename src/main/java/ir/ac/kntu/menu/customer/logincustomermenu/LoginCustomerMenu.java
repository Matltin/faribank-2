package ir.ac.kntu.menu.customer.logincustomermenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.customer.customermenu.CustomerMenu;
import ir.ac.kntu.person.customer.Customer;
import ir.ac.kntu.person.customer.State;

import java.text.ParseException;

public class LoginCustomerMenu extends Menu {

    private CustomerDB customerDB;
    private CustomerMenu customerMenu;

    public LoginCustomerMenu(CustomerDB customerDB, CustomerMenu customerMenu) {
        this.customerDB = customerDB;
        this.customerMenu = customerMenu;
    }

    public LoginCustomerMenu(CustomerDB customerDB) {
        this.customerDB = customerDB;
    }

    @Override
    public void show() throws ParseException {
        System.out.println("logging page");
        LoginCustomerMenuOption custMenuOption = printMenuOption();
        while (custMenuOption != LoginCustomerMenuOption.BACK) {
            if (custMenuOption != null) {
                switch (custMenuOption) {
                    case LOGIN -> login();
                    case REGISTER -> register();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            custMenuOption = printMenuOption();
        }
    }

    private LoginCustomerMenuOption printMenuOption() {
        System.out.println("----------logging customer Menu----------");
        LoginCustomerMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(LoginCustomerMenuOption.class);
    }

    private void login() throws ParseException {
        String iDocument = getIDocument();
        String phoneNumber = getPhoneNumber();
        Customer cust = null;
        for (Customer customer : customerDB.getCustomers()) {
            if (customer.getIDocument().equals(iDocument) && customer.getPhoneNumber().equals(phoneNumber)) {
                cust = customer;
                break;
            }
        }
        if (cust != null) {
            if(cust.getState() == State.ACCEPTED) {
                customerMenu.show(cust);
            } else if(cust.getState() == State.IN_PROGRESSING) {
                System.out.println(Constance.YELLOW + "in progressing!!" + Constance.RESET);
            } else if(cust.getState() == State.REJECT) {
                customerDB.removeCustomer(cust);
                System.out.println(cust.getMessageDB().getMessageList().get(0));
            }
        } else {
            System.out.println(Constance.RED + "IDocument or PhoneNumber is invalid!!" + Constance.RESET);
        }
    }

    private void register() {
        String firstName = getFirstName();
        String lastName = getLastName();
        String phoneNumber = getPhoneNumber();
        String iDocument = getIDocument();
        String password = getPassword();
        for (Customer customer : customerDB.getCustomers()) {
            if (customer.getPhoneNumber().equals(phoneNumber) || customer.getIDocument().equals(iDocument)) {
                System.out.println(Constance.RED + "the phone number or the iDocument is already exist" + Constance.RESET);
                return;
            }
        }
        Customer customer = new Customer(firstName, lastName, password, iDocument, phoneNumber);
        customerDB.addCustomer(customer);
    }
}
