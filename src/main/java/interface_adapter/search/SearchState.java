package interface_adapter.search;

public class SearchState {
    private String query;
    private String searchError;

    public void setQuery(String q) {
        this.query = q;
    }

    public String getQuery() {
        return query;
    }

    public void setSearchError(String error) {
        this.searchError = error;
    }

    public String getSearchError() {
        return searchError;
    }
}
