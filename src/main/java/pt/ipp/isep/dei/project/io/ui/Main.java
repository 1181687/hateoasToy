package pt.ipp.isep.dei.project.io.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseService;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeList;
import pt.ipp.isep.dei.project.services.*;

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
    //private SensorTypeList sensorTypeList;
    @Autowired
    private GeoAreaTypeService geoAreaTypeService;

    //GeographicalArea Repository Injection
    @Autowired
    private GeoAreaService geoAreaService;

    //House Repository Injection
    @Autowired
    private HouseService houseService;

    //SensorType Repository Injection
    @Autowired
    private SensorTypeService sensorTypeService;

    // Room Repository Injection
    @Autowired
    private RoomService roomService;

    // HouseGrid Repository Injection
    @Autowired
    private HouseGridService houseGridService;


    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }

    @Bean
    public CommandLineRunner mainRun() {

        return (args) -> {
           /* GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("City");
            GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
            geoAreaTypeService.createGeoAreaType(geoAreaTypeId);
            GetListOfTypeOfGeoArea getListOfTypeOfGeoArea = new GetListOfTypeOfGeoArea(geoAreaService);
            getListOfTypeOfGeoArea.run(); */

            AddNewGeographicalAreaType addNewGeographicalAreaType = new AddNewGeographicalAreaType(geoAreaTypeService);
            addNewGeographicalAreaType.run();

        };
    }
