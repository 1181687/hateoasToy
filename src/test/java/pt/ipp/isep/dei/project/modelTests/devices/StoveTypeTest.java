package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.stove.StoveType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoveTypeTest {

    private StoveType stoveType;
    private Room kitchen;

    @BeforeEach
    public void StartUp() {
        //Type
        stoveType = new StoveType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        RoomId roomId = new RoomId("kitchen");
        kitchen = new Room(roomId, "room", 1, dim);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "Stove";
        //Act
        String result = stoveType.getTypeName();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testCreateDevice() {
        //Arrange
        Device expectedResult = stoveType.createDevice("Meireles Stove");
        kitchen.addDevice(expectedResult);
        //Act
        Device result = kitchen.getDevice("Meireles Stove");
        //Assert
        assertEquals(result, expectedResult);
    }

}

