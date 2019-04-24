package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.microwaveoven.MicrowaveOvenType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MicrowaveOvenTypeTest {


    private MicrowaveOvenType microwaveOvenType;
    private Room kitchen;

    @BeforeEach
    public void StartUp() {
        //Type
        microwaveOvenType = new MicrowaveOvenType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        RoomId roomId = new RoomId("kitchen");
        kitchen = new Room(roomId, "room", 1, dim);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "MicrowaveOven";
        //Act
        String result = microwaveOvenType.getTypeName();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testCreateDevice() {
        //Arrange
        Device expectedResult = microwaveOvenType.createDevice("Bosch Microwave Oven");
        kitchen.addDevice(expectedResult);
        //Act
        Device result = kitchen.getDevice("Bosch Microwave Oven");
        //Assert
        assertEquals(result, expectedResult);
    }

}

