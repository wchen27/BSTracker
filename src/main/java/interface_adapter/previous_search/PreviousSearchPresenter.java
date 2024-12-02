package interface_adapter.previous_search;

import interface_adapter.ViewManagerModel;
import use_case.previous_search.PreviousSearchInputBoundary;
import use_case.previous_search.PreviousSearchOutputBoundary;
import use_case.previous_search.PreviousSearchOutputData;

/*
 * The presenter for the previous search
 */
public class PreviousSearchPresenter implements PreviousSearchOutputBoundary {

    private PreviousSearchViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    public PreviousSearchPresenter(PreviousSearchViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view if the previous searches are able to be gotten successfully
     * 
     * @param data The data that will be shown to the user
     */
    @Override
    public void prepareSuccessView(PreviousSearchOutputData data) {
        final PreviousSearchState state = viewModel.getState();
        state.setPreviousSearches(data.getPreviousSearches());

        viewModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view if the previous searches aren't able to be gotten successfully
     * @param errorMsg The error message to be shown to the user
     */
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
