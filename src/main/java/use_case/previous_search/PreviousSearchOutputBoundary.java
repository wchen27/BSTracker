package use_case.previous_search;

public interface PreviousSearchOutputBoundary {
    
    void prepareSuccessView(PreviousSearchOutputData data);

    void prepareFailView(String errorMsg);
}
