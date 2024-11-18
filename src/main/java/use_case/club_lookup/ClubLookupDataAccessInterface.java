package use_case.club_lookup;

import entity.User;

import java.util.List;

public interface ClubLookupDataAccessInterface {
    List<User> getMembers(String tag);
}
