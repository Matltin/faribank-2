package ir.ac.kntu.db;

import ir.ac.kntu.Constance;
import ir.ac.kntu.person.customer.Customer;
import ir.ac.kntu.util.ScannerWrapper;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
            customers.remove(customer);
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

    public void printCustomer() {
        Map<Integer, Customer> map = getMap();
        int size = map.size();
        int valueToDisPlay = Constance.VALUE_TO_DISPLAY;
        if(valueToDisPlay > size) {
            valueToDisPlay = size;
        }
        int currentPosition = 1;
        String inputStr;
        print(1, valueToDisPlay + 1, map);
        do {
            inputStr = ScannerWrapper.getInstance().nextLine();
            switch (inputStr) {
                case "next" -> currentPosition = plus(currentPosition, size, valueToDisPlay, map);
                case "back" -> currentPosition = minus(currentPosition, size, -valueToDisPlay, map);
                case "quit" -> {
                    return;
                }
                default -> System.out.println("invalid input");
            }
        } while(true);
    }

    private Map<Integer, Customer> getMap() {
        Map<Integer, Customer> map = new HashMap<>();
        int counter = 1;
        for(Customer customer : customers) {
            map.put(counter, customer);
            counter++;
        }
        return map;
    }

    private int minus(int currentPosition, int size, int amount, Map<Integer, Customer> map) {
        if(currentPosition + amount < 0) {
            currentPosition = 0;
            print(1, -amount + 1, map);
            voice();
        } else {
            if(currentPosition == size) {
                currentPosition += amount;
            }
            if(currentPosition + amount < 1) {
                currentPosition = 0;
                print(1, -amount + 1, map);
                return currentPosition;
            }
            print(currentPosition + amount, currentPosition + 1, map);
            currentPosition += amount;
        }
        return currentPosition;
    }

    private int plus(int currentPosition, int size, int amount, Map<Integer, Customer> map) {
        if(currentPosition + amount > size) {
            currentPosition = size;
            print(size - amount + 1, size + 1, map);
            voice();
        } else {
            if(currentPosition == 1) {
                currentPosition += amount;
            }
            if(currentPosition + amount > size) {
                currentPosition = size;
                print(size - amount, size, map);
                return currentPosition;
            }
            print(currentPosition, currentPosition + amount, map);
            currentPosition += amount;
        }
        return currentPosition;
    }


    private void print(int first, int second, Map<Integer, Customer> map) {
        for(int i = first; i < second; i++) {
            System.out.println(i + "." + map.get(i).getFirstName() + " " + map.get(i).getLastName() + " " + map.get(i).getPhoneNumber());
        }
    }

    private void voice() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File("ding.wav");
                try {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
