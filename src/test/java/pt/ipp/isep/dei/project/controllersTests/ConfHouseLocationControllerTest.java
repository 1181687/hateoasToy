package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.ConfHouseLocationController;
import pt.ipp.isep.dei.project.model.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfHouseLocationControllerTest {

    @Test
    public void testDefineNewAddress() {

        //Arrange
        RoomList roomList = new RoomList();
        HouseGridList houseGridList = new HouseGridList();
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);
        AreaShape areaShape = new AreaShape(20, 20, local);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, local, areaShape);
        House house = new House(roomList, houseGridList, address, insertedGeoArea);

        ConfHouseLocationController ctrl = new ConfHouseLocationController(house);

        String zipCodeNewAddress = "4150";
        double latitudeNewAddress = 43.5;
        double longitudeNewAddress = 51.5;
        double altitudeNewAddress = 180.0;
        Location localNewAddress = new Location(latitudeNewAddress, longitudeNewAddress, altitudeNewAddress);
        Address newAddress = new Address(zipCodeNewAddress, localNewAddress);

        ctrl.defineNewAddress(zipCodeNewAddress, latitudeNewAddress, longitudeNewAddress, altitudeNewAddress);
        ctrl.setAddressToTheHouse();

        //Act
        boolean result = ctrl.getmHouse().getAddress().equals(newAddress);

        //Assert
        assertTrue(result);
    }

}
