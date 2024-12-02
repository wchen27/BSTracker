package use_case.fetch_battle_log;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.ClubFactory;
import entity.MatchFactory;
import entity.UserFactory;
import org.junit.Test;

public class GetMatchesTest {

    @Test
    public void getMatchesTest() {
        APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(),
                new FileDataAccessObject("testfile.txt"));
        api.getMatches("#99uv0990");
    }

}
