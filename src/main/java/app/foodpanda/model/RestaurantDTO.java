package app.foodpanda.model;

import java.util.List;

public class RestaurantDTO {

    private String name;

    private String location;

    private List<String> deliveryZones;

    private String adminName;

    public RestaurantDTO(String name, String location, List<String> deliveryZones, String adminName) {
        this.name = name;
        this.location = location;
        this.deliveryZones = deliveryZones;
        this.adminName = adminName;
    }

    public RestaurantDTO() {
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getDeliveryZones() {
        return deliveryZones;
    }

    public void setDeliveryZones(List<String> deliveryZones) {
        this.deliveryZones = deliveryZones;
    }
}
