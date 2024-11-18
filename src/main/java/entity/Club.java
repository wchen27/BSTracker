package entity;

import java.util.List;

public class Club {

    private final String tag;
    private final List<User> members;

    public Club(String tag, List<User> members) {
        this.tag = tag;
        this.members = members;
    }

    public String getTag() {
        return tag;
    }

    public List<User> getMembers() {
        return members;
    }
}
