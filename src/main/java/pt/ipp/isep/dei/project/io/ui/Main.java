package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        GeoAreaTypeList geoAreaTypeList = new GeoAreaTypeList();
        GeoAreaList geoAreaList = new GeoAreaList();
        RoomList roomList = new RoomList();
        HouseGridList gridList = new HouseGridList();

        // mock objects
        Location location = new Location(0.0, 0.0, 0.0);
        Address address = new Address("0000", location);
        SensorTypeList sensorTypeList = new SensorTypeList();
        RectangleArea rectangleArea = new RectangleArea(20, 20, location);
        GeoAreaType geoAreaType = new GeoAreaType("Cidade");
        GeographicalArea insertedGeoArea = new GeographicalArea("Porto", geoAreaType, location, rectangleArea);

        //mock objects for test US605
        Calendar calendar0 = new GregorianCalendar(2018, 11, 1, 15, 00, 00);
        Date date1 = calendar0.getTime();
        Calendar calendar1 = new GregorianCalendar(2018, 11, 1, 16, 00, 00);
        Date date2 = calendar1.getTime();
        SensorType sensorTypeTemperature = new SensorType("temperature");
        Sensor sensor = new Sensor("sensor1", date1, sensorTypeTemperature, location);
        Measurement temp1 = new Measurement(20, date1);
        Measurement temp2 = new Measurement(22, date2);
        sensor.addMeasurementToList(temp1);
        sensor.addMeasurementToList(temp2);
        Dimensions dimensions = new Dimensions(300, 600, 600);
        Room room = new Room("room1", 1, dimensions);
        room.getSensorList().addSensorToTheListOfSensors(sensor);
        House house = new House(roomList, gridList, address, insertedGeoArea);
        house.addRoomToHouse(room);
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        PowerSourceType powerSourceType2 = new PowerSourceType("Public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType1);
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType2);

        //UI levels
        Admin admin = new Admin(geoAreaTypeList, geoAreaList, sensorTypeList, house, powerSourceTypeList, roomList, gridList);
        RegularUser regularUser = new RegularUser(geoAreaTypeList, geoAreaList, sensorTypeList, house, sensorTypeTemperature);

        int option = -1;
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
