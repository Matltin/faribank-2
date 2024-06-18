package ir.ac.kntu.db;

import ir.ac.kntu.phone.Phone;

import java.io.Serializable;
import java.util.Set;

public class SimCardDB implements Serializable {
    private Set<Phone> phones;

    public SimCardDB(Set<Phone> phones) {
        this.phones = phones;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
    }

    public void remove(Phone phone) {
        phones.remove(phone);
    }

    public boolean contain(Phone phone) {
        return phones.contains(phone);
    }

    public Phone findPhone(String phoneNumber) {
        for(Phone phone : phones) {
            if(phone.getPhoneNumber().equals(phoneNumber)) {
                return phone;
            }
        }
        return null;
    }
}
