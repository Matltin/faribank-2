package ir.ac.kntu.menu.customer.accountnumbermenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.BankDB;
import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.db.PayaDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.ContactPerson;
import ir.ac.kntu.person.customer.Customer;

public class AccountNumberMenu extends Menu {

    private CustomerDB customerDB;
    private Customer customer;
    private BankDB bankDB;
    private PayaDB payaDB;

    public AccountNumberMenu(CustomerDB customerDB, BankDB bankDB, PayaDB payaDB) {
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
        AccountNumberMenuOption numberMenuOption = printMenuOption();
        while (numberMenuOption != AccountNumberMenuOption.BACK) {
            if (numberMenuOption != null) {
                switch (numberMenuOption) {
                    case TRANSFER_TO_FARI -> transferToFari();
                    case TRANSFER_PAYA -> transferPaya();
                    case TRANSFER_POLE -> transferPole();
                    default -> System.out.print("");
                }
            } else {
                System.out.println(Constance.RED + "invalid input!!" + Constance.RESET);
            }
            numberMenuOption = printMenuOption();
        }
    }

    private AccountNumberMenuOption printMenuOption() {
        System.out.println("----------customer Menu----------");
        AccountNumberMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(AccountNumberMenuOption.class);
    }

    private void transferToFari() {
        String accountNo = getAccountNumber();
        Customer cust = customerDB.findCustomerByAccountNO(accountNo);
        if (cust != null) {
            if(!isAcceptedCustomer(cust)) {
                System.out.println(Constance.RED + "There is no customer" + Constance.RESET);
                return;
            }
            long inputMoney = getInputMoney();
            boolean check = customer.getAccount().transferFari(inputMoney, accountNo, customerDB);
            if(!check) {
                return;
            }
            ContactPerson contactPerson1 = new ContactPerson(cust.getFirstName(), cust.getLastName(), cust.getPhoneNumber(), cust.getAccount().getAccountNO());
            customer.getRecentTransaction().addContactPersonList(contactPerson1);
        } else {
            System.out.println("There is co person with this account number !!");
        }
    }

    private void transferPaya() {
        String accountNo = getAccountNumber();
        Customer cust = bankDB.findCustomerByAccNumber(accountNo);
        if(cust == null) {
            System.out.println(Constance.RED + "customer not found!!" + Constance.RESET);
            return;
        }
        long inputMoney = getInputMoney();
        boolean check = customer.getAccount().transferPaya(inputMoney, customer, cust, payaDB);
        if(!check) {
            return;
        }
        System.out.println(Constance.YELLOW + "in progressing" + Constance.RESET);
    }

    private void transferPole() {
        String accountNo = getAccountNumber();
        Customer cust = bankDB.findCustomerByAccNumber(accountNo);
        if(cust == null) {
            System.out.println(Constance.RED + "customer not found!!" + Constance.RESET);
            return;
        }
        long inputMoney = getInputMoney();
        boolean check = customer.getAccount().transferPole(inputMoney, accountNo, bankDB);
        if(!check) {
            return;
        }
        ContactPerson contactPerson1 = new ContactPerson(cust.getFirstName(), cust.getLastName(), cust.getPhoneNumber(), cust.getAccount().getAccountNO());
        customer.getRecentTransaction().addContactPersonList(contactPerson1);
    }
}
