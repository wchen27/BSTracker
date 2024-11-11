package use_case.user_lookup;

public interface UserLookupInputBoundary {

	/**
	 * Executes the user lookup use case.
	 * 
	 * @param userLookupInputData the input data
	 */
	void execute(UserLookupInputData userLookupInputData);

}
