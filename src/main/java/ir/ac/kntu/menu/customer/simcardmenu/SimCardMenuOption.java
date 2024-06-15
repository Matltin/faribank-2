package ir.ac.kntu.menu.customer.simcardmenu;

public enum SimCardMenuOption {
    BY_CONTACT,
    BY_PHONE_NUMBER,
    TO_YOURSELF,
    SHOW_TRANSACTION,
    SHOW_CHARGE,
    BACK;

    public static void printOption() {
        System.out.println("\n1.By contact\n" +
                "2.By phone number\n" +
                "3.To your self\n" +
                "4.Show transaction\n" +
                "5.Show charge\n" +
                "6.Back\n");
    }
}
