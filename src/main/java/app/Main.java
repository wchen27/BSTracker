package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.APIDataAccessObject;
import entity.MatchFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.match_lookup.MatchLookupViewModel;
import view.MatchView;
import view.SearchView;
import view.UserView;
import view.ViewManager;
import interface_adapter.search.SearchViewModel;
import interface_adapter.user_lookup.UserLookupViewModel;

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

		final APIDataAccessObject api = new APIDataAccessObject(new UserFactory());
		final APIDataAccessObject matchApi = new APIDataAccessObject(new MatchFactory());

		final SearchView searchView = SearchUseCaseFactory.create(searchViewModel, userLookupViewModel, matchLookupViewModel,
				viewManagerModel, api, api, matchApi);
		views.add(searchView, searchView.getViewName());

		final UserView userView = UserLookupUseCaseFactory.create(viewManagerModel, userLookupViewModel, api);
		views.add(userView, userView.getViewName());

		final MatchView matchView = MatchLookupUseCaseFactory.create(viewManagerModel, matchLookupViewModel);
		views.add(matchView, matchView.getViewName());

		viewManagerModel.setState(searchView.getViewName());
		viewManagerModel.firePropertyChanged();

		application.setSize(800, 600);
		application.setVisible(true);
	}
}
