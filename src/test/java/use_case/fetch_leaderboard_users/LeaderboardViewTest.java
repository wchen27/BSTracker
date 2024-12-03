package use_case.fetch_leaderboard_users;

import org.junit.Test;

import view.LeaderboardView;
import interface_adapter.leaderboard_lookup.LeaderboardLookupViewModel;
import interface_adapter.ViewManagerModel;

public class LeaderboardViewTest {

	@Test
	// Tests the construction of the Leaderboard View.
	public void LeaderboardViewTest() {
		LeaderboardView view = new LeaderboardView(new LeaderboardLookupViewModel(), new ViewManagerModel());
	}

}
