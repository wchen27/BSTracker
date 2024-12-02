package interface_adapter.match_lookup;

import entity.Match;

import java.util.List;

public class MatchLookupState {

    private List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public double getWinrate() {
        int wins = 0;
        for (Match match : matches) {
            if (match.isVictory()) {
                wins++;
            }
        }
        return wins / (double) matches.size() * 100;
    }

    public int getTrophyChange() {
        int trophyChange = 0;
        for (Match match : matches) {
            trophyChange += match.getTrophyChange();
        }
        return trophyChange;
    }
}
