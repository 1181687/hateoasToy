package pt.ipp.isep.dei.project.modelTests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

public class MicrowaveOvenSpecsTest {
    private Room kitchen;
    private Device microwaveOven;
    private House house;
    private DeviceSpecs microwaveOvenSpecs;

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
        this.microwaveOven = this.house.createDevice("MicrowaveOven", "MicrowaveOven 2000", kitchen);
        this.microwaveOven.setAttributesDevType("Nominal Power", 30);
        //this.microwaveOven.setAttributesDevType("Time", 30);
        this.microwaveOvenSpecs = microwaveOven.getSpecs();

    }

    /*
        @Test
        public void testGetTypeName() {
            //Arrange
            String expectedResult = "MicrowaveOven";

            //Act
            String result = microwaveOven.getSpecs().getTypeName();

            //Assert
            assertEquals(expectedResult, result);
        }

        @Test
        public void testIsProgrammable() {
            //Act
            boolean result = microwaveOvenSpecs.isProgrammable();

            //Assert
            assertTrue(result);
        }
    */
    @Test
    public void asProgrammable() {
    }

    @Test
    public void getProgramList() {
    }

    @Test
    public void getEnergyConsumptionInADay() {
    }

    @Test
    public void getNominalPower() {
    }

    @Test
    public void setTime() {
    }

    @Test
    public void getAttributesToString() {
    }

    @Test
    public void getNumberOfAttributes() {
    }

    @Test
    public void getSpecsList() {
    }

    @Test
    public void getAttributeValue() {
    }

    @Test
    public void setAttributeValue() {
    }

    @Test
    public void getAttributeDataType() {
    }

    @Test
    public void addProgram() {
    }

    @Test
    public void createNewProgram() {
    }
}