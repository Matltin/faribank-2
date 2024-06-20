package ir.ac.kntu.menu.customer.boxmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.box.Box;
import ir.ac.kntu.box.BoxType;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.customer.manageboxmenu.ManageBoxMenu;
import ir.ac.kntu.person.customer.Customer;
import ir.ac.kntu.profitthread.ProfitThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BoxMenu extends Menu {

    private Customer customer;
    private ManageBoxMenu manageBoxMenu;

    public BoxMenu(ManageBoxMenu manageBoxMenu) {
        this.manageBoxMenu = manageBoxMenu;
    }

    public void show(Customer customer) {
        this.customer = customer;
        show();
    }

    @Override
    public void show() {
        System.out.println("box Menu");
        BoxMenuOption boxMenuOption = printMenuOption();
        while (boxMenuOption != BoxMenuOption.BACK) {
            if (boxMenuOption != null) {
                switch (boxMenuOption) {
                    case SHOW_BOXES -> showBoxes();
                    case ADD_BOX -> addBox();
                    case MANAGE_BOXES -> manageBoxMenu.show(customer);
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            boxMenuOption = printMenuOption();
        }
    }

    private BoxMenuOption printMenuOption() {
        System.out.println("----------box Menu----------");
        BoxMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(BoxMenuOption.class);
    }

    private void showBoxes() {
        int counter = 1;
        for (Box box : customer.getAccount().getBoxDB().getBoxes()) {
            System.out.println(counter + "." + box);
            counter++;
        }
    }

    private void addBox() {
        String boxName = getBoxName();
        Box box = customer.getAccount().getBoxDB().findBox(boxName);
        if (box != null) {
            System.out.println(Constance.RED + "box with this name is already exist" + Constance.RESET);
            return;
        }
        String boxType = getBoxType();
        if (boxType.equals(BoxType.SAVING.getType())) {
            box = new Box(boxName, 0, BoxType.SAVING);
        } else if (boxType.equals(BoxType.PROFIT.getType())) {
            box = makeProfitBox(boxName);
            if(box == null) {
                return;
            }
        } else if (boxType.equals(BoxType.REMAINING.getType())) {
            System.out.println(Constance.RED + "you can not add remaining box" + Constance.RESET);
            return;
        } else {
            System.out.println(Constance.RED + "invalid input" + Constance.RESET);
            return;
        }
        customer.getAccount().getBoxDB().addBox(box);
    }

    private Box makeProfitBox(String boxName) {
        long inputMoney = getInputMoney();
        if (inputMoney > customer.getAccount().getBalance()) {
            System.out.println(Constance.RED + "your input money is more than tour balance!!" + Constance.RESET);
            return null;
        }
        int monthCounter = getMonthCounter();

        Box box = new Box(boxName, inputMoney, BoxType.PROFIT, monthCounter);
        customer.getAccount().withdraw(inputMoney);

        ScheduledExecutorService scheduler
                = Executors.newScheduledThreadPool(monthCounter);

        for(int i = 1; i <= monthCounter; i++) {
            ProfitThread profitThread = new ProfitThread();
            profitThread.setBox(box, customer);
            Thread thread = new Thread(profitThread);
            scheduler.schedule(thread, 30L * i, TimeUnit.DAYS);
        }
        return box;
    }
}
