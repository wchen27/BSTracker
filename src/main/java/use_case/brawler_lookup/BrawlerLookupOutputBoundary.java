package use_case.brawler_lookup;

public interface BrawlerLookupOutputBoundary {
    void prepareSuccessView(BrawlerLookupOutputData outputData);
    void prepareFailView(String errorMessage);
}
