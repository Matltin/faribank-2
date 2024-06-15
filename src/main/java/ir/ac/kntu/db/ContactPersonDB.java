package ir.ac.kntu.db;

import ir.ac.kntu.person.ContactPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactPersonDB {


    private List<ContactPerson> contactPersonList;

    public ContactPersonDB() {
        this.contactPersonList = new ArrayList<>();
    }

    public List<ContactPerson> getContactPerson() {
        return contactPersonList;
    }

    public void setContactPerson(List<ContactPerson> contactPerson) {
        this.contactPersonList = contactPerson;
    }

    public void addContactPerson(ContactPerson contactPerson) {
        contactPersonList.add(contactPerson);
    }

    public ContactPerson findPerson(String phoneNumber) {
        for (ContactPerson contactPerson : contactPersonList) {
            if (contactPerson.getPhoneNumber().equals(phoneNumber)) {
                return contactPerson;
            }
        }
        return null;
    }

    public void removePerson(ContactPerson contactPerson) {
        try {
            if (doesExist(contactPerson)) {
                contactPersonList.remove(contactPerson);
            } else {
                throw new RuntimeException("contact not found!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean doesExist(ContactPerson contactPerson) {
        return contactPersonList.contains(contactPerson);
    }

    public void printContactPerson() {
        int counter = 1;
        for (ContactPerson contactPerson : contactPersonList) {
            System.out.println(counter + "." + contactPerson.getFirstName() + " " + contactPerson.getLastName());
            counter++;
        }
    }

    public boolean checkContact(String accountNO) {
        for (ContactPerson contactPerson : contactPersonList) {
            if (contactPerson.getAccountNumber().equals(accountNO)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ContactPersonDB that = (ContactPersonDB) obj;
        return Objects.equals(contactPersonList, that.contactPersonList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(contactPersonList);
    }
}