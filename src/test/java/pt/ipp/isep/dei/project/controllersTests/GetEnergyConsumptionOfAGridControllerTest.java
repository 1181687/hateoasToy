package pt.ipp.isep.dei.project.controllersTests;

import org.junit.jupiter.api.BeforeEach;
import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfAGridController;
import pt.ipp.isep.dei.project.model.*;

public class GetEnergyConsumptionOfAGridControllerTest {

    private GetNominalPowerOfAGridController controller;
    private HouseGridList houseGridList;

    @BeforeEach
    public void StartUp(){
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeoAreaType geoAreaType = new GeoAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geoAreaType, location, areaShape);

        //House
        RoomList roomList = new RoomList();
        this.houseGridList = new HouseGridList();
        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        House houseEdificioB = new House(roomList, houseGridList, address, insertedGeoArea);

        this.controller = new GetNominalPowerOfAGridController(houseEdificioB);
    }


}
