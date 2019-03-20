package pt.ipp.isep.dei.project.utilsTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.utils.JSONReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONReaderTest {
    /**
     * readJSON File receives a DTO and transforms it
     */
    @Test
    public void testReadJSONFileToList_geoAreaDTO() {
        // arrange
        FileReader file;
        try {
            file = new FileReader("JSONfile.json");
        } catch (FileNotFoundException e) {
            file = null;
        }

        // geograhical area list
        List<GeographicalAreaDTO> resultJSON = JSONReader.readJSONFileToList(file);
        GeographicalAreaDTO isep = resultJSON.get(0);
        String geoA1 = resultJSON.get(0).getId();
        String geoA2 = resultJSON.get(1).getId();

        // sensor
        String sensorId = isep.getSensors().get(0).getId();
        String sensorName = isep.getSensors().get(0).getName();
        LocalDate sensorDate = isep.getSensors().get(0).getStartingDate();
        String sensorType = isep.getSensors().get(0).getSensorType();
        String sensorUnits = isep.getSensors().get(0).getUnits();

        // location
        LocationDTO location = resultJSON.get(0).getSensors().get(0).getLocation();

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
        assertEquals(41.178553, latitude);
        assertEquals(-8.608035, longitude);
        assertEquals(111, altitude);
    }
    

}