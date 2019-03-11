package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeographicalAreaListTest {
    private GeographicalAreaList geoAreaList;
    private GeographicalArea portoCity;
    private GeographicalArea bonfimStreet;
    private Sensor sensor;

    @BeforeEach
    public void StartUp() {
        // Geographical Area List
        geoAreaList = new GeographicalAreaList();

        // Geographical Areas
        portoCity = geoAreaList.newGeographicalArea("Porto", "City", 41.1496,
                -8.6109, 97, 10, 10);
        geoAreaList.addGeoArea(portoCity);
        bonfimStreet = geoAreaList.newGeographicalArea("Bonfim Street", "Street", 41.1496,
                -8.6109, 97, 5, 5);
        geoAreaList.addGeoArea(bonfimStreet);

        // Sensor
        Location location = new Location(41.1496, -8.6109, 97);
        SensorType temperature = new SensorType("Temperature");
        sensor = new Sensor("TT123123", temperature, location);
        portoCity.getSensorListInTheGeographicArea().addSensor(sensor);
    }

    @Test
    void addGeoAreaAlreadyThereTest() {
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

        String expectResult = "1 - Name: Porto, Type: City, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - Name: Bonfim Street, Type: Street, Latitude: 41.1496, Longitude: -8.6109, Inserted in: City Porto\n";

        // Act
        String result = geoAreaList.getGeoAreaListToString(true);

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getGeoAreaListToStringTrueCriteriaWithNoInsertedAreaTest() {
        // Arrange
        String expectResult = "1 - Name: Porto, Type: City, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - Name: Bonfim Street, Type: Street, Latitude: 41.1496, Longitude: -8.6109\n";

        // Act
        String result = geoAreaList.getGeoAreaListToString(true);

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getGeoAreaListToStringFalseCriteriaTest() {
        // Arrange
        bonfimStreet.setInsertedIn(portoCity);

        String expectResult = "1 - Name: Porto, Type: City, Latitude: 41.1496, Longitude: -8.6109\n" +
                "2 - Name: Bonfim Street, Type: Street, Latitude: 41.1496, Longitude: -8.6109\n";

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
        GeographicalArea northernRegion = new GeographicalArea("Northern Region", region, location, area);
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

        GeographicalArea expectedResult = null;

        // Act
        GeographicalArea result = geoAreaList.getGeographicalArea(portoCity);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAllSensorsTest() {
        // Arrange
        List<Sensor> expectedResult = new ArrayList<>();
        expectedResult.add(sensor);

        // Act
        List<Sensor> result = geoAreaList.getAllSensors().getListOfSensors();

        // Assert
        assertEquals(expectedResult, result);
    }
}