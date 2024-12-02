package interface_adapter.previous_search;

import use_case.previous_search.PreviousSearchInputBoundary;
import use_case.previous_search.PreviousSearchInteractor;

public class PreviousSearchController {
    
    private PreviousSearchInputBoundary interactor;

    public PreviousSearchController(PreviousSearchInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        interactor.execute();
    }

}
