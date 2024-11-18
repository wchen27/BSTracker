package interface_adapter.brawler_lookup;

import use_case.brawler_lookup.*;

public class BrawlerLookupController {

    private final BrawlerLookupInputBoundary inputBoundary;
    private final BrawlerLookupViewModel viewModel;
    private final BrawlerLookupOutputBoundary outputBoundary;

    public BrawlerLookupController(BrawlerLookupInputBoundary inputBoundary, BrawlerLookupViewModel viewModel,
                                   BrawlerLookupOutputBoundary outputBoundary) {
        this.inputBoundary = inputBoundary;
        this.viewModel = viewModel;
        this.outputBoundary = outputBoundary;
    }

    public void searchBrawler(String query) {
        BrawlerLookupInputData inputData = new BrawlerLookupInputData(query);
        inputBoundary.execute(inputData);
    }

    public void updateView(BrawlerLookupOutputData outputData) {
        use_case.brawler_lookup.BrawlerLookupState state = viewModel.getState();
        state.setBrawlerName(outputData.getBrawlerName());
        state.setUseCaseFailed(outputData.isUseCaseFailed());
        viewModel.setState(state);
    }
}
