package use_case.match_lookup;

public class MatchLookupOutputData {

    private final String time;
    private final String mode;
    private final String map;
    private final boolean victory;
    private final int trophyChange;
    private final String starPlayer;
    private final int trophyCount;

    public MatchLookupOutputData(String time, String mode, String map, boolean result, int trophyChange, String starPlayer, int trophyCount) {
        this.time = time;
        this.mode = mode;
        this.map = map;
        this.victory = result;
        this.trophyChange = trophyChange;
        this.starPlayer = starPlayer;
        this.trophyCount = trophyCount;
    }

    public String getTime() {
        return time;
    }

    public String getMode() {
        return mode;
    }

    public String getMap() {
        return map;
    }

    public boolean isVictory() {
        return victory;
    }

    public int getTrophyChange() {
        return trophyChange;
    }

    public String getStarPlayer() {
        return starPlayer;
    }

    public int getTrophyCount() {
        return trophyCount;
    }
}
