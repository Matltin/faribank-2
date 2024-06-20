package ir.ac.kntu.menu.customer.cardmenu;

public enum CardMenuOption {
    TRANSFER_TO_FARI,
    TRANSFER_PAYA,
    TRANSFER_POLE,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Transfer to Fari card\n" +
                "2.Transfer to another card(paya)\n" +
                "3.Transfer to another card(pole)\n" +
                "4.Back\n");
    }
}
