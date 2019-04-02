package pt.ipp.isep.dei.project.utilsTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.utils.CSVReader;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CSVReaderTest {
    private CSVReader csvReader = new CSVReader();
    private File file;

    @BeforeEach
    public void StartUp() {
        String path = "datasets/csv/DataSet_sp05_SensorData.csv";
        file = new File(path);
    }

    /**
     * Test that tries to read a valid file (with valid information) and see if the result of the
     * importation (List of Object) has the expected size and if it contains a certain Reading.
     */
    @org.junit.jupiter.api.Test
    public void testReadFile_withAFileWithAllTheInformationValid_ShouldReturnSuccessfulResults() {
        // Arrange
        String path = "datasets/csv/DataSet_sp05_SensorData.csv";
        file = new File(path);

        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setId("TT12346");
        LocalDateTime dateTime = LocalDateTime.of(2018, 12, 31, 2, 0, 0);
        readingDTO.setDateTime(dateTime);
        readingDTO.setValue(13.8);
        readingDTO.setUnits("C");

        Reading reading = ReadingMapper.mapToEntity(readingDTO);

        // Act
        List<Object> result = csvReader.readFile(file);

        Reading reading1 = ReadingMapper.mapToEntity((ReadingDTO) result.get(4));

        // Assert
        assertEquals(61, result.size());
        assertEquals(reading, reading1);
    }

    /**
     * Test that tries to read an empty file, which returns null.
     */
    @org.junit.jupiter.api.Test
    public void testReadFile_withEmptyFile_ShouldReturnNull() {
        // Arrange
        String path = "datasets/csv/DataSet_sp05_SensorData_empty.csv";
        file = new File(path);

        // Act
        boolean result = csvReader.readFile(file).isEmpty();

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to read an empty file, which returns null.
     */
    @Test
    public void testReadFile_withHalfEmptyFile_ShouldReturnTheCorrespondingNumberOfImportedReadings() {
        // Arrange
        String path = "datasets/csv/DataSet_sp05_SensorData_halfEmpty.csv";
        file = new File(path);

        // Act
        List<Object> result = csvReader.readFile(file);

        // Assert
        assertEquals(11, result.size());
    }

    /**
     * Test that tries to read an inexistent file, which returns null.
     */
    @Test
    public void testCreateScanner_InexistentFile_ShouldReturnNull() {
        // Arrange
        String path = "rfg.csv";
        file = new File(path);

        // Act
        Scanner result = csvReader.createScanner(file);

        // Assert
        assertEquals(null, result);
    }

    @Test
    public void testReadFile_NullObject_ShouldReturnNull() {
        // Arrange
        String path = "rfg.csv";
        file = new File(path);

        // Act
        List<Object> result = csvReader.readFile(file);

        // Assert
        assertEquals(Collections.emptyList(), result);
    }
}