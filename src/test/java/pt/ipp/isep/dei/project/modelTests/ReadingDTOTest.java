package pt.ipp.isep.dei.project.modelTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReadingDTOTest {
    private ReadingDTO readingDTO;


    /**
     * This method pretends to initialize some attributes of this test class to simplifying all tests.
     */
    @BeforeEach
    void StartUp() {
        this.readingDTO = new ReadingDTO();

        readingDTO.setId("reading");
        readingDTO.setValue(10.0);
        readingDTO.setUnits("C");
        LocalDateTime dateTime = LocalDateTime.of(2019, 3, 11, 0, 0, 0);
        readingDTO.setDateTime(dateTime);


    }

    /**
     * Test that tries to get the value of the Reading DTO, which is expected to be 10.0.
     */
    @Test
    void testGetValue_whenTheValueIsSetTo10_ShouldReturn10() {
        // Arrange
        readingDTO.setValue(10);
        Double expectedResult = 10.0;

        // Act
        Double result = readingDTO.getValue();

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to get the date time of the Reading DTO, which is expected to be 2019/03/11 00h00h00.
     */
    @Test
    void testGetDateTime_whenTheDateTimeIsSetToAValidDate_ShouldReturnTheDateSet() {
        // Arrange
        LocalDateTime dateTime = LocalDateTime.of(2019, 3, 11, 0, 0, 0);
        readingDTO.setDateTime(dateTime);
        LocalDateTime expectedResult = dateTime;

        // Act
        LocalDateTime result = readingDTO.getDateTime();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHashcode_Reading_NotEquals() {
        //Arrange
        ReadingDTO testReading = ReadingMapper.newReadingDTO();
        testReading.setId("reading1");

        int expectedResult = testReading.hashCode();

        //Act
        int result = readingDTO.hashCode();

        //Assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    public void testHashcode_ReadingDTO_Equals() {
        //Arrange
        ReadingDTO testReading = ReadingMapper.newReadingDTO();
        testReading.setValue(10);
        LocalDateTime dateTime = LocalDateTime.of(2019, 3, 11, 0, 0, 0);
        testReading.setDateTime(dateTime);

        int expectedResult = testReading.hashCode();
        //Act
        int result = readingDTO.hashCode();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEquals_NotAReadingDTO_Object_False() {
        //Arrange
        GeoAreaSensorDTO expectedResult = GeoAreaSensorMapper.newSensorDTO();
        //Act
        boolean result = readingDTO.equals(expectedResult);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testEquals_id_True() {
        //Arrange
        ReadingDTO testReading = readingDTO;
        //Act
        ReadingDTO result = readingDTO;
        //Assert
        assertEquals(testReading, result);
    }

    @Test
    public void testEquals_id_False() {
        //Arrange
        ReadingDTO testReading = ReadingMapper.newReadingDTO();
        testReading.setId("reading2");

        //Act
        ReadingDTO result = readingDTO;
        //Assert
        assertNotEquals(testReading, result);
    }

}