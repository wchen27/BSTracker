package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.ClubFactory;
import entity.MatchFactory;
import entity.UserFactory;

import interface_adapter.ViewManagerModel;

import interface_adapter.club_lookup.ClubLookupViewModel;
import interface_adapter.user_lookup.UserLookupController;
import use_case.user_lookup.UserLookupInteractor;
import view.*;
import interface_adapter.search.SearchViewModel;
import interface_adapter.user_lookup.UserLookupViewModel;
import interface_adapter.leaderboard_lookup.LeaderboardLookupViewModel;
import interface_adapter.match_lookup.MatchLookupViewModel;
import interface_adapter.previous_search.PreviousSearchViewModel;

public class Main {

	public static void main(String[] args) {

		final JFrame application = new JFrame("Brawl Stars Tracker");

		application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		final CardLayout cardLayout = new CardLayout();

		final JPanel views = new JPanel(cardLayout);
		application.add(views);

		final ViewManagerModel viewManagerModel = new ViewManagerModel();
		new ViewManager(views, cardLayout, viewManagerModel);

		final SearchViewModel searchViewModel = new SearchViewModel();
		final UserLookupViewModel userLookupViewModel = new UserLookupViewModel();
		final MatchLookupViewModel matchLookupViewModel = new MatchLookupViewModel();
		final ClubLookupViewModel clubLookupViewModel = new ClubLookupViewModel();
		final LeaderboardLookupViewModel leaderboardLookupViewModel = new LeaderboardLookupViewModel();
		final PreviousSearchViewModel previousSearchViewModel = new PreviousSearchViewModel();

		final FileDataAccessObject fileDataAccessObject = new FileDataAccessObject("previousSearches.txt");
		final APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(),
				new ClubFactory(), fileDataAccessObject);

		final SearchView searchView = SearchUseCaseFactory.create(searchViewModel, userLookupViewModel,
				matchLookupViewModel, leaderboardLookupViewModel, clubLookupViewModel, previousSearchViewModel,
				viewManagerModel, api, api, api, api, api, fileDataAccessObject);
		views.add(searchView, searchView.getViewName());

		final UserView userView = UserLookupUseCaseFactory.create(viewManagerModel, userLookupViewModel, api);
		views.add(userView, userView.getViewName());

		final MatchView matchView = MatchLookupUseCaseFactory.create(viewManagerModel, matchLookupViewModel);
		views.add(matchView, matchView.getViewName());

		final ClubView clubView = ClubLookupUseCaseFactory.create(viewManagerModel, clubLookupViewModel,
				userLookupViewModel, api);
		views.add(clubView, clubView.getViewName());

		final LeaderboardView leaderboardView = LeaderboardLookupUseCaseFactory.create(viewManagerModel,
				leaderboardLookupViewModel);
		views.add(leaderboardView, leaderboardView.getViewName());

		viewManagerModel.setState(searchView.getViewName());
		viewManagerModel.firePropertyChanged();

		application.setSize(800, 600);
		application.setVisible(true);
	}
}
