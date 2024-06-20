package ir.ac.kntu.menu.customer.cardmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.BankDB;
import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.db.PayaDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.ContactPerson;
import ir.ac.kntu.person.customer.Customer;

public class CardMenu extends Menu {

    private CustomerDB customerDB;
    private BankDB bankDB;
    private PayaDB payaDB;
    private Customer customer;

    public CardMenu(CustomerDB customerDB, BankDB bankDB, PayaDB payaDB) {
        this.customerDB = customerDB;
        this.bankDB = bankDB;
        this.payaDB = payaDB;
    }

    public void show(Customer customer) {
        this.customer = customer;
        show();
    }

    @Override
    public void show() {
        System.out.println("transfer menu");
        CardMenuOption cardMenuOption = printMenuOption();
        while (cardMenuOption != CardMenuOption.BACK) {
            if (cardMenuOption != null) {
                switch (cardMenuOption) {
                    case TRANSFER_TO_FARI -> transferToFari();
                    case TRANSFER_PAYA -> transferPaya();
                    case TRANSFER_POLE -> transferPole();
                    default -> System.out.print("");
                }
            } else {
                System.out.println(Constance.RED + "invalid input!!" + Constance.RESET);
            }
            cardMenuOption = printMenuOption();
        }
    }

    private CardMenuOption printMenuOption() {
        System.out.println("----------customer Menu----------");
        CardMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(CardMenuOption.class);
    }

    private void transferToFari() {
        String cardNumber = getCardNumber();
        String accountNumber = customerDB.getAccountNumber(cardNumber);
        if(accountNumber == null) {
            System.out.println(Constance.RED + "there is no customer with this card number!!" + Constance.RESET);
            return;
        }
        Customer cust = customerDB.findCustomerByAccountNO(accountNumber);
        if(!isAcceptedCustomer(cust)) {
            System.out.println(Constance.RED + "There is no customer" + Constance.RESET);
            return;
        }
        long inputMoney = getInputMoney();
        boolean check = customer.getAccount().transferFari(inputMoney, accountNumber, customerDB);
        if(!check) {
            return;
        }
        ContactPerson contactPerson1 = new ContactPerson(cust.getFirstName(), cust.getLastName(), cust.getPhoneNumber(), cust.getAccount().getAccountNO());
        customer.getRecentTransaction().addContactPersonList(contactPerson1);
    }

    private void transferPaya() {
        String cardNumber = getCardNumber();
        String accountNumber = bankDB.getAccountNumber(cardNumber);
        if(accountNumber == null) {
            System.out.println(Constance.RED + "there is no customer with this card number!!" + Constance.RESET);
            return;
        }
        Customer cust = bankDB.findCustomerByAccNumber(accountNumber);
        long inputMoney = getInputMoney();
        boolean check = customer.getAccount().transferPaya(inputMoney, customer, cust, payaDB);
        if(!check) {
            return;
        }
        System.out.println(Constance.YELLOW + "in progressing" + Constance.RESET);
    }

    private void transferPole() {
        String cardNumber = getCardNumber();
        String accountNumber = bankDB.getAccountNumber(cardNumber);
        if(accountNumber == null) {
            System.out.println(Constance.RED + "there is no customer with this card number!!" + Constance.RESET);
            return;
        }
        Customer cust = bankDB.findCustomerByAccNumber(accountNumber);
        if(cust == null) {
            System.out.println(Constance.RED + "customer not found!!" + Constance.RESET);
            return;
        }
        long inputMoney = getInputMoney();
        boolean check = customer.getAccount().transferPole(inputMoney, accountNumber, bankDB);
        if(!check) {
            return;
        }
        ContactPerson contactPerson1 = new ContactPerson(cust.getFirstName(), cust.getLastName(), cust.getPhoneNumber(), cust.getAccount().getAccountNO());
        customer.getRecentTransaction().addContactPersonList(contactPerson1);
    }
}
