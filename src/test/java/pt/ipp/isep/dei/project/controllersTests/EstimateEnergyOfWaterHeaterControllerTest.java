package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.EstimateEnergyOfWaterHeaterController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EstimateEnergyOfWaterHeaterControllerTest {
    private EstimateEnergyOfWaterHeaterController controller;
    private House house;
    private Room kitchen;
    private Room laundry;
    private Device device1;
    private Device device2;
    @Mock
    private HouseService houseService;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);

        // Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Urban area");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        Address address = new Address(null, null, null);
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);
        // Room Instantiation
        Dimension dim = new Dimension(3, 3.5, 3.5);
        this.kitchen = new Room("Kitchen", "room", 2, dim);
        house.addRoom(kitchen);
        Dimension dim1 = new Dimension(3, 3.5, 5.5);
        this.laundry = new Room("Laundry", "room", 1, dim1);
        house.addRoom(laundry);

        // ElectricWaterHeaters Instantiation
        device1 = house.createDevice("ElectricWaterHeater", "Bosch Tronic 3000", kitchen);
        device1.setAttributesDevType("Nominal Power", 0.5);
        device1.setAttributesDevType("Performance Ratio", 0.8);
        device1.setAttributesDevType("Hot-Water Temperature", 70);
        device1.setAttributesDevType("Cold-Water Temperature", 30);
        device1.setAttributesDevType("Volume Of Water To Heat", 70);
        device2 = house.createDevice("ElectricWaterHeater", "Bosch Tronic 4000", laundry);
        device2.setAttributesDevType("Nominal Power", 1.1);
        device2.setAttributesDevType("Performance Ratio", 0.9);
        device2.setAttributesDevType("Hot-Water Temperature", 70);
        device2.setAttributesDevType("Cold-Water Temperature", 30);
        device2.setAttributesDevType("Volume Of Water To Heat", 70);

        // Controller Instantiation
        when(houseService.getHouse()).thenReturn(this.house);
        this.controller = new EstimateEnergyOfWaterHeaterController(houseService);
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
        double expectedResult = 2.61;

        // Act
        double result = controller.getEnergyConsumptionOfAWaterHeater(device1.getName());

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void getTotalEnergyConsumptionTest() {
        // Arrange
        double expectedResult = 5.54;

        // Act
        double result = controller.getTotalEnergyConsumptionOfAllElectricWaterHeaters();

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void setColdWaterTemperatureTest() {
        // Arrange
        controller.setColdWaterTemp("Bosch Tronic 3000", 20);
        double expectedResult = 20;
        // Act
        Double result = (Double) device1.getSpecs().getAttributeValue("Cold-Water Temperature");

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void setVolumeOfWaterToHeatTest() {
        // Arrange
        controller.setVolumeOfWaterToHeat("Bosch Tronic 3000", 200);
        double expectedResult = 200;
        // Act
        Double result = (Double) device1.getSpecs().getAttributeValue("Volume Of Water To Heat");

        // Assert
        assertEquals(expectedResult, result, 0.0001);
    }

}