package app;

import interface_adapter.user_lookup.UserLookupViewModel;
import use_case.user_lookup.UserLookupDataAccessInterface;
import view.UserView;
import interface_adapter.ViewManagerModel;

public class UserLookupUseCaseFactory {
    private UserLookupUseCaseFactory() {
    }

    public static UserView create(
        ViewManagerModel viewManagerModel, 
        UserLookupViewModel userLookupViewModel,
        UserLookupDataAccessInterface userLookupDataAccessObject) {

            return new UserView(userLookupViewModel, viewManagerModel);
    }
}