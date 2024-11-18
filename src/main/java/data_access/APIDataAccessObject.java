package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import entity.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.club_lookup.ClubLookupDataAccessInterface;
import use_case.match_lookup.MatchLookupDataAccessInterface;
import use_case.user_lookup.UserLookupDataAccessInterface;
import use_case.brawler_lookup.BrawlerLookupDataAccessInterface;
import use_case.leaderboard_lookup.LeaderboardLookupDataAccessInterface;


public class APIDataAccessObject
		implements UserLookupDataAccessInterface, BrawlerLookupDataAccessInterface, MatchLookupDataAccessInterface,
		LeaderboardLookupDataAccessInterface, ClubLookupDataAccessInterface {

	private UserFactory userFactory;
	private MatchFactory matchFactory;
	private ClubFactory clubFactory;

	public APIDataAccessObject(UserFactory userFactory, MatchFactory matchFactory, ClubFactory clubFactory) {
		this.userFactory = userFactory;
		this.matchFactory = matchFactory;
		this.clubFactory = clubFactory;
	}

	@Override
	public User getUser(String tag) {

		Dotenv dotenv = Dotenv.load();
		String prettyTag = "";
		if(tag.startsWith("#")) {
			tag.replace("#", "%23");
		} else {
			prettyTag = "%23" + tag;
		}

		final String url = "https://api.brawlstars.com/v1/players/" + prettyTag;
		final String key = dotenv.get("API_KEY");

		final OkHttpClient client = new OkHttpClient().newBuilder().build();
		final Request request = new Request.Builder()
				.url(url)
				.addHeader("Authorization", key)
				.get()
				.build();

		try {
			final Response response = client.newCall(request).execute();
			final JSONObject responseBody = new JSONObject(response.body().string());
			//TODO - put the matches and brawlers into the user
			return userFactory.create(responseBody.getString("tag"), responseBody.getString("name"), responseBody.getInt("trophies"), responseBody.getInt("highestTrophies"), responseBody.getInt("3vs3Victories"), responseBody.getInt("duoVictories"), responseBody.getInt("soloVictories"), new Brawler[]{}, new Match[]{});

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Match> getMatches(String tag) {
		Dotenv dotenv = Dotenv.load();

		String prettyTag = tag.replace("#", "%23");
		final String key = dotenv.get("API_KEY");
		final String url = "https://api.brawlstars.com/v1/players/" + prettyTag + "/battlelog";
		final OkHttpClient client = new OkHttpClient().newBuilder().build();
		final Request request = new Request.Builder()
				.url(url)
				.addHeader("Authorization", key)
				.get()
				.build();

		try {
			final Response response = client.newCall(request).execute();
			final JSONObject responseBody = new JSONObject(response.body().string());
			final JSONArray matches = responseBody.getJSONArray("items");
			List<Match> matchList = new ArrayList<>();
			for (int i = 0; i < matches.length(); i++) {
				matchList.add(extractMatchData(matches, i));
			}

			return matchList;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Match extractMatchData(JSONArray matches, int i) {
		int trophyChange;
		String battleTime;
		String mode;
		String map;
		boolean result;
		String starPlayerName;
		String starPlayerBrawler;
		JSONObject currMatch = matches.getJSONObject(i);
		JSONObject event;
		JSONObject battle;
		JSONObject starPlayer;
		try {
			event = currMatch.getJSONObject("event");
		} catch (JSONException e) {
			event = new JSONObject();
		}
		try {
			battle = currMatch.getJSONObject("battle");
		} catch (JSONException e) {
			battle = new JSONObject();
		}
		try {
			starPlayer = battle.getJSONObject("starPlayer");
		} catch (JSONException e) {
			starPlayer = new JSONObject();
		}
		
		try {
			trophyChange = battle.optInt("trophyChange");
		} catch (JSONException e) {
			trophyChange = 0;
		}
		try {
			battleTime = currMatch.getString("battleTime");
		} catch (JSONException e) {
			battleTime = "Unknown";
		}
		try {
			mode = event.getString("mode");
		} catch (JSONException e) {
			mode = "Unknown";
		}
		try {
			map = event.getString("map");
		} catch (JSONException e) {
			map = "Unknown";
		}
		try {
			result = battle.getString("result").equals("victory");
		} catch (JSONException e) {
			result = false;
		}
		try {
			starPlayerName = starPlayer.getString("name");
		} catch (JSONException e) {
			starPlayerName = "Unknown";
		}
		try {
			starPlayerBrawler = starPlayer.getJSONObject("brawler").getString("name");
		} catch (JSONException e) {
			starPlayerBrawler = "Unknown";
		}
		return matchFactory.create(battleTime, mode, map, result, trophyChange, starPlayerName, starPlayerBrawler, 0);
	}
	
	public List<User> getLeaderboard(int amount) {
		Dotenv dotenv = Dotenv.load();

		final String url = "https://api.brawlstars.com/v1/rankings/global/players?limit=" + amount;
		final String key = dotenv.get("API_KEY");
		final List<User> topUsers = new ArrayList<User>(amount);

		final OkHttpClient client = new OkHttpClient().newBuilder().build();
		final Request request = new Request.Builder()
				.url(url)
				.addHeader("Authorization", key)
				.get()
				.build();

		try {
			final Response response = client.newCall(request).execute();
			final JSONObject responseBody = new JSONObject(response.body().string());
			final JSONArray items = responseBody.getJSONArray("items");
			for (int i = 0; i < items.length(); i++) {
				JSONObject user = items.getJSONObject(i);
				topUsers.add(
						userFactory.create(user.getString("tag"), user.getString("name"), user.getInt("trophies"), user.getInt("highestTrophies"), user.getInt("3vs3Victories"), user.getInt("duoVictories"), user.getInt("soloVictories"), new Brawler[]{}, new Match[]{}));
			}
			return topUsers;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> getMembers(String tag) {
		Dotenv dotenv = Dotenv.load();

		tag = tag.replace("#", "%23");

		final String url = "https://api.brawlstars.com/v1/clubs/" + tag + "/members";
		final String key = dotenv.get("API_KEY");

		final OkHttpClient client = new OkHttpClient().newBuilder().build();
		final Request request = new Request.Builder()
				.url(url)
				.addHeader("Authorization", key)
				.get()
				.build();

		try {
			final Response response = client.newCall(request).execute();
			final JSONObject responseBody = new JSONObject(response.body().string());
			final JSONArray items = responseBody.getJSONArray("items");
			final List<User> members = new ArrayList<User>(items.length());
			for (int i = 0; i < items.length(); i++) {
				JSONObject user = items.getJSONObject(i);
				members.add(
						userFactory.create(user.getString("tag"), user.getString("name"),
								user.getInt("trophies"))
				);
			}
			return members;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
