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
		String prettyTag = tag.replace("#", "%23");

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
			return userFactory.create(responseBody.getString("name"), responseBody.getInt("trophies"),
					responseBody.getString("tag"));

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
		JSONObject currMatch = matches.getJSONObject(i);
		JSONObject event = currMatch.getJSONObject("event");
		JSONObject battle = currMatch.getJSONObject("battle");
		JSONObject starPlayer = battle.getJSONObject("starPlayer");
		JSONObject starPlayerBrawler = starPlayer.getJSONObject("brawler");

		try {
			trophyChange = battle.optInt("trophyChange");
		} catch (JSONException e) {
			trophyChange = 0;
		}
		return matchFactory.create(currMatch.getString("battleTime"), event.getString("mode"), event.getString("map"),
				battle.getString("result").equals("victory"), trophyChange, starPlayer.getString("name"),
				starPlayerBrawler.getString("name"), 0);
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
						userFactory.create(user.getString("name"), user.getInt("trophies"), user.getString("tag")));
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
						userFactory.create(user.getString("name"), user.getInt("trophies"), user.getString("tag")));
			}
			return members;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
