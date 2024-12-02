package use_case.leaderboard_lookup;

/**
 * Input data structure for the leaderboard lookup use case.
 */
public class LeaderboardLookupInputData {

	private final int size;

	public LeaderboardLookupInputData(int size) {
		this.size = size;
	}

	/*
	 * Returns the size of the leaderboard to be searched.
	 */
	public int getSize() {
		return size;
	}
}
