package pt.ipp.isep.dei.project.utilsTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.utils.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

class CSVReaderTest {
    private CSVReader csvReader = new CSVReader();
    private File file;

    @BeforeEach
    void StartUp() {
        String path = "/Users/luisdealmeida/IdeaProjects/project_g3/datasets/csv/DataSet_sp05_SensorData.csv";
        file = new File(path);
    }

    /**
     * Test that tries to read a valid file (with valid information)
     * and see if the result of the importation (List of Object)
     */
    @Test
    void testReadFile_tryingToSeeIfTheInfoIsImported_ShouldReturnSuccessfulResults() {
        // Arrange
        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setID("TT12346");
        LocalDateTime dateTime = LocalDateTime.of(2018, 12, 31, 2, 0, 0);
        readingDTO.setDateTime(dateTime);
        readingDTO.setValue(13.8);
        readingDTO.setUnits("C");

        Reading reading = ReadingMapper.mapToEntity(readingDTO);

        // Act
        List<Object> result;
        try {
            result = csvReader.readFile(file);
        } catch (FileNotFoundException e) {
            result = null;
        }

        Reading reading1 = ReadingMapper.mapToEntity((ReadingDTO) result.get(4));

        // Assert
        assertEquals(61, result.size());
        assertEquals(reading, reading1);
    }
}