package ir.ac.kntu.menu.chief.blockmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.db.AdminDB;
import ir.ac.kntu.db.ChiefDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.admin.Admin;
import ir.ac.kntu.person.chief.Chief;
import ir.ac.kntu.person.chief.State;

public class BlockMenu extends Menu {


    private Chief chief;
    private ChiefDB chiefDB;
    private AdminDB adminDB;

    public BlockMenu(ChiefDB chiefDB, AdminDB adminDB) {
        this.chiefDB = chiefDB;
        this.adminDB = adminDB;
    }

    public void show(Chief chief) {
        this.chief = chief;
        show();
    }

    @Override
    public void show() {
        System.out.println("block Menu");
        BlockMenuOption blockMenuOption = printMenuOption();
        while (blockMenuOption != BlockMenuOption.BACK) {
            if (blockMenuOption != null) {
                switch (blockMenuOption) {
                    case ADMIN -> blockAdmin();
                    case CHIEF -> blockChief();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            blockMenuOption = printMenuOption();
        }
    }

    private BlockMenuOption printMenuOption() {
        System.out.println("----------admin Menu----------");
        BlockMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(BlockMenuOption.class);
    }

    private void blockAdmin() {
        String userName = getUserName();
        Admin admin = adminDB.findAdmin(userName);
        if (admin == null) {
            System.out.println(Constance.RED + "there is no chief with this user name!!" + Constance.RESET);
            return;
        }
        setBlockState(admin);
    }

    private void blockChief() {
        String userName = getUserName();
        Chief chief1 = chiefDB.findChief(userName);
        if (chief1 == null) {
            System.out.println(Constance.RED + "there is no chief with this user name!!" + Constance.RESET);
            return;
        }
        if (chief1.getPosition() < chief.getPosition()) {
            System.out.println(Constance.RED + "you do not have permission to block this user!!" + Constance.RESET);
            return;
        }
        setBlockState(chief1);
    }

    private void setBlockState(Object obj) {
        String blockState = getBlockState();
        if ("Y".equals(blockState)) {
            if (obj instanceof Chief newChief) {
                newChief.setState(State.BLOCKED);
            } else if (obj instanceof Admin admin) {
                admin.setState(State.BLOCKED);
            }
        } else if ("N".equals(blockState)) {
            if (obj instanceof Chief newChief) {
                newChief.setState(State.UNBLOCKED);
            } else if (obj instanceof Admin admin) {
                admin.setState(State.UNBLOCKED);
            }
        } else {
            System.out.println(Constance.RED + "invalid input!!" + Constance.RESET);
        }
    }
}
