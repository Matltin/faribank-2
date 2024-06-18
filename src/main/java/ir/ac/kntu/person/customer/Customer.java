package ir.ac.kntu.person.customer;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.*;
import ir.ac.kntu.faribank.account.Account;
import ir.ac.kntu.person.ContactPerson;
import ir.ac.kntu.person.Person;
import ir.ac.kntu.phone.Phone;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Customer extends Person implements Serializable {

    private String iDocument;
    private Phone phone;
    private Account account;
    private ContactPersonDB contactPersonDB;
    private RecentTransactionDB recentTransaction;
    private boolean contactAvailable;
    private State state;
    private MessageDB messageDB;
    private SimCardTransactionDB simCardTransactionDB;

    public Customer(String firstName, String lastName, String password, String iDocument, String phoneNumber, SimCardDB simCardDB) {
        super(firstName, lastName, password);
        this.iDocument = iDocument;
        this.phone = new Phone(phoneNumber, simCardDB);
        if(!simCardDB.contain(phone)) {
            simCardDB.addPhone(phone);
        }
        contactPersonDB = new ContactPersonDB();
        recentTransaction = new RecentTransactionDB();
        contactAvailable = true;
        state = State.IN_PROGRESSING;
        messageDB = new MessageDB();
        account = new Account(0, randomAccountNO());
        simCardTransactionDB = new SimCardTransactionDB();
    }

    public String getIDocument() {
        return iDocument;
    }

    public void setId(String iDocument) {
        this.iDocument = iDocument;
    }

    public String getPhoneNumber() {
        return phone.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone.setPhoneNumber(phoneNumber);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setContactAvailable(boolean contactAvailable) {
        this.contactAvailable = contactAvailable;
    }

    public ContactPersonDB getContactPerson() {
        return contactPersonDB;
    }

    public void setContactPeople(ContactPersonDB contactPersonDB) {
        this.contactPersonDB = contactPersonDB;
    }

    public boolean isContactAvailable() {
        return contactAvailable;
    }

    public RecentTransactionDB getRecentTransaction() {
        return recentTransaction;
    }

    public void setRecentTransaction(RecentTransactionDB recentTransaction) {
        this.recentTransaction = recentTransaction;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public MessageDB getMessageDB() {
        return messageDB;
    }

    public void setMessageDB(MessageDB messageDB) {
        this.messageDB = messageDB;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public SimCardTransactionDB getSimCardTransactionDB() {
        return simCardTransactionDB;
    }

    public void setSimCardTransactionDB(SimCardTransactionDB simCardTransactionDB) {
        this.simCardTransactionDB = simCardTransactionDB;
    }

    public void addContactPerson(String firstName, String lastName, String phoneNumber, CustomerDB customerDB) {
        String accountNumber = "";
        for (Customer customer : customerDB.getCustomers()) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                accountNumber = customer.getAccount().getAccountNO();
                break;
            }
        }
        Customer customer = customerDB.findCustomerByAccountNO(accountNumber);
        if (customer.state != State.ACCEPTED) {
            System.out.println(Constance.RED + "There is no customer!!" + Constance.RESET);
            return;
        }
        try {
            if (!accountNumber.isEmpty()) {
                ContactPerson contactPerson = new ContactPerson(firstName, lastName, phoneNumber, accountNumber);
                contactPersonDB.addContactPerson(contactPerson);
            } else {
                throw new RuntimeException("404 : user not found!!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private String randomAccountNO() {
        Random random = new Random();
        return String.valueOf(random.nextInt((int) Math.pow(10, 8), (int) Math.pow(10, 9)));
    }

    @Override
    public String toString() {
        return "Customer{" +
                super.toString() +
                "phoneNumber='" + phone.getPhoneNumber() + '\'' +
                ", iDocument='" + iDocument + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return Objects.equals(iDocument, customer.iDocument) && Objects.equals(phone, customer.phone) && Objects.equals(account, customer.account) && Objects.equals(contactPersonDB, customer.contactPersonDB) && Objects.equals(recentTransaction, customer.recentTransaction) && Objects.equals(messageDB, customer.messageDB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iDocument, phone, account, contactPersonDB, recentTransaction, messageDB);
    }
}
