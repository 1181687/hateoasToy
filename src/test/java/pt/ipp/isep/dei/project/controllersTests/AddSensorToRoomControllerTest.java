package pt.ipp.isep.dei.project.controllersTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.controllers.AddSensorToRoomController;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.services.SensorTypeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AddSensorToRoomControllerTest {
    private AddSensorToRoomController addSensorToRoomController;
    @Mock
    private SensorTypeService sensorTypeService;
    @Mock
    private RoomService roomService;
    @Mock
    private RoomSensorService roomSensorService;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        // Room

        this.addSensorToRoomController = new AddSensorToRoomController(sensorTypeService, roomService, roomSensorService);

    }

    @Test
    public void testDisplayRoomsInTheHouse() {
        // Arrange
        // RoomList with two rooms
        List<RoomDTO> roomDTOS = new ArrayList<>();

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        roomDTOS.add(RoomMapper.mapToDTO(room1));
        roomDTOS.add(RoomMapper.mapToDTO(room2));

        String expectResult = "1- Name: Kitchen, House Floor: 0, room\n" +
                "2- Name: Living Room, House Floor: 1, room\n";
        //act
        when(roomService.getAllRoomsDTO()).thenReturn(roomDTOS);
        List<RoomDTO> roomDTOList = addSensorToRoomController.getRoomListContent();
        StringBuilder result = new StringBuilder();
        int numberInTheList = 1;
        for (RoomDTO roomDTO : roomDTOList) {
            result.append(numberInTheList + "-" + " Name: " + roomDTO.getRoomId() + ", House Floor: " + roomDTO.getHouseFloor() + ", " + roomDTO.getDescription() + "\n");
            numberInTheList++;
        }
        //assert
        assertEquals(expectResult, result.toString());
    }

    @Test
    public void displayListOfSensorsType() {
        // Arrange
        // RoomList with two rooms
        RoomList roomList = new RoomList();

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        roomList.addRoom(room1);

        // Type of sensor
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        SensorType sensorType = new SensorType(sensorTypeId);

        // Sensors Type List
        List<SensorType> listSensorsType = new ArrayList<>();
        listSensorsType.add(sensorType);

        List<SensorTypeDTO> expectedResult = new ArrayList<>();
        expectedResult.add(SensorTypeMapper.mapToDto(listSensorsType.get(0)));

        // Act
        when(sensorTypeService.getSensorTypeList()).thenReturn(listSensorsType);
        List<SensorTypeDTO> result = addSensorToRoomController.getSensorTypes();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void createAndAddSensorToTheList() {
        // Arrange
        // Arrange
        List<RoomDTO> roomDTOS = new ArrayList<>();

        // Room
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room = new Room(name1, description, houseFloor1, dimension1);

        roomDTOS.add(RoomMapper.mapToDTO(room));

        // sensor
        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        SensorType sensorType = new SensorType(sensorTypeId);
        String id = "123";
        String sensorName = "A123";
        String units = "l/m2";

        RoomSensorDTO roomSensorDTO = RoomSensorMapper.newRoomSensorDTO();
        roomSensorDTO.setId(id);
        roomSensorDTO.setName(sensorName);
        roomSensorDTO.setSensorType(sensorTypeId.getSensorTypeId());
        roomSensorDTO.setUnits(units);
        roomSensorDTO.setRoomId(roomDTOS.get(0).getRoomId());

        // Sensors Type List
        SensorTypeList listSensorsType = new SensorTypeList();
        listSensorsType.addSensorType(sensorType);

        // Act
        when(roomSensorService.newSensor(roomSensorDTO)).thenReturn(true);
        boolean result = addSensorToRoomController.createAndAddSensorToTheList(roomSensorDTO);

        // Assert
        assertTrue(result);
    }


    @Test
    public void checkIfRoomListIsEmptyPositive() {
        // Act
        boolean result = addSensorToRoomController.getRoomListContent().isEmpty();
        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfRoomListIsEmptyNegative() {
        // Arrange
        List<RoomDTO> roomDTOS = new ArrayList<>();

        // Room
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room = new Room(name1, description, houseFloor1, dimension1);

        roomDTOS.add(RoomMapper.mapToDTO(room));

        // Act
        when(roomService.getAllRoomsDTO()).thenReturn(roomDTOS);
        boolean result = addSensorToRoomController.getRoomListContent().isEmpty();
        // Assert
        assertFalse(result);
    }

    @Test
    public void checkIfTheListOfSensorTypeIsEmptyNegative() {
        // Arrange
        // Sensors Type List
        List<SensorType> listSensorsType = new ArrayList<>();

        SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
        SensorType sensorType = new SensorType(sensorTypeId);
        listSensorsType.add(sensorType);

        when(sensorTypeService.getSensorTypeList()).thenReturn(listSensorsType);
        // Act
        boolean result = addSensorToRoomController.getSensorTypes().isEmpty();
        // Assert
        assertFalse(result);
    }

    @Test
    public void checkIfTheListOfSensorTypeIsEmptyPositive() {
        // Arrange
        List<SensorType> listSensorsType = new ArrayList<>();

        when(sensorTypeService.getSensorTypeList()).thenReturn(listSensorsType);
        // Act
        boolean result = addSensorToRoomController.getSensorTypes().isEmpty();
        // Assert
        assertTrue(result);
    }
}
