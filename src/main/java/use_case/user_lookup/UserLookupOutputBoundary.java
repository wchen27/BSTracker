package use_case.user_lookup;

public interface UserLookupOutputBoundary {

    /**
     * Prepares the success view for the UserLookup Use Case.
     * 
     * @param outputData the output data
     */
    void prepareSuccessView(UserLookupOutputData outputData);

    /**
     * Prepares the failure view for the User Lookup Use Case.
     * 
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
