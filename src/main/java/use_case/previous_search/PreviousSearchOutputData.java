package use_case.previous_search;

public class PreviousSearchOutputData {
    
    private String[] previousSearches;

    public PreviousSearchOutputData(String[] previousSearches) {
        this.previousSearches = previousSearches;
    }

    public String[] getPreviousSearches() {
        return this.previousSearches;
    }
}
