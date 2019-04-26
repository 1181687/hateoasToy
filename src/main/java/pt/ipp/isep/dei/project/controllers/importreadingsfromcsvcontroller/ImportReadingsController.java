package pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller;
/*
import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;
import pt.ipp.isep.dei.project.model.house.HouseService;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorList;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorList;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportReadingsController {
    private static final Logger LOGGER = Logger.getLogger(ImportReadingsController.class.getName());
    *//*private static final String READING_MESSAGE_ERROR = "Reading not imported: date of reading is before starting date of sensor. ";
    private static final String DUPLICATE_READING_MESSAGE_ERROR = "Reading not imported: duplicate reading ";
    private static final String SENSOR_NOT_ACTIVE = "Reading not imported: Sensor is deactive on the specific date.";
    private static final String SENSOR_NOT_EXISTS = "Reading not imported: Sensor does not exist.";
    private static final String DATE_INVALID = "Reading not imported: Invalid date. ";
    *//*

    @Autowired
    private GeographicalAreaService geographicalAreaService;
    private GeoAreaSensorList allSensorInTheGeoAreas;
    private GeoAreaSensor geoAreaSensor;
    private RoomSensor roomSensor;
    private Room room;
    private RoomSensorList allSensorInTheHouse;
    private List<Object> readingDTOList;
    private int numberOfNotImportedReadings;
    @Autowired
    private HouseService houseService;

    *//**
 * Constructor.
 *
 * @param geographicalAreaService GeographicalAreaService to be used.
 * @param houseService
 * <p>
 * Method that checks if a given date time is before the starting date of the sensor.
 * @param localDateTime Given date time to be compared.
 * @return True or False.
 * <p>
 * Method that adds a reading to the sensor.
 * @param readingDTO ReadingDTO to be transformed into a Reading and to be used.
 * <p>
 * Method that configures the log file, using a FileHandler object to send log information to the specified log file.
 * The last line is responsible for not letting the information show up in the console.
 *//*
    public ImportReadingsController(GeographicalAreaService geographicalAreaService, HouseService houseService) {
        this.geographicalAreaService = geographicalAreaService;
        this.allSensorInTheGeoAreas = this.geographicalAreaService.getAllSensors();
        this.houseService = houseService;
        this.allSensorInTheHouse = this.houseService.getAllSensors();
    }

    *//**
 * Method that checks if a given date time is before the starting date of the sensor.
 *
 * @param localDateTime Given date time to be compared.
 * @return True or False.
 *//*
    public boolean isDateTimeBeforeSensorStartingDate(LocalDateTime localDateTime) {
        return localDateTime.isBefore(geoAreaSensor.getStartingDate());
    }

    public boolean isDateTimeBeforeRoomSensorStartingDate(LocalDateTime localDateTime) {
        return localDateTime.isBefore(roomSensor.getStartingDate());
    }

    *//**
 * Method that adds a reading to the sensor.
 *
 * @param readingDTO ReadingDTO to be transformed into a Reading and to be used.
 *//*
    public void addReadingToSensor(ReadingDTO readingDTO) {
        Reading reading = ReadingMapper.mapToEntity(readingDTO);
        geoAreaSensor.addReadingsToList(reading);
    }

    public int getNumberOfNotImportedReadings() {
        return this.numberOfNotImportedReadings;
    }

    public boolean addReadingToSensorById(int option) {
        configLogFile();
        boolean imported = false;
        boolean importedRoomReading = false;
        for (Object object : this.readingDTOList) {
            ReadingDTO reading = (ReadingDTO) object;
            if (option == 1) {
                geoAreaSensor = allSensorInTheGeoAreas.getSensorById(reading.getId());
                if (Objects.isNull(geoAreaSensor)) {
                    numberOfNotImportedReadings++;
                    continue;
                }
                if (Objects.isNull(reading.getDateTime())) {
                    numberOfNotImportedReadings++;
                    String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
                    LOGGER.log(Level.WARNING, "Reading not imported due to invalid timestamp/date " + invalidInfo);
                    continue;
                }

                if (Objects.isNull(geoAreaSensor.getId())) {
                    numberOfNotImportedReadings++;
                    LOGGER.log(Level.WARNING, "Reading was not imported because its sensor has a null id.");
                    continue;
                }

                if (isDateTimeBeforeSensorStartingDate(reading.getDateTime())) {
                    numberOfNotImportedReadings++;
                    String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
                    LOGGER.log(Level.WARNING, "Reading not imported due to timestamp/date of reading being before starting date of sensor: " + invalidInfo);
                    continue;
                }

                if (!allSensorInTheGeoAreas.geoAreaSensorExists(geoAreaSensor.getId())) {
                    numberOfNotImportedReadings++;
                    String invalidInfo = "id: " + geoAreaSensor.getId() + ".";
                    LOGGER.log(Level.WARNING, "Reading was not imported because the following sensor id doesn't exist: " + invalidInfo);
                    continue;
                }

                if (geoAreaSensor.readingExistsBySensorIdLocalDateTime(ReadingMapper.mapToEntity(reading))) {
                    numberOfNotImportedReadings++;
                    String invalidInfo = "sensor id: " + geoAreaSensor.getId() + ", timestamp/date: " + reading.getDateTime() + ", value: " + reading.getValue() + ".";
                    LOGGER.log(Level.WARNING, "Reading was not imported because the following reading is duplicated: doesn't exist:\n" + invalidInfo);
                    continue;
                }
            }
            if (option == 2) {
                roomSensor = houseService.getSensorById(reading.getId());
                if (Objects.nonNull(room) && !room.equals(houseService.getRoomWithRightSensor(reading.getId()))) {
                    this.houseService.updateRepository(room);
                    room = houseService.getRoomWithRightSensor(reading.getId());
                } else {
                    if (Objects.isNull(room)) {
                        room = houseService.getRoomWithRightSensor(reading.getId());
                    }
                }

                if (Objects.isNull(roomSensor)) {
                    numberOfNotImportedReadings++;
                    continue;
                }

                if (Objects.isNull(reading.getDateTime())) {
                    numberOfNotImportedReadings++;
                    String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
                    LOGGER.log(Level.WARNING, "Reading not imported due to invalid timestamp/date " + invalidInfo);
                    continue;
                }

                if (isDateTimeBeforeRoomSensorStartingDate(reading.getDateTime())) {
                    numberOfNotImportedReadings++;
                    String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
                    LOGGER.log(Level.WARNING, "Reading not imported due to timestamp/date of reading being before starting date of sensor: " + invalidInfo);
                    continue;
                }

                if (!allSensorInTheHouse.roomSensorExists(roomSensor.getId())) {
                    numberOfNotImportedReadings++;
                    String invalidInfo = "id: " + geoAreaSensor.getId() + ".";
                    LOGGER.log(Level.WARNING, "Reading was not imported because the following sensor id doesn't exist: " + invalidInfo);
                    continue;
                }

                if (roomSensor.readingExistsBySensorIdLocalDateTime(new RoomReading(ReadingMapper.mapToEntity(reading).getValue(), ReadingMapper.mapToEntity(reading).getDateTime()))) {
                    numberOfNotImportedReadings++;
                    String invalidInfo = "sensor id: " + roomSensor.getId() + ", timestamp/date: " + reading.getDateTime() + ", value: " + reading.getValue() + ".";
                    LOGGER.log(Level.WARNING, "Reading was not imported because the following reading is duplicated: doesn't exist:\n" + invalidInfo);
                    continue;
                }

            }
            if (reading.getUnits().equals("F")) {
                double celsiusValue = Utils.convertFahrenheitToCelsius(reading.getValue());
                reading.setValue(Utils.round(celsiusValue, 2));
                reading.setUnits("C");
            }
            if (option == 1 && geoAreaSensor.addReading(ReadingMapper.mapToEntity(reading))) {
                imported = true;
                this.geographicalAreaService.updateRepository();
            }
            if (option == 2 && room.getSensorById(reading.getId()).addRoomReading(new RoomReading(ReadingMapper.mapToEntity(reading).getValue(), ReadingMapper.mapToEntity(reading).getDateTime()))) {

                importedRoomReading = true;

            }
        }
        if (importedRoomReading) {
            this.houseService.updateRepository(room);
            return importedRoomReading;
        }

        return imported;
    }

    public boolean isValidFormat(String fileName) {
        return fileName.endsWith(".csv") || fileName.endsWith(".json") || fileName.endsWith(".xml");
    }


    *//**
 * Method that configures the log file, using a FileHandler object to send log information to the specified log file.
 * The last line is responsible for not letting the information show up in the console.
 *//*
    private static void configLogFile() {
        FileHandler fh;
        try {
            fh = new FileHandler("log/outputErrors.log");
        } catch (IOException e) {
            fh = null;
        }
        LOGGER.addHandler(fh);
        LOGGER.setUseParentHandlers(false);
    }

    public ProjectFileReader createReader(String path) {
        return Utils.createReader(path);
    }

    public List<Object> readFile(File file, String path) throws FileNotFoundException {
        ProjectFileReader fileReader = createReader(path);
        readingDTOList = fileReader.readFile(file);
        return readingDTOList;
    }

    *//*
    public static void printxmlRoomReadings (String invalidSensorID, String invalidValue, String xmlOption) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("smart_home");
            doc.appendChild(rootElement);

            // error report elements
            Element errorsReport = doc.createElement("errors_report");
            rootElement.appendChild(errorsReport);


            // HOUSE
            Element house = doc.createElement("house");
            errorsReport.appendChild(house);


            // ROOM
            Element room =doc.createElement("room");
            house.appendChild(room);

            // SENSOR
            Element roomSensors = doc.createElement("sensor");
            room.appendChild(roomSensors);

            //READINGS
            Element roomSensorReadings = doc.createElement("room_sensor_reading");
            roomSensors.appendChild(roomSensorReadings);

            //Reading attribute id sensor
            Attr attrRoomSensorId =doc.createAttribute("sensor_id");
            attrRoomSensorId.setValue(invalidSensorID);
            roomSensorReadings.setAttributeNode(attrRoomSensorId);

            // reading error elements
            Element readingError = doc.createElement("reading_error");
            readingError.appendChild(doc.createTextNode(invalidValue));
            roomSensors.appendChild(readingError);
            switch (xmlOption) {
                case READING_MESSAGE_ERROR:
                    // cause elements - IF DATE IS BEFORE!!
                    Element dateBeforeMessage = doc.createElement("cause");
                    dateBeforeMessage.appendChild(doc.createTextNode(READING_MESSAGE_ERROR));
                    roomSensors.appendChild(dateBeforeMessage);
                    break;
                case DUPLICATE_READING_MESSAGE_ERROR:

                    // cause elements - IF DUPLICATE READING!!
                    Element duplicateValueMessage = doc.createElement("cause");
                    duplicateValueMessage.appendChild(doc.createTextNode(DUPLICATE_READING_MESSAGE_ERROR));
                    roomSensors.appendChild(duplicateValueMessage);
                    break;
                case SENSOR_NOT_ACTIVE:
                    // cause elements - IF SENSOR IS NOT ACTIVE!!
                    Element sensorDeactive = doc.createElement("cause");
                    sensorDeactive.appendChild(doc.createTextNode(SENSOR_NOT_ACTIVE));
                    roomSensors.appendChild(sensorDeactive);
                    break;
                case SENSOR_NOT_EXISTS:
                    // cause elements - IF SENSOR NOT EXISTS!!
                    Element sensorNull = doc.createElement("cause");
                    sensorNull.appendChild(doc.createTextNode(SENSOR_NOT_EXISTS));
                    roomSensors.appendChild(sensorNull);
                    break;
                default:
                    System.out.println("ola");

            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("log/File.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public static void printxmlNullSensor () {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("smart_home");
            doc.appendChild(rootElement);

            // error report elements
            Element errorsReport = doc.createElement("errors_report");
            rootElement.appendChild(errorsReport);


            // HOUSE
            Element house = doc.createElement("house");
            errorsReport.appendChild(house);


            // ROOM
            Element room =doc.createElement("room");
            house.appendChild(room);

            // SENSOR
            Element roomSensors = doc.createElement("sensor");
            room.appendChild(roomSensors);

            Element sensorNull = doc.createElement("cause");
            sensorNull.appendChild(doc.createTextNode(SENSOR_NOT_EXISTS));
            roomSensors.appendChild(sensorNull);
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();

        }

    }

    public static void printxmlReadingBeforeStartingDate () {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("smart_home");
            doc.appendChild(rootElement);

            // error report elements
            Element errorsReport = doc.createElement("errors_report");
            rootElement.appendChild(errorsReport);


            // HOUSE
            Element house = doc.createElement("house");
            errorsReport.appendChild(house);


            // ROOM
            Element room =doc.createElement("room");
            house.appendChild(room);

            // SENSOR
            Element roomSensors = doc.createElement("sensor");
            room.appendChild(roomSensors);

            Element sensorNull = doc.createElement("cause");
            sensorNull.appendChild(doc.createTextNode(READING_MESSAGE_ERROR));
            roomSensors.appendChild(sensorNull);
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();

        }

    }
}*/