package ir.ac.kntu.faribank;

import ir.ac.kntu.db.*;
import ir.ac.kntu.menu.admin.adminmenu.AdminMenu;
import ir.ac.kntu.menu.admin.branch.BranchMenu;
import ir.ac.kntu.menu.admin.loginadminmenu.LoginAdminMenu;
import ir.ac.kntu.menu.admin.requstmenu.RequestAdminMenu;
import ir.ac.kntu.menu.admin.searchmenu.SearchMenu;
import ir.ac.kntu.menu.admin.searchusermenu.SearchUserMenu;
import ir.ac.kntu.menu.admin.statemenu.StateMenu;
import ir.ac.kntu.menu.admin.useraccessmenu.UserAccessMenu;
import ir.ac.kntu.menu.customer.accountmangemenu.AccountMangeMenu;
import ir.ac.kntu.menu.customer.accountmangemenu.recenttransactionmenu.RecentTransactionMenu;
import ir.ac.kntu.menu.customer.contactmenu.ContactMenu;
import ir.ac.kntu.menu.customer.customermenu.CustomerMenu;
import ir.ac.kntu.menu.customer.logincustomermenu.LoginCustomerMenu;
import ir.ac.kntu.menu.customer.requestmenu.RequestCustomerMenu;
import ir.ac.kntu.menu.customer.settingmenu.SettingMenu;
import ir.ac.kntu.menu.customer.support.SupportMenu;
import ir.ac.kntu.menu.customer.transfermoneymenu.TransferMenu;
import ir.ac.kntu.menu.mainmenu.MainMenu;
import ir.ac.kntu.message.Message;
import ir.ac.kntu.person.admin.Admin;
import ir.ac.kntu.person.customer.Customer;
import ir.ac.kntu.person.customer.State;
import ir.ac.kntu.phone.Phone;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FariBank {

    private MainMenu mainMenu;
    private SimCardDB simCardDB;
    private AdminDB adminDB;
    private CustomerDB customerDB;
    private BankDB bankDB;
    private ChiefDB chiefDB;
    private AnswerDB answerDB;
    private PayaDB payaDB;

    public void start() throws ParseException {
        initializeDB();
        initialize();
        mainMenu.show();
        saveFile();
    }

    private void initializeDB() {
        simCardDB = new SimCardDB(readSimDB());
        customerDB = new CustomerDB(readCustomerDB());
        bankDB = new BankDB(readBankDB(), simCardDB);
        adminDB = new AdminDB(readAdminDB());
        chiefDB = new ChiefDB(readChiefDB());
        answerDB = new AnswerDB(readAnswerDB());
        payaDB = new PayaDB(readPayaDB());
    }

    private void saveFile() {
        saveSimDB();
        saveAdminDB();
        saveCustomerDB();
        saveBankDB();
        saveChiefDB();
        saveAdminDB();
        saveChiefDB();
        savePayaDB();
    }

    private void initialize() {

        Customer customer1 = new Customer("a", "a", "Mm@1383", "12", "09102607040", simCardDB);
        Customer customer2 = new Customer("b", "b", "Rr@1384", "13", "09112607040", simCardDB);
        Customer customer3 = new Customer("c", "c", "Cc@1383", "14", "09122607040", simCardDB);

        customer1.setState(State.ACCEPTED);
        customer2.setState(State.ACCEPTED);
        customer3.setState(State.ACCEPTED);

        customerDB.addCustomer(customer1);
        customerDB.addCustomer(customer2);
        customerDB.addCustomer(customer3);


        SearchUserMenu searchUserMenu = new SearchUserMenu(customerDB);
        StateMenu stateMenu = new StateMenu(answerDB);
        BranchMenu branchMenu = new BranchMenu(answerDB);
        SearchMenu searchMenu = new SearchMenu(answerDB, stateMenu, branchMenu);
        RequestAdminMenu requestAdminMenu = new RequestAdminMenu(answerDB, searchMenu);
        UserAccessMenu userAccessMenu = new UserAccessMenu(customerDB, searchUserMenu);
        AdminMenu adminMenu = new AdminMenu(customerDB, requestAdminMenu, userAccessMenu);
        RequestCustomerMenu requestCustMenu = new RequestCustomerMenu(answerDB);
        RecentTransactionMenu recentTransMenu = new RecentTransactionMenu();
        AccountMangeMenu accountMangeMenu = new AccountMangeMenu(customerDB, recentTransMenu);
        ContactMenu contactMenu = new ContactMenu(customerDB);
        SupportMenu supportMenu = new SupportMenu(requestCustMenu);
        SettingMenu settingMenu = new SettingMenu();
        AccountNumberMenu accountNumberMenu = new AccountNumberMenu(customerDB, bankDB, payaDB);
        ManageBoxMenu manageBoxMenu = new ManageBoxMenu();
        CardMenu cardMenu = new CardMenu(customerDB, bankDB, payaDB);
        BoxMenu boxMenu = new BoxMenu(manageBoxMenu);
        SimCardMenu simCardMenu = new SimCardMenu(customerDB, simCardDB, bankDB);
        TransferMenu transferMenu = new TransferMenu(customerDB, bankDB, accountNumberMenu, cardMenu);
        CustomerMenu customerMenu = new CustomerMenu(transferMenu, accountMangeMenu, contactMenu, supportMenu,
                settingMenu, boxMenu, simCardMenu);
        LoginAdminMenu loginAdminMenu = new LoginAdminMenu(adminDB, adminMenu);
        UserRoleMenu userRoleMenu = new UserRoleMenu(customerDB, adminDB, chiefDB);
        EditUserMenu editUserMenu = new EditUserMenu(adminDB, chiefDB);
        ir.ac.kntu.menu.chief.searchusermenu.SearchUserMenu searchUserMenu1 = new ir.ac.kntu.menu.chief.searchusermenu.SearchUserMenu(customerDB, adminDB, chiefDB, userRoleMenu);
        AddUserMenu addUserMenu = new AddUserMenu(chiefDB, adminDB);
        BlockMenu blockMenu = new BlockMenu(chiefDB, adminDB);
        LoginCustomerMenu loginCustomerMenu = new LoginCustomerMenu(customerDB, simCardDB, customerMenu, bankDB, answerDB);
        AutoTransaction autoTransaction = new AutoTransaction(payaDB, customerDB);
        ir.ac.kntu.menu.chief.setting.SettingMenu settingMenu1 = new ir.ac.kntu.menu.chief.setting.SettingMenu();
        ManageUserMenu manageUserMenu = new ManageUserMenu(chiefDB, adminDB, customerDB, editUserMenu, searchUserMenu1, addUserMenu, blockMenu);
        ChiefMenu chiefMenu = new ChiefMenu(manageUserMenu, settingMenu1, autoTransaction);
        LoginChiefMenu loginChiefMenu = new LoginChiefMenu(chiefDB, chiefMenu);
        mainMenu = new MainMenu(loginAdminMenu, loginCustomerMenu, loginChiefMenu);
    }

    private Set<Phone> readSimDB() {
        File file = new File("SimCardDB.txt");
        Set<Phone> phones = new HashSet<>();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Phone phone = (Phone) input.readObject();
                    phones.add(phone);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println("some problem in reading from file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
        return phones;
    }

    private void saveSimDB() {
        File file = new File("SimCardDB.txt");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for(Phone phone : simCardDB.getPhones()) {
                try{
                    output.writeObject(phone);
                } catch (IOException e) {
                    System.out.println("some problem in writing in file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
    }

    private Set<Admin> readAdminDB() {
        File file = new File("AdminDB.txt");
        Set<Admin> admins = new HashSet<>();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Admin admin = (Admin) input.readObject();
                    admins.add(admin);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println("some problem in reading from file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
        return admins;
    }

    private void saveAdminDB() {
        File file = new File("AdminDB.txt");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for(Admin admin : adminDB.getAdmins()) {
                try{
                    output.writeObject(admin);
                } catch (IOException e) {
                    System.out.println("some problem in writing in file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
    }

    private Set<Customer> readCustomerDB() {
        File file = new File("CustomerDB.txt");
        Set<Customer> customers = new HashSet<>();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Customer customer = (Customer) input.readObject();
                    customers.add(customer);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println("some problem in reading from file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
        return customers;
    }

    private void saveCustomerDB() {
        File file = new File("CustomerDB.txt");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for(Customer customer : customerDB.getCustomers()) {
                try{
                    output.writeObject(customer);
                } catch (IOException e) {
                    System.out.println("some problem in writing in file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
    }

    private Set<Chief> readChiefDB() {
        File file = new File("ChiefDB.txt");
        Set<Chief> chiefs = new HashSet<>();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Chief chief = (Chief) input.readObject();
                    chiefs.add(chief);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println("some problem in reading from file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
        return chiefs;
    }

    private void saveChiefDB() {
        File file = new File("ChiefDB.txt");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for(Chief chief : chiefDB.getChiefs()) {
                try{
                    output.writeObject(chief);
                } catch (IOException e) {
                    System.out.println("some problem in writing in file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
    }

    private List<Message> readAnswerDB() {
        File file = new File("AnswerDB.txt");
        List<Message> messages = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Message message = (Message) input.readObject();
                    messages.add(message);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println("some problem in reading from file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
        return messages;
    }

    private void saveAnswerDB() {
        File file = new File("AnswerDB.txt");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for(Message message : answerDB.getMessageList()) {
                try{
                    output.writeObject(message);
                } catch (IOException e) {
                    System.out.println("some problem in writing in file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
    }

    private List<Paya> readPayaDB() {
        File file = new File("PayaDB.txt");
        List<Paya> payas = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Paya paya = (Paya) input.readObject();
                    payas.add(paya);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println("some problem in reading from file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
        return payas;
    }

    private void savePayaDB() {
        File file = new File("PayaDB.txt");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for(Paya paya : payaDB.getPayas()) {
                try{
                    output.writeObject(paya);
                } catch (IOException e) {
                    System.out.println("some problem in writing in file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
    }

    private Set<Customer> readBankDB() {
        File file = new File("BankDB.txt");
        Set<Customer> customers = new HashSet<>();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Customer customer = (Customer) input.readObject();
                    customers.add(customer);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println("some problem in reading from file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
        return customers;
    }

    private void saveBankDB() {
        File file = new File("BankDB.txt");
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for(Customer customer : customerDB.getCustomers()) {
                try{
                    output.writeObject(customer);
                } catch (IOException e) {
                    System.out.println("some problem in writing in file");
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
    }


}
