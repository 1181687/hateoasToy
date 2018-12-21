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


}