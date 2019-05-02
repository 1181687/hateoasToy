package pt.ipp.isep.dei.project.utilsTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.utils.XMLReader;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class XMLReaderTest {
    private XMLReader XMLReader = new XMLReader();

    /**
     * readJSON File receives a DTO and transforms it
     */
    @Test
    public void testReadXMLFileToList_geoAreaDTO() {
        // arrange
        String path = "datasets/geoAreas/xml/XMLfile_GA.xml";
        File file = new File(path);

        // geograhical area list
        List<Object> resultXML = XMLReader.readFile(file);
        Object isepObj = resultXML.get(0);
        GeographicalAreaDTO isep = (GeographicalAreaDTO) isepObj;
        Object portoObj = resultXML.get(1);
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
        assertEquals("rainfall", sensorType);
        assertEquals("l/m2", sensorUnits);
        assertEquals(41.17923, latitude, 0.001);
        assertEquals(-8.606409, longitude, 0.001);
        assertEquals(125, altitude, 0.001);
    }

    @Test
    public void testReadXMLFileToListReadings_readingDTO() {
        // arrange
        String path = "datasets/sensorReadings/xml/XMLfile_Readings_twoReadings.xml";
        File file = new File(path);

        // reading list
        List<Object> resultXML = XMLReader.readFile(file);
        Object readingObj1 = resultXML.get(0);
        ReadingDTO reading1 = (ReadingDTO) readingObj1;
        Object readingObj2 = resultXML.get(1);
        ReadingDTO reading2 = (ReadingDTO) readingObj2;

        // readings
        String id1 = reading1.getId();
        LocalDateTime readingDate1 = reading1.getDateTime();
        Double value1 = reading1.getValue();
        String sensorUnits1 = reading1.getUnits();

        String id2 = reading2.getId();
        LocalDate readingDate2 = reading2.getDateTime().toLocalDate();
        Double value2 = reading2.getValue();
        String sensorUnits2 = reading2.getUnits();

        // result
        List<String> result = new ArrayList<>();
        result.add(id1);
        result.add(id2);

        // expected result
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("TT12346");
        expectedResult.add("RF12345");

        // assert
        assertEquals(expectedResult, result);
        assertEquals("TT12346", id1);
        assertEquals(LocalDateTime.of(2018, 12, 30, 2, 00), readingDate1);
        assertEquals(57.2, value1, 0.0001);
        assertEquals("F", sensorUnits1);

        assertEquals(expectedResult, result);
        assertEquals("RF12345", id2);
        assertEquals(LocalDate.of(2019, 1, 6), readingDate2);
        assertEquals(2.5, value2, 0.0001);
        assertEquals("mm", sensorUnits2);
    }

    @Test
    public void getTypeNameTest() {
        //Arrange
        String path = "rfg.xml";

        String expectedResult = "xml";

        // Act
        String result = XMLReader.getTypeName();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testReadFile_NullObject_ShouldReturnNull() {
        // Arrange
        String path = "rfg.xml";
        File file = new File(path);

        List<Object> expectedResult = null;

        // Act
        List<Object> result = XMLReader.readFile(file);

        // Assert
        assertEquals(expectedResult, result);
    }
}


