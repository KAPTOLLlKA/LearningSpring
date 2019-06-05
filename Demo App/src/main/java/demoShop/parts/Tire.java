package demoShop.parts;

public class Tire {
    private int id;
    private String manufacturer;
    private Type type;

    public Tire(int id, String manufacturer, Type type) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    public static enum Type {
        WINTER, SUMMER, ALL_SEASON
    }
}
