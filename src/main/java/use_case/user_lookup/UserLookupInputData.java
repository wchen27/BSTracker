package use_case.user_lookup;

/**
 * The Input Data for the User Lookup Use Case.
 */
public class UserLookupInputData {

	private final String tag;

	public UserLookupInputData(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}
}