package use_case.fetch_battle_log;

import data_access.APIDataAccessObject;
import entity.MatchFactory;
import org.junit.Test;

public class GetMatchesTest {

    @Test
    public void getMatchesTest(){
        APIDataAccessObject api = new APIDataAccessObject(new MatchFactory());
        api.getMatches("#99uv0990");
    }

}