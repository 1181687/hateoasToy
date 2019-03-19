package pt.ipp.isep.dei.project.utilsTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;
import pt.ipp.isep.dei.project.utils.JSONReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        // geo area ISEP
        String id = "ISEP";
        String description = "Campus do ISEP";
        String type = "Urban area";
        double width = 0.261;
        double length = 0.249;
        double latitude = 41.178553;
        double longitude = -8.608035;
        double altitude = 111;

        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(id, description, type, width, length, latitude, longitude, altitude);

        // sensor
        String idSensor = "S1";
        String nameSensor = "sensor1";
        String typeName = "Temperature";
        String units = "1/ms";

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(latitude);
        locationDTO.setLongitude(longitude);
        locationDTO.setElevation(altitude);
        LocalDate dateDTO = LocalDate.now();

        SensorDTO areaSensor1 = new SensorDTO();
        areaSensor1.setId(idSensor);
        areaSensor1.setName(nameSensor);
        areaSensor1.setSensorType(typeName);
        areaSensor1.setLocation(locationDTO);
        areaSensor1.setStartingDate(dateDTO);
        areaSensor1.setUnits(units);

        geographicalAreaDTO.addSensor(areaSensor1);

        List<GeographicalAreaDTO> geoAreaDto = new ArrayList<>();
        geoAreaDto.add(0, geographicalAreaDTO);


        // geo area Porto
        String id1 = "Porto";
        String description1 = "City of Porto";
        String type1 = "city";
        double width1 = 10.09;
        double length1 = 3.30;
        double latitude1 = 41.149935;
        double longitude1 = -8.610857;
        double altitude1 = 118;

        GeographicalAreaDTO geographicalAreaDTO1 = GeographicalAreaMapper.mapToDTO(id1, description1, type1, width1, length1, latitude1, longitude1, altitude1);

        List<String> expectedResult = new ArrayList<>();
        String idGeoArea = geographicalAreaDTO.getId();
        String idGeaoArea1 = geographicalAreaDTO1.getId();
        expectedResult.add(idGeoArea);
        expectedResult.add(idGeaoArea1);


        List<GeographicalAreaDTO> resultJSON = JSONReader.readJSONFileToList(file);
        String geoA1 = resultJSON.get(0).getId();
        String geoA2 = resultJSON.get(1).getId();
        List<String> result = new ArrayList<>();
        result.add(geoA1);
        result.add(geoA2);

        // assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testReadJSONFileToList_geoAreaDTO_assertNotEquals() {
        // arrange
        FileReader file;
        try {
            file = new FileReader("JSONfile.json");
        } catch (FileNotFoundException e) {
            file = null;
        }
        // geo area Porto
        String id = "Porto";
        String description = "Campus do ISEP";
        String type = "Urban area";
        double width = 0.261;
        double length = 0.249;
        double latitude = 41.178553;
        double longitude = -8.608035;
        double altitude = 111;

        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(id, description, type, width, length, latitude, longitude, altitude);

        // sensor
        SensorDTO areaSensor1 = new SensorDTO();

        geographicalAreaDTO.addSensor(areaSensor1);

        List<GeographicalAreaDTO> geoAreaDto = new ArrayList<>();
        geoAreaDto.add(0, geographicalAreaDTO);


        // geo area Lisboa
        String id1 = "Lisboa";
        String description1 = "City of Lisboa";
        String type1 = "city";
        double width1 = 20;
        double length1 = 40;
        double latitude1 = 70;
        double longitude1 = -9;
        double altitude1 = 42;

        GeographicalAreaDTO geographicalAreaDTO1 = GeographicalAreaMapper.mapToDTO(id1, description1, type1, width1, length1, latitude1, longitude1, altitude1);

        List<String> expectedResult = new ArrayList<>();
        String idGeoArea = geographicalAreaDTO.getId();
        String idGeaoArea1 = geographicalAreaDTO1.getId();
        expectedResult.add(idGeoArea);
        expectedResult.add(idGeaoArea1);

        List<GeographicalAreaDTO> resultJSON = JSONReader.readJSONFileToList(file);
        String geoA1 = resultJSON.get(0).getId();
        String geoA2 = resultJSON.get(1).getId();
        List<String> result = new ArrayList<>();
        result.add(geoA1);
        result.add(geoA2);

        // assert
        assertNotEquals(expectedResult, result);
    }

    @Test
    public void testReadJSONFileToList_Null() {
        // arrange
        FileReader file;
        try {
            file = new FileReader("JSONfile.json");
        } catch (FileNotFoundException e) {
            file = null;
        }
        // geo area ISEP
        String id = "ISEP";
        String description = "Campus do ISEP";
        String type = "Urban area";
        double width = 0.261;
        double length = 0.249;
        double latitude = 41;
        double longitude = -8.608035;
        double altitude = 111;

        GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(id, description, type, width, length, latitude, longitude, altitude);

        // sensor
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(Double.NaN);
        locationDTO.setLongitude(Double.NaN);
        locationDTO.setElevation(Double.NaN);
        LocalDate dateDTO = LocalDate.now();

        SensorDTO areaSensor1 = new SensorDTO();
        areaSensor1.setId(null);
        areaSensor1.setName(null);
        areaSensor1.setSensorType(null);
        areaSensor1.setLocation(locationDTO);
        areaSensor1.setStartingDate(dateDTO);
        areaSensor1.setUnits(null);

        geographicalAreaDTO.addSensor(areaSensor1);

        List<GeographicalAreaDTO> geoAreaDto = new ArrayList<>();
        geoAreaDto.add(0, geographicalAreaDTO);


        // geo area Porto
        String id1 = "Porto";
        String description1 = "City of Porto";
        String type1 = "city";
        double width1 = 10.09;
        double length1 = 3.30;
        double latitude1 = 41.149935;
        double longitude1 = -8.610857;
        double altitude1 = 118;

        GeographicalAreaDTO geographicalAreaDTO1 = GeographicalAreaMapper.mapToDTO(id1, description1, type1, width1, length1, latitude1, longitude1, altitude1);

        List<String> expectedResult = new ArrayList<>();
        String idGeoArea = geographicalAreaDTO.getId();
        String idGeaoArea1 = geographicalAreaDTO1.getId();
        expectedResult.add(idGeoArea);
        expectedResult.add(idGeaoArea1);


        List<GeographicalAreaDTO> resultJSON = JSONReader.readJSONFileToList(file);
        String geoA1 = resultJSON.get(0).getId();
        String geoA2 = resultJSON.get(1).getId();
        List<String> result = new ArrayList<>();
        result.add(geoA1);
        result.add(geoA2);

        // assert
        assertEquals(expectedResult, result);
    }

}