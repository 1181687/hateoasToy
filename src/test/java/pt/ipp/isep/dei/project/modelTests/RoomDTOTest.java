package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.house.RoomDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomDTOTest {

    private RoomDTO roomDTO;


    @BeforeEach
    public void StartUp() {
        this.roomDTO = new RoomDTO();

        roomDTO.setRoomId("id");
        roomDTO.setDescription("room");
        roomDTO.setHouseFloor(1);
        roomDTO.setWidth(1.1);
        roomDTO.setLength(3.5);
        roomDTO.setHeight(4.1);

    }

    @Test
    public void testGetRoomId_id() {
        //Arrange
        String expectedResult = "id";
        //Act
        String result = roomDTO.getRoomId();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetRoomId_id2() {
        //Arrange
        roomDTO.setRoomId("id2");
        String expectedResult = "id2";
        //Act
        String result = roomDTO.getRoomId();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetDescription_room() {
        //Arrange
        String expectedResult = "room";
        //Act
        String result = roomDTO.getDescription();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testSetDescription_room2() {
        //Arrange
        roomDTO.setDescription("room2");
        String expectedResult = "room2";
        //Act
        String result = roomDTO.getDescription();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetHouseFloor_1() {
        //Arrange
        double expectedResult = 1;
        //Act
        double result = roomDTO.getHouseFloor();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testSetHouseFloor_2() {
        //Arrange
        roomDTO.setHouseFloor(2);
        double expectedResult = 2;
        //Act
        double result = roomDTO.getHouseFloor();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testGetWidth_1Dot1() {
        //Arrange
        double expectedResult = 1.1;
        //Act
        double result = roomDTO.getWidth();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testSetWidth_2() {
        //Arrange
        roomDTO.setWidth(2);
        double expectedResult = 2;
        //Act
        double result = roomDTO.getWidth();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testGeLength_3Dot5() {
        //Arrange
        double expectedResult = 3.5;
        //Act
        double result = roomDTO.getLength();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testSetLength_2() {
        //Arrange
        roomDTO.setLength(2);
        double expectedResult = 2;
        //Act
        double result = roomDTO.getLength();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testGetHeight_4Dot1() {
        //Arrange
        double expectedResult = 4.1;
        //Act
        double result = roomDTO.getHeight();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }

    @Test
    public void testSetHeight_2() {
        //Arrange
        roomDTO.setHeight(2);
        double expectedResult = 2;
        //Act
        double result = roomDTO.getHeight();
        //Assert
        assertEquals(expectedResult, result, 0.00001);

    }
}

