package pt.ipp.isep.dei.project.modelTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.stove.StoveType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;


public class StoveTypeTest {

    private StoveType stoveType;
    private Room kitchen;

    @Before
    public void StartUp() {
        //Type
        stoveType = new StoveType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        kitchen = new Room("Kitchen", 1, dim);
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
        Device expectedResult = stoveType.createDevice("Fan 200");
        kitchen.addDevice(expectedResult);
        //Act
        Device result = kitchen.getDeviceByPosition(0);
        //Assert
        assertEquals(result, expectedResult);
    }
}
