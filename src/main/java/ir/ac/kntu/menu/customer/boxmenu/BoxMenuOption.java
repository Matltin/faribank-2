package ir.ac.kntu.menu.customer.boxmenu;

public enum BoxMenuOption {
    SHOW_BOXES,
    ADD_BOX,
    MANAGE_BOXES,
    BACK;

    public static void printOption() {
        System.out.println("\n1.Show boxes\n" +
                "2.Add box\n" +
                "3.Manage boxes\n" +
                "4.Back\n");
    }
}
