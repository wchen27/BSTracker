package use_case.match_lookup;

import entity.Match;

import java.util.List;

public class MatchLookupOutputData {

    private final List<Match> matches;

    public MatchLookupOutputData(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }
}
