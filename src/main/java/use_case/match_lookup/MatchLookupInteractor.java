package use_case.match_lookup;

import entity.Match;

public class MatchLookupInteractor implements MatchLookupInputBoundary{

    private final MatchLookupDataAccessInterface matchLookupDataAccessInterface;
    private final MatchLookupOutputBoundary matchLookupOutputBoundary;

    public MatchLookupInteractor(MatchLookupDataAccessInterface matchLookupDataAccessInterface, MatchLookupOutputBoundary matchLookupOutputBoundary) {
        this.matchLookupDataAccessInterface = matchLookupDataAccessInterface;
        this.matchLookupOutputBoundary = matchLookupOutputBoundary;
    }

    public void execute(MatchLookupInputData matchLookupInputData) {
        try {
            Match match = matchLookupDataAccessInterface.getMatch(matchLookupInputData.getTag());
            final MatchLookupOutputData matchLookupOutputData = new MatchLookupOutputData(match.getTime(), match.getMode(), match.getMap(), match.isVictory(), match.getTrophyChange(), match.getStarPlayer(), match.getTrophyCount());
            matchLookupOutputBoundary.prepareSuccessView(matchLookupOutputData);

        } catch (RuntimeException e) {
            matchLookupOutputBoundary.prepareFailView(e.getMessage());
        }
    }


}
