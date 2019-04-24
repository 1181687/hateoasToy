package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.fridge.FridgeType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FridgeTypeTest {


    private FridgeType fridgeType;
    private Room garage;

    @BeforeEach
    public void StartUp() {
        //Type
        fridgeType = new FridgeType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        RoomId roomId = new RoomId("garage");
        garage = new Room(roomId, "room", 1, dim);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Fridge";
        //Act
        String result = fridgeType.getTypeName();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testCreateDevice() {
        //Arrange
        Device expectedResult = fridgeType.createDevice("Smeg Fridge");
        garage.addDevice(expectedResult);
        //Act
        Device result = garage.getDevice("Smeg Fridge");
        //Assert
        assertEquals(result, expectedResult);
    }

}
