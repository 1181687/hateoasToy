package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReadingMapperTest {
    private ReadingDTO readingDTO;
    private Reading reading;

    /**
     * Method that initializes some attributes of this test class to simplify all tests.
     */
    @BeforeEach
    void StartUp() {
        // ReadingDTO
        readingDTO = ReadingMapper.newReadingDTO();
        LocalDateTime dateTime = LocalDateTime.of(2019, 3, 11, 0, 0, 0);
        readingDTO.setDateTime(dateTime);
        readingDTO.setValue(10);

        // Reading
        reading = new Reading(10, dateTime);
    }

    /**
     * Test that tries to create a Reading based on a ReadingDTO, which results in a new Reading with the information
     * contained by the ReadingDTO.
     */
    @Test
    void testMapToEntity_tryingToCreateBasedOnAReadingDTO_ShouldReturnTrue() {
        // Arrange
        Reading reading = new Reading(readingDTO.getValue(), readingDTO.getDateTime());

        // Act
        boolean result = ReadingMapper.mapToEntity(readingDTO).equals(reading);

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to create a Reading based on a null Object, which results in a non creation.
     */
    @Test
    void testMapToEntity_tryingToCreateBasedOnANullObject_ShouldReturnNull() {
        // Act
        Reading result = ReadingMapper.mapToEntity(null);

        // Assert
        assertNull(result);
    }

    /**
     * Test that tries to create a ReadingDTO based on a Reading, which results in a new ReadingDTO with the information
     * contained by the Reading.
     */
    @Test
    void testMapToDTO_tryingToCreateBasedOnAReading_ShouldReturnTrue() {
        // Assert
        ReadingDTO dto = ReadingMapper.mapToDTO(reading);
        Reading reading1 = ReadingMapper.mapToEntity(dto);

        // Act
        boolean result = reading1.equals(reading);

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to create a ReadingDTO based on a null Object, which results in a non creation.
     */
    @Test
    void testMapToDTO_tryingToCreateBasedOnANullObject_ShouldReturnNull() {
        // Act
        ReadingDTO result = ReadingMapper.mapToDTO(null);

        // Assert
        assertNull(result);
    }
}