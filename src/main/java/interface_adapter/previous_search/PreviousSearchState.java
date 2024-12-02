package interface_adapter.previous_search;

import java.util.Arrays;

public class PreviousSearchState {
    private String[] previousSearches;

    public void setPreviousSearches(String[] previousSearches) {
        this.previousSearches = previousSearches;
    }

    public String[] getPreviousSearches() {
        return this.previousSearches;
    }
}
