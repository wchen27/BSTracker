package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard_lookup.LeaderboardLookupViewModel;
import view.LeaderboardView;

public class LeaderboardLookupUseCaseFactory {

	private LeaderboardLookupUseCaseFactory() {
	}

	public static LeaderboardView create(ViewManagerModel viewManagerModel,
			LeaderboardLookupViewModel leaderboardLookupViewModel) {
		return new LeaderboardView(leaderboardLookupViewModel, viewManagerModel);
	}
}
