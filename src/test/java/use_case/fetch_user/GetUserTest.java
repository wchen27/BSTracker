package use_case.fetch_user;

import org.junit.Test;

import data_access.APIDataAccessObject;
import entity.UserFactory;

public class GetUserTest {

	@Test
	public void getUserSuccessTest() {
		APIDataAccessObject api = new APIDataAccessObject(new UserFactory());
		api.getUser("%23822GY0JYY");
	}

}
