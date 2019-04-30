package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.television.TelevisionType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TelevisionTypeTest {

    private TelevisionType televisionType;
    private Room livingRoom;

    @BeforeEach
    public void StartUp() {
        //Type
        televisionType = new TelevisionType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        livingRoom = new Room("Living Room", "room", 1, dim);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Television";
        //Act
        String result = televisionType.getTypeName();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testCreateDevice() {
        //Arrange
        Device expectedResult = televisionType.createDevice("Flat Screen TV");
        livingRoom.addDevice(expectedResult);
        //Act
        Device result = livingRoom.getDeviceByPosition(0);
        //Assert
        assertEquals(result, expectedResult);
    }

}
