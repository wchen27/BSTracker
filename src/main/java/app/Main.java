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
import view.*;
import interface_adapter.search.SearchViewModel;
import interface_adapter.user_lookup.UserLookupViewModel;
import io.github.cdimascio.dotenv.Dotenv;
import interface_adapter.leaderboard_lookup.LeaderboardLookupViewModel;
import interface_adapter.match_lookup.MatchLookupViewModel;
import interface_adapter.previous_search.PreviousSearchViewModel;

public class Main {

	/*
	 * Main method for the Brawl Stars Tracker!
	 */
	public static void main(String[] args) {

		// Create the main frame for the application and configure its exit behaviour
		final JFrame application = new JFrame("Brawl Stars Tracker");
		application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Initialize the card layout for the views
		final CardLayout cardLayout = new CardLayout();
		final JPanel views = new JPanel(cardLayout);
		application.add(views);

		// Make the view manager model. 
		final ViewManagerModel viewManagerModel = new ViewManagerModel();
		new ViewManager(views, cardLayout, viewManagerModel);

		// Initialize the view models for each view in the application. 
		final SearchViewModel searchViewModel = new SearchViewModel();
		final UserLookupViewModel userLookupViewModel = new UserLookupViewModel();
		final MatchLookupViewModel matchLookupViewModel = new MatchLookupViewModel();
		final ClubLookupViewModel clubLookupViewModel = new ClubLookupViewModel();
		final LeaderboardLookupViewModel leaderboardLookupViewModel = new LeaderboardLookupViewModel();
		final PreviousSearchViewModel previousSearchViewModel = new PreviousSearchViewModel();

		// Set up the DAO for calls to the Brawl Stars API.
		Dotenv env = Dotenv.load();
		final FileDataAccessObject fileDataAccessObject = new FileDataAccessObject("previousSearches.txt");
		final APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(),
				new ClubFactory(), env);

		// Create the actual views.
		final SearchView searchView = SearchUseCaseFactory.create(searchViewModel, userLookupViewModel,
				matchLookupViewModel, leaderboardLookupViewModel, clubLookupViewModel, previousSearchViewModel,
				viewManagerModel, api, api, api, api, fileDataAccessObject);
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

		// Set the default screen to the searching home screen.
		viewManagerModel.setState(searchView.getViewName());
		viewManagerModel.firePropertyChanged();

		// Configure visibility and dimension settings. 
		application.setSize(800, 600);
		application.setVisible(true);
	}
}
