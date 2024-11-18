package entity;

public class StarPower {

    private int id;
    private JsonLocalizedName name;

    public StarPower(int id, JsonLocalizedName name) {
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
