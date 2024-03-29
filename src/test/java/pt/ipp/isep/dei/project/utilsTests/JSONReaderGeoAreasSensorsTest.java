package pt.ipp.isep.dei.project.utilsTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.utils.JSONReaderGeoAreasSensors;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONReaderGeoAreasSensorsTest {
    private JSONReaderGeoAreasSensors jsonReaderGeoAreasSensors = new JSONReaderGeoAreasSensors();

    /**
     * readJSON File receives a DTO and transforms it
     */
    @Test
    public void testReadJSONFileToList_geoAreaDTO() throws FileNotFoundException {
        // arrange
        String path = "datasets/geoAreas/json/JSONfile.json";
        File file = new File(path);

        // geograhical area list
        List<Object> resultJSON = jsonReaderGeoAreasSensors.readFile(file);
        Object isepObj = resultJSON.get(0);
        GeographicalAreaDTO isep = (GeographicalAreaDTO) isepObj;
        Object portoObj = resultJSON.get(1);
        GeographicalAreaDTO porto = (GeographicalAreaDTO) portoObj;

        String geoA1 = isep.getId();
        String geoA2 = porto.getId();

        // sensor
        String sensorId = isep.getSensors().get(0).getId();
        String sensorName = isep.getSensors().get(0).getName();
        LocalDate sensorDate = isep.getSensors().get(0).getStartingDate();
        String sensorType = isep.getSensors().get(0).getSensorType();
        String sensorUnits = isep.getSensors().get(0).getUnits();

        // location
        LocationDTO location = isep.getSensors().get(0).getLocation();

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double altitude = location.getElevation();

        // result
        List<String> result = new ArrayList<>();
        result.add(geoA1);
        result.add(geoA2);

        // expected result
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("ISEP");
        expectedResult.add("Porto");

        // assert
        assertEquals(expectedResult, result);
        assertEquals("ISEP", isep.getId());
        assertEquals("Campus do ISEP", isep.getDescription());
        assertEquals("RF12345", sensorId);
        assertEquals("Meteo station ISEP - rainfall", sensorName);
        assertEquals(LocalDate.of(2016, 11, 15), sensorDate);
        assertEquals("Rainfall", sensorType);
        assertEquals("l/m2", sensorUnits);
        assertEquals(41.178553, latitude, 0.001);
        assertEquals(-8.608035, longitude, 0.001);
        assertEquals(111, altitude, 0.001);
    }

    @Test
    public void testReadJSONFileToList_readingsDTO() throws FileNotFoundException {
        // arrange
        String path = "datasets/sensorReadings/json/DataSet_sprint05_SensorData.json";
        File file = new File(path);

        // geograhical area list
        List<Object> resultJSON = jsonReaderGeoAreasSensors.readFile(file);
        Object readingObj = resultJSON.get(0);
        ReadingDTO readingDTO = (ReadingDTO) readingObj;

        String geoA1 = readingDTO.getId();

        // sensor
        String sensorId = readingDTO.getId();
        double value = readingDTO.getValue();
        LocalDateTime sensorDate = readingDTO.getDateTime();
        String units = readingDTO.getUnits();

        // result
        List<String> result = new ArrayList<>();
        result.add(geoA1);

        // expected result
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("TT12346");

        // assert
        assertEquals(expectedResult, result);
        assertEquals("TT12346", sensorId);
        assertEquals(LocalDateTime.of(2018, 12, 30, 02, 00), sensorDate);
        assertEquals("C", units);
        assertEquals(14, value, 0.001);
    }

    @Test
    public void getTypeNameTest() {
        //Arrange
        String path = "rfg.json";

        String expectedResult = "json";

        // Act
        String result = jsonReaderGeoAreasSensors.getTypeName();

        // Assert
        assertEquals(expectedResult, result);
    }
/*
    @Test
    public void testReadFile_NullObject_ShouldReturnNull () {
        // Arrange
        String path = "rfg.csv";
        File file = new File(path);

        // Act
        Throwable exception = assertThrows(FileNotFoundException.class, () -> jsonReaderGeoAreasSensors.readFile(file));

        //Assert
        assertEquals("rfg.csv (No such file or directory)", exception.getMessage());
    }
*/
}