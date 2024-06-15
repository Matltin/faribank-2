package ir.ac.kntu.menu.customer.accountmangemenu;

import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.customer.accountmangemenu.recenttransactionmenu.RecentTransactionMenu;
import ir.ac.kntu.person.customer.Customer;

import java.text.ParseException;

public class AccountMangeMenu extends Menu {

    private Customer customer;
    private CustomerDB customerDB;
    private RecentTransactionMenu recentTransMenu;

    public AccountMangeMenu(CustomerDB customerDB, RecentTransactionMenu recentTransMenu) {
        this.customerDB = customerDB;
        this.recentTransMenu = recentTransMenu;
    }

    public void show(Customer customer) throws ParseException {
        this.customer = customer;
        show();
    }

    @Override
    public void show() throws ParseException {
        System.out.println("account Menu");
        AccountMangeMenuOption accMngMenuOption = printMenuOption();
        while (accMngMenuOption != AccountMangeMenuOption.BACK) {
            if (accMngMenuOption != null) {
                switch (accMngMenuOption) {
                    case INCREASE_CREDIT -> increaseCredit();
                    case ACCOUNT_BALANCE -> showAccountBalance();
                    case TRANSACTION_LIST -> recentTransMenu.show(customer);
//                        showTransactionList();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            accMngMenuOption = printMenuOption();
        }
    }

    private AccountMangeMenuOption printMenuOption() {
        System.out.println("----------account Menu----------");
        AccountMangeMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(AccountMangeMenuOption.class);
    }

    private void increaseCredit() {
        long inputMoney = getInputMoney();
        customer.getAccount().increaseCredit(inputMoney, customerDB);
    }

    private void showAccountBalance() {
        System.out.println("Card number : " + customer.getAccount().getCard().getCardNumber());
        System.out.println("Account number : " + customer.getAccount().getAccountNO());
        System.out.println(customer.getAccount().getBalance());
    }

//    private void showTransactionList() {
//        for (Transaction transaction : customer.getAccount().getTransactionDB().getTransactions()) {
//            System.out.println(transaction);
//        }
//    }
}
