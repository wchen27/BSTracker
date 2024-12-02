package use_case.previous_search;

/*
 * The output data for the previous search for the user to see
 */
public class PreviousSearchOutputData {
    
    private String[] previousSearches;

    public PreviousSearchOutputData(String[] previousSearches) {
        this.previousSearches = previousSearches;
    }

    public String[] getPreviousSearches() {
        return this.previousSearches;
    }
}
