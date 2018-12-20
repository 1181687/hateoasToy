package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.PowerSourceType;
import static org.junit.jupiter.api.Assertions.*;

public class PowerSourceTypeTest {

    @Test
    public void testingEqualsMethodPositiveTest(){
        //Arrange
        String type1 = "Battery";
        String type2 = "Battery";
        PowerSourceType powerSourceType1 = new PowerSourceType(type1);
        PowerSourceType powerSourceType2 = new PowerSourceType(type2);
        //Act
        boolean result = powerSourceType1.equals(powerSourceType2);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testingEqualsMethodNegativeTest(){
        //Arrange
        String type1 = "Battery";
        String type2 = "Wind Generator";
        PowerSourceType powerSourceType1 = new PowerSourceType(type1);
        PowerSourceType powerSourceType2 = new PowerSourceType(type2);
        //Act
        boolean result = powerSourceType1.equals(powerSourceType2);
        //Assert
        assertFalse(result);
    }
}
