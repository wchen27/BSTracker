package interface_adapter.leaderboard_lookup;

import java.util.Map;
import java.util.HashMap;

public class LeaderboardLookupState {

	private Map<String, Integer> brawlerFrequency;
	private int numUsers;

	public LeaderboardLookupState() {
		this.brawlerFrequency = new HashMap<>();
	}

	public Map<String, Integer> getBrawlerFrequency() {
		return brawlerFrequency;
	}

	public void setBrawlerFrequency(Map<String, Integer> brawlerFrequency) {
		this.brawlerFrequency = brawlerFrequency;
	}

	public void setNumUsers(int numUsers) {
		this.numUsers = numUsers;
	}

	public int getNumUsers() {
		return numUsers;
	}
}
