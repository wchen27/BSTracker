package use_case.fetch_battle_log;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.ClubFactory;
import entity.Match;
import entity.MatchFactory;
import entity.UserFactory;
import io.github.cdimascio.dotenv.Dotenv;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GetMatchesTest {

    @Test
    // tests
    public void getMatchesTest() {
        Dotenv env = Dotenv.load();
        APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(),
                env);
        List<Match> response = api.getMatches("#99uv0990");
        assertNotEquals(response.size(), 0);
    }

    @Test(expected = JSONException.class)
    // tests failing to fetch a match list
    public void getMatchesFailedTest() {
        Dotenv env = Dotenv.load();
        APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(),
                env);
        api.getMatches("fail");
    }

    @Test
    // tests the validity of match data
    public void getMatchesDataTest() {
        Dotenv env = Dotenv.load();
        APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(),
                env);
        List<Match> response = api.getMatches("#99uv0990");
        Match match = response.get(0);
        assertNotNull(match);
        assertNotNull(match.getMap());
        assertNotNull(match.getMode());
        assertNotNull(match.getStarPlayer());
        assertNotNull(match.getTime());
        assertNotNull(match.getMode());
        match.getTrophyChange();
        match.getTrophyCount();
        match.isVictory();
        match.toString();
    }

}
