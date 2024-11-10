package interface_adapter.UserLookup;

import use_case.UserLookup.UserLookupOutputBoundary;
import use_case.UserLookup.UserLookupOutputData;

public class UserLookupPresenter implements UserLookupOutputBoundary{

    @Override
    public void prepareSuccessBoundary(UserLookupOutputData outputData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prepareSuccessBoundary'");
    }

    @Override
    public void prepareFailBoundary(String errorMessage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prepareErrorBoundary'");
    }
    
}
