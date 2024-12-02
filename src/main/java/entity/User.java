package entity;

import java.util.List;

public class User {

	private final String tag;
	private final String username;
	private final int trophies;
	private final int highestTrophies;
	private final int trioVictories;
	private final int duoVictories;
	private final int soloVictories;
	private final Brawler[] brawlers;
	private final List<Match> matches;
	//TODO implement the club
	// private final Club club;

	public User(String tag, String username, int trophies, int highestTrophies, int trioVictories, int duoVictories, int soloVictories, Brawler[] brawlers, List<Match> matches) {
		this.tag = tag;
		this.username = username;
		this.trophies = trophies;
		this.highestTrophies = highestTrophies;
		this.trioVictories = trioVictories;
		this.duoVictories = duoVictories;
		this.soloVictories = soloVictories;
		this.brawlers = brawlers;
		this.matches = matches;
	}

	public String getTag() {
		return tag;
	}

	public String getUsername() {
		return username;
	}

	public int getTrophies() {
		return trophies;
	}

	public int getHighestTrophies() {
		return highestTrophies;
	}

	public int getTrioVictories() {
		return trioVictories;
	}

	public int getDuoVictories() {
		return duoVictories;
	}

	public int getSoloVictories() {
		return soloVictories;
	}

	public Brawler[] getBrawlers() {
		return brawlers;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public int getPerformance() {
		return (trophies + trioVictories + duoVictories + soloVictories )/ 10 * 3;
	}
	
}
