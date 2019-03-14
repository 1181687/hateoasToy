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

    @Test
    void getId() {
        //Arrange
        String expectedResult = "ISEP-Temperature";
        //Act
        String result = sensorDTO.getId();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void setId() {
        //Arrange
        String expectedResult = "ISEP";
        sensorDTO.setId("ISEP");
        //Act
        String result = sensorDTO.getId();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getName() {
        //Arrange
        String expectedResult = "Temperature sensor";
        //Act
        String result = sensorDTO.getName();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void setName() {
        //Arrange
        String expectedResult = "New name";
        sensorDTO.setName("New name");
        //Act
        String result = sensorDTO.getName();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getStartingDate() {
        //Arrange
        LocalDate expectedResult = LocalDate.now();
        //Act
        LocalDate result = sensorDTO.getStartingDate();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void setStartingDate() {
        //Arrange
        LocalDate expectedResult = LocalDate.now().minusDays(1);
        sensorDTO.setStartingDate(LocalDate.now().minusDays(1));
        //Act
        LocalDate result = sensorDTO.getStartingDate();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getSensorType() {
        //Arrange
        String expectedResult = "Temperature";
        //Act
        String result = sensorDTO.getSensorType();
        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void setSensorType() {
        //Arrange
        String expectedResult = "Rainfall";
        sensorDTO.setSensorType("Rainfall");
        //Act
        String result = sensorDTO.getSensorType();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getLocation() {
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

    @Test
    void setLocation() {
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

    @Test
    void getUnits() {
        //Arrange
        String expectedResult = "ºC";
        //Act
        String result = sensorDTO.getUnits();
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void setUnits() {
        //Arrange
        String expectedResult = "ºF";
        sensorDTO.setUnits("ºF");
        //Act
        String result = sensorDTO.getUnits();
        //Assert
        assertEquals(expectedResult, result);
    }
}