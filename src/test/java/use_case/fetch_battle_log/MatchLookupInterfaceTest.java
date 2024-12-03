package use_case.fetch_battle_log;

import data_access.APIDataAccessObject;
import entity.ClubFactory;
import entity.Match;
import entity.MatchFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.match_lookup.MatchLookupController;
import interface_adapter.match_lookup.MatchLookupPresenter;
import interface_adapter.match_lookup.MatchLookupState;
import interface_adapter.match_lookup.MatchLookupViewModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Test;
import use_case.match_lookup.MatchLookupDataAccessInterface;
import use_case.match_lookup.MatchLookupInputBoundary;
import use_case.match_lookup.MatchLookupInteractor;
import use_case.match_lookup.MatchLookupOutputBoundary;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class MatchLookupInterfaceTest {
    @Test
    // test the MatchLookupController
    public void MatchLookupControllerTest() {
        Dotenv env = Dotenv.load();
        MatchLookupOutputBoundary presenter = new MatchLookupPresenter(new MatchLookupViewModel(), new ViewManagerModel());
        MatchLookupDataAccessInterface dao = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), env);
        MatchLookupInputBoundary interactor = new MatchLookupInteractor(dao, presenter);

        MatchLookupController controller = new MatchLookupController(interactor);
        controller.execute("#99uv0990");
    }

    @Test
    // test MatchLookupController with empty history
    public void MatchLookupControllerEmtpyTest() {
        Dotenv env = Dotenv.load();
        MatchLookupOutputBoundary presenter = new MatchLookupPresenter(new MatchLookupViewModel(), new ViewManagerModel());
        MatchLookupDataAccessInterface dao = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), env);
        MatchLookupInputBoundary interactor = new MatchLookupInteractor(dao, presenter);

        MatchLookupController controller = new MatchLookupController(interactor);
        controller.execute("fail");
    }

    @Test
    // test the MatchLookupState
    public void MatchLookupStateTest() {
        Dotenv env = Dotenv.load();
        MatchLookupViewModel viewModel = new MatchLookupViewModel();
        MatchLookupOutputBoundary presenter = new MatchLookupPresenter(viewModel, new ViewManagerModel());
        MatchLookupDataAccessInterface dao = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), env);
        MatchLookupInputBoundary interactor = new MatchLookupInteractor(dao, presenter);

        MatchLookupController controller = new MatchLookupController(interactor);
        controller.execute("#99uv0990");

        MatchLookupState state = viewModel.getState();
        assertNotNull(state.getMatches());
        state.getWinrate();
        state.getTrophyChange();
    }

    @Test
    // test MatchLookupState with empty MatchList
    public void MatchLookupStateEmtpyTest() {
        Dotenv env = Dotenv.load();
        MatchLookupViewModel viewModel = new MatchLookupViewModel();
        MatchLookupOutputBoundary presenter = new MatchLookupPresenter(viewModel, new ViewManagerModel());
        MatchLookupDataAccessInterface dao = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), env);
        MatchLookupInputBoundary interactor = new MatchLookupInteractor(dao, presenter);

        MatchLookupController controller = new MatchLookupController(interactor);
        controller.execute("#99uv0990");

        MatchLookupState state = viewModel.getState();
        state.setMatches(new ArrayList<>());
        state.getWinrate();
    }
}
