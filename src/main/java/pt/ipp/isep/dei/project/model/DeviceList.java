package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeviceList {
    private List<Device> mDeviceList = new ArrayList<>();

    public DeviceList() {
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
     *
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
                content.append(dev.getmName());
                content.append(", Location: ");
                content.append(dev.getLocation().getmName());
                content.append(".\n");
            }
            content.append("\n");
        }
        return content.toString();
    }


    /**
     * method that gets the List of devices ordered by Type
     *
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

    public Device newElectricWaterHeater(String name, Room selectedRoom, double hotWaterTemperature, double maximumVolume, double nominalPower, double performanceRatio) {

        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, nominalPower, performanceRatio);

        return new Device(name, selectedRoom, electricWaterHeater);
    }

    //METODO COMUM A WASHING MACHINE E DISH WASHER

    public List<Program> newProgramList(String programName, double duration, double energyConsumption, int numberOfPrograms) {
        List<Program> programList = new ArrayList<>();
        Program program = new Program(programName, duration, energyConsumption);
        for (int i = 1; i == numberOfPrograms; i++) {
            programList.add(program);
        }
        return programList;
    }

    //WASHING MACHINE

    public Device newWashingMachine(String name, Room selectedRoom, double nominalPower, double capacity,
                                    List<Program> programList) {
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);
        return new Device(name, selectedRoom, washingMachine);
    }

    //DISH WASHER

    public Device newDishWasher(String name, Room selectedRoom, double nominalPower, double capacity, String programName,
                                double duration, double energyConsumption, int numberOfPrograms) {
        List<Program> programList = newProgramList(programName, duration, energyConsumption, numberOfPrograms);
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);
        return new Device(name, selectedRoom, washingMachine);
    }

    //LAMP

    public Device newLamp(String name, Room selectedRoom, double nominalPower, double luminousFlux) {
        DeviceSpecs lamp = new Lamp(luminousFlux, nominalPower);
        return new Device(name, selectedRoom, lamp);
    }

    //FRIDGE

    public Device newFridge(String name, Room selectedRoom, double annualEnergyConsumption, double nominalPower, double freezerCapacity, double refrigeratorCapacity) {
        DeviceSpecs fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        return new Device(name, selectedRoom, fridge);
    }


    /**
     * Method that adds a device to the existing list.
     *
     * @param device
     * @return
     */
    public boolean addDeviceToDeviceList(Device device) {
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
            content.append(numberInTheList + " - Name of the device: " + getmDeviceList().get(i - 1).getmName());
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

    /**
     * @param type
     * @return
     */
    public List<Device> getDevicesByType(String type) {
        List<Device> newList = new ArrayList<>();
        for (int index = 0; index < mDeviceList.size(); index++) {
            if (mDeviceList.get(index).getType().equals(type)) {
                newList.add(mDeviceList.get(index));
            }
        }
        return newList;
    }

    /**
     * Method that allows the possibility of setting the cold-water temperature and the volume of water to heat in the
     * class Electric Water Heater.
     *
     * @param coldWaterTemp       Sets the current temperature of the water that is going to be heated.
     * @param volumeOfWaterToHeat Sets the amount of water to be heated.
     */
    public void setColdWaterTempAndVolumeOfWaterToHeat(double coldWaterTemp, double volumeOfWaterToHeat) {
        List<Device> newList = getDevicesByType("Electric Water Heater");
        for (int index = 0; index < newList.size(); index++) {
            newList.get(index).setColdWaterTempAndVolumeOfWaterToHeat(coldWaterTemp, volumeOfWaterToHeat);
        }
    }

    /**
     * @param type
     * @return
     */
    public double getEnergyConsumptionInADayOfAllDevicesOfAType(String type) {
        double energyConsumption = 0;
        List<Device> listOfDevicesWithTheType = getDevicesByType(type);
        for (int index = 0; index < listOfDevicesWithTheType.size(); index++) {
            energyConsumption += listOfDevicesWithTheType.get(index).getEnergyConsumptionInADay();
        }
        return energyConsumption;
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

    public List<Program> addToProgramList(String programName, double duration, double energyConsumption) {
        List<Program> programList = new ArrayList<>();
        Program program = new Program(programName, duration, energyConsumption);
        programList.add(program);

        return programList;
    }

}
