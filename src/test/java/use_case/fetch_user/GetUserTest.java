package use_case.fetch_user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import entity.ClubFactory;
import java.math.MathContext;

import org.junit.Test;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.UserFactory;
import io.github.cdimascio.dotenv.Dotenv;
import entity.MatchFactory;
import entity.User;

public class GetUserTest {

	@Test
	public void getUserSuccessTest() {
		Dotenv env = Dotenv.load();
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), env);
		User user = api.getUser("G2VCCRRUP");
		assertEquals("The right username was not found.", user.getUsername(), "Thigamore");
	}

}
