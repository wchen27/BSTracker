package interface_adapter.user_lookup;

import interface_adapter.ViewManagerModel;
import use_case.user_lookup.UserLookupOutputBoundary;
import use_case.user_lookup.UserLookupOutputData;

public class UserLookupPresenter implements UserLookupOutputBoundary {

    private final UserLookupViewModel userViewModel;
    private final ViewManagerModel viewManagerModel;

    public UserLookupPresenter(UserLookupViewModel userViewModel, ViewManagerModel managerModel) {
        this.userViewModel = userViewModel;
        this.viewManagerModel = managerModel;
    }

    @Override
    public void prepareSuccessView(UserLookupOutputData outputData) {
        final UserLookupState state = userViewModel.getState();
        state.setUsername(outputData.getUsername());
        state.setTrophyCount(outputData.getTrophies());

        userViewModel.firePropertyChanged();
        this.viewManagerModel.setState(userViewModel.getViewName());

    }

    @Override
    public void prepareFailView(String errorMessage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prepareErrorBoundary'");
    }

}
