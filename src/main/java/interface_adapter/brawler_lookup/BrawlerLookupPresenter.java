package interface_adapter.brawler_lookup;

import use_case.brawler_lookup.BrawlerLookupOutputBoundary;
import use_case.brawler_lookup.BrawlerLookupOutputData;
import interface_adapter.ViewManagerModel;
import use_case.brawler_lookup.BrawlerLookupState;

public class BrawlerLookupPresenter implements BrawlerLookupOutputBoundary {

    private final BrawlerLookupViewModel brawlerLookupViewModel;
    private final ViewManagerModel viewManagerModel;

    public BrawlerLookupPresenter(BrawlerLookupViewModel brawlerLookupViewModel, ViewManagerModel viewManagerModel) {
        this.brawlerLookupViewModel = brawlerLookupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(BrawlerLookupOutputData outputData) {
        final BrawlerLookupState state = brawlerLookupViewModel.getState();

        brawlerLookupViewModel.setBrawlerName(outputData.getBrawlerName());
        brawlerLookupViewModel.setGadgets(outputData.getGadgets());
        brawlerLookupViewModel.setStarPowers(outputData.getStarPowers());

        brawlerLookupViewModel.setUseCaseFailed(false);

        brawlerLookupViewModel.firePropertyChanged();

        viewManagerModel.setState(brawlerLookupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // In case of failure, update the ViewModel to indicate failure
        brawlerLookupViewModel.setUseCaseFailed(true);

        // Mark the view as failed and notify the view
        brawlerLookupViewModel.firePropertyChanged();

        // Optionally, you can throw an exception or log the error if necessary
        throw new UnsupportedOperationException("Failed to fetch brawler data: " + errorMessage);
    }
}
