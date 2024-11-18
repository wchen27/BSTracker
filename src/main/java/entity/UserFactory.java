package entity;

public class UserFactory {
    public User create(String tag, String username, int trophies, int highestTrophies, int trioVictories, int duoVictories, int soloVictories, Brawler[] brawlers, Match[] matches ) {
        return new User(tag, username, trophies, highestTrophies, trioVictories, duoVictories, soloVictories, brawlers, matches);
    }

    public User create(String tag, String username, int trophies) {
        return new User(tag, username, trophies, 0, 0, 0, 0, new Brawler[]{}, new Match[]{});
    }
}