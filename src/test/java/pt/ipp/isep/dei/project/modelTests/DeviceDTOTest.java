package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.devices.DeviceDTO;
import pt.ipp.isep.dei.project.model.devices.DeviceMapper;
import pt.ipp.isep.dei.project.model.house.RoomDTO;

import static org.junit.jupiter.api.Assertions.*;

class DeviceDTOTest {
    private DeviceDTO deviceDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId("Kitchen");
        roomDTO.setDescription("First Floor kitchen");
        roomDTO.setHouseFloor(1);

        deviceDTO = DeviceMapper.newDeviceDTO();
        deviceDTO.setName("Fridge Ariston");
        deviceDTO.setDeviceType("Fridge");
        deviceDTO.setLocation(roomDTO);
        deviceDTO.setNominalPower(2.4);
    }

    @Test
    public void testGetName() {
        // Arrange
        String expectedResult = "Fridge Ariston";
        // Act
        String result = deviceDTO.getName();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testSetName() {
        // Arrange
        String expectedResult = "Fridge Ariston V.2";
        deviceDTO.setName(expectedResult);
        // Act
        String result = deviceDTO.getName();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testGetDeviceType() {
        // Arrange
        String expectedResult = "Fridge";
        // Act
        String result = deviceDTO.getDeviceType();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testSetDeviceType() {
        // Arrange
        String expectedResult = "Lamp";
        deviceDTO.setDeviceType(expectedResult);
        // Act
        String result = deviceDTO.getDeviceType();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testGetLocation() {
        // Arrange
        RoomDTO expectedResult = new RoomDTO();
        expectedResult.setRoomId("Kitchen");
        expectedResult.setDescription("First Floor kitchen");
        expectedResult.setHouseFloor(1);

        // Act
        RoomDTO result = deviceDTO.getLocation();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testSetLocation() {
        // Arrange
        RoomDTO expectedResult = new RoomDTO();
        expectedResult.setRoomId("Bedroom");
        expectedResult.setDescription("First Floor bedroom");
        expectedResult.setHouseFloor(1);

        deviceDTO.setLocation(expectedResult);

        // Act
        RoomDTO result = deviceDTO.getLocation();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testGetNominalPower() {
        // Arrange
        double expectedResult = 2.4;
        // Act
        double result = deviceDTO.getNominalPower();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testSetNominalPower() {
        // Arrange
        double expectedResult = 3.0;
        deviceDTO.setNominalPower(expectedResult);
        // Act
        double result = deviceDTO.getNominalPower();
        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testEquals_SameClass_ShouldBeEquals() {
        // Arrange
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId("Kitchen");
        roomDTO.setDescription("First Floor kitchen");
        roomDTO.setHouseFloor(1);

        DeviceDTO expectedResult = DeviceMapper.newDeviceDTO();
        expectedResult.setName("Fridge Ariston");
        expectedResult.setDeviceType("Fridge");
        expectedResult.setLocation(roomDTO);
        expectedResult.setNominalPower(2.4);

        // Act
        boolean result = deviceDTO.equals(expectedResult);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testEquals_DifferentObjects_ShouldReturnFalse() {
        // Arrange
        RoomDTO expectedResult = new RoomDTO();
        expectedResult.setRoomId("Kitchen");
        expectedResult.setDescription("First Floor kitchen");
        expectedResult.setHouseFloor(1);

        // Act
        boolean result = deviceDTO.equals(expectedResult);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testEquals_SameObject_ShouldReturnTrue() {
        // Act
        boolean result = deviceDTO.equals(deviceDTO);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testHashCode() {
        // Arrange
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId("Kitchen");
        roomDTO.setDescription("First Floor kitchen");
        roomDTO.setHouseFloor(1);

        DeviceDTO newDeviceDTO = DeviceMapper.newDeviceDTO();
        newDeviceDTO.setName("Fridge Ariston");
        newDeviceDTO.setDeviceType("Fridge");
        newDeviceDTO.setLocation(roomDTO);
        newDeviceDTO.setNominalPower(2.4);

        int expectedResult = newDeviceDTO.hashCode();

        // Act
        int result = deviceDTO.hashCode();

        // Assert
        assertEquals(expectedResult,result);
    }
}