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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.club_lookup.ClubLookupDataAccessInterface;
import use_case.club_lookup.ClubLookupInputBoundary;
import use_case.club_lookup.ClubLookupInteractor;
import use_case.club_lookup.ClubLookupOutputBoundary;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClubLookupInteractorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void ClubLookupControllerSuccessTest() {
        ClubLookupOutputBoundary presenter = new ClubLookupPresenter(new ClubLookupViewModel(), new ViewManagerModel());
        ClubLookupDataAccessInterface dao = new APIDataAccessObject(new UserFactory(), new MatchFactory(),
                new ClubFactory(), Dotenv.load());
        ClubLookupInputBoundary interactor = new ClubLookupInteractor(dao, presenter);
        ClubLookupController controller = new ClubLookupController(interactor);

        controller.execute("#2VOQL2LUG");
        // check club tag printed to console
        assertTrue(outContent.toString().contains("Club tag exists!"));
        // club exists
    }

    @Test
    public void ClubLookupControllerFailTest() {
        ClubLookupOutputBoundary presenter = new ClubLookupPresenter(new ClubLookupViewModel(), new ViewManagerModel());
        ClubLookupDataAccessInterface dao = new APIDataAccessObject(new UserFactory(), new MatchFactory(),
                new ClubFactory(), Dotenv.load());
        ClubLookupInputBoundary interactor = new ClubLookupInteractor(dao, presenter);
        ClubLookupController controller = new ClubLookupController(interactor);

        controller.execute("#2VOQL2LU");
        // check club tag does not exist
        assertTrue(outContent.toString().contains("Club tag does not exist!"));
        // club does not exist
    }
}