/*
            data();

            //UI levels
            Admin admin = new Admin(geographicalAreaTypeList, geographicalAreaService, sensorTypeList, houseEdificioB, powerSourceTypeList, houseEdificioB.getRoomList(), houseService);
            RegularUser regularUser = new RegularUser(geographicalAreaTypeList, geographicalAreaService, sensorTypeList, houseEdificioB);
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

        geographicalAreaTypeList = new GeographicalAreaTypeList();
        //geographicalAreaList = new GeographicalAreaList();

        // GEOGRAPHICAL AREAS
        // Inserted Geo Area (Campus do ISEP)
        Location location2 = new Location(41.178553, -8.608035, 111);
        sensorTypeList = new SensorTypeList();
        AreaShape areaShape = new AreaShape(0.261, 0.249, location2);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType("Urban area");
        GeographicalArea insertedGeoArea = new GeographicalArea("DUMMY", "DUMMY", geographicalAreaType, location2, areaShape);

        // HOUSE
        Location houseLocation = new Location(41.177748, -8.607745, 112);
        Address address = new Address("4200-072", houseLocation, insertedGeoArea);
        houseEdificioB = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        //houseEdificioB.setAddress(address);
/*

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
        GeoAreaReading ewhEC = new GeoAreaReading(0.2, ewhDate);
        GeoAreaReading ewhEC1 = new GeoAreaReading(0.375, ewhDate1);
        GeoAreaReading ewhEC2 = new GeoAreaReading(0.375, ewhDate2);
        GeoAreaReading ewhEC3 = new GeoAreaReading(0.375, ewhDate3);
        GeoAreaReading ewhEC4 = new GeoAreaReading(0.375, ewhDate4);
        GeoAreaReading ewhEC5 = new GeoAreaReading(0.25, ewhDate5);
        GeoAreaReading ewhEC6 = new GeoAreaReading(0.2, ewhDate6);
        GeoAreaReading ewhEC7 = new GeoAreaReading(0.2, ewhDate7);
        GeoAreaReading ewhEC8 = new GeoAreaReading(0.2, ewhDate8);
        GeoAreaReading ewhEC9 = new GeoAreaReading(0.375, ewhDate9);
        GeoAreaReading ewhEC10 = new GeoAreaReading(0.375, ewhDate10);
        GeoAreaReading ewhEC11 = new GeoAreaReading(0.375, ewhDate11);
        GeoAreaReading ewhEC12 = new GeoAreaReading(0.375, ewhDate12);
        GeoAreaReading ewhEC13 = new GeoAreaReading(0.2, ewhDate13);
        GeoAreaReading ewhEC14 = new GeoAreaReading(0.1, ewhDate14);
        GeoAreaReading ewhEC15 = new GeoAreaReading(0.375, ewhDate15);
        GeoAreaReading ewhEC16 = new GeoAreaReading(0.375, ewhDate16);
        GeoAreaReading ewhEC17 = new GeoAreaReading(0.375, ewhDate17);
        GeoAreaReading ewhEC18 = new GeoAreaReading(0.15, ewhDate18);
        GeoAreaReading ewh1EC = new GeoAreaReading(0.2, ewhDate);
        GeoAreaReading ewh1EC1 = new GeoAreaReading(0.5, ewhDate1);
        GeoAreaReading ewh1EC2 = new GeoAreaReading(0.5, ewhDate2);
        GeoAreaReading ewh1EC3 = new GeoAreaReading(0.5, ewhDate3);
        GeoAreaReading ewh1EC4 = new GeoAreaReading(0.5, ewhDate4);
        GeoAreaReading ewh1EC5 = new GeoAreaReading(0.25, ewhDate5);
        GeoAreaReading ewh1EC6 = new GeoAreaReading(0.2, ewhDate6);
        GeoAreaReading ewh1EC7 = new GeoAreaReading(0.2, ewhDate7);
        GeoAreaReading ewh1EC8 = new GeoAreaReading(0.2, ewhDate8);
        GeoAreaReading ewh1EC9 = new GeoAreaReading(0.5, ewhDate9);
        GeoAreaReading ewh1EC10 = new GeoAreaReading(0.5, ewhDate10);
        GeoAreaReading ewh1EC11 = new GeoAreaReading(0.5, ewhDate11);
        GeoAreaReading ewh1EC12 = new GeoAreaReading(0.5, ewhDate12);
        GeoAreaReading ewh1EC13 = new GeoAreaReading(0.2, ewhDate13);
        GeoAreaReading ewh1EC14 = new GeoAreaReading(0.1, ewhDate14);
        GeoAreaReading ewh1EC15 = new GeoAreaReading(0.5, ewhDate15);
        GeoAreaReading ewh1EC16 = new GeoAreaReading(0.5, ewhDate16);
        GeoAreaReading ewh1EC17 = new GeoAreaReading(0.5, ewhDate17);
        GeoAreaReading ewh1EC18 = new GeoAreaReading(0.15, ewhDate18);

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
        GeoAreaReading dwEC = new GeoAreaReading(0.2, dwDate);
        GeoAreaReading dwEC1 = new GeoAreaReading(0.3, dwDate1);
        GeoAreaReading dwEC2 = new GeoAreaReading(0.2, dwDate2);
        GeoAreaReading dwEC3 = new GeoAreaReading(0.2, dwDate3);
        GeoAreaReading dwEC4 = new GeoAreaReading(0.2, dwDate4);
        GeoAreaReading dwEC5 = new GeoAreaReading(0.1, dwDate5);
        GeoAreaReading dwEC6 = new GeoAreaReading(0.1, dwDate6);
        GeoAreaReading dwEC7 = new GeoAreaReading(0.375, dwDate7);
        GeoAreaReading dwEC8 = new GeoAreaReading(0.375, dwDate8);
        GeoAreaReading dwEC9 = new GeoAreaReading(0.2, dwDate9);

        // Washing Machine B107
        LocalDateTime wmDate = LocalDateTime.of(2018, 12, 31, 10, 30, 00);
        LocalDateTime wdDate1 = LocalDateTime.of(2018, 12, 31, 10, 15, 00);
        LocalDateTime wdDate2 = LocalDateTime.of(2018, 12, 31, 13, 30, 00);
        LocalDateTime wdDate3 = LocalDateTime.of(2018, 12, 31, 13, 45, 00);
        GeoAreaReading wmEC = new GeoAreaReading(0.4, wmDate);
        GeoAreaReading wmEC1 = new GeoAreaReading(0.2, wdDate1);
        GeoAreaReading wmEC2 = new GeoAreaReading(0.25, wdDate2);
        GeoAreaReading wmEC3 = new GeoAreaReading(0.25, wdDate3);

        // Washing Machine B109
        LocalDateTime wm1Date = LocalDateTime.of(2018, 12, 31, 10, 15, 00);
        LocalDateTime wm1Date1 = LocalDateTime.of(2018, 12, 31, 10, 30, 00);
        LocalDateTime wm1Date2 = LocalDateTime.of(2018, 12, 31, 10, 45, 00);
        LocalDateTime wm1Date3 = LocalDateTime.of(2018, 12, 31, 11, 00, 00);
        GeoAreaReading wm1EC = new GeoAreaReading(0.4, wm1Date);
        GeoAreaReading wm1EC1 = new GeoAreaReading(0.2, wm1Date1);
        GeoAreaReading wm1EC2 = new GeoAreaReading(0.2, wm1Date2);
        GeoAreaReading wm1EC3 = new GeoAreaReading(0.25, wm1Date3);
        GeoAreaReading wm1EC4 = new GeoAreaReading(0.25, wm1Date3);


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

        // Room 2
        String id2 = "B109";
        Room room2 = new Room(id2, description, houseFloor, dimension);
        houseEdificioB.addRoom(room2);

        // Room 3
        String id3 = "B106";
        int houseFloor3 = 1;
        double height3 = 3.5;
        double length3 = 13;
        double width3 = 7;
        Dimension dimension3 = new Dimension(height3, length3, width3);
        Room room3 = new Room(id3, description, houseFloor3, dimension3);
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
        HouseGrid houseGrid = new HouseGrid(houseGridName);
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

    }

*/
}
