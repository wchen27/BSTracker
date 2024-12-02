package use_case.user_lookup;

import entity.User;

/*
 * The output data for the user lookup use case
 */
public class UserLookupOutputData {

	private final User user;

	public UserLookupOutputData(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
