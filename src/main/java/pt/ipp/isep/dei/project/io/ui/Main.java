package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        GeoAreaTypeList geoAreaTypeList = new GeoAreaTypeList();
        GeoAreaList geoAreaList = new GeoAreaList();
        RoomList roomList = new RoomList();
        HouseGridList gridList = new HouseGridList();

        // MOCK OBJECTS
        //Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        SensorTypeList sensorTypeList = new SensorTypeList();
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeoAreaType geoAreaType = new GeoAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geoAreaType, location, areaShape);
        geoAreaList.addGeoAreaInASpecificPosition(0, insertedGeoArea);

        // Sensor
        LocalDateTime date1 = LocalDate.of(2018, 12, 1).atTime(15, 00, 00);
        LocalDateTime date2 = LocalDate.of(2019, 12, 1).atTime(16, 00, 00);
        SensorType sensorTypeTemperature = new SensorType("temperature");
        Sensor sensor = new Sensor("sensor1", date1, sensorTypeTemperature, location);
        Measurement temp1 = new Measurement(20, date1);
        Measurement temp2 = new Measurement(22, date2);
        sensor.addMeasurementToList(temp1);
        sensor.addMeasurementToList(temp2);
        // Add sensor to the Inserted area
        insertedGeoArea.getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(sensor);

        // House
        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        House houseEdificioB = new House(roomList, gridList, address, insertedGeoArea);
        houseEdificioB.getmInsertedGeoArea().setInsertedIn(insertedGeoArea);

        // Rooms

        String name = "B107";
        int houseFloor = 1;
        double height = 3.5;
        double length = 11;
        double width = 7;
        Dimensions dimensions = new Dimensions(height, length, width);
        Room room1 = new Room(name, houseFloor, dimensions);
        houseEdificioB.addRoom(room1);
        room1.addSensorToTheListOfSensorsInTheRoom(sensor);
        houseEdificioB.addRoom(room1);

        String name2 = "B109";
        Room room2 = new Room(name2, houseFloor, dimensions);
        houseEdificioB.addRoom(room2);

        // Power Source Type (and List)
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        PowerSourceType powerSourceType2 = new PowerSourceType("Public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType1);
        powerSourceTypeList.addPowerSourceType(powerSourceType2);

        //UI levels
        Admin admin = new Admin(geoAreaTypeList, geoAreaList, sensorTypeList, houseEdificioB, powerSourceTypeList, roomList, gridList);
        RegularUser regularUser = new RegularUser(geoAreaTypeList, geoAreaList, sensorTypeList, houseEdificioB, sensorTypeTemperature);

        int userOption = -1;

        while (userOption != 0) {
            userOption = Menu.usersMenu();

            if (userOption == 1) {
                admin.runAdminOption();
            }
            if (userOption == 2) {
                regularUser.runRegularUserOption();
            }
        }
    }
}