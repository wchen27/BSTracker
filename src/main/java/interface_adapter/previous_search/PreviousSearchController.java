package interface_adapter.previous_search;

import use_case.previous_search.PreviousSearchInputBoundary;
import use_case.previous_search.PreviousSearchInteractor;

/*
 * The controller for the previous search
 */
public class PreviousSearchController {
    
    private PreviousSearchInputBoundary interactor;

    public PreviousSearchController(PreviousSearchInputBoundary interactor) {
        this.interactor = interactor;
    }

    /*
     * Executes the interactor to get the previous searches
     */
    public void execute() {
        interactor.execute();
    }

}
