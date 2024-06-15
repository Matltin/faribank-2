package ir.ac.kntu.menu.customer.manageboxmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.box.Box;
import ir.ac.kntu.box.BoxType;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.customer.Customer;

import java.util.Date;

public class ManageBoxMenu extends Menu {

    private Customer customer;

    public void show(Customer customer) {
        this.customer = customer;
        show();
    }

    @Override
    public void show() {
        System.out.println("manage box page");
        ManageBoxMenuOption manageBoxMenuOption = printMenuOption();
        while (manageBoxMenuOption != ManageBoxMenuOption.BACK) {
            if (manageBoxMenuOption != null) {
                switch (manageBoxMenuOption) {
                    case TRANSFER_ACCOUNT_TO_BOX -> transferByAccount();
                    case TRANSFER_BOX_TO_ACCOUNT -> transferByBox();
//                    case TRANSFER_BOX_TO_BOX -> transferBoxToBox();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            manageBoxMenuOption = printMenuOption();
        }
    }

    private ManageBoxMenuOption printMenuOption() {
        System.out.println("----------manage box Menu----------");
        ManageBoxMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(ManageBoxMenuOption.class);
    }

    private void transferByAccount() {
        String boxName = getBoxName();
        if (checkBox(boxName)) {
            return;
        }
        Box box = customer.getAccount().getBoxDB().findBox(boxName);
        if (box.getBoxType() == BoxType.REMAINING) {
            System.out.println(Constance.RED + "you cant transfer money to REMAINING box!!" + Constance.RESET);
            return;
        } else if (box.getBoxType() == BoxType.PROFIT) {
            System.out.println(Constance.RED + "you cant transfer money to PROFIT box!!" + Constance.RESET);
            return;
        }
        long inputMoney = getInputMoney();
        if (inputMoney > customer.getAccount().getBalance()) {
            System.out.println(Constance.RED + "your input money is more than your balance!!" + Constance.RESET);
            return;
        }
        box.deposit(inputMoney);
        customer.getAccount().withdraw(inputMoney);
    }

    private void transferByBox() {
        String boxName = getBoxName();
        if (checkBox(boxName)) {
            return;
        }
        Box box = customer.getAccount().getBoxDB().findBox(boxName);
        if(box.getBoxType() == BoxType.PROFIT) {
            if(!checkMonth(box)) {
                System.out.println(Constance.RED + "you can not transfer money before " + box.getCountMonth() +  " month!!" + Constance.RESET);
                return;
            }

        }
        long inputMoney = getInputMoney();
        if (inputMoney > box.getBalance()) {
            System.out.println(Constance.RED + "your input money is more than your balance's box!!" + Constance.RESET);
            return;
        }
        box.withdraw(inputMoney);
        customer.getAccount().deposit(inputMoney);
        if(box.getBoxType() == BoxType.PROFIT) {
            customer.getAccount().getBoxDB().removeBox(box);
        }
    }

//    private void transferBoxToBox() {
//        String boxName1 = getBoxName();
//        if (checkBox(boxName1)) {
//            return;
//        }
//        String boxName2 = getBoxName();
//        if (checkBox(boxName2)) {
//            return;
//        }
//        Box box1 = customer.getAccount().getBoxDB().findBox(boxName1);
//        Box box2 = customer.getAccount().getBoxDB().findBox(boxName2);
//        long inputMoney = getInputMoney();
//        if (inputMoney > box1.getBalance()) {
//            System.out.println(Constance.RED + "your input money is more than your balance's box!!" + Constance.RESET);
//            return;
//        }
//        box1.withdraw(inputMoney);
//        box2.deposit(inputMoney);
//    }

    private boolean checkBox(String boxName) {
        for (Box box : customer.getAccount().getBoxDB().getBoxes()) {
            if (boxName.equals(box.getName())) {
                return false;
            }
        }
        System.out.println(Constance.RED + "there is no box with this name!!" + Constance.RESET);
        return true;
    }

    private boolean checkMonth(Box box) {
        Date nowDate = new Date();
        Date boxDate = box.getDate();
        long diff = nowDate.getTime() - boxDate.getTime();
        if(diff > Constance.mileSecond * box.getCountMonth()) {
            return true;
        }
        return false;
    }
}
