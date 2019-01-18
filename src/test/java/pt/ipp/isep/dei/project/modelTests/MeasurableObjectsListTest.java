package pt.ipp.isep.dei.project.modelTests;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class MeasurableObjectsListTest {


    @Test
    void getNominalPower() {
        // Arrange
        MeasurableObjectsList mList = new MeasurableObjectsList();
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room1 = new Room("Room", 2, dim);
        Room room2 = new Room("Room", 2, dim);

        ProgramList programList = new ProgramList();
        Program program = new Program("prog1", 3, 4.5);
        programList.addProgram(program);

        Fridge specFridge = new Fridge(25, 50, 5000, 500);
        WashingMachine specWashing = new WashingMachine(400, 250.0, programList);
        DishWasher specDishWasher = new DishWasher(400, 250.0, programList);
        Device dev1 = new Device("FridgeAriston", room1, specFridge);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasher", room1, specDishWasher);

        mList.addMeasurableObjToMeasurableList(dev1);
        mList.addMeasurableObjToMeasurableList(room2);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room2.addDevice(dev3);

        double expectedResult = 750;

        // Act
        double result = mList.getNominalPower();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfMeasurableObjIsInListTrue() {
        // Arrange
        MeasurableObjectsList mList = new MeasurableObjectsList();
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room1 = new Room("Room", 2, dim);
        Room room2 = new Room("Room", 2, dim);

        ProgramList programList = new ProgramList();
        Program program = new Program("prog1", 3, 4.5);
        programList.addProgram(program);

        Fridge specFridge = new Fridge(25, 50, 5000, 500);
        WashingMachine specWashing = new WashingMachine(400, 250.0, programList);
        DishWasher specDishWasher = new DishWasher(400, 250.0, programList);
        Device dev1 = new Device("FridgeAriston", room1, specFridge);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasher", room1, specDishWasher);

        mList.addMeasurableObjToMeasurableList(dev1);
        mList.addMeasurableObjToMeasurableList(room2);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room2.addDevice(dev3);

        Measurable measurable1 = new Device("FridgeAriston", room1, specFridge);

        // act
        boolean result = mList.checkIfMeasurableObjIsInList(measurable1);

        // assert
        assertTrue(result);
    }

    @Test
    void checkIfMeasurableObjIsInListFalse() {
        // Arrange
        MeasurableObjectsList mList = new MeasurableObjectsList();
        Dimensions dim = new Dimensions(3, 3.5, 3.5);
        Room room1 = new Room("Room", 2, dim);
        Room room2 = new Room("Room", 2, dim);

        ProgramList programList = new ProgramList();
        Program program = new Program("prog1", 3, 4.5);
        programList.addProgram(program);

        Fridge specFridge = new Fridge(25, 50, 5000, 500);
        WashingMachine specWashing = new WashingMachine(400, 250.0, programList);
        DishWasher specDishWasher = new DishWasher(400, 250.0, programList);
        Device dev1 = new Device("FridgeAriston", room1, specFridge);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasher", room1, specDishWasher);

        mList.addMeasurableObjToMeasurableList(room2);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room2.addDevice(dev3);

        Measurable measurable1 = new Device("FridgeAriston", room1, specFridge);

        // act
        boolean result = mList.checkIfMeasurableObjIsInList(measurable1);

        // assert
        assertFalse(result);
    }
}