package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.electricwaterheater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ElectricWaterHeaterTypeTest {

    private ElectricWaterHeaterType electricWaterHeaterType;
    private Room kitchen;

    @BeforeEach
    public void StartUp() {
        //Type
        electricWaterHeaterType = new ElectricWaterHeaterType();

        // Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        kitchen = new Room("Kitchen", "room", 1, dim);
    }

    @Test
    public void testGetTypeName() {
        //Arrange
        String expectedResult = "ElectricWaterHeater";
        //Act
        String result = electricWaterHeaterType.getTypeName();
        //Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void testCreateDevice() {
        //Arrange
        Device expectedResult = electricWaterHeaterType.createDevice("ElectricWaterHeater 200");
        kitchen.addDevice(expectedResult);
        //Act
        Device result = kitchen.getDeviceByPosition(0);
        //Assert
        assertEquals(result, expectedResult);
    }

}

