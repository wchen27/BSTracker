package interface_adapter.user_lookup;

import use_case.user_lookup.UserLookupOutputBoundary;
import use_case.user_lookup.UserLookupOutputData;

public class UserLookupPresenter implements UserLookupOutputBoundary {

    @Override
    public void prepareSuccessView(UserLookupOutputData outputData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prepareSuccessBoundary'");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prepareErrorBoundary'");
    }

}
