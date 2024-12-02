package use_case.leaderboard_lookup;

import entity.Match;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Output data structure for the leaderboard lookup use case.
 */
public class LeaderboardLookupOutputData {
	private final List<Match> matches;
	private final int numUsers;
	private final Map<String, Integer> brawlerFrequency;

	public LeaderboardLookupOutputData(int numUsers, List<Match> matches) {
		this.matches = matches;
		this.numUsers = numUsers;
		this.brawlerFrequency = countBrawlerFrequency(matches);
	}

	// Counts the frequency of brawlers played in recent matches
	public Map<String, Integer> countBrawlerFrequency(List<Match> matches) {
		Map<String, Integer> result = new HashMap<>();
		for (Match match : matches) {
			String brawler = match.getStarPlayerBrawler();
			if (brawler.equals("Unknown")) {
				continue;
			}
			if (result.containsKey(brawler)) {
				result.put(brawler, result.get(brawler) + 1);
			} else {
				result.put(brawler, 1);
			}
		}
		return result;
	}

	// Returns the frequency map for the given set of loaded matches.
	public Map<String, Integer> getBrawlerFrequency() {
		return brawlerFrequency;
	}

	// Returns the number of users in the leaderboard.
	public int getNumUsers() {
		return numUsers;
	}
}
