package use_case.fetch_user;

import org.junit.Test;

import data_access.APIDataAccessObject;

public class GetUserTest {

	@Test
	public void getUserSuccessTest() {
		APIDataAccessObject api = new APIDataAccessObject();
		api.getUser("%23822GY0JYY");
	}

}
