package pt.ipp.isep.dei.project.io.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pt.ipp.isep.dei.project.io.ui.logger.MyLogger;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeList;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeList;
import pt.ipp.isep.dei.project.services.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.IOException;
import java.util.List;

@EnableJpaRepositories(basePackages = "pt.ipp.isep.dei.project")
@EntityScan(basePackages = "pt.ipp.isep.dei.project")
@ComponentScan(basePackages = "pt.ipp.isep.dei.project")
@SpringBootApplication
public class Main {

    private static final String VOLUME_OF_WATER = "Volume Of Water To Heat";
    private static final String HOT_WATER_TEMPERATURE = "Hot-Water Temperature";
    private static final String NOMINAL_POWER = "Nominal Power";
    private static final String PERFORMANCE_RATIO = "Performance Ratio";
    private static final String CAPACITY = "Capacity";
    private static final String GLASSES = "Glasses";
    private static final String DISHES = "Dishes";
    private static final String WOOL = "Wool";
    private static final String ECO = "Eco";
    private static final String ECO_TURBO = "Eco Turbo";
    private static final String FAST = "Fast";
    private static final String FAST_PLUS = "Fast Plus";
    private static final String SYNTHETIC_30_DEGREES = "Synthetic 30º";
    private static final String ELECTRIC_WATER_HEATER = "ElectricWaterHeater";
    private static final String DISHWASHER = "DishWasher";
    private static final String WASHING_MACHINE = "WashingMachine";
    private static final String DURATION = "Duration";
    private static final String ENERGY_CONSUMPTION = "Energy Consumption";

    private House houseEdificioB;
    private PowerSourceTypeList powerSourceTypeList;
    private SensorTypeList sensorTypeList;

    //GeographicalAreaService Repository Injection
    @Autowired
    private GeographicalAreaService geographicalAreaService;

    //House Repository Injection
    @Autowired
    private HouseService houseService;

    //GeoAreaSensorService Injection
    @Autowired
    private GeoAreaSensorService geoAreaSensorService;

    //RoomSensorService Injection
    @Autowired
    private RoomSensorService roomSensorService;

    @Autowired
    private SensorTypeService sensorTypeService;

    @Autowired
    private GeoAreaTypeService geoAreaTypeService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private SensorsService sensorsService;

    //HouseGrid Repository Injection
    @Autowired
    private HouseGridService houseGridService;


    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }

    @Bean
    public CommandLineRunner mainRun() {

        return (args) -> {

            try {
                MyLogger.setup();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Problems with creating the log files");
            }

            data();

            //UI levels
            Admin admin = new Admin(geographicalAreaService, geoAreaTypeService, sensorTypeList, powerSourceTypeList,
                    houseEdificioB.getRoomList(), houseService, geoAreaSensorService, roomSensorService,
                    sensorTypeService, roomService, houseEdificioB, houseGridService);
            RegularUser regularUser = new RegularUser(geoAreaTypeService, geographicalAreaService, sensorTypeList, houseService, roomService, roomSensorService);
            PowerUser powerUser = new PowerUser(houseService, roomService, sensorsService);
            RoomOwner roomOwner = new RoomOwner(houseService, roomService, sensorsService);

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
        };
    }

    public void data() {

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

        //geographicalAreaTypeList = new GeographicalAreaTypeList();
        //geographicalAreaList = new GeographicalAreaList();

        // GEOGRAPHICAL AREAS
        // Inserted Geo Area (Campus do ISEP)
        Location location2 = new Location(41.178553, -8.608035, 111);
        sensorTypeList = new SensorTypeList();
        AreaShape areaShape = new AreaShape(0.261, 0.249);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Urban area");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeographicalArea insertedGeoArea = new GeographicalArea("DUMMY", "DUMMY", geographicalAreaType, location2, areaShape);
        //List<GeographicalAreaDTO> geographicalAreas = new ArrayList<>();
        //GeographicalAreaDTO geographicalAreaDTO = (GeographicalAreaMapper.mapToDTOwithSensors(insertedGeoArea));
        //geographicalAreas.add(geographicalAreaDTO);
        //geographicalAreaService.saveGeoAreas(geographicalAreas);
        //geoAreaTypeService.save(geographicalAreaType);



        // HOUSE
        Address address = new Address("", null, null);
        houseEdificioB = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice, address);
