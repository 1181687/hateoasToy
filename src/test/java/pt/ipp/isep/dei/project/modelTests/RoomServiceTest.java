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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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

    }

    @Test
    public void testIsListOfRoomsEmpty_boolean_True() {
        // Act
        boolean result = roomService.isListOfRoomsEmpty();
        // Assert
        assertTrue(result);
    }

    /*
    @Test
    public void testIsListOfRoomsEmpty_boolean_False() {
        //Arrange
        //when(roomRepository.save(b107)).thenReturn(b107);
        //when(roomRepository.save(b109)).thenReturn(b109);
        //when(roomRepository.save(b210)).thenReturn(b210);
        //when(roomRepository.existsById(b107.getId())).thenReturn(true);
        //when(roomRepository.existsById(b109.getId())).thenReturn(true);
        //when(roomRepository.existsById(b210.getId())).thenReturn(true);
        roomService.save(b210);
        List<Room> expectedResult = roomService.getAllRooms();

        // Act
        //boolean result = roomService.isListOfRoomsEmpty();
        boolean result= expectedResult.isEmpty();

        // Assert
        assertFalse(result);
    }

    */

    @Test
    public void testRoomExists_boolean_false() {
        RoomIdDTO b107DTO = RoomIdMapper.mapToDTO(b107.getId());
        boolean result = roomService.roomExists(b107DTO);
    }

    @Test
    public void testRoomExists_boolean_true() {
    }


/*
    @Test
    public void testGetAllRoomsDTO() {
        RoomDTO roomDTO = RoomMapper.mapToDTO(b210);
        List<RoomDTO> roomDTOList = new ArrayList<>();
        roomDTOList.add(roomDTO);
        List<Room> roomList = new ArrayList<>();
        roomList.add(b210);

        when(roomRepository.findAll()).thenReturn(roomList);

//        RoomDTO expectedResult = roomDTOList.get(0);

  //      RoomDTO result = roomService.getAllRoomsDTO().get(0);

        List<RoomDTO> expectedResult = roomDTOList;

        List<RoomDTO> result = roomService.getAllRoomsDTO();

        //Assert
        assertEquals(expectedResult, result);
    }
*/

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
    }
}

