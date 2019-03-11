package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSource;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceList;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;

import static org.junit.jupiter.api.Assertions.*;

public class PowerSourceListTest {

    @Test
    public void testIfPowerSourceIsAddedToThePowerSourceListPositiveTest(){
        //Arrange
        String powerSourceName1 = "Power Source 1";
        String powerSourceName2 = "Power Source 2";
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        PowerSource powerSource1 = new PowerSource(powerSourceName1,powerSourceType1);
        PowerSource powerSource2 = new PowerSource(powerSourceName2,powerSourceType1);
        PowerSourceList powerSourceList = new PowerSourceList();
        powerSourceList.addPowerSource(powerSource1);

        //Act
        boolean result = powerSourceList.addPowerSource(powerSource2);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testIfPowerSourceIsAddedToThePowerSourceListNegativeTest(){

        //Arrange
        String powerSourceName1 = "Power Source 1";
        String powerSourceName2 = "Power Source 1";
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        PowerSourceType powerSourceType2 = new PowerSourceType("Battery");
        PowerSource powerSource1 = new PowerSource(powerSourceName1, powerSourceType1);
        PowerSource powerSource2 = new PowerSource(powerSourceName2, powerSourceType2);
        PowerSourceList powerSourceList = new PowerSourceList();
        powerSourceList.addPowerSource(powerSource1);

        //Act
        boolean result = powerSourceList.addPowerSource(powerSource2);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testCreateNewPowerSource(){
        //Arrange
        String name = "Power Source 1";
        PowerSourceType type = new PowerSourceType("Battery");
        PowerSourceList powerSourceList = new PowerSourceList();
        PowerSource expectedResult = new PowerSource(name,type);

        //Act
        PowerSource result = powerSourceList.createNewPowerSource(name,type);

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testListPowerSources(){
        //Arrange
        String name = "Power Source 1";
        String name2 = "Power Source 2";
        String typeName = "Battery";
        PowerSourceType type1 = new PowerSourceType(typeName);
        PowerSource powerSource1 = new PowerSource(name,type1);
        PowerSource powerSource2 = new PowerSource(name2,type1);
        PowerSourceList powerSourceList = new PowerSourceList();
        powerSourceList.addPowerSource(powerSource1);
        powerSourceList.addPowerSource(powerSource2);
        String expectedResult="1- Power Source 1\n" +
                "2- Power Source 2\n";

        //Act
        String result = powerSourceList.getPowerSourcesListToString();

        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    public void testPowerSourceNameAlreadyExistsTrue() {
        //Arrange
        String name = "Power Source 1";
        String name2 = "Power Source 2";
        String typeName = "Battery";
        PowerSourceType type1 = new PowerSourceType(typeName);
        PowerSource powerSource1 = new PowerSource(name, type1);
        PowerSource powerSource2 = new PowerSource(name2, type1);
        PowerSourceList list = new PowerSourceList();
        list.addPowerSource(powerSource1);
        list.addPowerSource(powerSource2);

        //Act
        boolean result = list.powerSourceNameAlreadyExists(name);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testPowerSourceNameAlreadyExistsFalse() {
        //Arrange
        String name = "Power Source 1";
        String name2 = "Power Source 2";
        String name3 = "Power Source 3";
        String typeName = "Battery";
        PowerSourceType type1 = new PowerSourceType(typeName);
        PowerSource powerSource1 = new PowerSource(name, type1);
        PowerSource powerSource2 = new PowerSource(name2, type1);
        PowerSourceList list = new PowerSourceList();
        list.addPowerSource(powerSource1);
        list.addPowerSource(powerSource2);

        //Act
        boolean result = list.powerSourceNameAlreadyExists(name3);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testPowerSourceNameAlreadyExistsEmptyList() {
        //Arrange
        String name = "Power Source 1";
        PowerSourceList list = new PowerSourceList();

        //Act
        boolean result = list.powerSourceNameAlreadyExists(name);

        //Assert
        assertFalse(result);
    }
}
