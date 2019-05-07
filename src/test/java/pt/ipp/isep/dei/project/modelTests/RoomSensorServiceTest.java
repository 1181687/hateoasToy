package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.repositories.RoomSensorRepository;
import pt.ipp.isep.dei.project.services.RoomSensorService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RoomSensorServiceTest {
    @Mock
    private RoomSensorRepository roomSensorRepository;
    @InjectMocks
    private RoomSensorService roomSensorService;
    private RoomSensor kitchenSensor;
    private Room kitchen;
    private RoomSensorDTO kitchenSensorDTO;


    @BeforeEach
    public void startup(){
        MockitoAnnotations.initMocks(this);
        // Room
        Dimension kitchenDimensions = new Dimension(3,2,3);
        kitchen = new Room("Kitchen","First floor kitchen",1,kitchenDimensions);
        // RoomSensor
        LocalDate startDate = LocalDate.of(2018,01,20);
        kitchenSensorDTO = RoomSensorMapper.newRoomSensorDTO();
        kitchenSensorDTO.setRoomId("Kitchen");
        kitchenSensorDTO.setUnits("C");
        kitchenSensorDTO.setSensorType("Temperature");
        kitchenSensorDTO.setName("Kitchen sensor 1");
        kitchenSensorDTO.setId("S01");
        kitchenSensorDTO.setStartingDate(startDate);


        roomSensorService.newSensor(kitchenSensorDTO);

        kitchenSensor = RoomSensorMapper.mapToEntity(kitchenSensorDTO);
    }

    @Test
    void getSensorById() {
        // Arrange
        SensorId sensorId = new SensorId("S01");

        // Act
        SensorIdDTO sensorIdDTO = SensorIdMapper.mapToDTO(sensorId);
        when(roomSensorRepository.findById(sensorId)).thenReturn(Optional.of(kitchenSensor));

        RoomSensorDTO result = roomSensorService.getSensorById(sensorIdDTO);
        // Assert
        assertEquals(kitchenSensorDTO,result);
    }

    @Test
    void saveSensors() {
    }

    @Test
    void testSensorExists_SensorExists_ShouldReturnTrue() {
        // Arrange
        SensorId sensorId = new SensorId("S01");

        // Act
        SensorIdDTO sensorIdDTO = SensorIdMapper.mapToDTO(sensorId);
        when(roomSensorRepository.existsById(sensorId)).thenReturn(true);

        boolean result = roomSensorService.sensorExists(sensorIdDTO);
        // Assert
        assertTrue(result);
    }

    @Test
    void testSensorExists_SensorDoesNotExists_ShouldReturnFalse() {
        // Arrange
        SensorId sensorId = new SensorId("S02");
        SensorIdDTO sensorIdDTO = SensorIdMapper.mapToDTO(sensorId);

        // Act
        when(roomSensorRepository.existsById(sensorId)).thenReturn(false);

        boolean result = roomSensorService.sensorExists(sensorIdDTO);

        // Assert
        assertFalse(result);
    }

    @Test
    void getReadingsDTO_ReadingsExist_ShouldBeEquals() {
        // Arrange
        SensorId sensorId = new SensorId("S01");

        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setId("S01");
        readingDTO.setValue(20);
        readingDTO.setDateTime(LocalDateTime.of(2018,12,30,12,25));
        readingDTO.setUnits("C");

        List<ReadingDTO> expectedResult = new ArrayList<>();
        expectedResult.add(readingDTO);

        kitchenSensor.addReading(ReadingMapper.mapToEntity(readingDTO));
        // Act
        when(roomSensorRepository.findById(sensorId)).thenReturn(Optional.of(kitchenSensor));

        LocalDate startDate = LocalDate.of(2018,12,29);
        LocalDate endDate = LocalDate.of(2018,12,31);

        List<ReadingDTO> result = roomSensorService.getReadingsDTO(startDate,endDate,sensorId);

        // Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getReadingsDTO_ReadingsDontExist_ShouldNotBeEquals() {
        // Arrange
        SensorId sensorId = new SensorId("S01");

        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setId("S01");
        readingDTO.setValue(20);
        readingDTO.setDateTime(LocalDateTime.of(2018,12,30,12,25));
        readingDTO.setUnits("C");

        List<ReadingDTO> expectedResult = new ArrayList<>();
        expectedResult.add(readingDTO);

        kitchenSensor.addReading(ReadingMapper.mapToEntity(readingDTO));
        // Act
        when(roomSensorRepository.findById(sensorId)).thenReturn(Optional.of(kitchenSensor));

        LocalDate startDate = LocalDate.of(2018,01,29);
        LocalDate endDate = LocalDate.of(2018,01,31);

        List<ReadingDTO> result = roomSensorService.getReadingsDTO(startDate,endDate,sensorId);

        // Assert
        assertNotEquals(expectedResult,result);
    }

    @Test
    void existSensors_SensorsExist_ShouldReturnTrue() {
        // Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");

        // Act
        when(roomSensorRepository.existsRoomSensorsByRoomIdAndSensorTypeId(kitchen.getId(),sensorTypeId)).thenReturn(true);

        boolean result = roomSensorService.existSensors(kitchen.getId(),kitchenSensor.getSensorType());

        // Assert
        assertTrue(result);
    }

    @Test
    void existSensors_SensorsDontExist_ShouldReturnFalse() {
        // Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");

        // Act
        when(roomSensorRepository.existsRoomSensorsByRoomIdAndSensorTypeId(kitchen.getId(),sensorTypeId)).thenReturn(false);

        boolean result = roomSensorService.existSensors(kitchen.getId(),kitchenSensor.getSensorType());

        // Assert
        assertFalse(result);
    }

    @Test
    void getSensorId_SensorExists_ShouldNotBeEquals() {
        // Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Rainfall");

        // Act
        when(roomSensorRepository.findByRoomIdAndSensorTypeId(kitchen.getId(),sensorTypeId)).thenReturn(kitchenSensor);

        SensorId result = roomSensorService.getSensorId(kitchen.getId(),kitchenSensor.getSensorType());

        // Assert
        assertNotEquals(kitchenSensor.getId(),result);
    }

    @Test
    void getSensorId_SensorTypeDoesntExist_Should() {
        // Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");

        // Act
        when(roomSensorRepository.findByRoomIdAndSensorTypeId(kitchen.getId(),sensorTypeId)).thenReturn(kitchenSensor);

        SensorId result = roomSensorService.getSensorId(kitchen.getId(),kitchenSensor.getSensorType());

        // Assert
        assertEquals(kitchenSensor.getId(),result);
    }

    @Test
    void getRoomSensor_SensorExists_ShouldReturnRightSensor() {
        // Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");

        // Act
        when(roomSensorRepository.findByRoomIdAndSensorTypeId(kitchen.getId(),sensorTypeId)).thenReturn(kitchenSensor);

        RoomSensor result = roomSensorService.getRoomSensor(kitchen.getId(),kitchenSensor.getSensorType());

        // Assert
        assertEquals(kitchenSensor,result);
    }

    @Test
    void getRoomSensor_SensorDoesntExists_ShouldReturnNull() {
        // Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Rainfall");

        // Act
        when(roomSensorRepository.findByRoomIdAndSensorTypeId(kitchen.getId(),sensorTypeId)).thenReturn(kitchenSensor);

        RoomSensor result = roomSensorService.getRoomSensor(kitchen.getId(),kitchenSensor.getSensorType());

        // Assert
        assertNull(result);
    }

    @Test
    void getRoomSensorDTO() {
        // Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Rainfall");

        // Act
        when(roomSensorRepository.findByRoomIdAndSensorTypeId(kitchen.getId(),sensorTypeId)).thenReturn(kitchenSensor);

        RoomSensor result = roomSensorService.getRoomSensor(kitchen.getId(),kitchenSensor.getSensorType());

        // Assert
        assertNull(result);
    }

    @Test
    void getLastMeasurement_ReadingExists_ShouldBeEquals() {
        // Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");

        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setId("S01");
        readingDTO.setValue(20);
        readingDTO.setDateTime(LocalDateTime.of(2018,12,30,12,25));
        readingDTO.setUnits("C");

        kitchenSensor.addReading(ReadingMapper.mapToEntity(readingDTO));
        // Act
        when(roomSensorRepository.findByRoomIdAndSensorTypeId(kitchen.getId(),sensorTypeId)).thenReturn(kitchenSensor);

        ReadingDTO result = roomSensorService.getLastMeasurement(kitchen.getId(),sensorTypeId);

        // Assert
        assertEquals(readingDTO,result);
    }

    @Test
    void getLastMeasurement_ReadingDoesntExists_ShouldBeEquals() {
        // Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");

        // Act
        when(roomSensorRepository.findByRoomIdAndSensorTypeId(kitchen.getId(),sensorTypeId)).thenReturn(kitchenSensor);

        ReadingDTO result = roomSensorService.getLastMeasurement(kitchen.getId(),sensorTypeId);

        // Assert
        assertNull(result);
    }

    @Test
    void getRoomSensorByRoomSensorTypeDate() {
        // Arrange
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");

        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setId("S01");
        readingDTO.setValue(20);
        readingDTO.setDateTime(LocalDateTime.of(2018,01,29,12,25));
        readingDTO.setUnits("C");

        kitchenSensor.addReading(ReadingMapper.mapToEntity(readingDTO));

        LocalDateTime startDate = LocalDateTime.of(2018,01,27,20,15);
        LocalDateTime endDate = LocalDateTime.of(2018,01,30,20,15);

        // Act
        when(roomSensorRepository.findByRoomIdAndSensorTypeIdAndReadingsBetween(kitchen.getId(),sensorTypeId,startDate,endDate)).thenReturn(kitchenSensor);

        RoomSensorDTO result = roomSensorService.getRoomSensorByRoomSensorTypeDate(kitchen.getId(),sensorTypeId,kitchenSensor.getStartingDate().toLocalDate());

        // Assert
        assertEquals(kitchenSensorDTO,result);
    }

    @Test
    void getMaxMeasurementValueOfADay() {
    }

    @Test
    void newSensor() {
    }

    @Test
    void getAllSensorsOfRoom() {
    }
}