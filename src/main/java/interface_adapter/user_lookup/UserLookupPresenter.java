package interface_adapter.user_lookup;

import javax.swing.JOptionPane;

import interface_adapter.ViewManagerModel;
import use_case.user_lookup.UserLookupOutputBoundary;
import use_case.user_lookup.UserLookupOutputData;

/*
 * The presenter for the user lookup
 */
public class UserLookupPresenter implements UserLookupOutputBoundary {

    private final UserLookupViewModel userViewModel;
    private final ViewManagerModel viewManagerModel;

    public UserLookupPresenter(UserLookupViewModel userViewModel, ViewManagerModel managerModel) {
        this.userViewModel = userViewModel;
        this.viewManagerModel = managerModel;
    }

    /**
     * Prepares the success view if a user was able to be found successfully
     * 
     * @param outputData The data that will be needed to show the correct information to the user
     */
    @Override
    public void prepareSuccessView(UserLookupOutputData outputData) {
        final UserLookupState state = userViewModel.getState();
        state.setUser(outputData.getUser());

        userViewModel.firePropertyChanged();
        this.viewManagerModel.setState(userViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view if a user was not able to be found
     * 
     * @param errorMessage The error message that the user should see
     */
    @Override
    public void prepareFailView(String errorMessage) {
        JOptionPane.showMessageDialog(null, "The user tag could not be fetched. Please try again! \n" + errorMessage,
                "Error", JOptionPane.ERROR_MESSAGE);
    }

}
