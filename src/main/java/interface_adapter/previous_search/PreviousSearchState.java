package interface_adapter.previous_search;

import java.util.Arrays;

/*
 * The State for the previous search's view model
 * Stores the previous searches
 */
public class PreviousSearchState {
    private String[] previousSearches;

    public void setPreviousSearches(String[] previousSearches) {
        this.previousSearches = previousSearches;
    }

    public String[] getPreviousSearches() {
        return this.previousSearches;
    }
}
