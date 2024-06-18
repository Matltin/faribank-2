package ir.ac.kntu.db;

import ir.ac.kntu.person.chief.Chief;

import java.util.Set;

public class ChiefDB {

    private Set<Chief> chiefs;

    public ChiefDB(Set<Chief> chiefs) {
        this.chiefs = chiefs;
//        this.chiefs.add(new Chief("matin", "ahamdi", "Rr@138406", "c", 1));
//        this.chiefs.add(new Chief("reyhane", "mirarabshahi", "Mm@138406", "d", 2));
    }

    public void addChief(Chief chief) {
        chiefs.add(chief);
    }

    public void removeChief(Chief chief) {
        chiefs.remove(chief);
    }

    public Set<Chief> getChiefs() {
        return chiefs;
    }

    public void setChiefs(Set<Chief> chiefs) {
        this.chiefs = chiefs;
    }

    public Chief findChief(String username) {
        for (Chief chief : chiefs) {
            if (chief.getUserName().equals(username)) {
                return chief;
            }
        }
        return null;
    }

    public boolean doesExist(Chief chief) {
        return chiefs.contains(chief);
    }
}
