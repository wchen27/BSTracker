package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.APIDataAccessObject;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import view.SearchView;
import view.UserView;
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

		final APIDataAccessObject api = new APIDataAccessObject(new UserFactory());

		final SearchView searchView = SearchUseCaseFactory.create(searchViewModel, userLookupViewModel,
				viewManagerModel, api, api);
		views.add(searchView, searchView.getViewName());

		final UserView userView = UserLookupUseCaseFactory.create(viewManagerModel, userLookupViewModel, api);
		views.add(userView, userView.getViewName());

		viewManagerModel.setState(searchView.getViewName());
		viewManagerModel.firePropertyChanged();

		application.pack();
		application.setVisible(true);
	}
}
