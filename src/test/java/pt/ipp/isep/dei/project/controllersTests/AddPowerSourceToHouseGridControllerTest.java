package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.AddPowerSourceToHouseGridController;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.PowerSourceType;
import pt.ipp.isep.dei.project.model.PowerSourceTypeList;

import static org.junit.jupiter.api.Assertions.*;

class AddPowerSourceToHouseGridControllerTest {

    @Test
    public void testCheckIfHouseGridListIsEmptyPositive() {

        // Arrange
        HouseGridList houseGridList = new HouseGridList();

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType);

        AddPowerSourceToHouseGridController addPowerSourceToHouseGridController = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);

        // Act
        boolean result = addPowerSourceToHouseGridController.checkIfHouseGridListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testCheckIfHouseGridListIsEmptyNegative() {

        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType);

        AddPowerSourceToHouseGridController addPowerSourceToHouseGridController = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);

        // Act
        boolean result = addPowerSourceToHouseGridController.checkIfHouseGridListIsEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetHouseGridListContentPositiveTest() {

        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType);


        AddPowerSourceToHouseGridController addPowerSourceToHouseGridController = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);

        String expectedResult = "1 - Name: hgname1\n";

        // Act
        String result = addPowerSourceToHouseGridController.getHouseGridListToString();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHouseGridListLength(){
        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType);

        AddPowerSourceToHouseGridController addPowerSourceToHouseGridController = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);

        int expectedResult=1;
        //Act
        int result = addPowerSourceToHouseGridController.houseGridListLength();

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testPowerSourceTypeListLength(){
        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType);

        AddPowerSourceToHouseGridController addPowerSourceToHouseGridController = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);

        int expectedResult=1;
        //Act
        int result = addPowerSourceToHouseGridController.getPowerSourceTypeListSize();

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testAddPowerSourceToHouseGridPositiveTest() {
        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType);

        String powerSourceName = "ps1";

        int position = 0;
        int positionOfPowerSource = 0;

        AddPowerSourceToHouseGridController controller = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeByPosition(positionOfPowerSource);

        //Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid(powerSourceName);

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
        houseGridList.getmHouseGridsList().add(houseGrid1);
        houseGridList.getmHouseGridsList().add(houseGrid2);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType);

        String powerSourceName = "ps1";

        int position = 1;
        int positionOfPowerSource = 0;

        AddPowerSourceToHouseGridController controller = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeByPosition(positionOfPowerSource);

        //Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid(powerSourceName);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testAddPowerSourceToHouseGridAddingExistingPowerSourceNegativeTest() {

        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType);

        String powerSourceName1 = "ps1";
        String powerSourceName2 = "ps1";

        int position = 0;

        AddPowerSourceToHouseGridController controller = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeByPosition(position);
        controller.createAndAddPowerSourceToHouseGrid(powerSourceName1);

        //Act
        boolean result = controller.createAndAddPowerSourceToHouseGrid(powerSourceName2);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testingDisplayPowerSourceTypeListPositiveTest() {

        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType);

        AddPowerSourceToHouseGridController controller = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);

        String expectedResult = "1 - Power Source Type: public electric grid\n";

        //Act
        String result = controller.getPowerSourceTypeListContent();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testListPowerSourcesConnectedToHouseGrid(){
        //Arrange
        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType);

        String powerSourceName1 = "ps1";

        int position = 0;

        AddPowerSourceToHouseGridController controller = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeByPosition(position);
        controller.createAndAddPowerSourceToHouseGrid(powerSourceName1);

        String expectedResult= "1- ps1\n";
        //Act
        String result=controller.listPowerSourcesConnectedToHouseGrid();
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testGetHouseGridName(){
        //Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmHouseGridsList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType);

        int position = 0;

        AddPowerSourceToHouseGridController controller = new AddPowerSourceToHouseGridController(houseGridList, powerSourceTypeList);
        controller.getHouseGridFromListByPosition(position);
        controller.getPowerSourceTypeByPosition(position);

        String expectedResult = "hgname1";

        //Act
        String result = controller.getHouseGridName();

        //Assert
        assertEquals(expectedResult,result);
    }

}