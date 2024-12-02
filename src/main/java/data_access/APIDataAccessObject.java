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
	private FileDataAccessObject fileDataAccessObject;
	private Dotenv env;

	public APIDataAccessObject(UserFactory userFactory, MatchFactory matchFactory, ClubFactory clubFactory, FileDataAccessObject fileDataAccessObject, Dotenv env) {
		this.userFactory = userFactory;
		this.matchFactory = matchFactory;
		this.clubFactory = clubFactory;
		this.fileDataAccessObject = fileDataAccessObject;
		this.env = env;
	}

	@Override
	public User getUser(String tag) {

		fileDataAccessObject.addSearch(tag);

		String prettyTag = "";
		if(tag.startsWith("#")) {
			prettyTag = tag.replace("#", "%23");
		} else {
			prettyTag = "%23" + tag;
		}

		final String url = "https://api.brawlstars.com/v1/players/" + prettyTag;
		final String key = env.get("API_KEY");

		final OkHttpClient client = new OkHttpClient().newBuilder().build();
		final Request request = new Request.Builder()
				.url(url)
				.addHeader("Authorization", key)
				.get()
				.build();
		
		final Response response;
		final JSONObject responseBody;
		try {
			response = client.newCall(request).execute();
			responseBody = new JSONObject(response.body().string()); 
			if(responseBody.has("reason") && responseBody.getString("reason").equals("accessDenied")) {
				throw new IOException("Access Denied");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return getUserInfo(responseBody);
	}

	private User getUserInfo(JSONObject responseBody) {
		String playerTag;
		String playerName;
		int playerTrophies;
		int highestTrophies;
		int victories3v3;
		int victoriesDuo;
		int victoriesSolo;
		try {
			playerTag = responseBody.getString("tag");
		} catch (JSONException e) {
			playerTag = "Unknown";
		}

		try {
			playerName = responseBody.getString("name");
		} catch (JSONException e) {
			playerName = "Unknown";
		}

		try {
			playerTrophies = responseBody.getInt("trophies");
		} catch (JSONException e) {
			playerTrophies = 0;
		}

		try {
			highestTrophies = responseBody.getInt("highestTrophies");
		} catch (JSONException e) {
			highestTrophies = 0;
		}

		try {
			victories3v3 = responseBody.getInt("3vs3Victories");
		} catch (JSONException e) {
			victories3v3 = 0;
		}

		try {
			victoriesDuo = responseBody.getInt("duoVictories");
		} catch (JSONException e) {
			victoriesDuo = 0;
		}

		try {
			victoriesSolo = responseBody.getInt("soloVictories");
		} catch (JSONException e) {
			victoriesSolo = 0;
		}

		return userFactory.create(playerTag, playerName, playerTrophies, highestTrophies, victories3v3, victoriesDuo,
				victoriesSolo, new Brawler[]{}, new Match[]{});
	}

	@Override
	public List<Match> getMatches(String tag) {

		
		fileDataAccessObject.addSearch(tag);

		String prettyTag = tag.replace("#", "%23");
		final String key = env.get("API_KEY");
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
				Match matchData = extractMatchData(matches, i);
				matchList.add(matchData);
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

		final String url = "https://api.brawlstars.com/v1/rankings/global/players?limit=" + amount;
		final String key = env.get("API_KEY");
		final List<User> topUsers = new ArrayList<User>(amount);

		final OkHttpClient client = new OkHttpClient().newBuilder().build();
		final Request request = new Request.Builder()
				.url(url)
				.addHeader("Authorization", key)
				.get()
				.build();
		
		final Response response;
		final JSONObject responseBody;
		try {
			response = client.newCall(request).execute();
			responseBody = new JSONObject(response.body().string());
			System.out.println(responseBody);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		final JSONArray items;
		try {
			items = responseBody.getJSONArray("items");
		} catch (JSONException e) {
			throw new RuntimeException("Bad response, check API Key", e);
		}
		for (int i = 0; i < items.length(); i++) {
			JSONObject user = items.getJSONObject(i);
			topUsers.add(getUserInfo(user));
		}
		return topUsers;

	}

	@Override
	public List<User> getMembers(String tag) {
	
		fileDataAccessObject.addSearch(tag);

		tag = tag.replace("#", "%23");

		final String url = "https://api.brawlstars.com/v1/clubs/" + tag + "/members";
		final String key = env.get("API_KEY");

		final OkHttpClient client = new OkHttpClient().newBuilder().build();
		final Request request = new Request.Builder()
				.url(url)
				.addHeader("Authorization", key)
				.get()
				.build();

		final Response response;
		final JSONObject responseBody;
		try {
			response = client.newCall(request).execute();
			responseBody = new JSONObject(response.body().string());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		final JSONArray items;
		try {
			items = responseBody.getJSONArray("items");
		} catch (JSONException e) {
			throw new RuntimeException("Bad response, check API Key", e);
		}


		final List<User> members = new ArrayList<User>(items.length());
		for (int i = 0; i < items.length(); i++) {
			JSONObject user = items.getJSONObject(i);
			String playerTag;
			String playerName;
			int playerTrophies;
			try {
				playerTag = user.getString("tag");
			} catch (JSONException e) {
				playerTag = "Unknown";
			}

			try {
				playerName = user.getString("name");
			} catch (JSONException e) {
				playerName = "Unknown";
			}

			try {
				playerTrophies = user.getInt("trophies");
			} catch (JSONException e) {
				playerTrophies = 0;
			}

			members.add(
					userFactory.create(playerTag, playerName, playerTrophies)
			);
		}
		return members;
	}
}
