package ir.ac.kntu.db;

import ir.ac.kntu.person.customer.Customer;

import java.util.Set;

public class BankDB {
    private Set<Customer> bankCustomer;

    public BankDB(Set<Customer> bankCustomer, SimCardDB simCardDB) {
        this.bankCustomer = bankCustomer;
        Customer customer1 = new Customer("jack", "valobiyayesehramiz", "Jj@1980", "91", "09052607040", simCardDB);
        Customer customer2 = new Customer("mmd", "valtopesehramiz", "Mm@1376", "55", "09092607040", simCardDB);

        customer1.getAccount().setAccountNO("123456789");
        customer1.getAccount().getCard().setCardNumber("123456789123");

        customer2.getAccount().setAccountNO("123456798");
        customer2.getAccount().getCard().setCardNumber("123456789132");

        bankCustomer.add(customer1);
        bankCustomer.add(customer2);

    }

    public Set<Customer> getBankCustomer() {
        return bankCustomer;
    }

    public void setBankCustomer(Set<Customer> bankCustomer) {
        this.bankCustomer = bankCustomer;
    }

    public void addCustomer(Customer customer) {
        bankCustomer.add(customer);
    }

    public void removeCustomer(Customer customer) {
        bankCustomer.remove(customer);
    }

    public boolean contain(Customer customer) {
        return bankCustomer.contains(customer);
    }

    public Customer findCustomerByAccNumber(String accountNO) {
        for(Customer customer : bankCustomer) {
            if(customer.getAccount().getAccountNO().equals(accountNO)) {
                return customer;
            }
        }
        return null;
    }

    public Customer findCustomerByPhone(String phoneNumber) {
        for (Customer customer : bankCustomer) {
            if(customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }

    public String getAccountNumber(String cardNumber) {
        for(Customer customer : bankCustomer) {
            if(customer.getAccount().getCard().getCardNumber().equals(cardNumber)) {
                return customer.getAccount().getAccountNO();
            }
        }
        return null;
    }

    public void printBankDB() {
        int counter = 1;
        for(Customer customer : bankCustomer) {
            System.out.println(counter + "." + customer.getFirstName() + customer.getLastName());
            counter++;
        }
    }
}
