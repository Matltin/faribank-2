package ir.ac.kntu.menu.chief.autotransactionmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.box.Box;
import ir.ac.kntu.box.BoxType;
import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.db.PayaDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.paya.Paya;
import ir.ac.kntu.person.ContactPerson;
import ir.ac.kntu.person.customer.Customer;
import ir.ac.kntu.transaction.Transaction;
import ir.ac.kntu.transaction.TransactionType;

import java.util.Date;

public class AutoTransaction extends Menu {

    private PayaDB payaDB;
    private CustomerDB customerDB;

    public AutoTransaction(PayaDB payaDB, CustomerDB customerDB) {
        this.payaDB = payaDB;
        this.customerDB = customerDB;
    }

    @Override
    public void show() {
        System.out.println("auto transaction Menu");
        AutoTransactionOption autoTransactionOption = printMenuOption();
        while (autoTransactionOption != AutoTransactionOption.BACK) {
            if (autoTransactionOption != null) {
                switch (autoTransactionOption) {
                    case TRANSFER -> transfer();
                    case BOX -> boxProfit();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            autoTransactionOption = printMenuOption();
        }
    }

    private AutoTransactionOption printMenuOption() {
        System.out.println("----------auto transaction Menu----------");
        AutoTransactionOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(AutoTransactionOption.class);
    }

    private void transfer() {
        payaDB.printPayaDB();
        if(payaDB.size() == 0) {
            System.out.println(Constance.RED + "There is no paya transaction to show" + Constance.RESET);
            return;
        }
        int number = getNumber();
        Paya paya;
        if(0 < number && number <= payaDB.size()) {
            paya = payaDB.getIndex(number - 1);
            String yesNo = getYesNo();
            if("Y".equals(yesNo)) {
                accessTransfer(paya);
                payaDB.removePaya(paya);
            } else if("N".equals(yesNo)) {
                payaDB.removePaya(paya);
            } else {
                System.out.println(Constance.RED + "invalid input!!" + Constance.RESET);
            }
        } else {
            System.out.println(Constance.RED + "invalid input!!" + Constance.RESET);
        }
    }

    private void boxProfit() {
        int counter = showBoxProfit();
        if(counter == 1) {
            System.out.println(Constance.RED + "there is no box to show!!" + Constance.RESET);
            return;
        }
        int number = getNumber();
        if(!(0 < number && number < counter)) {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
            return;
        }
        Box box = returnBox(number);
        Customer customer = returnCustomer(number);
        long money = (long)(box.getBalance() * Constance.getPROFIT())/100;
        customer.getAccount().deposit(money);
    }

    private void accessTransfer(Paya paya) {
        Customer sourceCustomer = paya.getSourceCustomer();
        Customer destinationCustomer = paya.getDestinationCustomer();
        long inputMoney = paya.getMoney();
        sourceCustomer.getAccount().withdraw(inputMoney + Constance.getFariPaya());

        Transaction transaction = new Transaction(destinationCustomer.getFirstName(), destinationCustomer.getLastName(),
                destinationCustomer.getAccount().getAccountNO(), sourceCustomer.getAccount().getAccountNO(), TransactionType.TRANSFER);
        sourceCustomer.getAccount().getTransactionDB().addTransaction(transaction);

        ContactPerson contactPerson1 = new ContactPerson(destinationCustomer.getFirstName(), destinationCustomer.getLastName(),
                destinationCustomer.getPhoneNumber(), destinationCustomer.getAccount().getAccountNO());
        sourceCustomer.getRecentTransaction().addContactPersonList(contactPerson1);

        sourceCustomer.getAccount().roundBalance();

        destinationCustomer.getAccount().deposit(inputMoney);
    }

    private int showBoxProfit() {
        Date nowDate = new Date();
        int counter = 1;
        for(Customer customer : customerDB.getCustomers()) {
            for(Box box : customer.getAccount().getBoxDB().getBoxes()) {
                long diff = nowDate.getTime() - box.getDate().getTime();
                if(diff > Constance.mileSecond && box.getBoxType() == BoxType.PROFIT) {
                    System.out.println(counter + "." + box);
                    counter++;
                }
            }
        }
        return counter;
    }

    private Box returnBox(int number) {
        Date nowDate = new Date();
        int counter = 1;
        for(Customer customer : customerDB.getCustomers()) {
            for(Box box : customer.getAccount().getBoxDB().getBoxes()) {
                long diff = nowDate.getTime() - box.getDate().getTime();
                if(diff > Constance.mileSecond && box.getBoxType() == BoxType.PROFIT) {
                    if(number == counter) {
                        return box;
                    }
                    counter++;
                }
            }
        }
        return null;
    }

    private Customer returnCustomer(int number) {
        Date nowDate = new Date();
        int counter = 1;
        for(Customer customer : customerDB.getCustomers()) {
            for(Box box : customer.getAccount().getBoxDB().getBoxes()) {
                long diff = nowDate.getTime() - box.getDate().getTime();
                if(diff > Constance.mileSecond && box.getBoxType() == BoxType.PROFIT) {
                    if(number == counter) {
                        return customer;
                    }
                    counter++;
                }
            }
        }
        return null;
    }
}
