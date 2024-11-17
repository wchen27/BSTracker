package interface_adapter.match_lookup;

import interface_adapter.ViewManagerModel;
import use_case.match_lookup.MatchLookupOutputBoundary;
import use_case.match_lookup.MatchLookupOutputData;


public class MatchLookupPresenter implements MatchLookupOutputBoundary {

    private final MatchLookupViewModel matchViewModel;
    private final ViewManagerModel viewManagerModel;

    public MatchLookupPresenter(MatchLookupViewModel matchViewModel, ViewManagerModel viewModelManager) {
        this.matchViewModel = matchViewModel;
        this.viewManagerModel = viewModelManager;
    }

    public void prepareSuccessView(MatchLookupOutputData outputData) {
        final MatchLookupState state = matchViewModel.getState();
        state.setMatches(outputData.getMatches());

        matchViewModel.firePropertyChanged();
        this.viewManagerModel.setState(matchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String errorMessage) {
        throw new UnsupportedOperationException(errorMessage);
    }
    
}
