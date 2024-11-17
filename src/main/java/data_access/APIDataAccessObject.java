package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Match;
import entity.MatchFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.UserFactory;
import entity.User;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.user_lookup.UserLookupDataAccessInterface;
import use_case.brawler_lookup.BrawlerLookupDataAccessInterface;

public class APIDataAccessObject implements UserLookupDataAccessInterface, BrawlerLookupDataAccessInterface {

	private UserFactory userFactory;
	private MatchFactory matchFactory;

	public APIDataAccessObject(UserFactory userFactory) {
		this.userFactory = userFactory;
	}

	public APIDataAccessObject(MatchFactory matchFactory) {
		this.matchFactory = matchFactory;
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
			System.out.println(response);
			final JSONObject responseBody = new JSONObject(response.body().string());
			System.out.println(responseBody.toString());
			return userFactory.create(responseBody.getString("name"), responseBody.getInt("trophies"));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

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
			System.out.println(responseBody);
			final JSONArray matches = responseBody.getJSONArray("items");
			System.out.println(matches.toString());
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

		try {
			trophyChange = battle.optInt("trophyChange");
		} catch (JSONException e) {
			trophyChange = 0;
		}
		return matchFactory.create(currMatch.getString("battleTime"), event.getString("mode"), event.getString("map"), battle.getString("result").equals("victory"), trophyChange, starPlayer.getString("name"), 0);
	}
}
