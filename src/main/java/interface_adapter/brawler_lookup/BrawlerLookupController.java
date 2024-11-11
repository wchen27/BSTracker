package interface_adapter.brawler_lookup;

import use_case.brawler_lookup.BrawlerLookupInputBoundary;
import use_case.brawler_lookup.BrawlerLookupInputData;

public class BrawlerLookupController {

    private final BrawlerLookupInputBoundary brawlerUseCaseInteractor;

    public BrawlerLookupController(BrawlerLookupInputBoundary brawlerLookupInputBoundary) {
        this.brawlerUseCaseInteractor = brawlerLookupInputBoundary;
    }

    public void execute(String query) {
        final BrawlerLookupInputData inputData = new BrawlerLookupInputData(query);

        brawlerUseCaseInteractor.execute(inputData);
    }

}
