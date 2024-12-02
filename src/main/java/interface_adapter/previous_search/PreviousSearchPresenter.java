package interface_adapter.previous_search;

import interface_adapter.ViewManagerModel;
import use_case.previous_search.PreviousSearchInputBoundary;
import use_case.previous_search.PreviousSearchOutputBoundary;
import use_case.previous_search.PreviousSearchOutputData;

public class PreviousSearchPresenter implements PreviousSearchOutputBoundary {

    private PreviousSearchViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    public PreviousSearchPresenter(PreviousSearchViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PreviousSearchOutputData data) {
        final PreviousSearchState state = viewModel.getState();
        state.setPreviousSearches(data.getPreviousSearches());

        viewModel.firePropertyChanged();
        viewManagerModel.setState("search");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMsg) {
        final PreviousSearchState state = viewModel.getState();
        System.out.println(errorMsg);
        state.setPreviousSearches(new String[]{"Couldn't get previous searches."});

        viewModel.firePropertyChanged();
        viewManagerModel.setState("search");
        viewManagerModel.firePropertyChanged();
    }
    
}
