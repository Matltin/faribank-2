package ir.ac.kntu.menu.chief.manageusermenu;

public enum ManageUserMenuOption {
    SHOW_USER,
    SEARCH_USER,
    ADD_USER,
    BLOCK_USER,
    EDIT_USER,
    ALLOCATE,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Show users\n" +
                "2.Search user\n" +
                "3.Add user\n" +
                "4.Block user\n" +
                "5.Edit user\n" +
                "6.Allocate\n" +
                "7.Back\n");
    }
}
