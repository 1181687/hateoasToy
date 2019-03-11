package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReadingMapperTest {
    private ReadingDTO readingDTO;

    @BeforeEach
    void StartUp() {
        readingDTO = ReadingMapper.newReadingDTO();
        LocalDateTime dateTime = LocalDateTime.of(2019, 3, 11, 0, 0, 0);
        readingDTO.setDateTime(dateTime);
        readingDTO.setValue(10);
    }

    @Test
    void mapToDTOTest() {
        // Act
        boolean result = ReadingMapper.mapToDTO(readingDTO).equals(new Reading(readingDTO.getValue(), readingDTO.getDateTime()));

        // Assert
        assertTrue(result);
    }
}