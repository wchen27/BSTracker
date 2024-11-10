package interface_adapter.UserLookup;

import use_case.UserLookup.UserLookupOutputBoundary;
import use_case.UserLookup.UserLookupOutputData;

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
