package use_case.UserLookup;

public interface UserLookupInputBoundary {
    
    /**
     * Executes the user lookup use case.
     * @param signupInputData the input data
     */
    void execute(UserLookupInputData inputData);
}
