package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

public class KettleSpecsTest {
    private static final String ATTRIBUTE_NOMINAL_POWER = "Nominal Power";
    private static final String ATTRIBUTE_MAXIMUM_VOLUME_WATER = "Maximum volume of Water";
    private static final String ATTRIBUTE_PERFORMANCE_RATIO = "Performance Ratio";

    private Room kitchen;
    private Device kettle;
    private House house;
    private DeviceSpecs kettleSpecs;

    /**
     * This method pretends to initialize
     */
    @BeforeEach
    public void StartUp() {

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        //Room
        Dimension dim = new Dimension(3, 5, 6);
        this.kitchen = new Room("Kitchen", 1, dim);
        this.house.addRoom(kitchen);
        this.kettle = this.house.createDevice("Kettle", "KettleKitchen", kitchen);
        this.kettle.setAttributesDevType(ATTRIBUTE_NOMINAL_POWER, 30);
        this.kettle.setAttributesDevType(ATTRIBUTE_MAXIMUM_VOLUME_WATER, 2);
        this.kettle.setAttributesDevType(ATTRIBUTE_PERFORMANCE_RATIO, 0.9);
        this.kettleSpecs = kettle.getSpecs();
    }

}
