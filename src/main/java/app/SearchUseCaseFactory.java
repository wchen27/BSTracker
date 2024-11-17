package app;

import interface_adapter.leaderboard_lookup.LeaderboardLookupController;
import interface_adapter.leaderboard_lookup.LeaderboardLookupPresenter;
import interface_adapter.leaderboard_lookup.LeaderboardLookupViewModel;
import interface_adapter.match_lookup.MatchLookupController;
import interface_adapter.match_lookup.MatchLookupPresenter;
import interface_adapter.match_lookup.MatchLookupViewModel;
import interface_adapter.user_lookup.UserLookupController;
import interface_adapter.user_lookup.UserLookupPresenter;
import interface_adapter.user_lookup.UserLookupViewModel;
import use_case.brawler_lookup.BrawlerLookupDataAccessInterface;
import use_case.brawler_lookup.BrawlerLookupInputBoundary;
import use_case.brawler_lookup.BrawlerLookupOutputBoundary;
import use_case.match_lookup.MatchLookupDataAccessInterface;
import use_case.match_lookup.MatchLookupInputBoundary;
import use_case.match_lookup.MatchLookupInteractor;
import use_case.match_lookup.MatchLookupOutputBoundary;
import use_case.leaderboard_lookup.LeaderboardLookupDataAccessInterface;
import use_case.leaderboard_lookup.LeaderboardLookupInputBoundary;
import use_case.leaderboard_lookup.LeaderboardLookupOutputBoundary;
import use_case.leaderboard_lookup.LeaderboardLookupInteractor;
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
                        SearchViewModel searchViewModel,
                        UserLookupViewModel userViewModel,
                        MatchLookupViewModel matchViewModel,
                        LeaderboardLookupViewModel leaderboardViewModel,
                        ViewManagerModel viewManagerModel,
                        BrawlerLookupDataAccessInterface brawlerDataAccessObject,
                        UserLookupDataAccessInterface userLookupDataAccessObject,
                        MatchLookupDataAccessInterface matchLookupDataAccessObject,
                        LeaderboardLookupDataAccessInterface leaderboardLookupDataAccessObject) {
                final BrawlerLookupController brawlerLookupController = createBrawlerLookupUseCase(searchViewModel,
                                brawlerDataAccessObject);
                final UserLookupController userLookupController = createUserLookupUseCase(userViewModel,
                                viewManagerModel,
                                userLookupDataAccessObject);
                final MatchLookupController matchLookupController = createMatchLookupUseCase(matchViewModel,
                                viewManagerModel, matchLookupDataAccessObject);
                final LeaderboardLookupController leaderboardLookupController = createLeaderboardLookupUseCase(
                                leaderboardViewModel, viewManagerModel, leaderboardLookupDataAccessObject);
                return new SearchView(searchViewModel, brawlerLookupController, userLookupController,
                                matchLookupController, leaderboardLookupController);

        }

        private static BrawlerLookupController createBrawlerLookupUseCase(SearchViewModel searchViewModel,
                        BrawlerLookupDataAccessInterface brawlerDataAccessObject) {
                final BrawlerLookupOutputBoundary brawlerLookupOutputBoundary = new BrawlerLookupPresenter();
                final BrawlerLookupInputBoundary brawlerLookupInteractor = new BrawlerLookupInteractor(
                                brawlerDataAccessObject,
                                brawlerLookupOutputBoundary);
                return new BrawlerLookupController(brawlerLookupInteractor);
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
}
