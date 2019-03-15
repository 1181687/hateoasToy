package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.ConfHouseLocationController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConfHouseLocationControllerTest {
    private ConfHouseLocationController ctrl;
    private GeographicalAreaList geographicalAreaList;
    private GeographicalArea porto;
    private GeographicalArea isep;
    private House house;

    @BeforeEach
    public void StartUp() {
        //Geographical Area List
        geographicalAreaList = new GeographicalAreaList();

        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        isep = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);
        GeographicalAreaType city = new GeographicalAreaType("City");
        porto = new GeographicalArea("Porto", "City of Porto", city, location, areaShape);
        geographicalAreaList.addGeoArea(isep);
        geographicalAreaList.addGeoArea(porto);
        isep.setInsertedIn(porto);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation, isep);
        house.setAddress(address);

        ctrl = new ConfHouseLocationController(geographicalAreaList, house);
    }

    @Test
    public void testDefineNewAddress() {
        //Arrange
        String zipCodeNewAddress = "4150";
        double latitudeNewAddress = 43.5;
        double longitudeNewAddress = 51.5;
        double altitudeNewAddress = 180.0;
        Location localNewAddress = new Location(latitudeNewAddress, longitudeNewAddress, altitudeNewAddress);
        Address newAddress = new Address(zipCodeNewAddress, localNewAddress, isep);

        ctrl.setGeographicalArea(0);
        ctrl.defineNewAddress(zipCodeNewAddress, latitudeNewAddress, longitudeNewAddress, altitudeNewAddress);
        ctrl.setAddress();

        //Act
        boolean result = ctrl.getHouse().getAddress().equals(newAddress);

        //Assert
        assertTrue(result);
    }

    /**
     * Test that tries to output a string with information about the geo areas in the list, using the criterion that
     * enables to output also the information about where they are inserted.
     */
    @Test
    public void testGetGeoAreaListToString_withTrueCriterion_shouldReturnString() {
        // Arrange
        String expectedResult = "1 - ID: ISEP, Description: Campus do ISEP, Type: Urban area, Latitude: 41.178553, Longitude: -8.608035, Inserted in: City City of Porto\n" +
                "2 - ID: Porto, Description: City of Porto, Type: City, Latitude: 41.178553, Longitude: -8.608035\n";

        // Act
        String result = ctrl.getGeoAreaListToString(true);

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to output a string with information about the geo areas in the list, not using the criterion that
     * enables to output also the information about where they are inserted.
     */
    @Test
    public void testGetGeoAreaListToString_withFalseCriterion_shouldReturnString() {
        // Arrange
        String expectedResult = "1 - ID: ISEP, Description: Campus do ISEP, Type: Urban area, Latitude: 41.178553, Longitude: -8.608035\n" +
                "2 - ID: Porto, Description: City of Porto, Type: City, Latitude: 41.178553, Longitude: -8.608035\n";

        // Act
        String result = ctrl.getGeoAreaListToString(false);

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to check if the Geographical Area List is empty, which isn't and, therefore, returns false.
     */
    @Test
    public void testIsGeographicalAreaListEmpty_whenItIsnt_shouldReturnFalse() {
        // Act
        boolean result = ctrl.isGeographicalAreaListEmpty();

        // Assert
        assertFalse(result);
    }

    /**
     * Test that tries to check if the Geographical Area List is empty, which is and, therefore, returns true.
     */
    @Test
    public void testIsGeographicalAreaListEmpty_whenItIs_shouldReturnTrue() {
        // Arrange
        geographicalAreaList.removeGeoArea(isep);
        geographicalAreaList.removeGeoArea(porto);

        // Act
        boolean result = ctrl.isGeographicalAreaListEmpty();

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to get the number of geo areas.
     */
    @Test
    public void testGetNumberOfGeoAreas_shouldReturnTwo() {
        // Arrange
        int expectedResult = 2;

        // Act
        int result = ctrl.getNumberOfGeoAreas();

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that tries to checks if the House is already in the specified Geographical Area, which it is and, therefore, returns true.
     */
    @Test
    public void testIsHouseAlreadyInGeoArea_whenItIs_shouldReturnTrue() {
        // Arrange
        ctrl.setGeographicalArea(0);

        // Act
        boolean result = ctrl.isHouseAlreadyInGeoArea();

        // Assert
        assertTrue(result);
    }

    /**
     * Test that tries to checks if the House is already in the specified Geographical Area, which it isn't and, therefore, returns false.
     */
    @Test
    public void testIsHouseAlreadyInGeoArea_whenItIsnt_shouldReturnTrue() {
        // Arrange
        ctrl.setGeographicalArea(1);

        // Act
        boolean result = ctrl.isHouseAlreadyInGeoArea();

        // Assert
        assertFalse(result);
    }
}
