package use_case.match_lookup;

import entity.Match;

import java.util.List;

public interface MatchLookupDataAccessInterface {
    List<Match> getMatches(String tag);
}
