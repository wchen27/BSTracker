package use_case.fetch_leaderboard_users;

import entity.ClubFactory;
import org.junit.Test;

import data_access.APIDataAccessObject;
import entity.UserFactory;
import entity.User;
import entity.MatchFactory;

import java.util.List;

public class GetLeaderboardTest {

	@Test
	public void getLeaderboardSuccessTest() {
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory());
		List<User> users = api.getLeaderboard(10);
	}

	@Test
	public void getLeaderboardFailTest() {
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory());
		try {
			List<User> users = api.getLeaderboard(-1);
		} catch (RuntimeException e) {
			System.out.println("Caught");
		}
	}

}