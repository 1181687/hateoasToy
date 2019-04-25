package pt.ipp.isep.dei.project.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.RoomAggregateRepository;
import pt.ipp.isep.dei.project.RoomRepository;
import pt.ipp.isep.dei.project.controllers.AddRoomController;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class AddRoomControllerTest {

    @Mock
    private RoomAggregateService roomAggregateService;


    private AddRoomController controller;

    @BeforeEach
    public void startUp(){
        MockitoAnnotations.initMocks(this);
        this.controller = new AddRoomController(this.roomAggregateService);
    }

    @Test
    public void addRoom_RoomDoesntExistInRepo_ShouldReturnTrue(){
        //Arrange
        RoomId id = new RoomId("Bathroom");
        Dimension dimension = new Dimension(10.0,10.0,3.0);
        String description = "Some description";
        int houseFloor = 1;

        when(roomAggregateService.addRoom(any(RoomId.class),anyString(),anyInt(),any(Dimension.class))).thenReturn(true);

        RoomDTO roomDTO = RoomMapper.newRoomDTO();
        roomDTO.setId(id.getId());
        roomDTO.setDescription(description);
        roomDTO.setHeight(dimension.getHeight());
        roomDTO.setLength(dimension.getLength());
        roomDTO.setWidth(dimension.getWidth());
        roomDTO.setHouseFloor(houseFloor);
        //Act
        boolean result = this.controller.addRoom(roomDTO);
        //Assert
        assertTrue(result);
    }

    @Test
    public void addRoom_RoomAlreadyExistInRepo_ShouldReturnFalse(){
        //Arrange
        RoomId id = new RoomId("Bathroom");
        Dimension dimension = new Dimension(10.0,10.0,3.0);
        String description = "Some description";
        int houseFloor = 1;

        when(roomAggregateService.addRoom(any(RoomId.class),anyString(),anyInt(),any(Dimension.class))).thenReturn(false);

        RoomDTO roomDTO = RoomMapper.newRoomDTO();
        roomDTO.setId(id.getId());
        roomDTO.setDescription(description);
        roomDTO.setHeight(dimension.getHeight());
        roomDTO.setLength(dimension.getLength());
        roomDTO.setWidth(dimension.getWidth());
        roomDTO.setHouseFloor(houseFloor);
        //Act
        boolean result = this.controller.addRoom(roomDTO);
        //Assert
        assertFalse(result);
    }

    @Test
    public void isNameExistant_WhenNoRoomHasSameName_ShouldReturnFalse(){
        //Arrange
        String name = "Bathroom";

        when(roomAggregateService.roomExists(any(RoomId.class))).thenReturn(false);
        //Act
        boolean result = controller.isNameExistant(name);
        //Assert
        assertFalse(result);
    }

    @Test
    public void isNameExistant_WhenARoomHasSameName_ShouldReturnTrue(){
        //Arrange
        String name = "Bathroom";

        when(roomAggregateService.roomExists(any(RoomId.class))).thenReturn(true);
        //Act
        boolean result = controller.isNameExistant(name);
        //Assert
        assertTrue(result);
    }
}
