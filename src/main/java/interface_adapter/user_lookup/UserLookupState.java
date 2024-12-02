package interface_adapter.user_lookup;

import entity.Brawler;
import entity.Match;
import entity.User;

import java.util.List;

public class UserLookupState {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public String getTag() {
        return user.getTag();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public int getTrophies() {
        return user.getTrophies();
    }

    public int getHighestTrophies() {
        return user.getTrophies();
    }

    public int getTrioVictories() {
        return user.getTrioVictories();
    }

    public int getDuoVictories() {
        return user.getDuoVictories();
    }

    public int getSoloVictories() {
        return user.getSoloVictories();
    }

    public Brawler[] getBrawlers() {
        return user.getBrawlers();
    }

    public List<Match> getMatches() {
        return user.getMatches();
    }

    public int getPerformance() {
        return user.getPerformance();
    }

}
