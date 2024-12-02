package use_case.leaderboard_lookup;

import entity.User;
import entity.Match;

import java.util.List;
import java.util.ArrayList;

/*
 * The interactor class that handles the leaderboard lookup use case.
 */
public class LeaderboardLookupInteractor implements LeaderboardLookupInputBoundary {

	private final LeaderboardLookupDataAccessInterface leaderboardLookupDataAccessInterface;
	private final LeaderboardLookupOutputBoundary leaderboardLookupOutputBoundary;

	public LeaderboardLookupInteractor(LeaderboardLookupDataAccessInterface leaderboardLookupDataAccessInterface,
			LeaderboardLookupOutputBoundary leaderboardLookupOutputBoundary) {
		this.leaderboardLookupDataAccessInterface = leaderboardLookupDataAccessInterface;
		this.leaderboardLookupOutputBoundary = leaderboardLookupOutputBoundary;
	}

	/**
	 * Executes the leaderboard lookup use case.
	 */
	public void execute(LeaderboardLookupInputData leaderboardLookupInputData) {
		try {
			List<User> users = leaderboardLookupDataAccessInterface
					.getLeaderboard(leaderboardLookupInputData.getSize());
			List<Match> matches = new ArrayList<Match>();
			for (User user : users) {
				matches.addAll(leaderboardLookupDataAccessInterface.getMatches(user.getTag()));
			}
			final LeaderboardLookupOutputData leaderboardLookupOutputData = new LeaderboardLookupOutputData(
					users.size(), matches);

			leaderboardLookupOutputBoundary.prepareSuccessView(leaderboardLookupOutputData);
		} catch (RuntimeException e) {
			leaderboardLookupOutputBoundary.prepareFailView(e.getMessage());
		}
	}
}