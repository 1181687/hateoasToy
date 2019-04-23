package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.walltowelheater.WallTowelHeaterType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WallTowelHeaterTypeTest {

    private WallTowelHeaterType wallTowelHeaterType;
    private Room kitchen;

    @BeforeEach
    public void StartUp() {
        //Type
        wallTowelHeaterType = new WallTowelHeaterType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        RoomId roomId = new RoomId("kitchen");
        kitchen = new Room(roomId, "room", 1, dim);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "WallTowelHeater";
        //Act
        String result = wallTowelHeaterType.getTypeName();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testCreateDevice() {
        //Arrange
        Device expectedResult = wallTowelHeaterType.createDevice("WallTowelHeater 200");
        kitchen.addDevice(expectedResult);
        //Act
        Device result = kitchen.getDevice("WallTowelHeater 200");
        //Assert
        assertEquals(result, expectedResult);
    }

}
