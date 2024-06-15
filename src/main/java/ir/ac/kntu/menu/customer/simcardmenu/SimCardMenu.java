package ir.ac.kntu.menu.customer.simcardmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.BankDB;
import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.db.SimCardDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.ContactPerson;
import ir.ac.kntu.person.customer.Customer;
import ir.ac.kntu.phone.Phone;
import ir.ac.kntu.simcard.SimCard;

public class SimCardMenu extends Menu {

    private Customer customer;
    private CustomerDB customerDB;
    private SimCardDB simCardDB;
    private BankDB bankDB;

    public SimCardMenu(CustomerDB customerDB, SimCardDB simCardDB, BankDB bankDB) {
        this.customerDB = customerDB;
        this.simCardDB = simCardDB;
        this.bankDB = bankDB;
    }

    public void show(Customer customer) {
        this.customer = customer;
        show();
    }

    @Override
    public void show() {
        System.out.println("sim card page");
        SimCardMenuOption simCardMenuOption = printMenuOption();
        while (simCardMenuOption != SimCardMenuOption.BACK) {
            if (simCardMenuOption != null) {
                switch (simCardMenuOption) {
                    case BY_CONTACT -> byContact();
                    case BY_PHONE_NUMBER -> byPhoneNumber();
                    case TO_YOURSELF -> toYourself();
                    case SHOW_TRANSACTION -> showTransaction();
                    case SHOW_CHARGE -> showCharge();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            simCardMenuOption = printMenuOption();
        }
    }

    private SimCardMenuOption printMenuOption() {
        System.out.println("----------sim card Menu----------");
        SimCardMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(SimCardMenuOption.class);
    }

    private void byContact() {
        customer.getContactPerson().printContactPerson();
        int number = getNumber();
        int counter = 1;
        for(ContactPerson contactPerson : customer.getContactPerson().getContactPerson()) {
            if(number == counter) {
                long charge = getChargeCredit();
                if(charge + Constance.getSimWage() > customer.getAccount().getBalance()) {
                    System.out.println(Constance.RED + "your input money in more than balance!!" + Constance.RESET);
                    return;
                }
                customer.getAccount().withdraw(charge + Constance.getSimWage());
                Customer contact = customerDB.findCustomerByPhone(contactPerson.getPhoneNumber());
                contact.getPhone().increaseChargeCredit(charge);
                SimCard simCard = new SimCard(contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), charge);
                customer.getSimCardTransactionDB().addSim(simCard);
            }
            counter++;
        }
    }

    private void byPhoneNumber() {
        String phoneNumber = getPhoneNumber();
        Phone phone = simCardDB.findPhone(phoneNumber);
        if(phone == null) {
            System.out.println(Constance.RED + "there is no phone number with this number!!" + Constance.RESET);
            return;
        }
        long charge = getChargeCredit();
        if(charge + Constance.getSimWage() > customer.getAccount().getBalance()) {
            System.out.println(Constance.RED + "your input money in more than balance!!" + Constance.RESET);
            return;
        }
        customer.getAccount().withdraw(charge + Constance.getSimWage());
        phone.increaseChargeCredit(charge);

        Customer cust = bankDB.findCustomerByPhone(phoneNumber);
        SimCard simCard = new SimCard(cust.getFirstName(), cust.getLastName(), cust.getPhoneNumber(), charge);
        customer.getSimCardTransactionDB().addSim(simCard);

    }

    private void toYourself() {
        long charge = getChargeCredit();
        if(charge + Constance.getSimWage() > customer.getAccount().getBalance()) {
            System.out.println(Constance.RED + "your input money in more than balance!!" + Constance.RESET);
            return;
        }
        customer.getAccount().withdraw(charge + Constance.getSimWage());
        customer.getPhone().increaseChargeCredit(charge);
        SimCard simCard = new SimCard(customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), charge);
        customer.getSimCardTransactionDB().addSim(simCard);
    }

    private void showTransaction() {
        customer.getSimCardTransactionDB().printTransaction();
    }

    private void showCharge() {
        System.out.println("customer charge : " + customer.getPhone().getChargeCredit());
    }
}
