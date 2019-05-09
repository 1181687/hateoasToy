package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;

import static org.junit.jupiter.api.Assertions.*;

class HouseGridMapperTest {


    @Test
    void mapToEntity() {
        //Arrange
        RoomDTO roomDTO = RoomMapper.newRoomDTO();
        roomDTO.setRoomId("bathroom");
        roomDTO.setHouseFloor(1);
        roomDTO.setDescription("upstairs bathroom");
        roomDTO.setWidth(2.0);
        roomDTO.setLength(3.2);
        roomDTO.setHeight(2.0);

        Room room = RoomMapper.mapToEntity(roomDTO);

        HouseGridDTO gridDTO = HouseGridMapper.newHouseGridDTO();
        gridDTO.setName("main grid");
        gridDTO.addRoomDTO(roomDTO);

        HouseGrid expectedResult = new HouseGrid(new HouseGridId("main grid"));
        expectedResult.addRoom(room);

        //Act
        HouseGrid result = HouseGridMapper.mapToEntity(gridDTO);

        //Assert
        assertEquals(expectedResult,result);

    }

    @Test
    void mapToEntity_WithoutRooms() {
        //Arrange

        HouseGridDTO gridDTO = HouseGridMapper.newHouseGridDTO();
        gridDTO.setName("main grid");

        HouseGrid expectedResult = new HouseGrid(new HouseGridId("main grid"));

        //Act
        HouseGrid result = HouseGridMapper.mapToEntity(gridDTO);

        //Assert
        assertEquals(expectedResult,result);

    }
}