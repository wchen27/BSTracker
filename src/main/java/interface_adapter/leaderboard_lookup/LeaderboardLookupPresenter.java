package interface_adapter.leaderboard_lookup;

import use_case.leaderboard_lookup.LeaderboardLookupOutputBoundary;
import use_case.leaderboard_lookup.LeaderboardLookupOutputData;

import javax.swing.JOptionPane;

import interface_adapter.ViewManagerModel;

public class LeaderboardLookupPresenter implements LeaderboardLookupOutputBoundary {

	private final LeaderboardLookupViewModel leaderboardLookupViewModel;
	private final ViewManagerModel viewManagerModel;

	public LeaderboardLookupPresenter(LeaderboardLookupViewModel leaderboardLookupViewModel,
			ViewManagerModel viewManagerModel) {
		this.leaderboardLookupViewModel = leaderboardLookupViewModel;
		this.viewManagerModel = viewManagerModel;
	}

	public void prepareSuccessView(LeaderboardLookupOutputData leaderboardLookupOutputData) {
		final LeaderboardLookupState state = leaderboardLookupViewModel.getState();
		state.setBrawlerFrequency(leaderboardLookupOutputData.getBrawlerFrequency());
		state.setNumUsers(leaderboardLookupOutputData.getNumUsers());

		leaderboardLookupViewModel.firePropertyChanged();
		this.viewManagerModel.setState(leaderboardLookupViewModel.getViewName());
		this.viewManagerModel.firePropertyChanged();
	}

	public void prepareFailView(String errorMessage) {
		JOptionPane.showMessageDialog(null, "Error when fetching global leaderboards! Please try again later. \n" + errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
