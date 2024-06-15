package ir.ac.kntu.menu.customer.accountmangemenu.recenttransactionmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.customer.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecentTransactionMenu extends Menu {

    private Customer customer;

    public void show(Customer customer) throws ParseException {
        this.customer = customer;
        show();
    }

    @Override
    public void show() throws ParseException {
        System.out.println("search by");
        RecentTransactionMenuOption rctTransOption = printMenuOption();
        while (rctTransOption != RecentTransactionMenuOption.BACK) {
            if (rctTransOption != null) {
                switch (rctTransOption) {
                    case BY_NUMBER -> searchByNumber();
                    case BY_TIME -> searchByTime();
                    case ALL -> searchAll();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            rctTransOption = printMenuOption();
        }
    }

    private RecentTransactionMenuOption printMenuOption() {
        System.out.println("----------search by----------");
        RecentTransactionMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(RecentTransactionMenuOption.class);
    }

    private void searchByNumber() {
        try {
            int size = customer.getAccount().getTransactionDB().getTransactions().size();
            if (size == 0) {
                throw new RuntimeException("there is no transaction yet!");
            }
            int number = getNumber();
            for (int i = size - 1; i >= size - number; i--) {
                System.out.println(customer.getAccount().getTransactionDB().getTransactions().get(i));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void searchByTime() throws ParseException {
        int size = customer.getAccount().getTransactionDB().getTransactions().size();
        if(size == 0) {
            System.out.println(Constance.RED + "there is no transaction yet!" + Constance.RESET);
            return;
        }
        try {
            String firstStr = getDate();
            String secondStr = getDate();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd -- HH:mm:ss");

            Date firstDate = simpleDateFormat.parse(firstStr);
            Date secondDate = simpleDateFormat.parse(secondStr);

            for (int i = size - 1; i >= 0; i++) {
                long time = customer.getAccount().getTransactionDB().getTransactions().get(i).getDate().getTime();
                if (firstDate.getTime() <= time && time <= secondDate.getTime()) {
                    System.out.println(customer.getAccount().getTransactionDB().getTransactions().get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(Constance.RED + "invalid input!!" + Constance.RESET);
        }
    }

    private void searchAll() {
        try {
            int size = customer.getAccount().getTransactionDB().getTransactions().size();
            if (size == 0) {
                throw new RuntimeException("there is no transaction yet!");
            }
            for (int i = size - 1; i >= 0; i--) {
                System.out.println(customer.getAccount().getTransactionDB().getTransactions().get(i));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
