package use_case.fetch_leaderboard_users;

import entity.ClubFactory;
import org.junit.Test;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.UserFactory;
import entity.User;
import entity.MatchFactory;

import java.util.List;

public class GetLeaderboardTest {

	@Test
	public void getLeaderboardSuccessTest() {
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(),
				new FileDataAccessObject("testfile.txt"));
		List<User> users = api.getLeaderboard(10);
	}

}
