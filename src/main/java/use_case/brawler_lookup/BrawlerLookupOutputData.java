package use_case.brawler_lookup;

import entity.Accessory;
import entity.StarPower;

import java.util.List;

public class BrawlerLookupOutputData {

    private final String brawlerName;
    private final int brawlerId;
    private final List<Accessory> gadgets;
    private final List<StarPower> starPowers;
    private final boolean useCaseFailed;
    private final String errorMessage;

    // Constructor for success
    public BrawlerLookupOutputData(String brawlerName, int brawlerId, List<Accessory> gadgets, List<StarPower> starPowers) {
        this.brawlerName = brawlerName;
        this.brawlerId = brawlerId;
        this.gadgets = gadgets;
        this.starPowers = starPowers;
        this.useCaseFailed = false;
        this.errorMessage = null;
    }

    // Constructor for failure
    public BrawlerLookupOutputData(String errorMessage) {
        this.brawlerName = null;
        this.brawlerId = -1;
        this.gadgets = null;
        this.starPowers = null;
        this.useCaseFailed = true;
        this.errorMessage = errorMessage;
    }

    public String getBrawlerName() {
        return brawlerName;
    }

    public int getBrawlerId() {
        return brawlerId;
    }

    public List<Accessory> getGadgets() {
        return gadgets;
    }

    public List<StarPower> getStarPowers() {
        return starPowers;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
