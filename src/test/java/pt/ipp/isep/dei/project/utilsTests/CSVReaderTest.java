package pt.ipp.isep.dei.project.utilsTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.utils.CSVReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVReaderTest {

    @Test
    public void parseLineTest() {
        // Arrange
        String line = "TT12346,2018-12-30T02:00:00+00:00,14.0";

        // Act
        List<String> result = CSVReader.parseLine(line);

        // Assert
        assertEquals(3, result.size());
        assertEquals("TT12346", result.get(0));
        assertEquals("2018-12-30T02:00:00+00:00", result.get(1));
        assertEquals("14.0", result.get(2));
    }

    @Test
    public void parseLineEmptyTest() {
        // Arrange
        String line = "";

        // Act
        List<String> result = CSVReader.parseLine(line);

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    public void parseLineNullTest() {
        // Arrange
        String line = null;

        // Act
        List<String> result = CSVReader.parseLine(line);

        // Assert
        assertEquals(0, result.size());
    }
}