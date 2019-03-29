package pt.ipp.isep.dei.project.modelTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.walltowelheater.WallTowelHeaterType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;


public class WallTowelHeaterTypeTest {

    private WallTowelHeaterType wallTowelHeaterType;
    private Room kitchen;

    @Before
    public void StartUp() {
        //Type
        wallTowelHeaterType = new WallTowelHeaterType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        kitchen = new Room("Kitchen", 1, dim);
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
        Device result = kitchen.getDeviceByPosition(0);
        //Assert
        assertEquals(result, expectedResult);
    }

}
