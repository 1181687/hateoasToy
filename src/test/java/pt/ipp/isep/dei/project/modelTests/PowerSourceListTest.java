package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pt.ipp.isep.dei.project.model.PowerSource;
import pt.ipp.isep.dei.project.model.PowerSourceList;
import pt.ipp.isep.dei.project.model.PowerSourceType;
import pt.ipp.isep.dei.project.model.PowerSourceTypeList;

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
        powerSourceList.addPowerSourceToList(powerSource1);

        //Act
        boolean result = powerSourceList.addPowerSourceToList(powerSource2);

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
        powerSourceList.addPowerSourceToList(powerSource1);

        //Act
        boolean result = powerSourceList.addPowerSourceToList(powerSource2);

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

}
