package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.ImportReadingsFromCSVController;
import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImportReadingsFromCSVControllerTest {
    private ImportReadingsFromCSVController controller;
    private GeographicalArea portoDistrict;
    private GeographicalArea portoCity;
    private Sensor temperatureSensor;
    private Sensor temperatureSensor1;
    private ReadingDTO readingDTO;

    @BeforeEach
    public void StartUp() {
        // Geographical Area Types
        GeographicalAreaType district = new GeographicalAreaType("District");
        GeographicalAreaType city = new GeographicalAreaType("City");

        // Geographical Areas
        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40, location1);
        portoDistrict = new GeographicalArea("Porto District", district, location1, areaShape1);
        geographicalAreaList.addGeoArea(portoDistrict);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10, location2);
        portoCity = new GeographicalArea("Porto City", city, location2, areaShape2);
        geographicalAreaList.addGeoArea(portoCity);

        // Sensors
        SensorType temperature = new SensorType("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        temperatureSensor = new Sensor("A123", startDate, temperature, sensorLocation);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        temperatureSensor1 = new Sensor("B123", startDate1, temperature, sensorLocation1);
        portoDistrict.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        // Reading DTO
        readingDTO = ReadingMapper.newReadingDTO();
        readingDTO.setValue(13);
        LocalDateTime dateTime = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        readingDTO.setDateTime(dateTime);

        // Controller
        controller = new ImportReadingsFromCSVController(geographicalAreaList);
    }

    @Test
    public void checkIfSensorExistsByIdTrueTest() {
        // Act
        boolean result = controller.checkIfSensorExistsById("A123");

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfSensorExistsByIdFalseTest() {
        // Act
        boolean result = controller.checkIfSensorExistsById("A123fasdasd");

        // Assert
        assertFalse(result);
    }

    @Test
    public void addReadingToSensorTest() {
        // Arrange
        controller.checkIfSensorExistsById("A123");
        controller.addReadingToSensor(readingDTO);

        // Act
        boolean result = temperatureSensor.isMeasurementListEmpty();

        // Assert
        assertFalse(result);
    }
}