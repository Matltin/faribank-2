package ir.ac.kntu.menu.chief.userrolemenu;

public enum UserRoleMenuOption {
    ADMIN,
    CHIEF,
    CUSTOMER,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Admin\n" +
                "2.Chief\n" +
                "3.customer\n" +
                "4.Back\n"
        );
    }
}
