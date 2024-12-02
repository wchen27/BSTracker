package use_case.user_lookup;

/*
 * The input boundary for the user lookup
 */
public interface UserLookupInputBoundary {

	/**
	 * Executes the user lookup use case.
	 * 
	 * @param userLookupInputData the input data
	 */
	void execute(UserLookupInputData userLookupInputData);

}
