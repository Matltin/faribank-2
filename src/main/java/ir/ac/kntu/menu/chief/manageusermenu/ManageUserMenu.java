package ir.ac.kntu.menu.chief.manageusermenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.AdminDB;
import ir.ac.kntu.db.ChiefDB;
import ir.ac.kntu.db.CustomerDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.chief.addusermenu.AddUserMenu;
import ir.ac.kntu.menu.chief.blockmenu.BlockMenu;
import ir.ac.kntu.menu.chief.editusermenu.EditUserMenu;
import ir.ac.kntu.menu.chief.searchusermenu.SearchUserMenu;
import ir.ac.kntu.person.admin.Admin;
import ir.ac.kntu.person.admin.Permission;
import ir.ac.kntu.person.chief.Chief;
import ir.ac.kntu.person.customer.Customer;

public class ManageUserMenu extends Menu {

    private Chief chief;
    private ChiefDB chiefDB;
    private AdminDB adminDB;
    private CustomerDB customerDB;
    private EditUserMenu editUserMenu;
    private SearchUserMenu searchUserMenu;
    private AddUserMenu addUserMenu;
    private BlockMenu blockMenu;

    public ManageUserMenu(ChiefDB chiefDB, AdminDB adminDB, CustomerDB customerDB,
                          EditUserMenu editUserMenu, SearchUserMenu searchUserMenu, AddUserMenu addUserMenu, BlockMenu blockMenu) {
        this.chiefDB = chiefDB;
        this.adminDB = adminDB;
        this.customerDB = customerDB;
        this.editUserMenu = editUserMenu;
        this.searchUserMenu = searchUserMenu;
        this.addUserMenu = addUserMenu;
        this.blockMenu = blockMenu;
    }

    public void show(Chief chief) {
        this.chief = chief;
        show();
    }

    @Override
    public void show() {
        System.out.println("manage user Menu");
        ManageUserMenuOption manageUserMenuOption = printMenuOption();
        while (manageUserMenuOption != ManageUserMenuOption.BACK) {
            if (manageUserMenuOption != null) {
                switch (manageUserMenuOption) {
                    case SHOW_USER -> showUser();
                    case SEARCH_USER -> searchUserMenu.show();
                    case ADD_USER -> addUserMenu.show(chief);
                    case BLOCK_USER -> blockMenu.show(chief);
                    case EDIT_USER -> editUserMenu.show();
                    case ALLOCATE -> allocate();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            manageUserMenuOption = printMenuOption();
        }
    }

    private ManageUserMenuOption printMenuOption() {
        System.out.println("----------admin Menu----------");
        ManageUserMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(ManageUserMenuOption.class);
    }

    private void showUser() {
        int counter = 1;
        System.out.println("--------------chiefs part--------------");
        for(Chief chief : chiefDB.getChiefs()) {
            System.out.println(counter + "." + chief);
            counter++;
        }
        System.out.println("--------------admins part--------------");
        for(Admin admin : adminDB.getAdmins()) {
            System.out.println(counter + "." + admin);
            counter++;
        }
        System.out.println("--------------customers part--------------");
        for(Customer customer : customerDB.getCustomers()) {
            System.out.println(counter + "." + customer);
            counter++;
        }
    }

    private void allocate() {
        adminDB.printAdmin();
        int number = getNumber();
        int counter = 1;
        if(!(0 < number && number <= adminDB.getAdmins().size())) {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
            return;
        }
        for (Admin admin : adminDB.getAdmins()) {
            if (counter == number) {
                permission(admin);
            }
            counter++;
        }
    }

    private void permission(Admin admin) {
        Permission per = admin.getPermission();
        setAuthentication(per);
        setRequest(per);
        setUserAccess(per);
        setContact(per);
        setSetting(per);
        setTransfer(per);
        setReport(per);
        setState(per);
        setUser(per);
    }

    private void setAuthentication(Permission permission) {
        int number;
        number = getAuthentication();
        if(number == 1) {
            permission.setAuthentication(true);
        } else if(number == 0) {
            permission.setAuthentication(false);
        } else {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
        }
    }

    private void setRequest(Permission permission) {
        int number;
        number = getRequest();
        if(number == 1) {
            permission.setRequest(true);
        } else if(number == 0) {
            permission.setRequest(false);
        } else {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
        }
    }

    private void setUserAccess(Permission permission) {
        int number;
        number = getUserAccess();
        if(number == 1) {
            permission.setUserAccess(true);
        } else if(number == 0) {
            permission.setUserAccess(false);
        } else {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
        }
    }

    private void setContact(Permission permission) {
        int number;
        number = getContact();
        if(number == 1) {
            permission.setContact(true);
        } else if(number == 0) {
            permission.setContact(false);
        } else {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
        }
    }

    private void setSetting(Permission permission) {
        int number;
        number = getSetting();
        if(number == 1) {
            permission.setSetting(true);
        } else if(number == 0) {
            permission.setSetting(false);
        } else {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
        }
    }

    private void setTransfer(Permission permission) {
        int number;
        number = getTransfer();
        if(number == 1) {
            permission.setTransfer(true);
        } else if(number == 0) {
            permission.setTransfer(false);
        } else {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
        }
    }

    private void setReport(Permission permission) {
        int number;
        number = getReport();
        if(number == 1) {
            permission.setReport(true);
        } else if(number == 0) {
            permission.setReport(false);
        } else {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
        }
    }

    private void setState(Permission permission) {
        int number;
        number = getSate();
        if(number == 1) {
            permission.setState(true);
        } else if(number == 0) {
            permission.setState(false);
        } else {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
        }
    }

    private void setUser(Permission permission) {
        int number;
        number = getUser();
        if(number == 1) {
            permission.setUser(true);
        } else if(number == 0) {
            permission.setUser(false);
        } else {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
        }
    }
}
