package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.television.TelevisionType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TelevisionTypeTest {

    private TelevisionType televisionType;
    private Room livingRoom;

    @BeforeEach
    public void StartUp() {
        // Type
        televisionType = new TelevisionType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        RoomId roomId = new RoomId("kitchen");
        livingRoom = new Room(roomId, "room", 1, dim);
    }

    @Test
    public void testCreateDevice() {
        Device expectedResult = televisionType.createDevice("Big TV");
        livingRoom.addDevice(expectedResult);

        //Act
        Device result = livingRoom.getDevice("Big TV");

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getTypeNameTest() {
        // Arrange
        String expectedResult = "Television";

        // Act
        String result = televisionType.getTypeName();

        // Assert
        assertEquals(expectedResult, result);
    }
}