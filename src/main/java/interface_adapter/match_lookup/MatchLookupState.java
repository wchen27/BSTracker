package interface_adapter.match_lookup;

import entity.Match;

import java.util.List;

public class MatchLookupState {

    private List<Match> matches;

    /**
     * Gets a list of the matches the user played.
     * @return a List of Matches.
     */
    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

}
