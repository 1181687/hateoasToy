package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;

import pt.ipp.isep.dei.project.controllers.US135Controller;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class US135ControllerTest {

    @Test
    public void testCheckIfHouseGridListIsEmptyPositive() {

        // Arrange
        HouseGridList houseGridList = new HouseGridList();

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        US135Controller us135Controller = new US135Controller(houseGridList, powerSourceTypeList);

        // Act
        boolean result = us135Controller.checkIfHouseGridListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testCheckIfHouseGridListIsEmptyNegative() {

        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        US135Controller us135Controller = new US135Controller(houseGridList, powerSourceTypeList);

        // Act
        boolean result = us135Controller.checkIfHouseGridListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetHouseGridListContentPositiveTest() {

        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);


        US135Controller us135Controller = new US135Controller(houseGridList, powerSourceTypeList);

        String expectedResult = "1 - Name: hgname1\n";

        // Act
        String result = us135Controller.getHouseGridListContent();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHouseGridListLength(){
        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        US135Controller us135Controller = new US135Controller(houseGridList, powerSourceTypeList);

        int expectedResult=1;
        //Act
        int result = us135Controller.houseGridListLength();

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testPowerSourceTypeListLength(){
        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        US135Controller us135Controller = new US135Controller(houseGridList, powerSourceTypeList);

        int expectedResult=1;
        //Act
        int result = us135Controller.powerSourceTypeListLength();

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testAddPowerSourceToHouseGridPositiveTest() {
        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        String powerSourceName = "ps1";
        Boolean isRechargeable = false;

        int position = 0;
        int positionOfPowerSource = 0;

        US135Controller controller = new US135Controller(houseGridList, powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeFromListByPosition(positionOfPowerSource);

        //Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid(powerSourceName, isRechargeable);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testAddPowerSourceToHouseGridWithMoreThanAHouseGridPositiveTest() {
        //Arrange
        String houseGridName1 = "house grid 1";
        String houseGridName2 = "house grid 2";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName1);
        HouseGrid houseGrid2 = new HouseGrid(houseGridName2);

        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);
        houseGridList.getmList().add(houseGrid2);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        String powerSourceName = "ps1";
        Boolean isRechargeable = false;

        int position = 1;
        int positionOfPowerSource = 0;

        US135Controller controller = new US135Controller(houseGridList, powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeFromListByPosition(positionOfPowerSource);

        //Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid(powerSourceName, isRechargeable);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testAddPowerSourceToHouseGridAddingExistingPowerSourceNegativeTest() {

        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        String powerSourceName1 = "ps1";
        String powerSourceName2 = "ps1";
        boolean isRechargeable = false;

        int position = 0;

        US135Controller controller = new US135Controller(houseGridList, powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeFromListByPosition(position);
        controller.createAndAddPowerSourceToHouseGrid(powerSourceName1, isRechargeable);

        //Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid(powerSourceName2, isRechargeable);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testingDisplayPowerSourceTypeListPositiveTest() {

        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        US135Controller controller = new US135Controller(houseGridList, powerSourceTypeList);

        String expectedResult = "1 - Power Source Type: public electric grid\n";

        //Act
        String result = controller.displayPowerSourceTypeList();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testingChooseRechargeableOption() {
        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        int position = 0;

        US135Controller controller = new US135Controller(houseGridList, powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeFromListByPosition(position);

        String expectedResult = "1 - Yes\n" + "2 - No\n";

        //Act
        String result = controller.chooseRechargeableOption();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testingIsRechargeablePositiveTest() {

        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        US135Controller controller = new US135Controller(houseGridList, powerSourceTypeList);

        int option = 1;

        //Act
        boolean result = controller.isRechargeable(option);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testingIsRechargeableNegativeTest() {

        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        US135Controller controller = new US135Controller(houseGridList, powerSourceTypeList);

        int option = 2;

        //Act
        boolean result = controller.isRechargeable(option);

        //Assert
        assertFalse(result);
    }
}