package use_case.club_lookup;

public interface ClubLookupOutputBoundary {
    void prepareSuccessView(ClubLookupOutputData outputData);

    void prepareFailView(String errorMessage);
}
