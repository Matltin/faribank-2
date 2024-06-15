package ir.ac.kntu.profitthread;

import ir.ac.kntu.Constant;
import ir.ac.kntu.box.Box;
import ir.ac.kntu.person.customer.Customer;

public class ProfitThread implements Runnable{
    private Box box;
    private Customer customer;

    public void setBox(Box box, Customer customer) {
        this.box = box;
        this.customer = customer;
    }

    @Override
    public void run() {
        long money = (long) (box.getBalance() * Constant.getPROFIT())/100;
        customer.getAccount().deposit(money);
        box.setCountMonth(box.getCountMonth() - 1);
        box.makeNewDate();
    }
}
