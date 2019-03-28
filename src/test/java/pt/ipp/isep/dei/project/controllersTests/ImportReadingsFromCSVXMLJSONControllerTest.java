package pt.ipp.isep.dei.project.controllersTests;

import pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller.ImportReadingsController;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.sensor.Sensor;

class ImportReadingsFromCSVXMLJSONControllerTest {
    private ImportReadingsController controller;
    private GeographicalArea portoDistrict;
    private GeographicalArea portoCity;
    private Sensor temperatureSensor;
    private Sensor temperatureSensor1;
    private ReadingDTO readingDTO;

    /**
     * This method pretends to initialize some attributes of this test class to simplifying all tests.
     */
    /*
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
        controller = new ImportReadingsController(geographicalAreaList);
    }

    /**
     * Test that tries to use an valid Id to verify if a Sensor exists, which works and returns true.
     */
    /*
    @Test
    public void testCheckIfSensorExistsById_tryingToUseAValidId_ShouldReturnTrue() {
        // Act
        boolean result = controller.checkIfSensorExistsById("432");

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to use an invalid Id to verify if a Sensor exists, which doesn't work and returns false.
     */
    /*
    @Test
    public void testCheckIfSensorExistsById_tryingToUseAnInvalidId_ShouldReturnFalse() {
        // Act
        boolean result = controller.checkIfSensorExistsById("A123fasdasd");

        // Assert
        assertFalse(result);
    }

    /**
     * Test that tries to check if a date time is before the starting date of a sensor, when it is, which should return true.
     */
    /*
    @Test
    public void testIsDateTimeBeforeSensorStartingDate_whenItIs_ShouldReturnTrue() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.of(2014, 12, 2, 15, 20, 00);
        controller.checkIfSensorExistsById("432");

        // Act
        boolean result = controller.isDateTimeBeforeSensorStartingDate(localDateTime);

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to check if a date time is before the starting date of a sensor, when it isn't, which should return false.
     */
    /*
    @Test
    public void testIsDateTimeBeforeSensorStartingDate_whenItIsnt_ShouldReturnFalse() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.of(2019, 12, 2, 15, 20, 00);
        controller.checkIfSensorExistsById("432");

        // Act
        boolean result = controller.isDateTimeBeforeSensorStartingDate(localDateTime);

        // Assert
        assertFalse(result);
    }

    /**
     * Test that tries to add a Reading to a Sensor, using a valid ReadingDTO, which results in a successful attempt.
     */
    /*
    @Test
    public void testAddReadingToSensor_tryingToAddAValidReading_ShouldReturnTrue() {
        // Arrange
        controller.checkIfSensorExistsById("432");
        controller.addReadingToSensor(readingDTO);
        Reading reading = ReadingMapper.mapToEntity(readingDTO);

        // Act
        boolean result = temperatureSensor.getListOfReadings().contains(reading);

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to add a null Object to a Sensor, which results in a non addition.
     */
    /*
    @Test
    public void testAddReadingToSensor_tryingToAddNullReading_ShouldReturnFalse() {
        // Arrange
        controller.checkIfSensorExistsById("432");
        controller.addReadingToSensor(null);

        // Act
        boolean result = temperatureSensor.isMeasurementListEmpty();

        // Assert
        assertFalse(result);
    }
    */
}