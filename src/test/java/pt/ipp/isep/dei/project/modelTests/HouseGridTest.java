package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HouseGridTest {

    @Test
    void TestDisplayRoomsAttachedToHouseGrid () {

        // Arrange
        Dimensions dimensionsRoom1 = new Dimensions(5.2, 3.7, 8.5);
        Room room1 = new Room("Kid's room", 1, dimensionsRoom1);
        Dimensions dimensionsRoom2 = new Dimensions(5.2, 3.7, 8.5);
        Room room2 = new Room("Bathroom", 1, dimensionsRoom2);

        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);

        houseGrid1.getmRoomsConnectedToHouseGrid().addRoom(room1);
        houseGrid1.getmRoomsConnectedToHouseGrid().addRoom(room2);

        String expectedResult =
                "1- Name: Kid's room, House Floor: 1, Dimensions - Height: 5.2, Dimensions - Length: 3.7, Dimensions - Width: 8.5\n" +
                "2- Name: Bathroom, House Floor: 1, Dimensions - Height: 5.2, Dimensions - Length: 3.7, Dimensions - Width: 8.5" +
                "\n";

        // Act
        String result = houseGrid1.getRoomsAttached();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void attachRoomInTheHouseGridRoomListTest() {
        // Arrange
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimensions dimensions1 = new Dimensions(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimensions1);
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        grid.attachRoomToTheRoomList(room);

        // Act
        boolean result = grid.getmRoomsConnectedToHouseGrid().getmRoomList().contains(room);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testListPowerSources(){
        //Arrange
        String name = "Power Source 1";
        String name2 = "Power Source 2";
        String typeName = "Battery";
        PowerSourceType type1 = new PowerSourceType(typeName);
        PowerSource powerSource1 = new PowerSource(name,type1);
        PowerSource powerSource2 = new PowerSource(name2,type1);
        HouseGrid houseGrid = new HouseGrid("House Grid1");
        houseGrid.addPowerSource(powerSource1);
        houseGrid.addPowerSource(powerSource2);
        String expectedResult="1- Power Source 1\n" +
                "2- Power Source 2\n";
        //Act
        String result=houseGrid.listPowerSources();
        //Assert
        assertEquals(expectedResult,result);
    }
}