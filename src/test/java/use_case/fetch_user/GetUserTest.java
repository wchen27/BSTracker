package use_case.fetch_user;

import entity.ClubFactory;
import org.junit.Test;

import data_access.APIDataAccessObject;
import entity.UserFactory;
import entity.MatchFactory;
public class GetUserTest {

	@Test
	public void getUserSuccessTest() {
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory());
		api.getUser("#822GY0JYY");
	}

}
