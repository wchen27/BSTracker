package interface_adapter.user_lookup;

import use_case.user_lookup.UserLookupInputData;
import use_case.user_lookup.UserLookupInputBoundary;

/*
 * The controller for the user lookup
 */
public class UserLookupController {

    private final UserLookupInputBoundary interactor;

    public UserLookupController(UserLookupInputBoundary interactor) {
        this.interactor = interactor;
    }
    
    /*
     * Executes the user lookup interactor with the tag
     * 
     * @param tag The tag for the user that will be looked up
     */
    public void execute(String tag) {
        final UserLookupInputData inputData = new UserLookupInputData(tag);

        interactor.execute(inputData);
    }

}
