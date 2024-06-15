package ir.ac.kntu.menu.chief.addusermenu;

public enum AddUserMenuOption {
    ADD_ADMIN,
    ADD_CHIEF,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Add admin\n" +
                "2.Add chief\n" +
                "3.back\n");
    }
}
