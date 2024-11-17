package interface_adapter.search;

public class SearchState {
    private String query;
    private String searchError;
    private int leaderboardSize;

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

    public void setLeaderboardSize(int size) {
        this.leaderboardSize = size;
    }

    public int getLeaderboardSize() {
        return leaderboardSize;
    }
}
