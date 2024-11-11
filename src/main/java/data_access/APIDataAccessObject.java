package data_access;

import java.io.IOException;

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

	public APIDataAccessObject(UserFactory userFactory) {
		this.userFactory = userFactory;
	}

	@Override
	public User getUser(String tag) {

		Dotenv dotenv = Dotenv.load();

		final String url = "https://api.brawlstars.com/v1/players/" + tag;
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
}
