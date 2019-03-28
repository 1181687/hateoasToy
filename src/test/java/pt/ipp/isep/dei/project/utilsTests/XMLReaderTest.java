package pt.ipp.isep.dei.project.utilsTests;


import pt.ipp.isep.dei.project.model.LocationDTO;


import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.utils.XMLReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class XMLReaderTest {
    private XMLReader XMLReader = new XMLReader();

    /**
     * readJSON File receives a DTO and transforms it
     */
    @Test
    public void testReadXMLFileToList_geoAreaDTO() throws FileNotFoundException {
        // arrange
        String path = "datasets/xml/XMLfile_GA.xml";
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
        assertEquals(41.17923, latitude,0.001);
        assertEquals(-8.606409, longitude,0.001);
        assertEquals(125, altitude,0.001);
    }

}


