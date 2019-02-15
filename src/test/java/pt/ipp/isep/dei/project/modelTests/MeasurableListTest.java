package pt.ipp.isep.dei.project.modelTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;
import static org.junit.jupiter.api.Assertions.*;

class MeasurableListTest {

    private Device dev1;
    private Device dev2;
    private Device dev3;
    private Room room1;
    private Room room2;
    private MeasurableList mList;

    @BeforeEach
    public void StartUp() {

        Dimension dim = new Dimension(3, 3.5, 3.5);
        this.room1 = new Room("Room1", 2, dim);
        this.room2 = new Room("Room2", 2, dim);

        FridgeType fridgeType = new FridgeType();
        this.dev1 = fridgeType.createDevice("FridgeAriston", room1);
        dev1.setAttributesDevType("Nominal Power",500);

        WashingMachineType washingMachineType = new WashingMachineType();
        this.dev2 = washingMachineType.createDevice("Washing Machine", room1);
        dev2.setAttributesDevType("Nominal Power",250);

        DishWasherType dishWasherType = new DishWasherType();
        this.dev3 = dishWasherType.createDevice("Dishwasher", room2);
        dev3.setAttributesDevType("Nominal Power",250);

        this.mList = new MeasurableList();
    }


    @Test
    void getNominalPower() {
        // Arrange
        mList.addMeasurable(dev1);
        mList.addMeasurable(room2);

        double expectedResult = 750;

        // Act
        double result = mList.getNominalPower();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfMeasurableObjIsInListTrue() {
        // Arrange
        mList.addMeasurable(dev1);
        mList.addMeasurable(room2);

        // act
        boolean result = mList.checkIfMeasurableObjIsInList(dev1);

        // assert
        assertTrue(result);
    }

    @Test
    void checkIfMeasurableObjIsInListFalse() {
        // Arrange
        mList.addMeasurable(room2);

        // act
        boolean result = mList.checkIfMeasurableObjIsInList(dev1);

        // assert
        assertFalse(result);
    }

    @Test
    void testGetListToString() {
        // Arrange
        mList.addMeasurable(room2);
        mList.addMeasurable(dev1);

        String expectedResult = "Room: Room2\nDevice: FridgeAriston, located in room: Room1\n";

        // act
        String result = mList.getListToString();

        // assert
        assertEquals(expectedResult, result);
    }
}