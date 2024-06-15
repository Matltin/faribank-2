package ir.ac.kntu.menu.customer.customermenu;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.customer.accountmangemenu.AccountMangeMenu;
import ir.ac.kntu.menu.customer.boxmenu.BoxMenu;
import ir.ac.kntu.menu.customer.contactmenu.ContactMenu;
import ir.ac.kntu.menu.customer.settingmenu.SettingMenu;
import ir.ac.kntu.menu.customer.simcardmenu.SimCardMenu;
import ir.ac.kntu.menu.customer.support.SupportMenu;
import ir.ac.kntu.menu.customer.transfermoneymenu.TransferMenu;
import ir.ac.kntu.person.customer.Customer;

import java.text.ParseException;

public class CustomerMenu extends Menu {

    private Customer customer;
    private TransferMenu transferMenu;
    private AccountMangeMenu accountMangeMenu;
    private ContactMenu contactMenu;
    private SupportMenu supportMenu;
    private SettingMenu settingMenu;
    private BoxMenu boxMenu;
    private SimCardMenu simCardMenu;

    public CustomerMenu(TransferMenu transferMenu, AccountMangeMenu accountMangeMenu,
                        ContactMenu contactMenu, SupportMenu supportMenu,
                        SettingMenu settingMenu, BoxMenu boxMenu, SimCardMenu simCardMenu) {
        this.transferMenu = transferMenu;
        this.accountMangeMenu = accountMangeMenu;
        this.contactMenu = contactMenu;
        this.supportMenu = supportMenu;
        this.settingMenu = settingMenu;
        this.boxMenu = boxMenu;
        this.simCardMenu = simCardMenu;
    }

    public void show(Customer customer) throws ParseException {
        this.customer = customer;
        show();
    }

    @Override
    public void show() throws ParseException {
        System.out.println("customer Menu");
        CustomerMenuOption custMenuOption = printMenuOption();
        while (custMenuOption != CustomerMenuOption.BACK) {
            if (custMenuOption != null) {
                switch (custMenuOption) {
                    case TRANSFER_MONEY -> transferMenu.show(customer);
                    case ACCOUNT_MANAGE -> accountMangeMenu.show(customer);
                    case CONTACT_MENU -> contactMenu.show(customer);
                    case SUPPORT -> supportMenu.show(customer);
                    case SETTING -> settingMenu.show(customer);
                    case BOXES -> boxMenu.show(customer);
                    case SIM_CARD -> simCardMenu.show(customer);
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            custMenuOption = printMenuOption();
        }
    }

    private CustomerMenuOption printMenuOption() {
        System.out.println("----------customer Menu----------");
        CustomerMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(CustomerMenuOption.class);
    }
}
