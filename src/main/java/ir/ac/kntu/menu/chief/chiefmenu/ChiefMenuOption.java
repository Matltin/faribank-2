package ir.ac.kntu.menu.chief.chiefmenu;

public enum ChiefMenuOption {
    MANAGE_USERS,
    AUTO_TRANSACTION,
    SETTING,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Manage users\n" +
                "2.Auto transaction\n" +
                "3.Setting\n" +
                "4.Back\n");
    }
}
