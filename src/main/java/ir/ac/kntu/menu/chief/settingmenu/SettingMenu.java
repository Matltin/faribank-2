package ir.ac.kntu.menu.chief.settingmenu;

import ir.ac.kntu.Constance;
import ir.ac.kntu.menu.Menu;

import java.text.ParseException;

public class SettingMenu extends Menu {

    @Override
    public void show() throws ParseException {
        System.out.println("Chief Menu");
        SettingMenuOption settingMenuOption = printMenuOption();
        while (settingMenuOption != SettingMenuOption.BACK) {
            if (settingMenuOption != null) {
                switch (settingMenuOption) {
                    case WAGE -> wage();
                    case SIM_WAGE -> simWage();
                    case FARI_FARI_WAGE -> fariFariWage();
                    case FARI_CARD_WAGE -> fariCardWage();
                    case FARI_PAYA_WAGE -> fariPayaWage();
                    case FARI_POLE_WAGE -> fariPoleWage();
                    case PROFIT -> profit();
                    default -> System.out.print("");
                }
            } else {
                System.out.println("invalid input!!");
            }
            settingMenuOption = printMenuOption();
        }
    }

    private SettingMenuOption printMenuOption() {
        System.out.println("----------admin Menu----------");
        SettingMenuOption.printOption();
        System.out.print("Enter your choice : ");
        return getOption(SettingMenuOption.class);
    }

    public void wage() {
        long wage = getWage();
        Constance.setWAGE(wage);
    }
    public void simWage() {
        long wage = getWage();
        Constance.setSimWage(wage);
    }
    public void fariFariWage() {
        long wage = getWage();
        Constance.setFariFariWage(wage);
    }
    public void fariCardWage() {
        long wage = getWage();
        Constance.setFariCardWage(wage);
    }
    public void fariPayaWage() {
        long wage = getWage();
        Constance.setFariPaya(wage);
    }
    public void fariPoleWage() {
        long wage = getWage();
        Constance.setFariPole(wage);
    }
    public void profit() {
        long wage = getWage();
        Constance.setPROFIT(wage);
    }
}
