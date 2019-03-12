package pt.ipp.isep.dei.project.controllersTests.importReadingsFromCSVControllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.importReadingsFromCSVController.ImportReadingsFromCSVController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

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
        portoDistrict = new GeographicalArea("Porto", "Porto District", district, location1, areaShape1);
        geographicalAreaList.addGeoArea(portoDistrict);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10, location2);
        portoCity = new GeographicalArea("Porto", "Porto City", city, location2, areaShape2);
        geographicalAreaList.addGeoArea(portoCity);

        // Sensors
        SensorType temperature = new SensorType("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(42.1596, -8.6109, 97);
        temperatureSensor = new Sensor("432", "A123", startDate, temperature, sensorLocation, "l/m2");
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        temperatureSensor1 = new Sensor("654", "B123", startDate1, temperature, sensorLocation1, "l/m2");
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