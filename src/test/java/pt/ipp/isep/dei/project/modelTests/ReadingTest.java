package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class ReadingTest {

    @Test
    public void testHashCode() {
        //Arrange
        LocalDateTime date = LocalDateTime.of(2018, 2, 3, 12, 30);
        Reading reading = new Reading(23.2, date);
        int expectedResult = 1;
        //Act
        int result = reading.hashCode();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void equals_WithDifferentTypeObjects_ShouldReturnFalse() {
        //Arrange
        LocalDateTime date = LocalDateTime.of(2018, 2, 3, 12, 30);
        Reading reading = new Reading(23.2, date);

        HouseGrid grid = new HouseGrid("Main Grid");
        //Act
        boolean result = reading.equals(grid);
        //Assert
        assertFalse(result);
    }

    @Test
    public void equals_WithReadingsWithDifferentDateAndSameValue_ShouldReturnFalse() {
        //Arrange
        LocalDateTime date = LocalDateTime.of(2018, 2, 3, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 2, 4, 12, 30);
        Reading reading = new Reading(23.2, date);
        Reading reading2 = new Reading(23.2, date2);
        //Act
        boolean result = reading.equals(reading2);
        //Assert
        assertFalse(result);
    }

    @Test
    public void equals_WithReadingsWithDifferentValueAndSameDate_ShouldReturnFalse() {
        //Arrange
        LocalDateTime date = LocalDateTime.of(2018, 2, 3, 12, 30);
        Reading reading = new Reading(23.2, date);
        Reading reading2 = new Reading(27.8, date);
        //Act
        boolean result = reading.equals(reading2);
        //Assert
        assertFalse(result);
    }

    @Test
    public void equals_ReadingsWithSameValueAndSameDate_ShouldReturnFalse() {
        //Arrange
        LocalDateTime date = LocalDateTime.of(2018, 2, 3, 12, 30);
        Reading reading = new Reading(23.2, date);
        Reading reading2 = new Reading(23.2, date);
        //Act
        boolean result = reading.equals(reading2);
        //Assert
        assertTrue(result);
    }

    @Test
    public void equals_ComparingSameReading_ShouldReturnTrue() {
        //Arrange
        LocalDateTime date = LocalDateTime.of(2018, 2, 3, 12, 30);
        Reading reading = new Reading(23.2, date);
        //Act
        boolean result = reading.equals(reading);
        //Assert
        assertTrue(result);
    }

}
