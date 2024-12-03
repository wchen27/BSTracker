package use_case.fetch_user;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import entity.Brawler;
import entity.ClubFactory;
import entity.Match;

import java.math.MathContext;
import java.util.ArrayList;

import org.junit.Test;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.UserFactory;
import io.github.cdimascio.dotenv.Dotenv;
import entity.MatchFactory;
import entity.User;

public class GetUserAPITest {

	@Test
	public void getUserSuccessTest() {
		Dotenv env = Dotenv.load();
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), env);
		User user = api.getUser("G2VCCRRUP");
		assertEquals("The right username was not found.", user.getUsername(), "Thigamore");
	}

	@Test
	public void getUserSuccessHashtagTest() {
		Dotenv env = Dotenv.load();
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), env);
		User user = api.getUser("#G2VCCRRUP");
		assertEquals("The right username was not found.", user.getUsername(), "Thigamore");
	}

	@Test
	public void getUserFailTest() {
		Dotenv env = Dotenv.configure().filename("test.env").load();
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), env);
		User user = api.getUser("#G2VCCRRUP");
		assertArrayEquals(user.getBrawlers(), new Brawler[]{});
		assertEquals(user.getDuoVictories(), 0);
		assertEquals(user.getHighestTrophies(), 0);
		assertEquals(user.getPerformance(), 0);
		assertEquals(user.getSoloVictories(), 0);
		assertEquals(user.getTag(), "Unknown");
		assertEquals(user.getTrioVictories(), 0);
		assertEquals(user.getTrophies(), 0);
		assertEquals(user.getUsername(), "Unknown");
	}

}
