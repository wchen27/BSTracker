package use_case.leaderboard_lookup;

/**
 * Output boundary interface for the leaderboard lookup use case.
 */
public interface LeaderboardLookupOutputBoundary {

	public void prepareSuccessView(LeaderboardLookupOutputData leaderboardLookupOutputData);

	public void prepareFailView(String errorMessage);

}
