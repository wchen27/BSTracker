package entity;

public class User {

	private final String username;
	private final int trophies;
	private final String tag;

	public User(String username, int trophies, String tag) {
		this.username = username;
		this.trophies = trophies;
		this.tag = tag;
	}

	public String getUsername() {
		return username;
	}

	public int getTrophies() {
		return trophies;
	}

	public String getTag() {
		return tag;
	}
}
