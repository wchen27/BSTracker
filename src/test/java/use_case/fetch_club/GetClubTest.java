package use_case.fetch_club;

import data_access.APIDataAccessObject;
import entity.ClubFactory;
import entity.MatchFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GetClubTest {

    @Test
    public void getClubSuccessTest() {
        APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory());
        List<User> members = api.getMembers("#2VOQL2LUG");
        List<String> membernames = new ArrayList<>();
        for (User member : members) {
            membernames.add(member.getUsername());
        }
        assert(membernames.contains("Thigamore"));
    }

}
