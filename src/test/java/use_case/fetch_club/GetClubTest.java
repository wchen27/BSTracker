package use_case.fetch_club;

import data_access.APIDataAccessObject;
import entity.ClubFactory;
import entity.MatchFactory;
import entity.UserFactory;
import org.junit.Test;

public class GetClubTest {

    @Test
    public void getClubSuccessTest() {
        APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory());
        api.getUser("#2VOQL2LUG");
    }

}
