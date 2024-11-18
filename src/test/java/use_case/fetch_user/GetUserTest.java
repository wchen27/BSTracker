package use_case.fetch_user;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import data_access.APIDataAccessObject;
import entity.UserFactory;
import entity.MatchFactory;
import entity.User;
public class GetUserTest {

	@Test
	public void getUserSuccessTest() {
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory());
		User user = api.getUser("#822GY0JYY");
		assertNotEquals(user.getUsername(), "Thigamore");
	}

}
