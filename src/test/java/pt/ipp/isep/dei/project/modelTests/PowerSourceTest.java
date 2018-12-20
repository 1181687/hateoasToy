package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;
import static org.junit.jupiter.api.Assertions.*;

public class PowerSourceTest {

    @Test
    public void testingEqualsWithNonRechargeablePowerSourcesPositiveTest(){
        //Arrange
        PowerSourceType powerSourceType1 = new PowerSourceType("public electric grid");
        boolean powerSourceType1IsRechargeable = false;
        PowerSourceType powerSourceType2 = new PowerSourceType("public electric grid");
        boolean powerSourceType2IsRechargeable = false;

        PowerSource powerSource1 = new PowerSource(powerSourceType1,powerSourceType1IsRechargeable);
        PowerSource powerSource2 = new PowerSource(powerSourceType2,powerSourceType2IsRechargeable);

        //Act
        boolean result = powerSource1.equals(powerSource2);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testingEqualsWithNonRechargeablePowerSourcesNegativeTest(){
        //Arrange
        PowerSourceType powerSourceType1 = new PowerSourceType("public electric grid");
        boolean powerSourceType1IsRechargeable = false;
        PowerSourceType powerSourceType2 = new PowerSourceType("photovoltaic panels");
        boolean powerSourceType2IsRechargeable = false;

        PowerSource powerSource1 = new PowerSource(powerSourceType1,powerSourceType1IsRechargeable);
        PowerSource powerSource2 = new PowerSource(powerSourceType2,powerSourceType2IsRechargeable);

        //Act
        boolean result = powerSource1.equals(powerSource2);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testingEqualsWithRechargeablePowerSourcesPositiveTest(){
        //Arrange
        PowerSourceType powerSourceType1 = new PowerSourceType("battery");
        boolean powerSourceType1IsRechargeable = true;
        PowerSourceType powerSourceType2 = new PowerSourceType("battery");
        boolean powerSourceType2IsRechargeable = true;

        PowerSource powerSource1 = new PowerSource(powerSourceType1,powerSourceType1IsRechargeable);
        PowerSource powerSource2 = new PowerSource(powerSourceType2,powerSourceType2IsRechargeable);

        //Act
        boolean result = powerSource1.equals(powerSource2);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testingEqualsWithRechargeablePowerSourcesNegativeTest(){
        //Arrange
        PowerSourceType powerSourceType1 = new PowerSourceType("battery");
        boolean powerSourceType1IsRechargeable = true;
        PowerSourceType powerSourceType2 = new PowerSourceType("battery associated with wind generator");
        boolean powerSourceType2IsRechargeable = true;

        PowerSource powerSource1 = new PowerSource(powerSourceType1,powerSourceType1IsRechargeable);
        PowerSource powerSource2 = new PowerSource(powerSourceType2,powerSourceType2IsRechargeable);

        //Act
        boolean result = powerSource1.equals(powerSource2);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testingEqualsWithARechargeableandNonRechargeablePowerSourcesNegativeTest(){
        //Arrange
        PowerSourceType powerSourceType1 = new PowerSourceType("public electric grid");
        boolean powerSourceType1IsRechargeable = false;
        PowerSourceType powerSourceType2 = new PowerSourceType("battery");
        boolean powerSourceType2IsRechargeable = true;

        PowerSource powerSource1 = new PowerSource(powerSourceType1,powerSourceType1IsRechargeable);
        PowerSource powerSource2 = new PowerSource(powerSourceType2,powerSourceType2IsRechargeable);

        //Act
        boolean result = powerSource1.equals(powerSource2);

        //Assert
        assertFalse(result);
    }
}
