package interface_adapter.leaderboard_lookup;

import interface_adapter.ViewModel;

public class LeaderboardLookupViewModel extends ViewModel<LeaderboardLookupState> {

	public LeaderboardLookupViewModel() {
		super("leaderboard lookup");
		this.setState(new LeaderboardLookupState());
	}

}
