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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfHouseLocationControllerTest {
    private ConfHouseLocationController ctrl;
    private GeographicalArea isep;

    @BeforeEach
    public void StartUp() {
        //Geographical Area List
        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();

        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        isep = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);
        geographicalAreaList.addGeoArea(isep);

        //House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList("Configuration.properties", "devicetype.count", "devicetype.name");

        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

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

}
