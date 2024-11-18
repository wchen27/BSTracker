package use_case.club_lookup;

import entity.User;

import java.util.List;

public class ClubLookupOutputData {

    private final List<User> members;

    public ClubLookupOutputData(List<User> members) {
        this.members = members;
    }

    public List<User> getMembers() {
        return members;
    }
}
