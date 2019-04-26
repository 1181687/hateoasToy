package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomMapperTest {

    @Test
    public void testMapToEntity_RoomDTO() {
        //Arrange
        String roomId = "id";
        String roomDescription = "room";
        int houseFloor = 1;
        double width = 1.1;
        double length = 1.2;
        double height = 1.3;
        Dimension roomDimension = new Dimension(width, length, height);

        Room room = new Room(roomId, roomDescription, houseFloor, roomDimension);

        RoomDTO roomDTO = RoomMapper.newRoomDTO();

        roomDTO.setRoomId(roomId);
        roomDTO.setHouseFloor(houseFloor);
        roomDTO.setDescription(roomDescription);
        roomDTO.setHeight(height);
        roomDTO.setLength(length);
        roomDTO.setWidth(width);

        Room expectedResult = RoomMapper.mapToEntity(roomDTO);
        //Act
        RoomDTO anotherRoom = RoomMapper.mapToDTO(room);
        Room result = RoomMapper.mapToEntity(anotherRoom);
        //Assert
        assertEquals(expectedResult, result);
    }
}
