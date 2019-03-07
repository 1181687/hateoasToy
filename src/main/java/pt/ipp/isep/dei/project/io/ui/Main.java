package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.utils.ReadJSONfile;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private static final String VOLUME_OF_WATER = "Volume Of Water To Heat";
    private static final String HOT_WATER_TEMPERATURE = "Hot-Water Temperature";
    private static final String NOMINAL_POWER = "Nominal Power";
    private static final String PERFORMANCE_RATIO = "Performance Ratio";
    private static final String CAPACITY = "Capacity";
    private static final String GLASSES = "Glasses";
    private static final String DISHES = "Dishes";

    private static final String ELECTRIC_WATER_HEATER = "Electric Water Heater";
    private static final String DISHWASHER = "Dishwasher";

    public static void main(String[] args) {

        ReadJSONfile.readJSONFileToList();

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

        // sensor 1
        Reading temp = new Reading(14.0, sensorDate1);
        Reading temp1 = new Reading(13.7, sensorDate1);
        Reading temp2 = new Reading(16.5, sensorDate1);
        Reading temp3 = new Reading(15.1, sensorDate1);
        Reading temp4 = new Reading(13.8, sensorDate2);
        Reading temp5 = new Reading(13.3, sensorDate2);
        Reading temp6 = new Reading(15.5, sensorDate2);
        Reading temp7 = new Reading(14.2, sensorDate2);
        Reading temp8 = new Reading(12.5, sensorDate3);
        Reading temp9 = new Reading(12.4, sensorDate3);
        Reading temp10 = new Reading(13.8, sensorDate3);
        Reading temp11 = new Reading(12.9, sensorDate3);
        Reading temp12 = new Reading(11.5, sensorDate4);
        Reading temp13 = new Reading(11.2, sensorDate4);
        Reading temp14 = new Reading(13.5, sensorDate4);
        Reading temp15 = new Reading(12.8, sensorDate4);

        // sensor 2
        Reading temp16 = new Reading(84.0, sensorDate1);
        Reading temp17 = new Reading(85.7, sensorDate1);
        Reading temp18 = new Reading(76.5, sensorDate1);
        Reading temp19 = new Reading(78.1, sensorDate1);
        Reading temp20 = new Reading(83.8, sensorDate2);
        Reading temp21 = new Reading(83.9, sensorDate2);
        Reading temp22 = new Reading(75.5, sensorDate2);
        Reading temp23 = new Reading(77.2, sensorDate2);
        Reading temp24 = new Reading(82.5, sensorDate3);
        Reading temp25 = new Reading(82.4, sensorDate3);
        Reading temp26 = new Reading(73.8, sensorDate3);
        Reading temp27 = new Reading(72.9, sensorDate3);
        Reading temp28 = new Reading(80.5, sensorDate4);
        Reading temp29 = new Reading(79.2, sensorDate4);
        Reading temp30 = new Reading(71.5, sensorDate4);
        Reading temp31 = new Reading(72.8, sensorDate4);

        // sensor 3
        Reading temp32 = new Reading(0.5, sensorDate);
        Reading temp33 = new Reading(1.2, sensorDate1);
        Reading temp34 = new Reading(1.5, sensorDate2);
        Reading temp35 = new Reading(0.3, sensorDate3);
        Reading temp36 = new Reading(0.0, sensorDate4);
        Reading temp37 = new Reading(0.0, sensorDate4);
        Reading temp38 = new Reading(0.0, sensorDate5);

        // sensor 4
        Reading temp39 = new Reading(8.0, sensorDate1);
        Reading temp40 = new Reading(6.9, sensorDate1);
        Reading temp41 = new Reading(16.5, sensorDate1);
        Reading temp42 = new Reading(11.2, sensorDate1);
        Reading temp43 = new Reading(7.2, sensorDate2);
        Reading temp44 = new Reading(5.3, sensorDate2);
        Reading temp45 = new Reading(15.1, sensorDate2);
        Reading temp46 = new Reading(9.2, sensorDate2);
        Reading temp47 = new Reading(6.5, sensorDate3);
        Reading temp48 = new Reading(4.3, sensorDate3);
        Reading temp49 = new Reading(14.8, sensorDate3);
        Reading temp50 = new Reading(8.9, sensorDate3);
        Reading temp51 = new Reading(6.1, sensorDate4);
        Reading temp52 = new Reading(3.2, sensorDate4);
        Reading temp53 = new Reading(14.1, sensorDate4);
        Reading temp54 = new Reading(8.3, sensorDate4);

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
        Reading ewhEC = new Reading(0.2, ewhDate);
        Reading ewhEC1 = new Reading(0.375, ewhDate1);
        Reading ewhEC2 = new Reading(0.375, ewhDate2);
        Reading ewhEC3 = new Reading(0.375, ewhDate3);
        Reading ewhEC4 = new Reading(0.375, ewhDate4);
        Reading ewhEC5 = new Reading(0.25, ewhDate5);
        Reading ewhEC6 = new Reading(0.2, ewhDate6);
        Reading ewhEC7 = new Reading(0.2, ewhDate7);
        Reading ewhEC8 = new Reading(0.2, ewhDate8);
        Reading ewhEC9 = new Reading(0.375, ewhDate9);
        Reading ewhEC10 = new Reading(0.375, ewhDate10);
        Reading ewhEC11 = new Reading(0.375, ewhDate11);
        Reading ewhEC12 = new Reading(0.375, ewhDate12);
        Reading ewhEC13 = new Reading(0.2, ewhDate13);
        Reading ewhEC14 = new Reading(0.1, ewhDate14);
        Reading ewhEC15 = new Reading(0.375, ewhDate15);
        Reading ewhEC16 = new Reading(0.375, ewhDate16);
        Reading ewhEC17 = new Reading(0.375, ewhDate17);
        Reading ewhEC18 = new Reading(0.15, ewhDate18);
        Reading ewh1EC = new Reading(0.2, ewhDate);
        Reading ewh1EC1 = new Reading(0.5, ewhDate1);
        Reading ewh1EC2 = new Reading(0.5, ewhDate2);
        Reading ewh1EC3 = new Reading(0.5, ewhDate3);
        Reading ewh1EC4 = new Reading(0.5, ewhDate4);
        Reading ewh1EC5 = new Reading(0.25, ewhDate5);
        Reading ewh1EC6 = new Reading(0.2, ewhDate6);
        Reading ewh1EC7 = new Reading(0.2, ewhDate7);
        Reading ewh1EC8 = new Reading(0.2, ewhDate8);
        Reading ewh1EC9 = new Reading(0.5, ewhDate9);
        Reading ewh1EC10 = new Reading(0.5, ewhDate10);
        Reading ewh1EC11 = new Reading(0.5, ewhDate11);
        Reading ewh1EC12 = new Reading(0.5, ewhDate12);
        Reading ewh1EC13 = new Reading(0.2, ewhDate13);
        Reading ewh1EC14 = new Reading(0.1, ewhDate14);
        Reading ewh1EC15 = new Reading(0.5, ewhDate15);
        Reading ewh1EC16 = new Reading(0.5, ewhDate16);
        Reading ewh1EC17 = new Reading(0.5, ewhDate17);
        Reading ewh1EC18 = new Reading(0.15, ewhDate18);

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
        Reading dwEC = new Reading(0.2, dwDate);
        Reading dwEC1 = new Reading(0.3, dwDate1);
        Reading dwEC2 = new Reading(0.2, dwDate2);
        Reading dwEC3 = new Reading(0.2, dwDate3);
        Reading dwEC4 = new Reading(0.2, dwDate4);
        Reading dwEC5 = new Reading(0.1, dwDate5);
        Reading dwEC6 = new Reading(0.1, dwDate6);
        Reading dwEC7 = new Reading(0.375, dwDate7);
        Reading dwEC8 = new Reading(0.375, dwDate8);
        Reading dwEC9 = new Reading(0.2, dwDate9);

        // Washing Machine B107
        LocalDateTime wmDate = LocalDateTime.of(2018, 12, 31, 10, 30, 00);
        LocalDateTime wdDate1 = LocalDateTime.of(2018, 12, 31, 10, 15, 00);
        LocalDateTime wdDate2 = LocalDateTime.of(2018, 12, 31, 13, 30, 00);
        LocalDateTime wdDate3 = LocalDateTime.of(2018, 12, 31, 13, 45, 00);
        Reading wmEC = new Reading(0.4, wmDate);
        Reading wmEC1 = new Reading(0.2, wdDate1);
        Reading wmEC2 = new Reading(0.25, wdDate2);
        Reading wmEC3 = new Reading(0.25, wdDate3);

        // Washing Machine B109
        LocalDateTime wm1Date = LocalDateTime.of(2018, 12, 31, 10, 15, 00);
        LocalDateTime wm1Date1 = LocalDateTime.of(2018, 12, 31, 10, 30, 00);
        LocalDateTime wm1Date2 = LocalDateTime.of(2018, 12, 31, 10, 45, 00);
        LocalDateTime wm1Date3 = LocalDateTime.of(2018, 12, 31, 11, 00, 00);
        Reading wm1EC = new Reading(0.4, wm1Date);
        Reading wm1EC1 = new Reading(0.2, wm1Date1);
        Reading wm1EC2 = new Reading(0.2, wm1Date2);
        Reading wm1EC3 = new Reading(0.25, wm1Date3);
        Reading wm1EC4 = new Reading(0.25, wm1Date3);


        // SENSORS
        // sensor 1
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

        // sensor 2
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

        // sensor 3
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

        // sensor 4
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



        // DEVICES
        double durationNotAsked = 30;

        // Electric Water Heater B107
        Device ewhB107 = houseEdificioB.createDevice(ELECTRIC_WATER_HEATER, "EHW B107", room1);
        ewhB107.setAttributesDevType(VOLUME_OF_WATER, 55);
        ewhB107.setAttributesDevType(HOT_WATER_TEMPERATURE, 100);
        ewhB107.setAttributesDevType(NOMINAL_POWER, 1.5);
        ewhB107.setAttributesDevType(PERFORMANCE_RATIO, 0.91);

        ewhB107.addReadingsToTheList(ewhEC);
        ewhB107.addReadingsToTheList(ewhEC1);
        ewhB107.addReadingsToTheList(ewhEC2);
        ewhB107.addReadingsToTheList(ewhEC3);
        ewhB107.addReadingsToTheList(ewhEC4);
        ewhB107.addReadingsToTheList(ewhEC5);
        ewhB107.addReadingsToTheList(ewhEC6);
        ewhB107.addReadingsToTheList(ewhEC7);
        ewhB107.addReadingsToTheList(ewhEC8);
        ewhB107.addReadingsToTheList(ewhEC9);
        ewhB107.addReadingsToTheList(ewhEC10);
        ewhB107.addReadingsToTheList(ewhEC11);
        ewhB107.addReadingsToTheList(ewhEC12);
        ewhB107.addReadingsToTheList(ewhEC13);
        ewhB107.addReadingsToTheList(ewhEC14);
        ewhB107.addReadingsToTheList(ewhEC15);
        ewhB107.addReadingsToTheList(ewhEC16);
        ewhB107.addReadingsToTheList(ewhEC17);
        ewhB107.addReadingsToTheList(ewhEC18);

        // Dishwasher B107
        Device dwB107 = houseEdificioB.createDevice(DISHWASHER, "Dishwasher B107", room1);
        dwB107.setAttributesDevType(CAPACITY, 50);
        dwB107.setAttributesDevType(NOMINAL_POWER, 1.5);


        dwB107.addReadingsToTheList(dwEC);
        dwB107.addReadingsToTheList(dwEC1);
        dwB107.addReadingsToTheList(dwEC2);
        dwB107.addReadingsToTheList(dwEC3);
        dwB107.addReadingsToTheList(dwEC4);
        dwB107.addReadingsToTheList(dwEC5);
        dwB107.addReadingsToTheList(dwEC6);
        dwB107.addReadingsToTheList(dwEC7);
        dwB107.addReadingsToTheList(dwEC8);
        dwB107.addReadingsToTheList(dwEC9);

        /*Programmable dwB107Programmable = dwB107.asProgrammable();
        TimeConstantProgramSpecs program = dwB107Programmable.newProgram(GLASSES, durationNotAsked, 0.9);
        TimeConstantProgramSpecs program1 = dwB107Programmable.newProgram("Eco", durationNotAsked, 1.3);
        TimeConstantProgramSpecs program2 = dwB107Programmable.newProgram("Eco turbo", durationNotAsked, 1.7);
        TimeConstantProgramSpecs program3 = dwB107Programmable.newProgram(DISHES, durationNotAsked, 2.1);
        dwB107Programmable.addProgram(program);
        dwB107Programmable.addProgram(program1);
        dwB107Programmable.addProgram(program2);
        dwB107Programmable.addProgram(program3);

        // Washing Machine B107
        Device wmB107 = houseEdificioB.createDevice("Washing Machine", "Washing Machine B107", room1);
        wmB107.setAttributesDevType(CAPACITY, 10);
        wmB107.setAttributesDevType(NOMINAL_POWER, 3.5);

        wmB107.addReadingsToTheList(wmEC);
        wmB107.addReadingsToTheList(wmEC1);
        wmB107.addReadingsToTheList(wmEC2);
        wmB107.addReadingsToTheList(wmEC3);

        Programmable wmB107Programmable = wmB107.asProgrammable();
        TimeConstantProgramSpecs program4 = wmB107Programmable.newProgram("Wool", durationNotAsked, 1.1);
        TimeConstantProgramSpecs program5 = wmB107Programmable.newProgram("Fast", durationNotAsked, 1.8);
        TimeConstantProgramSpecs program6 = wmB107Programmable.newProgram("Fast plus", durationNotAsked, 2.7);
        TimeConstantProgramSpecs program7 = wmB107Programmable.newProgram("Synthetic 30º", durationNotAsked, 2.8);
        wmB107Programmable.addProgram(program4);
        wmB107Programmable.addProgram(program5);
        wmB107Programmable.addProgram(program6);
        wmB107Programmable.addProgram(program7);

        // Electric Water Heater B109
        Device ewhB109 = houseEdificioB.createDevice(ELECTRIC_WATER_HEATER, "EHW B109", room2);
        ewhB109.setAttributesDevType(VOLUME_OF_WATER, 55);
        ewhB109.setAttributesDevType(HOT_WATER_TEMPERATURE, 100);
        ewhB109.setAttributesDevType(NOMINAL_POWER, 1.5);
        ewhB109.setAttributesDevType(PERFORMANCE_RATIO, 0.91);

        ewhB109.addReadingsToTheList(ewh1EC);
        ewhB109.addReadingsToTheList(ewh1EC1);
        ewhB109.addReadingsToTheList(ewh1EC2);
        ewhB109.addReadingsToTheList(ewh1EC3);
        ewhB109.addReadingsToTheList(ewh1EC4);
        ewhB109.addReadingsToTheList(ewh1EC5);
        ewhB109.addReadingsToTheList(ewh1EC6);
        ewhB109.addReadingsToTheList(ewh1EC7);
        ewhB109.addReadingsToTheList(ewh1EC8);
        ewhB109.addReadingsToTheList(ewh1EC9);
        ewhB109.addReadingsToTheList(ewh1EC10);
        ewhB109.addReadingsToTheList(ewh1EC11);
        ewhB109.addReadingsToTheList(ewh1EC12);
        ewhB109.addReadingsToTheList(ewh1EC13);
        ewhB109.addReadingsToTheList(ewh1EC14);
        ewhB109.addReadingsToTheList(ewh1EC15);
        ewhB109.addReadingsToTheList(ewh1EC16);
        ewhB109.addReadingsToTheList(ewh1EC17);
        ewhB109.addReadingsToTheList(ewh1EC18);

        // Dishwasher B019
        Device dwB109 = houseEdificioB.createDevice(DISHWASHER, "Dishwasher B109", room2);
        dwB109.setAttributesDevType(CAPACITY, 50);
        dwB109.setAttributesDevType(NOMINAL_POWER, 1.5);

        Programmable dwB109Programmable = dwB109.asProgrammable();
        TimeConstantProgramSpecs program16 = dwB109Programmable.newProgram(GLASSES, durationNotAsked, 0.9);
        TimeConstantProgramSpecs program17 = dwB109Programmable.newProgram("Eco", durationNotAsked, 1.3);
        TimeConstantProgramSpecs program18 = dwB109Programmable.newProgram("Eco turbo", durationNotAsked, 1.7);
        TimeConstantProgramSpecs program19 = dwB109Programmable.newProgram(DISHES, durationNotAsked, 2.1);
        dwB109Programmable.addProgram(program16);
        dwB109Programmable.addProgram(program17);
        dwB109Programmable.addProgram(program18);
        dwB109Programmable.addProgram(program19);


        // Washing Machine B109
        Device wmB109 = houseEdificioB.createDevice("Washing Machine", "Washing Machine B109", room2);
        wmB109.setAttributesDevType(CAPACITY, 10);
        wmB109.setAttributesDevType(NOMINAL_POWER, 2.5);

        wmB109.addReadingsToTheList(wm1EC);
        wmB109.addReadingsToTheList(wm1EC1);
        wmB109.addReadingsToTheList(wm1EC2);
        wmB109.addReadingsToTheList(wm1EC3);
        wmB109.addReadingsToTheList(wm1EC4);

        Programmable wmB109Programmable = wmB109.asProgrammable();
        TimeConstantProgramSpecs program8 = wmB109Programmable.newProgram("Wool", durationNotAsked, 0.9);
        TimeConstantProgramSpecs program9 = wmB109Programmable.newProgram("Fast", durationNotAsked, 1.3);
        TimeConstantProgramSpecs program10 = wmB109Programmable.newProgram("Fast plus", durationNotAsked, 1.7);
        TimeConstantProgramSpecs program11 = wmB109Programmable.newProgram("Synthetic 30º", durationNotAsked, 2.1);
        wmB109Programmable.addProgram(program8);
        wmB109Programmable.addProgram(program9);
        wmB109Programmable.addProgram(program10);
        wmB109Programmable.addProgram(program11);

        // Electric Water Heater 106
        Device ewhB106 = houseEdificioB.createDevice(ELECTRIC_WATER_HEATER, "EHW B106", room3);
        ewhB106.setAttributesDevType(VOLUME_OF_WATER, 55);
        ewhB106.setAttributesDevType(HOT_WATER_TEMPERATURE, 150);
        ewhB106.setAttributesDevType(NOMINAL_POWER, 2.2);
        ewhB106.setAttributesDevType(PERFORMANCE_RATIO, 0.92);


        // Dishwasher B106
        Device dwB106 = houseEdificioB.createDevice(DISHWASHER, "Dishwasher B106", room3);
        dwB106.setAttributesDevType(CAPACITY, 50);
        dwB106.setAttributesDevType(NOMINAL_POWER, 1.4);

        Programmable dwB106Programmable = dwB106.asProgrammable();
        TimeConstantProgramSpecs program12 = dwB106Programmable.newProgram(GLASSES, durationNotAsked, 0.8);
        TimeConstantProgramSpecs program13 = dwB106Programmable.newProgram("Light", durationNotAsked, 1.3);
        TimeConstantProgramSpecs program14 = dwB106Programmable.newProgram("Light turbo", durationNotAsked, 1.9);
        TimeConstantProgramSpecs program15 = dwB106Programmable.newProgram(DISHES, durationNotAsked, 2.3);
        dwB106Programmable.addProgram(program12);
        dwB106Programmable.addProgram(program13);
        dwB106Programmable.addProgram(program14);
        dwB106Programmable.addProgram(program15);*/

        // ROOM LIST
        houseEdificioB.addRoom(room1);
        houseEdificioB.addRoom(room2);
        houseEdificioB.addRoom(room3);


        // HOUSE GRID
        String houseGridName = "main grid";
        HouseGrid houseGrid = new HouseGrid(houseGridName);
        houseEdificioB.addGrid(houseGrid);
        houseGrid.addRoom(room1);
        houseGrid.addRoom(room2);
        houseGrid.addRoom(room3);

        // POWER SOURCES
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        PowerSourceType powerSourceType2 = new PowerSourceType("Public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType1);
        powerSourceTypeList.addPowerSourceType(powerSourceType2);

        //UI levels
        Admin admin = new Admin(geographicalAreaTypeList, geographicalAreaList, sensorTypeList, houseEdificioB, powerSourceTypeList, houseEdificioB.getRoomList());
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