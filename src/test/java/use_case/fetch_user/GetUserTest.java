package use_case.fetch_user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import entity.ClubFactory;
import java.math.MathContext;

import org.junit.Test;

import data_access.APIDataAccessObject;
import entity.UserFactory;
import entity.MatchFactory;
import entity.User;
public class GetUserTest {

	@Test
	public void getUserSuccessTest() {
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory());
		User user = api.getUser("G2VCCRRUP");
		assertEquals("The right username was not found.", user.getUsername(), "Thigamore");
	}

}
