package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.club_lookup.ClubLookupViewModel;
import interface_adapter.user_lookup.UserLookupController;
import interface_adapter.user_lookup.UserLookupPresenter;
import interface_adapter.user_lookup.UserLookupViewModel;
import use_case.user_lookup.UserLookupDataAccessInterface;
import use_case.user_lookup.UserLookupInputBoundary;
import use_case.user_lookup.UserLookupInteractor;
import use_case.user_lookup.UserLookupOutputBoundary;
import view.ClubView;
import view.SearchView;

public class ClubLookupUseCaseFactory {

        private ClubLookupUseCaseFactory() {
        }

        public static ClubView create(ViewManagerModel viewManagerModel, ClubLookupViewModel clubLookupViewModel,
                                      UserLookupViewModel userViewModel, UserLookupDataAccessInterface userLookupDataAccessObject) {
            final UserLookupController userLookupController = createUserLookupUseCase(userViewModel,
                    viewManagerModel,
                    userLookupDataAccessObject);
            return new ClubView(clubLookupViewModel, viewManagerModel, userLookupController);
        }

    private static UserLookupController createUserLookupUseCase(UserLookupViewModel userLookupViewModel,
                                                                ViewManagerModel viewManagerModel, UserLookupDataAccessInterface userLookupDataAcessObject) {

        final UserLookupOutputBoundary userLookupOutputBoundary = new UserLookupPresenter(userLookupViewModel,
                viewManagerModel);
        final UserLookupInputBoundary userLookupInteractor = new UserLookupInteractor(userLookupDataAcessObject,
                userLookupOutputBoundary);
        return new UserLookupController(userLookupInteractor);

    }
}
