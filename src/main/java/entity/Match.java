package entity;

public class Match {

    private final String time;
    private final String mode;
    private final String map;
    private final boolean result;
    private final int trophyChange;
    private final String starPlayer;
    private final String starPlayerBrawler;
    private final int trophyCount;

    public Match(String time, String mode, String map, boolean result, int trophyChange, String starPlayer,
            String starPlayerBrawler, int trophyCount) {
        this.time = time;
        this.mode = mode;
        this.map = map;
        this.result = result;
        this.trophyChange = trophyChange;
        this.starPlayer = starPlayer;
        this.starPlayerBrawler = starPlayerBrawler;
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
        return result;
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

    public String getStarPlayerBrawler() {
        return starPlayerBrawler;
    }

    @Override
    public String toString() {
        return "Match{" +
                "time='" + time + '\'' +
                ", mode='" + mode + '\'' +
                ", map='" + map + '\'' +
                ", result=" + result +
                ", trophyChange=" + trophyChange +
                ", starPlayer='" + starPlayer + '\'' +
                ", trophyCount=" + trophyCount +
                '}';
    }
}
