package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.controllers.ConfHouseLocationController;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfHouseLocationControllerTest {
    private ConfHouseLocationController ctrl;
    private House house;

    @BeforeEach
    public void StartUp() {
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        this.house.setAddress(address);
        this.house.setInsertedGeoArea(insertedGeoArea);

        ctrl = new ConfHouseLocationController(house);

    }

    @Test
    public void testDefineNewAddress() {
        //Arrange
        String zipCodeNewAddress = "4150";
        double latitudeNewAddress = 43.5;
        double longitudeNewAddress = 51.5;
        double altitudeNewAddress = 180.0;
        Location localNewAddress = new Location(latitudeNewAddress, longitudeNewAddress, altitudeNewAddress);
        Address newAddress = new Address(zipCodeNewAddress, localNewAddress);

        ctrl.defineNewAddress(zipCodeNewAddress, latitudeNewAddress, longitudeNewAddress, altitudeNewAddress);
        ctrl.setAddress();

        //Act
        boolean result = ctrl.getHouse().getAddress().equals(newAddress);

        //Assert
        assertTrue(result);
    }

}
