package pt.ipp.isep.dei.project.modelTests;
/*
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSource;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class PowerSourceTypeTest {

    @Test
    public void testHashCode() {
        //Arrange
        String powerSourceTypeName1 = "public electric grid";

        PowerSourceType powerSourceType1 = new PowerSourceType(powerSourceTypeName1);

        int expectedResult = Objects.hash(powerSourceTypeName1);
        // Act
        int result = powerSourceType1.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }

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

    @Test
    public void testingEqualsMethodWithDifferentObjectsNegativeTest(){
        //Arrange
        String type1 = "Battery";
        String powerSourceName = "Wind Generator";
        PowerSourceType powerSourceType = new PowerSourceType(type1);
        PowerSource powerSource = new PowerSource(powerSourceName,powerSourceType);

        //Act
        boolean result = powerSourceType.equals(powerSource);

        //Assert
        assertFalse(result);
    }
}
*/