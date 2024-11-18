package interface_adapter.club_lookup;

import use_case.club_lookup.ClubLookupInputBoundary;
import use_case.club_lookup.ClubLookupInputData;

public class ClubLookupController {

    private final ClubLookupInputBoundary interactor;

    public ClubLookupController(ClubLookupInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String tag) {
        final ClubLookupInputData inputData = new ClubLookupInputData(tag);

        interactor.execute(inputData);
    }

}
