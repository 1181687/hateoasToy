package pt.ipp.isep.dei.project.modelTests.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.devices.DeviceDTO;
import pt.ipp.isep.dei.project.model.house.*;

import static org.junit.jupiter.api.Assertions.*;

class DeviceDTOTest {
    private DeviceDTO deviceDTO;
    private Room kitchen;

    @BeforeEach
    public void startUp() {
        //Room
        Dimension dim = new Dimension(3, 5, 6);
        RoomId roomId = new RoomId("kitchen");
        kitchen = new Room(roomId, "room", 1, dim);
        deviceDTO = new DeviceDTO();
        deviceDTO.setName("DishWashaa");
        deviceDTO.setDeviceType("DishWasher");
        deviceDTO.setLocation(RoomMapper.mapToDTO(kitchen));
        deviceDTO.setNominalPower(1.8);
    }


    @Test
    void getName() {
        // Arrange
        String expectedResult = "DishWashaa";
        // Act
        String result = deviceDTO.getName();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void setName() {
        // Arrange
        String expectedResult = "Bettah DishWashaa";
        deviceDTO.setName(expectedResult);
        // Act

        String result = deviceDTO.getName();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getDeviceType() {
        // Arrange
        String expectedResult = "DishWasher";
        // Act
        String result = deviceDTO.getDeviceType();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void setDeviceType() {
        // Arrange
        String expectedResult = "WashingMachine";
        deviceDTO.setDeviceType(expectedResult);
        // Act

        String result = deviceDTO.getDeviceType();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getLocation() {
        // Arrange
        String expectedResult = "kitchen";
        // Act
        String result = deviceDTO.getLocation().getId();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void setLocation() {
        // Arrange
        Dimension dim = new Dimension(3, 5, 6);
        RoomId roomId = new RoomId("Laundry");
        Room laundry = new Room(roomId, "Laundry", 1, dim);
        String expectedResult = "Laundry";
        deviceDTO.setLocation(RoomMapper.mapToDTO(laundry));
        // Act
        String result = deviceDTO.getLocation().getId();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getNominalPower() {
        // Arrange
        double expectedResult = 1.8;
        // Act
        double result = deviceDTO.getNominalPower();
        // Assert
        assertEquals(expectedResult,result,0.0001);
    }

    @Test
    void setNominalPower() {
        // Arrange
        double expectedResult = 2.4;
        deviceDTO.setNominalPower(expectedResult);
        // Act

        double result = deviceDTO.getNominalPower();
        // Assert
        assertEquals(expectedResult,result);
    }
}