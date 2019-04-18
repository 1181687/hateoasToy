package pt.ipp.isep.dei.project.controllers.importreadingscontroller;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.readings.*;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReadingId;
import pt.ipp.isep.dei.project.model.readings.ReadingDTO;
import pt.ipp.isep.dei.project.model.readings.ReadingMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.services.GeoAreaService;
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

public class ImportGeoAreaReadingsController {
    private static final Logger LOGGER = Logger.getLogger(ImportGeoAreaReadingsController.class.getName());
    /*private static final String READING_MESSAGE_ERROR = "GeoAreaReading not imported: date of reading is before starting date of sensor. ";
    private static final String DUPLICATE_READING_MESSAGE_ERROR = "GeoAreaReading not imported: duplicate reading ";
    private static final String SENSOR_NOT_ACTIVE = "GeoAreaReading not imported: Sensor is deactive on the specific date.";
    private static final String SENSOR_NOT_EXISTS = "GeoAreaReading not imported: Sensor does not exist.";
    private static final String DATE_INVALID = "GeoAreaReading not imported: Invalid date. ";
    */

    @Autowired
    private GeoAreaService geographicalAreaService;
    private GeoAreaSensor geoAreaSensor;
    private List<Object> readingDTOList;
    private int numberOfNotImportedReadings;

    /**
     * Constructor.
     *
     * @param geographicalAreaService
     */
    public ImportGeoAreaReadingsController(GeoAreaService geographicalAreaService) {
        this.geographicalAreaService = geographicalAreaService;
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

    public boolean addReadingToGeoAreaSensorById() {
        configLogFile();
        boolean imported = false;
        for (Object object : this.readingDTOList) {
            ReadingDTO reading = (ReadingDTO) object;
            GeoAreaSensorId geoAreaSensorId = new GeoAreaSensorId(reading.getId());
            geoAreaSensor = geographicalAreaService.getSensorById(geoAreaSensorId);

            if (readingValidations(reading)) {
                continue;
            }

            if (geographicalAreaService.addReading(ReadingMapper.mapToGeoAraReadingEntity(reading))) {
                imported = true;
            }
        }
        return imported;
    }

    private boolean readingValidations(ReadingDTO reading) {
        GeoAreaSensorId geoAreaSensorId = new GeoAreaSensorId(reading.getId());
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
        if (geographicalAreaService.isReadingDuplicated(new GeoAreaReadingId(geoAreaSensorId, reading.getDateTime()))) {
            numberOfNotImportedReadings++;
            String invalidInfo = "sensor id: " + reading.getId() + ", timestamp/date: " + reading.getDateTime() + ", value: " + reading.getValue() + ".";
            LOGGER.log(Level.WARNING, "GeoAreaReading was not imported because the following reading is duplicated: doesn't exist:\n" + invalidInfo);
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