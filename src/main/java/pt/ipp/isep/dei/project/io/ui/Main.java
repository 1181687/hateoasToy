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
        SensorType temperature = new SensorType("temperature");
        Sensor sensor = new Sensor("sensor1", date1, temperature, location);
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
        int option = -1;
        int userOption = -1;

        while (userOption != 0) {
            userOption = Menu.usersMenu();

            if (userOption == 1) {
                option = Menu.administratorMenu();
                while (option != 0) {

                    switch (option) {
                        case 1:
                            US1UI ui = new US1UI(geoAreaTypeList);
                            ui.run();
                            break;
                        case 2:
                            US2UI ui2 = new US2UI(geoAreaTypeList);
                            ui2.run();
                            break;
                        case 3:
                            US3UI ui3 = new US3UI(geoAreaList, geoAreaTypeList);
                            ui3.run();
                            break;
                        case 4:
                            US4UI ui4 = new US4UI(geoAreaList, geoAreaTypeList);
                            ui4.run();
                            break;
                        case 5:
                            US5UI ui5 = new US5UI(sensorTypeList);
                            ui5.run();
                            break;
                        case 6:
                            US6UI ui6 = new US6UI(geoAreaList, sensorTypeList);
                            ui6.run();
                            break;
                        case 7:
                            US7UI ui7 = new US7UI(geoAreaList);
                            ui7.run();
                            break;
                        case 8:
                            US8UI ui8 = new US8UI(geoAreaList);
                            ui8.run();
                            break;
                        case 9:
                            US101UI ui9 = new US101UI(house);
                            ui9.run();
                            break;
                        case 10:
                            US105UI ui105 = new US105UI(house);
                            ui105.run();
                            break;
                        case 12:
                            US130UI ui130 = new US130UI(gridList);
                            ui130.run();
                            break;
                        case 13:
                            US135UI ui135 = new US135UI(gridList, powerSourceTypeList);
                            ui135.run();
                            break;
                        case 11:
                            US108UI ui108 = new US108UI(roomList);
                            ui108.run();
                            break;
                        case 15:
                            US147UI ui147 = new US147UI(gridList, roomList);
                            ui147.run();
                            break;
                        case 16:
                            US149UI ui149 = new US149UI(gridList, roomList);
                            ui149.run();
                            break;
                        case 17:
                            US253UI ui253 = new US253UI(house, roomList, sensorTypeList);
                            ui253.run();
                            break;
                    }
                    option = Menu.administratorMenu();
                }
            }
            if (userOption == 2) {
                option = Menu.regularUserMenu();

                while (option != 0) {

                    switch (option) {
                        case 1:
                            US1UI ui = new US1UI(geoAreaTypeList);
                            ui.run();
                            break;
                        case 2:
                            US2UI ui2 = new US2UI(geoAreaTypeList);
                            ui2.run();
                            break;
                        case 3:
                            US3UI ui3 = new US3UI(geoAreaList, geoAreaTypeList);
                            ui3.run();
                            break;
                        case 4:
                            US4UI ui4 = new US4UI(geoAreaList, geoAreaTypeList);
                            ui4.run();
                            break;
                        case 5:
                            US5UI ui5 = new US5UI(sensorTypeList);
                            ui5.run();
                            break;
                        case 6:
                            US6UI ui6 = new US6UI(geoAreaList, sensorTypeList);
                            ui6.run();
                            break;
                        case 7:
                            US7UI ui7 = new US7UI(geoAreaList);
                            ui7.run();
                            break;
                        case 8:
                            US8UI ui8 = new US8UI(geoAreaList);
                            ui8.run();
                            break;
                        case 9:
                            US101UI ui9 = new US101UI(house);
                            ui9.run();
                            break;
                        /*case 10:
                            US600UI ui10 = new US600UI(house, sensorTypeList);
                            ui10.run();
                            break;*/
                        case 11:
                            US605UI ui11 = new US605UI(house, sensorTypeList);
                            ui11.run1();
                            break;
                    }
                    option = Menu.regularUserMenu();
                }
            }
        }
    }
}
