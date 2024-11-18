package entity;

import java.util.List;

public class ClubFactory {
    public Club create(String tag, List<User> members) {
        return new Club(tag, members);
    }
}
