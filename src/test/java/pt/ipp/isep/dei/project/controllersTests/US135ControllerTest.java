package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;

import pt.ipp.isep.dei.project.controllers.US135Controller;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.*;

class US135ControllerTest {

    @Test
    void testCheckIfHouseGridListIsEmptyPositive () {

        // Arrange
        HouseGridList houseGridList = new HouseGridList();

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        US135Controller us135Controller = new US135Controller(houseGridList,powerSourceTypeList);

        // Act
        boolean result = us135Controller.checkIfHouseGridListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    void testCheckIfHouseGridListIsEmptyNegative () {

        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);

        US135Controller us135Controller = new US135Controller(houseGridList,powerSourceTypeList);

        // Act
        boolean result = us135Controller.checkIfHouseGridListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    void testGetHouseGridListContent () {

        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType);


        US135Controller us135Controller = new US135Controller(houseGridList,powerSourceTypeList);

        String expectedResult = "1 - Name: hgname1\n";

        // Act
        String result = us135Controller.getHouseGridListContent();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testAddPowerSourceToHouseGridPositiveTest(){
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

        US135Controller controller = new US135Controller(houseGridList,powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeFromListByPosition(positionOfPowerSource);

        //Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid(powerSourceName,isRechargeable);

        //Assert
        assertTrue(result);
    }

    @Test
    void testAddPowerSourceToHouseGridWithMoreThanAHouseGridPositiveTest(){
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

        US135Controller controller = new US135Controller(houseGridList,powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeFromListByPosition(positionOfPowerSource);

        //Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid(powerSourceName,isRechargeable);

        //Assert
        assertTrue(result);
    }
    @Test
    void testAddPowerSourceToHouseGridAddingExistingPowerSourceNegativeTest(){

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
        Boolean isRechargeable = false;

        int position = 0;

        US135Controller controller = new US135Controller(houseGridList,powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeFromListByPosition(position);
        controller.createAndAddPowerSourceToHouseGrid(powerSourceName1,isRechargeable);

        //Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid(powerSourceName2,isRechargeable);

        //Assert
        assertFalse(result);
    }
}