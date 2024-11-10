package interface_adapter.user_lookup;

import use_case.user_lookup.UserLookupInputData;
import use_case.user_lookup.UserLookupInputBoundary;

public class UserLookupController {

    private final UserLookupInputBoundary interactor;

    public UserLookupController(UserLookupInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String tag) {
        final UserLookupInputData inputData = new UserLookupInputData(tag);

        interactor.execute(inputData);
    }

}
