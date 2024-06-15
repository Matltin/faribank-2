package ir.ac.kntu.menu.chief.loginchiefmenu;

public enum LoginChiefMenuOption {
    LOGIN,
    REGISTER,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Login\n" +
                "2.Register\n" +
                "3.Back\n");
    }
}
