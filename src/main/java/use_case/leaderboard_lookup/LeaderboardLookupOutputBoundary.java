package use_case.leaderboard_lookup;

public interface LeaderboardLookupOutputBoundary {

	public void prepareSuccessView(LeaderboardLookupOutputData leaderboardLookupOutputData);

	public void prepareFailView(String errorMessage);

}
