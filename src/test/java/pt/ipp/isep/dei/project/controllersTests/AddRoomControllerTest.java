package pt.ipp.isep.dei.project.controllersTests;
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddRoomController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddRoomControllerTest {
    private AddRoomController controller;
    private House house;

    @BeforeEach
    public void StartUp() {
        // Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");
        house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        house.setAddress(address);

        // Controller
        controller = new AddRoomController(house);
    }

    @Test
    public void addRoomToHouseNegativeTest() {
        // Act
        boolean result = controller.addRoomToHouse();

        // Assert
        assertFalse(result);
    }

    @Test
    public void addRoomToHousePositiveTest() {
        // Arrange
        controller.newRoom(4, 4, 4, "Kitchen", 0);

        // Act
        boolean result = controller.addRoomToHouse();

        // Assert
        assertTrue(result);
    }

    @Test
    public void isNameExistantPositiveTest() {
        // Arrange
        Dimension dim = new Dimension(5, 6, 7);
        Room kitchen = new Room("Kitchen", 0, dim);
        house.addRoom(kitchen);

        // Act
        boolean result = controller.isNameExistant("KITCHEN");

        assertTrue(result);
    }

    @Test
    public void isNameExistantNegativeTest() {
        // Act
        boolean result = controller.isNameExistant("KITCHEN");

        // Assert
        assertFalse(result);
    }
}
*/