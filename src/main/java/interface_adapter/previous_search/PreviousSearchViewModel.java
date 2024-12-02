package interface_adapter.previous_search;

import interface_adapter.ViewModel;

public class PreviousSearchViewModel extends ViewModel<PreviousSearchState>{
    

    public PreviousSearchViewModel() {
        super("previous search");
        this.setState(new PreviousSearchState());
    }
}
