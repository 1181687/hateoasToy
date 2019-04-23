package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSource;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceId;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeId;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class PowerSourceTypeTest {

    /*@Test
    public void testHashCode() {
        //Arrange
        String powerSourceTypeName1 = "public electric grid";

        PowerSourceTypeId powerSourceTypeId = new PowerSourceTypeId(powerSourceTypeName1);

        PowerSourceType powerSourceType = new PowerSourceType(powerSourceTypeId);

        int expectedResult = Objects.hash(powerSourceType);
        // Act
        int result = powerSourceType.hashCode();

        // Assert
        assertEquals(expectedResult, result);
    }*/

    @Test
    public void testingEqualsMethodPositiveTest(){
        //Arrange
        PowerSourceTypeId powerSourceTypeId = new PowerSourceTypeId("Battery");
        PowerSourceTypeId powerSourceTypeId2 = new PowerSourceTypeId("Battery");
        PowerSourceType powerSourceType1 = new PowerSourceType(powerSourceTypeId);
        PowerSourceType powerSourceType2 = new PowerSourceType(powerSourceTypeId2);

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
        PowerSourceTypeId powerSourceTypeId = new PowerSourceTypeId(type1);
        PowerSourceTypeId powerSourceTypeId2 = new PowerSourceTypeId(type2);
        PowerSourceType powerSourceType1 = new PowerSourceType(powerSourceTypeId);
        PowerSourceType powerSourceType2 = new PowerSourceType(powerSourceTypeId2);

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
        PowerSourceId powerSourceId = new PowerSourceId(powerSourceName);
        PowerSourceTypeId powerSourceTypeId = new PowerSourceTypeId(type1);
        PowerSourceType powerSourceType = new PowerSourceType(powerSourceTypeId);
        HouseGridId gridId = new HouseGridId("HG1");
        PowerSource powerSource = new PowerSource(powerSourceId,powerSourceTypeId,gridId);

        //Act
        boolean result = powerSourceType.equals(powerSource);

        //Assert
        assertFalse(result);
    }
}