package pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.RoomReading;
import pt.ipp.isep.dei.project.model.house.HouseService;
import pt.ipp.isep.dei.project.model.house.Room;
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

public class ImportRoomReadingsController {
    //private static final Logger LOGGER = Logger.getLogger(ImportRoomReadingsController.class.getName());
    /*private static final String READING_MESSAGE_ERROR = "GeoAreaReading not imported: date of reading is before starting date of sensor. ";
    private static final String DUPLICATE_READING_MESSAGE_ERROR = "GeoAreaReading not imported: duplicate reading ";
    private static final String SENSOR_NOT_ACTIVE = "GeoAreaReading not imported: Sensor is deactive on the specific date.";
    private static final String SENSOR_NOT_EXISTS = "GeoAreaReading not imported: Sensor does not exist.";
    private static final String DATE_INVALID = "GeoAreaReading not imported: Invalid date. ";
    */
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private RoomSensor roomSensor;
    private Room room;
    private RoomSensorList allSensorInTheHouse;
    private List<Object> readingDTOList;
    private int numberOfNotImportedReadings;
    @Autowired
    private HouseService houseService;

    /**
     * Constructor
     *
     * @param houseService
     */
    public ImportRoomReadingsController(HouseService houseService) {
        this.houseService = houseService;
        this.allSensorInTheHouse = this.houseService.getAllSensors();
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

    /**
     * Method that checks if a given date time is before the starting date of the sensor.
     *
     * @param startingDate
     * @param readingTimestamp
     * @return
     */
    public boolean isDateTimeBeforeStartingDate(LocalDateTime startingDate, LocalDateTime readingTimestamp) {
        return readingTimestamp.isBefore(startingDate);
    }

    public int getNumberOfNotImportedReadings() {
        return this.numberOfNotImportedReadings;
    }

    public boolean addReadingToRoomSensorById() {
        configLogFile();
        boolean imported = false;
        for (Object object : this.readingDTOList) {
            ReadingDTO reading = (ReadingDTO) object;
            roomSensor = houseService.getSensorById(reading.getId());

            if (readingValidations(reading)) {
                continue;
            }

            if (roomSensor.addRoomReading(new RoomReading(reading.getValue(), reading.getDateTime()))) {
                imported = true;
            } else {
                numberOfNotImportedReadings++;
                String invalidInfo = "sensor id: " + reading.getId() + ", timestamp/date: " + reading.getDateTime() + ", value: " + reading.getValue() + ".";
                LOGGER.log(Level.WARNING, "Room reading was not imported because the following reading is duplicated: \n" + invalidInfo);
            }
        }
        return imported;
    }

    private boolean readingValidations(ReadingDTO reading) {
        if (Objects.isNull(roomSensor)) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + reading.getId() + ".";
            LOGGER.log(Level.WARNING, "Room reading was not imported because the following sensor id doesn't exist: " + invalidInfo);
            return true;
        }
        if (Objects.isNull(reading.getDateTime())) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
            LOGGER.log(Level.WARNING, "Room reading not imported due to invalid timestamp/date " + invalidInfo);
            return true;
        }
        if (isDateTimeBeforeStartingDate(roomSensor.getStartingDate(), reading.getDateTime())) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
            LOGGER.log(Level.WARNING, "Room reading was not imported due to timestamp/date of reading being before starting date of sensor: " + invalidInfo);
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

    public ProjectFileReader createReader(String path) {
        return Utils.createReader(path);
    }

    public List<Object> readFile(File file, String path) throws FileNotFoundException {
        ProjectFileReader fileReader = createReader(path);
        readingDTOList = fileReader.readFile(file);
        return readingDTOList;
    }
}