package interface_adapter.brawler_lookup;

import interface_adapter.ViewModel;
import use_case.brawler_lookup.BrawlerLookupOutputData;
import use_case.brawler_lookup.BrawlerLookupState;

public class BrawlerLookupViewModel extends ViewModel<BrawlerLookupState> {

    public BrawlerLookupViewModel() {
        super("brawler lookup");
        this.setState(new BrawlerLookupState());
    }

    public void updateBrawlerInfo(BrawlerLookupOutputData outputData) {
        BrawlerLookupState state = this.getState();

        // Update the state with new information
        state.setBrawlerName(outputData.getBrawlerName());
        state.setUseCaseFailed(outputData.isUseCaseFailed());

        this.setState(state);
        this.firePropertyChanged();
    }
}
