package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile("MeteringPeriodDevice"));
        if (1440 % meteringPeriodGrid != 0) {
            System.out.println("The grid metering period is not valid. Please configure the grid metering period for a valid value.");
            return;
        }

        if (1440 % meteringPeriodDevice != 0 || meteringPeriodDevice % meteringPeriodGrid != 0) {
            System.out.println("The device metering period is not valid. Please configure the device metering period for a valid value.");
            return;
        }

        GeographicalAreaTypeList geographicalAreaTypeList = new GeographicalAreaTypeList();
        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();
        RoomList roomList = new RoomList();
        DeviceList deviceList = new DeviceList();
        HouseGridList gridList = new HouseGridList();


        // GEOGRAPHICAL AREAS
        // Main Area
        Location locationMainArea = new Location(41.164077, -8.620802, 118);
        AreaShape areaShapeMainArea = new AreaShape(10.09, 3.30, locationMainArea);
        GeographicalAreaType geographicalAreaTypeMainArea = new GeographicalAreaType("City");
        GeographicalArea mainArea = new GeographicalArea("Porto", geographicalAreaTypeMainArea, locationMainArea, areaShapeMainArea);
        geographicalAreaList.addGeoArea(mainArea);

        // Inserted Geo Area (Campus do ISEP)
        Location location = new Location(41.178553, -8.608035, 111);
        SensorTypeList sensorTypeList = new SensorTypeList();
        AreaShape areaShape = new AreaShape(0.261, 0.249, location);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("Campus do ISEP", geographicalAreaType, location, areaShape);
        geographicalAreaList.addGeoArea(insertedGeoArea);
        insertedGeoArea.setInsertedIn(mainArea);


        // HOUSE
        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation);
        House houseEdificioB = new House(roomList, gridList, address, insertedGeoArea);
        houseEdificioB.getInsertedGeoArea().setInsertedIn(insertedGeoArea);


        // ReadingsS
        // General Dates
        LocalDateTime date = LocalDate.of(2018, 12, 29).atStartOfDay();
        LocalDateTime date1 = LocalDate.of(2018, 12, 30).atStartOfDay();
        LocalDateTime date2 = LocalDate.of(2018, 12, 31).atStartOfDay();
        LocalDateTime date3 = LocalDate.of(2019, 1, 1).atStartOfDay();
        LocalDateTime date4 = LocalDate.of(2019, 1, 2).atStartOfDay();
        LocalDateTime date5 = LocalDate.of(2019, 1, 2).atStartOfDay();
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 29, 00, 00, 00);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 29, 8, 00, 00);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 29, 16, 00, 00);
        LocalDateTime date9 = LocalDateTime.of(2019, 1, 30, 00, 00, 00);
        LocalDateTime date10 = LocalDateTime.of(2019, 1, 30, 8, 00, 00);
        LocalDateTime date11 = LocalDateTime.of(2019, 1, 30, 16, 00, 00);
        LocalDateTime date12 = LocalDateTime.of(2019, 1, 31, 00, 00, 00);
        LocalDateTime date13 = LocalDateTime.of(2019, 1, 31, 8, 00, 00);
        LocalDateTime date14 = LocalDateTime.of(2019, 1, 31, 16, 00, 00);
        LocalDateTime date15 = LocalDateTime.of(2019, 2, 1, 00, 00, 00);

        // Sensor 1
        Readings temp = new Readings(14.0, date1);
        Readings temp1 = new Readings(13.7, date1);
        Readings temp2 = new Readings(16.5, date1);
        Readings temp3 = new Readings(15.1, date1);
        Readings temp4 = new Readings(13.8, date2);
        Readings temp5 = new Readings(13.3, date2);
        Readings temp6 = new Readings(15.5, date2);
        Readings temp7 = new Readings(14.2, date2);
        Readings temp8 = new Readings(12.5, date3);
        Readings temp9 = new Readings(12.4, date3);
        Readings temp10 = new Readings(13.8, date3);
        Readings temp11 = new Readings(12.9, date3);
        Readings temp12 = new Readings(11.5, date4);
        Readings temp13 = new Readings(11.2, date4);
        Readings temp14 = new Readings(13.5, date4);
        Readings temp15 = new Readings(12.8, date4);

        // Sensor 2
        Readings temp16 = new Readings(84.0, date1);
        Readings temp17 = new Readings(85.7, date1);
        Readings temp18 = new Readings(76.5, date1);
        Readings temp19 = new Readings(78.1, date1);
        Readings temp20 = new Readings(83.8, date2);
        Readings temp21 = new Readings(83.9, date2);
        Readings temp22 = new Readings(75.5, date2);
        Readings temp23 = new Readings(77.2, date2);
        Readings temp24 = new Readings(82.5, date3);
        Readings temp25 = new Readings(82.4, date3);
        Readings temp26 = new Readings(73.8, date3);
        Readings temp27 = new Readings(72.9, date3);
        Readings temp28 = new Readings(80.5, date4);
        Readings temp29 = new Readings(79.2, date4);
        Readings temp30 = new Readings(71.5, date4);
        Readings temp31 = new Readings(72.8, date4);

        // Sensor 3
        Readings temp32 = new Readings(0.5, date);
        Readings temp33 = new Readings(1.2, date1);
        Readings temp34 = new Readings(1.5, date2);
        Readings temp35 = new Readings(0.3, date3);
        Readings temp36 = new Readings(0.0, date4);
        Readings temp37 = new Readings(0.0, date4);
        Readings temp38 = new Readings(0.0, date5);

        // Sensor 4
        Readings temp39 = new Readings(8.0, date1);
        Readings temp40 = new Readings(6.9, date1);
        Readings temp41 = new Readings(16.5, date1);
        Readings temp42 = new Readings(11.2, date1);
        Readings temp43 = new Readings(7.2, date2);
        Readings temp44 = new Readings(5.3, date2);
        Readings temp45 = new Readings(15.1, date2);
        Readings temp46 = new Readings(9.2, date2);
        Readings temp47 = new Readings(6.5, date3);
        Readings temp48 = new Readings(4.3, date3);
        Readings temp49 = new Readings(14.8, date3);
        Readings temp50 = new Readings(8.9, date3);
        Readings temp51 = new Readings(6.1, date4);
        Readings temp52 = new Readings(3.2, date4);
        Readings temp53 = new Readings(14.1, date4);
        Readings temp54 = new Readings(8.3, date4);

        // Device 1
        Readings deviceEC = new Readings(1.0, date6);
        Readings deviceEC1 = new Readings(2.9, date7);
        Readings deviceEC2 = new Readings(3.5, date8);
        Readings deviceEC3 = new Readings(1.2, date9);
        Readings deviceEC4 = new Readings(2.2, date10);
        Readings deviceEC5 = new Readings(3.3, date11);
        Readings deviceEC6 = new Readings(1.1, date12);
        Readings deviceEC7 = new Readings(2.2, date13);
        Readings deviceEC8 = new Readings(3.5, date14);
        Readings deviceEC9 = new Readings(1.3, date15);

        // Device 2
        Readings device2EC = new Readings(1.0, date6);
        Readings device2EC1 = new Readings(3.9, date7);
        Readings device2EC2 = new Readings(3.5, date8);
        Readings device2EC3 = new Readings(1.2, date9);
        Readings device2EC4 = new Readings(3.2, date10);
        Readings device2EC5 = new Readings(3.3, date11);
        Readings device2EC6 = new Readings(1.1, date12);
        Readings device2EC7 = new Readings(3.2, date13);
        Readings device2EC8 = new Readings(3.5, date14);
        Readings device2EC9 = new Readings(1.3, date15);


        // SENSORS
        // Sensor 1
        SensorType sensorTypeTemperature = new SensorType("temperature");
        LocalDateTime startingDate = LocalDate.of(2018, 10, 15).atStartOfDay();
        Sensor sensor = new Sensor("Temperature B109", startingDate, sensorTypeTemperature, houseLocation);
        sensor.addReadingsToList(temp);
        sensor.addReadingsToList(temp1);
        sensor.addReadingsToList(temp2);
        sensor.addReadingsToList(temp3);
        sensor.addReadingsToList(temp4);
        sensor.addReadingsToList(temp5);
        sensor.addReadingsToList(temp6);
        sensor.addReadingsToList(temp7);
        sensor.addReadingsToList(temp8);
        sensor.addReadingsToList(temp9);
        sensor.addReadingsToList(temp10);
        sensor.addReadingsToList(temp11);
        sensor.addReadingsToList(temp12);
        sensor.addReadingsToList(temp13);
        sensor.addReadingsToList(temp14);
        sensor.addReadingsToList(temp15);
        // Add sensor to the Inserted area
        insertedGeoArea.getSensorListInTheGeographicArea().addSensor(sensor);

        // Sensor 2
        SensorType sensorTypeHumidity = new SensorType("humidity");
        LocalDateTime startingDate2 = LocalDate.of(2018, 11, 22).atStartOfDay();
        Sensor sensor2 = new Sensor("Humidity B109", startingDate2, sensorTypeHumidity, houseLocation);
        sensor2.addReadingsToList(temp16);
        sensor2.addReadingsToList(temp17);
        sensor2.addReadingsToList(temp18);
        sensor2.addReadingsToList(temp19);
        sensor2.addReadingsToList(temp20);
        sensor2.addReadingsToList(temp21);
        sensor2.addReadingsToList(temp22);
        sensor2.addReadingsToList(temp23);
        sensor2.addReadingsToList(temp24);
        sensor2.addReadingsToList(temp25);
        sensor2.addReadingsToList(temp26);
        sensor2.addReadingsToList(temp27);
        sensor2.addReadingsToList(temp28);
        sensor2.addReadingsToList(temp29);
        sensor2.addReadingsToList(temp30);
        sensor2.addReadingsToList(temp31);

        // Sensor 3
        Location locationOfAreaSensors = new Location(41.179230, -8.606409, 125);
        SensorType sensorTypeRainfall = new SensorType("rainfall");
        LocalDateTime startingDate3 = LocalDate.of(2016, 11, 15).atStartOfDay();
        Sensor sensor3 = new Sensor("Meteo station ISEP - rainfall", startingDate3, sensorTypeRainfall, locationOfAreaSensors);
        sensor3.addReadingsToList(temp32);
        sensor3.addReadingsToList(temp33);
        sensor3.addReadingsToList(temp34);
        sensor3.addReadingsToList(temp35);
        sensor3.addReadingsToList(temp36);
        sensor3.addReadingsToList(temp37);
        sensor3.addReadingsToList(temp38);
        insertedGeoArea.getSensorListInTheGeographicArea().addSensor(sensor3);

        // Sensor 4
        SensorType sensorTypeTemp = new SensorType("temperature");
        LocalDateTime startingDate4 = LocalDate.of(2016, 11, 15).atStartOfDay();
        Sensor sensor4 = new Sensor("Meteo station ISEP - rainfall", startingDate4, sensorTypeTemp, locationOfAreaSensors);
        sensor4.addReadingsToList(temp39);
        sensor4.addReadingsToList(temp40);
        sensor4.addReadingsToList(temp41);
        sensor4.addReadingsToList(temp42);
        sensor4.addReadingsToList(temp43);
        sensor4.addReadingsToList(temp44);
        sensor4.addReadingsToList(temp45);
        sensor4.addReadingsToList(temp46);
        sensor4.addReadingsToList(temp47);
        sensor4.addReadingsToList(temp48);
        sensor4.addReadingsToList(temp49);
        sensor4.addReadingsToList(temp50);
        sensor4.addReadingsToList(temp51);
        sensor4.addReadingsToList(temp52);
        sensor4.addReadingsToList(temp53);
        sensor4.addReadingsToList(temp54);
        insertedGeoArea.getSensorListInTheGeographicArea().addSensor(sensor4);


        // ROOMS
        // Room 1
        String name = "B107";
        int houseFloor = 1;
        double height = 3.5;
        double length = 11;
        double width = 7;
        Dimension dimension = new Dimension(height, length, width);
        Room room1 = new Room(name, houseFloor, dimension);
        houseEdificioB.addRoom(room1);

        // Room 2
        String name2 = "B109";
        Room room2 = new Room(name2, houseFloor, dimension);
        houseEdificioB.addRoom(room2);
        room2.addSensorToListOfSensorsInRoom(sensor);
        room2.addSensorToListOfSensorsInRoom(sensor2);

        // Room 3
        String name3 = "B106";
        int houseFloor3 = 1;
        double height3 = 3.5;
        double length3 = 13;
        double width3 = 7;
        Dimension dimension3 = new Dimension(height3, length3, width3);
        Room room3 = new Room(name3, houseFloor3, dimension3);
        houseEdificioB.addRoom(room3);


        // LISTS OF PROGRAMS
        double durationNotAsked = 30;
        // Dishwasher B109
        Program program1 = new Program("Glasses", durationNotAsked, 0.9);
        Program program2 = new Program("Eco", durationNotAsked, 1.3);
        Program program3 = new Program("Eco turbo", durationNotAsked, 1.7);
        Program program4 = new Program("Dishes", durationNotAsked, 2.1);
        ProgramList dishwasherPrograms = new ProgramList();
        dishwasherPrograms.addProgram(program1);
        dishwasherPrograms.addProgram(program2);
        dishwasherPrograms.addProgram(program3);
        dishwasherPrograms.addProgram(program4);

        // Washing Machine B109
        Program program5 = new Program("Wool", durationNotAsked, 0.9);
        Program program6 = new Program("Fast", durationNotAsked, 1.3);
        Program program7 = new Program("Fast plus", durationNotAsked, 1.7);
        Program program8 = new Program("Synthetic 30º", durationNotAsked, 2.1);
        ProgramList washingMachinePrograms = new ProgramList();
        washingMachinePrograms.addProgram(program5);
        washingMachinePrograms.addProgram(program6);
        washingMachinePrograms.addProgram(program7);
        washingMachinePrograms.addProgram(program8);

        // Dishwasher B106
        Program program9 = new Program("Glasses", durationNotAsked, 0.8);
        Program program10 = new Program("Light", durationNotAsked, 1.3);
        Program program11 = new Program("Light turbo", durationNotAsked, 1.9);
        Program program12 = new Program("Dishes", durationNotAsked, 2.3);
        ProgramList dishwasherPrograms2 = new ProgramList();
        dishwasherPrograms2.addProgram(program9);
        dishwasherPrograms2.addProgram(program10);
        dishwasherPrograms2.addProgram(program11);
        dishwasherPrograms2.addProgram(program12);


        // DEVICES
        // Room 2
        // Electric Water Heater
        DeviceSpecs electricWaterHeater = new ElectricWaterHeaterSpecs(55, 100,
                0.91, 1.5);
        Device device = new Device("EHW B109", room2, electricWaterHeater);
        room2.addDevice(device);
        deviceList.addDevice(device);
        device.addReadingsToTheList(deviceEC);
        device.addReadingsToTheList(deviceEC1);
        device.addReadingsToTheList(deviceEC2);
        device.addReadingsToTheList(deviceEC3);
        device.addReadingsToTheList(deviceEC4);
        device.addReadingsToTheList(deviceEC5);
        device.addReadingsToTheList(deviceEC6);
        device.addReadingsToTheList(deviceEC7);
        device.addReadingsToTheList(deviceEC8);
        device.addReadingsToTheList(deviceEC9);

        // Dishwasher
        DeviceSpecs dishWasher = new DishWasherSpecs(50, 1.5, dishwasherPrograms);
        Device device2 = new Device("Dishwasher B109", room2, dishWasher);
        room2.addDevice(device2);
        deviceList.addDevice(device2);
        device.addReadingsToTheList(device2EC);
        device.addReadingsToTheList(device2EC1);
        device.addReadingsToTheList(device2EC2);
        device.addReadingsToTheList(device2EC3);
        device.addReadingsToTheList(device2EC4);
        device.addReadingsToTheList(device2EC5);
        device.addReadingsToTheList(device2EC6);
        device.addReadingsToTheList(device2EC7);
        device.addReadingsToTheList(device2EC8);
        device.addReadingsToTheList(device2EC9);

        // Washing Machine
        DeviceSpecs washingMachine = new WashingMachineSpecs(10, 2.5, washingMachinePrograms);
        Device device3 = new Device("Washing Machine B109", room2, washingMachine);
        room2.addDevice(device3);
        deviceList.addDevice(device3);

        // Room 3
        // Electric Water Heater
        DeviceSpecs electricWaterHeater2 = new ElectricWaterHeaterSpecs(55, 150,
                0.92, 2.2);
        Device device4 = new Device("EHW B106", room3, electricWaterHeater2);
        room3.addDevice(device4);
        deviceList.addDevice(device4);

        // Dishwasher
        DeviceSpecs dishWasher2 = new DishWasherSpecs(50, 1.4, dishwasherPrograms2);
        Device device5 = new Device("Dishwasher B106", room3, dishWasher2);
        room3.addDevice(device5);
        deviceList.addDevice(device5);


        // ROOM LIST
        roomList.addRoom(room1);
        roomList.addRoom(room2);
        roomList.addRoom(room3);


        // HOUSE GRID
        String houseGridName = "main grid";
        double maximumContractedPower = 200;
        HouseGrid houseGrid = new HouseGrid(houseGridName, maximumContractedPower, roomList);
        gridList.addHouseGrid(houseGrid);

        // POWER SOURCES
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        PowerSourceType powerSourceType2 = new PowerSourceType("Public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType1);
        powerSourceTypeList.addPowerSourceType(powerSourceType2);

        //UI levels
        Admin admin = new Admin(geographicalAreaTypeList, geographicalAreaList, deviceList, sensorTypeList, houseEdificioB, powerSourceTypeList, roomList, gridList);
        RegularUser regularUser = new RegularUser(geographicalAreaTypeList, geographicalAreaList, sensorTypeList, houseEdificioB, sensorTypeTemperature);
        PowerUser powerUser = new PowerUser(houseEdificioB);
        RoomOwner roomOwner = new RoomOwner(houseEdificioB);

        int option = Menu.usersMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {
            switch (option) {
                case 1:
                    admin.runAdminOption();
                    break;
                case 2:
                    regularUser.runRegularUserOption();
                    break;
                case 3:
                    powerUser.runPowerUserMenu();
                    break;
                case 4:
                    roomOwner.runRoomOwnerMenu();
                    break;
            }
            option = Menu.usersMenu();
        }

    }
}