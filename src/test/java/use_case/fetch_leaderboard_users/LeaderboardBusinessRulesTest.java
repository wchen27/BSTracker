package use_case.fetch_leaderboard_users;

import static org.junit.Assert.assertTrue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.junit.Test;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.ClubFactory;
import entity.MatchFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard_lookup.LeaderboardLookupPresenter;
import interface_adapter.leaderboard_lookup.LeaderboardLookupViewModel;
import use_case.leaderboard_lookup.LeaderboardLookupDataAccessInterface;
import use_case.leaderboard_lookup.LeaderboardLookupInputData;
import use_case.leaderboard_lookup.LeaderboardLookupInteractor;

public class LeaderboardBusinessRulesTest {

	@Test
	// Tests the failure of the Interactor.execute() method.
	public void InteractorExecuteFailTest() {

		LeaderboardLookupInteractor interactor = new LeaderboardLookupInteractor(
				new APIDataAccessObject(new UserFactory(), new MatchFactory(),
						new ClubFactory(), new FileDataAccessObject("testfile.txt")),
				new LeaderboardLookupPresenter(new LeaderboardLookupViewModel(), new ViewManagerModel()));
		LeaderboardLookupInputData data = new LeaderboardLookupInputData(-2);
		try {
			interactor.execute(data);
		} catch (RuntimeException e) {
			assertTrue(e.getMessage().contains("Illegal Capacity"));
		}
	}
}
