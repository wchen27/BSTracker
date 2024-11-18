package entity;

import java.util.List;

public class Brawler {

    private int id;
    private JsonLocalizedName name;
    private List<Accessory> gadgets;
    private List<StarPower> starPowers;

    public Brawler(int id, JsonLocalizedName name, List<Accessory> gadgets, List<StarPower> starPowers) {
        this.id = id;
        this.name = name;
        this.gadgets = gadgets;
        this.starPowers = starPowers;
    }

    // Getters
    public int getId() {
        return id;
    }

    public JsonLocalizedName getName() {
        return name;
    }

    public List<Accessory> getGadgets() {
        return gadgets;
    }

    public List<StarPower> getStarPowers() {
        return starPowers;
    }
}
