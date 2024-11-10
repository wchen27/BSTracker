package use_case.user_lookup;

public class UserLookupOutputData {

	private final String username;
	private final int trophies;

	public UserLookupOutputData(String username, int trophies) {
		this.username = username;
		this.trophies = trophies;
	}

	String getUsername() {
		return username;
	}

	int getTrophies() {
		return trophies;
	}
}
