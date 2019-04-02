package pt.ipp.isep.dei.project.modelTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadingDTOTest {
    private ReadingDTO readingDTO;

    /**
     * This method pretends to initialize some attributes of this test class to simplifying all tests.
     */
    @BeforeEach
    void StartUp() {
        readingDTO = ReadingMapper.newReadingDTO();
    }

    /**
     * Test that tries to get the value of the Reading DTO, which is expected to be 10.0.
     */
    @org.junit.jupiter.api.Test
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
}