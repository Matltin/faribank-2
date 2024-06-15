package ir.ac.kntu.menu.chief.editmenu;

public enum EditUserMenuOption {
    ADMIN,
    CHIEF,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Admin\n" +
                "2.Chief\n" +
                "3.Back\n"
        );
    }
}
