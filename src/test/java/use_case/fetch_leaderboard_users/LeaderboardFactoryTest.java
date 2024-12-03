package use_case.fetch_leaderboard_users;

import org.junit.Test;

import app.LeaderboardLookupUseCaseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard_lookup.LeaderboardLookupViewModel;

public class LeaderboardFactoryTest {

	@Test
	// Tests the Leaderboard use case factory functionality.
	public void LeaderboardLookupUseCaseFactoryTest() {
		LeaderboardLookupUseCaseFactory.create(new ViewManagerModel(), new LeaderboardLookupViewModel());
	}
}
