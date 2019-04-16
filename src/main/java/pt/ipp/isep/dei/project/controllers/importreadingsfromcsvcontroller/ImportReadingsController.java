package pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.house.HouseService;
import pt.ipp.isep.dei.project.model.readings.ReadingDTO;
import pt.ipp.isep.dei.project.model.readings.ReadingMapper;
import pt.ipp.isep.dei.project.model.readings.RoomReadingId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;
import pt.ipp.isep.dei.project.services.GeoAreaSensorReadingsService;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeoAreaService;
import pt.ipp.isep.dei.project.services.RoomSensorService;
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
    /*private static final String READING_MESSAGE_ERROR = "GeoAreaReading not imported: date of reading is before starting date of sensor. ";
    private static final String DUPLICATE_READING_MESSAGE_ERROR = "GeoAreaReading not imported: duplicate reading ";
    private static final String SENSOR_NOT_ACTIVE = "GeoAreaReading not imported: Sensor is deactive on the specific date.";
    private static final String SENSOR_NOT_EXISTS = "GeoAreaReading not imported: Sensor does not exist.";
    private static final String DATE_INVALID = "GeoAreaReading not imported: Invalid date. ";
    */

    @Autowired
    private GeoAreaService geographicalAreaService;
    private GeoAreaSensor geoAreaSensor;
    private RoomSensor roomSensor;
    @Autowired
    private RoomSensorService roomSensorService;
    private List<Object> readingDTOList;
    private int numberOfNotImportedReadings;
    @Autowired
    private GeoAreaSensorReadingsService geoAreaSensorReadingsService;

    /**
     * Constructor.
     *
     * @param geographicalAreaService GeographicalAreaService to be used.
     * @param roomSensorService
     */
    public ImportReadingsController(GeoAreaService geographicalAreaService, RoomSensorService roomSensorService, GeoAreaSensorReadingsService geoAreaSensorReadingsService) {
        this.geographicalAreaService = geographicalAreaService;
        this.roomSensorService = roomSensorService;
        this.geoAreaSensorReadingsService = geoAreaSensorReadingsService;
    }

    /**
     * Method that checks if a given date time is before the starting date of the sensor.
     * @param startingDate
     * @param readingTimestamp
     * @return
     */
    public boolean isDateTimeBeforeStartingDate(LocalDateTime startingDate,LocalDateTime readingTimestamp) {
        return readingTimestamp.isBefore(startingDate);
    }

    public int getNumberOfNotImportedReadings() {
        return this.numberOfNotImportedReadings;
    }

    public boolean addReadingsToSensorById(int option) {
        if (option == 1) {
            return addReadingToGeoAreaSensorById();
        }
        if (option == 2) {
            return addReadingToRoomSensorById();
        }
        return false;
    }

    public boolean addReadingToRoomSensorById() {
        configLogFile();
        boolean imported = false;
        for (Object object : this.readingDTOList) {
            ReadingDTO reading = (ReadingDTO) object;
            RoomSensorId roomSensorId = new RoomSensorId(reading.getId());
            roomSensor = roomSensorService.getSensorById(roomSensorId);

            if(readingValidations(reading)){
                continue;
            }

            if (roomSensorService.addReading(ReadingMapper.mapToRoomReadingEntity(reading))) {
                imported = true;
            }
        }
        return imported;
    }

    public boolean addReadingToGeoAreaSensorById() {
        configLogFile();
        boolean imported = false;
        for (Object object : this.readingDTOList) {
            ReadingDTO reading = (ReadingDTO) object;
            GeoAreaSensorId geoAreaSensorId = new GeoAreaSensorId(reading.getId());
            geoAreaSensor = geographicalAreaService.getSensorById(geoAreaSensorId);

            if(readingValidations(reading)){
                continue;
            }

            if (geoAreaSensorReadingsService.addReading(ReadingMapper.mapToGeoAraReadingEntity(reading))) {
                imported = true;
            }
        }
        return imported;
    }


    private boolean readingValidations(ReadingDTO reading) {
        RoomSensorId roomSensorId = new RoomSensorId(reading.getId());
        if (isDateTimeBeforeStartingDate(roomSensor.getStartingDate(),reading.getDateTime())) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
            LOGGER.log(Level.WARNING, "GeoAreaReading not imported due to timestamp/date of reading being before starting date of sensor: " + invalidInfo);
            return true;
        }
        if (roomSensorService.isReadingDuplicated(new RoomReadingId(roomSensorId, reading.getDateTime()))) {
            numberOfNotImportedReadings++;
            String invalidInfo = "sensor id: " + roomSensor.getId() + ", timestamp/date: " + reading.getDateTime() + ", value: " + reading.getValue() + ".";
            LOGGER.log(Level.WARNING, "GeoAreaReading was not imported because the following reading is duplicated: doesn't exist:\n" + invalidInfo);
            return true;
        }
        if (Objects.isNull(roomSensor)) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + geoAreaSensor.getId() + ".";
            LOGGER.log(Level.WARNING, "GeoAreaReading was not imported because the following sensor id doesn't exist: " + invalidInfo);
            return true;
        }
        if (Objects.isNull(reading.getDateTime())) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
            LOGGER.log(Level.WARNING, "GeoAreaReading not imported due to invalid timestamp/date " + invalidInfo);
            return true;
        }
        if (reading.getUnits().equals("F")) {
            double celsiusValue = Utils.convertFahrenheitToCelsius(reading.getValue());
            reading.setValue(Utils.round(celsiusValue, 2));
            reading.setUnits("C");
            return false;
        }
        return false;
    }

    private boolean isValidFormat(String fileName) {
        return fileName.endsWith(".csv") || fileName.endsWith(".json") || fileName.endsWith(".xml");
    }


    /**
     * Method that configures the log file, using a FileHandler object to send log information to the specified log file.
     * The last line is responsible for not letting the information show up in the console.
     */
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

    /*
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

            //GeoAreaReading attribute id sensor
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

    }*/
}