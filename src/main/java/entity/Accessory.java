package entity;

public class Accessory {

    private int id;
    private JsonLocalizedName name;

    public Accessory(int id, JsonLocalizedName name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public int getId() {
        return id;
    }

    public JsonLocalizedName getName() {
        return name;
    }
}
