package interface_adapter.previous_search;

import interface_adapter.ViewModel;

/*
 * The view model for the previous search
 */
public class PreviousSearchViewModel extends ViewModel<PreviousSearchState>{
    

    public PreviousSearchViewModel() {
        super("previous search");
        this.setState(new PreviousSearchState());
    }
}
