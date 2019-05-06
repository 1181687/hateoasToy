package pt.ipp.isep.dei.project.controllersTests;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.GetListOfSensorsAndDevicesRoomController;
import pt.ipp.isep.dei.project.model.devices.Device;

import pt.ipp.isep.dei.project.model.devices.DeviceType;
import pt.ipp.isep.dei.project.model.devices.electricwaterheater.ElectricWaterHeater;
import pt.ipp.isep.dei.project.model.devices.electricwaterheater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class GetListOfSensorsAndDevicesRoomControllerTest {

    private GetListOfSensorsAndDevicesRoomController controller;
    private Room room;
    @Mock
    private RoomService roomService;
    @Mock
    private RoomSensorService sensorService;

    @BeforeEach
    public void StartUp() {

        MockitoAnnotations.initMocks(this);

        // Room 1
        String name = "room1";
        String description = "room";
        int houseFloor = 3;
        Dimension dimension = new Dimension(3, 3.5, 3.5);
        this.room = new Room(name, description, houseFloor, dimension);


        this.controller = new GetListOfSensorsAndDevicesRoomController(this.roomService, this.sensorService);
    }

    @Test
    public void getRoomDTOList() {
        //Arrange
        List<Room> rooms = new ArrayList<>();
        rooms.add(this.room);

        when(this.roomService.getAllRooms()).thenReturn(rooms);

        List<RoomDTO> expectedResult = new ArrayList<>();
        expectedResult.add(RoomMapper.mapToDTO(this.room));

        //Act
        List<RoomDTO> result = this.controller.getRoomDTOList();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void getRoomSensorDTOList(){
        //Arrange
        RoomId roomId = new RoomId(this.room.getRoomId());
        LocalDateTime startingDate = LocalDateTime.of(2018,11,14,23,50);
        SensorId sensorId = new SensorId("TT1234");
        SensorTypeId typeId = new SensorTypeId("Temperature");
        RoomSensor sensor = new RoomSensor(sensorId,"TT12345",startingDate,typeId,"C",roomId);

        LocalDateTime startingDate2 = LocalDateTime.of(2017,1,14,23,50);
        SensorId sensorId2 = new SensorId("H1234");
        SensorTypeId typeId2 = new SensorTypeId("Humidity");
        RoomSensor sensor2 = new RoomSensor(sensorId2,"TT12345",startingDate2,typeId2,"l/m3",roomId);

        List<RoomSensor> sensors = Arrays.asList(sensor,sensor2);

        when(this.sensorService.getAllSensorsOfRoom(roomId)).thenReturn(sensors);

        List<RoomSensorDTO> expectedResult = Arrays.asList(RoomSensorMapper.mapToDTOWithoutReadings(sensor),RoomSensorMapper.mapToDTOWithoutReadings(sensor2));

        //Act
        List<RoomSensorDTO> result = this.controller.getRoomSensorDTOList(this.room.getRoomId());
        //Assert
        assertEquals(expectedResult,result);

    }

    @Test
    public void getDeviceDTOList(){
        DeviceType type = new ElectricWaterHeaterType();
        Device electricWaterHeater = type.createDevice("ewh2000");
        electricWaterHeater.setLocation(this.room);
        electricWaterHeater.setAttributesDevType("Hot-Water Temperature", 55);
        electricWaterHeater.setAttributesDevType("Performance Ratio", 0.9);
        electricWaterHeater.setAttributesDevType("Nominal Power", 700);

    }
}
