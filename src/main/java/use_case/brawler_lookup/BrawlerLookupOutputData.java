package use_case.brawler_lookup;

public class BrawlerLookupOutputData {

    private final String brawlerName;
    private final boolean useCaseFailed;

    public BrawlerLookupOutputData(String brawlerName, boolean useCaseFailed) {
        this.brawlerName = brawlerName;
        this.useCaseFailed = useCaseFailed;
    }

    public String getBrawlerName() {
        return brawlerName;
    }

}
