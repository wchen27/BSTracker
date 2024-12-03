package use_case.fetch_battle_log;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.ClubFactory;
import entity.MatchFactory;
import entity.UserFactory;
import io.github.cdimascio.dotenv.Dotenv;

import org.junit.Test;

public class GetMatchesTest {

    @Test
    public void getMatchesTest() {
        Dotenv env = Dotenv.load();
        APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(),
                env);
        api.getMatches("#99uv0990");
    }

}
