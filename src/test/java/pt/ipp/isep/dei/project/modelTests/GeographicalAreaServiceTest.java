package pt.ipp.isep.dei.project.modelTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pt.ipp.isep.dei.project.GeoAreaRepository;
import pt.ipp.isep.dei.project.io.ui.Main;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration(classes = {Main.class},
        loader = AnnotationConfigContextLoader.class)
@SpringJUnitConfig(GeographicalAreaServiceTest.Config.class)
@DataJpaTest
public class GeographicalAreaServiceTest {
    @InjectMocks
    private GeographicalAreaService geoAreaList;
    private GeographicalArea portoCity;
    private GeographicalArea bonfimStreet;
    private GeoAreaSensor sensor;

    @Mock
    private GeoAreaRepository geoAreaRepository;

    @BeforeEach
    public void StartUp() {
        MockitoAnnotations.initMocks(this);
        // Geographical Areas
        Location portoLocation = new Location(41.1496,
                -8.6109, 97);
        portoCity = geoAreaList.newGeographicalArea("Porto", "City of Porto", "City", portoLocation, 10, 10);
        geoAreaList.addGeoArea(portoCity);
        bonfimStreet = geoAreaList.newGeographicalArea("Bonfim", "Bonfim Street", "Street", portoLocation, 5, 5);
        geoAreaList.addGeoArea(bonfimStreet);

        // GeoAreaSensor
        Location location = new Location(41.1496, -8.6109, 97);
        SensorType temperature = new SensorType("Temperature");
        sensor = new GeoAreaSensor("s1", "TT123123", temperature, location, "l/m2");
        portoCity.getSensorListInTheGeographicArea().addSensor(sensor);
    }

