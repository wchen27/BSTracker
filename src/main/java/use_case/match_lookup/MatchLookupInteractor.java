package use_case.match_lookup;

import entity.Match;

import java.util.List;

public class MatchLookupInteractor implements MatchLookupInputBoundary{

    private final MatchLookupDataAccessInterface matchLookupDataAccessInterface;
    private final MatchLookupOutputBoundary matchLookupOutputBoundary;

    public MatchLookupInteractor(MatchLookupDataAccessInterface matchLookupDataAccessInterface, MatchLookupOutputBoundary matchLookupOutputBoundary) {
        this.matchLookupDataAccessInterface = matchLookupDataAccessInterface;
        this.matchLookupOutputBoundary = matchLookupOutputBoundary;
    }

    public void execute(MatchLookupInputData matchLookupInputData) {
        try {
            List<Match> matches = matchLookupDataAccessInterface.getMatches(matchLookupInputData.getTag());
            final MatchLookupOutputData matchLookupOutputData = new MatchLookupOutputData(matches);
            matchLookupOutputBoundary.prepareSuccessView(matchLookupOutputData);

        } catch (RuntimeException e) {
            matchLookupOutputBoundary.prepareFailView(e.getMessage());
        }
    }


}
