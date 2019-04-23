package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.electricwaterheater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.services.RoomAggregateService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectricWaterHeaterTypeTest {


    @Test
    public void testCreateDevice() {
        String name = "EWH Kitchen";
        Dimension dim = new Dimension(3, 3.5, 3.5);
        RoomId roomId = new RoomId("Room");
        Room room = new Room(roomId, "room", 2, dim);
        ElectricWaterHeaterType waterHeaterType = new ElectricWaterHeaterType();

        Device expectedResult = waterHeaterType.createDevice(name);
        room.addDevice(expectedResult);
        //Act
        Device result = room.getDevice(name);
        //Assert
        assertEquals(expectedResult, result);
    }
}
