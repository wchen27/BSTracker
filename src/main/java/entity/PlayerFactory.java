package entity;

/**
 * Factory for creating users.
 */
public interface PlayerFactory {
    /**
     * Creates a new Player.
     * @param playerTag the name of the new player
     * @param playerInfo the password of the new player
     * @return the new player
     */
    Player create(String playerTag, String playerInfo);
}