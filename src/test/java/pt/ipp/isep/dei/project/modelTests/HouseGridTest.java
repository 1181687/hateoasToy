package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Dimensions;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.SensorList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HouseGridTest {

    @Test
    void TestDisplayRoomsAttachedToHouseGrid () {

        // Arrange
        Dimensions dimensionsRoom1 = new Dimensions(5.2, 3.7, 8.5);
        SensorList list = new SensorList();
        Room room1 = new Room("Kid's room", 1, dimensionsRoom1, list);
        Dimensions dimensionsRoom2 = new Dimensions(5.2, 3.7, 8.5);
        Room room2 = new Room("Bathroom", 1, dimensionsRoom2, list);

        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);

        houseGrid1.getmRoomsConnectedToHouseGrid().addRoomToRoomList(room1);
        houseGrid1.getmRoomsConnectedToHouseGrid().addRoomToRoomList(room2);

        String expectedResult =
                "1- Name: Kid's room, House Floor: 1, Dimensions - Height: 5.2, Dimensions - Length: 3.7, Dimensions - Width: 8.5\n" +
                "2- Name: Bathroom, House Floor: 1, Dimensions - Height: 5.2, Dimensions - Length: 3.7, Dimensions - Width: 8.5" +
                "\n";

        // Act
        String result = houseGrid1.displayRoomsAttachedToHouseGrid();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void attachRoomInTheHouseGridRoomListTest() {
        // Arrange
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        SensorList list = new SensorList();
        Room room = new Room(roomName, houseFloor1, dimensions1, list);
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        grid.attachRoomInTheHouseGridRoomList(room);

        // Act
        boolean result = grid.getmRoomsConnectedToHouseGrid().getmRoomList().contains(room);

        // Assert
        assertTrue(result);
    }
}
