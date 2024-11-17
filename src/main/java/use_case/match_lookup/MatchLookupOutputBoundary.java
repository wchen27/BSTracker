package use_case.match_lookup;

public interface MatchLookupOutputBoundary {
    void prepareSuccessView(MatchLookupOutputData outputData);

    void prepareFailView(String errorMessage);
}
