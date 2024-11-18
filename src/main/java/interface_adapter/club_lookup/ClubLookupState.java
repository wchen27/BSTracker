package interface_adapter.club_lookup;

import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClubLookupState {

    private List<User> members;

    public ClubLookupState() {
        this.members = new ArrayList<>();
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
