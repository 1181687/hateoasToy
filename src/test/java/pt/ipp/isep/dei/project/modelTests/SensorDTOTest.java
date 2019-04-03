package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SensorDTOTest {
    SensorDTO sensorDTO;

    @BeforeEach
    void StartUp() {
        sensorDTO = new SensorDTO();

        LocationDTO location = new LocationDTO();
        location.setLatitude(45);
        location.setLongitude(45);
        location.setElevation(100);

        sensorDTO.setId("ISEP-Temperature");
        sensorDTO.setName("Temperature sensor");
        sensorDTO.setSensorType("Temperature");
        sensorDTO.setLocation(location);
        sensorDTO.setStartingDate(LocalDate.now());
        sensorDTO.setUnits("ºC");
    }

    /**
     * get method for sensor Id. gets id ISEP-Temperature
     */
    @org.junit.jupiter.api.Test
    void testGetId_get_isepTemperature() {
        //Arrange
        String expectedResult = "ISEP-Temperature";
        //Act
        String result = sensorDTO.getId();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * set method for sensor Id. changes ID to ISEP
     */
    @Test
    void testSetId_set_ISEP() {
        //Arrange
        String expectedResult = "ISEP";
        sensorDTO.setId("ISEP");
        //Act
        String result = sensorDTO.getId();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * get method for sensor name. gets Temperature sensor
     */
    @org.junit.jupiter.api.Test
    void testGetName_get_temperatureSensor() {
        //Arrange
        String expectedResult = "Temperature sensor";
        //Act
        String result = sensorDTO.getName();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * set method for sensor name. changes to new name
     */
    @Test
    void testSetName_set_newName() {
        //Arrange
        String expectedResult = "New name";
        sensorDTO.setName("New name");
        //Act
        String result = sensorDTO.getName();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * get method for starting date. gets local date now
     */
    @org.junit.jupiter.api.Test
    void testGetStartingDate_get() {
        //Arrange
        LocalDate expectedResult = LocalDate.now();
        //Act
        LocalDate result = sensorDTO.getStartingDate();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * set method for starting date. changes to local date -1 day
     */
    @Test
    void testSetStartingDate_set() {
        //Arrange
        LocalDate expectedResult = LocalDate.now().minusDays(1);
        sensorDTO.setStartingDate(LocalDate.now().minusDays(1));
        //Act
        LocalDate result = sensorDTO.getStartingDate();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * get method for sensor type. gets Temperature
     */
    @org.junit.jupiter.api.Test
    void testGetSensorType_get_temperature() {
        //Arrange
        String expectedResult = "Temperature";
        //Act
        String result = sensorDTO.getSensorType();
        //Assert
        assertEquals(expectedResult, result);

    }

    /**
     * set method for sensor type. changes sensor type to Rainfall
     */
    @org.junit.jupiter.api.Test
    void testSetSensorType_set_rainfall() {
        //Arrange
        String expectedResult = "Rainfall";
        sensorDTO.setSensorType("Rainfall");
        //Act
        String result = sensorDTO.getSensorType();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * get method for sensor location. gets location (comprised of latitude, longitude and elevation)
     */
    @Test
    void testGetLocation_get() {
        //Arrange
        List<Double> expectedResult = new ArrayList<>();
        expectedResult.add(45.0);
        expectedResult.add(45.0);
        expectedResult.add(100.0);
        //Act
        List<Double> result = new ArrayList<>();
        result.add(sensorDTO.getLocation().getLatitude());
        result.add(sensorDTO.getLocation().getLongitude());
        result.add(sensorDTO.getLocation().getElevation());
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * set method for sensor location. changes to a new location (comprised of latitude, longitude and elevation)
     */
    @Test
    void testSetLocation_set() {
        //Arrange
        LocationDTO newLocation = new LocationDTO();
        newLocation.setLatitude(45);
        newLocation.setLongitude(45);
        newLocation.setElevation(100);
        LocationDTO expectedResult = newLocation;
        sensorDTO.setLocation(newLocation);
        //Act
        LocationDTO result = sensorDTO.getLocation();
        //Assert
        assertEquals(expectedResult, result);

    }

    /**
     * get method for sensor units. gets units in ºC
     */
    @org.junit.jupiter.api.Test
    void testGetUnits_get_C() {
        //Arrange
        String expectedResult = "ºC";
        //Act
        String result = sensorDTO.getUnits();
        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * set method for sensor units. changes units to ºF
     */
    @Test
    void testSetUnits_set_F() {
        //Arrange
        String expectedResult = "ºF";
        sensorDTO.setUnits("ºF");
        //Act
        String result = sensorDTO.getUnits();
        //Assert
        assertEquals(expectedResult, result);
    }
}