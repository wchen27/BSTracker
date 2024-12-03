package use_case.fetch_club;

import data_access.APIDataAccessObject;
import entity.ClubFactory;
import entity.MatchFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.club_lookup.ClubLookupController;
import interface_adapter.club_lookup.ClubLookupPresenter;
import interface_adapter.club_lookup.ClubLookupViewModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Test;
import use_case.club_lookup.ClubLookupDataAccessInterface;
import use_case.club_lookup.ClubLookupInputBoundary;
import use_case.club_lookup.ClubLookupInteractor;
import use_case.club_lookup.ClubLookupOutputBoundary;



public class ClubLookupInteractorTest {

    @Test
    public void ClubLookupControllerSuccessTest() {
        ClubLookupOutputBoundary presenter = new ClubLookupPresenter(new ClubLookupViewModel(), new ViewManagerModel());
        ClubLookupDataAccessInterface dao = new APIDataAccessObject(new UserFactory(), new MatchFactory(),
                new ClubFactory(), Dotenv.load());
        ClubLookupInputBoundary interactor = new ClubLookupInteractor(dao, presenter);
        ClubLookupController controller = new ClubLookupController(interactor);

        controller.execute("#2VOQL2LUG");
    }

    @Test
    public void ClubLookupControllerFailTest() {
        ClubLookupOutputBoundary presenter = new ClubLookupPresenter(new ClubLookupViewModel(), new ViewManagerModel());
        ClubLookupDataAccessInterface dao = new APIDataAccessObject(new UserFactory(), new MatchFactory(),
                new ClubFactory(), Dotenv.load());
        ClubLookupInputBoundary interactor = new ClubLookupInteractor(dao, presenter);
        ClubLookupController controller = new ClubLookupController(interactor);

        controller.execute("#2VOQL2LU");
    }

}
