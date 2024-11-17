package use_case.match_lookup;

import entity.Match;

public interface MatchLookupDataAccessInterface {
    Match getMatch(String tag);
}
