package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseGridDTOTest {

    private HouseGridDTO houseGridDTO;
    private List<RoomDTO> roomDTOList;


    @BeforeEach
    public void StartUp() {
        this.houseGridDTO = new HouseGridDTO();

        houseGridDTO.setName("grid");
        houseGridDTO.setRoomDTOS(roomDTOList);


    }

    @Test
    public void testGetGridName_grid() {
        //Arrange
        String expectedResult = "grid";
        //Act
        String result = houseGridDTO.getName();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetGridName_id2() {
        //Arrange
        houseGridDTO.setName("id2");
        String expectedResult = "id2";
        //Act
        String result = houseGridDTO.getName();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetGridName_EmptyString_NameShouldStayTheSame() {
        //Arrange
        houseGridDTO.setName("");
        String expectedResult = "grid";
        //Act
        String result = houseGridDTO.getName();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetRoomDTOS_roomDTOList() {
        //Arrange
        List<RoomDTO> expectedResult = roomDTOList;
        //Act
        List<RoomDTO> result = houseGridDTO.getRoomDTOS();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetRoomDTOS_roomDTOList() {
        //Arrange
        houseGridDTO.setRoomDTOS(roomDTOList);
        List<RoomDTO> expectedResult = roomDTOList;
        //Act
        List<RoomDTO> result = houseGridDTO.getRoomDTOS();
        //Assert
        assertEquals(expectedResult, result);

    }


}
