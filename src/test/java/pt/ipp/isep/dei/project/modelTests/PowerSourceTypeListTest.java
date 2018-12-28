package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.PowerSourceType;
import pt.ipp.isep.dei.project.model.PowerSourceTypeList;

import static org.junit.jupiter.api.Assertions.*;

public class PowerSourceTypeListTest {

    @Test
    public void testAddPowerSourceTypeToPowerSourceTypeListPowerSourceIsAddedTest() {

        //Arrange
        String powerSourceTypeName1 = "Battery";
        String powerSourceTypeName2 = "Wind Generator";

        PowerSourceType type1 = new PowerSourceType(powerSourceTypeName1);
        PowerSourceType type2 = new PowerSourceType(powerSourceTypeName2);

        PowerSourceTypeList list = new PowerSourceTypeList();
        list.addPowerSourceTypeToPowerSourceTypeList(type1);

        //Act
        boolean result = list.addPowerSourceTypeToPowerSourceTypeList(type2);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testAddPowerSourceTypeToPowerSourceTypeListPowerSourceIsNotAddedTest() {

        //Arrange
        String powerSourceTypeName1 = "Battery";
        String powerSourceTypeName2 = "Battery";

        PowerSourceType type1 = new PowerSourceType(powerSourceTypeName1);
        PowerSourceType type2 = new PowerSourceType(powerSourceTypeName2);

        PowerSourceTypeList list = new PowerSourceTypeList();
        list.addPowerSourceTypeToPowerSourceTypeList(type1);

        //Act
        boolean result = list.addPowerSourceTypeToPowerSourceTypeList(type2);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testDisplayPowerSourceTypeList() {
        //Arrange
        String powerSourceTypeName1 = "Battery";
        String powerSourceTypeName2 = "Wind Generator";

        PowerSourceType type1 = new PowerSourceType(powerSourceTypeName1);
        PowerSourceType type2 = new PowerSourceType(powerSourceTypeName2);

        PowerSourceTypeList list = new PowerSourceTypeList();
        list.addPowerSourceTypeToPowerSourceTypeList(type1);
        list.addPowerSourceTypeToPowerSourceTypeList(type2);

        String expectedResult =
                        "1 - Power Source Type: Battery\n" +
                        "2 - Power Source Type: Wind Generator\n";

        //Act
        String result = list.displayPowerSourceTypeList();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetPowerSourceTypeFromASpecificPositionInTheListGetFirstPositionTest(){

        //Arrange
        String powerSourceTypeName1 = "Battery";
        String powerSourceTypeName2 = "Wind Generator";

        PowerSourceType type1 = new PowerSourceType(powerSourceTypeName1);
        PowerSourceType type2 = new PowerSourceType(powerSourceTypeName2);

        PowerSourceTypeList list = new PowerSourceTypeList();
        list.addPowerSourceTypeToPowerSourceTypeList(type1);
        list.addPowerSourceTypeToPowerSourceTypeList(type2);

        int position = 0;
        PowerSourceType expectedResult = type1;

        //Act
        PowerSourceType result = list.getPowerSourceTypeFromASpecificPositionInTheList(position);
    }

    @Test
    public void testGetPowerSourceTypeFromASpecificPositionInTheListGetLastPositionTest(){

        //Arrange
        String powerSourceTypeName1 = "Battery";
        String powerSourceTypeName2 = "Wind Generator";

        PowerSourceType type1 = new PowerSourceType(powerSourceTypeName1);
        PowerSourceType type2 = new PowerSourceType(powerSourceTypeName2);

        PowerSourceTypeList list = new PowerSourceTypeList();
        list.addPowerSourceTypeToPowerSourceTypeList(type1);
        list.addPowerSourceTypeToPowerSourceTypeList(type2);

        int position = 1;
        PowerSourceType expectedResult = type2;

        //Act
        PowerSourceType result = list.getPowerSourceTypeFromASpecificPositionInTheList(position);
    }
}
