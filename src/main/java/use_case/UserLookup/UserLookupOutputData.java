package use_case.UserLookup;

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
