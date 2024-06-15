package ir.ac.kntu.menu.chief.searchusermenu;

public enum SearchUserMenuOption {
    BY_FIRST_NAME,
    BY_LAST_NAME,
    BY_PHONE_NUMBER,
    BY_USER_ROLE,
    BACK;

    public static void printOption() {
        System.out.println("\nsearch by : \n" +
                "1.User first name\n" +
                "2.User last name\n" +
                "3.Phone number\n" +
                "4.User role\n" +
                "5.Back\n"
        );

    }
}
