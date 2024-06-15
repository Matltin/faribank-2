package ir.ac.kntu.menu.chief.autotransactionmenu;

public enum AutoTransactionOption {
    TRANSFER,
    BOX,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Transfer\n" +
                "2.Box\n" +
                "3.Back\n");
    }
}
