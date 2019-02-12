package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String configFile = "Configuration.properties";

        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(configFile, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(configFile, "MeteringPeriodDevice"));
        if (1440 % meteringPeriodGrid != 0) {
            System.out.println("The grid metering period is not valid. Please configure the grid metering period for a valid value.");
            return;
        }

        if (1440 % meteringPeriodDevice != 0 || meteringPeriodDevice % meteringPeriodGrid != 0) {
            System.out.println("The device metering period is not valid. Please configure the device metering period for a valid value.");
            return;
        }
        List<String> deviceTypeList = Utils.readConfigFileToList(configFile, "devicetype.count", "devicetype.name");

        GeographicalAreaTypeList geographicalAreaTypeList = new GeographicalAreaTypeList();
        GeographicalAreaList geographicalAreaList = new GeographicalAreaList();
        List<Device> deviceList = new ArrayList<>();


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
        House houseEdificioB = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        houseEdificioB.setAddress(address);
        houseEdificioB.setInsertedGeoArea(insertedGeoArea);


        // READINGS
        // Dates for the Sensors
        LocalDateTime sensorDate = LocalDate.of(2018, 12, 29).atStartOfDay();
        LocalDateTime sensorDate1 = LocalDate.of(2018, 12, 30).atStartOfDay();
        LocalDateTime sensorDate2 = LocalDate.of(2018, 12, 31).atStartOfDay();
        LocalDateTime sensorDate3 = LocalDate.of(2019, 1, 1).atStartOfDay();
        LocalDateTime sensorDate4 = LocalDate.of(2019, 1, 2).atStartOfDay();
        LocalDateTime sensorDate5 = LocalDate.of(2019, 1, 3).atStartOfDay();

        // Sensor 1
        Readings temp = new Readings(14.0, sensorDate1);
        Readings temp1 = new Readings(13.7, sensorDate1);
        Readings temp2 = new Readings(16.5, sensorDate1);
        Readings temp3 = new Readings(15.1, sensorDate1);
        Readings temp4 = new Readings(13.8, sensorDate2);
        Readings temp5 = new Readings(13.3, sensorDate2);
        Readings temp6 = new Readings(15.5, sensorDate2);
        Readings temp7 = new Readings(14.2, sensorDate2);
        Readings temp8 = new Readings(12.5, sensorDate3);
        Readings temp9 = new Readings(12.4, sensorDate3);
        Readings temp10 = new Readings(13.8, sensorDate3);
        Readings temp11 = new Readings(12.9, sensorDate3);
        Readings temp12 = new Readings(11.5, sensorDate4);
        Readings temp13 = new Readings(11.2, sensorDate4);
        Readings temp14 = new Readings(13.5, sensorDate4);
        Readings temp15 = new Readings(12.8, sensorDate4);

        // Sensor 2
        Readings temp16 = new Readings(84.0, sensorDate1);
        Readings temp17 = new Readings(85.7, sensorDate1);
        Readings temp18 = new Readings(76.5, sensorDate1);
        Readings temp19 = new Readings(78.1, sensorDate1);
        Readings temp20 = new Readings(83.8, sensorDate2);
        Readings temp21 = new Readings(83.9, sensorDate2);
        Readings temp22 = new Readings(75.5, sensorDate2);
        Readings temp23 = new Readings(77.2, sensorDate2);
        Readings temp24 = new Readings(82.5, sensorDate3);
        Readings temp25 = new Readings(82.4, sensorDate3);
        Readings temp26 = new Readings(73.8, sensorDate3);
        Readings temp27 = new Readings(72.9, sensorDate3);
        Readings temp28 = new Readings(80.5, sensorDate4);
        Readings temp29 = new Readings(79.2, sensorDate4);
        Readings temp30 = new Readings(71.5, sensorDate4);
        Readings temp31 = new Readings(72.8, sensorDate4);

        // Sensor 3
        Readings temp32 = new Readings(0.5, sensorDate);
        Readings temp33 = new Readings(1.2, sensorDate1);
        Readings temp34 = new Readings(1.5, sensorDate2);
        Readings temp35 = new Readings(0.3, sensorDate3);
        Readings temp36 = new Readings(0.0, sensorDate4);
        Readings temp37 = new Readings(0.0, sensorDate4);
        Readings temp38 = new Readings(0.0, sensorDate5);

        // Sensor 4
        Readings temp39 = new Readings(8.0, sensorDate1);
        Readings temp40 = new Readings(6.9, sensorDate1);
        Readings temp41 = new Readings(16.5, sensorDate1);
        Readings temp42 = new Readings(11.2, sensorDate1);
        Readings temp43 = new Readings(7.2, sensorDate2);
        Readings temp44 = new Readings(5.3, sensorDate2);
        Readings temp45 = new Readings(15.1, sensorDate2);
        Readings temp46 = new Readings(9.2, sensorDate2);
        Readings temp47 = new Readings(6.5, sensorDate3);
        Readings temp48 = new Readings(4.3, sensorDate3);
        Readings temp49 = new Readings(14.8, sensorDate3);
        Readings temp50 = new Readings(8.9, sensorDate3);
        Readings temp51 = new Readings(6.1, sensorDate4);
        Readings temp52 = new Readings(3.2, sensorDate4);
        Readings temp53 = new Readings(14.1, sensorDate4);
        Readings temp54 = new Readings(8.3, sensorDate4);

        // Electric Water Heater B107/B109
        LocalDateTime ewhDate = LocalDateTime.of(2018, 12, 31, 8, 00, 00);
        LocalDateTime ewhDate1 = LocalDateTime.of(2018, 12, 31, 8, 15, 00);
        LocalDateTime ewhDate2 = LocalDateTime.of(2018, 12, 31, 8, 30, 00);
        LocalDateTime ewhDate3 = LocalDateTime.of(2018, 12, 31, 8, 45, 00);
        LocalDateTime ewhDate4 = LocalDateTime.of(2018, 12, 31, 9, 00, 00);
        LocalDateTime ewhDate5 = LocalDateTime.of(2018, 12, 31, 9, 15, 00);
        LocalDateTime ewhDate6 = LocalDateTime.of(2018, 12, 31, 10, 30, 00);
        LocalDateTime ewhDate7 = LocalDateTime.of(2018, 12, 31, 10, 45, 00);
        LocalDateTime ewhDate8 = LocalDateTime.of(2018, 12, 31, 12, 00, 00);
        LocalDateTime ewhDate9 = LocalDateTime.of(2018, 12, 31, 12, 15, 00);
        LocalDateTime ewhDate10 = LocalDateTime.of(2018, 12, 31, 12, 30, 00);
        LocalDateTime ewhDate11 = LocalDateTime.of(2018, 12, 31, 12, 45, 00);
        LocalDateTime ewhDate12 = LocalDateTime.of(2018, 12, 31, 13, 00, 00);
        LocalDateTime ewhDate13 = LocalDateTime.of(2018, 12, 31, 13, 30, 00);
        LocalDateTime ewhDate14 = LocalDateTime.of(2018, 12, 31, 20, 15, 00);
        LocalDateTime ewhDate15 = LocalDateTime.of(2018, 12, 31, 20, 30, 00);
        LocalDateTime ewhDate16 = LocalDateTime.of(2018, 12, 31, 20, 45, 00);
        LocalDateTime ewhDate17 = LocalDateTime.of(2018, 12, 31, 21, 00, 00);
        LocalDateTime ewhDate18 = LocalDateTime.of(2018, 12, 31, 21, 15, 00);
        Readings ewhEC = new Readings(0.2, ewhDate);
        Readings ewhEC1 = new Readings(0.375, ewhDate1);
        Readings ewhEC2 = new Readings(0.375, ewhDate2);
        Readings ewhEC3 = new Readings(0.375, ewhDate3);
        Readings ewhEC4 = new Readings(0.375, ewhDate4);
        Readings ewhEC5 = new Readings(0.25, ewhDate5);
        Readings ewhEC6 = new Readings(0.2, ewhDate6);
        Readings ewhEC7 = new Readings(0.2, ewhDate7);
        Readings ewhEC8 = new Readings(0.2, ewhDate8);
        Readings ewhEC9 = new Readings(0.375, ewhDate9);
        Readings ewhEC10 = new Readings(0.375, ewhDate10);
        Readings ewhEC11 = new Readings(0.375, ewhDate11);
        Readings ewhEC12 = new Readings(0.375, ewhDate12);
        Readings ewhEC13 = new Readings(0.2, ewhDate13);
        Readings ewhEC14 = new Readings(0.1, ewhDate14);
        Readings ewhEC15 = new Readings(0.375, ewhDate15);
        Readings ewhEC16 = new Readings(0.375, ewhDate16);
        Readings ewhEC17 = new Readings(0.375, ewhDate17);
        Readings ewhEC18 = new Readings(0.15, ewhDate18);
        Readings ewh1EC = new Readings(0.2, ewhDate);
        Readings ewh1EC1 = new Readings(0.5, ewhDate1);
        Readings ewh1EC2 = new Readings(0.5, ewhDate2);
        Readings ewh1EC3 = new Readings(0.5, ewhDate3);
        Readings ewh1EC4 = new Readings(0.5, ewhDate4);
        Readings ewh1EC5 = new Readings(0.25, ewhDate5);
        Readings ewh1EC6 = new Readings(0.2, ewhDate6);
        Readings ewh1EC7 = new Readings(0.2, ewhDate7);
        Readings ewh1EC8 = new Readings(0.2, ewhDate8);
        Readings ewh1EC9 = new Readings(0.5, ewhDate9);
        Readings ewh1EC10 = new Readings(0.5, ewhDate10);
        Readings ewh1EC11 = new Readings(0.5, ewhDate11);
        Readings ewh1EC12 = new Readings(0.5, ewhDate12);
        Readings ewh1EC13 = new Readings(0.2, ewhDate13);
        Readings ewh1EC14 = new Readings(0.1, ewhDate14);
        Readings ewh1EC15 = new Readings(0.5, ewhDate15);
        Readings ewh1EC16 = new Readings(0.5, ewhDate16);
        Readings ewh1EC17 = new Readings(0.5, ewhDate17);
        Readings ewh1EC18 = new Readings(0.15, ewhDate18);

        // Dishwasher B107
        LocalDateTime dwDate = LocalDateTime.of(2018, 12, 31, 13, 00, 00);
        LocalDateTime dwDate1 = LocalDateTime.of(2018, 12, 31, 13, 15, 00);
        LocalDateTime dwDate2 = LocalDateTime.of(2018, 12, 31, 13, 30, 00);
        LocalDateTime dwDate3 = LocalDateTime.of(2018, 12, 31, 13, 45, 00);
        LocalDateTime dwDate4 = LocalDateTime.of(2018, 12, 31, 20, 15, 00);
        LocalDateTime dwDate5 = LocalDateTime.of(2018, 12, 31, 21, 15, 00);
        LocalDateTime dwDate6 = LocalDateTime.of(2018, 12, 31, 21, 30, 00);
        LocalDateTime dwDate7 = LocalDateTime.of(2018, 12, 31, 21, 45, 00);
        LocalDateTime dwDate8 = LocalDateTime.of(2018, 12, 31, 22, 00, 00);
        LocalDateTime dwDate9 = LocalDateTime.of(2018, 12, 31, 22, 15, 00);
        Readings dwEC = new Readings(0.2, dwDate);
        Readings dwEC1 = new Readings(0.3, dwDate1);
        Readings dwEC2 = new Readings(0.2, dwDate2);
        Readings dwEC3 = new Readings(0.2, dwDate3);
        Readings dwEC4 = new Readings(0.2, dwDate4);
        Readings dwEC5 = new Readings(0.1, dwDate5);
        Readings dwEC6 = new Readings(0.1, dwDate6);
        Readings dwEC7 = new Readings(0.375, dwDate7);
        Readings dwEC8 = new Readings(0.375, dwDate8);
        Readings dwEC9 = new Readings(0.2, dwDate9);

        // Washing Machine B107
        LocalDateTime wmDate = LocalDateTime.of(2018, 12, 31, 10, 30, 00);
        LocalDateTime wdDate1 = LocalDateTime.of(2018, 12, 31, 10, 15, 00);
        LocalDateTime wdDate2 = LocalDateTime.of(2018, 12, 31, 13, 30, 00);
        LocalDateTime wdDate3 = LocalDateTime.of(2018, 12, 31, 13, 45, 00);
        Readings wmEC = new Readings(0.4, wmDate);
        Readings wmEC1 = new Readings(0.2, wdDate1);
        Readings wmEC2 = new Readings(0.25, wdDate2);
        Readings wmEC3 = new Readings(0.25, wdDate3);

        // Washing Machine B109
        LocalDateTime wm1Date = LocalDateTime.of(2018, 12, 31, 10, 15, 00);
        LocalDateTime wm1Date1 = LocalDateTime.of(2018, 12, 31, 10, 30, 00);
        LocalDateTime wm1Date2 = LocalDateTime.of(2018, 12, 31, 10, 45, 00);
        LocalDateTime wm1Date3 = LocalDateTime.of(2018, 12, 31, 11, 00, 00);
        Readings wm1EC = new Readings(0.4, wm1Date);
        Readings wm1EC1 = new Readings(0.2, wm1Date1);
        Readings wm1EC2 = new Readings(0.2, wm1Date2);
        Readings wm1EC3 = new Readings(0.25, wm1Date3);
        Readings wm1EC4 = new Readings(0.25, wm1Date3);


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
        SensorType sensorTypeRainfall = new SensorType("Rainfall");
        LocalDateTime startingDate3 = LocalDate.of(2016, 11, 15).atStartOfDay();
        Sensor sensor3 = new Sensor("Meteo station ISEP - Rainfall", startingDate3, sensorTypeRainfall, locationOfAreaSensors);
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
        // Dishwasher B107/B109
        Program program = new Program("Glasses", durationNotAsked, 0.9);
        Program program1 = new Program("Eco", durationNotAsked, 1.3);
        Program program2 = new Program("Eco turbo", durationNotAsked, 1.7);
        Program program3 = new Program("Dishes", durationNotAsked, 2.1);
        ProgramList dishwasherPrograms = new ProgramList();
        dishwasherPrograms.addProgram(program);
        dishwasherPrograms.addProgram(program1);
        dishwasherPrograms.addProgram(program2);
        dishwasherPrograms.addProgram(program3);

        // Washing Machine B107
        Program program4 = new Program("Wool", durationNotAsked, 1.1);
        Program program5 = new Program("Fast", durationNotAsked, 1.8);
        Program program6 = new Program("Fast plus", durationNotAsked, 2.7);
        Program program7 = new Program("Synthetic 30º", durationNotAsked, 2.8);
        ProgramList washingMachinePrograms = new ProgramList();
        washingMachinePrograms.addProgram(program4);
        washingMachinePrograms.addProgram(program5);
        washingMachinePrograms.addProgram(program6);
        washingMachinePrograms.addProgram(program7);

        // Washing Machine B109
        Program program8 = new Program("Wool", durationNotAsked, 0.9);
        Program program9 = new Program("Fast", durationNotAsked, 1.3);
        Program program10 = new Program("Fast plus", durationNotAsked, 1.7);
        Program program11 = new Program("Synthetic 30º", durationNotAsked, 2.1);
        ProgramList washingMachinePrograms1 = new ProgramList();
        washingMachinePrograms1.addProgram(program8);
        washingMachinePrograms1.addProgram(program9);
        washingMachinePrograms1.addProgram(program10);
        washingMachinePrograms1.addProgram(program11);

        // Dishwasher B106
        Program program12 = new Program("Glasses", durationNotAsked, 0.8);
        Program program13 = new Program("Light", durationNotAsked, 1.3);
        Program program14 = new Program("Light turbo", durationNotAsked, 1.9);
        Program program15 = new Program("Dishes", durationNotAsked, 2.3);
        ProgramList dishwasherPrograms1 = new ProgramList();
        dishwasherPrograms1.addProgram(program12);
        dishwasherPrograms1.addProgram(program13);
        dishwasherPrograms1.addProgram(program14);
        dishwasherPrograms1.addProgram(program15);


        // DEVICES
        // Electric Water Heater B107
        DeviceSpecs electricWaterHeater = new ElectricWaterHeaterSpecs(55, 100,
                0.91, 1.5);
        Device device = new Device("EHW B107", room1, electricWaterHeater);
        room1.addDevice(device);
        deviceList.add(device);
        device.addReadingsToTheList(ewhEC);
        device.addReadingsToTheList(ewhEC1);
        device.addReadingsToTheList(ewhEC2);
        device.addReadingsToTheList(ewhEC3);
        device.addReadingsToTheList(ewhEC4);
        device.addReadingsToTheList(ewhEC5);
        device.addReadingsToTheList(ewhEC6);
        device.addReadingsToTheList(ewhEC7);
        device.addReadingsToTheList(ewhEC8);
        device.addReadingsToTheList(ewhEC9);
        device.addReadingsToTheList(ewhEC10);
        device.addReadingsToTheList(ewhEC11);
        device.addReadingsToTheList(ewhEC12);
        device.addReadingsToTheList(ewhEC13);
        device.addReadingsToTheList(ewhEC14);
        device.addReadingsToTheList(ewhEC15);
        device.addReadingsToTheList(ewhEC16);
        device.addReadingsToTheList(ewhEC17);
        device.addReadingsToTheList(ewhEC18);

        // Dishwasher B107
        DeviceSpecs dishWasher = new DishWasherSpecs(50, 1.5, dishwasherPrograms);
        Device Device = new Device("Dishwasher B107", room1, dishWasher);
        room1.addDevice(device);
        deviceList.add(Device);
        Device.addReadingsToTheList(dwEC);
        Device.addReadingsToTheList(dwEC1);
        Device.addReadingsToTheList(dwEC2);
        Device.addReadingsToTheList(dwEC3);
        Device.addReadingsToTheList(dwEC4);
        Device.addReadingsToTheList(dwEC5);
        Device.addReadingsToTheList(dwEC6);
        Device.addReadingsToTheList(dwEC7);
        Device.addReadingsToTheList(dwEC8);
        Device.addReadingsToTheList(dwEC9);

        // Washing Machine B107
        DeviceSpecs washingMachine = new WashingMachineSpecs(10, 3.5, washingMachinePrograms);
        Device device2 = new Device("Washing Machine B107", room1, washingMachine);
        room1.addDevice(device2);
        deviceList.add(device2);
        device2.addReadingsToTheList(wmEC);
        device2.addReadingsToTheList(wmEC1);
        device2.addReadingsToTheList(wmEC2);
        device2.addReadingsToTheList(wmEC3);

        // Electric Water Heater B109
        DeviceSpecs electricWaterHeater1 = new ElectricWaterHeaterSpecs(55, 100,
                0.91, 1.5);
        Device device3 = new Device("EHW B109", room2, electricWaterHeater1);
        room2.addDevice(device3);
        deviceList.add(device3);
        device3.addReadingsToTheList(ewh1EC);
        device3.addReadingsToTheList(ewh1EC1);
        device3.addReadingsToTheList(ewh1EC2);
        device3.addReadingsToTheList(ewh1EC3);
        device3.addReadingsToTheList(ewh1EC4);
        device3.addReadingsToTheList(ewh1EC5);
        device3.addReadingsToTheList(ewh1EC6);
        device3.addReadingsToTheList(ewh1EC7);
        device3.addReadingsToTheList(ewh1EC8);
        device3.addReadingsToTheList(ewh1EC9);
        device3.addReadingsToTheList(ewh1EC10);
        device3.addReadingsToTheList(ewh1EC11);
        device3.addReadingsToTheList(ewh1EC12);
        device3.addReadingsToTheList(ewh1EC13);
        device3.addReadingsToTheList(ewh1EC14);
        device3.addReadingsToTheList(ewh1EC15);
        device3.addReadingsToTheList(ewh1EC16);
        device3.addReadingsToTheList(ewh1EC17);
        device3.addReadingsToTheList(ewh1EC18);

        // Dishwasher B019
        DeviceSpecs dishWasher1 = new DishWasherSpecs(50, 1.5, dishwasherPrograms);
        Device device4 = new Device("Dishwasher B109", room2, dishWasher1);
        room2.addDevice(device4);
        deviceList.add(device4);

        // Washing Machine B109
        DeviceSpecs washingMachine1 = new WashingMachineSpecs(10, 2.5, washingMachinePrograms1);
        Device device5 = new Device("Washing Machine B109", room2, washingMachine1);
        room2.addDevice(device5);
        deviceList.add(device5);
        device5.addReadingsToTheList(wm1EC);
        device5.addReadingsToTheList(wm1EC1);
        device5.addReadingsToTheList(wm1EC2);
        device5.addReadingsToTheList(wm1EC3);
        device5.addReadingsToTheList(wm1EC4);

        // Electric Water Heater 106
        DeviceSpecs electricWaterHeater2 = new ElectricWaterHeaterSpecs(55, 150,
                0.92, 2.2);
        Device device6 = new Device("EHW B106", room3, electricWaterHeater2);
        room3.addDevice(device6);
        deviceList.add(device6);

        // Dishwasher B106
        DeviceSpecs dishWasher2 = new DishWasherSpecs(50, 1.4, dishwasherPrograms1);
        Device device7 = new Device("Dishwasher B106", room3, dishWasher2);
        room3.addDevice(device7);
        deviceList.add(device7);


        // ROOM LIST
        houseEdificioB.addRoom(room1);
        houseEdificioB.addRoom(room2);
        houseEdificioB.addRoom(room3);


        // HOUSE GRID
        String houseGridName = "main grid";
        HouseGrid houseGrid = new HouseGrid(houseGridName);
        houseEdificioB.addGrid(houseGrid);
        houseGrid.attachRoom(room1);
        houseGrid.attachRoom(room2);
        houseGrid.attachRoom(room3);

        // POWER SOURCES
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        PowerSourceType powerSourceType2 = new PowerSourceType("Public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType1);
        powerSourceTypeList.addPowerSourceType(powerSourceType2);

        //UI levels
        Admin admin = new Admin(geographicalAreaTypeList, geographicalAreaList, sensorTypeList, houseEdificioB, powerSourceTypeList, houseEdificioB.getRoomList(), houseEdificioB.getHouseGridList());
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