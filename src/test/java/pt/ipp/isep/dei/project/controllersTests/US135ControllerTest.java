package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import pt.ipp.isep.dei.project.controllers.US135Controller;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.PowerSource;
import pt.ipp.isep.dei.project.model.PowerSourceType;

import static org.junit.jupiter.api.Assertions.*;

class US135ControllerTest {

    @Test
    void testCheckIfHouseGridListIsEmptyPositive () {

        // Arrange
        HouseGridList houseGridList = new HouseGridList();

        String powerSourceName1 = "ps1";
        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        boolean powerSourceTypeIsRechargeable = false;

        PowerSource powerSource = new PowerSource(powerSourceName1, powerSourceType, powerSourceTypeIsRechargeable);

        US135Controller us135Controller = new US135Controller(houseGridList, powerSource);

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

        String powerSourceName1 = "ps1";
        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        boolean powerSourceTypeIsRechargeable = false;

        PowerSource powerSource = new PowerSource(powerSourceName1, powerSourceType, powerSourceTypeIsRechargeable);

        US135Controller us135Controller = new US135Controller(houseGridList, powerSource);

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
        boolean powerSourceTypeIsRechargeable = false;

        String powerSourceName1 = "Power Source 1";
        PowerSource powerSource = new PowerSource(powerSourceName1, powerSourceType, powerSourceTypeIsRechargeable);

        US135Controller us135Controller = new US135Controller(houseGridList, powerSource);

        String expectedResult = "1 - Name: hgname1\n";

        // Act
        String result = us135Controller.getHouseGridListContent();
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetHouseGridFromListByPosition () {
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        String powerSourceName1 = "Power Source 1";
        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        boolean powerSourceTypeIsRechargeable = false;

        PowerSource powerSource = new PowerSource(powerSourceName1, powerSourceType, powerSourceTypeIsRechargeable);

        US135Controller us135Controller = new US135Controller(houseGridList, powerSource);

        int posicao = 0;

        // Act
        HouseGrid resultado = us135Controller.getHouseGridFromListByPosition(posicao);

        // Assert
        assertEquals(houseGrid1, resultado);
    }
}