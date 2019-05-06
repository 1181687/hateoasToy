package pt.ipp.isep.dei.project.modelTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.repositories.HouseRepository;
import pt.ipp.isep.dei.project.repositories.RoomRepository;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.RoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;
    private HouseRepository houseRepository;
    @InjectMocks
    private RoomService roomService;
    private HouseService houseService;
    private Room b107;
    private Room b109;
    private Room b210;


    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);

        //Dimensions
        Dimension dimB107 = new Dimension(10, 6, 3.5);
        Dimension dimB109 = new Dimension(10, 6, 3.5);
        Dimension dimB210 = new Dimension(5, 5.5, 3.5);

        // Room Instantiation
        b107 = new Room("B107", "Classroom", 1, dimB107);
        b109 = new Room("B109", "Classroom", 1, dimB109);
        b210 = new Room("B210", "Classroom", 2, dimB210);
        roomRepository.save(b107);
        roomRepository.save(b109);
        roomRepository.save(b210);
    }
// when(CLASS.FUNCTION(PARAMETERS)).thenReturn(RETURN_VALUE)

    @Test
    public void testGetAllRooms_emptyList() {
        //Arrange
        List<Room> expectedResult = roomService.getAllRooms();
        // Act
        List<Room> result = roomService.getAllRooms();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllRooms_twoRooms() {
        //Arrange
        when(roomRepository.save(b109)).thenReturn(b109);
        when(roomRepository.save(b107)).thenReturn(b107);

        List<Room> expectedResult = new ArrayList<>();
        expectedResult.add(b107);
        expectedResult.add(b109);
        when(roomRepository.findAll()).thenReturn(expectedResult);
        // Act
        List<Room> result = roomService.getAllRooms();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsListOfRoomsEmpty_boolean_True() {
        // Act
        boolean result = roomService.isListOfRoomsEmpty();

        // Assert
        assertTrue(result);
    }


    @Test
    public void testIsListOfRoomsEmpty_boolean_False() {
        //Arrange
        when(roomRepository.save(b109)).thenReturn(b109);
        when(roomRepository.save(b107)).thenReturn(b107);

        List<Room> roomList = new ArrayList<>();
        roomList.add(b107);
        roomList.add(b109);
        when(roomRepository.findAll()).thenReturn(roomList);
        // Act
        boolean finalResult = roomService.isListOfRoomsEmpty();

        // Assert
        assertFalse(finalResult);
    }



    @Test
    public void testRoomExists_boolean_false() {
        //Arrange
        RoomIdDTO b107DTO = RoomIdMapper.mapToDTO(b107.getId());
        //Act
        boolean result = roomService.roomExists(b107DTO);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testRoomExists_boolean_true() {
        //Arrange
        when(roomRepository.save(b107)).thenReturn(b107);
        List<Room> roomList = new ArrayList<>();
        roomList.add(b107);
        when(roomRepository.findAll()).thenReturn(roomList);
        when(roomRepository.existsById(b107.getId())).thenReturn(true);

        RoomIdDTO b107DTO = RoomIdMapper.mapToDTO(b107.getId());

        //Act
        boolean result = roomService.roomExists(b107DTO);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testGetAllRoomsDTO_b107() {

        when(roomRepository.save(b107)).thenReturn(b107);
        List<Room> roomList = new ArrayList<>();
        roomList.add(b107);
        when(roomRepository.findAll()).thenReturn(roomList);
        when(roomRepository.existsById(b107.getId())).thenReturn(true);
        when(roomRepository.findById(b107.getId())).thenReturn(Optional.of(b107));

        RoomDTO roomDTO = RoomMapper.mapToDTO(b107);
        List<RoomDTO> roomDTOList = new ArrayList<>();
        roomDTOList.add(roomDTO);

        RoomDTO expectedResult = roomDTOList.get(0);
        RoomDTO result = roomService.getAllRoomsDTO().get(0);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllRoomsDTO_empty() {
        //Arrange
        List<RoomDTO> expectedResult = new ArrayList<>();
        //Act
        List<RoomDTO> result = roomService.getAllRoomsDTO();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetRoomById_null() {
        //Act
        RoomId roomId = new RoomId("B500");
        Room result = roomService.getRoomById(roomId);

        // Assert
        assertNull(result);
    }

    @Test
    public void testGetRoomById_b107() {
        //Arrange
        when(roomRepository.save(b107)).thenReturn(b107);
        List<Room> roomList = new ArrayList<>();
        roomList.add(b107);
        when(roomRepository.findAll()).thenReturn(roomList);
        when(roomRepository.existsById(b107.getId())).thenReturn(true);

        Room expectedResult = b107;
        when(roomRepository.findById(b107.getId())).thenReturn(Optional.of(expectedResult));

        //Act
        Room result = roomService.getRoomById(b107.getId());
        // Assert
        assertEquals(expectedResult, result);
    }
}

