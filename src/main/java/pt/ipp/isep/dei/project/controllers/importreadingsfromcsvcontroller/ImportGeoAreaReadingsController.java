package pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorList;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorList;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportGeoAreaReadingsController {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /*private static final String READING_MESSAGE_ERROR = "GeoAreaReading not imported: date of reading is before starting date of sensor. ";
    private static final String DUPLICATE_READING_MESSAGE_ERROR = "GeoAreaReading not imported: duplicate reading ";
    private static final String SENSOR_NOT_ACTIVE = "GeoAreaReading not imported: Sensor is deactive on the specific date.";
    private static final String SENSOR_NOT_EXISTS = "GeoAreaReading not imported: Sensor does not exist.";
    private static final String DATE_INVALID = "GeoAreaReading not imported: Invalid date. ";
    */

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

    /**
     * Constructor.
     *
     * @param geographicalAreaService GeographicalAreaService to be used.
     * @param houseService
     */
    public ImportGeoAreaReadingsController(GeographicalAreaService geographicalAreaService, HouseService houseService) {
        this.geographicalAreaService = geographicalAreaService;
        this.allSensorInTheGeoAreas = this.geographicalAreaService.getAllSensors();
        this.houseService = houseService;
        this.allSensorInTheHouse = this.houseService.getAllSensors();
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

    public boolean addReadingToGeoAreaSensorById() {
        boolean imported = false;
        long time = 0;
        for (Object object : this.readingDTOList) {
            ReadingDTO reading = (ReadingDTO) object;
            geoAreaSensor = geographicalAreaService.getSensorById(reading.getId());
            long startTime = System.currentTimeMillis();

            if (readingValidations(reading)) {
                continue;
            }
            long stopTime = System.currentTimeMillis();
            time += (stopTime - startTime) * 1000000000;
            //System.out.println(stopTime - startTime);

            if (geoAreaSensor.addReading(ReadingMapper.mapToEntity(reading))) {
                imported = true;
            } else {
                numberOfNotImportedReadings++;
                String invalidInfo = "sensor id: " + reading.getId() + ", timestamp/date: " + reading.getDateTime() + ", value: " + reading.getValue() + ".";
                LOGGER.log(Level.WARNING, "GeoAreaReading was not imported because the following reading is duplicated: \n" + invalidInfo);
            }
        }
        geographicalAreaService.updateRepository();
        System.out.println(time);
        return imported;
    }

    private boolean readingValidations(ReadingDTO reading) {
        if (Objects.isNull(geoAreaSensor)) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + reading.getId() + ".";
            LOGGER.log(Level.WARNING, "GeoAreaReading was not imported because the following sensor id doesn't exist: " + invalidInfo);
            return true;
        }
        if (Objects.isNull(reading.getDateTime())) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
            LOGGER.log(Level.WARNING, "GeoAreaReading not imported due to invalid timestamp/date " + invalidInfo);
            return true;
        }
        if (isDateTimeBeforeStartingDate(geoAreaSensor.getStartingDate(), reading.getDateTime())) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
            LOGGER.log(Level.WARNING, "GeoAreaReading not imported due to timestamp/date of reading being before starting date of sensor: " + invalidInfo);
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

    public boolean isValidFormat(String fileName) {
        return fileName.endsWith(".csv") || fileName.endsWith(".json") || fileName.endsWith(".xml");
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