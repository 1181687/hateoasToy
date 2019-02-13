package pt.ipp.isep.dei.project.modelTests;


class MeasurableListTest {


 /*   @Test
    void getNominalPower() {
        // Arrange
        MeasurableList mList = new MeasurableList();
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room1 = new Room("Room", 2, dim);
        Room room2 = new Room("Room", 2, dim);

        ProgramList programList = new ProgramList();
        Program program = new Program("prog1", 3, 4.5);
        programList.addProgram(program);

        FridgeSpecs specFridgeSpecs = new FridgeSpecs(25, 50, 5000, 500);
        WashingMachineSpecs specWashing = new WashingMachineSpecs(400, 250.0, programList);
        DishWasherSpecs specDishWasherSpecs = new DishWasherSpecs(400, 250.0, programList);
        Device dev1 = new Device("FridgeAriston", room1, specFridgeSpecs);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasherSpecs", room1, specDishWasherSpecs);

        mList.addMeasurable(dev1);
        mList.addMeasurable(room2);

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
        MeasurableList mList = new MeasurableList();
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room1 = new Room("Room", 2, dim);
        Room room2 = new Room("Room", 2, dim);

        ProgramList programList = new ProgramList();
        Program program = new Program("prog1", 3, 4.5);
        programList.addProgram(program);

        FridgeSpecs specFridgeSpecs = new FridgeSpecs(25, 50, 5000, 500);
        WashingMachineSpecs specWashing = new WashingMachineSpecs(400, 250.0, programList);
        DishWasherSpecs specDishWasherSpecs = new DishWasherSpecs(400, 250.0, programList);
        Device dev1 = new Device("FridgeAriston", room1, specFridgeSpecs);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasherSpecs", room1, specDishWasherSpecs);

        mList.addMeasurable(dev1);
        mList.addMeasurable(room2);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room2.addDevice(dev3);

        Measurable measurable1 = new Device("FridgeAriston", room1, specFridgeSpecs);

        // act
        boolean result = mList.checkIfMeasurableObjIsInList(measurable1);

        // assert
        assertTrue(result);
    }

    @Test
    void checkIfMeasurableObjIsInListFalse() {
        // Arrange
        MeasurableList mList = new MeasurableList();
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room1 = new Room("Room", 2, dim);
        Room room2 = new Room("Room", 2, dim);

        ProgramList programList = new ProgramList();
        Program program = new Program("prog1", 3, 4.5);
        programList.addProgram(program);

        FridgeSpecs specFridgeSpecs = new FridgeSpecs(25, 50, 5000, 500);
        WashingMachineSpecs specWashing = new WashingMachineSpecs(400, 250.0, programList);
        DishWasherSpecs specDishWasherSpecs = new DishWasherSpecs(400, 250.0, programList);
        Device dev1 = new Device("FridgeAriston", room1, specFridgeSpecs);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasherSpecs", room1, specDishWasherSpecs);

        mList.addMeasurable(room2);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room2.addDevice(dev3);

        Measurable measurable1 = new Device("FridgeAriston", room1, specFridgeSpecs);

        // act
        boolean result = mList.checkIfMeasurableObjIsInList(measurable1);

        // assert
        assertFalse(result);
    }

    @Test
    void testGetListToString() {
        // Arrange
        MeasurableList mList = new MeasurableList();
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room1 = new Room("Room1", 2, dim);
        Room room2 = new Room("Room2", 2, dim);

        ProgramList programList = new ProgramList();
        Program program = new Program("prog1", 3, 4.5);
        programList.addProgram(program);

        FridgeSpecs specFridgeSpecs = new FridgeSpecs(25, 50, 5000, 500);
        WashingMachineSpecs specWashing = new WashingMachineSpecs(400, 250.0, programList);
        DishWasherSpecs specDishWasherSpecs = new DishWasherSpecs(400, 250.0, programList);
        Device dev1 = new Device("FridgeAriston", room1, specFridgeSpecs);
        Device dev2 = new Device("WashingMachineBosh", room1, specWashing);
        Device dev3 = new Device("DishWasherSpecs", room1, specDishWasherSpecs);

        mList.addMeasurable(dev1);
        mList.addMeasurable(room2);

        room1.addDevice(dev1);
        room1.addDevice(dev2);
        room2.addDevice(dev3);

        String expectedResult = "Room: Room2\nDevice: FridgeAriston, located in room: Room1\n";

        // act
        String result = mList.getListToString();

        // assert
        assertEquals(expectedResult, result);
    }*/
}