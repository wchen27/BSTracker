package use_case.UserLookup;

public interface UserLookupOutputBoundary {

     /**
     * Prepares the success view for the UserLookup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessBoundary(UserLookupOutputData outputData);

    /**
     * Prepares the failure view for the User Lookup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailBoundary(String errorMessage);
}
