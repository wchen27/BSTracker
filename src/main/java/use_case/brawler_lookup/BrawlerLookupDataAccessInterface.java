package use_case.brawler_lookup;

import entity.Brawler;

public interface BrawlerLookupDataAccessInterface {
    Brawler getBrawler(int brawlerId);
}
