package pt.ipp.isep.dei.project.modelTests;

import org.junit.Before;
import org.junit.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;

import java.time.LocalDateTime;

import static org.junit.Assert.*;


class ReadingMapperTest {
    private ReadingDTO readingDTO;
    private Reading reading;

    /**
     * Method that initializes some attributes of this test class to simplify all tests.
     */
    @Before
    public void StartUp() {
        // ReadingDTO
        readingDTO = ReadingMapper.newReadingDTO();
        LocalDateTime dateTime = LocalDateTime.of(2019, 3, 11, 0, 0, 0);
        readingDTO.setDateTime(dateTime);
        readingDTO.setValue(10);
        readingDTO.setId("TT123");
        readingDTO.setUnits("C");

        // Reading
        reading = new Reading(10, dateTime);
    }

    /**
     * Test that tries to create a Reading based on a ReadingDTO, which results in a new Reading with the information
     * contained by the ReadingDTO.
     */
    @Test
    public void testMapToEntity_tryingToCreateBasedOnAReadingDTO_ShouldReturnTrue() {
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
    public void testMapToEntity_tryingToCreateBasedOnANullObject_ShouldReturnNull() {
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
    public void testMapToDTO_tryingToCreateBasedOnAReading_ShouldReturnTrue() {
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
    public void testMapToDTO_tryingToCreateBasedOnANullObject_ShouldReturnNull() {
        // Act
        ReadingDTO result = ReadingMapper.mapToDTO(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void testMapToDTO_id_units_tryingToTestTheSets() {
        // act
        String id = "TT123";
        String units = "C";
        double value = 14;
        LocalDateTime date = LocalDateTime.of(2019, 3, 11, 0, 0);

        ReadingDTO readingDTO = ReadingMapper.mapToDTOwithIDandUnits(id, date, value, units);

        // assert
        assertEquals(id, readingDTO.getId());
        assertEquals(units, readingDTO.getUnits());
        assertEquals(date, readingDTO.getDateTime());
        assertEquals(value, readingDTO.getValue(), 0.001);

    }
}