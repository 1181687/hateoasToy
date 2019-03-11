package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadingDTOTest {
    private ReadingDTO readingDTO;

    @BeforeEach
    void StartUp() {
        readingDTO = ReadingMapper.newReadingDTO();
    }

    @Test
    void getValueTest() {
        // Arrange
        readingDTO.setValue(10);
        Double expectedResult = 10.0;

        // Act
        Double result = readingDTO.getValue();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getDateTimeTest() {
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