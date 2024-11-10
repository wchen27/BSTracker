package interface_adapter.UserLookup;

import use_case.UserLookup.UserLookupInputData;
import use_case.UserLookup.UserLookupInteractor;

public class UserLookupController {

    private final UserLookupInteractor interactor;

    public UserLookupController(UserLookupInteractor interactor) {
        this.interactor = interactor;
    }

    public void execute(String tag) {
        final UserLookupInputData inputData = UserLookupInputData(tag);

        interactor.execute(inputData);
    }
    
}