/*
////////////(des)comentar///////////////
        // houseService.saveHouse(houseEdificioB);


        // READINGS

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


        // ROOMS
        // Room 1
        String id = "B107";
        String description = "Classroom";
        int houseFloor = 1;
        double height = 3.5;
        double length = 11;
        double width = 7;
        Dimension dimension = new Dimension(height, length, width);
        Room room1 = new Room(id, description, houseFloor, dimension);
        houseEdificioB.addRoom(room1);

        ////////////(des)comentar///////////////
        //      roomService.saveRoom(room1);

        // Room 2
        String id2 = "B109";
        Room room2 = new Room(id2, description, houseFloor, dimension);
        houseEdificioB.addRoom(room2);

        ////////////(des)comentar///////////////
        //     roomService.saveRoom(room2);

        // Room 3
        String id3 = "B106";
        int houseFloor3 = 1;
        double height3 = 3.5;
        double length3 = 13;
        double width3 = 7;
        Dimension dimension3 = new Dimension(height3, length3, width3);
        Room room3 = new Room(id3, description, houseFloor3, dimension3);
        houseEdificioB.addRoom(room3);
        roomService.saveRoom(room3);

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

        Programmable dwB107Programmable = dwB107.getSpecs().asProgrammable();
        Program program = dwB107Programmable.createNewProgram(GLASSES);
        program.setProgramAttributes(DURATION, durationNotAsked);
        program.setProgramAttributes(ENERGY_CONSUMPTION, 0.9);

        Program program1 = dwB107Programmable.createNewProgram(ECO);
        program1.setProgramAttributes(DURATION, durationNotAsked);
        program1.setProgramAttributes(ENERGY_CONSUMPTION, 1.3);

        Program program2 = dwB107Programmable.createNewProgram(ECO_TURBO);
        program2.setProgramAttributes(DURATION, durationNotAsked);
        program2.setProgramAttributes(ENERGY_CONSUMPTION, 1.7);

        Program program3 = dwB107Programmable.createNewProgram(DISHES);
        program3.setProgramAttributes(DURATION, durationNotAsked);
        program3.setProgramAttributes(ENERGY_CONSUMPTION, 2.1);

        dwB107Programmable.addProgram(program);
        dwB107Programmable.addProgram(program1);
        dwB107Programmable.addProgram(program2);
        dwB107Programmable.addProgram(program3);

        // Washing Machine B107
        Device wmB107 = houseEdificioB.createDevice(WASHING_MACHINE, "Washing Machine B107", room1);
        wmB107.setAttributesDevType(CAPACITY, 10);
        wmB107.setAttributesDevType(NOMINAL_POWER, 3.5);

        wmB107.addReadingsToTheList(wmEC);
        wmB107.addReadingsToTheList(wmEC1);
        wmB107.addReadingsToTheList(wmEC2);
        wmB107.addReadingsToTheList(wmEC3);

        Programmable wmB107Programmable = wmB107.getSpecs().asProgrammable();
        Program program4 = wmB107Programmable.createNewProgram(WOOL);
        program4.setProgramAttributes(DURATION, durationNotAsked);
        program4.setProgramAttributes(ENERGY_CONSUMPTION, 1.1);

        Program program5 = wmB107Programmable.createNewProgram(FAST);
        program5.setProgramAttributes(DURATION, durationNotAsked);
        program5.setProgramAttributes(ENERGY_CONSUMPTION, 1.8);

        Program program6 = wmB107Programmable.createNewProgram(FAST_PLUS);
        program6.setProgramAttributes(DURATION, durationNotAsked);
        program6.setProgramAttributes(ENERGY_CONSUMPTION, 2.7);

        Program program7 = wmB107Programmable.createNewProgram(SYNTHETIC_30_DEGREES);
        program7.setProgramAttributes(DURATION, durationNotAsked);
        program7.setProgramAttributes(ENERGY_CONSUMPTION, 2.8);

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

        Programmable dwB109Programmable = dwB109.getSpecs().asProgrammable();
        Program program16 = dwB109Programmable.createNewProgram(GLASSES);
        program16.setProgramAttributes(DURATION, durationNotAsked);
        program16.setProgramAttributes(ENERGY_CONSUMPTION, 0.9);

        Program program17 = dwB109Programmable.createNewProgram(ECO);
        program17.setProgramAttributes(DURATION, durationNotAsked);
        program17.setProgramAttributes(ENERGY_CONSUMPTION, 1.3);

        Program program18 = dwB109Programmable.createNewProgram(ECO_TURBO);
        program18.setProgramAttributes(DURATION, durationNotAsked);
        program18.setProgramAttributes(ENERGY_CONSUMPTION, 1.7);

        Program program19 = dwB109Programmable.createNewProgram(DISHES);
        program19.setProgramAttributes(DURATION, durationNotAsked);
        program19.setProgramAttributes(ENERGY_CONSUMPTION, 2.1);

        dwB109Programmable.addProgram(program16);
        dwB109Programmable.addProgram(program17);
        dwB109Programmable.addProgram(program18);
        dwB109Programmable.addProgram(program19);


        // Washing Machine B109
        Device wmB109 = houseEdificioB.createDevice(WASHING_MACHINE, "Washing Machine B109", room2);
        wmB109.setAttributesDevType(CAPACITY, 10);
        wmB109.setAttributesDevType(NOMINAL_POWER, 2.5);

        wmB109.addReadingsToTheList(wm1EC);
        wmB109.addReadingsToTheList(wm1EC1);
        wmB109.addReadingsToTheList(wm1EC2);
        wmB109.addReadingsToTheList(wm1EC3);
        wmB109.addReadingsToTheList(wm1EC4);

        Programmable wmB109Programmable = wmB109.getSpecs().asProgrammable();
        Program program8 = wmB109Programmable.createNewProgram(WOOL);
        program8.setProgramAttributes(DURATION, durationNotAsked);
        program8.setProgramAttributes(ENERGY_CONSUMPTION, 0.9);

        Program program9 = wmB109Programmable.createNewProgram(FAST);
        program9.setProgramAttributes(DURATION, durationNotAsked);
        program9.setProgramAttributes(ENERGY_CONSUMPTION, 1.3);

        Program program10 = wmB109Programmable.createNewProgram(FAST_PLUS);
        program10.setProgramAttributes(DURATION, durationNotAsked);
        program10.setProgramAttributes(ENERGY_CONSUMPTION, 1.7);

        Program program11 = wmB109Programmable.createNewProgram(SYNTHETIC_30_DEGREES);
        program11.setProgramAttributes(DURATION, durationNotAsked);
        program11.setProgramAttributes(ENERGY_CONSUMPTION, 2.1);

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

        Programmable dwB106Programmable = dwB106.getSpecs().asProgrammable();
        Program program12 = dwB106Programmable.createNewProgram(GLASSES);
        program12.setProgramAttributes(DURATION, durationNotAsked);
        program12.setProgramAttributes(ENERGY_CONSUMPTION, 0.8);

        Program program13 = dwB106Programmable.createNewProgram("Light");
        program13.setProgramAttributes(DURATION, durationNotAsked);
        program13.setProgramAttributes(ENERGY_CONSUMPTION, 1.3);

        Program program14 = dwB106Programmable.createNewProgram("Light turbo");
        program14.setProgramAttributes(DURATION, durationNotAsked);
        program14.setProgramAttributes(ENERGY_CONSUMPTION, 1.9);

        Program program15 = dwB106Programmable.createNewProgram(DISHES);
        program15.setProgramAttributes(DURATION, durationNotAsked);
        program15.setProgramAttributes(ENERGY_CONSUMPTION, 2.3);

        dwB106Programmable.addProgram(program12);
        dwB106Programmable.addProgram(program13);
        dwB106Programmable.addProgram(program14);
        dwB106Programmable.addProgram(program15);

        // ROOM LIST
        houseEdificioB.addRoom(room1);
        houseEdificioB.addRoom(room2);
        houseEdificioB.addRoom(room3);

        // HOUSE GRID
        String houseGridName = "main grid";
        HouseGrid houseGrid = new HouseGrid(new HouseGridId(houseGridName));
        houseEdificioB.addGrid(houseGrid);
        houseGrid.addRoom(room1);
        houseGrid.addRoom(room2);
        houseGrid.addRoom(room3);

        // POWER SOURCES
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        PowerSourceType powerSourceType2 = new PowerSourceType("Public electric grid");
        powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceType(powerSourceType1);
        powerSourceTypeList.addPowerSourceType(powerSourceType2);

        // GEO AREA Sensors
        SensorTypeId temperature = new SensorTypeId("temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 1, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        //GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("S01"), "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("S02"), "B123", startDate1, temperature, location2, "ºC", insertedGeoArea.getId());
        SensorType sensorType = new SensorType(temperature);

////////////(des)comentar///////////////

/*
        // Readings - GEO AREA Sensors
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 2, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        //temperatureSensor.addReadingsToList(reading);
        //temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 3, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        LocalDateTime readingDate4 = LocalDateTime.of(2018, 12, 4, 05, 24, 00);
        LocalDateTime readingDate6 = LocalDateTime.of(2018, 12, 5, 05, 24, 00);
        LocalDateTime readingDate5 = LocalDateTime.of(2018, 12, 4, 04, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        Reading reading4 = new Reading(20, readingDate4);
        Reading reading5 = new Reading(23, readingDate5);
        Reading reading6 = new Reading(19, readingDate6);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        temperatureSensor1.addReadingsToList(reading6);

        sensorTypeService.save(sensorType);
        geoAreaSensorService.saveGeoAreaSensor(temperatureSensor1);


        //ROOM SENSORS
        //Room1 - Sensors
        SensorId sensorId = new SensorId("S01");
        String sensorName = "A123";
        RoomId roomId1 = room1.getId();
        RoomSensor roomSensor1 = new RoomSensor(sensorId, sensorName, startDate, temperature, "ºC", roomId1);

        //Room3 - Sensor
        SensorId sensorId3 = new SensorId("S03");
        String sensorName3 = "A124";
        RoomId roomId3 = room3.getId();
        RoomSensor roomSensor3 = new RoomSensor(sensorId3, sensorName3, startDate, temperature, "ºC", roomId3);


        //Readings - RoomSensor1
        Reading roomReading1 = new Reading(11, readingDate1);
        Reading roomReading2 = new Reading(32, readingDate2);
        Reading roomReading3 = new Reading(9, readingDate3);
        Reading roomReading4 = new Reading(33, readingDate4);
        Reading roomReading5 = new Reading(7, readingDate);
        Reading roomReading6 = new Reading(35, readingDate5);
        roomSensor1.addReading(roomReading1);
        roomSensor1.addReading(roomReading2);
        roomSensor1.addReading(roomReading3);
        roomSensor1.addReading(roomReading4);
        roomSensor1.addReading(roomReading5);
        roomSensor1.addReading(roomReading6);

        List<RoomSensorDTO> roomSensorsDTO = new ArrayList<>();
        roomSensorsDTO.add(RoomSensorMapper.mapToDTO(roomSensor1));
        roomSensorsDTO.add(RoomSensorMapper.mapToDTO(roomSensor3));
        roomSensorService.saveSensors(roomSensorsDTO);
*/

    }


}