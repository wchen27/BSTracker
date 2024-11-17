package entity;

public class MatchFactory {
    public Match create(String time, String mode, String map, boolean result, int trophyChange, String starPlayer,
            String starPlayerBrawler, int trophyCount) {
        return new Match(time, mode, map, result, trophyChange, starPlayer, starPlayerBrawler, trophyCount);
    }
}
