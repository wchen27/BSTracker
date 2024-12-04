package use_case.fetch_user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import data_access.APIDataAccessObject;
import entity.ClubFactory;
import entity.MatchFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.user_lookup.UserLookupController;
import interface_adapter.user_lookup.UserLookupPresenter;
import interface_adapter.user_lookup.UserLookupState;
import interface_adapter.user_lookup.UserLookupViewModel;
import io.github.cdimascio.dotenv.Dotenv;
import use_case.user_lookup.UserLookupDataAccessInterface;
import use_case.user_lookup.UserLookupInputBoundary;
import use_case.user_lookup.UserLookupInteractor;
import use_case.user_lookup.UserLookupOutputBoundary;

public class UserLookupInterfaceTest {
    
    @Test
    public void GetUserControllerSuccessTest() {
        UserLookupOutputBoundary presenter = new UserLookupPresenter(new UserLookupViewModel(), new ViewManagerModel());
        UserLookupDataAccessInterface dao = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), Dotenv.load());
        UserLookupInputBoundary interactor = new UserLookupInteractor(dao, presenter);
        UserLookupController controller = new UserLookupController(interactor);

        controller.execute("99uv0990");
    }

    @Test
    public void GetUserControllerFailTest() {
        UserLookupOutputBoundary presenter = new UserLookupPresenter(new UserLookupViewModel(), new ViewManagerModel());
        UserLookupDataAccessInterface dao = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), Dotenv.load());
        UserLookupInputBoundary interactor = new UserLookupInteractor(dao, presenter);
        UserLookupController controller = new UserLookupController(interactor);

        controller.execute("99uv099");
    }

    @Test
    public void GetUserStateTest() {
        UserLookupViewModel viewModel = new UserLookupViewModel();
        UserLookupOutputBoundary presenter = new UserLookupPresenter(viewModel, new ViewManagerModel());
        UserLookupDataAccessInterface dao = new APIDataAccessObject(new UserFactory(), new MatchFactory(), new ClubFactory(), Dotenv.load());
        UserLookupInputBoundary interactor = new UserLookupInteractor(dao, presenter);
        UserLookupController controller = new UserLookupController(interactor);

        controller.execute("G2VCCRRUP");

        UserLookupState state = viewModel.getState();
        state.getDuoVictories();
        state.getHighestTrophies();
        state.getMatches();
        state.getPerformance();
        state.getSoloVictories();
        state.getTag();
        state.getTrioVictories();
        state.getTrophies();
        assertEquals(state.getUsername(),"Thigamore");
    }
}
