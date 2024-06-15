package ir.ac.kntu.menu.customer.transfermoneymenu;

public enum TransferMenuOption {
    TRANSFER_MONEY_RECENT_ACCOUNT,
    TRANSFER_MONEY_CONTACT,
    TRANSFER_MONEY_ACCOUNT,
    TRANSFER_MONEY_CARD_NUMBER,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Transfer money by recent account\n" +
                "2.Transfer money by contact\n" +
                "3.Transfer money by Account\n" +
                "4.Transfer money by card number\n" +
                "5.Back\n");
    }
}
