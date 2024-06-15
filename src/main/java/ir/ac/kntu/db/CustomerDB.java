package ir.ac.kntu.db;

import ir.ac.kntu.person.customer.Customer;

import java.util.Set;

public class CustomerDB {

    private Set<Customer> customers;

    public CustomerDB(Set<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        try {
            if (doesExist(customer)) {
                customers.remove(customer);
            } else {
                throw new RuntimeException("customer not found!!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Customer findCustomerByAccountNO(String accountNO) {
        for (Customer customer : customers) {
            if (customer.getAccount().getAccountNO().equals(accountNO)) {
                return customer;
            }
        }
        return null;
    }

    public Customer findCustomerByPhone(String phoneNumber) {
        for(Customer customer : customers) {
            if(customer.getPhone().getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }

    public boolean doesExist(Customer customer) {
        return customers.contains(customer);
    }

    public int size() {
        return customers.size();
    }

    public String getAccountNumber(String cardNumber) {
        for(Customer customer : customers) {
            if(customer.getAccount().getCard().getCardNumber().equals(cardNumber)) {
                return customer.getAccount().getAccountNO();
            }
        }
        return null;
    }
}
