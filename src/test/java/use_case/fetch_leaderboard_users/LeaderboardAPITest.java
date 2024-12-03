package use_case.fetch_leaderboard_users;

import entity.ClubFactory;
import org.junit.Test;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.UserFactory;
import io.github.cdimascio.dotenv.Dotenv;
import entity.User;
import entity.MatchFactory;

import java.util.List;

public class LeaderboardAPITest {

	@Test
	public void getLeaderboardSuccessTest() {
		Dotenv env = Dotenv.load();
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), env);
		List<User> users = api.getLeaderboard(10);
	}

	@Test
	public void getLeaderboardFailTest() {
		Dotenv env = Dotenv.load();
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), env);
		try {
			List<User> users = api.getLeaderboard(-1);
		} catch (RuntimeException e) {
			System.out.println("Caught");
		}
	}

}