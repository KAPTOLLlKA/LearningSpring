package demoShop.parts;

import java.sql.Date;

public class Tire {
    private Long id;
    private String manufacturer;
    private Type type;
    private Date soldAt;

    public Tire(Long id, String manufacturer, Type type) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    public static enum Type {
        WINTER, SUMMER, ALL_SEASON
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(Date soldAt) {
        this.soldAt = soldAt;
    }
}
