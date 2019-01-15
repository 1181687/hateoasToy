package pt.ipp.isep.dei.project.model;

public enum DeviceTypes {
    FRIDGE("Fridge"),
    LAMP("Lamp"),
    DISH_WASHER("Dish Washer"),
    WASHING_MACHINE("Washing Machine"),
    ELECTRIC_WATER_HEATER("Electric Water Heater");

    private String mDeviceTypeName;

    DeviceTypes(String deviceTypeName) {
        this.mDeviceTypeName = deviceTypeName;
    }

    public String getDeviceTypeName() {
        return mDeviceTypeName;
    }


}