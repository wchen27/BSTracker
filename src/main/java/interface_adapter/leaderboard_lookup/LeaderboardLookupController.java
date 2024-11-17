package interface_adapter.leaderboard_lookup;

import use_case.leaderboard_lookup.LeaderboardLookupInputBoundary;
import use_case.leaderboard_lookup.LeaderboardLookupInputData;

public class LeaderboardLookupController {

	private final LeaderboardLookupInputBoundary interactor;

	public LeaderboardLookupController(LeaderboardLookupInputBoundary interactor) {
		this.interactor = interactor;
	}

	public void execute(int size) {
		final LeaderboardLookupInputData inputData = new LeaderboardLookupInputData(size);

		interactor.execute(inputData);
	}
}