    /**
     * test that doesn't add a new geo area because it already exists
     */
    @Test
    public void testAddGeoAreaAlreadyThere_boolean_False() {
        // Act
        boolean result = geoAreaList.addGeoArea(portoCity);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getGeoAreaList() {
        // Arrange
        List<GeographicalArea> expectedResult = geoAreaList.getGeoAreaList();

        // Act
        List<GeographicalArea> result = geoAreaList.getGeoAreaList();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListOfGeographicalAreasByTypeTest() {
        //Arrange
        List<String> expectedResult = new ArrayList<>(Arrays.asList("Bonfim Street"));

        //Act
        List<String> result = geoAreaList.getListOfGeographicalAreasByType("Street");

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
        String result = geoAreaList.getGeoAreaListToString(true);

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getGeoAreaListToStringTrueCriteriaWithNoInsertedAreaTest() {
        // Arrange
        String expectResult = "1 - ID: Porto, Description: City of Porto, Type: City, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - ID: Bonfim, Description: Bonfim Street, Type: Street, Latitude: 41.1496, Longitude: -8.6109\n";

        // Act
        String result = geoAreaList.getGeoAreaListToString(true);

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
        String result = geoAreaList.getGeoAreaListToString(false);

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getGeographicalAreaInTheListTest() {
        // Arrange
        GeographicalArea expectedResult = bonfimStreet;

        // Act
        GeographicalArea resultado = geoAreaList.getGeographicalAreaInTheList(1);

        // Assert
        assertEquals(expectedResult, resultado);
    }

    @Test
    public void checkIfGeoAreaDoesntHaveAnInsertedAreaTrueTest() {
        // Act
        boolean result = geoAreaList.checkIfGeoAreaDoesntHaveAnInsertedArea(portoCity);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfGeoAreaDoesntHaveAnInsertedAreaFalseTest() {
        // Arrange
        bonfimStreet.setInsertedIn(portoCity);

        // Act
        boolean result = geoAreaList.checkIfGeoAreaDoesntHaveAnInsertedArea(bonfimStreet);

        // Assert
        assertFalse(result);
    }

    @Test
    public void checkIfGeoAreaIsInsertedInAnotherFalseTest() {
        // Act
        boolean result = geoAreaList.checkIfGeoAreaIsInsertedInAnother(1, 0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void checkIfGeoAreaIsInsertedInAnotherTrueTest() {
        // Arrange
        bonfimStreet.setInsertedIn(portoCity);

        // Act
        boolean result = geoAreaList.checkIfGeoAreaIsInsertedInAnother(1, 0);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfGeoAreaIsInsertedInAnotherIndirectlyTrueTest() {
        // Arrange
        GeographicalAreaType region = new GeographicalAreaType("Region");
        Location location = new Location(41.1496, -8.6109, 97);
        AreaShape area = new AreaShape(10, 10, location);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, area);
        geoAreaList.addGeoArea(northernRegion);
        portoCity.setInsertedIn(northernRegion);
        bonfimStreet.setInsertedIn(portoCity);

        // Act
        boolean result = geoAreaList.checkIfGeoAreaIsInsertedInAnother(0, 2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void addGeoAreaInASpecificPositionTest() {
        // Arrange
        geoAreaList.removeGeoArea(bonfimStreet);
        geoAreaList.addGeoAreaInASpecificPosition(0, bonfimStreet);

        GeographicalArea expectedResult = bonfimStreet;

        // Act
        GeographicalArea result = geoAreaList.getGeoAreaList().get(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void removeGeoAreaTrueTest() {
        // Act
        boolean result = geoAreaList.removeGeoArea(portoCity);

        // Assert
        assertTrue(result);
    }

    @Test
    public void removeGeoAreaFalseTest() {
        // Arrange
        geoAreaList.removeGeoArea(portoCity);

        // Act
        boolean result = geoAreaList.removeGeoArea(portoCity);

        // Assert
        assertFalse(result);
    }

    @Test
    public void getGeographicalAreaNullTest() {
        // Arrange
        geoAreaList.removeGeoArea(portoCity);

        // Act
        GeographicalArea result = geoAreaList.getGeographicalArea(portoCity);

        // Assert
        assertNull(result);
    }

    /**
     * Test that tries to get all the sensors in all the geo areas, which returns a list of Sensors.
     */
    @Test
    public void getAllSensorsTest() {
        // Arrange
        List<GeoAreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(sensor);

        // Act
        List<GeoAreaSensor> result = geoAreaList.getAllSensors().getListOfSensors();

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to use a valid/existing Id to search for a GeoAreaSensor, which results in True.
     **/
    @Test
    public void testCheckIfGeoAreaExistsById_tryingToTestAnExistingId_ShouldReturnTrue() {
        // Act
        boolean result = geoAreaList.checkIfGeoAreaExistsById("Porto");

        //
        assertTrue(result);
    }

    /**
     * Test that tries to use an invalid/non-existing Id to search for a GeoArea, which results in False.
     **/
    @Test
    public void testCheckIfGeoExistsById_tryingToTestANonExistingId_ShouldReturnFalse() {
        // Act
        boolean result = geoAreaList.checkIfGeoAreaExistsById("BadId");

        //
        assertFalse(result);
    }

    /**
     * Test that tries to use a valid/existing Id to get a geographical area, which turns out fine.
     **/
    @Test
    public void testGetGeoAreaById_tryingToTestAnExistingId_ShouldReturnTheCorrespondingGeoArea() {
        // Arrange
        GeographicalArea expectedResult = portoCity;

        // Act
        GeographicalArea result = geoAreaList.getGeoAreaById("Porto");

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to use a invalid/non-existing Id to get a geographical area, which returns null.
     **/
    @Test
    public void testGetGeoAreaById_tryingToTestANonExistingId_ShouldReturnNull() {
        // Act
        GeographicalArea result = geoAreaList.getGeoAreaById("Gondomar");

        // Assert
        assertNull(result);
    }

    /**
     * Test that finds a sensor in all of the geographical areas by its Id
     **/
    @Test
    public void testGetSensorById_ExistingId_ShouldReturnTheSensor() {
        // Act
        GeoAreaSensor result = geoAreaList.getSensorById("s1");

        // Assert
        assertEquals(sensor, result);
    }

    /**
     * Test that tries to finds a sensor in all of the geographical areas by its Id but there are non.
     **/
    @Test
    public void testGetSensorById_IdDoesNotExist_ShouldReturnNull() {
        // Act
        GeoAreaSensor result = geoAreaList.getSensorById("WrongID");

        // Assert
        assertNull(result);
    }

    @Configuration
    static class Config {
    }
}