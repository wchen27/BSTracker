package use_case.brawler_lookup;

public class BrawlerLookupInteractor implements BrawlerLookupInputBoundary {

    private final BrawlerLookupDataAccessInterface brawlerAccessObject;
    private final BrawlerLookupOutputBoundary brawlerLookupPresenter;

    public BrawlerLookupInteractor(BrawlerLookupDataAccessInterface brawlerAccessObject,
            BrawlerLookupOutputBoundary brawlerLookupPresenter) {
        this.brawlerAccessObject = brawlerAccessObject;
        this.brawlerLookupPresenter = brawlerLookupPresenter;
    }

    @Override
    public void execute(BrawlerLookupInputData inputData) {

    }

}
