package entity;

public class User {

	private final String username;
	private final int trophies;

	public User(String username, int trophies) {
		this.username = username;
		this.trophies = trophies;
	}

	public String getUsername() {
		return username;
	}

	public int getTrophies() {
		return trophies;
	}
}
