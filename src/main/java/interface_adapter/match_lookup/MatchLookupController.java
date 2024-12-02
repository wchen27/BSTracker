package interface_adapter.match_lookup;

import use_case.match_lookup.MatchLookupInputBoundary;
import use_case.match_lookup.MatchLookupInputData;

/*
 * The controller for MatchLookup.
 */
public class MatchLookupController {

    private final MatchLookupInputBoundary interactor;

    // Constructor that sets up the interactor.
    public MatchLookupController(MatchLookupInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Get the input data for a specific tag.
     * @param tag the tag of the user to look up the match history for.
     */
    public void execute(String tag) {
        final MatchLookupInputData inputData = new MatchLookupInputData(tag);

        interactor.execute(inputData);
    }
    
}
