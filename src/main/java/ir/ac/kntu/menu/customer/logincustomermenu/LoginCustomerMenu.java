package ir.ac.kntu.menu.customer.logincustomermenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.AnswerDB;
import ir.ac.kntu.db.BankDB;
import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.db.SimCardDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.customer.customermenu.CustomerMenu;
import ir.ac.kntu.message.Message;
import ir.ac.kntu.message.MessageOption;
import ir.ac.kntu.person.customer.Customer;
import ir.ac.kntu.person.customer.State;

import java.text.ParseException;

public class LoginCustomerMenu extends Menu {

    private CustomerDB customerDB;
    private SimCardDB simCardDB;
    private CustomerMenu customerMenu;
    private BankDB bankDB;
    private AnswerDB answerDB;

    public LoginCustomerMenu(CustomerDB customerDB, SimCardDB simCardDB, CustomerMenu customerMenu, BankDB bankDB, AnswerDB answerDB) {
        this.customerDB = customerDB;
        this.simCardDB = simCardDB;
        this.customerMenu = customerMenu;
        this.bankDB = bankDB;
        this.answerDB = answerDB;
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
                checkExist(cust);
                customerMenu.show(cust);
            } else if(cust.getState() == State.IN_PROGRESSING) {
                System.out.println(Constance.YELLOW + "in progressing!!" + Constance.RESET);
            } else if(cust.getState() == State.REJECT) {
                customerDB.removeCustomer(cust);
                System.out.println(cust.getMessageDB().getMessageList().get(1));
                register();
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
        Customer customer = new Customer(firstName, lastName, password, iDocument, phoneNumber, simCardDB);
        String request = "want to have account";
        Message message = new Message(phoneNumber, request, MessageOption.REPORT);
        customer.getMessageDB().addMessage(message);
        answerDB.add(message);
        customerDB.addCustomer(customer);
    }

    private void checkExist(Customer cust) {
        if(!bankDB.contain(cust)) {
            bankDB.addCustomer(cust);
        }
    }
}
