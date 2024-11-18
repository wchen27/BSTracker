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
        state.setUser(outputData.getUser());

        userViewModel.firePropertyChanged();
        this.viewManagerModel.setState(userViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        throw new UnsupportedOperationException(errorMessage);
    }

}
