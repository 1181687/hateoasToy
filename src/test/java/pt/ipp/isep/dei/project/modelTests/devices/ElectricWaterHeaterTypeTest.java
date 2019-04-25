package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.electricwaterheater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

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
        RoomId roomId = new RoomId("kitchen");
        kitchen = new Room(roomId, "room", 1, dim);
    }

    @Test
    public void testCreateDevice() {


        Device expectedResult = electricWaterHeaterType.createDevice("Cool Water Heater");
        kitchen.addDevice(expectedResult);
        //Act
        Device result = kitchen.getDevice("Cool Water Heater");
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getTypeNameTest() {
        // Arrange
        String expectedResult = "ElectricWaterHeater";

        // Act
        String result = electricWaterHeaterType.getTypeName();

        // Assert
        assertEquals(expectedResult, result);
    }
}
