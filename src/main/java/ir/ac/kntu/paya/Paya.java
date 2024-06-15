package ir.ac.kntu.paya;

import ir.ac.kntu.person.customer.Customer;

public class Paya {
    private Customer sourceCustomer;
    private Customer destinationCustomer;
    private long money;

    public Paya(Customer sourceCustomer, Customer destinationCustomer, long money) {
        this.sourceCustomer = sourceCustomer;
        this.destinationCustomer = destinationCustomer;
        this.money = money;
    }

    public Customer getSourceCustomer() {
        return sourceCustomer;
    }

    public void setSourceCustomer(Customer sourceCustomer) {
        this.sourceCustomer = sourceCustomer;
    }

    public Customer getDestinationCustomer() {
        return destinationCustomer;
    }

    public void setDestinationCustomer(Customer destinationCustomer) {
        this.destinationCustomer = destinationCustomer;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}
