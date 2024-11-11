package entity;

/**
 * The representation of a Brawl Stars player.
 */
public interface Player {

        /**
        * Returns the playerTag of the player.
        * @return the playerTag of the player.
        */
        String getTag();

        /**
        * Returns information about the player.
         * @return information about the player.
        */
        String getInfo();
}
