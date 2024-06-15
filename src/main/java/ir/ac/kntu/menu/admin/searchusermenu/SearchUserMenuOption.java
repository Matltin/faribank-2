package ir.ac.kntu.menu.admin.searchusermenu;

public enum SearchUserMenuOption {
    BY_FIRST_NAME,
    BY_LAST_NAME,
    BY_PHONE_NUMBER,
    BY_FIRST_LAST_NAME,
    BY_FIRST_NAME_PHONE_NUMBER,
    BY_LAST_NAME_PHONE_NUMBER,
    BY_FIRST_LAST_NAME_PHONE_NUMBER,
    BACK;

    public static void printOption() {
        System.out.println("\nSearch by\n" +
                "1.First name\n" +
                "2.Last name\n" +
                "3.Phone number\n" +
                "4.First name & Last name\n" +
                "5.First name & Phone number\n" +
                "6.Last name & Phone number\n" +
                "7.First name & Last name & Phone number\n" +
                "8.Back\n");
    }
}
