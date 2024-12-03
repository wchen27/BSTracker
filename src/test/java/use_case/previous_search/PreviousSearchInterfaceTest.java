package use_case.previous_search;

import org.junit.Test;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.ClubFactory;
import entity.MatchFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.previous_search.PreviousSearchController;
import interface_adapter.previous_search.PreviousSearchPresenter;
import interface_adapter.previous_search.PreviousSearchViewModel;
import io.github.cdimascio.dotenv.Dotenv;

public class PreviousSearchInterfaceTest {
    
    @Test
    public void previousSearchControllerTest() {
        Dotenv env = Dotenv.load();
        PreviousSearchDataAccessInterface dao = new FileDataAccessObject("testSearches.txt");
        PreviousSearchViewModel viewModel = new PreviousSearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PreviousSearchOutputBoundary presenter = new PreviousSearchPresenter(viewModel, viewManagerModel);
        PreviousSearchInputBoundary interactor = new PreviousSearchInteractor(dao, presenter);
        PreviousSearchController controller = new PreviousSearchController(interactor);

        controller.execute("Club: Test");
    }

    @Test
    public void previousSearchControllerDefaultTest() {
        Dotenv env = Dotenv.load();
        PreviousSearchDataAccessInterface dao = new FileDataAccessObject("testSearches.txt");
        PreviousSearchViewModel viewModel = new PreviousSearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PreviousSearchOutputBoundary presenter = new PreviousSearchPresenter(viewModel, viewManagerModel);
        PreviousSearchInputBoundary interactor = new PreviousSearchInteractor(dao, presenter);
        PreviousSearchController controller = new PreviousSearchController(interactor);

        controller.execute();
    }

    @Test
    public void previousSearchControllerFailTest() {
        Dotenv env = Dotenv.configure().filename("test.env").load();
        PreviousSearchDataAccessInterface dao = new FileDataAccessObject("testSearches.txt");
        PreviousSearchViewModel viewModel = new PreviousSearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PreviousSearchOutputBoundary presenter = new PreviousSearchPresenter(viewModel, viewManagerModel);
        PreviousSearchInputBoundary interactor = new PreviousSearchInteractor(dao, presenter);
        PreviousSearchController controller = new PreviousSearchController(interactor);

        controller.execute();
        viewModel.getState().getPreviousSearches();
        presenter.prepareFailView("Test");
    }
}
