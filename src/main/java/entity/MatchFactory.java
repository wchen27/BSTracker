package entity;

public class MatchFactory {
    /**
     * Creates a Match object with the specified parameters.
     * @param time the time the match was played
     * @param mode the gamemode of the match
     * @param map the map the match was played on
     * @param result whether the player won or lost the match
     * @param trophyChange the number of trophies gained or lost
     * @param starPlayer the player that the game deemed the most valuable
     * @param starPlayerBrawler the brawler that the star player was playing
     * @param trophyCount the trophy count of the player.
     * @return
     */
    public Match create(String time, String mode, String map, boolean result, int trophyChange, String starPlayer,
            String starPlayerBrawler, int trophyCount) {
        return new Match(time, mode, map, result, trophyChange, starPlayer, starPlayerBrawler, trophyCount);
    }
}
