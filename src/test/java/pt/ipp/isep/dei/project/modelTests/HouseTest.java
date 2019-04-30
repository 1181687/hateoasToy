package pt.ipp.isep.dei.project.modelTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Dimension;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.repositories.GeoAreaRepository;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HouseTest {
    private static final String FRIDGE_TYPE = "Fridge";
    private static final String ELECTRIC_W_H_TYPE = "ElectricWaterHeater";
    private static final String DISHWASHER_TYPE = "DishWasher";
    private static final String LAMP_TYPE = "Lamp";
    private static final String WASHING_MACHINE_TYPE = "WashingMachine";
    private static final String CONFIG_PROPERTIES = "Configuration.properties";
    private House house;
    private GeographicalArea ag;
    private Room laundry;
    private Room kitchen;
    private Device electricWaterHeater;
    @Mock
    private GeoAreaRepository geoAreaRepository;

    @BeforeEach
    public void StartUp() {
        // Geographical Area
        Location location = new Location(41.178553, -8.608035, 111);
        AreaShape areaShape = new AreaShape(1.261, 1.249);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Urban area");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        ag = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, ag);
        this.house.setAddress(address);

        // Room Instantiation
        Dimension dim = new Dimension(3, 3.5, 3.5);
        laundry = new Room("Laundry", "room", 2, dim);
        house.addRoom(laundry);
        kitchen = new Room("Kitchen", "room", 1, dim);

        // ElectricWaterHeaters Instantiation
        electricWaterHeater = house.createDevice(ELECTRIC_W_H_TYPE, "Bosch Tronic 3000", laundry);
        house.setDeviceAttribute("Bosch Tronic 3000", "Nominal Power", 0.5);
        house.setDeviceAttribute("Bosch Tronic 3000", "Performance Ratio", 0.8);
        house.setDeviceAttribute("Bosch Tronic 3000", "Hot-Water Temperature", 70);
    }

    @Test
    public void getDisplayRoomListTest() {
        // Arrange
        String expectResult = "1- Name: Laundry, House Floor: 2, Dimension - Height: 3.0, Length: 3.5, Width: 3.5\n";
        // Act
        String result = house.getRoomListContent();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void createDevice() {
        // Act
        Device result = house.createDevice(FRIDGE_TYPE, "Bosch 2000", laundry);
        Device expectedResult = house.getDeviceByName("Bosch 2000");


        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void createDeviceDeviceTypeNotExists_False() {
        // Act
        Device result = house.createDevice("Hoven2", "Bosch 1000", laundry);

        Device expectedResult = null;

        // Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void createDeviceNameAlreadyExists() {

        house.createDevice("Fridge", "Bosch 2000", laundry);
        Device result = house.createDevice("Fridge", "Bosch 2000", laundry);

        Device expectedResult = null;

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListSizeEmptyList() {
        // Arrange
        house.getRoomList().getListOfRooms().remove(laundry);
        int expectResult = 0;

        // Act
        int result = house.getRoomListSize();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getDisplayRoomListEmptyTest() {
        // Arrange
        house.getRoomList().getListOfRooms().remove(laundry);
        String expectResult = "";

        // Act
        String result = house.getRoomListContent();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetNameOfRoomInListOfRooms() {
        //Arrange
        String expectedResult = "Laundry";

        // Act
        String result = house.getRoomNameByPosition(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNameOfRoomInEmptyListOfRooms() {
        // Arrange
        house.getRoomList().getListOfRooms().remove(laundry);
        String expectedResult = null;

        // Act
        String result = house.getRoomNameByPosition(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getListSize() {
        // Arrange
        int expectResult = 1;

        // Act
        int result = house.getRoomListSize();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testeAverageRainfallOfHouseArea() {
        //Arrange
        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Rainfall");
        Location locS0 = new Location(41.178553, -8.608035, 111);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("S01"), "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Rainfall");
        Location locS1 = new Location(41.178553, -8.608035, 111);
        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("S01"), "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(30, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 4, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 5, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);
        Reading reading13 = new Reading(20, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);
        s1.addReadingsToList(reading13);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        double expectedResult = 26.5;

        SensorTypeId searchType = new SensorTypeId("Rainfall");
        //Act
        double result = house.getAverageDailyMeasurementInHouseArea(searchType, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testAddRoomToHouse() {
        // Act
        boolean result = house.addRoom(kitchen);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testAddRoomToHouseFalse() {
        // Act
        boolean result = house.addRoom(null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetLocationOfTheHouse() {
        // Arrange
        Location location = new Location(41.178553, -8.608035, 111);
        Location expectedResult = location;

        // Act
        Location result = house.getLocation();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseArea() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Temperature");
        Location locS0 = new Location(41.178553, -8.608035, 111);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("23"), "A122", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Temperature");
        Location locS1 = new Location(41.178553, -8.608035, 111);
        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("24"), "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s1);


        //Instantiate Reading
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 4, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(30, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        double expectedResult = 30;

        SensorTypeId type = new SensorTypeId("Temperature");

        //Act
        double result = house.getLastMeasurementByTypeInHouseArea(type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testeAverageRainfallOfHouseAreaNoMeasurements() {
        //Arrange
        //Instanciar sensor
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Rainfall");
        Location locS0 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("S02"), "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Rainfall");
        Location locS1 = new Location(42.149, -8.610, 97);
        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("32"), "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(2018, 12, 6);

        double expectedResult = 0;

        SensorTypeId searchType = new SensorTypeId("Rainfall");
        //Act
        double result = house.getAverageDailyMeasurementInHouseArea(searchType, startDate, endDate);

        //Assert
        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testGetLastTemperatureOfTheHouseAreaWithoutMeasurements() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("24"), "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("45"), "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        double expectedResult = Double.NaN;

        SensorTypeId type = new SensorTypeId("Temperature");

        //Act
        double result = house.getLastMeasurementByTypeInHouseArea(type);

        //Assert
        assertEquals(expectedResult, result, 0.0001);
    }

    /*    @Test
        public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresIguais() {
            //arrange
            //sensor
            LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            SensorTypeId sensorType0 = new SensorTypeId("Temperatura");
            Location locS0 = new Location(123, 345, 50);
            GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("87654", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");

            LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 5, 15, 20, 00);
            SensorTypeId sensorType1 = new SensorTypeId("Temperatura");
            Location locS1 = new Location(123, 355, 50);
            GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("87654", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");

            LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 11, 15, 20, 00);
            SensorTypeId sensorType2 = new SensorTypeId("Temperatura");
            Location locS2 = new Location(123, 345, 55);
            GeoAreaSensor s2 = new GeoAreaSensor(new SensorId("87654", "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");

            //Reading
            // Sensor0
            LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

            Reading reading01 = new Reading(23, dataHoraDaMedicao01);
            Reading reading02 = new Reading(24, dataHoraDaMedicao02);

            s0.addReadingsToList(reading01);
            s0.addReadingsToList(reading02);

            //Sensor1
            LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 12, 24, 17, 24, 00);

            Reading reading11 = new Reading(22, dataHoraDaMedicao11);
            Reading reading12 = new Reading(30, dataHoraDaMedicao12);

            s1.addReadingsToList(reading11);
            s1.addReadingsToList(reading12);

            //Sensor2
            LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 12, 4, 17, 24, 00);

            Reading reading21 = new Reading(20, dataHoraDaMedicao21);
            Reading reading22 = new Reading(27, dataHoraDaMedicao22);

            s2.addReadingsToList(reading21);
            s2.addReadingsToList(reading22);

        SensorTypeId sensorType = new SensorTypeId("Temperatura");
        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", "room", 1, dim);
        room.addSensorToListOfSensorsInRoom(s0);
        room.addSensorToListOfSensorsInRoom(s1);
        room.addSensorToListOfSensorsInRoom(s2);

            house.addRoom(room);

            Reading expectedResult = reading22;

            //Act
            Reading result = house.getLatestMeasurementBySensorTypeId("F5", sensorType);

            //Assert
            assertEquals(expectedResult, result);
        }

    @Test
    public void testGetLastTemperatureOfTheHouseAreaWithoutSensors() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

            house.addRoom(room1);
            house.addRoom(room2);

            double expectedResult = Double.NaN;

            SensorTypeId type = new SensorTypeId("Temperature");

            //Act
            double result = house.getLastMeasurementByTypeInHouseArea(type);

            //Assert
            assertEquals(expectedResult, result, 0.0001);
        }

        @Test
        public void testTotalDailyMeasurementInAHouseArea() {
            //Arrange
            //Instantiate sensor
            LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
            SensorTypeId sensorType0 = new SensorTypeId("Rainfall");
            Location locS0 = new Location(41.1, -8.6, 111);
            GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("6546", "A122", dataFuncionamento0, sensorType0, locS0, "l/m2");
            house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s0);

            LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
            SensorTypeId sensorType1 = new SensorTypeId("Rainfall");
            Location locS1 = new Location(41.1, -8.6, 111);
            GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("653", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
            house.getInsertedGeoArea().getSensorListInTheGeographicArea().addSensor(s1);

            // Sensor0 - Register 1
            LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 1, 15, 20, 00);
            Reading reading01 = new Reading(10, dataHoraDaMedicao01);
            s0.addReadingsToList(reading01);

            // Sensor0 - Register 2
            LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 1, 17, 24, 00);
            Reading reading02 = new Reading(11, dataHoraDaMedicao02);
            s0.addReadingsToList(reading02);

            //Sensor1 - Register 1
            LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 1, 17, 20, 00);
            Reading reading11 = new Reading(15, dataHoraDaMedicao11);
            s1.addReadingsToList(reading11);

            //Sensor1 - Register 2
            LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 1, 23, 24, 00);
            Reading reading12 = new Reading(20, dataHoraDaMedicao12);
            s1.addReadingsToList(reading12);

            LocalDate day = LocalDate.of(2018, 12, 1);

            double expectedResult = 20;

            SensorTypeId searchType = new SensorTypeId("Rainfall");
            //Act
            double result = house.getTotalDailyMeasurementInHouseArea(searchType, day);

            //Assert
            assertEquals(expectedResult, result, 0.001);
        }

    @Test
    public void getMaximumTemperatureOfARoomInASpecificDay() {
        //Arrange
        String name = "Master Bedroom";
        String description = "room";
        int houseFloor = 2;
        double height = 10.0;
        double length = 5.0;
        double width = 5.0;
        Dimension dimension = new Dimension(height, length, width);
        Room room1 = new Room(name, description, houseFloor, dimension);

            house.addRoom(room1);

            LocalDateTime date0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            SensorTypeId sensorType0 = new SensorTypeId("Temperature");
            Location locS0 = new Location(123, 345, 50);
            GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("3213", "A123", date0, sensorType0, locS0, "l/m2");

            LocalDateTime date1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            SensorTypeId sensorType1 = new SensorTypeId("Temperature");
            Location locS1 = new Location(123, 345, 50);
            GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("1414", "B123", date1, sensorType1, locS1, "l/m2");

            LocalDateTime dateTimeDayMeasure1 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            LocalDateTime dateTimeDayMeasure2 = LocalDateTime.of(1991, 12, 2, 20, 24, 00);

            Reading reading1 = new Reading(-20.0, dateTimeDayMeasure1);
            Reading reading2 = new Reading(-25.0, dateTimeDayMeasure2);

            s0.addReadingsToList(reading1);
            s0.addReadingsToList(reading2);

            LocalDateTime dateTimeDayMeasure3 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            LocalDateTime dateTimeDayMeasure4 = LocalDateTime.of(1991, 12, 2, 17, 24, 00);

            Reading reading3 = new Reading(-10.0, dateTimeDayMeasure3);
            Reading reading4 = new Reading(-15.0, dateTimeDayMeasure4);

            s1.addReadingsToList(reading3);
            s1.addReadingsToList(reading4);

            room1.getSensorList().addSensor(s0);
            room1.getSensorList().addSensor(s1);

            house.addRoom(room1);

            LocalDate dayNeeded = LocalDate.of(1991, 12, 2);

            double expectedResult = -10.0;

            //Act
            double result = house.getMaximumTemperatureOfRoomInSpecificDay(name, sensorType0, dayNeeded);

            //Assert
            assertEquals(expectedResult, result, 0.001);

        }

        @Test
        public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresQueNaoTem() {
            //arrange
            //Instanciar sensor
            LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            SensorTypeId sensorType0 = new SensorTypeId("Temperatura");
            Location locS0 = new Location(123, 345, 50);
            GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("g34", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");

            LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 5, 15, 20, 00);
            SensorTypeId sensorType1 = new SensorTypeId("Temperatura");
            Location locS1 = new Location(123, 355, 50);
            GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("2423r", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");

            LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 11, 15, 20, 00);
            SensorTypeId sensorType2 = new SensorTypeId("Humidade");
            Location locS2 = new Location(123, 345, 55);
            GeoAreaSensor s2 = new GeoAreaSensor(new SensorId("6325", "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");

            //Instanciar Reading
            // Sensor0
            LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

            Reading reading01 = new Reading(23, dataHoraDaMedicao01);
            Reading reading02 = new Reading(25, dataHoraDaMedicao02);

            s0.addReadingsToList(reading01);
            s0.addReadingsToList(reading02);

            //Sensor1
            LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 12, 4, 17, 24, 00);

            Reading reading11 = new Reading(22, dataHoraDaMedicao11);
            Reading reading12 = new Reading(25, dataHoraDaMedicao12);

            s1.addReadingsToList(reading11);
            s1.addReadingsToList(reading12);

            //Sensor2
            LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

            Reading reading21 = new Reading(20, dataHoraDaMedicao21);
            Reading reading22 = new Reading(25, dataHoraDaMedicao22);

            s2.addReadingsToList(reading21);
            s2.addReadingsToList(reading22);

            SensorTypeId tipoResultado = new SensorTypeId("Pluviosidade");

        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", "room", 1, dim);
        room.addSensorToListOfSensorsInRoom(s0);
        room.addSensorToListOfSensorsInRoom(s1);
        room.addSensorToListOfSensorsInRoom(s2);

            house.addRoom(room);

            //Act
            Reading result = house.getLatestMeasurementBySensorTypeId("F5", tipoResultado);

            //Assert
            assertNull(result);
        }

        @Test
        public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresQuartoNulo() {
            //arrange
            //Instanciar sensor
            LocalDateTime dataFuncionamento0 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            SensorTypeId sensorType0 = new SensorTypeId("Temperatura");
            Location locS0 = new Location(123, 345, 50);
            GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("4214", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");

            LocalDateTime dataFuncionamento1 = LocalDateTime.of(1991, 12, 5, 15, 20, 00);
            SensorTypeId sensorType1 = new SensorTypeId("Temperatura");
            Location locS1 = new Location(123, 355, 50);
            GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("4124", "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");

            LocalDateTime dataFuncionamento2 = LocalDateTime.of(1991, 12, 11, 15, 20, 00);
            SensorTypeId sensorType2 = new SensorTypeId("Humidade");
            Location locS2 = new Location(123, 345, 55);
            GeoAreaSensor s2 = new GeoAreaSensor(new SensorId("7657", "A123", dataFuncionamento2, sensorType2, locS2, "l/m2");

            //Instanciar Reading
            // Sensor0
            LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

            Reading reading01 = new Reading(23, dataHoraDaMedicao01);
            Reading reading02 = new Reading(25, dataHoraDaMedicao02);

            s0.addReadingsToList(reading01);
            s0.addReadingsToList(reading02);

            //Sensor1
            LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(1991, 12, 4, 17, 24, 00);

            Reading reading11 = new Reading(22, dataHoraDaMedicao11);
            Reading reading12 = new Reading(25, dataHoraDaMedicao12);

            s1.addReadingsToList(reading11);
            s1.addReadingsToList(reading12);

            //Sensor2
            LocalDateTime dataHoraDaMedicao21 = LocalDateTime.of(1991, 12, 2, 15, 20, 00);
            LocalDateTime dataHoraDaMedicao22 = LocalDateTime.of(1991, 12, 3, 17, 24, 00);

            Reading reading21 = new Reading(20, dataHoraDaMedicao21);
            Reading reading22 = new Reading(25, dataHoraDaMedicao22);

            s2.addReadingsToList(reading21);
            s2.addReadingsToList(reading22);

            SensorTypeId tipoResultado = new SensorTypeId("Pluviosidade");

        Dimension dim = new Dimension(4, 4, 4);
        Room room = new Room("F5", "room", 1, dim);
        room.addSensorToListOfSensorsInRoom(s0);
        room.addSensorToListOfSensorsInRoom(s1);
        room.addSensorToListOfSensorsInRoom(s2);

            house.addRoom(room);

            //Act
            Reading result = house.getLatestMeasurementBySensorTypeId("F6", tipoResultado);

            //Assert
            assertNull(result);
        }

        @Test
        public void testarUltimoRegistoDeUmaListaDeTiposDeSensoresListaVazia() {
            //arrange
            SensorTypeId tipoResultado = new SensorTypeId("Temperatura");

            //Act
            Reading result = house.getLatestMeasurementBySensorTypeId("F5", tipoResultado);

            //Assert
            assertNull(result);
        }

        @Test
        public void getDeviceListContentOfARoomTest() {
            // Arrange
            String expectedResult = "1 - Name of the device: Bosch Tronic 3000\n";

            // Act
            String result = house.getDeviceListContentRoom(0);

            // Assert
            assertEquals(expectedResult, result);
        }

        @Test
        public void testCheckIfNameAlreadyExists() {
            //Arrange
            String nameToCheck = "Room one";
            String name = "ROOM ONE";

        Dimension dim = new Dimension(5, 6, 7);
        Room room1 = new Room(name, "room", 1, dim);
        house.addRoom(room1);

            //Act
            boolean expectedResult = true;

            boolean result = house.isNameExistant(nameToCheck);
            //Assert
            assertEquals(expectedResult, result);

        }

        @Test
        public void testCheckIfNameAlreadyExistsFalse() {
            //Arrange
            String nameToCheck = "Room one";
            String name = "ROOM Two";

        Dimension dim = new Dimension(5, 6, 7);
        Room room1 = new Room(name, "room", 1, dim);
        house.addRoom(room1);
        //Act
        boolean expectedResult = false;

            boolean result = house.isNameExistant(nameToCheck);
            //Assert
            assertEquals(expectedResult, result);
        }

        @Test
        public void getSensorsListContentOfARoomTest() {
            // Arrange
            LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
            SensorTypeId sensorType0 = new SensorTypeId("Temperatura");
            Location locS0 = new Location(123, 345, 50);
            GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("242", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
            LocalDateTime dataFuncionamento1 = LocalDateTime.of(2010, 11, 2, 15, 20, 00);
            SensorTypeId sensorType1 = new SensorTypeId("Temperatura");
            Location locS1 = new Location(123, 300, 50);
            GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("767", "A456", dataFuncionamento1, sensorType1, locS1, "l/m2");
            laundry.addSensorToListOfSensorsInRoom(s0);
            laundry.addSensorToListOfSensorsInRoom(s1);
            String expectedResult =
                    "1 - Name of the sensor: A123\n" +
                            "2 - Name of the sensor: A456\n";
            // Act
            String result = house.getSensorListContentOfARoom(0);

            // Assert
            assertEquals(expectedResult, result);
        }

        @Test
        public void checkIfSensorListIsEmptyTestTrue() {
            // Arrange
            Dimension dim = new Dimension(3, 3.5, 3.5);
            Room room = new Room("Room", 2, dim);

            house.addRoom(room);

            int position = 0;
            // Act
            boolean result = house.isSensorListEmpty(position);

            // Assert
            assertTrue(result);
        }

        @Test
        public void checkIfSensorListIsEmptyTestFalse() {
            // Arrange
            LocalDateTime dataFuncionamento0 = LocalDateTime.of(2015, 11, 2, 15, 20, 00);
            SensorTypeId sensorType0 = new SensorTypeId("Temperatura");
            Location locS0 = new Location(123, 345, 50);
            GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("123", "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
            laundry.addSensorToListOfSensorsInRoom(s0);

            // Act
            boolean result = house.isSensorListEmpty(0);

            // Assert
            assertFalse(result);
        }

    @Test
    public void TestGetAllDevicesListByGridPosition() {
        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, "room", 2, dim);

            Device dev1 = house.createDevice(FRIDGE_TYPE, "FridgeAriston", room1);

            Device dev2 = house.createDevice(WASHING_MACHINE_TYPE, "WashingMachineBosh", room1);

            Device dev3 = house.createDevice(DISHWASHER_TYPE, "DishWasherSpecs", room1);


        //Room TWO
        String name2 = "KitchenBasement";
        Room room2 = new Room(name2, "room", -1, dim);

            Device dev4 = house.createDevice(FRIDGE_TYPE, "FridgeSiemens", room2);
            Device dev5 = house.createDevice(DISHWASHER_TYPE, "DishWasherTeka", room2);

            Device dev6 = house.createDevice(ELECTRIC_W_H_TYPE, "ElectricWaterHeaterSpecs", room2);

            //add to Lists
            house.addRoom(room1);
            house.addRoom(room2);

            HouseGrid grid = new HouseGrid("g1");
            grid.addRoom(room1);
            grid.addRoom(room2);
            house.addGrid(grid);

            List<Device> expectedResult = new ArrayList<>();
            expectedResult.add(dev1);
            expectedResult.add(dev2);
            expectedResult.add(dev3);
            expectedResult.add(dev4);
            expectedResult.add(dev5);
            expectedResult.add(dev6);

            List<Device> result = house.getAllDevicesListByGridPosition(0);

            assertEquals(expectedResult, result);
        }

        @Test
        public void checkIfDeviceListIsEmptyTestTrue() {
            // Arrange
            laundry.removeDevice(electricWaterHeater);

            // Act
            boolean result = house.isDeviceListEmpty(0);

            // Assert
            assertTrue(result);
        }


    @Test
    public void checkIfDeviceListIsEmptyTestFalse() {
        // Arrange
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", "room", 2, dim);

            Device dev1 = house.createDevice("Lamp", "Lamp1", room);

            house.addRoom(room);

            int position = 0;
            // Act
            boolean result = house.isDeviceListEmpty(position);

            // Assert
            assertFalse(result);
        }

        @Test
        public void testGetRoomListLength() {
            // Arrange
            double expectedResult = 1;

            // Act
            double result = house.houseRoomListSize();

            // Assert
            assertEquals(expectedResult, result, 0.001);

        }

    @Test
    public void testGetDeviceListContentNameTypeLocationByHG() {
        //Arrange
        //Room ONE
        String name = "Kitchen";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, "room", 2, dim);

            Device dev1 = house.createDevice(FRIDGE_TYPE, "FridgeAriston", room1);

            Device dev2 = house.createDevice(WASHING_MACHINE_TYPE, "WashingMachineBosh", room1);

            Device dev3 = house.createDevice(DISHWASHER_TYPE, "DishWasherSpecs", room1);


        //Room TWO
        String name2 = "KitchenBasement";
        Room room2 = new Room(name2, "room", -1, dim);

            Device dev4 = house.createDevice(FRIDGE_TYPE, "FridgeSiemens", room2);
            Device dev5 = house.createDevice(DISHWASHER_TYPE, "DishWasherTeka", room2);

            Device dev6 = house.createDevice(ELECTRIC_W_H_TYPE, "ElectricWaterHeaterSpecs", room2);


            HouseGrid hg = new HouseGrid("Grid");
            house.addGrid(hg);
            hg.addRoom(room1);
            hg.addRoom(room2);

            //add to Lists
            house.addRoom(room1);
            house.addRoom(room2);

            String expectedResult = "WashingMachine\n" +
                    "- Device Name: WashingMachineBosh, Location: Kitchen.\n" +
                    "\n" +
                    "DishWasher\n" +
                    "- Device Name: DishWasherSpecs, Location: Kitchen.\n" +
                    "- Device Name: DishWasherTeka, Location: KitchenBasement.\n" +
                    "\n" +
                    "ElectricWaterHeater\n" +
                    "- Device Name: ElectricWaterHeaterSpecs, Location: KitchenBasement.\n" +
                    "\n" +
                    "Fridge\n" +
                    "- Device Name: FridgeAriston, Location: Kitchen.\n" +
                    "- Device Name: FridgeSiemens, Location: KitchenBasement.\n\n";

            String result = house.getDeviceListContentNameTypeLocationByGrid(0);
            //Assert
            assertEquals(expectedResult, result);
        }

        /* --> TESTE A SER ALTERADO DEPOIS DE IMPLEMENTAÇÃO DE DTO's.
            @Test
            public void getEnergyConsumptionInADayOfAllDevicesOfATypeTestWithValidValues() {
                // Arrange
                // Dimension Instantiation
                double height = 3;
                double length = 3.5;
                double width = 3.5;
                Dimension dim = new Dimension(height, length, width);

                // Room Instantiation
                Room room = new Room("Room", 2, dim);

                // ElectricWaterHeaterSpecs Instantiation
                Device device0 = housegrid.createDevice(ELECTRIC_W_H_TYPE, "ElectricWaterHeater", room);
                device0.setAttributesDevType("Hot-Water Temperature",50);
                device0.setAttributesDevType("Volume Of Water To Heat",150);
                device0.setAttributesDevType("Performance Ratio",0.9);
                device0.setAttributesDevType("Nominal Power",100);

                housegrid.addRoom(room);

                int coldWaterTempPosition = 5;
                int volumeOfWaterToHeatPosition = 6;
                housegrid.setDeviceAttribute("ElectricWaterHeater", 0, coldWaterTempPosition, 30);
                housegrid.setDeviceAttribute("ElectricWaterHeater", 0, volumeOfWaterToHeatPosition, 100);

                double expectedResult = 2.09;

                // Act
                double result = housegrid.getDailyEnergyConsumptionOfADevice("ElectricWaterHeater", 0);

                // Assert
                assertEquals(expectedResult, result, 0.000001);
            }

            */
    @Test
    public void getNameByHGPosition() {
        // Arrange
        // Instantiate House Grids
        String gridName0 = "Grid0";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid1";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid2";
        HouseGrid grid2 = new HouseGrid(gridName2);


        house.addGrid(grid0);
        house.addGrid(grid1);
        house.addGrid(grid2);

        int position = 0;
        String expectedResult = "Grid0";

        // Act
        String result = house.getGridNameByPosition(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceNameOfATypeByPositionTest() {
        // Arrange
        String expectedResult = "Bosch Tronic 3000";

        // Act
        String result = house.getDeviceNameOfATypeByPosition("ElectricWaterHeater", 0);

        assertEquals(expectedResult, result);
    }

    @Test
    public void getNameByHGPositionEmpty() {
        // Arrange

        int position = 0;
        String expectedResult = "There are no Grids in the housegrid";

        // Act
        String result = house.getGridNameByPosition(position);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithPositiveTest() {
        // Arrange

        // Act
        boolean result = house.isHouseGridListEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkIfHouseGridListIsEmptyWithNegativeTest() {
        // Arrange
        String gridName = "Grid";
        HouseGrid grid = new HouseGrid(gridName);
        house.addGrid(grid);
        // Act
        boolean result = house.isHouseGridListEmpty();

        // Assert
        assertFalse(result);
    }

   /* @Test
    public void checkIfARoomIsAlreadyInAHouseGridOfTheListWithNegativeTest() {
        // Arrange
        String roomName = "Kitchen";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room = new Room(roomName, houseFloor1, dimension1);
        String gridName = "Grid";
        housegrid grid = housegrid.createHouseGrid(gridName);
        housegrid.addGrid(grid);

        // Act
        boolean result = housegrid.checkIfRoomIsAlreadyInHouseGrid(grid, room);

        // Assert
        assertFalse(result);
    }*/


    @Test
    public void getTheGridWhereTheRoomIsConnectedTest() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        String description = "room";
        int houseFloor = 0;
        Dimension dimension = new Dimension(4, 10, 12);
        Room room = new Room(roomName, description, houseFloor, dimension);

        // Instantiate House Grids
        String gridName0 = "Grid1";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid2";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid3";
        HouseGrid grid2 = new HouseGrid(gridName2);
        grid2.addRoom(room);

        house.addGrid(grid0);
        house.addGrid(grid1);
        house.addGrid(grid2);

        HouseGrid expectedResult = grid2;

        // Act
        HouseGrid result = house.getTheGridWhereTheRoomIsConnected(room);

        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getTheGridWhereTheRoomIsConnectedNullTest() {
        // Arrange
        // Instantiate Room
        String roomName = "Kitchen";
        String description = "room";
        int houseFloor = 0;
        Dimension dimension = new Dimension(4, 10, 12);
        Room room = new Room(roomName, description, houseFloor, dimension);

        // Instantiate House Grids
        String gridName0 = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName0);
        String gridName1 = "Grid";
        HouseGrid grid1 = new HouseGrid(gridName1);
        String gridName2 = "Grid";
        HouseGrid grid2 = new HouseGrid(gridName2);


        house.addGrid(grid0);
        house.addGrid(grid1);
        house.addGrid(grid2);

        HouseGrid expectedResult = null;

        // Act
        HouseGrid result = house.getTheGridWhereTheRoomIsConnected(room);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void displayOfTheContentOfTheHouseGrids() {
        // Arrange
        //grid
        String gridName0 = "Grid";
        String gridName1 = "Grid2";
        HouseGrid grid0 = new HouseGrid(gridName0);
        HouseGrid grid1 = new HouseGrid(gridName1);
        house.addGrid(grid0);
        house.addGrid(grid1);
        String expectedResult = "1 - Name: Grid\n2 - Name: Grid2\n";

        // Act
        String result = house.getHouseGridListToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthTest() {
        // Arrange
        //grid
        String gridName = "Grid";
        String gridName1 = "Grid2";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName1);
        house.addGrid(grid0);
        house.addGrid(grid1);
        int expectedResult = 2;

        // Act
        int result = house.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getHouseGridListLengthEmptyListTest() {
        // Arrange
        int expectedResult = 0;

        // Act
        int result = house.getHouseGridListSize();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAGridFromASpecificPosition() {
        // Arrange
        //grid
        String gridName = "Grid";
        HouseGrid grid0 = new HouseGrid(gridName);
        HouseGrid grid1 = new HouseGrid(gridName);
        house.addGrid(grid0);
        house.addGrid(grid1);
        HouseGrid expectedResult = grid0;

        // Act
        HouseGrid result = house.getHouseGridByPosition(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfThereAreNoDevicesHGbyPosition() {
        // Arrange
        //Room TWO
        String name2 = "KitchenBasement";
        String description = "room";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, description, -1, dim2);

        house.createDevice(FRIDGE_TYPE, "FridgeSiemens", room2);

        house.createDevice(DISHWASHER_TYPE, "DishWasherTeka", room2);

        house.createDevice(ELECTRIC_W_H_TYPE, "ElectricWaterHeaterSpecs", room2);

        HouseGrid grid = new HouseGrid("g1");
        grid.addRoom(room2);
        grid.addRoom(room2);
        house.addGrid(grid);

        // Act
        boolean result = house.checkIfThereAreNoDevicesInGridbyPosition(0);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesTrue() {
        // Arrange
        HouseGrid grid = new HouseGrid("g1");
        house.addGrid(grid);
        // Act
        boolean result = house.checkIfThereAreNoDevicesInGridbyPosition(0);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testGetNumberOfDevicesOfAType() {
        //Arrange
        //Room ONE
        String name = "Kitchen";
        String description = "room";
        Dimension dim = new Dimension(3.5, 10.5, 20.5);
        Room room1 = new Room(name, description, 2, dim);


        house.createDevice(FRIDGE_TYPE, "FridgeAriston", room1);
        house.createDevice(WASHING_MACHINE_TYPE, "WashingMachineBosh", room1);
        house.createDevice(DISHWASHER_TYPE, "DishWasher", room1);


        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, description, -1, dim2);

        house.createDevice(FRIDGE_TYPE, "FridgeSiemens", room2);
        house.createDevice(DISHWASHER_TYPE, "DishWasherTeka", room2);
        house.createDevice(ELECTRIC_W_H_TYPE, "ElectricWaterHeater", room2);

        int expectedResult = 2;

        house.addRoom(room1);
        house.addRoom(room2);

        int result = house.getNumberOfDevicesOfAType("Fridge");

        assertEquals(expectedResult, result);

    }

    @Test
    public void checkIfRoomListIsEmptyTrue() {
        // Arrange
        house.getRoomList().getListOfRooms().remove(laundry);

        // Act
        boolean result = house.roomListIsEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void getDeviceNameFromDeviceListExceptionTest() {
        // Arrange
        laundry.removeDevice(electricWaterHeater);
        String expectedResult = "There are no devices in the device list.";

        // Act
        String result = house.getDeviceNameOfATypeByPosition("ElectricWaterHeater", 0);

        assertEquals(expectedResult, result);
    }

    @Test
    public void getAllDevicesToStringTest() {
        // Arrange
        String expectedResult = "1 - Device: Bosch Tronic 3000, located in room: Laundry\n";

        // Act
        String result = house.getAllDevicesToString();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfRoomListIsEmptyFalse() {
        //arrange

        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        house.addRoom(room1);

        //act
        boolean result = house.roomListIsEmpty();
        //assert
        assertFalse(result);
    }

    @Test
    public void getDeviceListSizeEmptyList() {
        // Arrange
        house.getRoomList().getListOfRooms().remove(laundry);
        int expectResult = 0;

        // Act
        int result = house.getDeviceSize();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void getAllDevicesTest() {
        // Arrange
        List<Device> expectedResult = new ArrayList<>();
        expectedResult.add(electricWaterHeater);

        // Act
        List<Device> result = house.getAllDevices();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNumberOfDevicesTest() {
        // Arrange
        // Dimension Instantiation
        Dimension dim = new Dimension(3, 5, 6);

        // Room Instantiation
        Room room0 = new Room("Kitchen", "room", 1, dim);
        Room room1 = new Room("Laundry", "room", 2, dim);

        house.addRoom(room0);
        house.addRoom(room1);


        // Device Creation
        house.createDevice(FRIDGE_TYPE, "Fridgeratah V14", room0);
        house.createDevice(ELECTRIC_W_H_TYPE, "Bosch Tronic 3000", room1);

        int expectedResult = 2;

        // Act
        int result = house.getNumberOfDevices();

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceByPositionTest() {
        // Arrange
        Device expectedResult = electricWaterHeater;

        // Act
        Device result = house.getDeviceByPosition(0);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeviceListSize() {
        // Arrange
        int expectResult = 1;

        // Act
        int result = house.getDeviceSize();

        // Assert
        assertEquals(expectResult, result);
    }

    @Test
    public void testGetDateLastTemperatureOfTheHouseArea() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Temperature");
        Location locS0 = new Location(41.178553, -8.608035, 111);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("421"), "A122", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Temperature");
        Location locS1 = new Location(41.178553, -8.608035, 111);
        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("131"), "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s1);


        //Instantiate Reading
        // Sensor0
        LocalDateTime dataHoraDaMedicao01 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao02 = LocalDateTime.of(2018, 12, 4, 17, 24, 00);

        Reading reading01 = new Reading(23, dataHoraDaMedicao01);
        Reading reading02 = new Reading(30, dataHoraDaMedicao02);

        s0.addReadingsToList(reading01);
        s0.addReadingsToList(reading02);

        //Sensor1
        LocalDateTime dataHoraDaMedicao11 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        LocalDateTime dataHoraDaMedicao12 = LocalDateTime.of(2018, 12, 3, 17, 24, 00);

        Reading reading11 = new Reading(22, dataHoraDaMedicao11);
        Reading reading12 = new Reading(25, dataHoraDaMedicao12);

        s1.addReadingsToList(reading11);
        s1.addReadingsToList(reading12);

        LocalDateTime expectedResult = LocalDateTime.of(2018, 12, 4, 17, 24);

        SensorTypeId type = new SensorTypeId("Temperature");

        //Act
        LocalDateTime result = house.getDateOfLastMeasurementByType(type);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetInsertedGeoArea() {
        //arrange
        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", "room", 2, dim);

        //initiate House
        Location location = new Location(42.0, -8.608035, 111);
        AreaShape areaShape = new AreaShape(1.5, 2.5);
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId("Urban area");
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        GeographicalArea geo = new GeographicalArea("ISEP", "Campus do ISEP", geographicalAreaType, location, areaShape);

        house.setInsertedGeoArea(geo);

        GeographicalArea expectedResult = geo;

        GeographicalArea result = house.getInsertedGeoArea();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testCheckIfThereAreNoDevicesFalse() {
        // Arrange

        //initiate Room
        Dimension dim = new Dimension(3, 3.5, 3.5);
        Room room = new Room("Room", "room", 2, dim);

        //Room TWO
        String name2 = "KitchenBasement";
        Dimension dim2 = new Dimension(3.5, 30.5, 20.5);
        Room room2 = new Room(name2, "room", -1, dim2);


        // Device Instantiation
        house.createDevice(FRIDGE_TYPE, "Fridgeratah V14", room);

        house.createDevice(ELECTRIC_W_H_TYPE, "Bosch Tronic 3000", room2);


        house.addRoom(room);
        house.addRoom(room2);

        // Act
        boolean result = house.isDeviceListOfAllRoomsEmpty();

        // Assert
        assertFalse(result);
    }


    @Test
    public void testCheckIfThereAreNoDevicesOnRoomsTrue() {
        // Arrange
        laundry.removeDevice(electricWaterHeater);

        // Act
        boolean result = house.isDeviceListOfAllRoomsEmpty();

        // Assert
        assertTrue(result);
    }


    @Test
    public void testNewConstructorGrid() {
        //arrange
        //initiate House
        int meteringPeriodGrid = 15;
        int meteringPeriodDevice = 15;

        List<String> deviceTypeList = new ArrayList<>();
        deviceTypeList.add("Fridge");
        deviceTypeList.add("Lamp");
        deviceTypeList.add("DishWasher");
        deviceTypeList.add("WashingMachine");
        deviceTypeList.add("ElectricWaterHeater");

        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        int expectedResult = 15;


        //Act
        int result = house.getMeteringPeriodGrid();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testNewConstructorDevice() {
        //arrange
        //initiate House
        int meteringPeriodGrid = 15;
        int meteringPeriodDevice = 15;

        List<String> deviceTypeList = new ArrayList<>();
        deviceTypeList.add("Fridge");
        deviceTypeList.add("Lamp");
        deviceTypeList.add("DishWasher");
        deviceTypeList.add("WashingMachine");
        deviceTypeList.add("ElectricWaterHeater");

        House house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);

        int expectedResult = 15;


        //Act
        int result = house.getMeteringPeriodDevice();

        //Assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void testAddGridTrue() {
        //Arrange
        String gridName = "main grid";
        HouseGrid houseGrid = new HouseGrid(gridName);
        //Act
        boolean result = this.house.addGrid(houseGrid);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetDateLastTemperatureOfTheHouseAreaWithoutMeasurements() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        //Instantiate Sensors
        LocalDateTime dataFuncionamento0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        SensorTypeId sensorType0 = new SensorTypeId("Temperature");
        Location locS0 = new Location(-1, 30, 50);
        GeoAreaSensor s0 = new GeoAreaSensor(new SensorId("213"), "A123", dataFuncionamento0, sensorType0, locS0, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s0);

        LocalDateTime dataFuncionamento1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        SensorTypeId sensorType1 = new SensorTypeId("Temperature");
        Location locS1 = new Location(32.1576, 7.6199, 100);
        GeoAreaSensor s1 = new GeoAreaSensor(new SensorId("r32"), "A123", dataFuncionamento1, sensorType1, locS1, "l/m2");
        ag.getSensorListInTheGeographicArea().addSensor(s1);

        LocalDateTime expectedResult = null;

        SensorTypeId type = new SensorTypeId("Temperature");

        //Act
        LocalDateTime result = house.getDateOfLastMeasurementByType(type);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDateGetLastTemperatureOfTheHouseAreaWithoutSensors() {
        //arrange
        String name1 = "Kitchen";
        String description = "room";
        int houseFloor1 = 0;
        Dimension dimension1 = new Dimension(2, 2, 2);
        Room room1 = new Room(name1, description, houseFloor1, dimension1);

        String name2 = "Living Room";
        int houseFloor2 = 1;
        Dimension dimension2 = new Dimension(2, 1.5, 1.3);
        Room room2 = new Room(name2, description, houseFloor2, dimension2);

        house.addRoom(room1);
        house.addRoom(room2);

        LocalDateTime expectedResult = null;

        SensorTypeId type = new SensorTypeId("Temperature");

        //Act
        LocalDateTime result = house.getDateOfLastMeasurementByType(type);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getFirstHighestReading_WithOnlyOneDoubleNaNValue_Null() {
        //Arrange
        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);
        this.house.setInsertedGeoArea(portoCity);


        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("543"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        Reading reading = new Reading(Double.NaN, readingDate);
        temperatureSensor1.addReadingsToList(reading);

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        //interval LocalDate
        LocalDate startDate = LocalDate.of(2018, 12, 2);
        LocalDate endDate = LocalDate.of(2018, 12, 4);

        Reading expectedResult = reading;

        //Act
        Reading result = this.house.getFirstHighestReadingHouseArea(temperature, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddGridFalse() {
        //Arrange
        String gridName = "main grid";
        HouseGrid houseGrid = new HouseGrid(gridName);
        this.house.addGrid(houseGrid);
        //Act
        boolean result = this.house.addGrid(houseGrid);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testGridNameAlreadyExistsTrue() {
        //Arrange
        String gridName = "main grid";
        HouseGrid grid = new HouseGrid(gridName);
        this.house.addGrid(grid);
        //Act
        boolean result = this.house.gridNameAlreadyExists(gridName);
        //Assert
        assertTrue(result);
    }

    @Test
    public void testGridNameAlreadyExistsFalse() {
        //Arrange
        String gridName = "main grid";
        HouseGrid grid = new HouseGrid(gridName);
        this.house.addGrid(grid);
        String gridName2 = "grid 1";
        //Act
        boolean result = this.house.gridNameAlreadyExists(gridName2);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testGridNameAlreadyExists_WithNoHouseGrids_ShouldReturnFalse() {
        //Arrange
        String gridName2 = "grid 1";
        //Act
        boolean result = this.house.gridNameAlreadyExists(gridName2);
        //Assert
        assertFalse(result);
    }

    @Test
    public void testNewHouseGrid_ThrowsException() {
        //Arrange
        String name = "Main Grid";
        HouseGrid grid = new HouseGrid(name);
        this.house.addGrid(grid);
        //Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                this.house.createHouseGrid(grid)
        );
        //Assert
        assertEquals("Name already exists. Please, write a new one.", exception.getMessage());
    }

    @Test
    public void testNewHouseGrid_CreatesHouseGrid() {
        //Arrange
        String name = "Main Grid";
        HouseGrid expectedResult = new HouseGrid(name);
        //Act
        boolean result = this.house.createHouseGrid(expectedResult);
        //Assert
        assertTrue(result);
    }

    @Test
    public void getDeviceByNameExceptionTest() {
        // Arrange
        String expectedResult = "There isn't any device with that name.";

        // Act
        Throwable exception = assertThrows(RuntimeException.class, () ->
                house.getDeviceByName("Whatever")
        );

        // Assert
        assertEquals(expectedResult, exception.getMessage());
    }

    @Test
    public void setAttributeTrueTest() {
        // Act
        boolean result = house.setDeviceAttribute("Bosch Tronic 3000", "Cold-Water Temperature", 40);

        // Assert
        assertTrue(result);
    }

    @Test
    public void setAttributeFalseTest() {
        // Act
        boolean result = house.setDeviceAttribute("Bosch Tronic 3000", "Hot-Water Temperature", 70);

        // Assert
        assertFalse(result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added, included a negative value
     * expected result {2018-12-04=20, 2018-12-03=6.0, 2018-12-02=7.0}
     */
    @Test
    public void getDailyAmplitudeInterval() {
        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "Porto District", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "Porto City", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("123"), "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("2131"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);

        //Add sensors to Sensorlist

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);


        // Extra Reading
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(29, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(-5, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(15, time3);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 4, 23, 59, 00);


        // Map
        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time0.toLocalDate(), 7.0);
        expectedResult.put(time1.toLocalDate(), 6.0);
        expectedResult.put(time2.toLocalDate(), 20.0);

        //Act
        Map<LocalDate, Double> result = this.house.getDailyAmplitudeInIntervalInHouseArea(temperature, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * 12/04/2018 has only a DoubleNan values, so the amplitude in that day will be DoubleNan.
     * expected result {2018-12-04=NaN, 2018-12-03=6.0, 2018-12-02=7.0}
     */
    @Test
    public void getDailyAmplitudeInterval_doubleNanValuesFor4_12_2018() {

        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("123"), "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("12321"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);

        //Add sensors to Sensorlist

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        // Extra Reading
        double value = Double.NaN;
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(29, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(value, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(value, time3);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 4, 23, 59, 00);

        // Map expected
        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time0.toLocalDate(), 7.0);
        expectedResult.put(time1.toLocalDate(), 6.0);
        expectedResult.put(time2.toLocalDate(), value);

        //Act
        Map<LocalDate, Double> result = this.house.getDailyAmplitudeInIntervalInHouseArea(temperature, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * 12/04/2018 has one DoubleNan value and two valid values, so the amplitude in that day will be calculated
     * with that two valid values
     * expected result {2018-12-04=12, 2018-12-03=6.0, 2018-12-02=7.0}
     */
    @Test
    public void getDailyAmplitudeInterval_oneDayOneDoubleNanValueTwoValidValues_For4_12_2018() {

        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("123"), "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("12321"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);

        //Add sensors to Sensorlist

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        // Extra Reading
        double value = Double.NaN;
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(29, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(value, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(12, time3);
        LocalDateTime time4 = LocalDateTime.of(2018, 12, 4, 13, 20, 00);
        Reading reading8 = new Reading(24, time4);

        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);
        temperatureSensor1.addReadingsToList(reading8);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 2, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 4, 23, 59, 00);

        // Map expected
        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time0.toLocalDate(), 7.0);
        expectedResult.put(time1.toLocalDate(), 6.0);
        expectedResult.put(time2.toLocalDate(), 12.0);

        //Act
        Map<LocalDate, Double> result = this.house.getDailyAmplitudeInIntervalInHouseArea(temperature, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getDailyAmplitudeInterval_emptySensor_emptyMap() {

        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("12321"), "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("1231234"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);

        //Add sensors to Sensorlist

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        //interval LocalDate
        LocalDateTime startDateTime = LocalDateTime.of(2018, 12, 12, 00, 00, 01);
        LocalDateTime endDateTime = LocalDateTime.of(2018, 12, 14, 23, 59, 00);

        // Map expected
        Map<LocalDate, Double> expectedResult = new HashMap<>();

        //Act
        Map<LocalDate, Double> result = this.house.getDailyAmplitudeInIntervalInHouseArea(temperature, location2, startDateTime.toLocalDate(), endDateTime.toLocalDate());

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * 4/12/2018 is the expected date with the daily highest amplitude
     * expected highest amplipude is 20.
     */
    @Test
    public void getHighestDailyAmplitude_4_12_2018_amplitude20() {

        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("213123"), "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("123123"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);

        //Add sensors to Sensorlist

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        // LocalDate
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);

        // Maps
        Map<LocalDate, Double> dailyAmplitudes = new HashMap<>();

        dailyAmplitudes.put(time0.toLocalDate(), 7.0);
        dailyAmplitudes.put(time1.toLocalDate(), 6.0);
        dailyAmplitudes.put(time2.toLocalDate(), 20.0);

        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time2.toLocalDate(), 20.0);

        //Act
        Map<LocalDate, Double> result = this.house.getHighestDailyAmplitudeInHouseArea(dailyAmplitudes);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * beforeach has some readings, extra ones where added
     * there is a doubleNan value for the amplitude in 4/12/2018
     * 2/12/2018 is the expected date with the daily highest amplitude
     * expected highest amplipude is 7.
     */
    @Test
    public void getHighestDailyAmplitude_doubleNanValuesIn4_12_2018_highestAmplitude7() {

        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("123"), "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("543"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);

        //Add sensors to Sensorlist

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        // LocalDate
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);

        // Maps
        Map<LocalDate, Double> dailyAmplitudes = new HashMap<>();

        double value = Double.NaN;
        dailyAmplitudes.put(time0.toLocalDate(), 7.0);
        dailyAmplitudes.put(time1.toLocalDate(), 6.0);
        dailyAmplitudes.put(time2.toLocalDate(), value);

        Map<LocalDate, Double> expectedResult = new HashMap<>();
        expectedResult.put(time0.toLocalDate(), 7.0);

        //Act
        Map<LocalDate, Double> result = this.house.getHighestDailyAmplitudeInHouseArea(dailyAmplitudes);

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * temperatureSensor1 is the nearest sensor in Geographical area portocity
     * the Map is empty
     * expected a empty Map.
     */
    @Test
    public void getHighestDailyAmplitude_emptyMap_emptyMap() {

        // Maps
        Map<LocalDate, Double> dailyAmplitudesEmpty = new HashMap<>();

        Map<LocalDate, Double> expectedResult = new HashMap<>();

        //Act
        Map<LocalDate, Double> result = this.house.getHighestDailyAmplitudeInHouseArea(dailyAmplitudesEmpty);

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNearestSensorWithMostRecentReading_ValidReading() {
        //Arrange
        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("123"), "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("543"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);

        //Add sensors to Sensorlist

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        GeoAreaSensor expectedResult = temperatureSensor1;
        //Act
        GeoAreaSensor result = this.house.getNearestSensorWithMostRecentReading(temperature, houseLocation);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getNearestSensorWithMostRecentReading_isNull() {
        //Arrange
        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("123"), "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("543"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);

        //Add sensors to Sensorlist

        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        SensorTypeId rainfall = new SensorTypeId("rainfall");
        GeoAreaSensor expectedResult = null;
        //Act
        GeoAreaSensor result = this.house.getNearestSensorWithMostRecentReading(rainfall, houseLocation);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void isSensorListEmpty_False() {
        // Arrange
        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("123"), "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("543"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);


        // Act
        boolean result = this.house.isSensorListOfAGivenTypeEmpty(temperature);

        // Assert
        assertFalse(result);
    }

    @Test
    public void isSensorListEmptyTest_True() {
        // Arrange
        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);

        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("123"), "A123", startDate, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("543"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);

        // Act
        boolean result = this.house.isSensorListOfAGivenTypeEmpty(temperature);

        // Assert
        assertTrue(result);
    }


    @Test
    public void getFirstHighestReading_ValidReading_Reading4() {
        //Arrange
        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);
        this.house.setInsertedGeoArea(portoCity);


        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("123"), "A123", startDate0, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("543"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(31, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(31, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(31, time3);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        //interval LocalDate
        LocalDate startDate = LocalDate.of(2018, 12, 2);
        LocalDate endDate = LocalDate.of(2018, 12, 4);

        Reading expectedResult = reading4;

        //Act
        Reading result = this.house.getFirstHighestReadingHouseArea(temperature, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void getFirstHighestReading_noReadingInGivenInterval_Null() {
        //Arrange
        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);
        this.house.setInsertedGeoArea(portoCity);


        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("123"), "A123", startDate0, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("543"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(31, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(31, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(31, time3);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        //interval LocalDate
        LocalDate startDate = LocalDate.of(2018, 12, 6);
        LocalDate endDate = LocalDate.of(2018, 12, 8);

        Reading expectedResult = null;

        //Act
        Reading result = this.house.getFirstHighestReadingHouseArea(temperature, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void getFirstHighestReading_WithValidValueAndDoubleNaN_reading5() {
        //Arrange
        //Arrange
        // Geographical Area Types
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId("Region");
        GeoAreaTypeId geoAreaTypeId2 = new GeoAreaTypeId("District");
        GeoAreaTypeId geoAreaTypeId3 = new GeoAreaTypeId("City");
        GeographicalAreaType region = new GeographicalAreaType(geoAreaTypeId1);
        GeographicalAreaType district = new GeographicalAreaType(geoAreaTypeId2);
        GeographicalAreaType city = new GeographicalAreaType(geoAreaTypeId3);

        // Geographical Areas
        Location location = new Location(32.1496, 7.6109, 98);
        AreaShape areaShape = new AreaShape(100, 100);
        GeographicalArea northernRegion = new GeographicalArea("Norte", "Northern Region", region, location, areaShape);
        Location location1 = new Location(41.1496, -6.6109, 100);
        AreaShape areaShape1 = new AreaShape(40, 40);
        GeographicalArea portoDistrict = new GeographicalArea("Porto District", "District of Porto", district, location1, areaShape1);
        portoDistrict.setInsertedIn(northernRegion);
        Location location2 = new Location(42.1496, -8.6109, 97);
        AreaShape areaShape2 = new AreaShape(10, 10);
        GeographicalArea portoCity = new GeographicalArea("Porto City", "City of Porto", city, location2, areaShape2);
        portoCity.setInsertedIn(portoDistrict);

        // House
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(CONFIG_PROPERTIES, "MeteringPeriodDevice"));
        List<String> deviceTypeList = Utils.readConfigFileToList(CONFIG_PROPERTIES, "devicetype.count", "devicetype.name");
        this.house = new House(deviceTypeList, meteringPeriodGrid, meteringPeriodDevice);
        Location houseLocation = new Location(41.178553, -8.608035, 111);
        Address address = new Address("4200-072", houseLocation, portoCity);
        this.house.setAddress(address);
        this.house.setInsertedGeoArea(portoCity);


        // Sensors
        SensorTypeId temperature = new SensorTypeId("Temperature");
        LocalDateTime startDate0 = LocalDateTime.of(2018, 12, 2, 15, 20, 00);
        Location sensorLocation = new Location(38.1596, -8.6109, 97);
        GeoAreaSensor temperatureSensor = new GeoAreaSensor(new SensorId("123"), "A123", startDate0, temperature, sensorLocation, "l/m2");
        LocalDateTime startDate1 = LocalDateTime.of(2018, 12, 5, 15, 20, 00);
        Location sensorLocation1 = new Location(42.1496, -8.6109, 97);
        GeoAreaSensor temperatureSensor1 = new GeoAreaSensor(new SensorId("543"), "B123", startDate1, temperature, sensorLocation1, "l/m2");

        // Reading
        LocalDateTime readingDate = LocalDateTime.of(2018, 12, 2, 13, 20, 00);
        LocalDateTime readingDate1 = LocalDateTime.of(2018, 12, 3, 13, 24, 00);
        Reading reading = new Reading(23, readingDate);
        Reading reading1 = new Reading(30, readingDate1);
        temperatureSensor.addReadingsToList(reading);
        temperatureSensor.addReadingsToList(reading1);
        LocalDateTime readingDate2 = LocalDateTime.of(2018, 12, 2, 05, 20, 00);
        LocalDateTime readingDate3 = LocalDateTime.of(2018, 12, 3, 05, 24, 00);
        Reading reading2 = new Reading(22, readingDate2);
        Reading reading3 = new Reading(25, readingDate3);
        temperatureSensor1.addReadingsToList(reading2);
        temperatureSensor1.addReadingsToList(reading3);
        LocalDateTime time0 = LocalDateTime.of(2018, 12, 2, 12, 20, 00);
        Reading reading4 = new Reading(Double.NaN, time0);
        LocalDateTime time1 = LocalDateTime.of(2018, 12, 3, 13, 20, 00);
        Reading reading5 = new Reading(31, time1);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 4, 06, 20, 00);
        Reading reading6 = new Reading(31, time2);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 4, 12, 20, 00);
        Reading reading7 = new Reading(31, time3);
        temperatureSensor1.addReadingsToList(reading4);
        temperatureSensor1.addReadingsToList(reading5);
        temperatureSensor1.addReadingsToList(reading6);
        temperatureSensor1.addReadingsToList(reading7);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor);
        portoCity.getSensorListInTheGeographicArea().addSensor(temperatureSensor1);

        //interval LocalDate
        LocalDate startDate = LocalDate.of(2018, 12, 2);
        LocalDate endDate = LocalDate.of(2018, 12, 4);

        Reading expectedResult = reading5;

        //Act
        Reading result = this.house.getFirstHighestReadingHouseArea(temperature, startDate, endDate);
        //Assert
        assertEquals(expectedResult, result);
    }
}

