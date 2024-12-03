package use_case.fetch_leaderboard_users;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard_lookup.LeaderboardLookupController;
import interface_adapter.leaderboard_lookup.LeaderboardLookupPresenter;
import interface_adapter.leaderboard_lookup.LeaderboardLookupState;
import interface_adapter.leaderboard_lookup.LeaderboardLookupViewModel;
import use_case.leaderboard_lookup.LeaderboardLookupInteractor;

public class LeaderboardInterfaceAdapterTest {

	@Test
	// Tests the functionality of LeaderboardLookupState.
	public void LeaderboardStateTest() {
		LeaderboardLookupState state = new LeaderboardLookupState();
		Map<String, Integer> dummyfreq = new HashMap<>();
		dummyfreq.put("Dummy", 12);
		state.setBrawlerFrequency(dummyfreq);
		Map<String, Integer> returnedFrequency = state.getBrawlerFrequency();
		assertEquals(returnedFrequency, dummyfreq);
		state.setNumUsers(15);
		int returnedUsers = state.getNumUsers();
		assertEquals(returnedUsers, 15);
	}

	@Test
	// Test the functionality of LeaderboardLookupController.
	public void LeaderboardControllerTest() {
		LeaderboardLookupInteractor interactor = new LeaderboardLookupInteractor(
				new APIDataAccessObject(new UserFactory(), new MatchFactory(),
						new ClubFactory(), new FileDataAccessObject("testfile.txt")),
				new LeaderboardLookupPresenter(new LeaderboardLookupViewModel(), new ViewManagerModel()));
		LeaderboardLookupController controller = new LeaderboardLookupController(interactor);

		controller.execute(20);
	}
}
