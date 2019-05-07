
package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.GetCurrentAndMaxTempRoomController;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetCurrentAndMaxTempRoomControllerTest {
    private GetCurrentAndMaxTempRoomController ctrl;

    @Mock
    private RoomSensorService roomSensorService;

    @Mock
    private RoomService roomService;

    private Room room1;
    private Room room2;

    private SensorTypeId sensorTypeTemperatureId;

    private RoomSensor roomSensor;

    private Reading reading1;
    private Reading reading2;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        room2 = new Room(name2, description, houseFloor2, dimension2);

        sensorTypeTemperatureId = new SensorTypeId("Temperature");

        SensorId sensorId = new SensorId("sensor1");
        String sensorName = "Sensor numero 1";
        LocalDateTime startDateTime = LocalDateTime.of(2019,5,5,23,59,59);
        String units = "C";
        roomSensor = new RoomSensor(sensorId,sensorName,startDateTime,sensorTypeTemperatureId,units);

        double value1 = 31.2;
        LocalDateTime dateTime1 = LocalDateTime.of(2019,5,7,22,59,59);
        reading1 = new Reading(value1,dateTime1);

        double value2 = 30.2;
        LocalDateTime dateTime2 = LocalDateTime.of(2019,5,7,23,01,59);
        reading2 = new Reading(value2,dateTime2);

        this.ctrl = new GetCurrentAndMaxTempRoomController(sensorTypeTemperatureId, roomSensorService, roomService);
    }


    @Test
    public void getRoomDTOListTest_TwoDTORooms() {
        // RoomList with two rooms
        List<RoomDTO> roomDTOS = new ArrayList<>();

        roomDTOS.add(RoomMapper.mapToDTO(room1));
        roomDTOS.add(RoomMapper.mapToDTO(room2));

        List<RoomDTO> expectResult = roomDTOS;
        //act
        when(roomService.getAllRoomsDTO()).thenReturn(roomDTOS);
        List<RoomDTO> result = ctrl.getRoomDTOList();

        //assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getSensorType(){
        //arrange
        SensorTypeId expectedResult = sensorTypeTemperatureId;

        //act
        SensorTypeId result = ctrl.getType();

        //assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getLatestMeasurementOfRoomSensorTest(){
        //arrange
        room1.addSensorToListOfSensorsInRoom(roomSensor);

        roomSensor.addReading(reading1);
        roomSensor.addReading(reading2);

        ReadingDTO expectedResult = ReadingMapper.mapToDTO(reading2);

        //act
        RoomId roomId1 = new RoomId(room1.getRoomId());
        when(roomSensorService.getLastMeasurement(roomId1,sensorTypeTemperatureId)).thenReturn(expectedResult);
        ReadingDTO result = ctrl.getLatestMeasurementOfRoomSensor(room1.getRoomId());

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getMaximumTemperatureOfRoomInGivenDay(){
        //arrange
        room1.addSensorToListOfSensorsInRoom(roomSensor);

        roomSensor.addReading(reading1);
        roomSensor.addReading(reading2);

        LocalDate date1 = LocalDate.of(2019,5,7);

        double expectedResult = reading1.getValue();

        //act
        RoomId roomId1 = new RoomId(room1.getRoomId());
        when(roomSensorService.getMaxMeasurementValueOfADay(roomId1,sensorTypeTemperatureId,date1)).thenReturn(expectedResult);
        double result = ctrl.getMaximumTemperatureOfRoomInGivenDay(room1.getRoomId(),date1);

        //assert
        assertEquals(expectedResult, result);
    }
}
