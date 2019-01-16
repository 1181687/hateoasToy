package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeviceList {
    private List<Device> mDeviceList;
    private static final String SAME_NAME = "Name already exists. Please write a new one.";

    public DeviceList() {
        this.mDeviceList = new ArrayList<>();
    }

    /**
     * get DeviceList
     *
     * @return List<Device>
     */
    public List<Device> getmDeviceList() {
        return mDeviceList;
    }

    /**
     * get size of list of devices
     * @return integer
     */
    public int getLength() {
        return getmDeviceList().size();
    }

    /**
     * gets a Device by it's position
     *
     * @param position integer position of Device
     * @return Device
     */
    public Device getDeviceByPosition(int position) {
        return mDeviceList.get(position);
    }

    /**
     * Method that adds a device to the list of Devices
     *
     * @param device the device to be added
     * @return true if it adds, false if it doesn't add, because it already contains it or the device is null
     */
    public boolean addDevice(Device device) {
        if (Objects.isNull(device)) {
            return false;
        }
        if (!(mDeviceList.contains(device))) {
            this.mDeviceList.add(device);
        }
        return true;
    }

    /**
     * method that check if a name of a Device already exists on the list of devices.
     *
     * @param name name of device
     * @return boolean true if exists, false if it doesn't
     */
    public boolean checkIfNameAlreadyExists(String name) {

        for (int i = 0; i < mDeviceList.size(); i++) {
            if (mDeviceList.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method that get the String content Name and Location of all devices in the list,
     * grouped by device type.
     *
     * @return String with Device Name and Location grouped by Type.
     */
    public String getContentNameLocationOrderedByType() {

        StringBuilder content = new StringBuilder();
        Map<String, List<Device>> byDeviceType = mDeviceList.stream()
                .collect(Collectors.groupingBy(Device::getType));


        for (Map.Entry<String, List<Device>> entry : byDeviceType.entrySet()) {
            content.append(entry.getKey());
            content.append("\n");
            for (Device dev : entry.getValue()) {

                content.append("- Device Name: ");
                content.append(dev.getName());
                content.append(", Location: ");
                content.append(dev.getLocation().getmName());
                content.append(".\n");
            }
            content.append("\n");
        }
        return content.toString();
    }


    /**
     * method that creates the same hashCode to the same DeviceLists
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(mDeviceList);
    }

    /**
     * Equals method to determine if two DeviceList are equal.     *
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceList)) {
            return false;
        }
        DeviceList listOne = (DeviceList) obj;
        return this.mDeviceList.equals(listOne.mDeviceList);
    }
    //ELECTRIC WATER HEATER

    public DeviceSpecs createNewElectricWaterHeater(double mHotWaterTemperature, double mMaximumVolume,
                                                    double mNominalPower, double performanceRatio) {

        return new ElectricWaterHeater(mHotWaterTemperature, mMaximumVolume, mNominalPower, performanceRatio);
    }

    public Device newElectricWaterHeater(String name, Room selectedRoom, double mHotWaterTemperature,
                                         double mMaximumVolume, double mNominalPower, double mPerformanceRatio) {
        if (checkIfNameAlreadyExists(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        DeviceSpecs electricWaterHeater = createNewElectricWaterHeater(mHotWaterTemperature, mMaximumVolume,
                mNominalPower, mPerformanceRatio);

        return new Device(name, selectedRoom, electricWaterHeater);
    }

    //WASHING MACHINE

    public DeviceSpecs createNewWashingMachine(double capacity, double nominalPower) {
        return new WashingMachine(capacity, nominalPower);
    }

    public Device newWashingMachine (String name, Room selectedRoom, double nominalPower, double capacity) {
        if (checkIfNameAlreadyExists(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        DeviceSpecs washingMachine = createNewWashingMachine(capacity, nominalPower);
        return new Device(name, selectedRoom, washingMachine);
    }

    //DISH WASHER
    public DeviceSpecs createNewDishWasher(int capacity, double nominalPower) {
        return new DishWasher(capacity, nominalPower);
    }

    public Device newDishWasher (String name, Room selectedRoom, double nominalPower, int capacity) {
        if (checkIfNameAlreadyExists(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        DeviceSpecs dishWasher = createNewDishWasher(capacity, nominalPower);
        return new Device(name, selectedRoom, dishWasher);
    }

    //LAMP
    public DeviceSpecs createNewLamp(double luminousFlux, double nominalPower) {
        return new Lamp(luminousFlux, nominalPower);
    }

    public Device newLamp (String name, Room selectedRoom, double nominalPower, double luminousFlux) {
        if (checkIfNameAlreadyExists(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        DeviceSpecs lamp = createNewLamp(luminousFlux, nominalPower);
        return new Device(name, selectedRoom, lamp);
    }

    //FRIDGE
    public DeviceSpecs createNewFridge(double freezerCapacity, double refrigeratorCapacity, double annualEnergyConsumption, double nominalPower) {
        return new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
    }

    public Device newFridge(String name, Room selectedRoom, double annualEnergyConsumption, double nominalPower, double freezerCapacity, double refrigeratorCapacity) {
        if (checkIfNameAlreadyExists(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        DeviceSpecs fridge = createNewFridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        return new Device(name, selectedRoom, fridge);
    }


    /**
     * Method that adds a device to the existing list.
     * @param device
     * @return
     */
    public boolean addDeviceToDeviceList (Device device){
        if (!(mDeviceList.contains(device))) {
            mDeviceList.add(device);
            return true;
        }
        return false;
    }

    /**
     * method that displays the device list content
     *
     * @return content of the device list
     */
    public String getDeviceListContent() {
        StringBuilder content = new StringBuilder();
        int deviceListLength = getLength();
        int numberInTheList = 1;
        for (int i = 1; i <= deviceListLength; i++) {
            content.append(numberInTheList + " - Name of the device: " + getmDeviceList().get(i - 1).getName());
            content.append("\n");
            numberInTheList++;
        }
        return content.toString();
    }

    /**
     * method that check if the device list is empty
     */
    public boolean checkIfDeviceListIsEmpty() {
        return mDeviceList.isEmpty();
    }

    public String getDeviceTypeListContent() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (DeviceTypes deviceTypeName : DeviceTypes.values()) {
            String deviceType = deviceTypeName.getDeviceTypeName();
            content.append(numberInTheList + "- ");
            content.append(deviceType);
            content.append("\n");
            numberInTheList++;
        }
        return content.toString();
    }

    /**
     * TO DO - LUÍS
     *
     * @param type
     * @return
     */
    public DeviceList getAllDevicesOfAType(String type) {
        DeviceList listOfDevicesWithTheType = new DeviceList();
        for (Device device : mDeviceList) {
            if (device.getType().equals(type)) {
                listOfDevicesWithTheType.addDevice(device);
            }
        }
        return listOfDevicesWithTheType;
    }

    /**
     * TO DO - LUÍS
     *
     * @param devicePosition
     * @param attribute
     * @param value
     * @return
     */
    public boolean setAttribute(int devicePosition, int attribute, double value) {
        Device device = mDeviceList.get(devicePosition);
        return device.setAttributesDevType(attribute, value);
    }

    /**
     * TO DO - LUÍS
     *
     * @param devicePosition
     * @return
     */
    public double getEnergyConsumptionOfADevice(int devicePosition) {
        Device device = mDeviceList.get(devicePosition);
        return device.getEnergyConsumptionInADay();
    }

    /**
     * TO DO - LUÍS
     *
     * @return
     */
    public double getTotalEnergyConsumption() {
        double totalEnergyConsumption = 0;
        for (Device device : mDeviceList) {
            totalEnergyConsumption += device.getEnergyConsumptionInADay();
        }
        return totalEnergyConsumption;
    }
}
