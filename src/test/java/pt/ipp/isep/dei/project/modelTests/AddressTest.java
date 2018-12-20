package pt.ipp.isep.dei.project.modelTests;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.Address;
import pt.ipp.isep.dei.project.model.Location;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {
    @Test
    public void testIfTwoAddressAreNotEqual() {

        //Arrange

        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);


        String zipCodeNewAddress = "4150";
        double latitudeNewAddress = 43.5;
        double longitudeNewAddress = 51.5;
        double altitudeNewAddress = 180.0;
        Location localNewAddress = new Location(latitudeNewAddress, longitudeNewAddress, altitudeNewAddress);
        Address newAddress = new Address(zipCodeNewAddress, localNewAddress);

        //Act
        boolean result = address.equals(newAddress);

        //Assert
        assertFalse(result);
    }


    @Test
    public void testIfTwoAddressAreTheSame() {

        //Arrange

        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);

        //Act
        boolean result = address.equals(address);

        //Assert
        assertTrue(result);
    }


    @Test
    public void testIfTwoAddressAreEqual() {

        //Arrange

        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);


        String zipCodeNewAddress = "4050";
        double latitudeNewAddress = 40.5;
        double longitudeNewAddress = 50.5;
        double altitudeNewAddress = 100.0;
        Location localNewAddress = new Location(latitudeNewAddress, longitudeNewAddress, altitudeNewAddress);
        Address newAddress = new Address(zipCodeNewAddress, localNewAddress);

        //Act
        boolean result = address.equals(newAddress);

        //Assert
        assertTrue(result);
    }


    @Test
    public void testIfTwoAddressesWithTheSameAttributesAreEqual() {

        //Arrange

        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address1 = new Address(zipCode, local);

        Address address2 = new Address(zipCode, local);

        boolean expectedResult = true;

        //act
        boolean result = address1.equals(address2);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIfTwoAddressesWithDifferentAttributesAreEqualFalse() {

        //arrange

        String zipCode1 = "4050";
        String zipCode2 = "4000";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address1 = new Address(zipCode1, local);
        Address address2 = new Address(zipCode2, local);

        boolean expectedResult = false;

        //act
        boolean result = address1.equals(address2);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIfTwoDifferentObjectsAreTheSameFalse() {

        //arrange
        String zipCode1 = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address1 = new Address(zipCode1, local);

        boolean expectedResult = false;

        //act
        boolean result = address1.equals(local);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testarHashCode() {
        //Arrange
        String zipCode = "4050";
        double latitude = 40.5;
        double longitude = 50.5;
        double altitude = 100.0;
        Location local = new Location(latitude, longitude, altitude);
        Address address = new Address(zipCode, local);

        int expectedResult = 1;

        //Act
        int result = address.hashCode();

        //Assert
        assertEquals(expectedResult, result);

    }

}
