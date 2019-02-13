package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.EstimateEnergyOfWaterHeaterController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EstimateEnergyOfWaterHeaterControllerTest {
    private EstimateEnergyOfWaterHeaterController controller;
    private House houseEdificioB;
    private Room room;
    private Room room1;
    private Device cenas1;
    private Device cenas2;

    @BeforeEach
    public void StartUp() {
        // Room Instantiation
        String name = "Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        this.room = new Room(name, 2, dim);
        String name1 = "Laundry";
        Dimension dim1 = new Dimension(3, 3.5, 5.5);
        this.room1 = new Room(name1, 1, dim1);

        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.houseEdificioB = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        houseEdificioB.setAddress(address);
        houseEdificioB.setInsertedGeoArea(insertedGeoArea);

        houseEdificioB.addRoom(room);
        houseEdificioB.addRoom(room1);

        // ElectricWaterHeaters Instantiation
        double hotWaterTemp = 100;
        double maximumVolume = 100;
        double performanceRatio = 0.9;
        double nominalPower = 50;
        ElectricWaterHeaterType EWHType = new ElectricWaterHeaterType();
        cenas1 = EWHType.createDevice("Bosch Tronic 3000", room);
        //ElectricWaterHeaterSpecs electricWaterHeaterSpecs = new ElectricWaterHeaterSpecs(hotWaterTemp, maximumVolume, performanceRatio, nominalPower);
        //ElectricWaterHeater EWH1 = new ElectricWaterHeater("Bosch Tronic 3000", room, electricWaterHeaterSpecs);

        double hotWaterTemp1 = 100;
        double maximumVolume1 = 100;
        double performanceRatio1 = 0.7;
        double nominalPower1 = 50;

        cenas2 = EWHType.createDevice("Bosch Tronic Coiso", room1);
        //ElectricWaterHeaterSpecs electricWaterHeaterSpecs1 = new ElectricWaterHeaterSpecs(hotWaterTemp1, maximumVolume1, performanceRatio1, nominalPower1);
        //ElectricWaterHeater EWH2 = new ElectricWaterHeater("Bosch Tronic Coiso", room1, electricWaterHeaterSpecs1);

        this.controller = new EstimateEnergyOfWaterHeaterController(houseEdificioB);
    }

    @Test
    public void getNumberOfWaterHeatersTest() {
        // Arrange
        int expectedResult = 2;
        // Act
        int result = controller.getNumberOfWaterHeaters();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameOfWaterHeaterTest() {
        // Arrange
        String expectedResult = "Bosch Tronic 3000";

        // Act
        String result = controller.getNameOfWaterHeater(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getEnergyConsumptionTest() {
        // Arrange
        double coldWaterTemp = 30;
        controller.setColdWaterTemp(1, coldWaterTemp);
        double volumeOfWaterToHeat = 70;
        controller.setVolumeOfWaterToHeat(1, volumeOfWaterToHeat);

        double expectedResult = 3.99;

        // Act
        double result = controller.getEnergyConsumptionOfAWaterHeater(1);

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getTotalEnergyConsumptionTest() {
        // Arrange
        double coldWaterTemp = 30;
        controller.setColdWaterTemp(0, coldWaterTemp);
        double volumeOfWaterToHeat = 70;
        controller.setVolumeOfWaterToHeat(0, volumeOfWaterToHeat);

        double coldWaterTemp1 = 30;
        controller.setColdWaterTemp(1, coldWaterTemp1);
        double volumeOfWaterToHeat1 = 70;
        controller.setVolumeOfWaterToHeat(1, volumeOfWaterToHeat1);

        double expectedResult = 9.12;

        // Act
        double result = controller.getTotalEnergyConsumptionOfAllDevicesOfAType();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }
}