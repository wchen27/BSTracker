package app;

import interface_adapter.club_lookup.ClubLookupController;
import interface_adapter.club_lookup.ClubLookupPresenter;
import interface_adapter.club_lookup.ClubLookupViewModel;
import interface_adapter.leaderboard_lookup.LeaderboardLookupController;
import interface_adapter.leaderboard_lookup.LeaderboardLookupPresenter;
import interface_adapter.leaderboard_lookup.LeaderboardLookupViewModel;
import interface_adapter.match_lookup.MatchLookupController;
import interface_adapter.match_lookup.MatchLookupPresenter;
import interface_adapter.match_lookup.MatchLookupViewModel;
import interface_adapter.previous_search.PreviousSearchController;
import interface_adapter.previous_search.PreviousSearchPresenter;
import interface_adapter.previous_search.PreviousSearchViewModel;
import interface_adapter.user_lookup.UserLookupController;
import interface_adapter.user_lookup.UserLookupPresenter;
import interface_adapter.user_lookup.UserLookupViewModel;
import use_case.club_lookup.ClubLookupDataAccessInterface;
import use_case.club_lookup.ClubLookupInputBoundary;
import use_case.club_lookup.ClubLookupInteractor;
import use_case.club_lookup.ClubLookupOutputBoundary;
import use_case.match_lookup.MatchLookupDataAccessInterface;
import use_case.match_lookup.MatchLookupInputBoundary;
import use_case.match_lookup.MatchLookupInteractor;
import use_case.match_lookup.MatchLookupOutputBoundary;
import use_case.previous_search.PreviousSearchDataAccessInterface;
import use_case.previous_search.PreviousSearchInputBoundary;
import use_case.previous_search.PreviousSearchInteractor;
import use_case.previous_search.PreviousSearchOutputBoundary;
import use_case.user_lookup.UserLookupDataAccessInterface;
import use_case.leaderboard_lookup.LeaderboardLookupDataAccessInterface;
import use_case.leaderboard_lookup.LeaderboardLookupInputBoundary;
import use_case.leaderboard_lookup.LeaderboardLookupOutputBoundary;
import use_case.leaderboard_lookup.LeaderboardLookupInteractor;
import use_case.user_lookup.UserLookupOutputBoundary;
import view.SearchView;
import interface_adapter.search.SearchViewModel;
import interface_adapter.ViewManagerModel;
import use_case.user_lookup.UserLookupInteractor;
import use_case.user_lookup.UserLookupInputBoundary;

public final class SearchUseCaseFactory {
        private SearchUseCaseFactory() {
        }

        public static SearchView create(
                        SearchViewModel searchViewModel,
                        UserLookupViewModel userViewModel,
                        MatchLookupViewModel matchViewModel,
                        LeaderboardLookupViewModel leaderboardViewModel,
                        ClubLookupViewModel clubViewModel,
                        PreviousSearchViewModel previousSearchViewModel,
                        ViewManagerModel viewManagerModel,
                        UserLookupDataAccessInterface userLookupDataAccessObject,
                        MatchLookupDataAccessInterface matchLookupDataAccessObject,
                        LeaderboardLookupDataAccessInterface leaderboardLookupDataAccessObject,
                        ClubLookupDataAccessInterface clubLookupDataAccessObject,
                        PreviousSearchDataAccessInterface previousSearchDataAccessObject
                        ) {
                final UserLookupController userLookupController = createUserLookupUseCase(userViewModel,
                                viewManagerModel,
                                userLookupDataAccessObject);
                final MatchLookupController matchLookupController = createMatchLookupUseCase(matchViewModel,
                                viewManagerModel, matchLookupDataAccessObject);
                final LeaderboardLookupController leaderboardLookupController = createLeaderboardLookupUseCase(
                                leaderboardViewModel, viewManagerModel, leaderboardLookupDataAccessObject);
                final ClubLookupController clubLookupController = createClubLookupUseCase(clubViewModel,
                                viewManagerModel, clubLookupDataAccessObject);
                final PreviousSearchController previousSearchController = createPreviousSearchUseCase(
                        previousSearchViewModel, viewManagerModel, previousSearchDataAccessObject);
                return new SearchView(searchViewModel, previousSearchViewModel, userLookupController,
                                matchLookupController, leaderboardLookupController, clubLookupController, previousSearchController);

        }

        private static PreviousSearchController createPreviousSearchUseCase(PreviousSearchViewModel viewModel,
                        ViewManagerModel viewManagerModel, PreviousSearchDataAccessInterface dataAccessObject) {
                final PreviousSearchOutputBoundary outputBoundary = new PreviousSearchPresenter(viewModel, viewManagerModel);
                final PreviousSearchInputBoundary inputBoundary = new PreviousSearchInteractor(dataAccessObject, outputBoundary);
                return new PreviousSearchController(inputBoundary);
        }

        private static UserLookupController createUserLookupUseCase(UserLookupViewModel userLookupViewModel,
                        ViewManagerModel viewManagerModel, UserLookupDataAccessInterface userLookupDataAcessObject) {

                final UserLookupOutputBoundary userLookupOutputBoundary = new UserLookupPresenter(userLookupViewModel,
                                viewManagerModel);
                final UserLookupInputBoundary userLookupInteractor = new UserLookupInteractor(userLookupDataAcessObject,
                                userLookupOutputBoundary);
                return new UserLookupController(userLookupInteractor);

        }

        private static MatchLookupController createMatchLookupUseCase(MatchLookupViewModel matchLookupViewModel,
                        ViewManagerModel viewManagerModel, MatchLookupDataAccessInterface matchLookupDataAccessObject) {
                final MatchLookupOutputBoundary matchLookupOutputBoundary = new MatchLookupPresenter(
                                matchLookupViewModel, viewManagerModel);
                final MatchLookupInputBoundary matchLookupInteractor = new MatchLookupInteractor(
                                matchLookupDataAccessObject, matchLookupOutputBoundary);
                return new MatchLookupController(matchLookupInteractor);
        }

        private static LeaderboardLookupController createLeaderboardLookupUseCase(
                        LeaderboardLookupViewModel leaderboardLookupViewModel, ViewManagerModel viewManagerModel,
                        LeaderboardLookupDataAccessInterface leaderboardLookupDataAccessObject) {

                final LeaderboardLookupOutputBoundary leaderboardLookupOutputBoundary = new LeaderboardLookupPresenter(
                                leaderboardLookupViewModel, viewManagerModel);
                final LeaderboardLookupInputBoundary leaderboardLookupInteractor = new LeaderboardLookupInteractor(
                                leaderboardLookupDataAccessObject, leaderboardLookupOutputBoundary);

                return new LeaderboardLookupController(leaderboardLookupInteractor);

        }

        private static ClubLookupController createClubLookupUseCase(ClubLookupViewModel clubLookupViewModel,
                        ViewManagerModel viewManagerModel, ClubLookupDataAccessInterface clubLookupDataAccessObject) {
                final ClubLookupOutputBoundary clubLookupOutputBoundary = new ClubLookupPresenter(clubLookupViewModel,
                                viewManagerModel);
                final ClubLookupInputBoundary clubLookupInteractor = new ClubLookupInteractor(
                        clubLookupDataAccessObject, clubLookupOutputBoundary);
                return new ClubLookupController(clubLookupInteractor);
        }
}
