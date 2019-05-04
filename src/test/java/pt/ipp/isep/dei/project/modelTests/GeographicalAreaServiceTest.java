package pt.ipp.isep.dei.project.modelTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.repositories.GeoAreaRepository;
import pt.ipp.isep.dei.project.repositories.GeoAreaSensorRepository;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class GeographicalAreaServiceTest {
    @Mock
    private GeoAreaRepository geoAreaRepository;
    @Mock
    private GeoAreaSensorRepository geoAreaSensorRepository;
    @InjectMocks
    private GeographicalAreaService geographicalAreaService;
    private GeographicalArea portoCity;
    private GeographicalArea bonfimStreet;
    private GeoAreaSensor sensor;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        // Geographical Areas
        Location portoLocation = new Location(41.1496,
                -8.6109, 97);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(new GeoAreaTypeId("City"));
        GeoAreaId geoAreaId = new GeoAreaId(portoLocation, "Porto", geographicalAreaType);
        AreaShape areaShape = new AreaShape(10, 10);
        portoCity = new GeographicalArea("Porto", "City of Porto", geographicalAreaType, portoLocation, areaShape);
        geographicalAreaService.addGeoArea(portoCity);
        bonfimStreet = geographicalAreaService.newGeographicalArea("Bonfim", "Bonfim Street", "Street", portoLocation, 5, 5);

        // GeoAreaSensor
        Location location = new Location(41.1496, -8.6109, 97);
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 05, 02, 11, 45, 00);
        sensor = new GeoAreaSensor(new SensorId("s1"), "TT123123", startDate, temperature, location, "l/m2", portoCity.getId());
    }


    /**
     * test that doesn't add a new geo area because it already exists
     */

    @Test
    public void testAddGeoAreaAlreadyThere_boolean_False() {
        // Act
        when(geoAreaRepository.save(portoCity)).thenReturn(portoCity);
        when(geoAreaRepository.existsById(portoCity.getId())).thenReturn(true);
        boolean result = geographicalAreaService.addGeoArea(portoCity);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getListOfGeoAreasTypeToString_TestReturnsTheRightTypes() {
        //Arrange
        List<GeographicalArea> geographicalAreaList = new ArrayList<>();
        geographicalAreaList.add(bonfimStreet);

        when(geoAreaRepository.findAll()).thenReturn(geographicalAreaList);

        List<String> expectedResult = new ArrayList<>();

        expectedResult.add(geographicalAreaList.get(0).getGeoAreaType().getTypeId());

        //Act
        List<String> result = geographicalAreaService.getListOfGeoAreasTypeToString("Street");

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getGeoAreaListToStringTrueCriteriaTest() {
        // Arrange
        bonfimStreet.setInsertedIn(portoCity);

        String expectResult = "1 - ID: Porto, Description: City of Porto, Type: City, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Bonfim, Description: Bonfim Street, Type: Street, Latitude: 41.1496, Longitude: -8.6109, Inserted in: City City of Porto\n";

        // Act
        String result = geographicalAreaService.getGeoAreaListToString(true);

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getGeoAreaListToStringTrueCriteriaWithNoInsertedAreaTest() {
        // Arrange
        String expectResult = "1 - ID: Porto, Description: City of Porto, Type: City, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Bonfim, Description: Bonfim Street, Type: Street, Latitude: 41.1496, Longitude: -8.6109\n";

        // Act
        String result = geographicalAreaService.getGeoAreaListToString(true);

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getGeoAreaListToStringFalseCriteriaTest() {
        // Arrange
        bonfimStreet.setInsertedIn(portoCity);

        String expectResult = "1 - ID: Porto, Description: City of Porto, Type: City, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Bonfim, Description: Bonfim Street, Type: Street, Latitude: 41.1496, Longitude: -8.6109\n";

        // Act
        String result = geographicalAreaService.getGeoAreaListToString(false);

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getGeographicalAreaInTheListTest() {
        // Arrange
        GeographicalArea expectedResult = bonfimStreet;

        // Act
        GeographicalArea resultado = geographicalAreaService.getGeographicalAreaInTheList(1);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void checkIfGeoAreaDoesntHaveAnInsertedAreaTrueTest() {
        // Act
        boolean result = geographicalAreaService.checkIfGeoAreaDoesntHaveAnInsertedArea(portoCity);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfGeoAreaDoesntHaveAnInsertedAreaFalseTest() {
        // Arrange
        bonfimStreet.setInsertedIn(portoCity);

        // Act
        boolean result = geographicalAreaService.checkIfGeoAreaDoesntHaveAnInsertedArea(bonfimStreet);

        // Assert
        assertFalse(result);
    }

    @Test
    public void checkIfGeoAreaIsInsertedInAnotherFalseTest() {
        // Act
        boolean result = geographicalAreaService.checkIfGeoAreaIsInsertedInAnother(1, 0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void checkIfGeoAreaIsInsertedInAnotherTrueTest() {
        // Arrange
        bonfimStreet.setInsertedIn(portoCity);

        // Act
        boolean result = geographicalAreaService.checkIfGeoAreaIsInsertedInAnother(1, 0);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfGeoAreaIsInsertedInAnotherIndirectlyTrueTest() {
        // Arrange
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Region");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId);
        Location location = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, area);
        geographicalAreaService.addGeoArea(northernRegion);
        portoCity.setInsertedIn(northernRegion);
        bonfimStreet.setInsertedIn(portoCity);

        // Act
        boolean result = geographicalAreaService.checkIfGeoAreaIsInsertedInAnother(0, 2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void addGeoAreaInASpecificPositionTest() {
        // Arrange
        geographicalAreaService.removeGeoArea(bonfimStreet);
        geographicalAreaService.addGeoAreaInASpecificPosition(0, bonfimStreet);

        GeographicalArea expectedResult = bonfimStreet;

        // Act
        GeographicalArea result = geographicalAreaService.getGeoAreaList().get(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void removeGeoAreaTrueTest() {
        // Act
        boolean result = geographicalAreaService.removeGeoArea(portoCity);

        // Assert
        assertTrue(result);
    }

    @Test
    public void removeGeoAreaFalseTest() {
        // Arrange
        geographicalAreaService.removeGeoArea(portoCity);

        // Act
        boolean result = geographicalAreaService.removeGeoArea(portoCity);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getGeographicalAreaNullTest() {
        // Arrange
        geographicalAreaService.removeGeoArea(portoCity);

        // Act
        GeographicalArea result = geographicalAreaService.getGeographicalArea(portoCity);

        // Assert
        assertNull(result);
    }


    /**
     * Test that tries to get all the sensors in all the geo areas, which returns a list of Sensors.
     */

/*    @Test
    public void getAllSensorsTest() {
        // Arrange
        List<GeoAreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(sensor);

        // Act
        List<GeoAreaSensor> result = geographicalAreaService.getAllSensors().getListOfSensors();

        // Assert
        assertEquals(expectedResult, result);
    }*/


    /**
     * Test that tries to use a valid/existing Id to search for a GeoAreaSensor, which results in True.
     **/

/*    @Test
    public void testCheckIfGeoAreaExistsById_tryingToTestAnExistingId_ShouldReturnTrue() {
        // Act
        boolean result = geographicalAreaService.checkIfGeoAreaExistsById("Porto");

        //
        assertTrue(result);
    }*/


    /**
     * Test that tries to use an invalid/non-existing Id to search for a GeoArea, which results in False.
     **/

    @Test
    public void testCheckIfGeoExistsById_tryingToTestANonExistingId_ShouldReturnFalse() {
        // Act
        boolean result = geographicalAreaService.checkIfGeoAreaExistsById("BadId");

        //
        assertFalse(result);
    }


    /**
     * Test that tries to use a valid/existing Id to get a geographical area, which turns out fine.
     **/

/*    @Test
    public void testGetGeoAreaById_tryingToTestAnExistingId_ShouldReturnTheCorrespondingGeoArea() {
        // Arrange
        GeographicalArea expectedResult = portoCity;

        // Act
        GeographicalArea result = geographicalAreaService.getGeoAreaById("Porto");

        // Assert
        assertEquals(expectedResult, result);
    }*/


    /**
     * Test that tries to use a invalid/non-existing Id to get a geographical area, which returns null.
     **/

    @Test
    public void testGetGeoAreaById_tryingToTestANonExistingId_ShouldReturnNull() {
        // Act
        GeographicalArea result = geographicalAreaService.getGeoAreaById("Gondomar");

        // Assert
        assertNull(result);
    }


    /**
     * Test that finds a sensor in all of the geographical areas by its Id
     **/

    /*@Test
    public void testGetSensorById_ExistingId_ShouldReturnTheSensor() {
        // Act
        SensorId sensorId = new SensorId("s1");

        GeoAreaSensor result = geographicalAreaService.getSensorById(sensorId);

        // Assert
        assertEquals(sensor, result);
    }*/


    /**
     * Test that tries to finds a sensor in all of the geographical areas by its Id but there are non.
     **/

    @Test
    public void testGetSensorById_IdDoesNotExist_ShouldReturnNull() {
        // Act
        SensorId sensorId = new SensorId("WrongID");
        GeoAreaSensor result = geographicalAreaService.getSensorById(sensorId);

        // Assert
        assertNull(result);
    }
}
