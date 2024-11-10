package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.APIDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
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

		
	}
}
