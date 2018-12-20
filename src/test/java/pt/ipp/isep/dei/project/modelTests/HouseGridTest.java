package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseGridTest {

    @Test
    void TestDisplayRoomsAttachedToHouseGrid () {

        // Arrange
        double maximumContractedPower = 2000;

        Dimensions dimensionsRoom1 = new Dimensions(5.2, 3.7, 8.5);
        Room room1 = new Room ("Kid's room", 1, dimensionsRoom1);
        Dimensions dimensionsRoom2 = new Dimensions(5.2, 3.7, 8.5);
        Room room2 = new Room ("Bathroom", 1, dimensionsRoom2);
        RoomList roomsConnectedToHouseGrid = new RoomList();

        roomsConnectedToHouseGrid.addRoomToRoomList(room1);
        roomsConnectedToHouseGrid.addRoomToRoomList(room2);

        HouseGrid houseGrid1 = new HouseGrid(maximumContractedPower, roomsConnectedToHouseGrid);

        String expectedResult =
                "1- Name: Kid's room, House Floor: 1, Dimensions - Height: 5.2, Dimensions - Length: 3.7, Dimensions - Width: 8.5\n" +
                "2- Name: Bathroom, House Floor: 1, Dimensions - Height: 5.2, Dimensions - Length: 3.7, Dimensions - Width: 8.5" +
                "\n";

        // Act
        String result = houseGrid1.displayRoomsAttachedToHouseGrid();

        // Assert
        assertEquals(expectedResult, result);
    }
}
