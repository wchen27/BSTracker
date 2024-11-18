package use_case.brawler_lookup;

public class BrawlerLookupState {

    private String query;
    private String brawlerName;
    private boolean useCaseFailed;
    private String searchError;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getBrawlerName() {
        return brawlerName;
    }

    public void setBrawlerName(String brawlerName) {
        this.brawlerName = brawlerName;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public void setUseCaseFailed(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public String getSearchError() {
        return searchError;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }
}
