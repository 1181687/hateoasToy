package pt.ipp.isep.dei.project.model;

public enum DeviceType {
    FRIDGE("Fridge"),
    LAMP("Lamp"),
    DISH_WASHER("Dish Washer"),
    WASHING_MACHINE("Washing Machine"),
    ELECTRIC_WATER_HEATER("Electric Water Heater");

    private String mDeviceTypeName;

    /**
     * construtor of the eNum class.
     *
     * @param deviceTypeName
     */
    DeviceType(String deviceTypeName) {
        this.mDeviceTypeName = deviceTypeName;
    }

    /**
     * get the device type name.
     *
     * @return the device type name.
     */
    public String getDeviceTypeName() {
        return mDeviceTypeName;
    }


}