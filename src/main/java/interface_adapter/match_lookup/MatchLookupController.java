package interface_adapter.match_lookup;

import use_case.match_lookup.MatchLookupInputBoundary;
import use_case.match_lookup.MatchLookupInputData;

public class MatchLookupController {

    private final MatchLookupInputBoundary interactor;

    public MatchLookupController(MatchLookupInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String tag) {
        final MatchLookupInputData inputData = new MatchLookupInputData(tag);

        interactor.execute(inputData);
    }
    
}
