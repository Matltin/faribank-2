package ir.ac.kntu.menu.customer.manageboxmenu;

public enum ManageBoxMenuOption {
    TRANSFER_ACCOUNT_TO_BOX,
    TRANSFER_BOX_TO_ACCOUNT,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Transfer from account to box\n" +
                "2.Transfer from box to account\n" +
                "3.Back\n");
    }
}
