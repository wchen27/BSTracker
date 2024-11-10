package data_access;

import java.io.IOException;

import org.json.JSONObject;

import entity.User;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.UserLookup.UserLookupDataAccessInterface;

public class APIDataAccessObject implements UserLookupDataAccessInterface {

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
			final JSONObject responseBody = new JSONObject(response.body().string());
			System.out.println(responseBody.toString());
			return new User();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
