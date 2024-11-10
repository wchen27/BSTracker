package app;

import interface_adapter.user_lookup.UserLookupController;
import interface_adapter.user_lookup.UserLookupPresenter;
import interface_adapter.user_lookup.UserLookupViewModel;
import use_case.brawler_lookup.BrawlerLookupDataAccessInterface;
import use_case.brawler_lookup.BrawlerLookupInputBoundary;
import use_case.brawler_lookup.BrawlerLookupOutputBoundary;
import use_case.user_lookup.UserLookupOutputBoundary;
import view.SearchView;
import interface_adapter.brawler_lookup.BrawlerLookupController;
import interface_adapter.brawler_lookup.BrawlerLookupPresenter;
import interface_adapter.search.SearchViewModel;
import use_case.brawler_lookup.BrawlerLookupInteractor;
import use_case.user_lookup.UserLookupDataAccessInterface;
import interface_adapter.ViewManagerModel;
import use_case.user_lookup.UserLookupInteractor;
import use_case.user_lookup.UserLookupInputBoundary;

public final class SearchUseCaseFactory {
    private SearchUseCaseFactory() {
    }

    public static SearchView create(
        SearchViewModel searchViewModel, UserLookupViewModel userViewModel, ViewManagerModel viewManagerModel, BrawlerLookupDataAccessInterface brawlerDataAccessObject, UserLookupDataAccessInterface userLookupDataAcessObject) {
            final BrawlerLookupController brawlerLookupController = createBrawlerLookupUseCase(searchViewModel, brawlerDataAccessObject);
            final UserLookupController userLookupController = createUserLookupUseCase(userViewModel, viewManagerModel, userLookupDataAcessObject);
            return new SearchView(searchViewModel, brawlerLookupController, userLookupController);

    }

    private static BrawlerLookupController createBrawlerLookupUseCase(SearchViewModel searchViewModel, BrawlerLookupDataAccessInterface brawlerDataAccessObject) {
        final BrawlerLookupOutputBoundary brawlerLookupOnputBoundary = new BrawlerLookupPresenter();
        final BrawlerLookupInputBoundary brawlerLookupInteractor = new BrawlerLookupInteractor(brawlerDataAccessObject, brawlerLookupOnputBoundary);
        return new BrawlerLookupController(brawlerLookupInteractor);
    }

    private static UserLookupController createUserLookupUseCase(UserLookupViewModel userLookupViewModel, ViewManagerModel viewManagerModel, UserLookupDataAccessInterface userLookupDataAcessObject) {

        final UserLookupOutputBoundary userLookupOutputBoundary = new UserLookupPresenter(userLookupViewModel, viewManagerModel);
        final UserLookupInputBoundary userLookupInteractor = new UserLookupInteractor(userLookupDataAcessObject, userLookupOutputBoundary);
        return new UserLookupController(userLookupInteractor);

    }   


    

}