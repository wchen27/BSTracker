package use_case.match_lookup;

import entity.Match;

import java.util.List;

public class MatchLookupOutputData {

    private final List<Match> matches;
    private double winrate;

    public MatchLookupOutputData(List<Match> matches) {
        this.matches = matches;
        int numWins = 0;
        for (Match match : matches) {
            if (match.isVictory()) {
                numWins++;
            }
        }
        this.winrate = numWins / (double) matches.size();
    }

    public List<Match> getMatches() {
        return matches;
    }

    public double getWinrate() {
        return winrate;
    }
}
