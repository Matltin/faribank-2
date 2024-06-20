package ir.ac.kntu.menu.admin.adminmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.admin.requstmenu.RequestAdminMenu;
import ir.ac.kntu.menu.admin.useraccessmenu.UserAccessMenu;
import ir.ac.kntu.message.Message;
import ir.ac.kntu.message.MessageOption;
import ir.ac.kntu.person.admin.Admin;
import ir.ac.kntu.person.admin.Permission;
import ir.ac.kntu.person.customer.Customer;
import ir.ac.kntu.person.customer.State;

public class AdminMenu extends Menu {

    private Admin admin;
    private CustomerDB customerDB;
    private RequestAdminMenu requestAdminMenu;
    private UserAccessMenu userAccessMenu;

    public AdminMenu(CustomerDB customerDB, RequestAdminMenu requestAdminMenu, UserAccessMenu userAccessMenu) {
        this.customerDB = customerDB;
        this.requestAdminMenu = requestAdminMenu;
        this.userAccessMenu = userAccessMenu;
    }

    public void show(Admin admin) {
        this.admin = admin;
        show();
    }

    @Override
    public void show() {
        System.out.println("admin Menu");
        AdminMenuOption adminMenuOption = printMenuOption();
        while (adminMenuOption != AdminMenuOption.BACK) {
            if (adminMenuOption != null) {
                switch (adminMenuOption) {
                    case AUTHENTICATION -> checkAuthentication();
                    case REQUEST -> checkRequest();
                    case USER_ACCESS -> checkUserAccess();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            adminMenuOption = printMenuOption();
        }
    }

    private AdminMenuOption printMenuOption() {
        System.out.println("----------admin Menu----------");
        AdminMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(AdminMenuOption.class);
    }

    private void checkAuthentication() {
        Permission permission = admin.getPermission();
        if(!permission.isAuthentication()) {
            System.out.println(Constance.RED + "you do not have permission" + Constance.RESET);
            return;
        }
        authentication();
    }

    private void authentication() {
        int size = printCustomer();
        if(customerDB.size() == 0) {
            System.out.println("it is empty!!");
            return;
        }
        int number = getNumber();
        if(!(0 < number && number < size)) {
            System.out.println(Constance.RED + "out of the range!!" + Constance.RESET);
            return;
        }
        String yesOrNo = getYesNo();
        if("Y".equals(yesOrNo)) {
            accessCustomer(number);
        } else if("N".equals(yesOrNo)) {
            rejectCustomer(number);
        } else {
            System.out.println("invalid input!!");
        }
    }

    private void accessCustomer(int number) {
        int counter = 0;
        for (Customer customer : customerDB.getCustomers()) {
            if (customer.getState() == State.IN_PROGRESSING) {
                counter++;
                if (counter == number) {
                    customer.setState(State.ACCEPTED);
                    System.out.println("the state is changed!!");
                    break;
                }
            }
        }
    }

    private void rejectCustomer(int number) {
        int counter = 0;
        for (Customer customer : customerDB.getCustomers()) {
            if (customer.getState() == State.IN_PROGRESSING) {
                counter++;
                if (counter == number) {
                    customer.setState(State.REJECT);
                    String message = getMessage();
                    Message newMessage = new Message(customer.getPhoneNumber(), "", MessageOption.REPORT);
                    newMessage.setMessageAnswer(message);
                    customer.getMessageDB().addMessage(newMessage);
                    System.out.println("the state is changed!!");
                    break;
                }
            }
        }
    }

    private void checkRequest() {
        Permission permission = admin.getPermission();
        if(!permission.isRequest()) {
            System.out.println(Constance.RED + "you do not have permission" + Constance.RESET);
            return;
        }
        requestAdminMenu.show();
    }

    private void checkUserAccess() {
        Permission permission = admin.getPermission();
        if(!permission.isUserAccess()) {
            System.out.println(Constance.RED + "you do not have permission" + Constance.RESET);
            return;
        }
        userAccessMenu.show();
    }

    private int printCustomer() {
        int counter = 1;
        for(Customer customer : customerDB.getCustomers()) {
            if(customer.getState() == State.IN_PROGRESSING) {
                System.out.println(counter + "." + customer.getFirstName() + " " + customer.getLastName());
                counter++;
            }
        }
        return counter;
    }
}
