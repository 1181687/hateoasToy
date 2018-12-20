package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pt.ipp.isep.dei.project.model.PowerSource;
import pt.ipp.isep.dei.project.model.PowerSourceList;
import pt.ipp.isep.dei.project.model.PowerSourceType;

public class PowerSourceListTest {

    @Test
    public void testIfPowerSourceIsAddedToThePowerSourceListPositiveTest(){
        //Arrange
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        boolean isRechargeable1 = true;
        PowerSource powerSource1 = new PowerSource(powerSourceType1,isRechargeable1);
        PowerSourceList powerSourceList = new PowerSourceList();

        //Act
        boolean result = powerSourceList.addPowerSourceToList(powerSource1);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testIfPowerSourceIsAddedToThePowerSourceListNegativeTest(){

        //Arrange
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        PowerSourceType powerSourceType2 = new PowerSourceType("Battery");
        boolean isRechargeable1 = true;
        boolean isRechargeable2 = true;
        PowerSource powerSource1 = new PowerSource(powerSourceType1,isRechargeable1);
        PowerSource powerSource2 = new PowerSource(powerSourceType2,isRechargeable2);
        PowerSourceList powerSourceList = new PowerSourceList();
        powerSourceList.addPowerSourceToList(powerSource1);

        //Act
        boolean result = powerSourceList.addPowerSourceToList(powerSource2);

        //Assert
        assertFalse(result);
    }

}
