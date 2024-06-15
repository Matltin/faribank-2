package ir.ac.kntu.menu.customer.accountnumbermenu;

public enum AccountNumberMenuOption {
    TRANSFER_TO_FARI,
    TRANSFER_PAYA,
    TRANSFER_POLE,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Transfer to Fari account\n" +
                "2.Transfer to another account(paya)\n" +
                "3.Transfer to another account(pole)\n" +
                "4.Back\n");
    }
}
