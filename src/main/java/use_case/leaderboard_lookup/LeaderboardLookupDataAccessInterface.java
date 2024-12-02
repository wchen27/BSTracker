package use_case.leaderboard_lookup;

import entity.User;
import entity.Match;

import java.util.List;

/**
 * Interface for accessing leaderboard data for the leaderboard lookup use case. 
 */
public interface LeaderboardLookupDataAccessInterface {

	public List<User> getLeaderboard(int size);

	public List<Match> getMatches(String tag);
}
