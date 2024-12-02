package use_case.previous_search;

/*
 * The output boundary for previous search to allow the interactor to communicate with the view
 */
public interface PreviousSearchOutputBoundary {
    
    void prepareSuccessView(PreviousSearchOutputData data);

    void prepareFailView(String errorMsg);
}
