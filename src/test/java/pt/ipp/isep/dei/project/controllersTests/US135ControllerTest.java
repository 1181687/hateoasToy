package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.US135Controller;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.PowerSource;
import pt.ipp.isep.dei.project.model.PowerSourceType;

import static org.junit.jupiter.api.Assertions.*;

class US135ControllerTest {

    @Test
    void TestCheckIfHouseGridListIsEmptyPositive () {

        // Arrange
        HouseGridList houseGridList = new HouseGridList();

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        boolean powerSourceTypeIsRechargeable = false;

        PowerSource powerSource = new PowerSource(powerSourceType, powerSourceTypeIsRechargeable);

        US135Controller us135Controller = new US135Controller(houseGridList, powerSource);

        // Act
        boolean result = us135Controller.checkIfHouseGridListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    void TestCheckIfHouseGridListIsEmptyNegative () {

        // Arrange
        String houseGridName = "hgname1";
        HouseGrid houseGrid1 = new HouseGrid(houseGridName);
        HouseGridList houseGridList = new HouseGridList();
        houseGridList.getmList().add(houseGrid1);

        PowerSourceType powerSourceType = new PowerSourceType("public electric grid");
        boolean powerSourceTypeIsRechargeable = false;

        PowerSource powerSource = new PowerSource(powerSourceType, powerSourceTypeIsRechargeable);

        US135Controller us135Controller = new US135Controller(houseGridList, powerSource);

        // Act
        boolean result = us135Controller.checkIfHouseGridListIsEmpty();

        // Assert
        assertFalse(result);
    }
}