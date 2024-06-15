package ir.ac.kntu.menu.chief.settingmenu;

public enum SettingMenuOption {
    WAGE,
    SIM_WAGE,
    FARI_FARI_WAGE,
    FARI_CARD_WAGE,
    FARI_POLE_WAGE,
    FARI_PAYA_WAGE,
    PROFIT,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Wage\n" +
                "2.Sim wage\n" +
                "3.Fari to fari wage\n" +
                "3.Fari to another card wage\n" +
                "4.Fari pole wage\n" +
                "5.Fari paya wage\n" +
                "6.Profit\n" +
                "7.Back\n");
    }
}
