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

    /**
     * Creates a success view or a fail view based on a match history input data.
     * @param matchLookupInputData the list of matches from a certain player.
     */
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
